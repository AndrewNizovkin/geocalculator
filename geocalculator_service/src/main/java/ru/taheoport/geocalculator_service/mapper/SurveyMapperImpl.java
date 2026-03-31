package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for
 * converting external data to and from the model format.
 */
@Component
@RequiredArgsConstructor
public class SurveyMapperImpl implements SurveyMapper{

    private final DataMapper dataMapper;
    /**
     * Extracts model data from the Leica total station file
     *
     * @param lines            list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromLeica(List<String> lines, SurveyRepository surveyRepository) {
        surveyRepository.clearAll();
        boolean success = true;
        String separator = "\\s+";
//        String separator = " ";
        String code = "Not";
        String currentToolHeight = "2000";
        String[] lineArray;
        String[] stringBuffer;

        while (!lines.isEmpty()) {
            lineArray = lines.removeFirst().split(separator);
            stringBuffer = new String[6];

            switch (lineArray[0].substring(0, 2)) {

                case "41" -> {
                    code = lineArray[0].substring(7);
                }

                case "11" -> {
                    for (String string : lineArray) {
                        switch (string.substring(0, 2)){

                            //targetNumber
                            case "11" -> {
                                stringBuffer[0] = string.substring(7);
                            }

                            //targetInclinedDistance
                            case "31" -> {
                                stringBuffer[1] = string.substring(7);
                            }

                            //targetDirection
                            case "21" -> {
                                stringBuffer[2] = string.substring(7);
                            }

                            //targetTiltAngle
                            case "22" -> {
                                stringBuffer[3] = string.substring(7);
                            }

                            //targetHeight
                            case "87" -> {
                                stringBuffer[4] = string.substring(7);
                            }

                            //stationHeight
                            case "88" -> {
                                stringBuffer[5] = string.substring(7);
                            }
                        }

                        stringBuffer[0] = code.equals("Not") ? stringBuffer[0] : code;

                    }

                    if (!currentToolHeight.equals(stringBuffer[5])) {
                        currentToolHeight = stringBuffer[5];
                        SurveyStation station = surveyRepository.addNewStation();
                        station.setStationHeight(dataMapper.leicaToMillimeter(stringBuffer[5]));
                    }

                    Measurement target = surveyRepository.addNewMeasurement(surveyRepository.size() - 1);
                    target.setTargetName(dataMapper.removeFirstZero(stringBuffer[0]));
                    target.setTargetInclinedDistance(dataMapper.leicaToMillimeter(stringBuffer[1]));
                    target.setTargetDirection(dataMapper.leicaToDirection(stringBuffer[2], 1));
                    target.setTargetTiltAngle(dataMapper.leicaToTiltAngle(stringBuffer[3], 1));
                    target.setTargetHeight(dataMapper.leicaToMillimeter(stringBuffer[4]));

                }

            }
        }

        if (surveyRepository.size() == 0) success = false;

        return success;
    }

    /**
     * Extracts model data from the Nikon total station file
     * @param lines list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromNikon(List<String> lines, SurveyRepository surveyRepository) {
        surveyRepository.clearAll();
        boolean success = true;
        String separator = ",";

        while (!lines.isEmpty()) {
            String[] linesArray = lines.removeFirst().split(separator);

            switch (linesArray[0]) {

                case "ST" -> {
                    SurveyStation station = surveyRepository.addNewStation();
                    station.setStationName(linesArray[1]);
                    station.setOrName(linesArray[3]);
                    station.setStationHeight(dataMapper.meterToMillimeter(linesArray[5]));
                }

                case "SS" -> {
                    if (surveyRepository.size() > 0 ) {
                        Measurement target = surveyRepository.addNewMeasurement(surveyRepository.size() - 1);
                        target.setTargetName(linesArray[7]);
                        target.setTargetInclinedDistance(dataMapper.meterToMillimeter(linesArray[3]));
                        target.setTargetDirection(dataMapper.dmsToSeconds(linesArray[4]));
                        target.setTargetTiltAngle(dataMapper.dmsToSeconds(linesArray[5]));
                        target.setTargetHeight(dataMapper.meterToMillimeter(linesArray[2]));
                    }
                }
            }
        }

        if (surveyRepository.size() == 0) success = false;

        return success;
    }

    /**
     * Extracts model data from the Topcon total station file
     *
     * @param lines            list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromTopcon(List<String> lines, SurveyRepository surveyRepository) {
        surveyRepository.clearAll();
        boolean success = true;
        String[] stations;
        String[] targets;
        String[] measurements;

        for (String line : lines) {

            stations = line.split("_'");
            for (String station : stations) {
                if (station.isEmpty()) continue;
                SurveyStation surveyStation = surveyRepository.addNewStation();
                surveyStation.setStationName(station.substring(0, station.indexOf('_')));
                surveyStation.setStationHeight(dataMapper.meterToMillimeter(station.substring(
                        station.indexOf(')') + 1,
                        station.indexOf("_+")
                )));
                targets = station.substring(station.indexOf("_+") + 2)
                        .split("_\\+");
                for (String target : targets) {
                    Measurement measurement = surveyRepository.addNewMeasurement(surveyRepository.size() - 1);
                    measurement.setTargetName(target.substring(0, target.indexOf('_')));

                    measurements = target
                            .substring(target.indexOf('?'))
                            .replaceAll("[+?*_(),dm\\n]", " ")
                            .trim()
                            .split("\\s+");
                    measurement.setTargetInclinedDistance(dataMapper.leicaToMillimeter(measurements[0]));
                    measurement.setTargetTiltAngle(dataMapper.leicaToTiltAngle(measurements[1], 0));
                    measurement.setTargetDirection(dataMapper.leicaToDirection(measurements[2], 0));
                    measurement.setTargetHeight(dataMapper.meterToMillimeter(measurements[6]));
                }

            }

        }

        if (surveyRepository.size() == 0) success = false;

        return success;
    }

    /**
     * Creates list of strings in tah-format from survey model
     *
     * @param surveyRepository survey model
     * @return list of strings in tah-format
     */
    @Override
    public List<String> surveyToListTah(SurveyRepository surveyRepository) {
        List<String> listTah = new ArrayList<>();

        String separator = " ";

        for (int i = 0; i < surveyRepository.size(); i++) {
            StringBuilder line = new StringBuilder();

            line.append(surveyRepository.getStationName(i)).append(separator);
            line.append(dataMapper.millimeterToMeter(surveyRepository.getStationX(i))).append(separator);
            line.append(dataMapper.millimeterToMeter(surveyRepository.getStationY(i))).append(separator);
            line.append(dataMapper.millimeterToMeter(surveyRepository.getStationZ(i))).append(separator);
            line.append(dataMapper.millimeterToMeter(surveyRepository.getStationHeight(i))).append(separator);
            line.append(surveyRepository.getOrName(i)).append(separator);
            line.append(dataMapper.millimeterToMeter(surveyRepository.getOrX(i))).append(separator);
            line.append(dataMapper.millimeterToMeter(surveyRepository.getOrY(i)));

            listTah.add(line.toString());
        }

        listTah.add("//");

        for (int i = 0; i < surveyRepository.size(); i++) {
            for (int j = 0; j < surveyRepository.measurementSize(i); j++) {
                StringBuilder line = new StringBuilder();

                line.append(surveyRepository.getTargetName(i, j)).append(separator);
                line.append(dataMapper.millimeterToMeter(surveyRepository.getTargetInclinedDistance(i, j))).append(separator);
                line.append(dataMapper.secondsToDms(surveyRepository.getTargetDirection(i, j))).append(separator);
                line.append(dataMapper.secondsToDms(surveyRepository.getTargetTiltAngle(i, j))).append(separator);
                line.append(dataMapper.millimeterToMeter(surveyRepository.getTargetHeight(i, j))).append(separator);
                line.append(i);

                listTah.add(line.toString());
            }
            listTah.add("//");
        }
        return listTah;
    }

    /**
     * Creates general report of geodetic survey processing
     *
     * @param surveyRepository survey model
     * @return list of strings
     */
    @Override
    public List<String> surveyToReports(SurveyRepository surveyRepository) {
        return List.of();
    }

    /**
     * Extracts model data from surveyRequest
     *
     * @param surveyRequest    list of string in tah+ format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean surveyRequestToSurvey(List<String> surveyRequest, SurveyRepository surveyRepository) {
        boolean success = true;
        if (surveyRequest.isEmpty()) return false;
        String[] station;
        String[] target;
        int stationIndex;
        String line = surveyRequest.removeFirst();

        while (!line.equals("//") && !line.isEmpty()) {
            station = line.split("\\s+");
            if (station.length != 9) continue;

            SurveyStation surveyStation = surveyRepository.addNewStation();
            surveyStation.setStationName(station[0]);
            surveyStation.setStationX(dataMapper.meterToMillimeter(station[1]));
            surveyStation.setStationY(dataMapper.meterToMillimeter(station[2]));
            surveyStation.setStationZ(dataMapper.meterToMillimeter(station[3]));
            surveyStation.setStationHeight(dataMapper.meterToMillimeter(station[4]));
            surveyStation.setOrDirection(dataMapper.dmsToSeconds(station[5]));
            surveyStation.setOrName(station[6]);
            surveyStation.setOrX(dataMapper.meterToMillimeter(station[7]));
            surveyStation.setOrY(dataMapper.meterToMillimeter(station[8]));
            line = surveyRequest.removeFirst();
        }

        while (!line.isEmpty()) {
            line = surveyRequest.removeFirst();
            if (line.equals("//")) continue;

            target = line.split("\\s+");
            if (target.length != 6) continue;

            stationIndex = Integer.parseInt(target[5]);
            Measurement measurement = surveyRepository.addNewMeasurement(stationIndex);
            measurement.setTargetName(target[0]);
            measurement.setTargetInclinedDistance(dataMapper.meterToMillimeter(target[1]));
            measurement.setTargetDirection(dataMapper.dmsToSeconds(target[2]));
            measurement.setTargetTiltAngle(dataMapper.dmsToSeconds(target[3]));
            measurement.setTargetHeight(dataMapper.meterToMillimeter(target[4]));
        }

        if (surveyRepository.size() == 0) success = false;

        return success;
    }
}

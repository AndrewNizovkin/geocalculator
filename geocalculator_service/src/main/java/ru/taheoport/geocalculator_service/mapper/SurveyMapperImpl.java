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
                    target.setTargetDirection(dataMapper.leicaToDirection(stringBuffer[2]));
                    target.setTargetTiltAngle(dataMapper.leicaToTiltAngle(stringBuffer[3]));
                    target.setTargetHeight(dataMapper.leicaToMillimeter(stringBuffer[4]));

                }

            }
        }

        if (surveyRepository.size() == 0) success = false;

        return success;
    }

    /**
     * Extracts model data from the Nikon total station file
     *
     * @param lines            list of string in gis-format
     * @param surveyRepository survey model
     * @return This is true if the data contains a geodetic survey.
     */
    @Override
    public boolean readFromNikon(List<String> lines, SurveyRepository surveyRepository) {
        return false;
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
        return false;
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
}

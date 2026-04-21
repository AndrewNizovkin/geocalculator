package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.dto.ReportResiduals;
import ru.taheoport.geocalculator_service.model.BindType;
import ru.taheoport.geocalculator_service.model.PolygonStation;
import ru.taheoport.geocalculator_service.model.Residuals;
import ru.taheoport.geocalculator_service.model.ValidResiduals;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for
 * converting external data to and from the model format.
 */
@Component
@RequiredArgsConstructor
public class PolygonMapperImpl implements PolygonMapper{

    private final DataMapper dataMapper;

    private final PolygonRepository polygonRepository;

    private final Residuals residuals;

    private final ValidResiduals validResiduals;

    private final ReportResiduals reportResiduals;

    /**
     * Extract data from polygonRequest and fills polygon model
     *
     * @param polygonRequest    list of string with data
     * @return true if polygonRequest contains valid geodetic data
     */
    @Override
    public boolean polygonRequestToPolygon(List<String> polygonRequest) {
        polygonRepository.clearAll();
        boolean Success = true;
        String target = "";

        for (String line : polygonRequest) {
            if (line.equals("#valid-residuals")) {
                target = "valid-residuals";
                continue;
            }
            if (line.equals("#polygon")) {
                target = "polygon";
                continue;
            }

            switch (target) {

                case "valid-residuals" -> {
                    String[] record = line.split("=");
                    switch (record[0]) {

                        case "elevation" -> {
                            validResiduals.setValidElevation(Integer.parseInt(record[1]));
                        }

                        case "angle" -> {
                            validResiduals.setValidAngle(Integer.parseInt(record[1]));
                        }

                        case "absolute" -> {
                            validResiduals.setValidAbsolute(record[1]);
                        }

                        case "relative" -> {
                            validResiduals.setValidRelative(record[1]);
                        }
                    }
                }

                case "polygon" -> {
                    String[] record = line.trim().split("\\s+");
                    if (record.length != 7) continue;
                    PolygonStation polygonStation = polygonRepository.addNewStation();

                    polygonStation.setStationName(record[0]);

                    if (record[1].equals("Not")) {
                        polygonStation.setHorAngle(0);
                    } else {
                        polygonStation.setHorAngle(dataMapper.dmsToSeconds(record[1]));
                    }

                    if (record[2].equals("Not")) {
                        polygonStation.setHorDistance(0);
                    } else {
                        polygonStation.setHorDistance(dataMapper.meterToMillimeter(record[2]));
                    }

                    if (record[3].equals("Not")) {
                        polygonStation.setElevation(0);
                    } else {
                        polygonStation.setElevation(dataMapper.meterToMillimeter(record[3]));
                    }

                    if (record[4].equals("Not") || record[5].equals("Not") || record[6].equals("Not")) {
                        polygonStation.setStatus(false);
                        polygonStation.setStationX(0);
                        polygonStation.setStationY(0);
                        polygonStation.setStationZ(0);
                    } else {
                        polygonStation.setStatus(true);
                        polygonStation.setStationX(dataMapper.meterToMillimeter(record[4]));
                        polygonStation.setStationY(dataMapper.meterToMillimeter(record[5]));
                        polygonStation.setStationZ(dataMapper.meterToMillimeter(record[6]));
                    }
                }
            }

        }

        if (polygonRepository.size() == 0) Success = false;
        return Success;
    }

    /**
     * Creates response contains processing reports
     * @return List of strings
     */
    @Override
    public List<String> polygonToPolygonResponse() {
        List<String> polygonReports = new ArrayList<>();
        StringBuilder reportLine = new StringBuilder();

        polygonReports.add("#residuals");

        reportLine
                .append("elevation=")
                .append(reportResiduals.getActualElevation());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        reportLine
                .append("angle=")
                .append(reportResiduals.getActualAngle());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        reportLine
                .append("x=")
                .append(reportResiduals.getActualX());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        reportLine
                .append("y=")
                .append(reportResiduals.getActualY());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        reportLine
                .append("absolute=")
                .append(reportResiduals.getActualAbsolute());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        reportLine
                .append("relative=")
                .append(reportResiduals.getActualRelative());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        reportLine
                .append("perimeter=")
                .append(reportResiduals.getPerimeter());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        polygonReports.add("#report-catalog");
        for (int i = 0; i < polygonRepository.size(); i++) {
            PolygonStation station = polygonRepository.getStationById(i);
            reportLine
                    .append(station.getStationName()).append(" ")
                    .append(dataMapper.millimeterToMeter(station.getStationX())).append(" ")
                    .append(dataMapper.millimeterToMeter(station.getStationY())).append(" ")
                    .append(dataMapper.millimeterToMeter(station.getStationZ()));
            polygonReports.add(reportLine.toString());
            reportLine.setLength(0);
        }

        polygonReports.add("#report-plan");
        polygonReports.add("");
        polygonReports.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonReports.add("");
        polygonReports.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonReports.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonReports.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonReports.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonReports.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonReports.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonReports.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonReports.add("");
        polygonReports.add("");
        polygonReports.add("");







        return polygonReports;
    }

    /**
     * Creates error response
     *
     * @param message string message
     * @return list of strings
     */
    @Override
    public List<String> getErrorResponse(String message) {

        return List.of("#error", message);
    }

    /**
     * Sets blank values for reportResiduals
     */
    @Override
    public void clearReportResiduals() {
        String blank = "-.-";
        reportResiduals.setPerimeter(blank);
        reportResiduals.setActualElevation(blank);
        reportResiduals.setActualAngle(blank);
        reportResiduals.setActualX(blank);
        reportResiduals.setActualY(blank);
        reportResiduals.setActualAbsolute(blank);
        reportResiduals.setActualRelative(blank);
        reportResiduals.setValidElevation(blank);
        reportResiduals.setValidAngle(blank);
        reportResiduals.setValidAbsolute(blank);
        reportResiduals.setValidRelative(blank);
    }

    /**
     * Sets report residuals
     */
    @Override
    public void setReportResiduals() {
        reportResiduals.setPerimeter(dataMapper.millimeterToMeter(residuals.getPerimeter()));
        reportResiduals.setValidRelative(validResiduals.getValidRelative());
        reportResiduals.setValidAbsolute(validResiduals.getValidAbsolute());
        reportResiduals.setValidElevation(String.valueOf(Math.round(validResiduals.getValidElevation() * Math.sqrt(residuals.getPerimeter() / 1000000.0))));

        if (residuals.getBindType() == BindType.TZ || residuals.getBindType() == BindType.ZT) {
            return;
        }

        if (residuals.getBindType() == BindType.TT) {
            reportResiduals.setActualAngle(String.valueOf(residuals.getAngle()));
            reportResiduals.setValidAngle(String.valueOf(Math.round(validResiduals.getValidAngle() * Math.sqrt((polygonRepository.size() - 2)))));
        }

        reportResiduals.setActualElevation(String.valueOf(residuals.getElevation()));
        reportResiduals.setActualX(dataMapper.millimeterToMeter(residuals.getLinearX()));
        reportResiduals.setActualY(dataMapper.millimeterToMeter(residuals.getLinearY()));
        reportResiduals.setActualAbsolute(dataMapper.millimeterToMeter(residuals.getAbsolute()));
        reportResiduals.setActualRelative(residuals.getRelative());

    }
}

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
import java.util.Locale;

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
                        polygonStation.setElevationCorrected(0);
                    } else {
                        polygonStation.setElevation(dataMapper.meterToMillimeter(record[3]));
                        polygonStation.setElevationCorrected(polygonStation.getElevation());
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

        PolygonStation firstStation = polygonRepository.getStationById(0);
        switch (residuals.getBindType()) {

            case TT, TO, TZ -> {
                reportLine
                        .append("| ")
                        .append(dataMapper.stringToTableRight(firstStation.getStationName(), 10))
                        .append(" |          |          |         |          |          |        |          |        | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getStationX()), 12))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getStationY()), 12))
                        .append(" |");
                polygonReports.add(reportLine.toString());
                reportLine.setLength(0);
                reportLine
                        .append("|            |          |          |         | ")
                        .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(firstStation.getDirectionAngle()), 8))
                        .append(" |          |        |          |        |              |              |");
                polygonReports.add(reportLine.toString());
                reportLine.setLength(0);

//                polygonReports.add("|            |          |          |         |          |          |        |          |        |              |              |");
            }

            case OO, OT, ZT -> {
                reportLine
                        .append("| ")
                        .append(dataMapper.stringToTableRight(firstStation.getStationName(), 10))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(firstStation.getHorAngle()), 8))
                        .append(" |          | ")
                        .append(dataMapper.stringToTableRight(String.format(Locale.US,"%.2f", firstStation.getCorrectionHorAngle()), 7))
                        .append(" |          |          |        |          |        | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getStationX()), 12))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getStationY()), 12))
                        .append(" |");
                polygonReports.add(reportLine.toString());
                reportLine.setLength(0);
                reportLine
                        .append("|            |          | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getHorDistance()), 8))
                        .append(" |         | ")
                        .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(firstStation.getDirectionAngle()), 8))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getDeltaX()), 8))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getCorrectionX()), 6))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getDeltaY()), 8))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(firstStation.getCorrectionY()), 6))
                        .append(" |              |              |");
                polygonReports.add(reportLine.toString());
                reportLine.setLength(0);
            }
        }

        PolygonStation station;
        for (int i = 1; i < polygonRepository.size() - 2; i++) {
            station = polygonRepository.getStationById(i);
            reportLine
                    .append("| ")
                    .append(dataMapper.stringToTableRight(station.getStationName(), 10))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(station.getHorAngle()), 8))
                    .append(" |          | ")
                    .append(dataMapper.stringToTableRight(String.format(Locale.US,"%.2f", station.getCorrectionHorAngle()), 7))
                    .append(" |          |          |        |          |        | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getStationX()), 12))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getStationY()), 12))
                    .append(" |");
            polygonReports.add(reportLine.toString());
            reportLine.setLength(0);
            reportLine
                    .append("|            |          | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getHorDistance()), 8))
                    .append(" |         | ")
                    .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(station.getDirectionAngle()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getDeltaX()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getCorrectionX()), 6))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getDeltaY()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(station.getCorrectionY()), 6))
                    .append(" |              |              |");
            polygonReports.add(reportLine.toString());
            reportLine.setLength(0);
        }

        PolygonStation beforeLastStation = polygonRepository.getStationById(polygonRepository.size() - 2);
        reportLine
                .append("| ")
                .append(dataMapper.stringToTableRight(beforeLastStation.getStationName(), 10))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(beforeLastStation.getHorAngle()), 8))
                .append(" |          | ")
                .append(dataMapper.stringToTableRight(String.format(Locale.US, "%.2f", beforeLastStation.getCorrectionHorAngle()), 7))
                .append(" |          |          |        |          |        | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getStationX()), 12))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getStationY()), 12))
                .append(" |");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        switch (residuals.getBindType()) {
            case OO, TO, TZ -> {
                reportLine
                        .append("|            |          | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getHorDistance()), 8))
                        .append(" |         | ")
                        .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(beforeLastStation.getDirectionAngle()), 8))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getDeltaX()), 8))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getCorrectionX()), 6))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getDeltaY()), 8))
                        .append(" | ")
                        .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(beforeLastStation.getCorrectionY()), 6))
                        .append(" |              |              |");
                polygonReports.add(reportLine.toString());
                reportLine.setLength(0);
            }

            case TT, OT, ZT -> {
                reportLine
                        .append("|            |          |          |         | ")
                        .append(dataMapper.stringToTableRight(dataMapper.secondsToDms(beforeLastStation.getDirectionAngle()), 8))
                        .append(" |          |        |          |        |              |              |");
                polygonReports.add(reportLine.toString());
                reportLine.setLength(0);
            }
        }

        PolygonStation lastStation = polygonRepository.getStationById(polygonRepository.size() - 1);
        reportLine
                .append("| ")
                .append(dataMapper.stringToTableRight(lastStation.getStationName(), 10))
                .append(" |          |          |         |          |          |        |          |        | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(lastStation.getStationX()), 12))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(lastStation.getStationY()), 12))
                .append(" |");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        polygonReports.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonReports.add("");
        polygonReports.add("   Технические параметры полигона");

        reportLine.append("Периметр полигона : ").append(reportResiduals.getPerimeter()).append("м.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        polygonReports.add("     1. Угловые невязки");
        reportLine.append("фактическая : ").append(reportResiduals.getActualAngle()).append("сек.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine.append("допустимая : ").append(reportResiduals.getValidAngle()).append("сек.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        polygonReports.add("     2. Линейные невязки");
        reportLine.append("DX : ").append(reportResiduals.getActualX()).append("м.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine.append("DY : ").append(reportResiduals.getActualY()).append("м.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine.append("фактическая абсолютная : ").append(reportResiduals.getActualAbsolute()).append("м.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine.append("допустимая абсолютная : ").append(reportResiduals.getValidAbsolute()).append("м.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine.append("фактическая относительная : ").append(reportResiduals.getActualRelative());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine.append("допустимая относительная : ").append(reportResiduals.getValidRelative());
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

        polygonReports.add("#report-elevation");
        polygonReports.add("");
        polygonReports.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonReports.add("");
        polygonReports.add("-----------------------------------------------------------------------");
        polygonReports.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonReports.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonReports.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonReports.add("|------------|----------|------------|--------|------------|----------|");
        polygonReports.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonReports.add("|------------|----------|------------|--------|------------|----------|");

        long sumElevation = 0;
        double sumCorrectionZ = 0.0;
        long sumElevationCorrected = 0;
        int start = 0;
        int end = polygonRepository.size() - 1;
        if (residuals.getBindType() == BindType.TT ||
                        residuals.getBindType() == BindType.TO ||
                        residuals.getBindType() == BindType.TZ) start = 1;
        if (residuals.getBindType() == BindType.TT ||
                residuals.getBindType() == BindType.OT ||
                residuals.getBindType() == BindType.ZT) end = polygonRepository.size() - 2;

        PolygonStation polygonStation;
        for (int i = start; i < end; i++) {
            polygonStation = polygonRepository.getStationById(i);
            sumElevation += polygonStation.getElevation();
            sumCorrectionZ += polygonStation.getCorrectionZ();
            sumElevationCorrected += polygonStation.getElevationCorrected();

            reportLine
                    .append("| ")
                    .append(dataMapper.stringToTableRight(polygonStation.getStationName(), 10))
                    .append(" |          |            |        |            | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(polygonStation.getStationZ()), 8))
                    .append(" |");
            polygonReports.add(reportLine.toString());
            reportLine.setLength(0);
            reportLine
                    .append("|            | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(polygonStation.getHorDistance()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(polygonStation.getElevation()), 10))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(String.format(Locale.US, "%.2f", polygonStation.getCorrectionZ()), 6))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(polygonStation.getElevationCorrected()), 10))
                    .append(" |          |");
            polygonReports.add(reportLine.toString());
            reportLine.setLength(0);
        }

        reportLine
                .append("| ")
                .append(dataMapper.stringToTableRight(polygonRepository.getStationById(end).getStationName(), 10))
                .append(" |          |            |        |            | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(polygonRepository.getStationById(end).getStationZ()), 8))
                .append(" |");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);


        polygonReports.add("|------------|----------|------------|--------|------------|----------|");
        reportLine
                .append("|контр.суммы | ")
                .append(dataMapper.stringToTableRight(reportResiduals.getPerimeter(), 8))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(sumElevation), 10))
                .append(" | ")
                .append(dataMapper.stringToTableRight(String.format(Locale.US, "%.2f", sumCorrectionZ), 6))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(sumElevationCorrected), 10))
                .append(" |          |");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        polygonReports.add("-----------------------------------------------------------------------");
        polygonReports.add("");

        polygonReports.add("   Технические параметры полигона");
        reportLine
                .append("Периметр полигона : ")
                .append(reportResiduals.getPerimeter())
                .append("м.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine
                .append("фактическая высотная невязка : ")
                .append(reportResiduals.getActualElevation())
                .append("мм.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);
        reportLine
                .append("допустимая высотная невязка : ")
                .append(reportResiduals.getValidElevation())
                .append("мм.");
        polygonReports.add(reportLine.toString());
        reportLine.setLength(0);

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

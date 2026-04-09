package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

    /**
     * Extract data from polygonRequest and fills polygon model
     *
     * @param polygonRequest    list of string with data
     * @param polygonRepository polygon station repository
     * @param validResiduals    instance of ValidResiduals
     * @return true if polygonRequest contains valid geodetic data
     */
    @Override
    public boolean polygonRequestToPolygon(
            List<String> polygonRequest,
            PolygonRepository polygonRepository,
            ValidResiduals validResiduals) {
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
     *
     * @param polygonRepository polygon station repository
     * @param validResiduals    instance of ValidResiduals
     * @param residuals         instance of Residuals
     * @return List of strings
     */
    @Override
    public List<String> polygonToPolygonResponse(
            PolygonRepository polygonRepository,
            ValidResiduals validResiduals,
            Residuals residuals) {
        return List.of();
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
}

package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;
import ru.taheoport.geocalculator_service.model.Target;
import ru.taheoport.geocalculator_service.repository.ExtractRepository;

import java.util.List;

/**
 * This class provides methods for
 *  converting external data to and from the model format.
 */
@Component
@RequiredArgsConstructor
public class ExtractMapperImpl implements ExtractMapper{

    private final DataMapper dataMapper;

    private final ExtractRepository extractRepository;

    /**
     * Extract data from polygonRequest and fills Extraction model
     * @param extractRequest list of string with geodetic data
     * @return true if extractRequest contains valid geodetic data
     */
    @Override
    public boolean extractRequestToExtraction(List<String> extractRequest) {
        boolean success = true;
        extractRepository.clearAll();
        if (extractRequest.isEmpty()) return false;
        String[] station;
        String[] target;
        int stationIndex;
        String line = extractRequest.removeFirst();

        while (!line.equals("//") && !extractRequest.isEmpty()) {
            station = line.split("\\s+");
            if (station.length != 8) {
                line = extractRequest.removeFirst();
                continue;
            }

            Extraction extraction = extractRepository.addNewExtraction();
            extraction.setStationName(station[0]);
            extraction.setStationHeight(dataMapper.meterToMillimeter(station[4]));
            line = extractRequest.removeFirst();
        }

        while (!extractRequest.isEmpty()) {
            line = extractRequest.removeFirst();
            if (line.equals("//")) continue;

            target = line.split("\\s+");
            if (target.length != 6) continue;

            stationIndex = Integer.parseInt(target[5]);
            Measurement measurement = extractRepository.addNewMeasurement(stationIndex);
            measurement.setTargetName(target[0]);
            measurement.setTargetInclinedDistance(dataMapper.meterToMillimeter(target[1]));
            measurement.setTargetDirection(dataMapper.dmsToSeconds(target[2]));
            measurement.setTargetTiltAngle(dataMapper.dmsToSeconds(target[3]));
            measurement.setTargetHeight(dataMapper.meterToMillimeter(target[4]));
        }

        if (extractRepository.size() == 0) success = false;

        return success;
    }

    /**
     * Creates response contains processing reports
     *
     * @return list of strings
     */
    @Override
    public List<String> extractionToExtractResponse() {
        return List.of();
    }

    /**
     * Creates error response
     * @param message string message
     * @return list of strings
     */
    @Override
    public List<String> getErrorResponse(String message) {
        return List.of("#error", message);
    }
}

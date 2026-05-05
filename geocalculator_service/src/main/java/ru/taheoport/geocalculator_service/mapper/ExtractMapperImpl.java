package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.Solution;
import ru.taheoport.geocalculator_service.repository.ExtractRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepository;

import java.util.ArrayList;
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

    private final SolutionRepository solutionRepository;

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
    public List<String> solutionToExtractResponse() {
        List<String> extractResponse = new ArrayList<>();
        Solution solution;
        StringBuilder line = new StringBuilder();

        extractResponse.add("#extract-pol");

        for (int i = 0; i < solutionRepository.size(); i++) {
            solution = solutionRepository.getSolutionById(i);
            line
                    .append(solution.getStationName())
                    .append(" ")
                    .append(dataMapper.secondsToDms(solution.getHorAngle()))
                    .append(" ")
                    .append(dataMapper.millimeterToMeter(solution.getAverageHorDistance()))
                    .append(" ")
                    .append(dataMapper.millimeterToMeter(solution.getAverageElevation()))
                    .append(" Not Not Not");
            extractResponse.add(line.toString());
            line.setLength(0);
        }

        extractResponse.add("#extract-report");
        extractResponse.add("");
        extractResponse.add("              ВЕДОМОСТЬ ВЫЧИСЛЕНИЯ СРЕДНИХ ГОРИЗОНТАЛЬНЫХ ПРОЛОЖЕНИЙ И ПРЕВЫШЕНИЙ.");
        extractResponse.add("--------------------------------------------------------------------------------------------------");
        extractResponse.add("|Наименование|       Гор. проложение, м.      |        |          Превышение, м.        |        |");
        extractResponse.add("|   точки    |--------------------------------| DL,мм. |--------------------------------| Dh,мм. |");
        extractResponse.add("|  стояния   |  вперёд  |  назад   |  среднее |        |  вперёд  |   назад  |  среднее |        |");
        extractResponse.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");
        extractResponse.add("|      1     |     2    |     3    |     4    |   5    |     6    |     7    |     8    |    9   |");
        extractResponse.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");

        solution = solutionRepository.getSolutionById(0);
        line
                .append("| ")
                .append(dataMapper.stringToTableRight(solution.getStationName(), 10))
                .append(" |          |          |          |        |          |          |          |        |");
        extractResponse.add(line.toString());
        line.setLength(0);

        line
                .append("|            |          | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getInverseHorDistance()), 8))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getAverageHorDistance()), 8))
                .append(" |        |          | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getInverseElevation()), 8))
                .append(" | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getAverageElevation()), 8))
                        .append(" |        |");
        extractResponse.add(line.toString());
        line.setLength(0);

        for (int i = 1; i < solutionRepository.size() - 2; i++) {
            solution = solutionRepository.getSolutionById(i);
            line
                    .append("| ")
                    .append(dataMapper.stringToTableRight(solution.getStationName(), 10))
                    .append(" |          |          |          |        |          |          |          |        |");
            extractResponse.add(line.toString());
            line.setLength(0);

            line
                    .append("|            | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getDirectHorDistance()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getInverseHorDistance()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getAverageHorDistance()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(String.valueOf(solution.getDeltaHorDistance()), 6))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getDirectElevation()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getInverseElevation()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getAverageElevation()), 8))
                    .append(" | ")
                    .append(dataMapper.stringToTableRight(String.valueOf(solution.getDeltaElevation()), 6))
                    .append(" |");
            extractResponse.add(line.toString());
            line.setLength(0);
        }

        solution = solutionRepository.getSolutionById(solutionRepository.size() - 2);
        line
                .append("| ")
                .append(dataMapper.stringToTableRight(solution.getStationName(), 10))
                .append(" |          |          |          |        |          |          |          |        |");
        extractResponse.add(line.toString());
        line.setLength(0);

        line
                .append("|            | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getDirectHorDistance()), 8))
                .append(" |          | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getAverageHorDistance()), 8))
                .append(" |        | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getDirectElevation()), 8))
                .append(" |          | ")
                .append(dataMapper.stringToTableRight(dataMapper.millimeterToMeter(solution.getAverageElevation()), 8))
                .append(" |        |");
        extractResponse.add(line.toString());
        line.setLength(0);

        solution = solutionRepository.getSolutionById(solutionRepository.size() - 1);
        line
                .append("| ")
                .append(dataMapper.stringToTableRight(solution.getStationName(), 10))
                .append(" |          |          |          |        |          |          |          |        |");
        extractResponse.add(line.toString());
        line.setLength(0);
        extractResponse.add("--------------------------------------------------------------------------------------------------");

        return extractResponse;
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

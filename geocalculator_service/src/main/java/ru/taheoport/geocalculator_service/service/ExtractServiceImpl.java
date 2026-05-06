package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.*;
import ru.taheoport.geocalculator_service.model.Extraction;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.Solution;
import ru.taheoport.geocalculator_service.repository.ExtractRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepository;
import ru.taheoport.geocalculator_service.repository.SolutionRepositoryImpl;

import java.util.List;

/**
 * Provides methods for extracting polygon from survey data
 */
@Service
@RequiredArgsConstructor
public class ExtractServiceImpl implements ExtractService{

    private final ExtractCalculator extractCalculator;

    private final ExtractMapper extractMapper;

    /**
     * Gets general report of polygon extraction
     *
     * @param extractRequest List of strings
     * @return List of strings
     */
    @Override
    public List<String> getExtractReports(List<String> extractRequest) {

        String message = extractMapper.extractRequestToExtraction(extractRequest);
        if (!message.equals("OK")) return extractMapper.getErrorResponse(message);

        extractCalculator.ExtractionToSolution();

        return extractMapper.solutionToExtractResponse();
    }
}

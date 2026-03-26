package ru.taheoport.geocalculator_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taheoport.geocalculator_service.mapper.DirectCalculator;
import ru.taheoport.geocalculator_service.mapper.InverseCalculator;
import ru.taheoport.geocalculator_service.mapper.SurveyMapper;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SurveyServiceImpl implements SurveyService {

    private final DirectCalculator directCalculator;

    private final InverseCalculator inverseCalculator;

    private final SurveyMapper surveyMapper;

    private final SurveyRepository surveyRepository;

    /**
     * Converts geodetic data from different types of total stations
     *
     * @param importRequest list of strings with geodetic data
     * @return list of strings in tah-format
     */
    @Override
    public List<String> importFromTotalStation(List<String> importRequest) {
        String importType = "";

        if (!importRequest.isEmpty()) {
            importType = importRequest.removeFirst();
        }

        switch (importType) {

            case "import-leica" -> {
                if (surveyMapper.readFromLeica(importRequest, surveyRepository)) {
                    return surveyMapper.surveyToListTah(surveyRepository);
                }
            }

            case "import-nikon" -> {
                if (surveyMapper.readFromNikon(importRequest,surveyRepository)) {
                    return surveyMapper.surveyToListTah(surveyRepository);
                }
            }

            case "import-topcon" -> {
                if (surveyMapper.readFromTopcon(importRequest,surveyRepository)) {
                    return surveyMapper.surveyToListTah(surveyRepository);
                }
            }

        }

        return List.of();
    }

    /**
     * Process geodetic survey data and creates reports
     *
     * @param surveyRequest list of strings in tah-format
     * @return General report of geodetic survey processing
     */
    @Override
    public List<String> getSurveyReports(List<String> surveyRequest) {

        return List.of();
    }

    /**
     * Executes mathematical processing of geodetic survey
     */
    @Override
    public void calculateSurvey() {

        for (int i = 0; i < surveyRepository.size(); i++) {
            surveyRepository.saveBaseDirectionAngle(
                    i,
                    inverseCalculator.getDirection(
                            surveyRepository.getStationX(i),
                            surveyRepository.getStationY(i),
                            surveyRepository.getOrX(i),
                            surveyRepository.getOrY(i)
                    ));
        }

        for (int i = 0; i < surveyRepository.size(); i++) {

            for (int j = 0; j < surveyRepository.measurementSize(i); j++) {

                surveyRepository.saveTargetDirectionAngle(
                        i,
                        j,
                        directCalculator.getDirectionalAngle(
                                surveyRepository.getBaseDirectionAngle(i),
                                surveyRepository.getOrDirection(i),
                                surveyRepository.getTargetDirection(i, j)
                        ));

                surveyRepository.saveTargetDeltaX(
                        i,
                        j,
                        directCalculator.getDeltaX(
                                surveyRepository.getTargetDirectionAngle(i, j),
                                surveyRepository.getTargetInclinedDistance(i, j),
                                surveyRepository.getTargetTiltAngle(i, j)
                        ));

                surveyRepository.saveTargetDeltaY(
                        i,
                        j,
                        directCalculator.getDeltaY(
                                surveyRepository.getTargetDirectionAngle(i, j),
                                surveyRepository.getTargetInclinedDistance(i, j),
                                surveyRepository.getTargetTiltAngle(i, j)
                        ));

                surveyRepository.saveTargetDeltaZ(
                        i,
                        j,
                        directCalculator.getDeltaZ(
                                surveyRepository.getTargetInclinedDistance(i, j),
                                surveyRepository.getTargetTiltAngle(i, j)
                        ) + surveyRepository.getStationHeight(i) - surveyRepository.getTargetHeight(i, j));


                surveyRepository.saveTargetX(
                        i,
                        j,
                        surveyRepository.getStationX(i) + surveyRepository.getTargetDeltaX(i, j)
                );

                surveyRepository.saveTargetY(
                        i,
                        j,
                        surveyRepository.getStationY(i) + surveyRepository.getTargetDeltaY(i, j)
                );

                surveyRepository.saveTargetZ(
                        i,
                        j,
                        surveyRepository.getStationZ(i) + surveyRepository.getTargetDeltaZ(i, j)
                );
            }

        }
    }
}

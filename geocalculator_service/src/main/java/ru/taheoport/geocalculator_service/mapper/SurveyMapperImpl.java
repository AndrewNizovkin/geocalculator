package ru.taheoport.geocalculator_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
        return false;
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

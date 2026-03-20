package ru.taheoport.geocalculator_service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;
import ru.taheoport.geocalculator_service.validator.ValidatorDefault;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SurveyRepositoryImpl.class)
class SurveyRepositoryImplTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @BeforeEach
    void clearSurveyRepository() {
        surveyRepository.clearAll();
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "5",
            "10"
    })
    void clearAll(int value) {
        for (int i = 0; i < value; i++) {
            surveyRepository.addNewStation();
        }
        surveyRepository.clearAll();

        int actual = surveyRepository.size();

        assertEquals(0, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "5",
            "10"
    })
    void size(int expect) {
        for (int i = 0; i < expect; i++) {
            surveyRepository.addNewStation();
        }

        int actual = surveyRepository.size();

        assertEquals(expect, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "5",
            "10"
    })
    void measurementSize(int expect) {
        for (int i = 0; i < expect; i++) {
            surveyRepository.addNewStation();
        }
        for (int i = 0; i < expect; i++) {
            surveyRepository.addNewMeasurement(surveyRepository.size() - 1);
        }

        int actual = surveyRepository.measurementSize(surveyRepository.size() - 1);

        assertEquals(expect, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "station1, 1000000, 1000000, 100000, 1678, 0, or1, 200000, 200000, 324000, 5",
            "station2, 451000000, 100450000, 100000, 1978, 18000, or2, 200000, 200000, 1296000, 10",
            "station3, 165000000, 1000000, 10000, 1778, 480, or3, 200000, 200000, 123456, 1000"
    })
    void addNewStation(
            String stationName,
            long stationX,
            long stationY,
            long stationZ,
            long stationHeight,
            long orDirection,
            String orName,
            long orX,
            long orY,
            long baseDirectionAngle,
            int expectMeasurementSize
    ) {
        SurveyStation expectSurveyStation = surveyRepository.addNewStation();
        expectSurveyStation.setStationName(stationName);
        expectSurveyStation.setStationX(stationX);
        expectSurveyStation.setStationY(stationY);
        expectSurveyStation.setStationZ(stationZ);
        expectSurveyStation.setStationHeight(stationHeight);
        expectSurveyStation.setOrDirection(orDirection);
        expectSurveyStation.setOrName(orName);
        expectSurveyStation.setOrX(orX);
        expectSurveyStation.setOrY(orY);
        expectSurveyStation.setBaseDirectionAngle(baseDirectionAngle);
        for (int i = 0; i < expectMeasurementSize; i++) {
            expectSurveyStation.getMeasurements().add(new Measurement());
        }

        int actualStationIndex = surveyRepository.size() - 1;
        int actualMeasurementSize = surveyRepository.measurementSize(actualStationIndex);

        assertEquals(expectMeasurementSize, actualMeasurementSize);
        assertEquals(expectSurveyStation.getStationName(), surveyRepository.getStationName(actualStationIndex));
        assertEquals(expectSurveyStation.getStationX(), surveyRepository.getStationX(actualStationIndex));
        assertEquals(expectSurveyStation.getStationY(), surveyRepository.getStationY(actualStationIndex));
        assertEquals(expectSurveyStation.getStationZ(), surveyRepository.getStationZ(actualStationIndex));
        assertEquals(expectSurveyStation.getStationHeight(), surveyRepository.getStationHeight(actualStationIndex));
        assertEquals(expectSurveyStation.getOrDirection(), surveyRepository.getOrDirection(actualStationIndex));
        assertEquals(expectSurveyStation.getOrName(), surveyRepository.getOrName(actualStationIndex));
        assertEquals(expectSurveyStation.getOrX(), surveyRepository.getOrX(actualStationIndex));
        assertEquals(expectSurveyStation.getOrY(), surveyRepository.getOrY(actualStationIndex));
        assertEquals(expectSurveyStation.getBaseDirectionAngle(), surveyRepository.getBaseDirectionAngle(actualStationIndex));
    }

    @ParameterizedTest
    @CsvSource({
            "picket1, 3, 39456, 113, 0, 39451, 3451358, -3456, 28567, -0345, 456567321, 2567890345, 111657",
            "picket1, 3, 39456, 456113, 2000000, 451, 3451358, -3456, 28567, -345, 456567321, 2567890345, 11657",
            "picket1, 45675, 39456, -113, 1600, 639451, 3451358, -3456, 28567, 345, 456567321, 2567890345, 151657",
            "picket1, 4663, 39456, 8113, 1600, 39451, 3451358, 3456, 28567, -345, 456567321, 2567890345, 111667",
    })
    void addNewMeasurement(
            String targetName,
            long targetDirection,
            long targetInclinedDistance,
            long targetTiltAngle,
            long targetHeight,
            long targetHorizontalDistance,
            long targetDirectionAngle,
            long targetDeltaX,
            long targetDeltaY,
            long targetDeltaZ,
            long targetX,
            long targetY,
            long targetZ
    ) {
        surveyRepository.addNewStation();
        int currentStationIndex = surveyRepository.size() - 1;
        Measurement expectMeasurement = surveyRepository.addNewMeasurement(currentStationIndex);
        expectMeasurement.setTargetName(targetName);
        expectMeasurement.setTargetDirection(targetDirection);
        expectMeasurement.setTargetInclinedDistance(targetInclinedDistance);
        expectMeasurement.setTargetTiltAngle(targetTiltAngle);
        expectMeasurement.setTargetHeight(targetHeight);
        expectMeasurement.setTargetHorizontalDistance(targetHorizontalDistance);
        expectMeasurement.setTargetDirectionAngle(targetDirectionAngle);
        expectMeasurement.setTargetDeltaX(targetDeltaX);
        expectMeasurement.setTargetDeltaY(targetDeltaY);
        expectMeasurement.setTargetDeltaZ(targetDeltaZ);
        expectMeasurement.setTargetX(targetX);
        expectMeasurement.setTargetY(targetY);
        expectMeasurement.setTargetZ(targetZ);

        int actualMeasurementIndex = surveyRepository.measurementSize(currentStationIndex) - 1;

        assertEquals(expectMeasurement.getTargetName(), surveyRepository.getTargetName(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetDirection(), surveyRepository.getTargetDirection(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetInclinedDistance(), surveyRepository.getTargetInclinedDistance(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetTiltAngle(), surveyRepository.getTargetTiltAngle(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetHeight(), surveyRepository.getTargetHeight(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetHorizontalDistance(), surveyRepository.getTargetHorizontalDistance(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetDirectionAngle(), surveyRepository.getTargetDirectionAngle(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetDeltaX(), surveyRepository.getTargetDeltaX(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetDeltaY(), surveyRepository.getTargetDeltaY(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetDeltaZ(), surveyRepository.getTargetDeltaZ(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetX(), surveyRepository.getTargetX(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetY(), surveyRepository.getTargetY(currentStationIndex, actualMeasurementIndex));
        assertEquals(expectMeasurement.getTargetZ(), surveyRepository.getTargetZ(currentStationIndex, actualMeasurementIndex));
    }

}
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
            "station1, 1000.000, 1000.000, 100.000, 1.678, 0.0000, or1, 200.000, 200.000, 45.4545, 5",
            "station2, 451000.000, 100450.000, 100.000, 1.978, 5.0000, or2, 200.000, 200.000, 270.4545, 10",
            "station3, 165000.000, 1000.000, 10.000, 1.778, 0.0800, or3, 200.000, 200.000, 89.4545, 1000"
    })
    void addNewStation(
            String stationName,
            double stationX,
            double stationY,
            double stationZ,
            double stationHeight,
            double orDirection,
            String orName,
            double orX,
            double orY,
            double baseDirectionAngle,
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
            "picket1, 0.0003, 39.456, 0.0113, 0.000, 39.451, 345.1358, -3.456, 28.567, -0.345, 456567.321, 2567890.345, 111.657",
            "picket1, 0.0003, 39.456, 0.0113, 2000.000, 0.451, 345.1358, -3.456, 28.567, -0.345, 456567.321, 2567890.345, 11.657",
            "picket1, 0.0003, 39.456, 0.0113, 1.600, 639.451, 345.1358, -3.456, 28.567, 0.345, 456567.321, 2567890.345, 151.657",
            "picket1, 0.0003, 39.456, 0.0113, 1.600, 39.451, 345.1358, 3.456, 28.567, -0.345, 456567.321, 2567890.345, 111.667",
    })
    void addNewMeasurement(
            String targetName,
            double targetDirection,
            double targetInclinedDistance,
            double targetTiltAngle,
            double targetHeight,
            double targetHorizontalDistance,
            double targetDirectionAngle,
            double targetDeltaX,
            double targetDeltaY,
            double targetDeltaZ,
            double targetX,
            double targetY,
            double targetZ
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
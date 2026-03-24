package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.mapper.*;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;
import ru.taheoport.geocalculator_service.repository.SurveyRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        InverseCalculatorImpl.class,
        DirectCalculatorDefault.class,
        SurveyMapperImpl.class,
        SurveyRepositoryImpl.class,
        SurveyServiceImpl.class
})
class SurveyServiceImplTest {

    @Autowired
    private DirectCalculator directCalculator;

    @Autowired
    private InverseCalculator inverseCalculator;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyService surveyService;


    @ParameterizedTest
    @CsvSource({
            "1035419, 1035412, 9237, -29094, 206, 478685350, 2296938170, 11426, 0, 0",
            "1035419, 408167, -15825, 36604, 16, 478660288, 2297003868, 11236, 0, 1",
            "1056205, 383231, -12884, 43628, -115, 478647399, 2297047490, 11116, 1, 0",
            "1000888, 1000888, 6018, -42685, 8078, 478656732, 2297029055, 19008, 2, 0",
            "1000888, 1001444, 6133, -42672, -1991977, 478656847, 2297029068, -1981047, 2, 1",
            "1000888, 937454, -5016, -29668, -1997212, 478645698, 2297042072, -1986282, 2, 2",
    })
    void calculateSurveyTest(
            long expectBaseDirectionAngle,
            long expectDirectionAngle,
            long expectDeltaX,
            long expectDeltaY,
            long expectDeltaZ,
            long expectTargetX,
            long expectTargetY,
            long expectTargetZ,
            int stationIndex,
            int targetIndex
    ) {

        createDemoSurvey();

        surveyService.calculateSurvey();

        int actualSizeSurvey = surveyRepository.size();
        int actualMeasurementSize = surveyRepository.measurementSize(2);

        long actualBaseDirectionAngle = surveyRepository.getBaseDirectionAngle(stationIndex);
        long actualDirectionAngle = surveyRepository.getTargetDirectionAngle(stationIndex, targetIndex);
        long actualDeltaX = surveyRepository.getTargetDeltaX(stationIndex, targetIndex);
        long actualDeltaY = surveyRepository.getTargetDeltaY(stationIndex, targetIndex);
        long actualDeltaZ = surveyRepository.getTargetDeltaZ(stationIndex, targetIndex);
        long actualX = surveyRepository.getTargetX(stationIndex, targetIndex);
        long actualY = surveyRepository.getTargetY(stationIndex, targetIndex);
        long actualZ = surveyRepository.getTargetZ(stationIndex, targetIndex);


        assertEquals(3, actualSizeSurvey);
        assertEquals(3,actualMeasurementSize);

        assertEquals(expectBaseDirectionAngle, actualBaseDirectionAngle, 1);
        assertEquals(expectDirectionAngle, actualDirectionAngle, 1);
        assertEquals(expectDeltaX, actualDeltaX, 1);
        assertEquals(expectDeltaY, actualDeltaY, 1);
        assertEquals(expectDeltaZ, actualDeltaZ, 1);
        assertEquals(expectTargetX, actualX, 1);
        assertEquals(expectTargetY, actualY, 1);
        assertEquals(expectTargetZ, actualZ, 1);
    }

    /**
     * Creates a repository with tested parameters
     */
    private void createDemoSurvey() {
        surveyRepository.clearAll();
        int stationIndex = 0;

        SurveyStation surveyStation =  surveyRepository.addNewStation();
        surveyStation.setStationName("1301");
        surveyStation.setStationX(478676113L);
        surveyStation.setStationY(2296967264L);
        surveyStation.setStationZ(11220L);
        surveyStation.setStationHeight(1538L);
        surveyStation.setOrDirection(0L);
        surveyStation.setOrName("1302");
        surveyStation.setOrX(478685352L);
        surveyStation.setOrY(2296938168L);

        Measurement measurement = surveyRepository.addNewMeasurement(stationIndex);
        measurement.setTargetName("1302");
        measurement.setTargetDirection(1295993L);
        measurement.setTargetInclinedDistance(30526L);
        measurement.setTargetTiltAngle(1809L);
        measurement.setTargetHeight(1600L);

        measurement = surveyRepository.addNewMeasurement(stationIndex);
        measurement.setTargetName("T100");
        measurement.setTargetDirection(668748L);
        measurement.setTargetInclinedDistance(39878L);
        measurement.setTargetTiltAngle(406L);
        measurement.setTargetHeight(1600L);

        surveyStation =  surveyRepository.addNewStation();
        stationIndex++;
        surveyStation.setStationName("100");
        surveyStation.setStationX(478660283L);
        surveyStation.setStationY(2297003862L);
        surveyStation.setStationZ(11231L);
        surveyStation.setStationHeight(1580L);
        surveyStation.setOrDirection(0L);
        surveyStation.setOrName("1301");
        surveyStation.setOrX(478676113L);
        surveyStation.setOrY(2296967264L);

        measurement = surveyRepository.addNewMeasurement(stationIndex);
        measurement.setTargetName("74");
        measurement.setTargetDirection(623026L);
        measurement.setTargetInclinedDistance(45491L);
        measurement.setTargetTiltAngle(-429L);
        measurement.setTargetHeight(1600L);

        surveyStation =  surveyRepository.addNewStation();
        stationIndex++;
        surveyStation.setStationName("101");
        surveyStation.setStationX(478650714L);
        surveyStation.setStationY(2297071740L);
        surveyStation.setStationZ(10930L);
        surveyStation.setStationHeight(1550L);
        surveyStation.setOrDirection(590160L);
        surveyStation.setOrName("100");
        surveyStation.setOrX(478660283L);
        surveyStation.setOrY(2297003862L);

        measurement = surveyRepository.addNewMeasurement(stationIndex);
        measurement.setTargetName("999");
        measurement.setTargetDirection(590160L);
        measurement.setTargetInclinedDistance(43599L);
        measurement.setTargetTiltAngle(31001L);
        measurement.setTargetHeight(0L);

        measurement = surveyRepository.addNewMeasurement(stationIndex);
        measurement.setTargetName("12");
        measurement.setTargetDirection(590716L);
        measurement.setTargetInclinedDistance(43594L);
        measurement.setTargetTiltAngle(30742L);
        measurement.setTargetHeight(2000000L);

        measurement = surveyRepository.addNewMeasurement(stationIndex);
        measurement.setTargetName("40");
        measurement.setTargetDirection(526726L);
        measurement.setTargetInclinedDistance(30114L);
        measurement.setTargetTiltAngle(8484L);
        measurement.setTargetHeight(2000000L);
    }
}
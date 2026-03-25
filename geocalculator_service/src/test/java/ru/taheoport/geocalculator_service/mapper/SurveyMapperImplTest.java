package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;
import ru.taheoport.geocalculator_service.repository.SurveyRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        DataMapperDefault.class,
        SurveyMapperImpl.class,
        SurveyRepositoryImpl.class
})
class SurveyMapperImplTest {

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private SurveyRepository surveyRepository;

    @Test
    void surveyToListTahTest() {

        List<String> expectListTah = new ArrayList<>();
        expectListTah.add("1301 478676.113 2296967.264 11.220 1.538 1302 478685.352 2296938.168");
        expectListTah.add("100 478660.283 2297003.862 11.231 1.580 1301 478676.113 2296967.264");
        expectListTah.add("101 478650.714 2297071.740 10.930 1.550 100 478660.283 2297003.862");
        expectListTah.add("//");
        expectListTah.add("1302 30.526 359.5953 0.3009 1.600 0");
        expectListTah.add("T100 39.878 185.4548 0.0646 1.600 0");
        expectListTah.add("//");
        expectListTah.add("74 45.491 173.0346 -0.0709 1.600 1");
        expectListTah.add("//");
        expectListTah.add("999 43.599 163.5600 8.3641 0.000 2");
        expectListTah.add("12 43.594 164.0516 8.3222 2000.000 2");
        expectListTah.add("40 30.114 146.1846 2.2124 2000.000 2");
        expectListTah.add("//");
        int expectSize = expectListTah.size();
        createDemoSurvey();

        List<String> actualListTah = surveyMapper.surveyToListTah(surveyRepository);
        int actualSize = actualListTah.size();

        assertEquals(expectSize, actualSize);

        while (!expectListTah.isEmpty()){
            String expectLine = expectListTah.removeFirst();
            String actualLine = actualListTah.removeFirst();

            assertEquals(expectLine, actualLine);
        }

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
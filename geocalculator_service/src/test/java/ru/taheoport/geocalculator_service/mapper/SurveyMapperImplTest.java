package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.Measurement;
import ru.taheoport.geocalculator_service.model.SurveyStation;
import ru.taheoport.geocalculator_service.repository.SurveyRepository;
import ru.taheoport.geocalculator_service.repository.SurveyRepositoryImpl;

import java.util.ArrayList;
import java.util.LinkedList;
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

    @Test
    void readFromLeicaTest() {
        surveyRepository.clearAll();
        List<String> importLeicaList = getImportLeicaTestList();

        boolean actualSuccess = surveyMapper.readFromLeica(importLeicaList, surveyRepository);

        assertTrue(actualSuccess);
    }

    @Test
    void readFromNikonTest() {
        surveyRepository.clearAll();
        List<String> importNikonList = getImportNikonTestList();
        int expectSize = 3;

        boolean actualSuccess = surveyMapper.readFromNikon(importNikonList, surveyRepository);
        int actualSize = surveyRepository.size();

        assertTrue(actualSuccess);
        assertEquals(expectSize, actualSize);

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

    /**
     * Creates test list for import from total station Leica
     * @return List strings in gsi16 format
     */
    private List<String> getImportLeicaTestList() {
        List<String> importLeicaList = new ArrayList<>();
        importLeicaList.add("410001+00009901 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110002+00000001 21.324+35959550 22.324+09002380 31...0+00152081 51....+0000+000 87...0+00001600 88...0+00001531 ");
        importLeicaList.add("410003+000T100  42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110004+00000002 21.324+06137090 22.324+08953090 31...0+00038187 51....+0000+000 87...0+00001600 88...0+00001531 ");
        importLeicaList.add("410005+0009904K 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110006+00000003 21.324+15723000 22.324+09051020 31...0+00033938 51....+0000+000 87...0+00001600 88...0+00001531 ");
        importLeicaList.add("410499+00009902 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110500+00000326 21.324+35959550 22.324+08954590 31...0+00038187 51....+0000+000 87...0+00001600 88...0+00001536 ");
        importLeicaList.add("410501+00000330 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110502+00000327 21.324+32825350 22.324+08850230 31...0+00003511 51....+0000+000 87...0+00001600 88...0+00001536 ");
        importLeicaList.add("410503+00000055 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110504+00000328 21.324+29051110 22.324+08917280 31...0+00004374 51....+0000+000 87...0+00001600 88...0+00001536 ");
        importLeicaList.add("410505+00000055 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110539+00000347 21.324+00000010 22.324+08952260 31...0+00055591 51....+0000+000 87...0+00001600 88...0+00001537 ");
        importLeicaList.add("410540+0000T102 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110541+00000348 21.324+14133000 22.324+09010350 31...0+00033479 51....+0000+000 87...0+00001600 88...0+00001537 ");
        importLeicaList.add("410542+00000040 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110543+00000349 21.324+12537430 22.324+09006440 31...0+00026790 51....+0000+000 87...0+00001600 88...0+00001537 ");

        return importLeicaList;
    }

    /**
     * Creates test list for import from total station Nikon
     * @return List strings in raw format
     */
    private List<String> getImportNikonTestList() {
        List<String> importNikonList = new ArrayList<>();
        importNikonList.add("Waiting to receive…");
        importNikonList.add("CO,Nikon RAW data format V2.00");
        importNikonList.add("CO,161226-1");
        importNikonList.add("CO,Description:");
        importNikonList.add("CO,Client:");
        importNikonList.add("CO,Comments:");
        importNikonList.add("CO,Downloaded 26-Dec-2016 15:57:43");
        importNikonList.add("CO,Software: Pre-install version: 1.1.3.1");
        importNikonList.add("CO,Instrument: Nivo3.M");
        importNikonList.add("CO,Dist Units: Metres");
        importNikonList.add("CO,Angle Units: DDDMMSS");
        importNikonList.add("CO,Zero azimuth: North");
        importNikonList.add("CO,Zero VA: Compass");
        importNikonList.add("CO,Coord Order: NEZ");
        importNikonList.add("CO,HA Raw data: HA zero to BS");
        importNikonList.add("CO,Tilt Correction:  VA:ON HA:ON");
        importNikonList.add("CO, 161226-1 <JOB> Created 26-Dec-2016 10:13:32");
        importNikonList.add("CO,S/N:A150710");
        importNikonList.add("CO,Temp:15C Press:760mmHg Prism:34 26-Dec-2016 10:15:46");
        importNikonList.add("CO,HA set in Quick Station");
        importNikonList.add("ST,2819,,150,,1.588,0.0000,0.0000");
        importNikonList.add("SS,1,1.600,250.223,2.4012,-0.0330,10:20:45,T156");
        importNikonList.add("CO,Temp:15C Press:760mmHg Prism:34 26-Dec-2016 10:31:08");
        importNikonList.add("CO,HA set in Quick Station");
        importNikonList.add("ST,156,,2819,,1.597,0.0000,0.0000");
        importNikonList.add("SS,2,1.600,113.376,205.0223,-0.0207,10:39:12,T157");
        importNikonList.add("CO,Temp:15C Press:760mmHg Prism:0 26-Dec-2016 10:39:56");
        importNikonList.add("SS,3,1.670,64.19,205.1503,-0.0332,10:39:56,2");
        importNikonList.add("SS,4,1.670,51.64,205.1626,-0.0126,10:40:12,2");
        importNikonList.add("CO,Input HD:0.750");
        importNikonList.add("CO,Temp:15C Press:760mmHg Prism:0 26-Dec-2016 10:57:12");
        importNikonList.add("CO,HA set in Quick Station");
        importNikonList.add("ST,157,,156,,1.556,0.0000,0.0000");
        importNikonList.add("CO,Temp:15C Press:760mmHg Prism:34 26-Dec-2016 10:57:51");
        importNikonList.add("SS,28,1.600,113.379,0.0001,0.0309,10:57:51,T156");
        importNikonList.add("SS,29,1.600,97.740,182.5923,-0.2934,11:03:50,T158");
        importNikonList.add("SS,30,1.600,97.58,184.0216,-0.2843,11:04:03,2");

        return importNikonList;
    }


}
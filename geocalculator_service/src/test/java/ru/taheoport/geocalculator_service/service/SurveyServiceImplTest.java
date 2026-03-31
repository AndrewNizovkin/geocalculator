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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        InverseCalculatorImpl.class,
        DirectCalculatorDefault.class,
        SurveyMapperImpl.class,
        SurveyRepositoryImpl.class,
        SurveyServiceImpl.class,
        DataMapperDefault.class
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

    @Test
    void importFromTotalStationLeicaTest() {
        List<String> importLeicaList = getImportLeicaTestList();

        List<String> expectTahList = getExpectLeicaTahList();
        int expectSize = expectTahList.size();

        List<String> actualTahList = surveyService.importFromTotalStation(importLeicaList);
        int actualSize = actualTahList.size();

        assertNotNull(actualTahList);

        assertEquals(expectSize, actualSize);

        while (!expectTahList.isEmpty()) {
            String expectLine = expectTahList.removeFirst();
            String actualLine = actualTahList.removeFirst();

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void importFromTotalStationNikonTest() {
        List<String> expectTahList = getExpectNikonTahList();
        int expectTahSize = expectTahList.size();

        List<String> importNikonList = getImportNikonTestList();
        List<String> actualTahList = surveyService.importFromTotalStation(importNikonList);

        assertNotNull(actualTahList);
        int actualTahSize = actualTahList.size();
        assertEquals(expectTahSize, actualTahSize);

        for (int i = 0; i < expectTahSize; i++) {
            String expectLine = expectTahList.removeFirst();
            String actualLine = actualTahList.removeFirst();

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void importFromTotalStationTopconTest() {
        List<String> expectTahList = getExpectTopconTahList();
        int expectSize = expectTahList.size();

        List<String> importTopconList = getImportTopconList();
        List<String> actualTahList = surveyService.importFromTotalStation(importTopconList);

        assertNotNull(actualTahList);
        int actualSize = actualTahList.size();
        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectTahList.removeFirst();
            String actualLine = actualTahList.removeFirst();

            assertEquals(expectLine, actualLine);
        }
    }

    /**
     * Creates expect list for testing import from Leica
     * @return list in tah-format
     */
    private List<String> getExpectLeicaTahList() {
        List<String> expectLeicaTahList = new ArrayList<>();
        expectLeicaTahList.add("noname 0.000 0.000 0.000 1.531 noname 0.000 0.000");
        expectLeicaTahList.add("noname 0.000 0.000 0.000 1.536 noname 0.000 0.000");
        expectLeicaTahList.add("noname 0.000 0.000 0.000 1.537 noname 0.000 0.000");
        expectLeicaTahList.add("//");
        expectLeicaTahList.add("9901 152.081 359.5955 -0.0238 1.600 0");
        expectLeicaTahList.add("T100 38.187 61.3709 0.0651 1.600 0");
        expectLeicaTahList.add("9904K 33.938 157.2300 -0.5102 1.600 0");
        expectLeicaTahList.add("//");
        expectLeicaTahList.add("9902 38.187 359.5955 0.0501 1.600 1");
        expectLeicaTahList.add("330 3.511 328.2535 1.0937 1.600 1");
        expectLeicaTahList.add("55 4.374 290.5111 0.4232 1.600 1");
        expectLeicaTahList.add("//");
        expectLeicaTahList.add("55 55.591 0.0001 0.0734 1.600 2");
        expectLeicaTahList.add("T102 33.479 141.3300 -0.1035 1.600 2");
        expectLeicaTahList.add("40 26.790 125.3743 -0.0644 1.600 2");
        expectLeicaTahList.add("//");

        return expectLeicaTahList;
    }

    /**
     * Creates test list for import from total station Leica
     * @return List strings in gsi16 format
     */
    private List<String> getImportLeicaTestList() {
        List<String> importLeicaList = new ArrayList<>();
        importLeicaList.add("import-leica");
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
     * Creates expect list for testing import from nikon
     * @return list in tah-format
     */
    private List<String> getExpectNikonTahList() {
        List<String> expectNixonTahList = new ArrayList<>();
        expectNixonTahList.add("2819 0.000 0.000 0.000 1.588 150 0.000 0.000");
        expectNixonTahList.add("156 0.000 0.000 0.000 1.597 2819 0.000 0.000");
        expectNixonTahList.add("157 0.000 0.000 0.000 1.556 156 0.000 0.000");
        expectNixonTahList.add("//");
        expectNixonTahList.add("T156 250.223 2.4012 -0.0330 1.600 0");
        expectNixonTahList.add("//");
        expectNixonTahList.add("T157 113.376 205.0223 -0.0207 1.600 1");
        expectNixonTahList.add("2 64.190 205.1503 -0.0332 1.670 1");
        expectNixonTahList.add("2 51.640 205.1626 -0.0126 1.670 1");
        expectNixonTahList.add("//");
        expectNixonTahList.add("T156 113.379 0.0001 0.0309 1.600 2");
        expectNixonTahList.add("T158 97.740 182.5923 -0.2934 1.600 2");
        expectNixonTahList.add("2 97.580 184.0216 -0.2843 1.600 2");
        expectNixonTahList.add("//");

        return expectNixonTahList;
    }

    /**
     * Creates test list for import from total station Nikon
     * @return List strings in raw format
     */
    private List<String> getImportNikonTestList() {
        List<String> importNikonList = new ArrayList<>();
        importNikonList.add("import-nikon");
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

    /**
     * Creates expect list for testing import from Topcon
     * @return list strings in tah-format
     */
    private List<String> getExpectTopconTahList() {
        List<String> expectTopconTahList = new ArrayList<>();
        expectTopconTahList.add("T3 0.000 0.000 0.000 1.595 noname 0.000 0.000");
        expectTopconTahList.add("T6 0.000 0.000 0.000 1.620 noname 0.000 0.000");
        expectTopconTahList.add("T5 0.000 0.000 0.000 1.650 noname 0.000 0.000");
        expectTopconTahList.add("//");
        expectTopconTahList.add("T4 9.294 359.5959 -0.0645 1.595 0");
        expectTopconTahList.add("P100 14.487 48.0358 2.1756 1.595 0");
        expectTopconTahList.add("P151 15.557 206.0742 5.2132 2.720 0");
        expectTopconTahList.add("//");
        expectTopconTahList.add("T3 42.091 359.5959 -3.4713 1.620 1");
        expectTopconTahList.add("P200 17.622 9.4544 -7.1011 1.620 1");
        expectTopconTahList.add("P201 19.137 16.4505 -5.4634 1.620 1");
        expectTopconTahList.add("//");
        expectTopconTahList.add("T4 31.776 0.0000 4.4023 1.650 2");
        expectTopconTahList.add("P500 30.113 346.1252 2.5322 1.650 2");
        expectTopconTahList.add("P502 24.110 344.4233 3.1748 1.650 2");
        expectTopconTahList.add("//");

        return expectTopconTahList;
    }
    /**
     * Creates test list for import from total station Topcon
     * @return List strings in topcon format
     */
    private List<String> getImportTopconList() {
        List<String> importTopconList = new ArrayList<>();
        importTopconList.add("import-topcon");
        importTopconList.add("_'T3_(_)1.595_+T4_ ?+00009294m0900645+3595959d+00009294***+00+00000_*_,1.595_+P100_ ?+00014487m0874204+0480358d+00014475***+00+00000_*_,1.595_+P151_ ?+00015557m0843828+2060742d+00015489***+00+00000_*_,2.720_'T6_(_)1.620_+T3_ ?+00042091m0934713+3595959d+00041999***+00+00000_*_,1.620_+P200_ ?+00017622m0971011+0094544d+00017484***+00+00000_*_,1.620_+P201_ ?+00019137m0954634+0164505d+00019040***+00+00000_*_,1.620_'T5_(_)1.650_+T4_ ?+00031776m0851937+0000000d+00031670***+00+00000_*_,1.650_+P500_ ?+00030113m0870638+3461252d+00030075***+00+00000_*_,1.650_+P502_ ?+00024110m0864212+3444233d+00024070***+00+00000_*_,1.650_\n");

        return importTopconList;
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
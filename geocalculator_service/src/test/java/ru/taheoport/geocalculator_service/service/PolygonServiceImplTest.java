package ru.taheoport.geocalculator_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.ReportResiduals;
import ru.taheoport.geocalculator_service.mapper.*;
import ru.taheoport.geocalculator_service.model.Residuals;
import ru.taheoport.geocalculator_service.model.ValidResiduals;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;
import ru.taheoport.geocalculator_service.repository.PolygonRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        PolygonServiceImpl.class,
        PolygonRepositoryImpl.class,
        PolygonMapperImpl.class,
        PolygonCalculatorImpl.class,
        DirectCalculatorDefault.class,
        InverseCalculatorImpl.class,
        ValidResiduals.class,
        Residuals.class,
        DataMapperDefault.class,
        ReportResiduals.class
})
class PolygonServiceImplTest {

    @Autowired
    private PolygonRepository polygonRepository;

    @Autowired
    private PolygonService polygonService;

    @Autowired
    private ValidResiduals validResiduals;

    @Autowired
    private Residuals residuals;

    @Autowired
    private PolygonMapper polygonMapper;

    @Autowired
    private PolygonCalculator polygonCalculator;

    @Autowired
    private ReportResiduals reportResiduals;

    @Test
    void setReportResidualsTestTT() {
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestTT());
        assertTrue(success);
        String expectPerimeter = "224.560";
        String expectActualElevation = "5";
        String expectActualAngle = "55";
        String expectActualX = "-0.015";
        String expectActualY = "-0.013";
        String expectActualAbsolute = "0.020";
        String expectActualRelative = "1:11228";
        String expectValidElevation = "24";
        String expectValidAngle = "45";
        String expectValidAbsolute = "0.20";
        String expectValidRelative = "1:2000";
        polygonCalculator.setBindType();
        polygonCalculator.adjustPolygon();
        polygonMapper.clearReportResiduals();
        polygonMapper.setReportResiduals();

        String actualPerimeter = reportResiduals.getPerimeter();
        String actualActualElevation = reportResiduals.getActualElevation();
        String actualActualAngle = reportResiduals.getActualAngle();
        String actualActualX = reportResiduals.getActualX();
        String actualActualY = reportResiduals.getActualY();
        String actualActualAbsolute = reportResiduals.getActualAbsolute();
        String actualActualRelative = reportResiduals.getActualRelative();
        String actualValidElevation = reportResiduals.getValidElevation();
        String actualValidAngle = reportResiduals.getValidAngle();
        String actualValidAbsolute = reportResiduals.getValidAbsolute();
        String actualValidRelative = reportResiduals.getValidRelative();

        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectActualElevation, actualActualElevation);
        assertEquals(expectActualAngle, actualActualAngle);
        assertEquals(expectActualX, actualActualX);
        assertEquals(expectActualY, actualActualY);
        assertEquals(expectActualAbsolute, actualActualAbsolute);
        assertEquals(expectActualRelative, actualActualRelative);
        assertEquals(expectValidElevation, actualValidElevation);
        assertEquals(expectValidAngle, actualValidAngle);
        assertEquals(expectValidAbsolute, actualValidAbsolute);
        assertEquals(expectValidRelative, actualValidRelative);
    }

    @Test
    void setReportResidualsTestOO() {
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestOO());
        assertTrue(success);
        String expectPerimeter = "224.560";
        String expectActualElevation = "5";
        String expectActualAngle = "-.-";
        String expectActualX = "-0.063";
        String expectActualY = "0.041";
        String expectActualAbsolute = "0.075";
        String expectActualRelative = "1:2994";
        String expectValidElevation = "24";
        String expectValidAngle = "-.-";
        String expectValidAbsolute = "0.20";
        String expectValidRelative = "1:2000";
        polygonCalculator.setBindType();
        polygonCalculator.adjustPolygon();
        polygonMapper.clearReportResiduals();
        polygonMapper.setReportResiduals();

        String actualPerimeter = reportResiduals.getPerimeter();
        String actualActualElevation = reportResiduals.getActualElevation();
        String actualActualAngle = reportResiduals.getActualAngle();
        String actualActualX = reportResiduals.getActualX();
        String actualActualY = reportResiduals.getActualY();
        String actualActualAbsolute = reportResiduals.getActualAbsolute();
        String actualActualRelative = reportResiduals.getActualRelative();
        String actualValidElevation = reportResiduals.getValidElevation();
        String actualValidAngle = reportResiduals.getValidAngle();
        String actualValidAbsolute = reportResiduals.getValidAbsolute();
        String actualValidRelative = reportResiduals.getValidRelative();

        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectActualElevation, actualActualElevation);
        assertEquals(expectActualAngle, actualActualAngle);
        assertEquals(expectActualX, actualActualX);
        assertEquals(expectActualY, actualActualY);
        assertEquals(expectActualAbsolute, actualActualAbsolute);
        assertEquals(expectActualRelative, actualActualRelative);
        assertEquals(expectValidElevation, actualValidElevation);
        assertEquals(expectValidAngle, actualValidAngle);
        assertEquals(expectValidAbsolute, actualValidAbsolute);
        assertEquals(expectValidRelative, actualValidRelative);
    }

    @Test
    void setReportResidualsTestTZ() {
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestTZ());
        assertTrue(success);
        String expectPerimeter = "190.830";
        String expectActualElevation = "-.-";
        String expectActualAngle = "-.-";
        String expectActualX = "-.-";
        String expectActualY = "-.-";
        String expectActualAbsolute = "-.-";
        String expectActualRelative = "-.-";
        String expectValidElevation = "22";
        String expectValidAngle = "-.-";
        String expectValidAbsolute = "0.20";
        String expectValidRelative = "1:2000";
        polygonCalculator.setBindType();
        polygonCalculator.adjustPolygon();
        polygonMapper.clearReportResiduals();
        polygonMapper.setReportResiduals();

        String actualPerimeter = reportResiduals.getPerimeter();
        String actualActualElevation = reportResiduals.getActualElevation();
        String actualActualAngle = reportResiduals.getActualAngle();
        String actualActualX = reportResiduals.getActualX();
        String actualActualY = reportResiduals.getActualY();
        String actualActualAbsolute = reportResiduals.getActualAbsolute();
        String actualActualRelative = reportResiduals.getActualRelative();
        String actualValidElevation = reportResiduals.getValidElevation();
        String actualValidAngle = reportResiduals.getValidAngle();
        String actualValidAbsolute = reportResiduals.getValidAbsolute();
        String actualValidRelative = reportResiduals.getValidRelative();

        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectActualElevation, actualActualElevation);
        assertEquals(expectActualAngle, actualActualAngle);
        assertEquals(expectActualX, actualActualX);
        assertEquals(expectActualY, actualActualY);
        assertEquals(expectActualAbsolute, actualActualAbsolute);
        assertEquals(expectActualRelative, actualActualRelative);
        assertEquals(expectValidElevation, actualValidElevation);
        assertEquals(expectValidAngle, actualValidAngle);
        assertEquals(expectValidAbsolute, actualValidAbsolute);
        assertEquals(expectValidRelative, actualValidRelative);
    }

    @Test
    void getPolygonReportsTestTT() {
        List<String> expectResponse = getExpectPolygonResponseTT();
        int expectSize = expectResponse.size();

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestTT());
        assertNotNull(actualResponse);
        int  actualSize = actualResponse.size();

        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectResponse.get(i);
            String actualLine = actualResponse.get(i);

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void getPolygonReportsTestTO() {
        List<String> expectResponse = getExpectPolygonResponseTO();
        int expectSize = expectResponse.size();

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestTO());
        assertNotNull(actualResponse);
        int  actualSize = actualResponse.size();

        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectResponse.get(i);
            String actualLine = actualResponse.get(i);

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void getPolygonReportsTestOT() {
        List<String> expectResponse = getExpectPolygonResponseOT();
        int expectSize = expectResponse.size();

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestOT());
        assertNotNull(actualResponse);
        int  actualSize = actualResponse.size();

        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectResponse.get(i);
            String actualLine = actualResponse.get(i);

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void getPolygonReportsTestTZ() {
        List<String> expectResponse = getExpectPolygonResponseTZ();
        int expectSize = expectResponse.size();

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestTZ());
        assertNotNull(actualResponse);
        int  actualSize = actualResponse.size();

        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectResponse.get(i);
            String actualLine = actualResponse.get(i);

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void getPolygonReportsTestZT() {
        List<String> expectResponse = getExpectPolygonResponseZT();
        int expectSize = expectResponse.size();

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestZT());
        assertNotNull(actualResponse);
        int  actualSize = actualResponse.size();

        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectResponse.get(i);
            String actualLine = actualResponse.get(i);

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void getPolygonReportsTestOO() {
        List<String> expectResponse = getExpectPolygonResponseOO();
        int expectSize = expectResponse.size();

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestOO());
        assertNotNull(actualResponse);
        int  actualSize = actualResponse.size();

        assertEquals(expectSize, actualSize);

        for (int i = 0; i < expectSize; i++) {
            String expectLine = expectResponse.get(i);
            String actualLine = actualResponse.get(i);

            assertEquals(expectLine, actualLine);
        }
    }

    @Test
    void getPolygonReportsTestZZ() {
        String expectMessage = "Unknown polygon binding scheme!";
        String expectTitle = "#error";
        int expectSize = 2;

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestZZ());
        int actualSize = actualResponse.size();
        assertEquals(expectSize, actualSize);

        String actualTitle = actualResponse.get(0);
        String actualMessage = actualResponse.get(1);

        assertEquals(expectTitle, actualTitle);
        assertEquals(expectMessage, actualMessage);
    }

    @Test
    void getPolygonReportsTestFewStations() {
        String expectMessage = "Few stations!";
        String expectTitle = "#error";
        int expectSize = 2;

        List<String> actualResponse = polygonService.getPolygonReports(getTestPolygonRequestFewStations());
        int actualSize = actualResponse.size();
        assertEquals(expectSize, actualSize);

        String actualTitle = actualResponse.get(0);
        String actualMessage = actualResponse.get(1);

        assertEquals(expectTitle, actualTitle);
        assertEquals(expectMessage, actualMessage);
    }

    @Test
    void getPolygonReportsTestBadRequest() {
        String expectMessage = "Bad request!";
        String expectTitle = "#error";
        int expectSize = 2;
        List<String> badRequest = new ArrayList<>();
        badRequest.add("asdfasefas");
        badRequest.add("654654354135");

        List<String> actualResponse = polygonService.getPolygonReports(badRequest);
        int actualSize = actualResponse.size();
        assertEquals(expectSize, actualSize);

        String actualTitle = actualResponse.get(0);
        String actualMessage = actualResponse.get(1);

        assertEquals(expectTitle, actualTitle);
        assertEquals(expectMessage, actualMessage);
    }


    /**
     * Creates expect response for bind type TT
     * @return list of strings
     */
    private List<String> getExpectPolygonResponseTT() {
        List<String> polygonResponse = new ArrayList<>();

        polygonResponse.add("#residuals");
        polygonResponse.add("elevation=5");
        polygonResponse.add("angle=55");
        polygonResponse.add("x=-0.015");
        polygonResponse.add("y=-0.013");
        polygonResponse.add("absolute=0.020");
        polygonResponse.add("relative=1:11228");
        polygonResponse.add("perimeter=224.560");
        polygonResponse.add("#report-catalog");
        polygonResponse.add("1008 456961.430 2261163.707 16.930");
        polygonResponse.add("1007 456988.790 2261202.196 17.720");
        polygonResponse.add("100 457057.454 2261204.245 17.432");
        polygonResponse.add("101 457122.419 2261202.417 17.257");
        polygonResponse.add("102 457129.441 2261145.698 16.328");
        polygonResponse.add("1003 457129.609 2261111.970 15.767");
        polygonResponse.add("1004 457131.018 2261065.036 14.661");
        polygonResponse.add("#report-plan");
        polygonResponse.add("");
        polygonResponse.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonResponse.add("");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonResponse.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonResponse.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|       1008 |          |          |         |          |          |        |          |        |   456961.430 |  2261163.707 |");
        polygonResponse.add("|            |          |          |         |  54.3534 |          |        |          |        |              |              |");
        polygonResponse.add("|       1007 | 127.0659 |          |  -11.00 |          |          |        |          |        |   456988.790 |  2261202.196 |");
        polygonResponse.add("|            |          |   68.689 |         |   1.4222 |   68.659 |  0.005 |    2.045 |  0.004 |              |              |");
        polygonResponse.add("|        100 | 176.4053 |          |  -11.00 |          |          |        |          |        |   457057.454 |  2261204.245 |");
        polygonResponse.add("|            |          |   64.987 |         | 358.2304 |   64.961 |  0.004 |   -1.832 |  0.004 |              |              |");
        polygonResponse.add("|        101 |  98.4017 |          |  -11.00 |          |          |        |          |        |   457122.419 |  2261202.417 |");
        polygonResponse.add("|            |          |   57.154 |         | 277.0310 |    7.018 |  0.004 |  -56.722 |  0.003 |              |              |");
        polygonResponse.add("|        102 | 173.1354 |          |  -11.00 |          |          |        |          |        |   457129.441 |  2261145.698 |");
        polygonResponse.add("|            |          |   33.730 |         | 270.1653 |    0.166 |  0.002 |  -33.730 |  0.002 |              |              |");
        polygonResponse.add("|       1003 | 181.2628 |          |  -11.00 |          |          |        |          |        |   457129.609 |  2261111.970 |");
        polygonResponse.add("|            |          |          |         | 271.4310 |          |        |          |        |              |              |");
        polygonResponse.add("|       1004 |          |          |         |          |          |        |          |        |   457131.018 |  2261065.036 |");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("     1. Угловые невязки");
        polygonResponse.add("фактическая : 55сек.");
        polygonResponse.add("допустимая : 45сек.");
        polygonResponse.add("     2. Линейные невязки");
        polygonResponse.add("DX : -0.015м.");
        polygonResponse.add("DY : -0.013м.");
        polygonResponse.add("фактическая абсолютная : 0.020м.");
        polygonResponse.add("допустимая абсолютная : 0.20м.");
        polygonResponse.add("фактическая относительная : 1:11228");
        polygonResponse.add("допустимая относительная : 1:2000");
        polygonResponse.add("#report-elevation");
        polygonResponse.add("");
        polygonResponse.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonResponse.add("");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonResponse.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonResponse.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|       1007 |          |            |        |            |   17.720 |");
        polygonResponse.add("|            |   68.689 |     -0.286 |  -1.53 |     -0.288 |          |");
        polygonResponse.add("|        100 |          |            |        |            |   17.432 |");
        polygonResponse.add("|            |   64.987 |     -0.174 |  -1.45 |     -0.175 |          |");
        polygonResponse.add("|        101 |          |            |        |            |   17.257 |");
        polygonResponse.add("|            |   57.154 |     -0.928 |  -1.27 |     -0.929 |          |");
        polygonResponse.add("|        102 |          |            |        |            |   16.328 |");
        polygonResponse.add("|            |   33.730 |     -0.560 |  -0.75 |     -0.561 |          |");
        polygonResponse.add("|       1003 |          |            |        |            |   15.767 |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|контр.суммы |  224.560 |     -1.948 |  -5.00 |     -1.953 |          |");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("фактическая высотная невязка : 5мм.");
        polygonResponse.add("допустимая высотная невязка : 24мм.");

        return polygonResponse;
    }


    /**
     * Creates expect response for bind type TO
     * @return list of strings
     */
    private List<String> getExpectPolygonResponseTO() {
        List<String> polygonResponse = new ArrayList<>();

        polygonResponse.add("#residuals");
        polygonResponse.add("elevation=5");
        polygonResponse.add("angle=-.-");
        polygonResponse.add("x=-0.030");
        polygonResponse.add("y=-0.009");
        polygonResponse.add("absolute=0.031");
        polygonResponse.add("relative=1:7244");
        polygonResponse.add("perimeter=224.560");
        polygonResponse.add("#report-catalog");
        polygonResponse.add("1008 456961.430 2261163.707 16.930");
        polygonResponse.add("1007 456988.790 2261202.196 17.720");
        polygonResponse.add("100 457057.457 2261204.248 17.432");
        polygonResponse.add("101 457122.427 2261202.426 17.257");
        polygonResponse.add("102 457129.462 2261145.708 16.328");
        polygonResponse.add("1003 457129.639 2261111.979 15.767");
        polygonResponse.add("#report-plan");
        polygonResponse.add("");
        polygonResponse.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonResponse.add("");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonResponse.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonResponse.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|       1008 |          |          |         |          |          |        |          |        |   456961.430 |  2261163.707 |");
        polygonResponse.add("|            |          |          |         |  54.3534 |          |        |          |        |              |              |");
        polygonResponse.add("|       1007 | 127.0659 |          |    0.00 |          |          |        |          |        |   456988.790 |  2261202.196 |");
        polygonResponse.add("|            |          |   68.689 |         |   1.4233 |   68.658 |  0.009 |    2.049 |  0.003 |              |              |");
        polygonResponse.add("|        100 | 176.4053 |          |    0.00 |          |          |        |          |        |   457057.457 |  2261204.248 |");
        polygonResponse.add("|            |          |   64.987 |         | 358.2326 |   64.961 |  0.009 |   -1.825 |  0.003 |              |              |");
        polygonResponse.add("|        101 |  98.4017 |          |    0.00 |          |          |        |          |        |   457122.427 |  2261202.426 |");
        polygonResponse.add("|            |          |   57.154 |         | 277.0343 |    7.027 |  0.008 |  -56.720 |  0.002 |              |              |");
        polygonResponse.add("|        102 | 173.1354 |          |    0.00 |          |          |        |          |        |   457129.462 |  2261145.708 |");
        polygonResponse.add("|            |          |   33.730 |         | 270.1737 |    0.173 |  0.005 |  -33.730 |  0.001 |              |              |");
        polygonResponse.add("|       1003 |          |          |         |          |          |        |          |        |   457129.639 |  2261111.979 |");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("     1. Угловые невязки");
        polygonResponse.add("фактическая : -.-сек.");
        polygonResponse.add("допустимая : -.-сек.");
        polygonResponse.add("     2. Линейные невязки");
        polygonResponse.add("DX : -0.030м.");
        polygonResponse.add("DY : -0.009м.");
        polygonResponse.add("фактическая абсолютная : 0.031м.");
        polygonResponse.add("допустимая абсолютная : 0.20м.");
        polygonResponse.add("фактическая относительная : 1:7244");
        polygonResponse.add("допустимая относительная : 1:2000");
        polygonResponse.add("#report-elevation");
        polygonResponse.add("");
        polygonResponse.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonResponse.add("");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonResponse.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonResponse.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|       1007 |          |            |        |            |   17.720 |");
        polygonResponse.add("|            |   68.689 |     -0.286 |  -1.53 |     -0.288 |          |");
        polygonResponse.add("|        100 |          |            |        |            |   17.432 |");
        polygonResponse.add("|            |   64.987 |     -0.174 |  -1.45 |     -0.175 |          |");
        polygonResponse.add("|        101 |          |            |        |            |   17.257 |");
        polygonResponse.add("|            |   57.154 |     -0.928 |  -1.27 |     -0.929 |          |");
        polygonResponse.add("|        102 |          |            |        |            |   16.328 |");
        polygonResponse.add("|            |   33.730 |     -0.560 |  -0.75 |     -0.561 |          |");
        polygonResponse.add("|       1003 |          |            |        |            |   15.767 |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|контр.суммы |  224.560 |     -1.948 |  -5.00 |     -1.953 |          |");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("фактическая высотная невязка : 5мм.");
        polygonResponse.add("допустимая высотная невязка : 24мм.");

        return polygonResponse;
    }

    /**
     * Creates expect response for bind type OT
     * @return list of strings
     */
    private List<String> getExpectPolygonResponseOT() {
        List<String> polygonResponse = new ArrayList<>();

        polygonResponse.add("#residuals");
        polygonResponse.add("elevation=5");
        polygonResponse.add("angle=-.-");
        polygonResponse.add("x=-0.023");
        polygonResponse.add("y=-0.039");
        polygonResponse.add("absolute=0.045");
        polygonResponse.add("relative=1:4990");
        polygonResponse.add("perimeter=224.560");
        polygonResponse.add("#report-catalog");
        polygonResponse.add("1007 456988.790 2261202.196 17.720");
        polygonResponse.add("100 457057.456 2261204.238 17.432");
        polygonResponse.add("101 457122.424 2261202.406 17.257");
        polygonResponse.add("102 457129.442 2261145.694 16.328");
        polygonResponse.add("1003 457129.609 2261111.970 15.767");
        polygonResponse.add("1004 457131.018 2261065.036 14.661");
        polygonResponse.add("#report-plan");
        polygonResponse.add("");
        polygonResponse.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonResponse.add("");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonResponse.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonResponse.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|       1007 | 127.0659 |          |    0.00 |          |          |        |          |        |   456988.790 |  2261202.196 |");
        polygonResponse.add("|            |          |   68.689 |         |   1.4138 |   68.659 |  0.007 |    2.030 |  0.012 |              |              |");
        polygonResponse.add("|        100 | 176.4053 |          |    0.00 |          |          |        |          |        |   457057.456 |  2261204.238 |");
        polygonResponse.add("|            |          |   64.987 |         | 358.2231 |   64.961 |  0.007 |   -1.843 |  0.011 |              |              |");
        polygonResponse.add("|        101 |  98.4017 |          |    0.00 |          |          |        |          |        |   457122.424 |  2261202.406 |");
        polygonResponse.add("|            |          |   57.154 |         | 277.0248 |    7.012 |  0.006 |  -56.722 |  0.010 |              |              |");
        polygonResponse.add("|        102 | 173.1354 |          |    0.00 |          |          |        |          |        |   457129.442 |  2261145.694 |");
        polygonResponse.add("|            |          |   33.730 |         | 270.1642 |    0.164 |  0.003 |  -33.730 |  0.006 |              |              |");
        polygonResponse.add("|       1003 | 181.2628 |          |    0.00 |          |          |        |          |        |   457129.609 |  2261111.970 |");
        polygonResponse.add("|            |          |          |         | 271.4310 |          |        |          |        |              |              |");
        polygonResponse.add("|       1004 |          |          |         |          |          |        |          |        |   457131.018 |  2261065.036 |");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("     1. Угловые невязки");
        polygonResponse.add("фактическая : -.-сек.");
        polygonResponse.add("допустимая : -.-сек.");
        polygonResponse.add("     2. Линейные невязки");
        polygonResponse.add("DX : -0.023м.");
        polygonResponse.add("DY : -0.039м.");
        polygonResponse.add("фактическая абсолютная : 0.045м.");
        polygonResponse.add("допустимая абсолютная : 0.20м.");
        polygonResponse.add("фактическая относительная : 1:4990");
        polygonResponse.add("допустимая относительная : 1:2000");
        polygonResponse.add("#report-elevation");
        polygonResponse.add("");
        polygonResponse.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonResponse.add("");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonResponse.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonResponse.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|       1007 |          |            |        |            |   17.720 |");
        polygonResponse.add("|            |   68.689 |     -0.286 |  -1.53 |     -0.288 |          |");
        polygonResponse.add("|        100 |          |            |        |            |   17.432 |");
        polygonResponse.add("|            |   64.987 |     -0.174 |  -1.45 |     -0.175 |          |");
        polygonResponse.add("|        101 |          |            |        |            |   17.257 |");
        polygonResponse.add("|            |   57.154 |     -0.928 |  -1.27 |     -0.929 |          |");
        polygonResponse.add("|        102 |          |            |        |            |   16.328 |");
        polygonResponse.add("|            |   33.730 |     -0.560 |  -0.75 |     -0.561 |          |");
        polygonResponse.add("|       1003 |          |            |        |            |   15.767 |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|контр.суммы |  224.560 |     -1.948 |  -5.00 |     -1.953 |          |");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("фактическая высотная невязка : 5мм.");
        polygonResponse.add("допустимая высотная невязка : 24мм.");

        return polygonResponse;
    }

    /**
     * Creates expect response for bind type TZ
     * @return list of strings
     */
    private List<String> getExpectPolygonResponseTZ() {
        List<String> polygonResponse = new ArrayList<>();

        polygonResponse.add("#residuals");
        polygonResponse.add("elevation=-.-");
        polygonResponse.add("angle=-.-");
        polygonResponse.add("x=-.-");
        polygonResponse.add("y=-.-");
        polygonResponse.add("absolute=-.-");
        polygonResponse.add("relative=-.-");
        polygonResponse.add("perimeter=190.830");
        polygonResponse.add("#report-catalog");
        polygonResponse.add("1008 456961.430 2261163.707 16.930");
        polygonResponse.add("1007 456988.790 2261202.196 17.720");
        polygonResponse.add("100 457057.448 2261204.245 17.434");
        polygonResponse.add("101 457122.409 2261202.420 17.260");
        polygonResponse.add("102 457129.436 2261145.700 16.332");
        polygonResponse.add("#report-plan");
        polygonResponse.add("");
        polygonResponse.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonResponse.add("");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonResponse.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonResponse.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|       1008 |          |          |         |          |          |        |          |        |   456961.430 |  2261163.707 |");
        polygonResponse.add("|            |          |          |         |  54.3534 |          |        |          |        |              |              |");
        polygonResponse.add("|       1007 | 127.0659 |          |    0.00 |          |          |        |          |        |   456988.790 |  2261202.196 |");
        polygonResponse.add("|            |          |   68.689 |         |   1.4233 |   68.658 |  0.000 |    2.049 |  0.000 |              |              |");
        polygonResponse.add("|        100 | 176.4053 |          |    0.00 |          |          |        |          |        |   457057.448 |  2261204.245 |");
        polygonResponse.add("|            |          |   64.987 |         | 358.2326 |   64.961 |  0.000 |   -1.825 |  0.000 |              |              |");
        polygonResponse.add("|        101 |  98.4017 |          |    0.00 |          |          |        |          |        |   457122.409 |  2261202.420 |");
        polygonResponse.add("|            |          |   57.154 |         | 277.0343 |    7.027 |  0.000 |  -56.720 |  0.000 |              |              |");
        polygonResponse.add("|        102 |          |          |         |          |          |        |          |        |   457129.436 |  2261145.700 |");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 190.830м.");
        polygonResponse.add("     1. Угловые невязки");
        polygonResponse.add("фактическая : -.-сек.");
        polygonResponse.add("допустимая : -.-сек.");
        polygonResponse.add("     2. Линейные невязки");
        polygonResponse.add("DX : -.-м.");
        polygonResponse.add("DY : -.-м.");
        polygonResponse.add("фактическая абсолютная : -.-м.");
        polygonResponse.add("допустимая абсолютная : 0.20м.");
        polygonResponse.add("фактическая относительная : -.-");
        polygonResponse.add("допустимая относительная : 1:2000");
        polygonResponse.add("#report-elevation");
        polygonResponse.add("");
        polygonResponse.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonResponse.add("");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonResponse.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonResponse.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|       1007 |          |            |        |            |   17.720 |");
        polygonResponse.add("|            |   68.689 |     -0.286 |   0.00 |     -0.286 |          |");
        polygonResponse.add("|        100 |          |            |        |            |   17.434 |");
        polygonResponse.add("|            |   64.987 |     -0.174 |   0.00 |     -0.174 |          |");
        polygonResponse.add("|        101 |          |            |        |            |   17.260 |");
        polygonResponse.add("|            |   57.154 |     -0.928 |   0.00 |     -0.928 |          |");
        polygonResponse.add("|        102 |          |            |        |            |   16.332 |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|контр.суммы |  190.830 |     -1.388 |   0.00 |     -1.388 |          |");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 190.830м.");
        polygonResponse.add("фактическая высотная невязка : -.-мм.");
        polygonResponse.add("допустимая высотная невязка : 22мм.");

        return polygonResponse;
    }

    /**
     * Creates expect response for bind type ZT
     * @return list of strings
     */
    private List<String> getExpectPolygonResponseZT() {
        List<String> polygonResponse = new ArrayList<>();

        polygonResponse.add("#residuals");
        polygonResponse.add("elevation=-.-");
        polygonResponse.add("angle=-.-");
        polygonResponse.add("x=-.-");
        polygonResponse.add("y=-.-");
        polygonResponse.add("absolute=-.-");
        polygonResponse.add("relative=-.-");
        polygonResponse.add("perimeter=155.871");
        polygonResponse.add("#report-catalog");
        polygonResponse.add("100 457057.472 2261204.265 17.429");
        polygonResponse.add("101 457122.433 2261202.422 17.255");
        polygonResponse.add("102 457129.445 2261145.700 16.327");
        polygonResponse.add("1003 457129.609 2261111.970 15.767");
        polygonResponse.add("1004 457131.018 2261065.036 14.661");
        polygonResponse.add("#report-plan");
        polygonResponse.add("");
        polygonResponse.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonResponse.add("");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonResponse.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonResponse.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|        100 | 176.4053 |          |    0.00 |          |          |        |          |        |   457057.472 |  2261204.265 |");
        polygonResponse.add("|            |          |   64.987 |         | 358.2231 |   64.961 |  0.000 |   -1.843 |  0.000 |              |              |");
        polygonResponse.add("|        101 |  98.4017 |          |    0.00 |          |          |        |          |        |   457122.433 |  2261202.422 |");
        polygonResponse.add("|            |          |   57.154 |         | 277.0248 |    7.012 |  0.000 |  -56.722 |  0.000 |              |              |");
        polygonResponse.add("|        102 | 173.1354 |          |    0.00 |          |          |        |          |        |   457129.445 |  2261145.700 |");
        polygonResponse.add("|            |          |   33.730 |         | 270.1642 |    0.164 |  0.000 |  -33.730 |  0.000 |              |              |");
        polygonResponse.add("|       1003 | 181.2628 |          |    0.00 |          |          |        |          |        |   457129.609 |  2261111.970 |");
        polygonResponse.add("|            |          |          |         | 271.4310 |          |        |          |        |              |              |");
        polygonResponse.add("|       1004 |          |          |         |          |          |        |          |        |   457131.018 |  2261065.036 |");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 155.871м.");
        polygonResponse.add("     1. Угловые невязки");
        polygonResponse.add("фактическая : -.-сек.");
        polygonResponse.add("допустимая : -.-сек.");
        polygonResponse.add("     2. Линейные невязки");
        polygonResponse.add("DX : -.-м.");
        polygonResponse.add("DY : -.-м.");
        polygonResponse.add("фактическая абсолютная : -.-м.");
        polygonResponse.add("допустимая абсолютная : 0.20м.");
        polygonResponse.add("фактическая относительная : -.-");
        polygonResponse.add("допустимая относительная : 1:2000");
        polygonResponse.add("#report-elevation");
        polygonResponse.add("");
        polygonResponse.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonResponse.add("");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonResponse.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonResponse.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|        100 |          |            |        |            |   17.429 |");
        polygonResponse.add("|            |   64.987 |     -0.174 |   0.00 |     -0.174 |          |");
        polygonResponse.add("|        101 |          |            |        |            |   17.255 |");
        polygonResponse.add("|            |   57.154 |     -0.928 |   0.00 |     -0.928 |          |");
        polygonResponse.add("|        102 |          |            |        |            |   16.327 |");
        polygonResponse.add("|            |   33.730 |     -0.560 |   0.00 |     -0.560 |          |");
        polygonResponse.add("|       1003 |          |            |        |            |   15.767 |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|контр.суммы |  155.871 |     -1.662 |   0.00 |     -1.662 |          |");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 155.871м.");
        polygonResponse.add("фактическая высотная невязка : -.-мм.");
        polygonResponse.add("допустимая высотная невязка : 20мм.");

        return polygonResponse;
    }

    /**
     * Creates expect response for bind type OO
     * @return list of strings
     */
    private List<String> getExpectPolygonResponseOO() {
        List<String> polygonResponse = new ArrayList<>();

        polygonResponse.add("#residuals");
        polygonResponse.add("elevation=5");
        polygonResponse.add("angle=-.-");
        polygonResponse.add("x=-0.063");
        polygonResponse.add("y=0.041");
        polygonResponse.add("absolute=0.075");
        polygonResponse.add("relative=1:2994");
        polygonResponse.add("perimeter=224.560");
        polygonResponse.add("#report-catalog");
        polygonResponse.add("1007 456988.700 2261202.196 17.720");
        polygonResponse.add("100 457057.377 2261204.252 17.432");
        polygonResponse.add("101 457122.357 2261202.434 17.257");
        polygonResponse.add("102 457129.416 2261145.706 16.328");
        polygonResponse.add("1003 457129.609 2261111.970 15.767");
        polygonResponse.add("#report-plan");
        polygonResponse.add("");
        polygonResponse.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        polygonResponse.add("");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        polygonResponse.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        polygonResponse.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        polygonResponse.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        polygonResponse.add("|       1007 | 127.0659 |          |    0.00 |          |          |        |          |        |   456988.700 |  2261202.196 |");
        polygonResponse.add("|            |          |   68.689 |         |   1.4334 |   68.658 |  0.019 |    2.069 | -0.013 |              |              |");
        polygonResponse.add("|        100 | 176.4053 |          |    0.00 |          |          |        |          |        |   457057.377 |  2261204.252 |");
        polygonResponse.add("|            |          |   64.987 |         | 358.2427 |   64.962 |  0.018 |   -1.806 | -0.012 |              |              |");
        polygonResponse.add("|        101 |  98.4017 |          |    0.00 |          |          |        |          |        |   457122.357 |  2261202.434 |");
        polygonResponse.add("|            |          |   57.154 |         | 277.0444 |    7.043 |  0.016 |  -56.718 | -0.010 |              |              |");
        polygonResponse.add("|        102 | 173.1354 |          |    0.00 |          |          |        |          |        |   457129.416 |  2261145.706 |");
        polygonResponse.add("|            |          |   33.730 |         | 270.1838 |    0.183 |  0.009 |  -33.730 | -0.006 |              |              |");
        polygonResponse.add("|       1003 |          |          |         |          |          |        |          |        |   457129.609 |  2261111.970 |");
        polygonResponse.add("-------------------------------------------------------------------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("     1. Угловые невязки");
        polygonResponse.add("фактическая : -.-сек.");
        polygonResponse.add("допустимая : -.-сек.");
        polygonResponse.add("     2. Линейные невязки");
        polygonResponse.add("DX : -0.063м.");
        polygonResponse.add("DY : 0.041м.");
        polygonResponse.add("фактическая абсолютная : 0.075м.");
        polygonResponse.add("допустимая абсолютная : 0.20м.");
        polygonResponse.add("фактическая относительная : 1:2994");
        polygonResponse.add("допустимая относительная : 1:2000");
        polygonResponse.add("#report-elevation");
        polygonResponse.add("");
        polygonResponse.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
        polygonResponse.add("");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
        polygonResponse.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
        polygonResponse.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|       1007 |          |            |        |            |   17.720 |");
        polygonResponse.add("|            |   68.689 |     -0.286 |  -1.53 |     -0.288 |          |");
        polygonResponse.add("|        100 |          |            |        |            |   17.432 |");
        polygonResponse.add("|            |   64.987 |     -0.174 |  -1.45 |     -0.175 |          |");
        polygonResponse.add("|        101 |          |            |        |            |   17.257 |");
        polygonResponse.add("|            |   57.154 |     -0.928 |  -1.27 |     -0.929 |          |");
        polygonResponse.add("|        102 |          |            |        |            |   16.328 |");
        polygonResponse.add("|            |   33.730 |     -0.560 |  -0.75 |     -0.561 |          |");
        polygonResponse.add("|       1003 |          |            |        |            |   15.767 |");
        polygonResponse.add("|------------|----------|------------|--------|------------|----------|");
        polygonResponse.add("|контр.суммы |  224.560 |     -1.948 |  -5.00 |     -1.953 |          |");
        polygonResponse.add("-----------------------------------------------------------------------");
        polygonResponse.add("");
        polygonResponse.add("   Технические параметры полигона");
        polygonResponse.add("Периметр полигона : 224.560м.");
        polygonResponse.add("фактическая высотная невязка : 5мм.");
        polygonResponse.add("допустимая высотная невязка : 24мм.");

        return polygonResponse;
    }


    /**
     * Creates test polygonResponse with bindType = TT
     * @return list of strings
     */
    private List<String> getTestPolygonRequestTT() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("1008 Not Not Not 456961.430 2261163.707 16.930");
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.720");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.609 2261111.97 15.767");
        polygonRequest.add("1004 Not Not Not 457131.018 2261065.036 14.661");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = TO
     * @return list of strings
     */
    private List<String> getTestPolygonRequestTO() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("1008 Not Not Not 456961.430 2261163.707 16.930");
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.720");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.639 2261111.979 15.767");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = OT
     * @return list of strings
     */
    private List<String> getTestPolygonRequestOT() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.720");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.609 2261111.97 15.767");
        polygonRequest.add("1004 Not Not Not 457131.018 2261065.036 14.661");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = OO
     * @return list of strings
     */
    private List<String> getTestPolygonRequestOO() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.700 2261202.196 17.720");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.609 2261111.97 15.767");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = TZ
     * @return list of strings
     */
    private List<String> getTestPolygonRequestTZ() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("1008 Not Not Not 456961.430 2261163.707 16.930");
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.720");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = ZT
     * @return list of strings
     */
    private List<String> getTestPolygonRequestZT() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.609 2261111.97 15.767");
        polygonRequest.add("1004 Not Not Not 457131.018 2261065.036 14.661");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = ZZ
     * @return list of strings
     */
    private List<String> getTestPolygonRequestZZ() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");

        return polygonRequest;
    }

    /**
     * Creates test polygonResponse with bindType = ZZ
     * @return list of strings
     */
    private List<String> getTestPolygonRequestFewStations() {
        List<String> polygonRequest = new ArrayList<>();

        polygonRequest.add("#valid-residuals");
        polygonRequest.add("elevation=50");
        polygonRequest.add("angle=20");
        polygonRequest.add("absolute=0.20");
        polygonRequest.add("relative=1:2000");
        polygonRequest.add("#polygon");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");

        return polygonRequest;
    }


}
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


}
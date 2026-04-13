package ru.taheoport.geocalculator_service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.BindType;
import ru.taheoport.geocalculator_service.model.PolygonStation;
import ru.taheoport.geocalculator_service.model.Residuals;
import ru.taheoport.geocalculator_service.model.ValidResiduals;
import ru.taheoport.geocalculator_service.repository.PolygonRepository;
import ru.taheoport.geocalculator_service.repository.PolygonRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        PolygonCalculatorImpl.class,
        PolygonRepositoryImpl.class,
        DirectCalculatorDefault.class,
        InverseCalculatorImpl.class,
        Residuals.class,
        PolygonMapperImpl.class,
        DataMapperDefault.class,
        ValidResiduals.class
})
class PolygonCalculatorImplTest {

    @Autowired
    private PolygonCalculator polygonCalculator;

    @Autowired
    private PolygonRepository polygonRepository;

    @Autowired
    private PolygonMapper polygonMapper;

    @Autowired
    private ValidResiduals validResiduals;

    @Autowired
    private Residuals residuals;

    @Autowired
    private InverseCalculator inverseCalculator;

    @Test
    void setBindTypeTestTT() {
        BindType expectBindType = BindType.TT;
        List<String> polygonRequest = getTestPolygonRequestTT();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestTO() {
        BindType expectBindType = BindType.TO;
        List<String> polygonRequest = getTestPolygonRequestTO();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestOT() {
        BindType expectBindType = BindType.OT;
        List<String> polygonRequest = getTestPolygonRequestOT();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestOO() {
        BindType expectBindType = BindType.OO;
        List<String> polygonRequest = getTestPolygonRequestOO();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestTZ() {
        BindType expectBindType = BindType.TZ;
        List<String> polygonRequest = getTestPolygonRequestTZ();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestZT() {
        BindType expectBindType = BindType.ZT;
        List<String> polygonRequest = getTestPolygonRequestZT();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestZZ() {
        BindType expectBindType = BindType.ZZ;
        List<String> polygonRequest = getTestPolygonRequestZZ();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 4, 224560",
            "0, 6, 224560",
            "1, 3, 190830"
    })
    void  setPerimeterTest(int start, int end, long expectPerimeter) {
        List<String> polygonRequest = getTestPolygonRequestTT();

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);
        polygonCalculator.setPerimeter(start, end);
        long actualPerimeter = residuals.getPerimeter();
        assertEquals(expectPerimeter, actualPerimeter);
    }

    @Test
    void setCorrectionHorAngleTest(){
        List<String> polygonRequest = getTestPolygonRequestTT();
        long expectResidualsAngle = 55;
        double expectCorrectionAngle = -11.0;

        boolean access = polygonMapper.polygonRequestToPolygon(
                polygonRequest,
                polygonRepository,
                validResiduals
        );
        assertTrue(access);

        int sizePolygon = polygonRepository.size();
        PolygonStation baseA = polygonRepository.getStationById(0);
        PolygonStation baseB = polygonRepository.getStationById(1);
        PolygonStation baseC = polygonRepository.getStationById(sizePolygon - 2);
        PolygonStation baseD = polygonRepository.getStationById(sizePolygon - 1);
        baseA.setDirectionAngle(inverseCalculator.getDirection(
                baseA.getStationX(),
                baseA.getStationY(),
                baseB.getStationX(),
                baseB.getStationY()
        ));
        baseC.setDirectionAngle(inverseCalculator.getDirection(
                baseC.getStationX(),
                baseC.getStationY(),
                baseD.getStationX(),
                baseD.getStationY()
        ));
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);

        long actualResidualsAngle = residuals.getAngle();

        assertEquals(expectResidualsAngle, actualResidualsAngle);
        for (int i = 1; i <= sizePolygon - 2; i++) {
            double actualCorrectionAngle = polygonRepository.getStationById(i).getCorrectionHorAngle();
            assertEquals(expectCorrectionAngle, actualCorrectionAngle, 0.00000001);
        }
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
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.715");
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
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.715");
        polygonRequest.add("100 176.4053 64.987 -0.174 Not Not Not");
        polygonRequest.add("101 98.4017 57.154 -0.928 Not Not Not");
        polygonRequest.add("102 173.1354 33.73 -0.56 Not Not Not");
        polygonRequest.add("1003 181.2628 Not Not 457129.609 2261111.97 15.767");

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
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.715");
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
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.715");
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
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.715");
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
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

    @ParameterizedTest
    @CsvSource({
            "0.124, 0",
            "-0.124, 0",
            "0.678, 1",
            "-0.678, -1",
            "0.546, 1",
            "-0.546, -1",
            "2.567, 3",
            "-2.567, -3"
    })
    void doubleToLongTest(
            double value,
            long expectValue
    ) {
        long actualValue = polygonCalculator.doubleToLong(value);

        assertEquals(expectValue, actualValue);
    }

    @Test
    void setBindTypeTestTT() {
        BindType expectBindType = BindType.TT;
        List<String> polygonRequest = getTestPolygonRequestTT();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestTO() {
        BindType expectBindType = BindType.TO;
        List<String> polygonRequest = getTestPolygonRequestTO();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestOT() {
        BindType expectBindType = BindType.OT;
        List<String> polygonRequest = getTestPolygonRequestOT();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestOO() {
        BindType expectBindType = BindType.OO;
        List<String> polygonRequest = getTestPolygonRequestOO();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestTZ() {
        BindType expectBindType = BindType.TZ;
        List<String> polygonRequest = getTestPolygonRequestTZ();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestZT() {
        BindType expectBindType = BindType.ZT;
        List<String> polygonRequest = getTestPolygonRequestZT();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
        assertTrue(access);

        polygonCalculator.setBindType();
        BindType actualBindType = residuals.getBindType();

        assertEquals(expectBindType, actualBindType);
    }

    @Test
    void setBindTypeTestZZ() {
        BindType expectBindType = BindType.ZZ;
        List<String> polygonRequest = getTestPolygonRequestZZ();

        boolean access = polygonMapper.polygonRequestToPolygon(polygonRequest);
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
        polygonMapper.polygonRequestToPolygon(getTestPolygonRequestTT());
        polygonCalculator.setPerimeter(start, end);

        long actualPerimeter = residuals.getPerimeter();

        assertEquals(expectPerimeter, actualPerimeter);
    }

    @Test
    void setCorrectionHorAngleTest(){
        loadTestPolygonTT();
        long expectResidualsAngle = 55;
        double expectCorrectionAngle = -11.0;
        int sizePolygon = polygonRepository.size();
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);

        long actualResidualsAngle = residuals.getAngle();

        assertEquals(expectResidualsAngle, actualResidualsAngle);
        for (int i = 1; i <= sizePolygon - 2; i++) {
            double actualCorrectionAngle = polygonRepository.getStationById(i).getCorrectionHorAngle();
            assertEquals(expectCorrectionAngle, actualCorrectionAngle, 0.00000001);
        }
    }

    @Test
    void setDirectionAngleDirectOrderTest() {
        boolean success = loadTestPolygonTT();
        assertTrue(success);

        long expectResidualsAngle = 55;
        long expectFirst = 6142;
        long expectSecond = 1290184;
        long expectThird = 997390;
        long expectFourth = 973013;
        int sizePolygon = polygonRepository.size();
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);
        polygonCalculator.setDirectionAngle(1, sizePolygon - 3);

        long actualResidualsAngle = residuals.getAngle();
        long actualFirst = polygonRepository.getStationById(1).getDirectionAngle();
        long actualSecond = polygonRepository.getStationById(2).getDirectionAngle();
        long actualThird = polygonRepository.getStationById(3).getDirectionAngle();
        long actualFourth = polygonRepository.getStationById(4).getDirectionAngle();

        assertEquals(expectResidualsAngle, actualResidualsAngle);
        assertEquals(expectFirst, actualFirst);
        assertEquals(expectSecond, actualSecond);
        assertEquals(expectThird, actualThird);
        assertEquals(expectFourth, actualFourth);
    }

    @Test
    void setDirectionAngleReverseOrderTest() {
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestOT());

        int sizePolygon = polygonRepository.size();
        PolygonStation baseC = polygonRepository.getStationById(sizePolygon - 2);
        PolygonStation baseD = polygonRepository.getStationById(sizePolygon - 1);
        baseC.setDirectionAngle(inverseCalculator.getDirection(
                baseC.getStationX(),
                baseC.getStationY(),
                baseD.getStationX(),
                baseD.getStationY()
        ));

        long expectFirst = 6098;
        long expectSecond = 1290151;
        long expectThird = 997368;
        long expectFourth = 973002;
        polygonCalculator.setDirectionAngle(sizePolygon - 3, 0);

        long actualFirst = polygonRepository.getStationById(0).getDirectionAngle();
        long actualSecond = polygonRepository.getStationById(1).getDirectionAngle();
        long actualThird = polygonRepository.getStationById(2).getDirectionAngle();
        long actualFourth = polygonRepository.getStationById(3).getDirectionAngle();

        assertEquals(expectFirst, actualFirst);
        assertEquals(expectSecond, actualSecond);
        assertEquals(expectThird, actualThird);
        assertEquals(expectFourth, actualFourth);
    }


    @Test
    void setDeltaXYTest() {
        boolean success = loadTestPolygonTT();
        assertTrue(success);
        long expectFirstDx = 68659;
        long expectFirstDy = 2045;
        long expectSecondDx = 64961;
        long expectSecondDy = -1832;
        long expectThirdDx = 7018;
        long expectThirdDy = -56722;
        long expectFourthDx = 166;
        long expectFourthDy = -33730;
        int sizePolygon = polygonRepository.size();
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);
        polygonCalculator.setDirectionAngle(1, sizePolygon - 3);
        polygonCalculator.setDeltaXY(1, sizePolygon - 3);

        long actualFirstDx = polygonRepository.getStationById(1).getDeltaX();
        long actualFirstDy = polygonRepository.getStationById(1).getDeltaY();
        long actualSecondDx = polygonRepository.getStationById(2).getDeltaX();
        long actualSecondDy = polygonRepository.getStationById(2).getDeltaY();
        long actualThirdDx = polygonRepository.getStationById(3).getDeltaX();
        long actualThirdDy = polygonRepository.getStationById(3).getDeltaY();
        long actualFourthDx = polygonRepository.getStationById(4).getDeltaX();
        long actualFourthDy = polygonRepository.getStationById(4).getDeltaY();

        assertEquals(expectFirstDx, actualFirstDx);
        assertEquals(expectFirstDy, actualFirstDy);
        assertEquals(expectSecondDx, actualSecondDx);
        assertEquals(expectSecondDy, actualSecondDy);
        assertEquals(expectThirdDx, actualThirdDx);
        assertEquals(expectThirdDy, actualThirdDy);
        assertEquals(expectFourthDx, actualFourthDx);
        assertEquals(expectFourthDy, actualFourthDy);
    }

    @Test
    void setCorrectionXYTestResiduals() {
        boolean success = loadTestPolygonTT();
        assertTrue(success);
        long expectFx = -15;
        long expectFy = -13;
        long expectAbsolute = 20;
        String expectRelative = "1:11228";
        int sizePolygon = polygonRepository.size();
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);
        polygonCalculator.setDirectionAngle(1, sizePolygon - 3);
        polygonCalculator.setDeltaXY(1, sizePolygon - 3);
        polygonCalculator.setPerimeter(1, sizePolygon - 3);
        polygonCalculator.setCorrectionXY(1, sizePolygon - 3);

        long actualFx = residuals.getLinearX();
        long actualFy = residuals.getLinearY();
        double actualAbsolute = residuals.getAbsolute();
        String actualRelative = residuals.getRelative();

        assertEquals(expectFx, actualFx);
        assertEquals(expectFy, actualFy);
        assertEquals(expectAbsolute, actualAbsolute, 0.0000007);
        assertEquals(expectRelative, actualRelative);
    }

    @Test
    void setCorrectionXYTest() {
        boolean success = loadTestPolygonTT();
        assertTrue(success);
        long expectFirstCorrX = 5;
        long expectFirstCorrY = 4;
        long expectSecondCorrX = 4;
        long expectSecondCorrY = 4;
        long expectThirdCorrX = 4;
        long expectThirdCorrY = 3;
        long expectFourthCorrX = 2;
        long expectFourthCorrY = 2;
        int sizePolygon = polygonRepository.size();
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);
        polygonCalculator.setDirectionAngle(1, sizePolygon - 3);
        polygonCalculator.setDeltaXY(1, sizePolygon - 3);
        polygonCalculator.setPerimeter(1, sizePolygon - 3);
        polygonCalculator.setCorrectionXY(1, sizePolygon - 3);

        long actualFirstCorrX = polygonRepository.getStationById(1).getCorrectionX();
        long actualFirstCorrY = polygonRepository.getStationById(1).getCorrectionY();
        long actualSecondCorrX = polygonRepository.getStationById(2).getCorrectionX();
        long actualSecondCorrY = polygonRepository.getStationById(2).getCorrectionY();
        long actualThirdCorrX = polygonRepository.getStationById(3).getCorrectionX();
        long actualThirdCorrY = polygonRepository.getStationById(3).getCorrectionY();
        long actualFourthCorrX = polygonRepository.getStationById(4).getCorrectionX();
        long actualFourthCorrY = polygonRepository.getStationById(4).getCorrectionY();

        assertEquals(expectFirstCorrX, actualFirstCorrX);
        assertEquals(expectFirstCorrY, actualFirstCorrY);
        assertEquals(expectSecondCorrX, actualSecondCorrX);
        assertEquals(expectSecondCorrY, actualSecondCorrY);
        assertEquals(expectThirdCorrX, actualThirdCorrX);
        assertEquals(expectThirdCorrY, actualThirdCorrY);
        assertEquals(expectFourthCorrX, actualFourthCorrX);
        assertEquals(expectFourthCorrY, actualFourthCorrY);
    }

    @Test
    void setCorrectionZTest() {
        boolean success = loadTestPolygonTT();
        assertTrue(success);
        long expectResidualElevation = 5;
        double expectFirstCorrZ = -1.52941307445672;
        double expectSecondCorrZ = -1.4469852155326;
        double expectThirdCorrZ = -1.27257748485928;
        double expectFourthCorrZ = -0.751024225151407;

        int sizePolygon = polygonRepository.size();
        polygonCalculator.setPerimeter(1, sizePolygon - 3);
        polygonCalculator.setCorrectionZ(1, sizePolygon - 3);

        long actualResidualElevation = residuals.getElevation();
        double actualFirstCorrZ = polygonRepository.getStationById(1).getCorrectionZ();
        double actualSecondCorrZ = polygonRepository.getStationById(2).getCorrectionZ();
        double actualThirdCorrZ = polygonRepository.getStationById(3).getCorrectionZ();
        double actualFourthCorrZ = polygonRepository.getStationById(4).getCorrectionZ();

        assertEquals(expectResidualElevation, actualResidualElevation);
        assertEquals(expectFirstCorrZ, actualFirstCorrZ, 0.001);
        assertEquals(expectSecondCorrZ, actualSecondCorrZ, 0.001);
        assertEquals(expectThirdCorrZ, actualThirdCorrZ, 0.001);
        assertEquals(expectFourthCorrZ, actualFourthCorrZ, 0.001);
    }

    @Test
    void setXYZTestDirectOrder() {
        boolean success = loadTestPolygonTT();
        assertTrue(success);
        long expectFirstX = 457057454L;
        long expectFirstY = 2261204245L;
        long expectFirstZ = 17432L;
        long expectSecondX = 457122419L;
        long expectSecondY = 2261202417L;
        long expectSecondZ = 17257L;
        long expectThirdX = 457129441L;
        long expectThirdY = 2261145698L;
        long expectThirdZ = 16328L;
        int sizePolygon = polygonRepository.size();
        polygonCalculator.setCorrectionHorAngle(1, sizePolygon - 2);
        polygonCalculator.setDirectionAngle(1, sizePolygon - 3);
        polygonCalculator.setDeltaXY(1, sizePolygon - 3);
        polygonCalculator.setPerimeter(1, sizePolygon - 3);
        polygonCalculator.setCorrectionXY(1, sizePolygon - 3);
        polygonCalculator.setCorrectionZ(1, sizePolygon -3);
        polygonCalculator.setXYZ(2, sizePolygon - 3);

        long actualFirstX = polygonRepository.getStationById(2).getStationX();
        long actualFirstY = polygonRepository.getStationById(2).getStationY();
        long actualFirstZ = polygonRepository.getStationById(2).getStationZ();
        long actualSecondX = polygonRepository.getStationById(3).getStationX();
        long actualSecondY = polygonRepository.getStationById(3).getStationY();
        long actualSecondZ = polygonRepository.getStationById(3).getStationZ();
        long actualThirdX = polygonRepository.getStationById(4).getStationX();
        long actualThirdY = polygonRepository.getStationById(4).getStationY();
        long actualThirdZ = polygonRepository.getStationById(4).getStationZ();

        assertEquals(expectFirstX, actualFirstX);
        assertEquals(expectFirstY, actualFirstY);
        assertEquals(expectFirstZ, actualFirstZ);
        assertEquals(expectSecondX, actualSecondX);
        assertEquals(expectSecondY, actualSecondY);
        assertEquals(expectSecondZ, actualSecondZ);
        assertEquals(expectThirdX, actualThirdX);
        assertEquals(expectThirdY, actualThirdY);
        assertEquals(expectThirdZ, actualThirdZ);
    }

    @Test
    void adjustPolygonTestTT() {
        BindType expectBindType = BindType.TT;
        long expectPerimeter = 224560;
        long expectResidualsAngle = 55;
        long expectFx = -15;
        long expectFy = -13;
        long expectAbsolute = 20;
        String expectRelative = "1:11228";
        long expectFirstX = 457057454L;
        long expectFirstY = 2261204245L;
        long expectFirstZ = 17432L;
        long expectSecondX = 457122419L;
        long expectSecondY = 2261202417L;
        long expectSecondZ = 17257L;
        long expectThirdX = 457129441L;
        long expectThirdY = 2261145698L;
        long expectThirdZ = 16328L;
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestTT());
        assertTrue(success);
        polygonCalculator.adjustPolygon();

        BindType actualBindType = residuals.getBindType();
        long actualPerimeter = residuals.getPerimeter();
        long actualResidualsAngle = residuals.getAngle();
        long actualFx = residuals.getLinearX();
        long actualFy = residuals.getLinearY();
        double actualAbsolute = residuals.getAbsolute();
        String actualRelative = residuals.getRelative();
        long actualFirstX = polygonRepository.getStationById(2).getStationX();
        long actualFirstY = polygonRepository.getStationById(2).getStationY();
        long actualFirstZ = polygonRepository.getStationById(2).getStationZ();
        long actualSecondX = polygonRepository.getStationById(3).getStationX();
        long actualSecondY = polygonRepository.getStationById(3).getStationY();
        long actualSecondZ = polygonRepository.getStationById(3).getStationZ();
        long actualThirdX = polygonRepository.getStationById(4).getStationX();
        long actualThirdY = polygonRepository.getStationById(4).getStationY();
        long actualThirdZ = polygonRepository.getStationById(4).getStationZ();

        assertEquals(expectBindType, actualBindType);
        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectResidualsAngle, actualResidualsAngle);
        assertEquals(expectFx, actualFx);
        assertEquals(expectFy, actualFy);
        assertEquals(expectAbsolute, actualAbsolute, 0.0000007);
        assertEquals(expectRelative, actualRelative);
        assertEquals(expectFirstX, actualFirstX);
        assertEquals(expectFirstY, actualFirstY);
        assertEquals(expectFirstZ, actualFirstZ);
        assertEquals(expectSecondX, actualSecondX);
        assertEquals(expectSecondY, actualSecondY);
        assertEquals(expectSecondZ, actualSecondZ);
        assertEquals(expectThirdX, actualThirdX);
        assertEquals(expectThirdY, actualThirdY);
        assertEquals(expectThirdZ, actualThirdZ);
    }

    @Test
    void adjustPolygonTestTO() {
        BindType expectBindType = BindType.TO;
        long expectPerimeter = 224560;
        long expectFx = -30;
        long expectFy = -9;
        long expectAbsolute = 31;
        String expectRelative = "1:7244";
        long expectFirstX = 457057457L;
        long expectFirstY = 2261204248L;
        long expectFirstZ = 17432L;
        long expectSecondX = 457122427L;
        long expectSecondY = 2261202426L;
        long expectSecondZ = 17257L;
        long expectThirdX = 457129462L;
        long expectThirdY = 2261145708L;
        long expectThirdZ = 16328L;
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestTO());
        assertTrue(success);
        polygonCalculator.adjustPolygon();

        BindType actualBindType = residuals.getBindType();
        long actualPerimeter = residuals.getPerimeter();
        long actualFx = residuals.getLinearX();
        long actualFy = residuals.getLinearY();
        double actualAbsolute = residuals.getAbsolute();
        String actualRelative = residuals.getRelative();
        long actualFirstX = polygonRepository.getStationById(2).getStationX();
        long actualFirstY = polygonRepository.getStationById(2).getStationY();
        long actualFirstZ = polygonRepository.getStationById(2).getStationZ();
        long actualSecondX = polygonRepository.getStationById(3).getStationX();
        long actualSecondY = polygonRepository.getStationById(3).getStationY();
        long actualSecondZ = polygonRepository.getStationById(3).getStationZ();
        long actualThirdX = polygonRepository.getStationById(4).getStationX();
        long actualThirdY = polygonRepository.getStationById(4).getStationY();
        long actualThirdZ = polygonRepository.getStationById(4).getStationZ();

        assertEquals(expectBindType, actualBindType);
        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectFx, actualFx);
        assertEquals(expectFy, actualFy);
        assertEquals(expectAbsolute, actualAbsolute, 0.0000007);
        assertEquals(expectRelative, actualRelative);
        assertEquals(expectFirstX, actualFirstX);
        assertEquals(expectFirstY, actualFirstY);
        assertEquals(expectFirstZ, actualFirstZ);
        assertEquals(expectSecondX, actualSecondX);
        assertEquals(expectSecondY, actualSecondY);
        assertEquals(expectSecondZ, actualSecondZ);
        assertEquals(expectThirdX, actualThirdX);
        assertEquals(expectThirdY, actualThirdY);
        assertEquals(expectThirdZ, actualThirdZ);
    }

    @Test
    void adjustPolygonTestTZ() {
        BindType expectBindType = BindType.TZ;
        long expectPerimeter = 190830;
        long expectFirstX = 457057448L;
        long expectFirstY = 2261204245L;
        long expectFirstZ = 17434L;
        long expectSecondX = 457122409L;
        long expectSecondY = 2261202420L;
        long expectSecondZ = 17260L;
        long expectThirdX = 457129436L;
        long expectThirdY = 2261145700L;
        long expectThirdZ = 16332L;
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestTZ());
        assertTrue(success);
        polygonCalculator.adjustPolygon();

        BindType actualBindType = residuals.getBindType();
        long actualPerimeter = residuals.getPerimeter();
        long actualFirstX = polygonRepository.getStationById(2).getStationX();
        long actualFirstY = polygonRepository.getStationById(2).getStationY();
        long actualFirstZ = polygonRepository.getStationById(2).getStationZ();
        long actualSecondX = polygonRepository.getStationById(3).getStationX();
        long actualSecondY = polygonRepository.getStationById(3).getStationY();
        long actualSecondZ = polygonRepository.getStationById(3).getStationZ();
        long actualThirdX = polygonRepository.getStationById(4).getStationX();
        long actualThirdY = polygonRepository.getStationById(4).getStationY();
        long actualThirdZ = polygonRepository.getStationById(4).getStationZ();

        assertEquals(expectBindType, actualBindType);
        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectFirstX, actualFirstX);
        assertEquals(expectFirstY, actualFirstY);
        assertEquals(expectFirstZ, actualFirstZ);
        assertEquals(expectSecondX, actualSecondX);
        assertEquals(expectSecondY, actualSecondY);
        assertEquals(expectSecondZ, actualSecondZ);
        assertEquals(expectThirdX, actualThirdX);
        assertEquals(expectThirdY, actualThirdY);
        assertEquals(expectThirdZ, actualThirdZ);
    }

    @Test
    void adjustPolygonTestOT() {
        BindType expectBindType = BindType.OT;
        long expectPerimeter = 224560;
        long expectFx = -23;
        long expectFy = -39;
        long expectAbsolute = 45;
        String expectRelative = "1:4990";
        long expectFirstX = 457057456L;
        long expectFirstY = 2261204238L;
        long expectFirstZ = 17432L;
        long expectSecondX = 457122424L;
        long expectSecondY = 2261202406L;
        long expectSecondZ = 17257L;
        long expectThirdX = 457129442L;
        long expectThirdY = 2261145694L;
        long expectThirdZ = 16328L;
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestOT());
        assertTrue(success);
        polygonCalculator.adjustPolygon();

        BindType actualBindType = residuals.getBindType();
        long actualPerimeter = residuals.getPerimeter();
        long actualFx = residuals.getLinearX();
        long actualFy = residuals.getLinearY();
        double actualAbsolute = residuals.getAbsolute();
        String actualRelative = residuals.getRelative();
        long actualFirstX = polygonRepository.getStationById(1).getStationX();
        long actualFirstY = polygonRepository.getStationById(1).getStationY();
        long actualFirstZ = polygonRepository.getStationById(1).getStationZ();
        long actualSecondX = polygonRepository.getStationById(2).getStationX();
        long actualSecondY = polygonRepository.getStationById(2).getStationY();
        long actualSecondZ = polygonRepository.getStationById(2).getStationZ();
        long actualThirdX = polygonRepository.getStationById(3).getStationX();
        long actualThirdY = polygonRepository.getStationById(3).getStationY();
        long actualThirdZ = polygonRepository.getStationById(3).getStationZ();

        assertEquals(expectBindType, actualBindType);
        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectFx, actualFx);
        assertEquals(expectFy, actualFy);
        assertEquals(expectAbsolute, actualAbsolute, 0.0000007);
        assertEquals(expectRelative, actualRelative);
        assertEquals(expectFirstX, actualFirstX);
        assertEquals(expectFirstY, actualFirstY);
        assertEquals(expectFirstZ, actualFirstZ);
        assertEquals(expectSecondX, actualSecondX);
        assertEquals(expectSecondY, actualSecondY);
        assertEquals(expectSecondZ, actualSecondZ);
        assertEquals(expectThirdX, actualThirdX);
        assertEquals(expectThirdY, actualThirdY);
        assertEquals(expectThirdZ, actualThirdZ);
    }

    @Test
    void adjustPolygonTestZT() {
        BindType expectBindType = BindType.ZT;
        long expectPerimeter = 155871;
        long expectFirstX = 457057472L;
        long expectFirstY = 2261204265L;
        long expectFirstZ = 17429L;
        long expectSecondX = 457122433L;
        long expectSecondY = 2261202422L;
        long expectSecondZ = 17255L;
        long expectThirdX = 457129445L;
        long expectThirdY = 2261145700L;
        long expectThirdZ = 16327L;
        boolean success = polygonMapper.polygonRequestToPolygon(getTestPolygonRequestZT());
        assertTrue(success);
        polygonCalculator.adjustPolygon();

        BindType actualBindType = residuals.getBindType();
        long actualPerimeter = residuals.getPerimeter();
        long actualFirstX = polygonRepository.getStationById(0).getStationX();
        long actualFirstY = polygonRepository.getStationById(0).getStationY();
        long actualFirstZ = polygonRepository.getStationById(0).getStationZ();
        long actualSecondX = polygonRepository.getStationById(1).getStationX();
        long actualSecondY = polygonRepository.getStationById(1).getStationY();
        long actualSecondZ = polygonRepository.getStationById(1).getStationZ();
        long actualThirdX = polygonRepository.getStationById(2).getStationX();
        long actualThirdY = polygonRepository.getStationById(2).getStationY();
        long actualThirdZ = polygonRepository.getStationById(2).getStationZ();

        assertEquals(expectBindType, actualBindType);
        assertEquals(expectPerimeter, actualPerimeter);
        assertEquals(expectFirstX, actualFirstX);
        assertEquals(expectFirstY, actualFirstY);
        assertEquals(expectFirstZ, actualFirstZ);
        assertEquals(expectSecondX, actualSecondX);
        assertEquals(expectSecondY, actualSecondY);
        assertEquals(expectSecondZ, actualSecondZ);
        assertEquals(expectThirdX, actualThirdX);
        assertEquals(expectThirdY, actualThirdY);
        assertEquals(expectThirdZ, actualThirdZ);
    }


    /**
     * Loads to model test polygon with bindType TT
     * and sets base direction angle
     * @return boolean result of loading
     */
    private boolean loadTestPolygonTT() {
        List<String> polygonRequest = getTestPolygonRequestTT();
        boolean success = polygonMapper.polygonRequestToPolygon(polygonRequest);

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
        return success;
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
        polygonRequest.add("1007 127.0659 68.689 -0.286 456988.790 2261202.196 17.720");
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
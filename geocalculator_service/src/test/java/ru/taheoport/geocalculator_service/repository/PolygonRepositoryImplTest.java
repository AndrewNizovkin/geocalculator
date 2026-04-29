package ru.taheoport.geocalculator_service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.model.PolygonStation;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PolygonRepositoryImpl.class)
class PolygonRepositoryImplTest {

    @Autowired
    private PolygonRepository polygonRepository;

    @BeforeEach
    void clearRepository() {
        polygonRepository.clearAll();
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "5",
            "10"
    })
    void sizeTest(int expectSize) {

        for (int i = 0; i < expectSize; i++) {
            polygonRepository.addNewStation();
        }

        int actualSize = polygonRepository.size();

        assertEquals(expectSize, actualSize);
    }

    @ParameterizedTest
    @CsvSource({
            "1007, 457619, 68689, -286, 456988790, 2261202196, 17715, true",
            "1008, 0, 0, 0, 0, 0, 0, false",
            "noname, 0, 0, 0, 0, 0, 0, false",
            "1004, 0, 0, 0, 457131018, 2261065036, 14661, true"
    })
    void addNewStationTest(
            String expectStationName,
            long expectHorAngle,
            long expectHorDistance,
            long expectElevation,
            long expectX,
            long expectY,
            long expectZ,
            boolean expectStatus
    ) {
        PolygonStation polygonStation = polygonRepository.addNewStation();
        polygonStation.setStationName(expectStationName);
        polygonStation.setHorAngle(expectHorAngle);
        polygonStation.setHorDistance(expectHorDistance);
        polygonStation.setElevation(expectElevation);
        polygonStation.setStationX(expectX);
        polygonStation.setStationY(expectY);
        polygonStation.setStationZ(expectZ);
        polygonStation.setStatus(expectStatus);

        PolygonStation actualPolygonStation = polygonRepository.getStationById(polygonRepository.size() - 1);

        assertNotNull(actualPolygonStation);

        assertEquals(expectStationName, actualPolygonStation.getStationName());
        assertEquals(expectHorAngle, actualPolygonStation.getHorAngle());
        assertEquals(expectHorDistance, actualPolygonStation.getHorDistance());
        assertEquals(expectElevation, actualPolygonStation.getElevation());
        assertEquals(expectX, actualPolygonStation.getStationX());
        assertEquals(expectY, actualPolygonStation.getStationY());
        assertEquals(expectZ, actualPolygonStation.getStationZ());
        assertEquals(expectStatus, actualPolygonStation.isStatus());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "3, 0",
            "3, 3"
    })
    void getStationByIdValidID(
            int before,
            int after
    ) {
        for (int i = 0; i < before; i++) {
            polygonRepository.addNewStation();
        }
        PolygonStation expectStation = polygonRepository.addNewStation();
        expectStation.setStationName("expectStation");
        expectStation.setHorAngle(345);
        expectStation.setHorDistance(100000);
        expectStation.setElevation(456);
        expectStation.setStationX(33334455);
        expectStation.setStationY(2323232);
        expectStation.setStationZ(3333333);
        expectStation.setStatus(true);
        expectStation.setCorrectionHorAngle(23.2323444443);
        expectStation.setDirectionAngle(3433);
        expectStation.setDeltaX(33333);
        expectStation.setDeltaY(-556);
        expectStation.setElevationCorrected(234565);
        expectStation.setCorrectionX(23);
        expectStation.setCorrectionY(-5);
        expectStation.setCorrectionZ(2.2323232323);
        int actualIndex = polygonRepository.size() - 1;
        for (int i = 0; i < after; i++) {
            polygonRepository.addNewStation();
        }

        PolygonStation actualStation = polygonRepository.getStationById(actualIndex);

        assertEquals(expectStation.getStationName(), actualStation.getStationName());
        assertEquals(expectStation.getHorAngle(), actualStation.getHorAngle());
        assertEquals(expectStation.getHorDistance(), actualStation.getHorDistance());
        assertEquals(expectStation.getElevation(), actualStation.getElevation());
        assertEquals(expectStation.getStationX(), actualStation.getStationX());
        assertEquals(expectStation.getStationY(), actualStation.getStationY());
        assertEquals(expectStation.getStationZ(), actualStation.getStationZ());
        assertEquals(expectStation.isStatus(), actualStation.isStatus());
        assertEquals(expectStation.getCorrectionHorAngle(), actualStation.getCorrectionHorAngle());
        assertEquals(expectStation.getDirectionAngle(), actualStation.getDirectionAngle());
        assertEquals(expectStation.getDeltaX(), actualStation.getDeltaX());
        assertEquals(expectStation.getDeltaY(), actualStation.getDeltaY());
        assertEquals(expectStation.getElevationCorrected(), actualStation.getElevationCorrected());
        assertEquals(expectStation.getCorrectionX(), actualStation.getCorrectionX());
        assertEquals(expectStation.getCorrectionY(), actualStation.getCorrectionY());
        assertEquals(expectStation.getCorrectionZ(), actualStation.getCorrectionZ());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 3",
            "3, 4",
            "3, -1"
    })
    void getStationByIdInvalidID(
            int before,
            int badIndex
    ) {
        for (int i = 0; i < before; i++) {
            polygonRepository.addNewStation();
        }

        PolygonStation actualStation = polygonRepository.getStationById(badIndex);

        assertNull(actualStation);
    }

}
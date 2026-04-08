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

}
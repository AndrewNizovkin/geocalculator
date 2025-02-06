package ru.taheoport.geocalculator_service.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.DirectStringRequest;

@SpringBootTest(classes = {
        DirectValidatorDefault.class,
        ValidatorDefault.class
})
class DirectValidatorDefaultTest {

    @Autowired
    private DirectValidator directValidator;

    @ParameterizedTest
    @CsvSource({
            "0.000, 0.000, 0.0000, 0.000, 0.000, 0.000, 0.000, 0.0000, 0.000, 0.0000, 0.000",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.0646, 1.600"
    })
    void isValidDirectStringRequestTrueTest(
            String landmarkX,
            String landmarkY,
            String landmarkDirection,
            String baseX,
            String baseY,
            String baseZ,
            String baseHeight,
            String targetDirection,
            String targetInclinedDistance,
            String targetTiltAngle,
            String targetHeight
    ) {
        DirectStringRequest directStringRequest = new DirectStringRequest();
        directStringRequest.setLandmarkX(landmarkX);
        directStringRequest.setLandmarkY(landmarkY);
        directStringRequest.setLandmarkDirection(landmarkDirection);
        directStringRequest.setBaseX(baseX);
        directStringRequest.setBaseY(baseY);
        directStringRequest.setBaseZ(baseZ);
        directStringRequest.setBaseHeight(baseHeight);
        directStringRequest.setTargetDirection(targetDirection);
        directStringRequest.setTargetInclinedDistance(targetInclinedDistance);
        directStringRequest.setTargetTiltAngle(targetTiltAngle);
        directStringRequest.setTargetHeight(targetHeight);

        boolean actualResult = directValidator.isValidDirectStringRequest(directStringRequest);

        Assertions.assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "'0,000', 0.000, 0.0000, 0.000, 0.000, 0.000, 0.000, 0.0000, 0.000, 0.0000, 0.000",
            "478685.352, 22969З8.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.02100, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, #478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, '2 296967.264', 11.220, 1.538, 185.4548, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.22O, 1.538, 185.4548, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538/, 185.4548, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.45.48, 39.878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39-878, 0.0646, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.046, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.0466, 1.600",
            "478685.352, 2296938.168, 0.0210, 478676.113, 2296967.264, 11.220, 1.538, 185.4548, 39.878, 0.0436, 1.6OO"
    })
    void isValidDirectStringRequestFalseTest(
            String landmarkX,
            String landmarkY,
            String landmarkDirection,
            String baseX,
            String baseY,
            String baseZ,
            String baseHeight,
            String targetDirection,
            String targetInclinedDistance,
            String targetTiltAngle,
            String targetHeight
    ) {
        DirectStringRequest directStringRequest = new DirectStringRequest();
        directStringRequest.setLandmarkX(landmarkX);
        directStringRequest.setLandmarkY(landmarkY);
        directStringRequest.setLandmarkDirection(landmarkDirection);
        directStringRequest.setBaseX(baseX);
        directStringRequest.setBaseY(baseY);
        directStringRequest.setBaseZ(baseZ);
        directStringRequest.setBaseHeight(baseHeight);
        directStringRequest.setTargetDirection(targetDirection);
        directStringRequest.setTargetInclinedDistance(targetInclinedDistance);
        directStringRequest.setTargetTiltAngle(targetTiltAngle);
        directStringRequest.setTargetHeight(targetHeight);

        boolean actualResult = directValidator.isValidDirectStringRequest(directStringRequest);

        Assertions.assertFalse(actualResult);

    }
}
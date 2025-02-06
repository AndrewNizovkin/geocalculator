package ru.taheoport.geocalculator_service.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.InverseStringRequest;

@SpringBootTest(classes = {
        ValidatorDefault.class,
        InverseValidatorDefault.class})
class InverseValidatorDefaultTest {

    @Autowired
    private InverseValidator inverseValidator;

    @ParameterizedTest
    @CsvSource({
            "0.000, 0.000, 0.000, 0.000, 0.000, 0.000",
            "-100000.000, 544646540.000, -1250.999, -56960.123, 85460.000, 0.000"
    })
    void isValidInverseStringRequestTrueTest(
            String baseX,
            String baseY,
            String baseZ,
            String targetX,
            String targetY,
            String targetZ
    ) {
        InverseStringRequest inverseStringRequest = new InverseStringRequest();
        inverseStringRequest.setBaseX(baseX);
        inverseStringRequest.setBaseY(baseY);
        inverseStringRequest.setBaseZ(baseZ);
        inverseStringRequest.setTargetX(targetX);
        inverseStringRequest.setTargetY(targetY);
        inverseStringRequest.setTargetZ(targetZ);

        boolean actualResult = inverseValidator.isValidInverseStringRequest(inverseStringRequest);

        Assertions.assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "'0,000', 0.000, 0.000, 0.000, 0.000, 0.000",
            "-100000.000, 544646540.0O0, -1250.999, -56960.123, 85460.000, 0.000",
            "-100000.000, 544646540.000, -12.50.999, -56960.123, 85460.000, 0.000",
            "-100000.000, 544646540.000, -1250.999, -56960.12З, 85460.000, 0.000",
            "-100000.000, 544646540.000, -1250.999, -56960.123, #85460.000, 0.000",
            "-100000.000, 544646540.000, -1250.999, -56960.123, 85460.000, '0.0 00'"
    })
    void isValidInverseStringRequestFalseTest(
            String baseX,
            String baseY,
            String baseZ,
            String targetX,
            String targetY,
            String targetZ
    ) {
        InverseStringRequest inverseStringRequest = new InverseStringRequest();
        inverseStringRequest.setBaseX(baseX);
        inverseStringRequest.setBaseY(baseY);
        inverseStringRequest.setBaseZ(baseZ);
        inverseStringRequest.setTargetX(targetX);
        inverseStringRequest.setTargetY(targetY);
        inverseStringRequest.setTargetZ(targetZ);

        boolean actualResult = inverseValidator.isValidInverseStringRequest(inverseStringRequest);

        Assertions.assertFalse(actualResult);
    }
}
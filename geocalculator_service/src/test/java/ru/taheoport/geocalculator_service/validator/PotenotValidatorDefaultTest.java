package ru.taheoport.geocalculator_service.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taheoport.geocalculator_service.dto.PotenotStringRequest;

import java.util.List;

@SpringBootTest(classes = {
        PotenotValidatorDefault.class,
        ValidatorDefault.class
})
class PotenotValidatorDefaultTest {

    @Autowired
    PotenotValidator potenotValidator;

    @ParameterizedTest
    @CsvSource({
            "100.000, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425",
            "100.000, 100.000, 331.5847, 200.000, 200.000, 17.1908, 100.000, 300.000, 62.2946",
            "100.000, 100.000, 317.1807, 200.000, 200.000, 324.3448, 100.000, 300.000, 325.5742",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, -100.000, -300.000, 206.2045"
    })
    void isValidPotenotStringRequestTrueTest(
            String firstX,
            String firstY,
            String firstDirection,
            String secondX,
            String secondY,
            String secondDirection,
            String thirdX,
            String thirdY,
            String thirdDirection
    ) {
        List<PotenotStringRequest> potenotStringRequestList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection)
        );

        boolean actualResult = potenotValidator.isValidPotenotStringRequest(potenotStringRequestList);

        Assertions.assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "100.O00, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425",
            "100.000, '100,000', 331.5847, 200.000, 200.000, 17.1908, 100.000, 300.000, 62.2946",
            "100.000, 100.000, 317.1897, 200.000, 200.000, 324.3448, 100.000, 300.000, 325.5742",
            "-100.000, -100.000, 163.0559, -200..000, -200.000, 183.5442, -100.000, -300.000, 206.2045",
            "-100.000, -100.000, 163.0559, -200.000, -#200.000, 183.5442, -100.000, -300.000, 206.2045",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.442, -100.000, -300.000, 206.2045",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, --100.000, -300.000, 206.2045",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, -100.000, '-300 000', 206.2045",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, -100.000, -300.000, 206.20452"
    })
    void isValidPotenotStringRequestNoValidDataTest(
            String firstX,
            String firstY,
            String firstDirection,
            String secondX,
            String secondY,
            String secondDirection,
            String thirdX,
            String thirdY,
            String thirdDirection
    ) {
        List<PotenotStringRequest> potenotStringRequestList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection)
        );

        boolean actualResult = potenotValidator.isValidPotenotStringRequest(potenotStringRequestList);

        Assertions.assertFalse(actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "100.000, 100.000, 251.1405, 200.000, 200.000, 351.3834, 100.000, 300.000, 112.1425",
            "-100.000, -100.000, 163.0559, -200.000, -200.000, 183.5442, -100.000, -300.000, 206.2045"
    })
    void isValidPotenotStringRequestNoValidListSizeTest(
            String firstX,
            String firstY,
            String firstDirection,
            String secondX,
            String secondY,
            String secondDirection,
            String thirdX,
            String thirdY,
            String thirdDirection
    ) {
        List<PotenotStringRequest> shortList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection)
        );

        List<PotenotStringRequest> longList = List.of(
                new PotenotStringRequest(firstX, firstY, firstDirection),
                new PotenotStringRequest(secondX, secondY, secondDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection),
                new PotenotStringRequest(thirdX, thirdY, thirdDirection)
        );


        boolean actualResultShortList = potenotValidator.isValidPotenotStringRequest(shortList);
        boolean actualResultLongList = potenotValidator.isValidPotenotStringRequest(longList);

        Assertions.assertFalse(actualResultShortList);
        Assertions.assertFalse(actualResultLongList);
    }

}
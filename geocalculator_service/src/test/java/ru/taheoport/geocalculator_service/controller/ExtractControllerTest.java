package ru.taheoport.geocalculator_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ExtractControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getPolygonReportsTest() {
        List<String> expectResponse = getExpectExtractResponse();
        List<String> extractRequest = getTestExtractRequest();

        String responseBody = webTestClient.post()
                .uri("extract")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(extractRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        for (String expectLine : expectResponse) {
            assertTrue(responseBody.contains(expectLine));
        }


    }

    /**
     * Gets test extract request
     * @return list of strings in tah-format
     */
    private List<String> getTestExtractRequest() {
        List<String> extractRequest = new ArrayList<>();
        extractRequest.add("101 478650.714 2297071.740 10.930 1.550 100 478660.283 2297003.862");
        extractRequest.add("102 478675.411 2297114.212 10.733 1.507 101 478650.714 2297071.740");
        extractRequest.add("103 478702.294 2297134.754 10.789 1.503 102 478675.411 2297114.212");
        extractRequest.add("104 478731.901 2297154.110 10.722 1.522 103 478702.294 2297134.754");
        extractRequest.add("105 478799.005 2297187.623 10.668 1.484 104 478731.901 2297154.110");
        extractRequest.add("//");
        extractRequest.add("T100 68.557 359.5954 0.1741 1.600 0");
        extractRequest.add("T102 49.147 141.4810 -0.0948 1.600 0");
        extractRequest.add("23 46.708 142.2019 -0.0742 1.600 0");
        extractRequest.add("//");
        extractRequest.add("T101 49.133 359.5957 0.2042 1.600 1");
        extractRequest.add("T103 33.840 157.3344 0.1548 1.600 1");
        extractRequest.add("//");
        extractRequest.add("T102 33.836 0.0007 0.0435 1.600 2");
        extractRequest.add("T104 35.381 175.4634 0.0330 1.600 2");
        extractRequest.add("//");
        extractRequest.add("T103 35.374 359.5956 0.1433 1.600 3");
        extractRequest.add("T105 75.012 173.2137 0.0130 1.600 3");
        extractRequest.add("//");
        extractRequest.add("T104 75.020 359.5955 0.0753 1.600 4");
        extractRequest.add("T106 53.068 187.1505 0.0734 1.600 4");
        extractRequest.add("//");

        return extractRequest;
    }

    /**
     * Gets expect extractResponse
     * @return list of string
     */
    private List<String> getExpectExtractResponse() {
        List<String> extractResponse = new ArrayList<>();

        extractResponse.add("#extract-pol");
        extractResponse.add("T100 0.0000 68.556 -0.303 Not Not Not");
        extractResponse.add("101 141.4816 49.140 -0.197 Not Not Not");
        extractResponse.add("102 157.3347 33.838 0.058 Not Not Not");
        extractResponse.add("103 175.4627 35.378 -0.067 Not Not Not");
        extractResponse.add("104 173.2141 75.016 -0.051 Not Not Not");
        extractResponse.add("105 187.1510 53.068 0.001 Not Not Not");
        extractResponse.add("T106 0.0000 0.000 0.000 Not Not Not");
        extractResponse.add("#extract-report");
        extractResponse.add("");
        extractResponse.add("              ВЕДОМОСТЬ ВЫЧИСЛЕНИЯ СРЕДНИХ ГОРИЗОНТАЛЬНЫХ ПРОЛОЖЕНИЙ И ПРЕВЫШЕНИЙ.");
        extractResponse.add("--------------------------------------------------------------------------------------------------");
        extractResponse.add("|Наименование|       Гор. проложение, м.      |        |          Превышение, м.        |        |");
        extractResponse.add("|   точки    |--------------------------------| DL,мм. |--------------------------------| Dh,мм. |");
        extractResponse.add("|  стояния   |  вперёд  |  назад   |  среднее |        |  вперёд  |   назад  |  среднее |        |");
        extractResponse.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");
        extractResponse.add("|      1     |     2    |     3    |     4    |   5    |     6    |     7    |     8    |    9   |");
        extractResponse.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");
        extractResponse.add("|       T100 |          |          |          |        |          |          |          |        |");
        extractResponse.add("|            |          |   68.556 |   68.556 |        |          |    0.303 |   -0.303 |        |");
        extractResponse.add("|        101 |          |          |          |        |          |          |          |        |");
        extractResponse.add("|            |   49.147 |   49.132 |   49.140 |     15 |   -0.190 |    0.203 |   -0.197 |     13 |");
        extractResponse.add("|        102 |          |          |          |        |          |          |          |        |");
        extractResponse.add("|            |   33.840 |   33.836 |   33.838 |      4 |    0.063 |   -0.052 |    0.058 |     11 |");
        extractResponse.add("|        103 |          |          |          |        |          |          |          |        |");
        extractResponse.add("|            |   35.381 |   35.374 |   35.378 |      7 |   -0.061 |    0.072 |   -0.067 |     11 |");
        extractResponse.add("|        104 |          |          |          |        |          |          |          |        |");
        extractResponse.add("|            |   75.012 |   75.020 |   75.016 |     -8 |   -0.045 |    0.056 |   -0.051 |     11 |");
        extractResponse.add("|        105 |          |          |          |        |          |          |          |        |");
        extractResponse.add("|            |   53.068 |          |   53.068 |        |    0.001 |          |    0.001 |        |");
        extractResponse.add("|       T106 |          |          |          |        |          |          |          |        |");
        extractResponse.add("--------------------------------------------------------------------------------------------------");

        return extractResponse;
    }

}
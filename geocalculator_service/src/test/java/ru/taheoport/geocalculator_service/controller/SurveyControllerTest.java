package ru.taheoport.geocalculator_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.taheoport.geocalculator_service.dto.PotenotTaskRequest;
import ru.taheoport.geocalculator_service.dto.PotenotTaskResponse;
import ru.taheoport.geocalculator_service.service.SurveyService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class SurveyControllerTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void importFromTotalStationLeicaTest() {

        List<String> importRequest = getImportLeicaTestList();
        List<String> expectTah = getExpectLeicaTahList();
        int expectSize = expectTah.size();

        List<String> responseBody = webTestClient.post()
                .uri("survey/import")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(importRequest), List.class)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        int actualSize = responseBody.size();
//        assertEquals(expectSize, actualSize);

        String expectLine = expectTah.removeFirst();



//        PotenotTaskResponse responseBody = webTestClient.post()
//                .uri("potenot")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(potenotTaskRequestList), PotenotTaskRequest.class)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(PotenotTaskResponse.class)
//                .returnResult()
//                .getResponseBody();


    }

    @Test
    void getSurveyReports() {
    }

    /**
     * Creates test list for import from total station Leica
     * @return List strings in gsi16 format
     */
    private List<String> getImportLeicaTestList() {
        List<String> importLeicaList = new ArrayList<>();
        importLeicaList.add("import-leica");
        importLeicaList.add("410001+00009901 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110002+00000001 21.324+35959550 22.324+09002380 31...0+00152081 51....+0000+000 87...0+00001600 88...0+00001531 ");
        importLeicaList.add("410003+000T100  42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110004+00000002 21.324+06137090 22.324+08953090 31...0+00038187 51....+0000+000 87...0+00001600 88...0+00001531 ");
        importLeicaList.add("410005+0009904K 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110006+00000003 21.324+15723000 22.324+09051020 31...0+00033938 51....+0000+000 87...0+00001600 88...0+00001531 ");
        importLeicaList.add("410499+00009902 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110500+00000326 21.324+35959550 22.324+08954590 31...0+00038187 51....+0000+000 87...0+00001600 88...0+00001536 ");
        importLeicaList.add("410501+00000330 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110502+00000327 21.324+32825350 22.324+08850230 31...0+00003511 51....+0000+000 87...0+00001600 88...0+00001536 ");
        importLeicaList.add("410503+00000055 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110504+00000328 21.324+29051110 22.324+08917280 31...0+00004374 51....+0000+000 87...0+00001600 88...0+00001536 ");
        importLeicaList.add("410505+00000055 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110539+00000347 21.324+00000010 22.324+08952260 31...0+00055591 51....+0000+000 87...0+00001600 88...0+00001537 ");
        importLeicaList.add("410540+0000T102 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110541+00000348 21.324+14133000 22.324+09010350 31...0+00033479 51....+0000+000 87...0+00001600 88...0+00001537 ");
        importLeicaList.add("410542+00000040 42....+00000000 43....+00000000 44....+00000000 45....+00000000 46....+00000000 47....+00000000 48....+00000000 49....+00000000 ");
        importLeicaList.add("110543+00000349 21.324+12537430 22.324+09006440 31...0+00026790 51....+0000+000 87...0+00001600 88...0+00001537 ");

        return importLeicaList;
    }

    /**
     * Creates expect list for testing import from Leica
     * @return list in tah-format
     */
    private List<String> getExpectLeicaTahList() {
        List<String> expectLeicaTahList = new ArrayList<>();
        expectLeicaTahList.add("noname 0.000 0.000 0.000 1.531 noname 0.000 0.000");
        expectLeicaTahList.add("noname 0.000 0.000 0.000 1.536 noname 0.000 0.000");
        expectLeicaTahList.add("noname 0.000 0.000 0.000 1.537 noname 0.000 0.000");
        expectLeicaTahList.add("//");
        expectLeicaTahList.add("9901 152.081 359.5955 -0.0238 1.600 0");
        expectLeicaTahList.add("T100 38.187 61.3709 0.0651 1.600 0");
        expectLeicaTahList.add("9904K 33.938 157.2300 -0.5102 1.600 0");
        expectLeicaTahList.add("//");
        expectLeicaTahList.add("9902 38.187 359.5955 0.0501 1.600 1");
        expectLeicaTahList.add("330 3.511 328.2535 1.0937 1.600 1");
        expectLeicaTahList.add("55 4.374 290.5111 0.4232 1.600 1");
        expectLeicaTahList.add("//");
        expectLeicaTahList.add("55 55.591 0.0001 0.0734 1.600 2");
        expectLeicaTahList.add("T102 33.479 141.3300 -0.1035 1.600 2");
        expectLeicaTahList.add("40 26.790 125.3743 -0.0644 1.600 2");
        expectLeicaTahList.add("//");

        return expectLeicaTahList;
    }


}
package ru.taheoport.geocalculator_service.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
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

        String responseBody = webTestClient.post()
                .uri("survey/import")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(importRequest), List.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);

        for (String expectLine : expectTah) {
            assertTrue(responseBody.contains(expectLine));
        }


    }

    @Test
    void getSurveyReportsTest() {
        List<String> surveyReportsRequest = getSurveyRequestTest();
        List<String> expectSurveyReports = getExpectSurveyReports();

        String responseBody = webTestClient.post()
                .uri("survey/report")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(surveyReportsRequest), List.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);

        for (String expectLine : expectSurveyReports) {
            assertTrue(responseBody.contains(expectLine));
        }
    }

    @Test
    void getSurveyReportsTestBadRequest() {
        List<String> badRequest = List.of("asdfas", "sdfasdf");
        List<String> expectResponse = List.of("#error", "Bad request!");

        String responseBody = webTestClient.post()
                .uri("survey/report")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(badRequest)
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

    /**
     * Creates test surveyRequest
     * @return list strings in surveyRequest format
     */
    private List<String> getSurveyRequestTest() {
        List<String> surveyRequestTest = new ArrayList<>();
        surveyRequestTest.add("1301 478676.113 2296967.264 11.220 1.538 0.0000 1302 478685.352 2296938.168");
        surveyRequestTest.add("100 478660.283 2297003.862 11.231 1.580 0.0000 1301 478676.113 2296967.264");
        surveyRequestTest.add("101 478650.714 2297071.740 10.930 1.550 163.5600 100 478660.283 2297003.862");
        surveyRequestTest.add("//");
        surveyRequestTest.add("1302 30.526 359.5953 0.3009 1.600 0");
        surveyRequestTest.add("T100 39.878 185.4548 0.0646 1.600 0");
        surveyRequestTest.add("//");
        surveyRequestTest.add("74 45.491 173.0346 -0.0709 1.600 1");
        surveyRequestTest.add("//");
        surveyRequestTest.add("999 43.599 163.5600 8.3641 0.000 2");
        surveyRequestTest.add("12 43.594 164.0516 8.3222 2000.000 2");
        surveyRequestTest.add("40 30.114 146.1846 2.2124 2000.000 2");
        surveyRequestTest.add("//");

        return surveyRequestTest;
    }

    /**
     * Creates expect survey reports
     * @return list of strings in survey reports format
     */
    private List<String> getExpectSurveyReports() {
        List<String> expectSurveyReports = new ArrayList<>();
        expectSurveyReports.add("#survey-report");
        expectSurveyReports.add("//");
        expectSurveyReports.add("1301 478676.113 2296967.264 11.220");
        expectSurveyReports.add("1302 478685.352 2296938.168 0.000");
        expectSurveyReports.add("1302 478685.350 2296938.170 11.426");
        expectSurveyReports.add("T100 478660.289 2297003.868 11.236");
        expectSurveyReports.add("//");
        expectSurveyReports.add("100 478660.283 2297003.862 11.231");
        expectSurveyReports.add("1301 478676.113 2296967.264 0.000");
        expectSurveyReports.add("74 478647.399 2297047.490 11.116");
        expectSurveyReports.add("//");
        expectSurveyReports.add("101 478650.714 2297071.740 10.930");
        expectSurveyReports.add("100 478660.283 2297003.862 0.000");
        expectSurveyReports.add("999 478656.732 2297029.055 19.008");
        expectSurveyReports.add("12 478656.847 2297029.068 -1981.047");
        expectSurveyReports.add("40 478645.698 2297042.072 -1986.282");

        expectSurveyReports.add("#processing-report");
        expectSurveyReports.add("                           В  Е  Д  О  М  О  С  Т  Ь    В  Ы  Ч  И  С  Л  Е  Н  И  Я    К  О  О  Р  Д  И  Н  А  Т");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("|  Название   |  Длина  | Направ-  |   Угол   |  Высота  |   Дир.   |        П р и р а щ е н и я      |            К о о р д и н а т ы         |");
        expectSurveyReports.add("|   точки     |  линии  |  ление   |  наклона | наведения|   угол   |---------------------------------|----------------------------------------|");
        expectSurveyReports.add("|             |    м.   |  г.мс    |    г.мс  |     м.   |   г.мс   |   DX, м. |   DY, м. |   DZ, м.  |     X, м.   |    Y, м.    |     Z, м.  |");
        expectSurveyReports.add("|-------------|---------|----------|----------|----------|----------|----------|----------|-----------|--------- ---|-------------|------------|");
        expectSurveyReports.add("|      1      |     2   |     3    |     4    |     5    |     6    |     7    |     8    |      9    |     10      |      11     |      12    |");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("                                   Станция 1301       Ориентир 1302       Высота инструмента i = 1.538");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("|       1301 |          |          |          |          |          |          |          |           |  478676.113 | 2296967.264 |     11.220 |");
        expectSurveyReports.add("|       1302 |          |          |          |          |          |          |          |           |  478685.352 | 2296938.168 |            |");
        expectSurveyReports.add("|       1302 |   30.526 | 359.5953 |   0.3009 |    1.600 | 287.3652 |    9.237 |  -29.094 |     0.206 |  478685.350 | 2296938.170 |     11.426 |");
        expectSurveyReports.add("|       T100 |   39.878 | 185.4548 |   0.0646 |    1.600 | 113.2247 |  -15.824 |   36.604 |     0.016 |  478660.289 | 2297003.868 |     11.236 |");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("                                   Станция 100        Ориентир 1301       Высота инструмента i = 1.580");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("|        100 |          |          |          |          |          |          |          |           |  478660.283 | 2297003.862 |     11.231 |");
        expectSurveyReports.add("|       1301 |          |          |          |          |          |          |          |           |  478676.113 | 2296967.264 |            |");
        expectSurveyReports.add("|         74 |   45.491 | 173.0346 |  -0.0709 |    1.600 | 106.2711 |  -12.884 |   43.628 |    -0.115 |  478647.399 | 2297047.490 |     11.116 |");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("                                   Станция 101        Ориентир 100        Высота инструмента i = 1.550");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");
        expectSurveyReports.add("|        101 |          |          |          |          |          |          |          |           |  478650.714 | 2297071.740 |     10.930 |");
        expectSurveyReports.add("|        100 |          |          |          |          |          |          |          |           |  478660.283 | 2297003.862 |            |");
        expectSurveyReports.add("|        999 |   43.599 | 163.5600 |   8.3641 |    0.000 | 278.0128 |    6.018 |  -42.685 |     8.078 |  478656.732 | 2297029.055 |     19.008 |");
        expectSurveyReports.add("|         12 |   43.594 | 164.0516 |   8.3222 | 2000.000 | 278.1044 |    6.133 |  -42.672 | -1991.977 |  478656.847 | 2297029.068 |  -1981.047 |");
        expectSurveyReports.add("|         40 |   30.114 | 146.1846 |   2.2124 | 2000.000 | 260.2414 |   -5.016 |  -29.668 | -1997.212 |  478645.698 | 2297042.072 |  -1986.282 |");
        expectSurveyReports.add("------------------------------------------------------------------------------------------------------------------------------------------------");

        return expectSurveyReports;
    }

}
import { SurveyRepository } from "../../js/repository/SurveyRepository.js";
import { SurveyService } from "../../js/service/SurveyService.js";

let content = document.getElementById("content");
content.innerHTML = 'Hello, I am test-survey.js';

let surveyRepository = new SurveyRepository();

let surveyService = new SurveyService();

// createDemoSurveyReposiory();

// showSurveyRepository("Демо-репозиторий начальные значения");

// surveyRepository.clearAll();

// showSurveyRepository("Очищаем репозиторий");

createDemoSurveyService();

showSurveyService("Начальное содержание демо-съёмки")
 
surveyService.removeMeasurement(3, 3);

showSurveyService("Удаляем измерение")

/**
 * Создаёт тестовый репозиторий
 */
function createDemoSurveyReposiory() {
    // let surveyRepository = new SurveyRepository();
    
    surveyRepository.addNewStation();
    surveyRepository.saveStationName(0, "1301");
    surveyRepository.saveStationX(0, "478676.113");
    surveyRepository.saveStationY(0, "2296967.264");
    surveyRepository.saveStationZ(0, "11.220");
    surveyRepository.saveStationHeight(0, "1.538");
    surveyRepository.saveOrDirection(0, "0.0000");
    surveyRepository.saveOrName(0, "1302");
    surveyRepository.saveOrX(0, "478685.352");
    surveyRepository.saveOrY(0, "2296938.168");

    surveyRepository.addNewMeasurement(0);
    surveyRepository.saveTargetName(0, 0, "1302");
    surveyRepository.saveTargetDirection(0, 0, "359.5953");
    surveyRepository.saveTargetDistance(0, 0, "30.562");
    surveyRepository.saveTargetTiltAngle(0, 0, "0.3009");
    surveyRepository.saveTargetHeight(0, 0, "1.600");

    surveyRepository.addNewMeasurement(0);
    surveyRepository.saveTargetName(0, 1, "T100");
    surveyRepository.saveTargetDirection(0, 1, "185.4548");
    surveyRepository.saveTargetDistance(0, 1, "39.878");
    surveyRepository.saveTargetTiltAngle(0, 1, "0.0646");
    surveyRepository.saveTargetHeight(0, 1, "1.600");

// add station
    surveyRepository.addNewStation();
    surveyRepository.saveStationName(1, "100");
    surveyRepository.saveStationX(1, "478660.283");
    surveyRepository.saveStationY(1, "2297003.862");
    surveyRepository.saveStationZ(1, "11.231");
    surveyRepository.saveStationHeight(1, "1.58");
    surveyRepository.saveOrDirection(1, "0.0100");
    surveyRepository.saveOrName(1, "1301");
    surveyRepository.saveOrX(1, "478676.113");
    surveyRepository.saveOrY(1, "2296967.264");

    surveyRepository.addNewMeasurement(1);
    surveyRepository.saveTargetName(1, 0, "1301");
    surveyRepository.saveTargetDirection(1, 0, "359.5955");
    surveyRepository.saveTargetDistance(1, 0, "39.884");
    surveyRepository.saveTargetTiltAngle(1, 0, "0.0054");
    surveyRepository.saveTargetHeight(1, 0, "1.600");

    surveyRepository.addNewMeasurement(1);
    surveyRepository.saveTargetName(1, 1, "T101");
    surveyRepository.saveTargetDirection(1, 1, "164.3757");
    surveyRepository.saveTargetDistance(1, 1, "68.564");
    surveyRepository.saveTargetTiltAngle(1, 1, "-0.1338");
    surveyRepository.saveTargetHeight(1, 1, "1.600");

    surveyRepository.addNewMeasurement(1);
    surveyRepository.saveTargetName(1, 2, "74");
    surveyRepository.saveTargetDirection(1, 2, "161.2650");
    surveyRepository.saveTargetDistance(1, 2, "43.653");
    surveyRepository.saveTargetTiltAngle(1, 2, "0.0041");
    surveyRepository.saveTargetHeight(1, 2, "1.600");
}

/**
 * Заполняет репозиторий демо значениями методами сервиса
 */
function createDemoSurveyService() {

    surveyService.addNewStation();
    surveyService.saveStationName(0, "1301");
    surveyService.saveStationX(0, "478676.113");
    surveyService.saveStationY(0, "2296967.264");
    surveyService.saveStationZ(0, "11.220");
    surveyService.saveStationHeight(0, "1.538");
    surveyService.saveOrDirection(0, "0.0000");
    surveyService.saveOrName(0, "1302");
    surveyService.saveOrX(0, "478685.352");
    surveyService.saveOrY(0, "2296938.168");

    surveyService.addNewMeasurement(0);
    surveyService.saveTargetName(0, 0, "1302");
    surveyService.saveTargetDirection(0, 0, "359.5953");
    surveyService.saveTargetDistance(0, 0, "30.562");
    surveyService.saveTargetTiltAngle(0, 0, "0.3009");
    surveyService.saveTargetHeight(0, 0, "1.600");

    surveyService.addNewMeasurement(0);
    surveyService.saveTargetName(0, 1, "T100");
    surveyService.saveTargetDirection(0, 1, "185.4548");
    surveyService.saveTargetDistance(0, 1, "39.878");
    surveyService.saveTargetTiltAngle(0, 1, "0.0646");
    surveyService.saveTargetHeight(0, 1, "1.600");

    surveyService.addNewMeasurement(0);
    surveyService.saveTargetName(0, 2, "T101");
    surveyService.saveTargetDirection(0, 2, "164.3757");
    surveyService.saveTargetDistance(0, 2, "68.564");
    surveyService.saveTargetTiltAngle(0, 2, "-0.1338");
    surveyService.saveTargetHeight(0, 2, "1.600");



// add station
    surveyService.addNewStation();
    surveyService.saveStationName(1, "100");
    surveyService.saveStationX(1, "478660.283");
    surveyService.saveStationY(1, "2297003.862");
    surveyService.saveStationZ(1, "11.231");
    surveyService.saveStationHeight(1, "1.58");
    surveyService.saveOrDirection(1, "0.0100");
    surveyService.saveOrName(1, "1301");
    surveyService.saveOrX(1, "478676.113");
    surveyService.saveOrY(1, "2296967.264");

    surveyService.addNewMeasurement(1);
    surveyService.saveTargetName(1, 0, "1301");
    surveyService.saveTargetDirection(1, 0, "359.5955");
    surveyService.saveTargetDistance(1, 0, "39.884");
    surveyService.saveTargetTiltAngle(1, 0, "0.0054");
    surveyService.saveTargetHeight(1, 0, "1.600");

    surveyService.addNewMeasurement(1);
    surveyService.saveTargetName(1, 1, "T101");
    surveyService.saveTargetDirection(1, 1, "164.3757");
    surveyService.saveTargetDistance(1, 1, "68.564");
    surveyService.saveTargetTiltAngle(1, 1, "-0.1338");
    surveyService.saveTargetHeight(1, 1, "1.600");

    surveyService.addNewMeasurement(1);
    surveyService.saveTargetName(1, 2, "74");
    surveyService.saveTargetDirection(1, 2, "161.2650");
    surveyService.saveTargetDistance(1, 2, "43.653");
    surveyService.saveTargetTiltAngle(1, 2, "0.0041");
    surveyService.saveTargetHeight(1, 2, "1.600");

}

/**
 * Выводит на экран содержимое репозитория
 */
function showSurveyRepository(message) {
    // let content = document.getElementById("content");
    let newElem = document.createElement('div');
    newElem.innerHTML = `<br>------${message}-----<br>`
    content.append(newElem);


    newElem = document.createElement('div');
    newElem.innerHTML = `Размер репозитория: ${surveyRepository.size()} станций`;

    content.append(newElem);

    if(surveyRepository.size() > 0) {

        let stations = document.createElement('ol');

        for(let i = 0; i < surveyRepository.size(); i++) {

            let station = document.createElement('li');
            station.innerHTML = `
            name: ${surveyRepository.getStationName(i)}<br>
            X: ${surveyRepository.getStationX(i)}<br>
            Y: ${surveyRepository.getStationY(i)}<br>
            Z: ${surveyRepository.getStationZ(i)}<br>
            i: ${surveyRepository.getStationHeight(i)}<br>
            OrDir: ${surveyRepository.getOrDirection(i)}<br>
            OrName: ${surveyRepository.getOrName(i)}<br>
            OrX: ${surveyRepository.getOrX(i)}<br>
            OrY: ${surveyRepository.getOrY(i)}<br>
            Количество измерений: ${surveyRepository.measurementSize(i)}<br>
            
            `;

            if(surveyRepository.measurementSize(i) > 0) {
                let measurements = document.createElement('ol');

                for(let j = 0; j < surveyRepository.measurementSize(i); j++) {
                    let measurement = document.createElement('li');
                    measurement.innerHTML = `
                    targetName: ${surveyRepository.getTargetName(i, j)}<br>
                    direction : ${surveyRepository.getTargetDirection(i, j)}<br>
                    distance: ${surveyRepository.getTargetDistance(i, j)}<br>
                    tiltAngle: ${surveyRepository.getTargetTiltAngle(i, j)}<br>
                    targetHeight: ${surveyRepository.getTargetHeight(i, j)}<br>
                    `;
                    measurements.append(measurement);
                }
                station.append(measurements);
            }

            stations.append(station);
            
        }

        content.append(stations);


    }
}

/**
 * Выводит на экран содержимое репозитория методами сервиса
 */
function showSurveyService(message) {
    // let content = document.getElementById("content");
    let newElem = document.createElement('div');
    newElem.innerHTML = `<br>------${message}-----<br>`
    content.append(newElem);

    newElem = document.createElement('div');
    newElem.innerHTML = `Количество станций: ${surveyService.size()}`;

    content.append(newElem);

    if(surveyService.size() > 0) {

        let stations = document.createElement('ol');

        for(let i = 0; i < surveyService.size(); i++) {

            let station = document.createElement('li');
            station.innerHTML = `
            name: ${surveyService.getStationName(i)}<br>
            X: ${surveyService.getStationX(i)}<br>
            Y: ${surveyService.getStationY(i)}<br>
            Z: ${surveyService.getStationZ(i)}<br>
            i: ${surveyService.getStationHeight(i)}<br>
            OrDir: ${surveyService.getOrDirection(i)}<br>
            OrName: ${surveyService.getOrName(i)}<br>
            OrX: ${surveyService.getOrX(i)}<br>
            OrY: ${surveyService.getOrY(i)}<br>
            Количество измерений: ${surveyService.measurementSize(i)}<br>
            
            `;

            if(surveyService.measurementSize(i) > 0) {
                let measurements = document.createElement('ol');

                for(let j = 0; j < surveyService.measurementSize(i); j++) {
                    let measurement = document.createElement('li');
                    measurement.innerHTML = `
                    targetName: ${surveyService.getTargetName(i, j)}<br>
                    direction : ${surveyService.getTargetDirection(i, j)}<br>
                    distance: ${surveyService.getTargetDistance(i, j)}<br>
                    tiltAngle: ${surveyService.getTargetTiltAngle(i, j)}<br>
                    targetHeight: ${surveyService.getTargetHeight(i, j)}<br>
                    `;
                    measurements.append(measurement);
                }
                station.append(measurements);
            }

            stations.append(station);
            
        }

        content.append(stations);


    }
}
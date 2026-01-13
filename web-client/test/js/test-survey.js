import { SurveyRepository } from "../../js/repository/SurveyRepository.js";

let content = document.getElementById("content");
content.innerHTML = 'Hello, I am test-survey.js';

let surveyRepository = new SurveyRepository();

createDemoSurveyReposiory();

showSurveyRepository("Демо-репозиторий начальные значения");

surveyRepository.clearAll();

showSurveyRepository("Очищаем репозиторий");

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
import { BasePointRepository} from "../../js/repository/BasePointRepository.js";
import { BasePointService } from "../../js/service/BasePointService.js";

let content = document.getElementById("content");
content.innerHTML = 'Hello, I am test-base-points.js';

//#region  test BasePointRepository
const basePointRepository = new BasePointRepository();
createDemoBasePointRepository();
showBasePointRepository("Начальное состояние демо репозитория");
basePointRepository.sortByName();
showBasePointRepository("Репозиторий отсортирован");
basePointRepository.removeBasePoint(0);
showBasePointRepository("удалёна первая точка");
basePointRepository.addNewBasePoint();
showBasePointRepository("добавлена новая пустая точка");
basePointRepository.removeBasePoint(-1);
showBasePointRepository("удалёна последняя точка");
//#endregion

//#region region test BasePointService
let basePointService = new BasePointService();
createDemoBasePointService();
showBasePointService("Сервис демо создан");
basePointService.sortByName();
showBasePointService("отсортировано по имени содержимое");
let linesArray = basePointService.getLinesArray();
showLinesAray("Массив строк в формате 'kat'", linesArray);
basePointService.removeBasePoint(0);
showBasePointService("удалена первая точка");
basePointService.addNewBasePoint();
basePointService.saveBasePointName(-1, "Шахты");
showBasePointService("добавлена точка с названием 'Шахты'");
basePointService.addNewBasePoint();
basePointService.saveBasePointName(-1, "Артём");
showBasePointService("добавлена точка с названием 'Артём'");
basePointService.sortByName();
showBasePointService("отсортировано по имени содержимое");
basePointService.clearAll();
showBasePointService("репозиторий очищен");
//#endregion

/**
 * Displays message and content of repository by basePointService methods
 * @param {string} message 
 */
function showBasePointService(message) {

    let newElem = document.createElement('div');
    newElem.innerHTML = `<br>------${message}-----<br>`;
    content.append(newElem);


    newElem = document.createElement('div');
    newElem.innerHTML = `Размер репозитория: ${basePointService.size()} станций`;

    content.append(newElem);

    if (basePointService.size() > 0) {
        for (let i = 0; i < basePointService.size(); i++) {
            let newElem = document.createElement('div');
            let text = `${basePointService.getBasePointName(i)} `;
            text += `${basePointService.getBasePointX(i)} `
            text += `${basePointService.getBasePointY(i)} `
            text += `${basePointService.getBasePointZ(i)}`
            newElem.innerHTML = text;
            content.append(newElem);
        }

    }
}

/**
 * Fills basePointService by demo data
 */
function createDemoBasePointService() {

    basePointService.addNewBasePoint();
    basePointService.saveBasePointName(-1, "1304");
    basePointService.saveBasePointX(-1, "478959.197");
    basePointService.saveBasePointY(-1, "2297237.990");
    basePointService.saveBasePointZ(-1, "12.603");


    basePointService.addNewBasePoint();
    basePointService.saveBasePointName(-1, "1301");
    basePointService.saveBasePointX(-1, "478676.113");
    basePointService.saveBasePointY(-1, "2296967.264");
    basePointService.saveBasePointZ(-1, "11.220");

    basePointService.addNewBasePoint();
    basePointService.saveBasePointName(-1, "1303");
    basePointService.saveBasePointX(-1, "478892.696");
    basePointService.saveBasePointY(-1, "2297239.168");
    basePointService.saveBasePointZ(-1, "10.926");

    basePointService.addNewBasePoint();
    basePointService.saveBasePointName(-1, "1302");
    basePointService.saveBasePointX(-1, "478685.352");
    basePointService.saveBasePointY(-1, "2296938.168");
    basePointService.saveBasePointZ(-1, "11.426");



// 1301 478676.113 2296967.264 11.220
// 1302 478685.352 2296938.168 11.426
// 1304 478959.197 2297237.990 12.603
// 1303 478892.696 2297239.168 10.926
}


/**
 * Displays basePointRepository
 */
function showBasePointRepository(message) {

    let newElem = document.createElement('div');
    newElem.innerHTML = `<br>------${message}-----<br>`;
    content.append(newElem);


    newElem = document.createElement('div');
    newElem.innerHTML = `Размер репозитория: ${basePointRepository.size()} станций`;

    content.append(newElem);

    if (basePointRepository.size() > 0) {
        for (let i = 0; i < basePointRepository.size(); i++) {
            let newElem = document.createElement('div');
            let text = `${basePointRepository.getBasePointName(i)} `;
            text += `${basePointRepository.getBasePointX(i)} `
            text += `${basePointRepository.getBasePointY(i)} `
            text += `${basePointRepository.getBasePointZ(i)}`
            newElem.innerHTML = text;
            content.append(newElem);
        }

    }

}

/**
 * Fills basePointRepository by demo data
 */
function createDemoBasePointRepository() {

    basePointRepository.addNewBasePoint();
    basePointRepository.saveBasePointName(-1, "1304");
    basePointRepository.saveBasePointX(-1, "478959.197");
    basePointRepository.saveBasePointY(-1, "2297237.990");
    basePointRepository.saveBasePointZ(-1, "12.603");


    basePointRepository.addNewBasePoint();
    basePointRepository.saveBasePointName(-1, "1301");
    basePointRepository.saveBasePointX(-1, "478676.113");
    basePointRepository.saveBasePointY(-1, "2296967.264");
    basePointRepository.saveBasePointZ(-1, "11.220");

    basePointRepository.addNewBasePoint();
    basePointRepository.saveBasePointName(-1, "1303");
    basePointRepository.saveBasePointX(-1, "478892.696");
    basePointRepository.saveBasePointY(-1, "2297239.168");
    basePointRepository.saveBasePointZ(-1, "10.926");

    basePointRepository.addNewBasePoint();
    basePointRepository.saveBasePointName(-1, "1302");
    basePointRepository.saveBasePointX(-1, "478685.352");
    basePointRepository.saveBasePointY(-1, "2296938.168");
    basePointRepository.saveBasePointZ(-1, "11.426");



// 1301 478676.113 2296967.264 11.220
// 1302 478685.352 2296938.168 11.426
// 1304 478959.197 2297237.990 12.603
// 1303 478892.696 2297239.168 10.926
}

/**
 * Displays string array
 * @param {string} message 
 * @param {string[]} linesArray 
 */
function showLinesAray(message, linesArray) {

    let newElem = document.createElement('div');
    newElem.innerHTML = `<br>------${message}-----<br>`
    content.append(newElem);

    linesArray.forEach((elem) => {
        let newElem = document.createElement('div');        
        newElem.innerHTML = `${elem}`;
        content.append(newElem);

    });
}
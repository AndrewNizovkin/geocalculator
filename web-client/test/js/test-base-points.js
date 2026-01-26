import { BasePointRepository} from "../../js/repository/BasePointRepository.js";

let content = document.getElementById("content");
content.innerHTML = 'Hello, I am test-base-points.js';

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
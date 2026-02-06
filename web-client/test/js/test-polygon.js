import { PolygonRepository } from "../../js/repository/PolygonRepository.js";
import { PolygonService } from "../../js/service/PolygonService.js";

const content = document.getElementById("content");

const polygonRepository = new PolygonRepository();

const polygonService = new PolygonService();

// test PolygonRepository

// fillDemoPolygonRepository();
// showPolygonRepository("Демо-репозиторий");
// polygonRepository.removeStation(0);
// showPolygonRepository("удалили первую");
// polygonRepository.insertNewStation(2);
// showPolygonRepository("вставили по индексу 2");
// polygonRepository.clearAll();
// showPolygonRepository("Очистили");

// test PolygonService

showPolygonService("После создания нового polygonService");
fillDemoPolygonService();
showPolygonService("Заполнил демо данными");
polygonService.insertNewStation(0);
showPolygonService("Вставил пустую в начало");
polygonService.removeStation(5);
showPolygonService("Удалил последнюю");
polygonService.insertNewStation(2);
showPolygonService("Вставил пустую с индексом 2");
polygonService.clearAll();
showPolygonService("Очистил всё");
polygonService.addNewStation();
showPolygonService("Добавил пустую");

/**
 * Отображает текущее состояние репозитория 
 * методами polygonService
 * @param {string} message 
 */
function showPolygonService(message) {

    let element = document.createElement('div');
    element.innerHTML = `<br>------${message}------<br>`;

    content.append(element);

    element = document.createElement('div');
    element.innerHTML = `Количество станций полигонометрии в репозитории: ${polygonService.size()}<br>`;
    content.append(element);

    if (polygonService.size() > 0) {
        const stations = document.createElement('ol');
        for (let i = 0; i < polygonService.size(); i++) {
            let station = document.createElement('li');
            station.innerHTML = `
            name: ${polygonService.getStationName(i)}<br>
            horAngle: ${polygonService.getHorAngle(i)}<br>
            horDistance: ${polygonService.getHorDistance(i)}<br>
            elevation: ${polygonService.getElevation(i)}<br>
            stationX: ${polygonService.getStationX(i)}<br>
            stationY: ${polygonService.getStationY(i)}<br>
            stationZ: ${polygonService.getStationZ(i)}<br>
            `;
            stations.append(station);
        }
        content.append(stations);
    }


}

/**
 * Displays polygonRepositoty
 * @param {string} message 
 */
function showPolygonRepository(message) {

    let element = document.createElement('div');
    element.innerHTML = `<br>------${message}------<br>`;

    content.append(element);

    element = document.createElement('div');
    element.innerHTML = `Количество станций в репозитории: ${polygonRepository.size()}<br>`;
    content.append(element);

    if (polygonRepository.size() > 0) {
        const stations = document.createElement('ol');
        for (let i = 0; i < polygonRepository.size(); i++) {
            let station = document.createElement('li');
            station.innerHTML = `
            name: ${polygonRepository.getStationName(i)}<br>
            horAngle: ${polygonRepository.getHorAngle(i)}<br>
            horDistance: ${polygonRepository.getHorDistance(i)}<br>
            elevation: ${polygonRepository.getElevation(i)}<br>
            stationX: ${polygonRepository.getStationX(i)}<br>
            stationY: ${polygonRepository.getStationY(i)}<br>
            stationZ: ${polygonRepository.getStationZ(i)}<br>
            `;
            stations.append(station);
        }
        content.append(stations);
    }

}

/**
 * Fiils demo data to polygonRepository
 */
function fillDemoPolygonRepository() {

    for (let i = 0; i < 5; i++) {
        polygonRepository.addNewStation();
        
        polygonRepository.saveStationName(-1, `station-${i + 1}`);
        polygonRepository.saveHorAngle(-1, "359.5959");
        polygonRepository.saveHorDistance(-1, "69.999");
        polygonRepository.saveElevation(-1, "-1.356");
        if (i == 0) {
            polygonRepository.saveStationX(-1, "478676.113");
            polygonRepository.saveStationY(-1, "2297003.862");
            polygonRepository.saveStationZ(-1, "12.630");
        } else {
            polygonRepository.saveStationX(-1, "Not");
            polygonRepository.saveStationY(-1, "Not");
            polygonRepository.saveStationZ(-1, "Not");
        }
        
    }

}

/**
 * Fiils demo data to polygonService
 */
function fillDemoPolygonService() {

    for (let i = 0; i < 5; i++) {
        polygonService.addNewStation();
        
        polygonService.saveStationName(-1, `station-${i + 1}`);
        polygonService.saveHorAngle(-1, "359.5959");
        polygonService.saveHorDistance(-1, "69.999");
        polygonService.saveElevation(-1, "-1.356");
        if (i == 0) {
            polygonService.saveStationX(-1, "478676.113");
            polygonService.saveStationY(-1, "2297003.862");
            polygonService.saveStationZ(-1, "12.630");
        } else {
            polygonService.saveStationX(-1, "Not");
            polygonService.saveStationY(-1, "Not");
            polygonService.saveStationZ(-1, "Not");
        }
        
    }

}


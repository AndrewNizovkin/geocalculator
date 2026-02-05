import { PolygonRepository } from "../../js/repository/PolygonRepository.js";

const content = document.getElementById("content");

const polygonRepository = new PolygonRepository();

// polygonRepository.addNewStation();

// polygonRepository.saveStationName(0, "first")

fillDemoPolygonRepository();

showPolygonRepository("Демо-репозиторий");

polygonRepository.removeStation(0);

showPolygonRepository("удалили первую");

polygonRepository.insertNewStation(2);

showPolygonRepository("вставили по индексу 2");

polygonRepository.clearAll();

showPolygonRepository("Очистили");

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


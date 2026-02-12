/**
 * This class provides methods for converting 
 * models and dto objects for PolygonService
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class PolygonMapper {

    /**
     * Converts an instance of the PolygonRepositoryinto 
     * an array of strings in the 'pol' format
     * @param {PolygonRepository} polygonRepository 
     * @returns {string[]}
     */
    polygonRepositoryToArray(polygonRepository) {
        const linesArray = new Array();
        let line = '';

        for (let i = 0; i < polygonRepository.size(); i++) {
            line = '';
            line += `${polygonRepository.getStationName(i)} `;
            line += `${polygonRepository.getHorAngle(i)} `;
            line += `${polygonRepository.getHorDistance(i)} `;
            line += `${polygonRepository.getElevation(i)} `;
            if (polygonRepository.getStatus(i)) {
                line += `${polygonRepository.getStationX(i)} `;
                line += `${polygonRepository.getStationY(i)} `;
                line += `${polygonRepository.getStationZ(i)}`;
            } else {
                line += `Not `;
                line += `Not `;
                line += `Not`;

            }
            
            linesArray.push(line);
        }

        return linesArray;
    }

    /**
     * converts an array of strings in the 'pol' format to 
     * an instance of the 'PolygonRepository' object
     * @param {string[]} linesArray 
     * @param {PolygonRepository} polygonRepository 
     */
    arrayToPolygonRepository(linesArray, polygonRepository) {
        for (let line of linesArray) {
            let itemsArray = line.trim().split(/\s+/);
            if (itemsArray.length == 7) {
                polygonRepository.addNewStation();
                polygonRepository.saveStationName(-1, itemsArray.at(0));
                polygonRepository.saveHorAngle(-1, itemsArray.at(1));
                polygonRepository.saveHorDistance(-1, itemsArray.at(2));
                polygonRepository.saveElevation(-1, itemsArray.at(3));
                polygonRepository.saveStationX(-1, itemsArray.at(4));
                polygonRepository.saveStationY(-1, itemsArray.at(5));
                polygonRepository.saveStationZ(-1, itemsArray.at(6));
                if ((itemsArray.at(4) == "Not") || (itemsArray.at(5) == "Not") || (itemsArray.at(6) == "Not")) {
                    polygonRepository.saveStatus(-1, false);
                } else {
                    polygonRepository.saveStatus(-1, true);
                }
            }
        }

        return polygonRepository;
    }

}
// import { BasePointRepository } from "../../repository/BasePointRepository";

/**
 * This class provides methods for converting 
 * models and dto objects for BasePoint domen.
 */
export class BasePointMapper {

    /**
     * converts an instance of the 'BasePointRepository' object into 
     * an array of strings in the 'kat' format
     * @param {BasePointRepository} basePointRepository 
     * @returns {string[]}
     */
    basePointRepositoryToArray(basePointRepository) {
        const linesArray = new Array();

        if (basePointRepository.size() > 0) {
            for (let i = 0; i < basePointRepository.size(); i++) {
                let line = '';
                line += `${basePointRepository.getBasePointName(i)} `;
                line += `${basePointRepository.getBasePointX(i)} `;
                line += `${basePointRepository.getBasePointY(i)} `;
                line += `${basePointRepository.getBasePointZ(i)}`;
                linesArray.push(line);
            } 
        }
        return linesArray;
    }

    /**
     * converts an array of strings in the 'kat' format to 
     * an instance of the 'BasePointRepository' object
     * @param {string[]} arrayKat 
     * @param {BasePointRepository} basePointRepository 
     * @returns 
     */
    arrayToBasePointRepository(arrayKat, basePointRepository) {

        arrayKat.forEach((line) => {
            let arrayItem = line.trim().split(/\s+/);
            if (arrayItem.length == 4) {
                basePointRepository.addNewBasePoint();
                basePointRepository.saveBasePointName(-1, arrayItem.at(0));
                basePointRepository.saveBasePointX(-1, arrayItem.at(1));
                basePointRepository.saveBasePointY(-1, arrayItem.at(2));
                basePointRepository.saveBasePointZ(-1, arrayItem.at(3));
            }
        });

        return basePointRepository;

    }

}
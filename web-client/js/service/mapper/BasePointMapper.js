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

}
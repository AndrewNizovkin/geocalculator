import { BasePointRepository } from "../repository/BasePointRepository.js";
import { BasePointMapper } from "./mapper/BasePointMapper.js";
import { BasePointProvider } from "./provider/BasePointProvider.js";

/**
 * Provides methods for working with a basepoints repository
 */
export class BasePointService {
    constructor() {
        this.basePointRepository = new BasePointRepository();
        this.basePointMapper = new BasePointMapper();
        this.basePointProvider = new BasePointProvider();
    }

    /**
     * Clears repository
     */
    clearAll() {
        this.basePointRepository.clearAll();
    }

    /**
     * Gets the number of base points in the repository
     * @returns {number}
     */
    size() {
        return this.basePointRepository.size();
    }

    /**
     * Returns an array of strings in the 'kat' format
     * @returns {string[]}
     */
    getLinesArray() {

        let linesArray = this.basePointMapper.basePointRepositoryToArray(this.basePointRepository);

        return linesArray;

    }

    /**
     * Fills in the base point repository from a text file in the "kat" format
     * @param {File} fileKat 
     */
    async readFromTextFile(fileKat) {
        try {
            await this.basePointProvider.getStringArrayFromDevice(fileKat).then((arrayKat) => {
                this.basePointRepository.clearAll();
                this.surveyRepository = this.basePointMapper.arrayToBasePointRepository(arrayKat, this.basePointRepository);
            });
        } catch (err) {
            console.error(err.message);
        }

    }

    /**
     * Adds new empty base point to repository
     */
    addNewBasePoint() {
        this.basePointRepository.addNewBasePoint();
    }

    /**
     * Removes the base point with the specified index from the repository
     * @param {number} index 
     */
    removeBasePoint(index) {
        this.basePointRepository.removeBasePoint(index);
        }

    /**
     * Sorts by name repository
     */
    sortByName() {
        this.basePointRepository.sortByName();
    }


    /**
     * Gets name of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointName(index) {
        return this.basePointRepository.getBasePointName(index);
    }

    /**
     * Sets name of the base point with the specified index
     * @param {number} index 
     * @param {string} pointName 
     */
    saveBasePointName(index, pointName) {
        this.basePointRepository.saveBasePointName(index, pointName);
    }

    /**
     * Gets pointX of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointX(index) {
        return this.basePointRepository.getBasePointX(index);
    }

    /**
     * Sets pointX of the base point with the specified index
     * @param {number} index 
     * @param {string} pointX 
     */
    saveBasePointX(index, pointX) {
        this.basePointRepository.saveBasePointX(index, pointX);
    }

    /**
     * Gets pointY of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointY(index) {
        return this.basePointRepository.getBasePointY(index);
    }

    /**
     * Sets pointY of the base point with the specified index
     * @param {number} index 
     * @param {string} pointY
     */
    saveBasePointY(index, pointY) {
        this.basePointRepository.saveBasePointY(index, pointY);
    }

    /**
     * Gets pointZ of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointZ(index) {
        return this.basePointRepository.getBasePointZ(index);
    }

    /**
     * Sets pointZ of the base point with the specified index
     * @param {number} index 
     * @param {string} pointZ 
     */
    saveBasePointZ(index, pointZ) {
        this.basePointRepository.saveBasePointZ(index, pointZ);
    }    

}
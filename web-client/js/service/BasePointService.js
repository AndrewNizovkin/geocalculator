import { BasePointRepository } from "../repository/BasePointRepository.js";
import { BasePointMapper } from "./mapper/BasePointMapper.js";

/**
 * Provides methods for working with a basepoints repository
 */
export class BasePointService {
    constructor() {
        this.basePointRepository = new BasePointRepository();
        this.basePointMapper = new BasePointMapper();
    }

    /**
     * Fills demo repository
     */
    loadDemoRepository() {
    this.basePointRepository.addNewBasePoint();
    this.basePointRepository.saveBasePointName(-1, "1304");
    this.basePointRepository.saveBasePointX(-1, "478959.197");
    this.basePointRepository.saveBasePointY(-1, "2297237.990");
    this.basePointRepository.saveBasePointZ(-1, "12.603");


    this.basePointRepository.addNewBasePoint();
    this.basePointRepository.saveBasePointName(-1, "1301");
    this.basePointRepository.saveBasePointX(-1, "478676.113");
    this.basePointRepository.saveBasePointY(-1, "2296967.264");
    this.basePointRepository.saveBasePointZ(-1, "11.220");

    this.basePointRepository.addNewBasePoint();
    this.basePointRepository.saveBasePointName(-1, "1303");
    this.basePointRepository.saveBasePointX(-1, "478892.696");
    this.basePointRepository.saveBasePointY(-1, "2297239.168");
    this.basePointRepository.saveBasePointZ(-1, "10.926");

    this.basePointRepository.addNewBasePoint();
    this.basePointRepository.saveBasePointName(-1, "1302");
    this.basePointRepository.saveBasePointX(-1, "478685.352");
    this.basePointRepository.saveBasePointY(-1, "2296938.168");
    this.basePointRepository.saveBasePointZ(-1, "11.426");

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
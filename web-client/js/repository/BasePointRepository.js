import { BasePoint } from "../model/BasePoint.js";

/**
 * Provides methods for working with a collection of basepoints
 */
export class BasePointRepository {
    constructor() {
        this.basePoints = new Array();
    }

    /**
     * Clears repository
     */
    clearAll() {
        this.basePoints = new Array();
    }

    /**
     * Gets the number of base points in the repository
     * @returns {number}
     */
    size() {
        return this.basePoints.length;
    }

    /**
     * Adds new instance of BasePoint to repository
     */
    addNewBasePoint() {
        let basePoint = new BasePoint();
        this.basePoints.push(basePoint);
    }

    /**
     * Removes the base point with the specified index from the repository
     * @param {number} index 
     */
    removeBasePoint(index) {
        if (index < this.basePoints.length) {
            this.basePoints.splice(index, 1);
        }
    }

    /**
     * Sorts by name repository
     */
    sortByName() {
        this.basePoints.sort((a, b) => {
            if (a.pointName > b.pointName) return 1;
            if (a.pointName < b.pointName) return -1;
            return 0;
        });
    }

    /**
     * Gets name of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointName(index) {
        if (index < this.basePoints.length) {
            return this.basePoints.at(index).pointName;
        }
    }

    /**
     * Sets name of the base point with the specified index
     * @param {number} index 
     * @param {string} pointName 
     */
    saveBasePointName(index, pointName) {
        if (index < this.basePoints.length) {
            this.basePoints.at(index).pointName = pointName;
        }
    }

    /**
     * Gets pointX of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointX(index) {
        if (index < this.basePoints.length) {
            return this.basePoints.at(index).pointX;
        }
    }

    /**
     * Sets pointX of the base point with the specified index
     * @param {number} index 
     * @param {string} pointX 
     */
    saveBasePointX(index, pointX) {
        if (index < this.basePoints.length) {
            this.basePoints.at(index).pointX = pointX;
        }
    }

    /**
     * Gets pointY of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointY(index) {
        if (index < this.basePoints.length) {
            return this.basePoints.at(index).pointY;
        }
    }

    /**
     * Sets pointY of the base point with the specified index
     * @param {number} index 
     * @param {string} pointY
     */
    saveBasePointY(index, pointY) {
        if (index < this.basePoints.length) {
            this.basePoints.at(index).pointY = pointY;
        }
    }

    /**
     * Gets pointZ of the base point with the specified index
     * @param {number} index 
     * @returns {string}
     */
    getBasePointZ(index) {
        if (index < this.basePoints.length) {
            return this.basePoints.at(index).pointZ;
        }
    }

    /**
     * Sets pointZ of the base point with the specified index
     * @param {number} index 
     * @param {string} pointZ 
     */
    saveBasePointZ(index, pointZ) {
        if (index < this.basePoints.length) {
            this.basePoints.at(index).pointZ = pointZ;
        }
    }

}
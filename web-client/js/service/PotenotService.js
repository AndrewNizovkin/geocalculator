import { Potenot } from '../model/Potenot.js';
/**
 * This class provides methods for working with the potenot task model.
 */
export class PotenotService {

    /**
     * @constructor
     */
    constructor() {
        this.potenot = new Potenot();

    }

    /**
     * Sets the initial values of the potenot model fields
     */
    clearAll() {
        this.potenot.firstX = "0.000";
        this.potenot.firstY = "0.000";
        this.potenot.firstDirection = "0.000";
        this.potenot.secondX = "0.000";
        this.potenot.secondY = "0.000";
        this.potenot.secondDirection = "0.000";
        this.potenot.thirdX = "0.000";
        this.potenot.thirdY = "0.000";
        this.potenot.thirdDirection = "0.000";                
        this.potenot.baseX = "";
        this.potenot.baseY = "";
        

    }

    /**
     * Solves potenot task
     * @returns {Any} test mode
     */
    solvePotenotTask() {

        return "Hello! I am potenotService :)";
        
    }

    /**
     * 
     * @param {string} value coordinate X of first point in meters
     */
    saveFirstX(value) {
        this.potenot.firstX = value;
    }

    /**
     * 
     * @param {string} value coordinate Y of first point in meters
     */
    saveFirstY(value) {
        this.potenot.firstY = value;
    }    

    /**
     * 
     * @param {string} value direction from base to first point in d.mmss format
     */
    saveFirstDirection(value) {
        this.potenot.firstDirection = value;
    }    

    /**
     * 
     * @param {string} value coordinate X of second point in meters
     */
    saveSecondX(value) {
        this.potenot.secondX = value;
    }

    /**
     * 
     * @param {string} value coordinate Y of second point in meters
     */
    saveSecondY(value) {
        this.potenot.secondY = value;
    }    

    /**
     * 
     * @param {string} value direction from base to second point in d.mmss format
     */
    saveSecondDirection(value) {
        this.potenot.secondDirection = value;
    }    

    /**
     * 
     * @param {string} value coordinate X of third point in meters
     */
    saveThirdX(value) {
        this.potenot.thirdX = value;
    }

    /**
     * 
     * @param {string} value coordinate Y of third point in meters
     */
    saveThirdY(value) {
        this.potenot.thirdY = value;
    }    

    /**
     * 
     * @param {string} value direction from base to third point in d.mmss format
     */
    saveThirdDirection(value) {
        this.potenot.thirdDirection = value;
    }    

    /**
     * 
     * @param {string} value coordinate X of base point in meters
     */
    saveBaseX(value) {
        this.potenot.baseX = value;
    }

    /**
     * 
     * @param {string} value coordinate Y of base point in meters
     */
    saveBaseY(value) {
        this.potenot.baseY = value;
    }
    
    /**
     * 
     * @returns {string} coordinate X of first point in meters
     */
    getFirstX() {
        return this.potenot.firstX;
    }

    /**
     * 
     * @returns {string} coordinate Y of first point in meters
     */
    getFirstY() {
        return this.potenot.firstY;
    }

    /**
     * 
     * @returns {string} direction from base to first point in d.mmss format
     */
    getFirstDirection() {
        return this.potenot.firstDirection;
    }

    /**
     * 
     * @returns {string} coordinate X of second point in meters
     */
    getSecondX() {
        return this.potenot.secondX;
    }

    /**
     * 
     * @returns {string} coordinate Y of second point in meters
     */
    getSecondY() {
        return this.potenot.secondY;
    }

    /**
     * 
     * @returns {string} direction from base to second point in d.mmss format
     */
    getSecondDirection() {
        return this.potenot.secondDirection;
    }

    /**
     * 
     * @returns {string} coordinate X of third point in meters
     */
    getThirdX() {
        return this.potenot.thirdX;
    }

    /**
     * 
     * @returns {string} coordinate Y of third point in meters
     */
    getThirdY() {
        return this.potenot.thirdY;
    }

    /**
     * 
     * @returns {string} direction from base to third point in d.mmss format
     */
    getThirdDirection() {
        return this.potenot.thirdDirection;
    }

    /**
     * 
     * @returns {string} coordinate X of base point in meters
     */
    getBaseX() {
        return this.potenot.baseX;
    }

    /**
     * 
     * @returns {string} coordinate Y of base point in meters
     */
    getBaseY() {
        return this.potenot.baseY;
    }


}
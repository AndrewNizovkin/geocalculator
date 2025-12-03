import {Inverse} from '../model/Inverse.js';
import { InverseMapper } from './mapper/InverseMapper.js';
import { InverseProvider } from './provider/InverseProvider.js';
/***
 * This class provides methods for working with the inverse task.
 */
export class InverseService {

    /**
     * @constructor
     */
    constructor() {
        this.inverse = new Inverse();
        this.inverseMapper = new InverseMapper();
        this.inverseProvider = new InverseProvider();
    }


    /**
     * Solves inverse geodetic task
     * 
    //  */
    solveInverseTask() {

        let jsonRequest = this.inverseMapper.inverseToRequest(this.inverse);

        let jsonResponse = this.inverseProvider.getInverseResponse(jsonRequest);

        let inverseResponse = this.inverseMapper.responseToInverseResponse(jsonResponse);

        this.inverse.direction = inverseResponse?.direction;
        this.inverse.horDistance = inverseResponse?.horDistance;
        this.inverse.inclinedDistance = inverseResponse?.inclinedDistance;
        this.inverse.tiltAngle = inverseResponse?.tiltAngle;
        this.inverse.elevation = inverseResponse?.elevation;

    }

    /**
     * Clears all fields of the this.inverse
     */
    clearAll() {
        this.inverse.baseX = "0.000";
        this.inverse.baseY = "0.000";
        this.inverse.baseZ = "0.000";
        this.inverse.targetX = "0.000";
        this.inverse.targetY = "0.000";
        this.inverse.targetZ = "0.000";
        this.inverse.direction = "";
        this.inverse.horDistance = "";
        this.inverse.inclinedDistance = "";
        this.inverse.tiltAngle = "";
        this.inverse.elevation = ""       
    }

    /**
     * 
     * @returns {string} base coordinate X in meters
     */
    getBaseX() {
        return this.inverse.baseX;
    }

    /**
     * 
     * @returns {string} base coordinate Y in meters
     */
    getBaseY() {
        return this.inverse.baseY;
    }

    /**
     * 
     * @returns {string} base coordinate Z in meters
     */
    getBaseZ() {
        return this.inverse.baseZ;
    }

    /**
     * 
     * @returns {string} target coordinate X in meters
     */
    getTargetX() {
        return this.inverse.targetX;
    }

    /**
     * 
     * @returns {string} target coordinate Y in meters
     */
    getTargetY() {
        return this.inverse.targetY;
    }

    /**
     * 
     * @returns {string} target coordinate Z in meters
     */
    getTargetZ() {
        return this.inverse.targetZ;
    }

    /**
     * 
     * @returns {string} direction from base to target in d.mmss
     */
    getDirection() {
        return this.inverse.direction;
    }


    /**
     * 
     * @returns {string} horizontal distance from base to target in meters
     */
    getHorDistance() {
        return this.inverse.horDistance;
    }

    /**
     * 
     * @returns {string} inclined distance from base to target in meters
     */
    getInclinedDistance() {
        return this.inverse.inclinedDistance;
    }

    /**
     * 
     * @returns {string} tilt angle from base to target in d.mmss
     */
    getTiltAngle() {
        return this.inverse.tiltAngle;
    }

    /**
     * 
     * @returns {string} elevation the target over base in meters
     */
    getElevation() {
        return this.inverse.elevation;
    }

    /**
     * 
     * @param {string} value base coordinate X in meters 
     */
    saveBaseX(value) {
        this.inverse.baseX = value;
    }

    /**
     * 
     * @param {string} value base coordinate Y in meters
     */
    saveBaseY(value) {
        this.inverse.baseY = value;
    }

    /**
     * 
     * @param {string} value base coordinate Z in meters
     */
    saveBaseZ(value) {
        this.inverse.baseZ = value;
    }

    /**
     * 
     * @param {string} value target coordinate X in meters
     */
    saveTargetX(value) {
        this.inverse.targetX = value;
    }

    /**
     * 
     * @param {string} value target coordinate Y in meters
     */
    saveTargetY(value) {
        this.inverse.targetY = value;
    }

    /**
     * 
     * @param {string} value target coordinate Z in meters
     */
    saveTargetZ(value) {
        this.inverse.targetZ = value;
    }

    /**
     * 
     * @param {string} value direction from base to target in d.mmss
     */
    saveDirection(value) {
        this.inverse.direction = value;
    }

    /**
     * 
     * @param {string} value horizontal distance from base to target in meters
     */
    saveHorDistance(value) {
        this.inverse.horDistance = value;
    }

    /**
     * 
     * @param {string} value inclined distance from base to target in meters
     */
    saveInclinedDistance(value) {
        this.inverse.inclinedDistance = value;
    }

    /**
     * 
     * @param {string} value tilt angle from base to target in d.mmss
     */
    saveTiltAngle(value) {
        this.inverse.tiltAngle = value;
    }

    /**
     * 
     * @param {string} value elevation the target over base in meters
     */
    saveElevation(value) {
        this.inverse.elevation = value;
    }
    

}

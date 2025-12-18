import { Direct } from "../../js/model/Direct.js";
import { DirectMapper } from './mapper/DirectMapper.js';
import { DirectProvider } from './provider/DirectProvider.js';

/**
 * This class provides methods for working with the direct task model.
 */
export class DirectService {

    /**
     * @constructor
     */
    constructor() {
        this.direct = new Direct();
        this.directMapper = new DirectMapper();
        this.directProvider = new DirectProvider();
    }

    /**
     * Solves direct geodetic task
     */
    async solveDirectTask() {

        let directRequest = this.directMapper.directToRequest(this.direct);

        await this.directProvider.getDirectResponse(directRequest).then(response => {
            let directResponse = this.directMapper.responseToDirectResponse(response);

            this.direct.targetX = directResponse?.targetX;
            this.direct.targetY = directResponse?.targetY;
            this.direct.targetZ = directResponse?.targetZ;            
        });

        // let jsonResponse = this.directProvider.getDirectResponse(jsonRequest);

        // let directResponse = this.directMapper.responseToDirectResponse(jsonResponse);

        // this.direct.targetX = directResponse?.targetX;
        // this.direct.targetY = directResponse?.targetY;
        // this.direct.targetZ = directResponse?.targetZ;
    }

    /**
     * Sets the initial values of the direct model fields
     */
    clearAll() {
        this.direct.landmarkX = "0.000";
        this.direct.landmarkY = "0.000";
        this.direct.landmarkDirection = "0.0000";
        this.direct.baseX = "0.000";
        this.direct.baseY = "0.000";
        this.direct.baseZ = "0.000";
        this.direct.baseHeight = "0.000";
        this.direct.targetDirection = "0.0000";
        this.direct.targetInclinedDistance = "0.000";
        this.direct.targetTiltAngle = "0.0000";
        this.direct.targetHeight = "0.000";
        this.direct.targetX = "";
        this.direct.targetY = "";
        this.direct.targetZ = "";
    }

    /**
     * 
     * @param {string} value coordinate X of landmark in meters
     */
    saveLandmarkX(value) {
        this.direct.landmarkX = value;
    }

    /**
     * 
     * @param {string} value coordinate Y of landmark in meters
     */
    saveLandmarkY(value) {
        this.direct.landmarkY = value;
    }
    
    /**
     * 
     * @param {string} value direction to landmark from base in 'd.mmss' format
     */
    saveLandmarkDirection(value) {
        this.direct.landmarkDirection = value;
    }

    /**
     * 
     * @param {string} value coordinate X of base in meters
     */
    saveBaseX(value) {
        this.direct.baseX = value;
    }


    /**
     * 
     * @param {string} value coordinate Y of base in meters
     */
    saveBaseY(value) {
        this.direct.baseY = value;
    }

    /**
     * 
     * @param {string} value coordinate Z of base in meters
     */
    saveBaseZ(value) {
        this.direct.baseZ = value;
    }

    /**
     * 
     * @param {string} value height of the tool above the base-point in meters
     */
    saveBaseHeight(value) {
        this.direct.baseHeight = value;
    }

    /**
     * 
     * @param {string} value direction to target from base in 'd.mmss' format
     */
    saveTargetDirection(value) {
        this.direct.targetDirection = value;
    }
    
    /**
     * 
     * @param {string} value inclined distance from base to target in meters
     */
    saveTargetInclindeDistance(value) {
        this.direct.targetInclinedDistance = value;
    }

    /**
     * 
     * @param {string} value tilt angle of line 'base-target' in 'd.mmss' format
     */
    saveTargetTiltAngle(value) {
        this.direct.targetTiltAngle = value;
    }

    /**
     * 
     * @param {string} value height of the tool above the target-point in meters
     */
    saveTargetHeight(value) {
        this.direct.targetHeight = value;
    }

    /**
     * 
     * @param {string} value coordinate X of target in meters
     */
    saveTargetX(value) {
        this.direct.targetX = value;
    }

    /**
     * 
     * @param {string} value coordinate Y of target in meters
     */
    saveTargetY(value) {
        this.direct.targetY = value;
    }

    /**
     * 
     * @param {string} value coordinate Z of target in meters
     */
    saveTargetZ(value) {
        this.direct.targetZ = value;
    }

    /**
     * 
     * @returns {string} landmark coordinate X in meters
     */
    getLandmarkX() {
        return this.direct.landmarkX;
    }
    
    /**
     * 
     * @returns {string} landmark coordinate Y in meters
     */
    getLandmarkY() {
        return this.direct.landmarkY;
    }

    /**
     * 
     * @returns {string} direction to landmark from base in 'd.mmss' format
     */
    getLandmarkDirection() {
        return this.direct.landmarkDirection;
    }

    /**
     * 
     * @returns {string} base coordinate X in meters
     */
    getBaseX() {
        return this.direct.baseX;
    }

    /**
     * 
     * @returns {string} base coordinate Y in meters
     */
    getBaseY() {
        return this.direct.baseY;
    }

    /**
     * 
     * @returns {string} base coordinate Z in meters
     */
    getBaseZ() {
        return this.direct.baseZ;
    }

    /**
     * 
     * @returns {string} height of the tool above the base-point in meters
     */
    getBaseHeight() {
        return this.direct.baseHeight;
    }

    /**
     * 
     * @returns {string} direction to target from base in 'd.mmss' format
     */
    getTargetDirection() {
        return this.direct.targetDirection;
    }

    getTargetInclinedDistance() {
        return this.direct.targetInclinedDistance;
    }


    /**
     * 
     * @returns {string} tilt angle of line 'base-target' in 'd.mmss' format
     */
    getTargetTiltAngle() {
        return this.direct.targetTiltAngle;
    }

    /**
     * 
     * @returns {string} height of the tool above the target-point in meters
     */
    getTargetHeight() {
        return this.direct.targetHeight;
    }

    /**
     * 
     * @returns {string} target coordinate X in meters
     */
    getTargetX() {
        return this.direct.targetX;
    }    

    /**
     * 
     * @returns {string} target coordinate Y in meters
     */
    getTargetY() {
        return this.direct.targetY;
    }    

    /**
     * 
     * @returns {string} target coordinate Z in meters
     */
    getTargetZ() {
        return this.direct.targetZ;
    }    

}
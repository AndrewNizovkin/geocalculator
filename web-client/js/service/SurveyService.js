import { SurveyRepository } from "../repository/SurveyRepository.js";

/**
 * This class provides methods for working 
 * with the survey model.
 */
export class SurveyService {

    constructor() {
        this.surveyRepository = new SurveyRepository();
    }

    /**
     * Clears survey repository
     */
    clearAll() {
        this.surveyRepository.clearAll();
    }

    /**
     * Gets size of survey repository
     * @returns {number}
     */
    size() {
        return this.surveyRepository.size();
    }

    /**
     * Gets the number of measurements at the specified station
     * @param {number} indexStation 
     * @returns {number}
     */
    measurementSize(indexStation) {
        return this.surveyRepository.measurementSize(indexStation);
    }

    /**
     * Appends new SurveyStation to the end of repository
     */
    addNewStation () {
        this.surveyRepository.addNewStation();
    }

    /**
     * Inserts new SurveyStation to need position in repository
     * @param {number} index position new element at repository
     */
    insertNewStation(indexStation) {
        this.surveyRepository.insertNewStation(indexStation);
    }

    /**
     * Removes element of repository from need position
     * @param {number} indexStation location of the repository item being deleted
     */
    removeStation(indexStation) {
        this.surveyRepository.removeStation(indexStation);
    }

    /**
     * Adds a new empty instance of Measurement to the end of the station measurement collection with the specified index
     * @param {number} indexStation the index of the station to which the new measurement is being added
     */
    addNewMeasurement(indexStation) {
        this.surveyRepository.addNewMeasurement(indexStation);
    }

    /**
     * Adds a new empty measurement to the station's measurement collection with the specified index at the specified position
     * @param {number} indexStation surveyStation index
     * @param {number} indexMeasurement position of new measurement
     */
    insertNewMeasurement(indexStation, indexMeasurement) {
        this.surveyRepository.insertNewMeasurement(indexStation, indexMeasurement);
    }

    /**
     * Deletes an item from the station's measurement collection from the specified position
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     */
    removeMeasurement(indexStation, indexMeasurement) {
        this.surveyRepository.removeMeasurement(indexStation, indexMeasurement);
    }

    /**
     * Gets the name of the station with the specified index
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationName(indexStation) {
        return this.surveyRepository.getStationName(indexStation);
    }

    /**
     * Saves the specified station name with the specified index
     * @param {number} indexStation 
     * @param {string} stationName 
     */
    saveStationName(indexStation, stationName) {
        this.surveyRepository.saveStationName(indexStation, stationName);
    }

    /**
     * Gets the coordinate X of the station with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationX(indexStation) {
        return this.surveyRepository.getStationX(indexStation);
    }

    /**
     * Saves the specified coordinate X for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationX 
     */
    saveStationX(indexStation, stationX) {
        this.surveyRepository.saveStationX(indexStation, stationX);
    }    
    
    /**
     * Gets the coordinate X of the station with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationY(indexStation) {
        return this.surveyRepository.getStationY(indexStation);
    }

    /**
     * Saves the specified coordinate Y for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationY 
     */
    saveStationY(indexStation, stationY) {
        this.surveyRepository.saveStationY(indexStation, stationY);
    }        

    /**
     * Gets the coordinate Z of the station with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationZ(indexStation) {
        return this.surveyRepository.getStationZ(indexStation);
    }

    /**
     * Saves the specified coordinate Z for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationZ 
     */
    saveStationZ(indexStation, stationZ) {
        this.surveyRepository.saveStationZ(indexStation, stationZ);
    }            

    /**
     * Gets the height of the tool above the survey station-point for station 
     * with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationHeight(indexStation) {
        return this.surveyRepository.getStationHeight(indexStation);
    }

    /**
     * Saves the height of the tool above the survey station-point for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationHeight 
     */
    saveStationHeight(indexStation, stationHeight) {
        this.surveyRepository.saveStationHeight(indexStation, stationHeight);
    }    
    
    /**
     * Gets the specified direction to the landmark for the station 
     * with the specified index in d.mmss format
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrDirection(indexStation) {
        return this.surveyRepository.getOrDirection(indexStation);
    }

    /**
     * Saves the specified direction to the landmark for the station with the specified index in d.mmss format
     * @param {number} indexStation 
     * @param {string} orDirection 
     */
    saveOrDirection(indexStation, orDirection) {
        this.surveyRepository.saveOrDirection(indexStation, orDirection);
    }

    /**
     * Gets the specified name of the landmark for the station 
     * with the specified index in d.mmss format
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrName(indexStation) {
        return this.surveyRepository.getOrName(indexStation);
    }


    /**
     * Saves the specified landmark name for the station with the specified index
     * @param {number} indexStation 
     * @param {string} orName 
     */
    saveOrName(indexStation, orName) {
        this.surveyRepository.saveOrName(indexStation, orName);
    }

    /**
     * Gets the specified coordinate X of the landmark for the station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrX(indexStation) {
        return this.surveyRepository.getOrX(indexStation);
    }


    /**
     * Saves the specified landmark coordinate X for the station with the specified index in meters
     * @param {number} indexStation 
     * @param {string} orX 
     */
    saveOrX(indexStation, orX) {
        this.surveyRepository.saveOrX(indexStation, orX);
    }

    /**
     * Gets the specified coordinate Y of the landmark for the station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrY(indexStation) {
        return this.surveyRepository.getOrY(indexStation);
    }

    /**
     * Saves the specified landmark coordinate Y for the station 
     * with the specified index in metters
     * @param {number} indexStation 
     * @param {string} orY
     */
    saveOrY(indexStation, orY) {
        this.surveyRepository.saveOrY(indexStation, orY);
    }

    /**
     * Gets the target name for an item in the measurement collection 
     * with the specified indexes of position of repository
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetName(indexStation, indexMeasurement) {
        return this.surveyRepository.getTargetName(indexStation, indexMeasurement);
    }

    /**
     * Saves the target name for an item in the measurement collection 
     * with the specified indexes of position of repository
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetName 
     */
    saveTargetName(indexStation, indexMeasurement, targetName) {
        this.surveyRepository.saveTargetName(indexStation, indexMeasurement, targetName);
    }

    /**
     * Gets the direction to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetDirection(indexStation, indexMeasurement) {
        return this.surveyRepository.getTargetDirection(indexStation, indexMeasurement);
    }
    
    /**
     * Saves the direction to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetDirection 
     */
    saveTargetDirection(indexStation, indexMeasurement, targetDirection) {
        this.surveyRepository.saveTargetDirection(indexStation, indexMeasurement, targetDirection);
    }    

    /**
     * Gets the inclined distance to target for an item in the measurement collection 
     * with the specified indexes of position of repository in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetDistance(indexStation, indexMeasurement) {
        return this.surveyRepository.getTargetDistance(indexStation, indexMeasurement);
    }


    /**
     * Saves the inclined distance to target for an item in the measurement collection 
     * with the specified indexes of position of repository in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetDistance 
     */
    saveTargetDistance(indexStation, indexMeasurement, targetDistance) {
        this.surveyRepository.saveTargetDistance(indexStation, indexMeasurement, targetDistance);
    }    

    /**
     * Gets the tilt angle to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetTiltAngle(indexStation, indexMeasurement) {
        return this.surveyRepository.getTargetTiltAngle(indexStation, indexMeasurement);
    }


    /**
     * Saves the tilt algle to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetTiltAngle 
     */
    saveTargetTiltAngle(indexStation, indexMeasurement, targetTiltAngle) {
        this.surveyRepository.saveTargetTiltAngle(indexStation, indexMeasurement, targetTiltAngle);
    }    

    /**
     * Gets the height of the tool above the target-point for station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetHeight(indexStation, indexMeasurement) {
        return this.surveyRepository.getTargetHeight(indexStation, indexMeasurement);
    }
    
    /**
     * Saves the height of the tool above the target-point for station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetHeight 
     */
    saveTargetHeight(indexStation, indexMeasurement, targetHeight) {
        this.surveyRepository.saveTargetHeight(indexStation, indexMeasurement, targetHeight);
    }    


}
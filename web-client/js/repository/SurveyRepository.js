import {SurveyStation} from '../model/SurveyStation.js';
import {Measurement} from '../model/Measurement.js';
/**
 * Provides methods for working with a collection of survey stations
 */
export class SurveyRepository {
    #surveyStations;
    
    constructor() {
        this.#surveyStations = new Array();
    }

    /**
     * Clears repository
     */
    clearAll() {
        this.#surveyStations = new Array();
    }

    /**
     * Gets size of survey repository
     * @returns {number}
     */
    size() {
        return this.#surveyStations.length;
    }

    /**
     * Gets the number of measurements at the specified station
     * @param {number} indexStation 
     * @returns {number}
     */
    measurementSize(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.measurements.length;
    }

    /**
     * Appends new SurveyStation to the end of repository
     */
    addNewStation () {
        let surveyStation = new SurveyStation();
        this.#surveyStations.push(surveyStation);
    }

    /**
     * Inserts new SurveyStation to need position in repository
     * @param {number} index position new element at repository
     */
    insertNewStation(indexStation) {
        if(indexStation <= this.#surveyStations.length) {
            let surveyStation = new SurveyStation();
            this.#surveyStations.splice(indexStation, 0, surveyStation);
        }
    }

    /**
     * Removes element of repository from need position
     * @param {number} indexStation location of the repository item being deleted
     */
    removeStation(indexStation) {
        if(indexStation < this.#surveyStations.length) {
            this.#surveyStations.splice(indexStation,1);
        }
    }

    /**
     * Adds a new empty instance of Measurement to the end of the station measurement collection with the specified index
     * @param {number} indexStation the index of the station to which the new measurement is being added
     */
    addNewMeasurement(indexStation) {
        if(indexStation < this.#surveyStations.length) {
            let measurement = new Measurement();
            let suveyStation = this.#surveyStations.at(indexStation);
            suveyStation.measurements.push(measurement);
        }
    }

    /**
     * Adds a new empty measurement to the station's measurement collection with the specified index at the specified position
     * @param {number} indexStation surveyStation index
     * @param {number} indexMeasurement position of new measurement
     */
    insertNewMeasurement(indexStation, indexMeasurement) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement <= surveyStation.measurements.length) {
                let measurement = new Measurement();
                surveyStation.measurements.splice(indexMeasurement, 0, measurement);
            }
        }
    }

    /**
     * Deletes an item from the station's measurement collection from the specified position
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     */
    removeMeasurement(indexStation, indexMeasurement) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement < surveyStation.measurements.length) {
                surveyStation.measurements.splice(indexMeasurement, 1);
            }
        }
    }

    /**
     * Gets the name of the station with the specified index
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationName(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.stationName;
    }

    /**
     * Saves the specified station name with the specified index
     * @param {number} indexStation 
     * @param {string} stationName 
     */
    saveStationName(indexStation, stationName) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.stationName = stationName;
        }
    }

    /**
     * Gets the coordinate X of the station with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationX(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.stationX;
    }

    /**
     * Saves the specified coordinate X for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationX 
     */
    saveStationX(indexStation, stationX) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.stationX = stationX;
        }
    }    
    
    /**
     * Gets the coordinate X of the station with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationY(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.stationY;
    }

    /**
     * Saves the specified coordinate Y for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationY 
     */
    saveStationY(indexStation, stationY) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.stationY = stationY;
        }
    }        

    /**
     * Gets the coordinate Z of the station with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationZ(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.stationZ
    }

    /**
     * Saves the specified coordinate Z for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationZ 
     */
    saveStationZ(indexStation, stationZ) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.stationZ = stationZ;
        }
    }            

    /**
     * Gets the height of the tool above the survey station-point for station 
     * with the specified index in metters
     * @param {number} indexStation 
     * @returns {string}
     */
    getStationHeight(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.stationHeight;
    }

    /**
     * Saves the height of the tool above the survey station-point for station with the specified index in metters
     * @param {number} indexStation 
     * @param {string} stationHeight 
     */
    saveStationHeight(indexStation, stationHeight) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.stationHeight = stationHeight;
        }
    }    
    
    /**
     * Gets the specified direction to the landmark for the station 
     * with the specified index in d.mmss format
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrDirection(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.orDirection;
    }

    /**
     * Saves the specified direction to the landmark for the station with the specified index in d.mmss format
     * @param {number} indexStation 
     * @param {string} orDirection 
     */
    saveOrDirection(indexStation, orDirection) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.orDirection = orDirection;
        }        
    }

    /**
     * Gets the specified name of the landmark for the station 
     * with the specified index in d.mmss format
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrName(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.orName;
    }


    /**
     * Saves the specified landmark name for the station with the specified index
     * @param {number} indexStation 
     * @param {string} orName 
     */
    saveOrName(indexStation, orName) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.orName = orName;
        }        
    }

    /**
     * Gets the specified coordinate X of the landmark for the station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrX(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.orX;
    }


    /**
     * Saves the specified landmark coordinate X for the station with the specified index in meters
     * @param {number} indexStation 
     * @param {string} orX 
     */
    saveOrX(indexStation, orX) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.orX = orX;
        }        
    }

    /**
     * Gets the specified coordinate Y of the landmark for the station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @returns {string}
     */
    getOrY(indexStation) {
        if(indexStation >= this.#surveyStations.length) {
            return null;
        }
        let surveyStation = this.#surveyStations.at(indexStation);
        return surveyStation.orY;
    }

    /**
     * Saves the specified landmark coordinate Y for the station 
     * with the specified index in metters
     * @param {number} indexStation 
     * @param {string} orY
     */
    saveOrY(indexStation, orY) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            surveyStation.orY = orY;
        }        
    }

    /**
     * Gets the target name for an item in the measurement collection 
     * with the specified indexes of position of repository
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetName(indexStation, indexMeasurement) {
        if(indexStation >= this.#surveyStations.lenth) {
            return null;
        }

        let surveyStation = this.#surveyStations.at(indexStation);

        if(indexMeasurement >= surveyStation.measurements.length) {
            return null;
        }
        let measurement = surveyStation.measurements.at(indexMeasurement);
        return measurement.targetName;
    }

    /**
     * Saves the target name for an item in the measurement collection 
     * with the specified indexes of position of repository
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetName 
     */
    saveTargetName(indexStation, indexMeasurement, targetName) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement < surveyStation.measurements.length) {
                let measurement = surveyStation.measurements.at(indexMeasurement);
                measurement.targetName = targetName;
            }
        }                
    }

    /**
     * Gets the direction to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetDirection(indexStation, indexMeasurement) {
        if(indexStation >= this.#surveyStations.lenth) {
            return null;
        }

        let surveyStation = this.#surveyStations.at(indexStation);

        if(indexMeasurement >= surveyStation.measurements.length) {
            return null;
        }
        let measurement = surveyStation.measurements.at(indexMeasurement);
        return measurement.targetDirection;
    }
    
    /**
     * Saves the direction to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetDirection 
     */
    saveTargetDirection(indexStation, indexMeasurement, targetDirection) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement < surveyStation.measurements.length) {
                let measurement = surveyStation.measurements.at(indexMeasurement);
                measurement.targetDirection = targetDirection;
            }
        }                
    }    

    /**
     * Gets the inclined distance to target for an item in the measurement collection 
     * with the specified indexes of position of repository in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetDistance(indexStation, indexMeasurement) {
        if(indexStation >= this.#surveyStations.lenth) {
            return null;
        }

        let surveyStation = this.#surveyStations.at(indexStation);

        if(indexMeasurement >= surveyStation.measurements.length) {
            return null;
        }
        let measurement = surveyStation.measurements.at(indexMeasurement);
        return measurement.targetDistance;
    }


    /**
     * Saves the inclined distance to target for an item in the measurement collection 
     * with the specified indexes of position of repository in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetDistance 
     */
    saveTargetDistance(indexStation, indexMeasurement, targetDistance) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement < surveyStation.measurements.length) {
                let measurement = surveyStation.measurements.at(indexMeasurement);
                measurement.targetDistance = targetDistance;
            }
        }                
    }    

    /**
     * Gets the tilt angle to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetTiltAngle(indexStation, indexMeasurement) {
        if(indexStation >= this.#surveyStations.lenth) {
            return null;
        }

        let surveyStation = this.#surveyStations.at(indexStation);

        if(indexMeasurement >= surveyStation.measurements.length) {
            return null;
        }
        let measurement = surveyStation.measurements.at(indexMeasurement);
        return measurement.targetTiltAngle;
    }


    /**
     * Saves the tilt algle to target for an item in the measurement collection 
     * with the specified indexes of position of repository in d.mmss format
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetTiltAngle 
     */
    saveTargetTiltAngle(indexStation, indexMeasurement, targetTiltAngle) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement < surveyStation.measurements.length) {
                let measurement = surveyStation.measurements.at(indexMeasurement);
                measurement.targetTiltAngle = targetTiltAngle;
            }
        }                
    }    

    /**
     * Gets the height of the tool above the target-point for station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns {string}
     */
    getTargetHeight(indexStation, indexMeasurement) {
        if(indexStation >= this.#surveyStations.lenth) {
            return null;
        }

        let surveyStation = this.#surveyStations.at(indexStation);

        if(indexMeasurement >= surveyStation.measurements.length) {
            return null;
        }
        let measurement = surveyStation.measurements.at(indexMeasurement);
        return measurement.targetHeight;
    }
    
    /**
     * Saves the height of the tool above the target-point for station 
     * with the specified index in meters
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @param {string} targetHeight 
     */
    saveTargetHeight(indexStation, indexMeasurement, targetHeight) {
        if(indexStation < this.#surveyStations.length) {
            let surveyStation = this.#surveyStations.at(indexStation);
            if(indexMeasurement < surveyStation.measurements.length) {
                let measurement = surveyStation.measurements.at(indexMeasurement);
                measurement.targetHeight = targetHeight;
            }
        }                
    }    


}
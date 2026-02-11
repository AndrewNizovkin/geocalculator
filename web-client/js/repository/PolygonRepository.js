import { PolygonStation } from "../model/PolygonStation.js";

/**
 * Provides methods for working with a collection of polygon stations
 * @author Nizovkin_A.V.
 */
export class PolygonRepository {
    #polygonStations;
    constructor() {
        this.#polygonStations = new Array();
    }

    /**
     * Crears polygon repository
     */
    clearAll() {
        this.#polygonStations = new Array;
    }

    /**
     * Gets count of stations in polygon repository
     * @returns {number}
     */
    size() {
        return this.#polygonStations.length;
    }

    /**
     * Adds new instance of PolygonStation
     */
    addNewStation() {
        this.#polygonStations.push(new PolygonStation());
    }

    /**
     * Inserts a new station into the repository 
     * at the position passed by the argument
     * @param {number} stationIndex 
     */
    insertNewStation(stationIndex) {
        if (stationIndex <= this.#polygonStations.length) {
            this.#polygonStations.splice(stationIndex, 0, new PolygonStation());
        }
    }

    /**
     * Deletes polygon station with the specified index from the repository
     * @param {number} stationIndex 
     */
    removeStation(stationIndex) {
        if (this.#polygonStations.length > 0) {
            this.#polygonStations.splice(stationIndex, 1);
        }
    }

    /**
     * Gets name of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationName(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).stationName;
    }

    /**
     * Saves name of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationName 
     * @returns 
     */
    saveStationName(stationIndex, stationName) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).stationName = stationName;
    }


    /**
     * Gets horizontal angle of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getHorAngle(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).horAngle;
    }

    /**
     * Saves horizontal angle of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} horAngle 
     * @returns 
     */
    saveHorAngle(stationIndex, horAngle) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).horAngle = horAngle;
    }

    /**
     * Gets horisontal distance of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getHorDistance(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).horDistance;
    }

    /**
     * Saves horizontal distance of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} horDistance 
     * @returns 
     */
    saveHorDistance(stationIndex, horDistance) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).horDistance = horDistance;
    }
    

    /**
     * Gets elevation of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getElevation(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).elevation;
    }

    /**
     * Saves elevation of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} elevation 
     * @returns 
     */
    saveElevation(stationIndex, elevation) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).elevation = elevation;
    }


    /**
     * Gets coordinate X of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationX(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).stationX;
    }

    /**
     * Saves coordinate X of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationX 
     * @returns 
     */
    saveStationX(stationIndex, stationX) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).stationX = stationX;
    }


    /**
     * Gets coordinate Y of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationY(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).stationY;
    }

    /**
     * Saves coordinate Y of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationX 
     * @returns 
     */
    saveStationY(stationIndex, stationY) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).stationY = stationY;
    }


    /**
     * Gets coordinate Z of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationZ(stationIndex) {
        if (stationIndex > this.#polygonStations.length) return null;
        return this.#polygonStations.at(stationIndex).stationZ;
    }

    /**
     * Saves coordinate Z of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationZ 
     * @returns 
     */
    saveStationZ(stationIndex, stationZ) {
        if (stationIndex > this.#polygonStations.length) return;        
        this.#polygonStations.at(stationIndex).stationZ = stationZ;
    }
}
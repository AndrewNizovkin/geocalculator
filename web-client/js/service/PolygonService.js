import { PolygonRepository } from "../repository/PolygonRepository.js";
import { PolygonMapper } from "./mapper/PolygonMapper.js";
import { PolygonProvider } from "./provider/PolygonProvider.js";

/**
 * This class provides methods for working 
 * with the polygon model.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class PolygonService {
    #polygonRepository;
    #polygonMapper;
    #polygonProvider;

    constructor() {
        this.#polygonRepository = new PolygonRepository();
        this.#polygonMapper = new PolygonMapper();
        this.#polygonProvider = new PolygonProvider();
    }

    /**
     * Crears polygon repository
     */
    clearAll() {
        this.#polygonRepository.clearAll();
    }

    /**
     * Gets count of stations in polygon repository
     * @returns {number}
     */
    size() {
        return this.#polygonRepository.size();
    }

   /**
    * Returns an array of strings in the 'pol' format
    * @returns {string[]}
    */
    getLinesPolArray() {
        return this.#polygonMapper.poligonRepositoryToArray(this.#polygonRepository);
    }

    /**
     * Fills in the survey stations repository from a text file in the "tah" format
     * @param {File} 
     */
    async readFromDevice(filePol) {
        try {
            await this.#polygonProvider.getStringArrayFromDevice(filePol).then((object) => {
                this.#polygonRepository.clearAll();
                this.#polygonRepository = this.#polygonMapper.arrayToPolygonRepository(object, this.#polygonRepository);
            });
        } catch (err) {
            console.error(err.message);
        }
    }


    /**
     * Adds new instance of PolygonStation
     */
    addNewStation() {
        this.#polygonRepository.addNewStation();
    }

    /**
     * Inserts a new station into the repository 
     * at the position passed by the argument
     * @param {number} stationIndex 
     */
    insertNewStation(stationIndex) {
        this.#polygonRepository.insertNewStation(stationIndex);
    }

    /**
     * Deletes polygon station with the specified index from the repository
     * @param {number} stationIndex 
     */
    removeStation(stationIndex) {
        this.#polygonRepository.removeStation(stationIndex);
    }

    /**
     * Gets name of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationName(stationIndex) {
        return this.#polygonRepository.getStationName(stationIndex);
    }

    /**
     * Saves name of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationName 
     * @returns 
     */
    saveStationName(stationIndex, stationName) {
        this.#polygonRepository.saveStationName(stationIndex, stationName);
    }


    /**
     * Gets horizontal angle of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getHorAngle(stationIndex) {
        return this.#polygonRepository.getHorAngle(stationIndex);
    }

    /**
     * Saves horizontal angle of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} horAngle 
     * @returns 
     */
    saveHorAngle(stationIndex, horAngle) {
        this.#polygonRepository.saveHorAngle(stationIndex, horAngle);
    }

    /**
     * Gets horisontal distance of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getHorDistance(stationIndex) {
        return this.#polygonRepository.getHorDistance(stationIndex);
    }

    /**
     * Saves horizontal distance of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} horDistance 
     * @returns 
     */
    saveHorDistance(stationIndex, horDistance) {
        this.#polygonRepository.saveHorDistance(stationIndex, horDistance);
    }
    

    /**
     * Gets elevation of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getElevation(stationIndex) {
        return this.#polygonRepository.getElevation(stationIndex);
    }

    /**
     * Saves elevation of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} elevation 
     * @returns 
     */
    saveElevation(stationIndex, elevation) {
        this.#polygonRepository.saveElevation(stationIndex, elevation);
    }


    /**
     * Gets coordinate X of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationX(stationIndex) {
        return this.#polygonRepository.getStationX(stationIndex);
    }

    /**
     * Saves coordinate X of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationX 
     * @returns 
     */
    saveStationX(stationIndex, stationX) {
        this.#polygonRepository.saveStationX(stationIndex, stationX);
    }


    /**
     * Gets coordinate Y of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationY(stationIndex) {
        return this.#polygonRepository.getStationY(stationIndex);
    }

    /**
     * Saves coordinate Y of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationX 
     * @returns 
     */
    saveStationY(stationIndex, stationY) {
        this.#polygonRepository.saveStationY(stationIndex, stationY);
    }


    /**
     * Gets coordinate Z of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {string}
     */
    getStationZ(stationIndex) {
        return this.#polygonRepository.getStationZ(stationIndex);
    }

    /**
     * Saves coordinate Z of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {string} stationZ 
     * @returns 
     */
    saveStationZ(stationIndex, stationZ) {
        this.#polygonRepository.saveStationZ(stationIndex, stationZ);
    }

}
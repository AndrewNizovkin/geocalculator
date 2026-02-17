import { PolygonRepository } from "../repository/PolygonRepository.js";
import { PolygonMapper } from "./mapper/PolygonMapper.js";
import { PolygonProvider } from "./provider/PolygonProvider.js";
import { Residuals } from "../model/Residuals.js";

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
    #residuals;
    #reportCatalog;
    #reportPlan;
    #reportElevation;

    constructor() {
        this.#polygonRepository = new PolygonRepository();
        this.#polygonMapper = new PolygonMapper();
        this.#polygonProvider = new PolygonProvider();
        this.#residuals = new Residuals();
        this.#reportCatalog = new Array();
        this.#reportPlan = new Array();
        this.#reportElevation = new Array();
    }

    /**
     * Clears polygon repository
     */
    clearAll() {
        this.#polygonRepository.clearAll();
        this.clearReports();
    }

    /**
     * Clears results of polygon processing
     */
    clearReports() {
        this.#residuals = new Residuals();
        this.#reportCatalog = new Array();
        this.#reportPlan = new Array();
        this.#reportElevation = new Array();
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
        return this.#polygonMapper.polygonRepositoryToArray(this.#polygonRepository);
    }

    /**
     * Fills in the survey stations repository from a text file in the "tah" format
     * @param {File} 
     */
    async readFromDevice(filePol) {
        try {
            await this.#polygonProvider.getStringArrayFromDevice(filePol).then((object) => {
                this.clearAll();
                this.#polygonRepository = this.#polygonMapper.arrayToPolygonRepository(object, this.#polygonRepository);
            });
        } catch (err) {
            console.error(err.message);
        }
    }

    /**
     * Updates reports after mathematical processing 
     * of polygonometric measurements
     */
    async calculatePolygon(reportFile) {
        // this.#setDemoReports();
        // this.clearAll();
        this.clearReports();
        let polygonRequest = this.getLinesPolArray();

        try {
            await this.#polygonProvider.getPolygonResponse(reportFile).then((polygonResponse) => {
                this.#polygonMapper.polygonResponseToReports(
                    polygonResponse, 
                    this.#residuals, 
                    this.#reportCatalog,
                    this.#reportPlan,
                    this.#reportElevation
                );
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

    /**
     * Gets status of the polygon station 
     * with the specified index
     * @param {number} stationIndex 
     * @returns {boolean}
     */
    getStatus(stationIndex) {
        return this.#polygonRepository.getStatus(stationIndex);

    }

    /**
     * Saves status of the polygon station 
     * with the specified index
     * @param {number} indexStation 
     * @param {boolean} status 
     * @returns 
     */
    saveStatus(stationIndex, status) {
        this.#polygonRepository.saveStatus(stationIndex, status);
    }     
    
    /**
     * Gets elevation residual of polygon
     * @returns {string}
     */
    getResidualElevation() {
        return this.#residuals.elevation;
    }

    /**
     * Gets horizontal angle residual of polygon
     * @returns {string}
     */
    getResidualAngle() {
        return this.#residuals.angle;
    }

    /**
     * Gets line residual of polygon along x axis
     * @returns {string}
     */
    getResidualX() {
        return this.#residuals.x;
    }

    /**
     * Gets line residual of polygon along y axis
     * @returns {string}
     */
    getResidualY() {
        return this.#residuals.y;
    }

    /**
     * Gets absolute residual of polygon
     * @returns {string}
     */
    getResidualAbsolute() {
        return this.#residuals.ablolute;
    }

    /**
     * Gets relative residual of polygon
     * @returns {string}
     */
    getResidualRelative() {
        return this.#residuals.relative;
    }

    /**
     * Gets perimeter of polygon
     * @returns {string}
     */
    getPerimeter() {
        return this.#residuals.perimeter;
    }

    /**
     * Gets the calculated coordinates of the polygon points
     * @returns {string[]}
     */
    getReportCatalog() {
        return this.#reportCatalog;
    }

    /**
     * Gets a list of calculations of plan coordinates
     * of the polygon points
     * @returns {string[]}
     */
    getReportPlan() {
        return this.#reportPlan;
    }

    /**
     * Gets a list of calculations of height coordinates
     * of the polygon points
     * @returns {string[]}
     */
    getReportElevation() {
        return this.#reportElevation;
    }

}
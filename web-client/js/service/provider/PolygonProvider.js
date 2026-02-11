import { TextFileReader } from "./TextFileReader.js";

/**
 * This class provides methods 
 * for getting data from external sources for PolygonService
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class PolygonProvider {

    /**
     * Reads the lines of a text file into
     *  an array of strings
     * @param {File} pathToFile 
     * @returns {string[]}
     */
    async getStringArrayFromDevice(filePol) {
        return TextFileReader.readFromTextFile(filePol);
    }

}
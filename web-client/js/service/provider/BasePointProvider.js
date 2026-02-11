import { TextFileReader } from "./TextFileReader.js";
/**
 * This class provides methods 
 * for getting data from external sources.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class BasePointProvider {

    /**
     * Reads the lines of a text file into
     *  an array of strings
     * @param {File} pathToFile 
     * @returns {string[]}
     */
    async getStringArrayFromDevice(fileTah) {
        return TextFileReader.readFromTextFile(fileTah);
    }

}
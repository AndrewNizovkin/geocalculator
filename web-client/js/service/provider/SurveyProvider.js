import { TextFileReader } from "./TextFileReader.js";

/**
 * This class provides methods 
 * for getting data from external sources.
 */
export class SurveyProvider {

    /**
     * Reads the lines of a text file into
     *  an array of strings
     * @param {File} pathToFile 
     * @returns {string[]}
     */
    async getStringArrayFromDevice(fileTah) {
        return TextFileReader.readFromTextFile(fileTah);
    }

    /**
     * 
     * @param {string} pathToFile 
     * @param {string[]} stringArray 
     */
    saveToDevice(pathToFile, stringArray) {

    }

}
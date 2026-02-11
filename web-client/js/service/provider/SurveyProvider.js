import { TextFileReader } from "./TextFileReader.js";

/**
 * This class provides methods 
 * for getting data from external sources.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
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
     * Sends to backend Post HTTP request and get response
     * @param {string[]} lineArray 
     * @returns {string[]}
     */
    async importTah(importRequest) {
        const urlServer = `http://${AppConfigurator.baseUrl}/${AppConfigurator.directEndPoint}`;

        try {
            const response = await fetch('http://192.168.0.12:8181/import', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(importRequest)
            });

            if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

            return await response.json();
        } catch(error) {
            alert(`Ошибка отправки данных: ${error.message}`);
            // throw error;
        }

    }

}
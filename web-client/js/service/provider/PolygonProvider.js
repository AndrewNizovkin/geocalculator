import { TextFileReader } from "./TextFileReader.js";
import { AppConfigurator } from "../../AppConfigurator.js";

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

    /**
     * Sends a request to the back-end server for mathematical processing
     * of polygonometric measurements and returns 
     * the server's response in the form of a summary report
     * @param {string[]} polygonRequest 
     * @returns {string[]} 
     */
    async getPolygonResponse(polygonRequest) {

        const urlServer = `http://${AppConfigurator.baseUrl}/${AppConfigurator.polygonReportEndPoint}`;

        try {
            const response = await fetch(urlServer, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(polygonRequest)
            });

            if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

            return await response.json();
        } catch(error) {
            alert(`Ошибка отправки данных: ${error.message}`);
            // throw error;
        }

    }

}
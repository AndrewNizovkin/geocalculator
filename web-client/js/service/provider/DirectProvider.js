import { AppConfigurator } from '../../AppConfigurator.js';
/**
 * This class executes a query to the backend to solve the direct geodetic task.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class DirectProvider {

    /**
     * Sends to backend Post HTTP request and get response
     * @param {directRequest} directRequest contains data for solve direct geodetic task
     * @returns {Promise} json with response of server
     */
    async getDirectResponse(directRequest) {
        const urlServer = `http://${AppConfigurator.baseUrl}/${AppConfigurator.directEndPoint}`;

        try {
            const response = await fetch('http://192.168.0.12:8181/direct', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(directRequest)
            });

            if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

            return await response.json();
        } catch(error) {
            alert(`Ошибка отправки данных: ${error.message}`);
            // throw error;
        }



    }

}
import { AppConfigurator } from '../../AppConfigurator.js';

/**
 * This class executes a query to the backend to solve the inverse geodetic task.
 */
export class InverseProvider {

    /**
     * Sends to backend Post HTTP request and get response
     * @param {InverseRequest} inverseRequest 
     * @returns {Promise} json with response of server
     */
     async getInverseResponse(inverseRequest) {
        const urlServer = `http://${AppConfigurator.baseUrl}/${AppConfigurator.inverseEndPoint}`;

        try {
            const response = await fetch(urlServer, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(inverseRequest)
            });

            if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

            return await response.json();
        } catch(error) {
            alert(`Ошибка отправки данных: ${error.message}`);
            // throw error;
        }
       
    }
}
import { AppConfigurator } from '../../AppConfigurator.js';

/**
 * This class executes a query to the backend 
 * to solve the Potenot task.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class PotenotProvider {

    /**
     * Sends to backend Post HTTP request and get response
     * @param {PotenotRequest} jsonResponse 
     * @returns {Promise} json of potenotResponse
     */
    async getPotenotResponse(potenotRequests) {

        const urlServer = `http://${AppConfigurator.baseUrl}/${AppConfigurator.potenotEndPoint}`;

        try {
            const response = await fetch(urlServer, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(potenotRequests)
            });

            if(!response.ok) throw new Error(`Ошибка HTTP ${response.status}`);

            return await response.json();
        } catch(error) {
            alert(`Ошибка отправки данных: ${error.message}`);
            // throw error;
        }
    }
}
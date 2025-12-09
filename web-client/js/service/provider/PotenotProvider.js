/**
 * This class executes a query to the backend 
 * to solve the Potenot task.
 */
export class PotenotProvider {

    getPotenotResponse(jsonResponse) {

        // send to backend Post HTTP request and get good response

        const response = {
            pointX: 7777777111,
            pointY: 8888888222
        };

        return JSON.stringify(response);
    }

}
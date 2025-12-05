/**
 * This class executes a query to the backend to solve the direct geodetic task.
 */
export class DirectProvider {

    getDirectResponse(jsonRequest) {

        // send to backend Post HTTP request and get good response

        const response = {
            targetX: 100000000,
            targetY: 200000000,
            targetZ: 300000
        };

        return JSON.stringify(response);
    }

}
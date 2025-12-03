/**
 * This class executes a query to the backend to solve the inverse geodetic task.
 */
export class InverseProvider {

    getInverseResponse(jsonRequest) {

        // send to backend Post HTTP request and get good response

        let response = {
            baseX: 1000,
            direction: 3600,
            horDistance: 123456,
            inclinedDistance: 123452,
            tiltAngle: -121,
            elevation: 2345
        };


        return JSON.stringify(response);
    }
}
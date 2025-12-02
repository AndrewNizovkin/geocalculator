// import { InverseResponse } from "../../dto/InverseResponse.js";

export class InverseProvider {

    getInverseResponse(inverseRequest) {

        // send to backend Post HTTP request and get good response

        let response = {
            baseX: 1000,
            direction: 3600,
            horDistanse: 123456,
            inclinedDistance: 123452,
            tiltAngle: -121,
            elevation: 2345
        };


        return JSON.stringify(response);
    }
} 
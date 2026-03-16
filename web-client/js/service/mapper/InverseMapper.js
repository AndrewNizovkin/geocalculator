import {InverseResponse} from '../../dto/InverseResponse.js';
import {InverseRequest} from '../../dto/InverseRequest.js';

/**
 * This class represents methods for converting models to dto and vice versa.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class InverseMapper {
   

    /**
     * Creates body of Post-request to backend with data fo solve inverse task
     * @param {Inverse} inverse instance of Inverse
     */
    inverseToRequest(inverse) {
        let inverseRequest = new InverseRequest();

        inverseRequest.baseX = inverse?.baseX;
        inverseRequest.baseY = inverse?.baseY;
        inverseRequest.baseZ = inverse?.baseZ;
        inverseRequest.targetX = inverse?.targetX;
        inverseRequest.targetY = inverse?.targetY;
        inverseRequest.targetZ = inverse?.targetZ;

        return inverseRequest;
    }

    /**
     * Converts body of json response from  backend to instance of InverseResponse
     * @param {string} jsonResponse json response from backend
     * @returns {InverseResponse}  Contains a solution to the inverse geodetic task
     */
    responseToInverseResponse(jsonResponse) {

        let inverseResponse = new InverseResponse();

        inverseResponse.direction = jsonResponse?.direction;
        inverseResponse.horDistance = jsonResponse?.horDistance;
        inverseResponse.inclinedDistance = jsonResponse?.inclinedDistance;
        inverseResponse.tiltAngle = jsonResponse?.tiltAngle;
        inverseResponse.elevation = jsonResponse?.elevation;

        return inverseResponse;
    }

}
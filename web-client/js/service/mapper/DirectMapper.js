import { DirectRequest } from '../../dto/DirectRequest.js';
import { DirectResponse } from '../../dto/DirectResponse.js';

/**
 * This class represents methods for converting 
 * models to dto and vice versa for Direct Task.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class DirectMapper {
    constructor() {
            
    }

    /**
     * Creates body of Post-request to backend with data 
     * for solve direct task
     * @param {Direct} direct instance of Direct
     * @returns {string} json of instance of DirectreRequest
     */
    directToRequest(direct) {
        let directRequest = new DirectRequest();

        directRequest.landmarkX = direct?.landmarkX;
        directRequest.landmarkY = direct?.landmarkY;
        directRequest.landmarkDirection = direct?.landmarkDirection;
        directRequest.baseX = direct?.baseX;
        directRequest.baseY = direct?.baseY;
        directRequest.baseZ = direct?.baseZ;
        directRequest.baseHeight = direct?.baseHeight;
        directRequest.targetDirection = direct?.targetDirection;
        directRequest.targetInclinedDistance = direct?.targetInclinedDistance;
        directRequest.targetTiltAngle = direct?.targetTiltAngle;
        directRequest.targetHeight = direct?.targetHeight;

        return directRequest;
    }

    /**
     * Converts body of json response from  backend to instance of DirectResponse
     * @param {string} jsonResponse 
     * @returns {DirectRequest} Contains a solution to the direct geodetic task
     */
    responseToDirectResponse(jsonResponse) {

        let directResponse = new DirectResponse();

        // let response = JSON.parse(jsonResponse);

        directResponse.targetX = jsonResponse?.targetX;
        directResponse.targetY = jsonResponse?.targetY;
        directResponse.targetZ = jsonResponse?.targetZ;

        return directResponse;
    }

}
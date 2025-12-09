import { UnitsConverter } from './UnitsConverter.js';
import { DirectRequest } from '../../dto/DirectRequest.js';
import { DirectResponse } from '../../dto/DirectResponse.js';

/**
 * This class represents methods for converting 
 * models to dto and vice versa for Direct Task.
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

        directRequest.landmarkX = UnitsConverter.meterToMillimeter(direct?.landmarkX);
        directRequest.landmarkY = UnitsConverter.meterToMillimeter(direct?.landmarkY);
        directRequest.landmarkDirection = UnitsConverter.dmsToSecond(direct?.landmarkDirection);
        directRequest.baseX = UnitsConverter.meterToMillimeter(direct?.baseX);
        directRequest.baseY = UnitsConverter.meterToMillimeter(direct?.baseY);
        directRequest.baseZ = UnitsConverter.meterToMillimeter(direct?.baseZ);
        directRequest.baseHeight = UnitsConverter.meterToMillimeter(direct?.baseHeight);
        directRequest.targetDirection = UnitsConverter.dmsToSecond(direct?.targetDirection);
        directRequest.targetInclinedDistance = UnitsConverter.meterToMillimeter(direct?.targetInclinedDistance);
        directRequest.targetTiltAngle = UnitsConverter.dmsToSecond(direct?.targetTiltAngle);
        directRequest.targetHeight = UnitsConverter.meterToMillimeter(direct?.targetHeight);

        return JSON.stringify(directRequest);
    }

    /**
     * Converts body of json response from  backend to instance of DirectResponse
     * @param {string} jsonResponse 
     * @returns {DirectRequest} Contains a solution to the direct geodetic task
     */
    responseToDirectResponse(jsonResponse) {

        let directResponse = new DirectResponse();

        let response = JSON.parse(jsonResponse);

        directResponse.targetX = UnitsConverter.millimeterToMeter(response?.targetX);
        directResponse.targetY = UnitsConverter.millimeterToMeter(response?.targetY);
        directResponse.targetZ = UnitsConverter.millimeterToMeter(response?.targetZ);

        return directResponse;
    }

}
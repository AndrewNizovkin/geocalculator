import {InverseResponse} from '../../dto/InverseResponse.js';
import {InverseRequest} from '../../dto/InverseRequest.js';
import { UnitsConverter } from './UnitsConverter.js';

/**
 * This class represents methods for converting models to dto and vice versa.
 */
export class InverseMapper {
   

    /**
     * Creates body of Post-request to backend with data fo solve inverse task
     */
    inverseToRequest(inverse) {
        let inverseRequest = new InverseRequest();

        inverseRequest.baseX = UnitsConverter.meterToMillimeter(inverse?.baseX);
        inverseRequest.baseY = UnitsConverter.meterToMillimeter(inverse?.baseY);
        inverseRequest.baseZ = UnitsConverter.meterToMillimeter(inverse?.baseZ);
        inverseRequest.targetX = UnitsConverter.meterToMillimeter(inverse?.targetX);
        inverseRequest.targetY = UnitsConverter.meterToMillimeter(inverse?.targetY);
        inverseRequest.targetZ = UnitsConverter.meterToMillimeter(inverse?.targetZ);

        return JSON.stringify(inverseRequest);
    }

    /**
     * Converts body of json response from  backend to instance of InverseResponse
     * @param {string} jsonResponse json response from backend
     * @returns 
     */
    responseToInverseResponse(jsonResponse) {
        let inverseResponse = new InverseResponse();

        let response = JSON.parse(jsonResponse);

        inverseResponse.direction = UnitsConverter.secondToDms(response?.direction);
        inverseResponse.horDistance = UnitsConverter.millimeterToMeter(response?.horDistance);
        inverseResponse.inclinedDistance = UnitsConverter.millimeterToMeter(response?.inclinedDistance);
        inverseResponse.tiltAngle = UnitsConverter.secondToDms(response?.tiltAngle);
        inverseResponse.elevation = UnitsConverter.millimeterToMeter(response?.elevation);

        return inverseResponse;
    }

}
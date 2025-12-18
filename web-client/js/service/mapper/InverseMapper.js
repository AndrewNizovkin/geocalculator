import {InverseResponse} from '../../dto/InverseResponse.js';
import {InverseRequest} from '../../dto/InverseRequest.js';
import { UnitsConverter } from './UnitsConverter.js';

/**
 * This class represents methods for converting models to dto and vice versa.
 */
export class InverseMapper {
   

    /**
     * Creates body of Post-request to backend with data fo solve inverse task
     * @param {Inverse} inverse instance of Inverse
     */
    inverseToRequest(inverse) {
        let inverseRequest = new InverseRequest();

        inverseRequest.baseX = UnitsConverter.meterToMillimeter(inverse?.baseX);
        inverseRequest.baseY = UnitsConverter.meterToMillimeter(inverse?.baseY);
        inverseRequest.baseZ = UnitsConverter.meterToMillimeter(inverse?.baseZ);
        inverseRequest.targetX = UnitsConverter.meterToMillimeter(inverse?.targetX);
        inverseRequest.targetY = UnitsConverter.meterToMillimeter(inverse?.targetY);
        inverseRequest.targetZ = UnitsConverter.meterToMillimeter(inverse?.targetZ);

        return inverseRequest;
    }

    /**
     * Converts body of json response from  backend to instance of InverseResponse
     * @param {string} jsonResponse json response from backend
     * @returns {InverseResponse}  Contains a solution to the inverse geodetic task
     */
    responseToInverseResponse(jsonResponse) {

        let inverseResponse = new InverseResponse();

        inverseResponse.direction = UnitsConverter.secondToDms(jsonResponse?.direction);
        inverseResponse.horDistance = UnitsConverter.millimeterToMeter(jsonResponse?.horDistance);
        inverseResponse.inclinedDistance = UnitsConverter.millimeterToMeter(jsonResponse?.inclinedDistance);
        inverseResponse.tiltAngle = UnitsConverter.secondToDms(jsonResponse?.tiltAngle);
        inverseResponse.elevation = UnitsConverter.millimeterToMeter(jsonResponse?.elevation);

        return inverseResponse;
    }

}
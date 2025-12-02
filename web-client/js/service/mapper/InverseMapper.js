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

        return inverseRequest;
    }

}
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

    

}
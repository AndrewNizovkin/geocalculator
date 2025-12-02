import { InverseRequest } from "../../dto/InverseRequest";
import { InverseResponse } from "../../dto/InverseResponse";
import { UnitsConverter } from "./UnitsConverter";

/**
 * This class represents methods for converting models to dto and vice versa.
 */
export class inverseMapper {

    /**
     * Creates body of Post-request to backend with data fo solve inverse task
     * @param {Inverse} inverse instance of Inverse  
     * @returns {string} body of Post-request to backend with data fo solve inverse task
     */
    inverseToRequest(inverse) {
        let inverseRequest = new InverseRequest();

        inverseRequest.baseX = UnitsConverter.meterToMillimeter(inverse?.baseX);
        inverseRequest.baseY = UnitsConverter.meterToMillimeter(inverse?.baseY);
        inverseRequest.baseZ = UnitsConverter.meterToMillimeter(inverse?.baseZ);
        inverseRequest.targetX = UnitsConverter.meterToMillimeter(inverse.targetX);
        inverseRequest.targetY = UnitsConverter.meterToMillimeter(inverse.targetY);
        inverseRequest.targetZ = UnitsConverter.meterToMillimeter(inverse.targetZ);

        return JSON.stringify(inverseRequest);
    }

}
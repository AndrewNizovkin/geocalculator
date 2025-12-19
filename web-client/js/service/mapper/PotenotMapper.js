import { UnitsConverter } from './UnitsConverter.js';
import {PotenotRequest} from '../../dto/PotenotRequest.js';
import { PotenotResponse } from '../../dto/PotenotResponse.js';

/**
 *  * This class represents methods for converting 
 * models to dto and vice versa for Potenot Task.
 */
export class PotenotMapper {

    /**
     * Creates body of Post-request to backend with data 
     * for solve potenot task
     * @param {Potenot} potenot json of Array of instances of PotenotRequest
     * @returns {string} 
     */
    potenotToPotenotRequest(potenot) {
        let potenotRequests = new Array();

        let potenotRequest = new PotenotRequest();
        
        potenotRequest.pointX = UnitsConverter.meterToMillimeter(potenot?.firstX);
        potenotRequest.pointY = UnitsConverter.meterToMillimeter(potenot?.firstY);
        potenotRequest.direction = UnitsConverter.dmsToSecond(potenot?.firstDirection);
        potenotRequests.push(potenotRequest);

        potenotRequest = new PotenotRequest();
        potenotRequest.pointX = UnitsConverter.meterToMillimeter(potenot?.secondX);
        potenotRequest.pointY = UnitsConverter.meterToMillimeter(potenot?.secondY);
        potenotRequest.direction = UnitsConverter.dmsToSecond(potenot?.secondDirection);
        potenotRequests.push(potenotRequest);
        
        potenotRequest = new PotenotRequest();
        potenotRequest.pointX = UnitsConverter.meterToMillimeter(potenot?.thirdX);
        potenotRequest.pointY = UnitsConverter.meterToMillimeter(potenot?.thirdY);
        potenotRequest.direction = UnitsConverter.dmsToSecond(potenot?.thirdDirection);
        potenotRequests.push(potenotRequest);

        return potenotRequests;
    }


    /**
     * Converts body of json response from  backend to instance of PotenotResponse
     * @param {string} jsonResponse response from backend
     * @returns {PotenotResponse} Contains a solution to the potenot task
     */
    responseToPotenotResponse(response) {
        let potenotResponse = new PotenotResponse();

        // let response = JSON.parse(jsonResponse);

        potenotResponse.pointX = response?.pointX;
        potenotResponse.pointY = response?.pointY;

        return potenotResponse;
    }

}
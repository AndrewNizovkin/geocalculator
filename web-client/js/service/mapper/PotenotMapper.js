import { UnitsConverter } from './UnitsConverter.js';
import {PotenotRequest} from '../../dto/PotenotRequest.js';
import { PotenotResponse } from '../../dto/PotenotResponse.js';

/**
 * This class represents methods for converting 
 * models to dto and vice versa for Potenot Task.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
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
        
        potenotRequest.pointX = potenot?.firstX;
        potenotRequest.pointY = potenot?.firstY;
        potenotRequest.direction = potenot?.firstDirection;
        potenotRequests.push(potenotRequest);

        potenotRequest = new PotenotRequest();
        potenotRequest.pointX = potenot?.secondX;
        potenotRequest.pointY = potenot?.secondY;
        potenotRequest.direction = potenot?.secondDirection;
        potenotRequests.push(potenotRequest);
        
        potenotRequest = new PotenotRequest();
        potenotRequest.pointX = potenot?.thirdX;
        potenotRequest.pointY = potenot?.thirdY;
        potenotRequest.direction = potenot?.thirdDirection;
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
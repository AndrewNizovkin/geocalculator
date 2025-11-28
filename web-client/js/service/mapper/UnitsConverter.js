export class UnitsConverter {
    
    /***
     * Converts meters to millimeters
     * 
     * @param {string} value linear value in meters
     * @returns {number} linear value in millimeters
     */
    meterToMillimeter(value) {
        let result = +(value) * 1000;
        return result;
    }


    
}
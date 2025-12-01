export class UnitsConverter {
    
    /***
     * Converts meters to millimeters
     * 
     * @param {string} value linear value in meters
     * @returns {number} linear value in millimeters
     */
    static meterToMillimeter(value) {
        let result = +(value) * 1000;
        return result;
    }

    /**
     * Converts millimeters to meters 
     * @param {number} value linear value in millimeters
     * @returns {string} linear value in metters
     */
    static millimeterToMeter(value) {
        let result = +(value) / 1000; 
        return result.toFixed(3);
    }

    /**
     * Converts DMS to seconds
     * @param {string} value angular value in D.MMSS format
     * @returns {number} angular value in seconds
     */
    static dmsToSecond(value) {
        let numberValue = +value;
        let degee = Math.trunc(numberValue);

        numberValue = (numberValue - degee) * 100;
        let minute = Math.trunc(numberValue);
        
        let second = Math.round((numberValue - minute) * 100);
        return degee * 3600 + minute * 60 + second;
    }

    /**
     * Converts seconds to DMS
     * @param {number} value angular value in seconds
     * @returns {string} angular value in D.MMSS format
     */
    static secondToDms(value) {
    let numberValue = +(value);
    let signum = numberValue < 0 ? "-" : "";
    numberValue = Math.abs(numberValue);

    let degee = Math.trunc(numberValue / 3600);
    numberValue = numberValue - (3600 * degee);

    let minute = Math.trunc(numberValue / 60);
    numberValue = numberValue - (minute * 60);
    minute =  minute >= 10 ? minute : "0" + minute;

    let second = numberValue.toFixed(0);
    second = second >=10 ? second : "0" + second;

        return String(signum + degee + "." + minute + second);
    }    
    
}
/**
 * Checks whether the argument is a positive or negative decimal number.
 * @param {string} value 
 * @returns {boolean}
 */
function isValidDigitalNumber(value) {
    const regex = /^[-+]?(\d*\.)?\d+$/;
    return regex.test(value);
}

/**
 * Checks whether the argument is a horizontal angle 
 * in the range 0.0000-359.5959 in d.mmss format.
 * @param {string} value
 * @returns {boolean}
 */
function isValidHorizontalAngle(value) {
    if (!/^\d+\.\d\d\d\d$/.test(value)) {
        return false;
    }
    
    let parts = value.split('.');
    let degrees = parseInt(parts[0], 10);
    let minutes = parseInt(parts[1].slice(0, 2), 10);
    let seconds = parseInt(parts[1].slice(2), 10);

    if (
        degrees >= 0 && degrees <= 359 &&
        minutes >= 0 && minutes <= 59 &&
        seconds >= 0 && seconds <= 59
    ) {
        return true;
    }
    return false;
}

/**
 * Checks whether the argument is a tilt angle 
 * in the range 0- +-89.5959 in d.mmss format.
 * @param {string} value 
 * @returns {boolean}
 */
function isValidTiltAngle(value) {
    if (!/^[+-]?\d+\.\d\d\d\d$/.test(value)) {
        return false;
    }

    let parts = value.split('.');
    let degrees = Math.abs(parseInt(parts[0], 10));

    let minutes = parseInt(parts[1].substring(0, 2), 10);
    let seconds = parseInt(parts[1].substring(2), 10);

    if (
        degrees >= 0 && degrees <= 89 &&
        minutes >= 0 && minutes <= 59 &&
        seconds >= 0 && seconds <= 59
    ) {
        return true;
    }
    return false;
}
/**
 * This class contains static methods 
 * for verifying user-entered values.
 * @author Nizovkin A.V.
 * @copyright 2025 Nizovkin A.V.
 */
export class ValueValidator {

    /**
     * Checks whether the argument is a valid name.
     * @param {string} value 
     * @returns {boolean}
     */
    static isValidName(value) {
        const regex = /^[^#"'\/\s]{1,20}$/;
        return regex.test(value);
    }

    /**
     * Checks whether the argument is a positive or negative decimal number.
     * @param {string} value 
     * @returns {boolean}
     */
    static isValidDigitalNumber(value) {
        const regex = /^[-+]?(\d*\.)?\d+$/;
        return regex.test(value);
    }

    /**
     * Checks whether the argument is a positive decimal number.
     * @param {string} value 
     * @returns {boolean}
     */
    static isValidPositiveNumber(value) {
        const regex = /^(\d*\.)?\d+$/;
        return regex.test(value);
    }

    /**
     * Checks whether the argument is a horizontal angle 
     * in the range 0.0000-359.5959 in d.mmss format.
     * @param {string} value
     * @returns {boolean}
     */
    static isValidHorizontalAngle(value) {
        if (!/^\d+\.\d\d\d\d$/.test(value)) {
            return false;
        }
        
        let parts = value.split('.');
        let degrees = parseInt(parts[0], 10);
        let minutes = parseInt(parts[1].slice(0, 2), 10);
        let seconds = parseInt(parts[1].slice(2), 10);

        if (
            degrees >= 0 && degrees <= 359 &&
            minutes >= 0 && minutes <= 60 &&
            seconds >= 0 && seconds <= 60
        ) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the argument is a tilt angle 
     * in the range 0 - +-89.5959 in d.mmss format.
     * @param {string} value 
     * @returns {boolean}
     */
    static isValidTiltAngle(value) {
        if (!/^[+-]?\d+\.\d\d\d\d$/.test(value)) {
            return false;
        }

        let parts = value.split('.');
        let degrees = Math.abs(parseInt(parts[0], 10));

        let minutes = parseInt(parts[1].substring(0, 2), 10);
        let seconds = parseInt(parts[1].substring(2), 10);

        if (
            degrees >= 0 && degrees <= 89 &&
            minutes >= 0 && minutes <= 60 &&
            seconds >= 0 && seconds <= 60
        ) {
            return true;
        }
        return false;
    }

   /**
   * Verifies that the value of the element is a valid name 
   * and displays the result for the user.
   * @param {HTMLElement} element 
   */
  static checkName(element) {
    
    // if (element.value === '') {
    //   element.value = 'noname';
    // }

    // element.value = element.value.trim();

    if (ValueValidator.isValidName(element.value)) {
      if (element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    } else {
      if (!element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    }
  }
   
  /**
   * Verifies that the value of the element is a number 
   * and displays the result for the user.
   * @param {HTMLElement} element 
   */
  static checkNumber(element) {
    
    // if (element.value === '') {
    //   element.value = '0.000';
    // }
    // element.value = element.value.trim();

    if (ValueValidator.isValidDigitalNumber(element.value)) {
      let valueNumber = +element.value;
      element.value = valueNumber.toFixed(3);

      if (element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    } else {
      if (!element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    }
  }

   /**
   * Verifies that the value of the element is a positive number 
   * and displays the result for the user.
   * @param {HTMLElement} element 
   */
  static checkPositiveNumber(element) {
    
    // if (element.value === '') {
    //   element.value = '0.000';
    // }
    // element.value = element.value.trim();    
    if (ValueValidator.isValidPositiveNumber(element.value)) {
      let valueNumber = +element.value;
      element.value = valueNumber.toFixed(3);
      if (element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    } else {
      if (!element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    }
  }

   /**
   * Verifies that the value of the element is a hogizontal angle 
   * in the range 0.0000-359.5959 in d.mmss format 
   * and displays the result for the user.
   * @param {HTMLElement} element 
   */
  static checkHorisontalAngle(element) {
    // // if (element.value === '') {
    // //   element.value = '0.0000';
    // // }
    // element.value = element.value.trim();    

    // if (ValueValidator.isValidPositiveNumber(element.value)) {
    //   let valueNumber = +element.value;
    //   element.value = valueNumber.toFixed(4);
    // }
    
    if (ValueValidator.isValidHorizontalAngle(element.value)) {
      if (element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    } else {
      if (!element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    }
  }

   /**
   * Verifies that the value of the element is a tilt angle 
   * in the range 0 - +-89.5959 in d.mmss format.
   * and displays the result for the user.
   * @param {HTMLElement} element 
   */
  static checkTiltAngle(element) {
    // element.value = element.value.trim();    
    // if (element.value === '') {
    //   element.value = '0.0000';
    // }
    // if (ValueValidator.isValidDigitalNumber(element.value)) {
    //   let valueNumber = +element.value;
    //   element.value = valueNumber.toFixed(4);
    // }

    if (ValueValidator.isValidTiltAngle(element.value)) {
      if (element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    } else {
      if (!element.classList.contains('novalid')) {
        element.classList.toggle('novalid');
      }
    }
  }

}
import {Inverse} from '../model/Inverse.js';

/***
 * 
 */
export class InverseService {

    constructor() {
        this.inverse = new Inverse();
    }

    getBaseX() {
        return this.inverse.baseX;
    }

    getBaseY() {
        return this.inverse.baseY;
    }

    saveBaseX(value) {
        this.inverse.baseX = value;
    }

    saveBaseY(value) {
        this.inverse.baseY = value;
    }

}

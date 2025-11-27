import {Inverse} from '../model/inverse.js';

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

    setBaseX(value) {
        this.inverse.baseX = value;
    }

    setBaseY(value) {
        this.inverse.baseY = value;
    }

}

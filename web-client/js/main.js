import {InverseService} from './service/InverseService.js';
import { DirectService } from './service/DirectService.js';
// import {UnitsConverter } from './service/mapper/UnitsConverter.js';
// import { InverseMapper } from './service/mapper/InverseMapper.js';
// export {UnitsConverter};
// alert('Превет, создаю inverseService');

let inverseService = new InverseService();
// testInverseService();

let directService = new DirectService();

testDirectService();

/**
 * Tests directService
 */
function testDirectService() {
    alert(directService.solveDirectTask());

}

function showDirect() {
    alert(`
        
        `);
}


/**
 * Tests inverseService
 */
function testInverseService() {
    showInverse();

    setInverse('222')

    showInverse();

    inverseService.solveInverseTask();

    showInverse();

}

function showInverse() {
    alert(`baseX: ${inverseService.getBaseX()} \n
    baseY: ${inverseService.getBaseY()} \n
    baseZ: ${inverseService.getBaseZ()} \n
    targetX: ${inverseService.getTargetX()} \n
    targetY: ${inverseService.getTargetY()} \n
    targetZ: ${inverseService.getTargetZ()} \n
    direction: ${inverseService.getDirection()} \n
    horDistance: ${inverseService.getHorDistance()} \n
    inclinedDistande: ${inverseService.getInclinedDistance()} \n
    tiltAngle: ${inverseService.getTiltAngle()} \n
    elevation: ${inverseService.getElevation()}
    `);
}

function setInverse(value) {
    inverseService.saveBaseX(`${value}.000`);
    inverseService.saveBaseY(`${value}.000`);
    inverseService.saveBaseZ(`${value}.000`);
    inverseService.saveTargetX(`${value}.000`);
    inverseService.saveTargetY(`${value}.000`);
    inverseService.saveTargetZ(`${value}.000`);
    // inverseService.saveDirection(`${value}.0000`);
    // inverseService.saveHorDistance(`${value}.000`);
    // inverseService.saveInclinedDistance(`${value}.000`);
    // inverseService.saveTiltAngle(`-${value}.0000`);
    // inverseService.saveElevation(`${value}.000`);
}
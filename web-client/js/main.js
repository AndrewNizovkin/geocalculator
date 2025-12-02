import {InverseService} from './service/InverseService.js';
// import {UnitsConverter } from './service/mapper/UnitsConverter.js';
import { InverseMapper } from './service/mapper/InverseMapper.js';
// export {UnitsConverter};
// alert('Превет, создаю inverseService');

let inverseService = new InverseService();

showInverse();

setInverse('222')

showInverse();

// let inverseMapper = new InverseMapper();

let result = inverseService.solveInverseTask();

alert(`rezult = ${ result }`);

// alert(inverseService.solveInverseTask());
// alert(inverseService.solveInverseTask());

// showInverse();
// inverseService.setBaseX(2000);
// inverseService.setBaseY(3000);

// alert(`baseX: ${inverseService.getBaseX()} \nbaseY: ${inverseService.getBaseY()}`);

// let unitsConverter = new UnitsConverter();

// alert(`1000.999м. = ${unitsConverter.meterToMillimeter('1000.999')}`);

// alert(`100.99m. = ${UnitsConverter.meterToMillimeter("100.99")}mm.`);
// alert(`222333mm = ${UnitsConverter.millimeterToMeter(222333)}m.`);
// alert(`1.0000d.mmss = ${UnitsConverter.dmsToSecond("1.0000")}sek`);
// alert(`3600sek = ${UnitsConverter.secondToDms(3600)}d.mmss`);

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
    inverseService.saveDirection(`${value}.0000`);
    inverseService.saveHorDistance(`${value}.000`);
    inverseService.saveInclinedDistance(`${value}.000`);
    inverseService.saveTiltAngle(`-${value}.0000`);
    inverseService.saveElevation(`${value}.000`);
}
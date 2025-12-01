import {InverseService} from './service/InverseService.js';
// import { } from './service/mapper/UnitsConverter.js';
// import (UnitsConverter) from ''

// alert('Превет, создаю inverseService');

let inverseService = new InverseService();

getInverse();

inverseService.saveDirection('234.4545');

getInverse();

// inverseService.setBaseX(2000);
// inverseService.setBaseY(3000);

// alert(`baseX: ${inverseService.getBaseX()} \nbaseY: ${inverseService.getBaseY()}`);

// let unitsConverter = new UnitsConverter();

// alert(`1000.999м. = ${unitsConverter.meterToMillimeter('1000.999')}`);

// alert(`100.99m. = ${UnitsConverter.meterToMillimeter("100.99")}mm.`);
// alert(`222333mm = ${UnitsConverter.millimeterToMeter(222333)}m.`);
// alert(`1.0000d.mmss = ${UnitsConverter.dmsToSecond("1.0000")}sek`);
// alert(`3600sek = ${UnitsConverter.secondToDms(3600)}d.mmss`);

function getInverse() {
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
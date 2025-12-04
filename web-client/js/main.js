import {InverseService} from './service/InverseService.js';
import {DirectService} from './service/DirectService.js';
// import { Direct } from './model/Direct.js';
// import {UnitsConverter } from './service/mapper/UnitsConverter.js';
// import { InverseMapper } from './service/mapper/InverseMapper.js';
// export {UnitsConverter};
// alert('Превет, создаю inverseService');

let inverseService = new InverseService();
// testInverseService();

let directService = new DirectService();

// alert(`targetX: ${directService.getTargetX()} \n
// targetY: ${directService.getTargetY()} \n
// targetZ: ${directService.getTargetZ()}
// `);


testDirectService();




/**
 * Tests directService
 */
function testDirectService() {

    showDirect("начальные значения");
        
    setDemoDirect();

    showDirect("устанавливаем значения");

    let result = directService.solveDirectTask();

    let currentContent = document.getElementsByClassName('content');

    currentContent[0].innerHTML = currentContent[0].innerHTML + `<br>---directMapper.directToDirectRequest---<br>${result}`;

    }

function setDemoDirect() {
    directService.saveLandmarkX("200.000");
    directService.saveLandmarkY("200.100");
    directService.saveLandmarkDirection("45.4545");
    directService.saveBaseX("100.100");
    directService.saveBaseY("100.210");
    directService.saveBaseZ("100.300");
    directService.saveBaseHeight("1.589");
    directService.saveTargetDirection("275.5959");
    directService.saveTargetInclindeDistance("999.999");
    directService.saveTargetTiltAngle("-0.0343");
    directService.saveTargetHeight("1.600");
    directService.saveTargetX("150.100");
    directService.saveTargetY("150.200");
    directService.saveTargetZ("150.300");

}

function showDirect(message) {
    
    
    let content = document.getElementsByClassName('content');

    let page = `<br>${message}<br>----------<br>` + `
    landmarkX: ${directService.getLandmarkX()} <br>
    landmarkY: ${directService.getLandmarkY()} <br>
    landmarkDirection: ${directService.getLandmarkDirection()} <br>
    baseHeight: ${directService.getBaseHeight()}<br>
    targetDirection: ${directService.getTargetDirection()} <br>
    targetInclinedDistance: ${directService.getTargetInclinedDistance()} <br>
    targetTiltAngle: ${directService.getTargetTiltAngle()} <br>
    targetHeight: ${directService.getTargetHeight()} <br>
    baseX: ${directService.getBaseX()} <br>
    baseY: ${directService.getBaseY()} <br>
    baseZ: ${directService.getBaseZ()} <br>
    targetX: ${directService.getTargetX()} <br>
    targetY: ${directService.getTargetY()} <br>
    targetZ: ${directService.getTargetZ()} <br>

    `;
    // page = page + content[0].innerHTML;

    content[0].innerHTML = content[0].innerHTML + page;
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
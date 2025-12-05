import {InverseService} from './service/InverseService.js';
import {DirectService} from './service/DirectService.js';

let inverseService = new InverseService();

testInverseService();

let directService = new DirectService();

testDirectService();




/**
 * Tests directService
 */
function testDirectService() {

    showDirect("начальные значения direct");
        
    setDemoDirect();

    showDirect("устанавливаем демо значения direct");

    directService.solveDirectTask();

    showDirect("Решаем прямую геодезическую задачу");

    directService.clearAll();

    showDirect("Очищаем модель");



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

    let page = `<br><br>------------------<br>${message}<br>------------------<br>` + `
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
    showInverse("Начальное значение модели inverse");

    setInverse('222')

    showInverse("Устанавиваем демо значения модели");

    inverseService.solveInverseTask();

    showInverse("Решаем обратную задачу");

    inverseService.clearAll();

    showInverse("Очищаем inverse");

}

function showInverse(message) {

    let content = document.getElementsByClassName('content');

    let page = `<br><br>------------------<br>${message}<br>------------------<br>` + `
    baseX: ${inverseService.getBaseX()} <br>
    baseY: ${inverseService.getBaseY()} <br>
    baseZ: ${inverseService.getBaseZ()} <br>
    targetX: ${inverseService.getTargetX()} <br>
    targetY: ${inverseService.getTargetY()} <br>
    targetZ: ${inverseService.getTargetZ()} <br>
    direction: ${inverseService.getDirection()} <br>
    horDistance: ${inverseService.getHorDistance()} <br>
    inclinedDistande: ${inverseService.getInclinedDistance()} <br>
    tiltAngle: ${inverseService.getTiltAngle()} <br>
    elevation: ${inverseService.getElevation()} <br>
    `;

    content[0].innerHTML = content[0].innerHTML + page;

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
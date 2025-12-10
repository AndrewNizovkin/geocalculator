import {InverseService} from './service/InverseService.js';
import {DirectService} from './service/DirectService.js';
import { PotenotService } from './service/PotenotService.js';

let inverseService = new InverseService();

testInverseService();

let directService = new DirectService();

testDirectService();

let potenotService = new PotenotService();

testPotenotService();

/**
 * Tests PotenotService
 */
function testPotenotService() {

    showPotenot("Начальное значение модели 'potenot'");

    setDemoPotenot();

    showPotenot("Устанавливаем демо значения 'potenot'");

    potenotService.solvePotenotTask();

    showPotenot("Решаем обратную геодезическую засечку (Задача Потенота)");

    potenotService.clearAll();

    showPotenot("Инициализируем (очищаем) модель 'potenot'");
    
}

function addToContent(page) {
    let content = document.getElementsByClassName('content');
    content[0].innerHTML += page;    
}

function showPotenot(message) {

    let page = `<br><br>------------------<br>${message}<br>------------------<br>` + `
    firstX: ${potenotService.getFirstX()}<br>
    firstY: ${potenotService.getFirstY()}<br>
    firstDirection: ${potenotService.getFirstDirection()}<br>
    secondX: ${potenotService.getSecondX()}<br>
    secondY: ${potenotService.getSecondY()}<br>
    secondDirection: ${potenotService.getSecondDirection()}<br>
    thirdX: ${potenotService.getThirdX()}<br>
    thirdY: ${potenotService.getThirdY()}<br>
    thirdDirection: ${potenotService.getThirdDirection()}<br>
    baseX: ${potenotService.getBaseX()}<br>
    baseY: ${potenotService.getBaseY()}<br>
    `;
    addToContent(page);

}

function setDemoPotenot() {
    potenotService.saveFirstX('100000.100');
    potenotService.saveFirstY('100000.200');
    potenotService.saveFirstDirection('89.0001');
    potenotService.saveSecondX('200000.100');
    potenotService.saveSecondY('200000.200');
    potenotService.saveSecondDirection('220.2021');
    potenotService.saveThirdX('300000.100');
    potenotService.saveThirdY('300000.200');
    potenotService.saveThirdDirection('359.5959');
    potenotService.saveBaseX('0.001');
    potenotService.saveBaseY('0.002');

}





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
    addToContent(page);
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
    addToContent(page);
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
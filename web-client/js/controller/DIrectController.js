import {DirectService} from '../service/DirectService.js';

/**
 * 
 */
export class DirectController {
    constructor() {
        this.directService = new DirectService();
    }

    loadPageDirect() {
        let content = document.getElementById("content");
        content.innerHTML = `
      <div class="back-task toolbar">
        <div class="button clear" title="Очистить" id="direct-clear"></div>
        <div class="button run" title="Решить задачу" id="direct-run"></div>
      </div>

      <div class="panel-direct">

        <div class="panel-direct-basis">

          <div class="panel-title">Параметры базиса</div>

          <div class="frame">
            <div class="frame-label">Координаты базы</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="direct-base-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="direct-base-y" value="2333444.555"/></div>
            <div class="frame-value"><span>Z: </span><input type="text" class="menu-item" id="direct-base-z" value="2333444.555"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Координаты ориентира</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="direct-landmark-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="direct-landmark-y" value="2333444.555"/></div>
          </div>

        </div>

        <div class="panel-direct-measurement">

          <div class="panel-title">Измерения</div>

          <div class="frame">
            <div class="frame-label">Направление на ориентир</div>
            <div class="frame-value"><input type="text" class="menu-item" id="direct-landmark-direction" value="359.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Высота инструмента над точкой</div>
            <div class="frame-value"><input type="text" class="menu-item" id="direct-base-height" value="99.555"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Направление на цель</div>
            <div class="frame-value"><input type="text" class="menu-item" id="direct-target-direction" value="359.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Наклонное расстояние до цели</div>
            <div class="frame-value"><input type="text" class="menu-item" id="direct-target-distance" value="99.555"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Угол наклона</div>
            <div class="frame-value"><input type="text" class="menu-item" id="direct-target-tilt" value="-9.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Высота цели над определяемой точкой</div>
            <div class="frame-value"><input type="text" class="menu-item" id="direct-target-height" value="99.555"/></div>
          </div>

        </div>

        <div class="panel-direct-result">
          <div class="panel-title">Результаты обработки</div>
          <div class="frame">
            <div class="frame-label">Координаты цели</div>
            <div class="frame-value" id="direct-target-x">X: 2000000.000</div>
            <div class="frame-value" id="direct-target-y">Y: 2000000.000</div>
            <div class="frame-value" id="direct-target-z">Z: 2000.000</div>
          </div>

        </div>

      </div>
        
        `;

        this.setData();

        this.setResult();

        let buttonClear = document.getElementById("direct-clear");
        buttonClear.addEventListener('click', () => {
          this.directService.clearAll();
          this.setData();
          this.setResult();
        });

        let buttonRun = document.getElementById("direct-run");
        buttonRun.addEventListener('click', () => {
          this.directService.solveDirectTask();
          this.setResult();
        });

        
    }

    /**
     * Sets data of gui components from model
     */
    setData() {

        document.getElementById("direct-base-x").value = this.directService.getBaseX();

        document.getElementById("direct-base-y").value = this.directService.getBaseY();

        document.getElementById("direct-base-z").value = this.directService.getBaseZ();

        document.getElementById("direct-landmark-x").value = this.directService.getLandmarkX();

        document.getElementById("direct-landmark-y").value = this.directService.getLandmarkY();

        document.getElementById("direct-landmark-direction").value = this.directService.getLandmarkDirection();

        document.getElementById("direct-base-height").value = this.directService.getBaseHeight();

        document.getElementById("direct-target-direction").value = this.directService.getTargetDirection();

        document.getElementById("direct-target-distance").value = this.directService.getTargetInclinedDistance();

        document.getElementById("direct-target-tilt").value = this.directService.getTargetTiltAngle();

        document.getElementById("direct-target-height").value = this.directService.getTargetHeight();

    }

    /**
     * Sets result of gui components from model
     */
    setResult() {

      document.getElementById("direct-target-x").innerHTML = `X: ${this.directService.getTargetX()}`;

      document.getElementById("direct-target-y").innerHTML = `Y: ${this.directService.getTargetY()}`;

      document.getElementById("direct-target-z").innerHTML = `Z: ${this.directService.getTargetZ()}`;

    }
}
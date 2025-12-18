import {DirectService} from '../service/DirectService.js';

/**
 * 
 */
export class DirectController {
    constructor() {
        this.directService = new DirectService();
    }

    /**
     * Loads direct page to content of main page
     */
    loadPageDirect() {
        let content = document.getElementById("content");
        content.innerHTML = `
      <div class="toolbar" id="direct-toolbar">
        <div class="button clear" title="Очистить" id="direct-clear"></div>
        <div class="button run" title="Решить задачу" id="direct-run"></div>
      </div>

      <div class="panel-direct" id="direct-panel">

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

        document.getElementById("direct-toolbar").addEventListener('click', (event) => {

          switch(event.target.id) {

            case "direct-clear":
              this.directService.clearAll();
              this.setData();
              this.setResult();              
              break;

            case "direct-run":
              this.directService.solveDirectTask().then(() => this.setResult());
              break;
          }
        });

        document.getElementById("direct-panel").addEventListener('input', (event) => {
          let element = event.target;

          switch(element.id) {

            case "direct-base-x":
              this.directService.saveBaseX(element.value);
              break;

            case "direct-base-y":
              this.directService.saveBaseY(element.value);
              break;

            case "direct-base-z":
              this.directService.saveBaseZ(element.value);
              break;

            case "direct-landmark-x":
              this.directService.saveLandmarkX(element.value);
              break;

            case "direct-landmark-y":
              this.directService.saveLandmarkY(element.value);
              break;

            case "direct-landmark-direction":
              this.directService.saveLandmarkDirection(element.value);
              break;

            case "direct-base-height":
              this.directService.saveBaseHeight(element.value);
              break;

            case "direct-target-direction":
              this.directService.saveTargetDirection(element.value);
              break;

            case "direct-target-distance":
              this.directService.saveTargetInclindeDistance(element.value);
              break;

            case "direct-target-tilt":
              this.directService.saveTargetTiltAngle(element.value);
              break;

            case "direct-target-height":
              this.directService.saveTargetHeight(element.value);
              break;
          }
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
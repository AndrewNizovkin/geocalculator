import {InverseService} from '../service/InverseService.js';

/**
 * 
 */
export class InverseController {
    constructor() {
        this.inverseService = new InverseService();
    }

    /**
     * Loads page Inverse to panel content
     * sets listeners
     */
    loadPageInverse() {
        let content = document.getElementById("content");

        content.innerHTML = `
      <div class="toolbar" id="inverse-toolbar">
        <div class="button clear" id="inverse-clear" title="Очистить"></div>
        <div class="button run" id="inverse-run" title="Решить задачу"></div>
      </div>

      <div class="panel-inverse" id="inverse-panel">

        <div class="panel-inverse-base">

          <div class="panel-title">Координаты базы</div>

          <div class="frame">
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="inverse-base-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="inverse-base-y" value="2333444.555"/></div>
            <div class="frame-value"><span>Z: </span><input type="text" class="menu-item" id="inverse-base-z" value="2333.555"/></div>
          </div>

        </div>

        <div class="panel-inverse-target">

          <div class="panel-title">Координаты цели</div>

          <div class="frame">
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="inverse-target-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="inverse-target-y" value="2333444.555"/></div>
            <div class="frame-value"><span>Z: </span><input type="text" class="menu-item" id="inverse-target-z" value="2333.555"/></div>            
          </div>

        </div>

        <div class="panel-inverse-result">

          <div class="panel-title">Результаты обработки</div>

          <div class="frame">
            <div class="frame-label">Дирекционный угол</div>
            <div class="frame-value" id="inverse-direction">359.5959</div>
          </div>

          <div class="frame">
            <div class="frame-label">Горизонтальное проложение</div>
            <div class="frame-value" id="inverse-hor-distance">99.999</div>
          </div>

          <div class="frame">
            <div class="frame-label">Наклонное расстояние</div>
            <div class="frame-value" id="inverse-inc-distance">359.5959</div>
          </div>

          <div class="frame">
            <div class="frame-label">Угол наклона</div>
            <div class="frame-value" id="inverse-tilt">-9.5959</div>
          </div>

          <div class="frame">
            <div class="frame-label">Превышение цели над базой</div>
            <div class="frame-value" id="inverse-elevation">-99.999</div>
          </div>

        </div>
      </div>
        
        `;

        this.setData();

        this.setResult();

        document.getElementById("inverse-toolbar").addEventListener('click', (event) => {

          switch(event.target.id) {

            case "inverse-clear":
              this.inverseService.clearAll();
              this.setData();
              this.setResult();              
              break;

            case "inverse-run":
              this.inverseService.solveInverseTask();
              this.setResult();
              break;
          }
        });

        document.getElementById("inverse-panel").addEventListener('input', (event) => {
          let element = event.target;

          switch(element.id) {

            case "inverse-base-x":
              this.inverseService.saveBaseX(element.value);
              break;

            case "inverse-base-y":
              this.inverseService.saveBaseY(element.value);
              break;

            case "inverse-base-z":
              this.inverseService.saveBaseZ(element.value);
              break;

            case "inverse-target-x":
              this.inverseService.saveTargetX(element.value);
              break;

            case "inverse-target-y":
              this.inverseService.saveTargetY(element.value);
              break;

            case "inverse-target-z":
              this.inverseService.saveTargetZ(element.value);
              break;

          }
        });

    }

    /**
     * Sets data of gui components from model
     */
    setData() {

      document.getElementById("inverse-base-x").value = this.inverseService.getBaseX();

      document.getElementById("inverse-base-y").value = this.inverseService.getBaseY();

      document.getElementById("inverse-base-z").value = this.inverseService.getBaseZ();

      document.getElementById("inverse-target-x").value = this.inverseService.getTargetX();

      document.getElementById("inverse-target-y").value = this.inverseService.getTargetY();

      document.getElementById("inverse-target-z").value = this.inverseService.getTargetZ();

    }

    /**
     * Sets result of gui components from model
     */
    setResult() {

      document.getElementById("inverse-direction").innerHTML = this.inverseService.getDirection();

      document.getElementById("inverse-hor-distance").innerHTML = this.inverseService.getHorDistance();

      document.getElementById("inverse-inc-distance").innerHTML = this.inverseService.getInclinedDistance();

      document.getElementById("inverse-tilt").innerHTML = this.inverseService.getTiltAngle();

      document.getElementById("inverse-elevation").innerHTML = this.inverseService.getElevation();

    }

}
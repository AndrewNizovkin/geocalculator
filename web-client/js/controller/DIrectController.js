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
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="direct-landmakrX" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="direct-landmakrY" value="2333444.555"/></div>
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

        let buttonClear = document.getElementById("direct-clear");
        buttonClear.addEventListener('click', this.directService.clearAll());

        let buttonRun = document.getElementById("direct-run");
        buttonRun.addEventListener('click', this.directService.solveDirectTask());

        let inputBaseX = document.getElementById("direct-base-x");
        inputBaseX.value = this.directService.getBaseX();

        

    }

}
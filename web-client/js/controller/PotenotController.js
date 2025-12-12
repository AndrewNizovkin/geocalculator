import {PotenotService} from '../service/PotenotService.js';

/**
 * 
 */
export class PotenotController {
    constructor() {
        this.potenotService = new PotenotService();
    }

    /**
     * Loaded Potenot task page and adds listeners to components
     */
    loadPagePotenot() {
        let content  = document.getElementById("content");

        content.innerHTML = `
      <div class="toolbar">
        <div class="button clear" id="potenot-clear" title="Очистить"></div>
        <div class="button run" id="potenot-run" title="Решить задачу"></div>
      </div>

      <div class="panel-potenot">

        <div class="panel-potenot-basis">

          <div class="panel-title">Исходные данные</div>

          <div class="frame">
            <div class="frame-label">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="potenot-first-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="potenot-first-y" value="2333444.555"/></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" class="menu-item" id="potenot-first-direction" value="359.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="potenot-second-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="potenot-second-y" value="2333444.555"/></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" class="menu-item" id="potenot-second-direction" value="359.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" id="potenot-third-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" id="potenot-third-y" value="2333444.555"/></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" class="menu-item" id="potenot-third-direction" value="359.5959"/></div>
          </div>

        </div>

        <div class="panel-potenot-result">

          <div class="panel-title">Результаты обработки</div>

          <div class="frame">
            <div class="frame-label">Координаты определяемой точки</div>
            <div id="potenot-base-x" class="frame-value">X: 2333444.555</div>
            <div id="potenot-base-y" class="frame-value">Y: 2333444.555</div>
          </div>

        </div>

      </div>

        
        `;
        
        this.setData();

        this.setResult();

        let buttonClear = document.getElementById("potenot-clear");
        buttonClear.addEventListener('click', () => {
          this.potenotService.clearAll();
          this.setData();
          this.setResult();
        });

        let buttonRun = document.getElementById("potenot-run");
        buttonRun.addEventListener('click', () => {
          this.potenotService.solvePotenotTask();
          this.setResult();
        });

    }

    /**
     * Sets data of gui components from model
     */
    setData() {

      document.getElementById("potenot-first-x").value = this.potenotService.getFirstX();

      document.getElementById("potenot-first-y").value = this.potenotService.getFirstY();

      document.getElementById("potenot-first-direction").value = this.potenotService.getFirstDirection();

      document.getElementById("potenot-second-x").value = this.potenotService.getSecondX();

      document.getElementById("potenot-second-y").value = this.potenotService.getSecondY();

      document.getElementById("potenot-second-direction").value = this.potenotService.getSecondDirection();

      document.getElementById("potenot-third-x").value = this.potenotService.getThirdX();

      document.getElementById("potenot-third-y").value = this.potenotService.getThirdY();

      document.getElementById("potenot-third-direction").value = this.potenotService.getThirdDirection();
    }

    /**
     * Sets result of gui components from model
     */
    setResult() {

      document.getElementById("potenot-base-x").innerHTML = `X: ${this.potenotService.getBaseX()}`;

      document.getElementById("potenot-base-y").innerHTML = `X: ${this.potenotService.getBaseY()}`;

    }

}
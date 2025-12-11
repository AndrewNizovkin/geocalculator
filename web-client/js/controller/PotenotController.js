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
        <div class="button clear" title="Очистить"></div>
        <div class="button run" title="Решить задачу"></div>
      </div>

      <div class="panel-potenot">

        <div class="panel-potenot-basis">

          <div class="panel-title">Исходные данные</div>

          <div class="frame">
            <div class="frame-label">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" name="potenot-first-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" name="potenot-first-y" value="2333444.555"/></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" class="menu-item" name="potenot-first-direction" value="359.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" name="potenot-second-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" name="potenot-second-y" value="2333444.555"/></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" class="menu-item" name="potenot-second-direction" value="359.5959"/></div>
          </div>

          <div class="frame">
            <div class="frame-label">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" name="potenot-third-x" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" name="potenot-third-y" value="2333444.555"/></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" class="menu-item" name="potenot-third-direction" value="359.5959"/></div>
          </div>

        </div>

        <div class="panel-potenot-result">

          <div class="panel-title">Результаты обработки</div>

          <div class="frame">
            <div class="frame-label">Координаты определяемой точки</div>
            <div id="targetX" class="frame-value"><span>X: </span>2333444.555</div>
            <div id="targetY" class="frame-value"><span>Y: </span>2333444.555</div>
          </div>

        </div>

      </div>
        
        `;

    }

}
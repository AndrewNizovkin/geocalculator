import {InverseService} from '../service/InverseService.js';

/**
 * 
 */
export class InverseController {
    constructor() {
        this.inverseService = new InverseService();
    }

    /**
     * Loaded page Inverse to panel content
     * sets listeners
     */
    loadPageInverse() {
        let content = document.getElementById("content");

        content.innerHTML = `
      <div class="toolbar">
        <div class="button clear" title="Очистить"></div>
        <div class="button run" title="Решить задачу"></div>
      </div>

      <div class="panel-inverse">

        <div class="panel-inverse-base">

          <div class="panel-title">Координаты базы</div>

          <div class="frame">
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" name="baseX" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" name="baseY" value="2333444.555"/></div>
            <div class="frame-value"><span>Z: </span><input type="text" class="menu-item" name="baseZ" value="2333.555"/></div>
          </div>

        </div>

        <div class="panel-inverse-target">

          <div class="panel-title">Координаты цели</div>

          <div class="frame">
            <div class="frame-value"><span>X: </span><input type="text" class="menu-item" name="targetX" value="2333444.555"/></div>
            <div class="frame-value"><span>Y: </span><input type="text" class="menu-item" name="targetY" value="2333444.555"/></div>
            <div class="frame-value"><span>Z: </span><input type="text" class="menu-item" name="targetZ" value="2333.555"/></div>            
          </div>

        </div>

        <div class="panel-inverse-result">

          <div class="panel-title">Результаты обработки</div>

          <div class="frame">
            <div class="frame-label">Дирекционный угол</div>
            <div class="frame-value">359.5959</div>
          </div>

          <div class="frame">
            <div class="frame-label">Горизонтальное проложение</div>
            <div class="frame-value">99.999</div>
          </div>

          <div class="frame">
            <div class="frame-label">Наклонное расстояние</div>
            <div class="frame-value">359.5959</div>
          </div>

          <div class="frame">
            <div class="frame-label">Угол наклона</div>
            <div class="frame-value">-9.5959</div>
          </div>

          <div class="frame">
            <div class="frame-label">Превышение цели над базой</div>
            <div class="frame-value">-99.999</div>
          </div>

        </div>

      </div>
        
        `;
    }

}
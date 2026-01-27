import {BasePointService} from "../service/BasePointService.js";

/**
 * Provides methods for managing the DOM elements 
 * of the 'Points' screen
 */
export class BasePointController {
    constructor() {
        this.basePointService = new BasePointService();
        this.currentBasePoint = 0;
    }

    /**
     * Loads basePoints page to content of main page
     */
    loadPageBasePoints() {
        let content = document.getElementById("content");

        content.innerHTML = `
      <div class="toolbar" id="points-toolbar">
        <div class="button-points" id="points-clear" title="Удалить все точки">Удалить все</div>
        <div class="button-points" id="points-remove" title="Удалить все точки">Удалить выбранную</div>
        <div class="button-points" id="points-add" title="Добавить новую точку">Добавить новую</div>
        <div class="button-points" id="points-file" title="Добавить из файла">Добавить из файла</div>
      </div>

      <div class="panel-points" id="panel-points">
        <div class="scrollpanel-points">
          <table class="table-points">
            <thead>
              <th class="menu-item">Название</th>
              <th class="menu-item">X</th>
              <th class="menu-item">Y</th>
              <th class="menu-item">Z</th>
            </thead>
            <tbody id="list-points">
              <!-- Демо данные -->
              <tr>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-name" size="12" value="1304"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-x" size="12" value="478959.197"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-y" size="12" value="2296938.168"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-z" size="12" value="11.426"/></td>
              </tr>

              <tr>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-name" size="12" value="1303"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-x" size="12" value="478959.197"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-y" size="12" value="2296938.168"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-z" size="12" value="11.426"/></td>
              </tr>

              <tr>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-name" size="12" value="1302"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-x" size="12" value="478685.352"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-y" size="12" value="2296938.168"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-z" size="12" value="11.426"/></td>
              </tr>

              <tr>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-name" size="12" value="1301"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-x" size="12" value="478676.113"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-y" size="12" value="2296967.264"/></td>
                <td><input type="text" class="menu-item" data-point-id="0" data-target="point-z" size="12" value="11.220"/></td>
              </tr>
              <!-- Демо данные -->
            </tbody>

          </table>            
        </div>


      </div>
        
        `;

    }

}
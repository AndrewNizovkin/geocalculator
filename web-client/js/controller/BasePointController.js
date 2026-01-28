import {BasePointService} from "../service/BasePointService.js";

/**
 * Provides methods for managing the DOM elements 
 * of the 'Points' screen
 */
export class BasePointController {
    constructor() {
        this.basePointService = new BasePointService();
        this.basePointService.addNewBasePoint();
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
        <div class="button-points" id="points-file" title="Добавить из файла">
          <input type="file" id="points-open-input" accept=".kat">
          Добавить из файла
        </div>
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
            </tbody>

          </table>            
        </div>


      </div>

        
        `;

        this.setTableBasePoints();
        this.addListenersTablePoints();
        this.addListenersToolbarPoints();

    }

    /**
     * Displays the contents of the base point repository in the DOM
     */
    setTableBasePoints() {
      if (this.basePointService.size() > 0) {
        let tableBasePoints = document.getElementById("list-points");
        tableBasePoints.innerHTML = '';
        
        for (let i = 0; i < this.basePointService.size(); i++) {
          let row = this.getElementBasePoint(i);
          tableBasePoints.append(row);
        }
      }
      
    }

    /**
     * Adds evert listeners to panel-points
     */
    addListenersTablePoints() {
      const panelPoints = document.getElementById("panel-points");

      panelPoints.addEventListener('click', (event) => {
        let element = event.target;

        if (element.hasAttribute("data-point-id")) {
          this.currentBasePoint = +element.dataset.pointId;
        }
      });

      panelPoints.addEventListener('input', (event) => {
        let element = event.target;

        if (element.hasAttribute("data-target")) {
          switch (element.dataset.target) {

            case "point-name":
              this.basePointService.saveBasePointName(
                +element.dataset.pointId, 
                element.value)
              break;

            case "point-x":
              this.basePointService.saveBasePointX(
                +element.dataset.pointId, 
                element.value)
              break;

            case "point-y":
              this.basePointService.saveBasePointY(
                +element.dataset.pointId, 
                element.value)
              break;

            case "point-z":
              this.basePointService.saveBasePointZ(
                +element.dataset.pointId, 
                element.value)
              break;

            }
        }

      });
    }

    /**
     * Adds event listeners to points toolbar
     */
    addListenersToolbarPoints() {
      const tolbarPoints = document.getElementById("points-toolbar");

      tolbarPoints.addEventListener('click', (event) => {
        let element = event.target;

        switch (element.id) {

          case "points-clear":
            this.basePointService.clearAll();
            this.basePointService.addNewBasePoint();
            this.currentBasePoint = 0;
            this.setTableBasePoints();
            break;

          case "points-remove":
            if (this.basePointService.size() > 1) {
              this.basePointService.removeBasePoint(this.currentBasePoint);
              if (this.currentBasePoint == this.basePointService.size()) {
                this.currentBasePoint--;
              }
              this.setTableBasePoints();
            }
            break;

          case "points-add":
            this.basePointService.addNewBasePoint();
            this.currentBasePoint = this.basePointService.size() - 1;
            this.setTableBasePoints();
            break;

          case "points-file":
            document.getElementById("points-open-input").click();
            break;
        }
      });

      tolbarPoints.addEventListener('change', (event) => {
        let element = event.target;

        switch (element.id) {

          case "points-open-input":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.basePointService.addFromTextFile(file).then(() => {
                this.setTableBasePoints();
                this.currentBasePoint = 0;
                });
             } catch (error) {
              console.error(error.message);
            }
            break;

        }
      });
      
    }

    /**
     * Gets row of the base station table
     * @param {number} pointIndex 
     * @returns {HTMLElement}
     */
    getElementBasePoint(pointIndex) {
      let row = document.createElement('tr');

      let cell = document.createElement('td');
      let item = document.createElement('input');
      item.type = "text";
      item.className = "menu-item";
      item.setAttribute("data-point-id", pointIndex);
      item.setAttribute("data-target", "point-name");
      item.size = "12";
      item.value = this.basePointService.getBasePointName(pointIndex);
      cell.append(item);
      row.append(cell);

      cell = document.createElement('td');
      item = document.createElement('input');
      item.type = "text";
      item.className = "menu-item";
      item.setAttribute("data-point-id", pointIndex);
      item.setAttribute("data-target", "point-x");
      item.size = "12";
      item.value = this.basePointService.getBasePointX(pointIndex);
      cell.append(item);
      row.append(cell);

      cell = document.createElement('td');
      item = document.createElement('input');
      item.type = "text";
      item.className = "menu-item";
      item.setAttribute("data-point-id", pointIndex);
      item.setAttribute("data-target", "point-y");
      item.size = "12";
      item.value = this.basePointService.getBasePointY(pointIndex);
      cell.append(item);
      row.append(cell);

      cell = document.createElement('td');
      item = document.createElement('input');
      item.type = "text";
      item.className = "menu-item";
      item.setAttribute("data-point-id", pointIndex);
      item.setAttribute("data-target", "point-z");
      item.size = "12";
      item.value = this.basePointService.getBasePointZ(pointIndex);
      cell.append(item);
      row.append(cell);

      return row;
    }

}
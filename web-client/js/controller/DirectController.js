import {DirectService} from '../service/DirectService.js';

/**
 * Displays the Direct Geodetic Task screen and 
 * adds event handlers for its components.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class DirectController {
  #directService;
  #basePointService;
  #insertCoordinateToBase;

  /**
   * @constructor
   * @param {BasePointService} basePointService 
   */
  constructor(basePointService) {
      this.#directService = new DirectService();
      this.#basePointService = basePointService;
      this.#insertCoordinateToBase = true;
  }

  /**
   * Loads direct page to content of main page
   */
  loadPageDirect() {
      let content = document.getElementById("content");
      content.innerHTML = `
    <div class="toolbar" id="direct-toolbar">
      <div class="button clear" title="Очистить" id="direct-clear">Очистить</div>
      <div class="button run" title="Решить задачу" id="direct-run">Обработать</div>
    </div>

    <div class="panel-direct" id="direct-panel">

      <div class="panel-direct-basis">

        <div class="panel-title">Параметры базиса</div>

        <div class="frame">
          <div class="frame-label menu-item toggle" id="direct-base-button">Координаты базы</div>
          <div class="frame-value"><span>X: </span><input type="text" id="direct-base-x"></div>
          <div class="frame-value"><span>Y: </span><input type="text" id="direct-base-y"></div>
          <div class="frame-value"><span>Z: </span><input type="text" id="direct-base-z"></div>
        </div>

        <div class="frame">
          <div class="frame-label menu-item toggle" id="direct-landmark-button">Координаты ориентира</div>
          <div class="frame-value"><span>X: </span><input type="text" id="direct-landmark-x"></div>
          <div class="frame-value"><span>Y: </span><input type="text" id="direct-landmark-y"></div>
        </div>

      </div>

      <div class="panel-direct-measurement">

        <div class="panel-title">Измерения</div>

        <div class="frame">
          <div class="frame-label">Направление на ориентир</div>
          <div class="frame-value"><input type="text" id="direct-landmark-direction"></div>
        </div>

        <div class="frame">
          <div class="frame-label">Высота инструмента над точкой</div>
          <div class="frame-value"><input type="text" id="direct-base-height"></div>
        </div>

        <div class="frame">
          <div class="frame-label">Направление на цель</div>
          <div class="frame-value"><input type="text" id="direct-target-direction"></div>
        </div>

        <div class="frame">
          <div class="frame-label">Наклонное расстояние до цели</div>
          <div class="frame-value"><input type="text" id="direct-target-distance"></div>
        </div>

        <div class="frame">
          <div class="frame-label">Угол наклона</div>
          <div class="frame-value"><input type="text" id="direct-target-tilt"></div>
        </div>

        <div class="frame">
          <div class="frame-label">Высота цели над определяемой точкой</div>
          <div class="frame-value"><input type="text" id="direct-target-height"></div>
        </div>

      </div>

      <div class="panel-direct-result">
        <div class="panel-title">Результаты обработки</div>
        <div class="frame">
          <div class="frame-label">Координаты цели</div>
          <div class="frame-value" id="direct-target-x">X: </div>
          <div class="frame-value" id="direct-target-y">Y: </div>
          <div class="frame-value" id="direct-target-z">Z: </div>
        </div>

      </div>

    </div>

      
      `;

      this.#setData();
      this.#setResult();
      this.#setListBasePoints();
      this.#addToolbarListeners();
      this.#addDirectPanelListeners();
      
      
  }

  /**
   * Adds event listeners for direct-toolbar
   */
  #addToolbarListeners() {
      document.getElementById("direct-toolbar").addEventListener('click', (event) => {

        switch(event.target.id) {

          case "direct-clear":
            this.#directService.clearAll();
            this.#setData();
            this.#setResult();              
            break;

          case "direct-run":
            this.#directService.solveDirectTask().then(() => this.#setResult());
            break;
        }
      });

  }

  /**
   * Adds event listeners for direct-panel
   */
  #addDirectPanelListeners() {
    let panelDirect = document.getElementById("direct-panel");

    panelDirect.addEventListener('click', (event) => {
      let element = event.target;
      let toggleRect = element.getBoundingClientRect(); 
      let panelDirectRect = panelDirect.getBoundingClientRect();
      let listBasePoints = document.getElementById("list-base-point");      
      let overlay = document.getElementById("overlay");

      if (element.hasAttribute("data-base-point-id")) {

        listBasePoints.classList.toggle("open");
        overlay.classList.toggle("open");

        let basePointId = +element.dataset.basePointId;

        if (this.#insertCoordinateToBase) {
          this.#directService.saveBaseX(this.#basePointService.getBasePointX(basePointId));
          this.#directService.saveBaseY(this.#basePointService.getBasePointY(basePointId));
          this.#directService.saveBaseZ(this.#basePointService.getBasePointZ(basePointId));
        } else {
          this.#directService.saveLandmarkX(this.#basePointService.getBasePointX(basePointId));
          this.#directService.saveLandmarkY(this.#basePointService.getBasePointY(basePointId));
        }

        this.#setData();

    }
      

      switch (element.id) {

        case "direct-base-button":
          listBasePoints.style.top = `${toggleRect.top - panelDirectRect.top +toggleRect.height + window.scrollY}px`;
          listBasePoints.style.left = `${toggleRect.left - panelDirectRect.left + window.scrollX}px`;
          listBasePoints.classList.toggle("open");
          overlay.classList.toggle("open");
          this.#insertCoordinateToBase = true;
          break;

        case "direct-landmark-button":
          listBasePoints.style.top = `${toggleRect.top - panelDirectRect.top +toggleRect.height + window.scrollY}px`;
          listBasePoints.style.left = `${toggleRect.left - panelDirectRect.left + window.scrollX}px`;
          listBasePoints.classList.toggle("open");
          overlay.classList.toggle("open");
          this.#insertCoordinateToBase = false;
          break;
      }
      
    });        

    panelDirect.addEventListener('change', (event) => {
      let element = event.target;

      switch(element.id) {

        case "direct-base-x":
          this.#directService.saveBaseX(element.value);
          break;

        case "direct-base-y":
          this.#directService.saveBaseY(element.value);
          break;

        case "direct-base-z":
          this.#directService.saveBaseZ(element.value);
          break;

        case "direct-landmark-x":
          this.#directService.saveLandmarkX(element.value);
          break;

        case "direct-landmark-y":
          this.#directService.saveLandmarkY(element.value);
          break;

        case "direct-landmark-direction":
          this.#directService.saveLandmarkDirection(element.value);
          break;

        case "direct-base-height":
          this.#directService.saveBaseHeight(element.value);
          break;

        case "direct-target-direction":
          this.#directService.saveTargetDirection(element.value);
          break;

        case "direct-target-distance":
          this.#directService.saveTargetInclindeDistance(element.value);
          break;

        case "direct-target-tilt":
          this.#directService.saveTargetTiltAngle(element.value);
          break;

        case "direct-target-height":
          this.#directService.saveTargetHeight(element.value);
          break;
      }
    });

  }

  /**
   * Sets data of gui components from model
   */
  #setData() {

      document.getElementById("direct-base-x").value = this.#directService.getBaseX();

      document.getElementById("direct-base-y").value = this.#directService.getBaseY();

      document.getElementById("direct-base-z").value = this.#directService.getBaseZ();

      document.getElementById("direct-landmark-x").value = this.#directService.getLandmarkX();

      document.getElementById("direct-landmark-y").value = this.#directService.getLandmarkY();

      document.getElementById("direct-landmark-direction").value = this.#directService.getLandmarkDirection();

      document.getElementById("direct-base-height").value = this.#directService.getBaseHeight();

      document.getElementById("direct-target-direction").value = this.#directService.getTargetDirection();

      document.getElementById("direct-target-distance").value = this.#directService.getTargetInclinedDistance();

      document.getElementById("direct-target-tilt").value = this.#directService.getTargetTiltAngle();

      document.getElementById("direct-target-height").value = this.#directService.getTargetHeight();

  }

  /**
   * Sets result of gui components from model
   */
  #setResult() {

    document.getElementById("direct-target-x").innerHTML = `X: ${this.#directService.getTargetX()}`;

    document.getElementById("direct-target-y").innerHTML = `Y: ${this.#directService.getTargetY()}`;

    document.getElementById("direct-target-z").innerHTML = `Z: ${this.#directService.getTargetZ()}`;

  }

  /**
   * Creates and adds a list of base stations to the DOM
   */
  #setListBasePoints() {
    const panelDirect = document.getElementById("direct-panel");
    const listBasePoints = document.createElement('div');

    listBasePoints.className = "pop-up";
    listBasePoints.id = "list-base-point";

    if (this.#basePointService.size() > 0) {
      for (let i = 0; i < this.#basePointService.size(); i++) {
        let row = document.createElement('div');
        row.className = "menu-item";
        row.setAttribute("data-base-point-id", i);
        row.innerHTML = this.#basePointService.getBasePointName(i);
        listBasePoints.append(row);
      }
    }
    panelDirect.append(listBasePoints);
  }

}
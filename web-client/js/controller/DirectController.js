import {DirectService} from '../service/DirectService.js';
import {ValueValidator} from './ValueValidator.js';

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
  #resultActual;

  /**
   * @constructor
   * @param {BasePointService} basePointService 
   */
  constructor(basePointService) {
      this.#directService = new DirectService();
      this.#basePointService = basePointService;
      this.#insertCoordinateToBase = true;
      this.#resultActual = false;
  }

  /**
   * Loads direct page to content of main page
   */
  loadPageDirect() {
      let content = document.getElementById("content");
      content.innerHTML = `
        <div class="toolbar panel" id="direct-toolbar">
          <a href="#" class="button clear" title="Очистить" id="direct-clear">Очистить</a>
          <a href="#" class="button run" title="Решить задачу" id="direct-run">Обработать</a>
        </div>

        <div class="panel" id="direct-panel">

          <div class="panel" id="panel-direct-basis">

            <div class="panel-title">Параметры базиса</div>

            <div class="frame">
              <a href="#" class="frame-label menu-item" title="Вставить из каталога" id="direct-base-button">Координаты базы</a>
              <div class="frame-value"><span>X: </span><input type="text" id="direct-base-x"></div>
              <div class="frame-value"><span>Y: </span><input type="text" id="direct-base-y"></div>
              <div class="frame-value"><span>Z: </span><input type="text" id="direct-base-z"></div>
            </div>

            <div class="frame">
              <a href="#" class="frame-label menu-item" title="Вставить из каталога" id="direct-landmark-button">Координаты ориентира</a>
              <div class="frame-value"><span>X: </span><input type="text" id="direct-landmark-x"></div>
              <div class="frame-value"><span>Y: </span><input type="text" id="direct-landmark-y"></div>
            </div>

          </div>

          <div class="panel" id="panel-direct-measurement">

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

          <div class="panel" id="panel-direct-result">
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
            if (this.#isValidData()) {
              this.#directService.solveDirectTask().then(() => {
                this.#setResult()
                this.#resultActual = true;
              });
            } else {
              // this.#directService.clearRezults();
              // this.#setResult();
              alert("Данные содержат ошибки");
            }
            
            break;
        }
      });

  }

  /**
   * Adds event listeners for direct-panel
   */
  #addDirectPanelListeners() {
    let panelDirect = document.getElementById("direct-panel");

    panelDirect.addEventListener('input', () => {
      if (this.#resultActual) {
        this.#directService.clearRezults();
        this.#setResult();
        this.#resultActual = false;
      }
    });

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
          document.getElementById("direct-base-button").classList.toggle("toggle");

          this.#directService.saveBaseX(this.#basePointService.getBasePointX(basePointId));
          this.#directService.saveBaseY(this.#basePointService.getBasePointY(basePointId));
          this.#directService.saveBaseZ(this.#basePointService.getBasePointZ(basePointId));

        } else {
          document.getElementById("direct-landmark-button").classList.toggle("toggle");

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
          element.classList.toggle("toggle");
          break;

        case "direct-landmark-button":
          listBasePoints.style.top = `${toggleRect.top - panelDirectRect.top +toggleRect.height + window.scrollY}px`;
          listBasePoints.style.left = `${toggleRect.left - panelDirectRect.left + window.scrollX}px`;
          listBasePoints.classList.toggle("open");
          overlay.classList.toggle("open");
          this.#insertCoordinateToBase = false;
          element.classList.toggle("toggle");
          break;
      }
      
    });        

    panelDirect.addEventListener('change', (event) => {
      let element = event.target;

      switch(element.id) {

        case "direct-base-x":
          ValueValidator.checkNumber(element);
          this.#directService.saveBaseX(element.value);
          break;

        case "direct-base-y":
          ValueValidator.checkNumber(element);
          this.#directService.saveBaseY(element.value);
          break;

        case "direct-base-z":
          ValueValidator.checkNumber(element);
          this.#directService.saveBaseZ(element.value);
          break;

        case "direct-landmark-x":
          ValueValidator.checkNumber(element);
          this.#directService.saveLandmarkX(element.value);
          break;

        case "direct-landmark-y":
          ValueValidator.checkNumber(element);
          this.#directService.saveLandmarkY(element.value);
          break;

        case "direct-landmark-direction":
          ValueValidator.checkHorisontalAngle(element);
          this.#directService.saveLandmarkDirection(element.value);
          break;

        case "direct-base-height":
          ValueValidator.checkNumber(element);
          this.#directService.saveBaseHeight(element.value);
          break;

        case "direct-target-direction":
          ValueValidator.checkHorisontalAngle(element);
          this.#directService.saveTargetDirection(element.value);
          break;

        case "direct-target-distance":
          ValueValidator.checkPositiveNumber(element);
          this.#directService.saveTargetInclindeDistance(element.value);
          break;

        case "direct-target-tilt":
          ValueValidator.checkTiltAngle(element);
          this.#directService.saveTargetTiltAngle(element.value);
          break;

        case "direct-target-height":
          ValueValidator.checkNumber(element);
          this.#directService.saveTargetHeight(element.value);
          break;
      }
    });

  }

  /**
   * Sets data of gui components from model
   */
  #setData() {
    let element = document.getElementById("direct-base-x");
    element.value = this.#directService.getBaseX();
    ValueValidator.checkNumber(element);

    element = document.getElementById("direct-base-y");
    element.value = this.#directService.getBaseY();
    ValueValidator.checkNumber(element);

    element = document.getElementById("direct-base-z");
    element.value = this.#directService.getBaseZ();
    ValueValidator.checkNumber(element);
    
    element = document.getElementById("direct-landmark-x");
    element.value = this.#directService.getLandmarkX();
    ValueValidator.checkNumber(element);
    
    element = document.getElementById("direct-landmark-y");
    element.value = this.#directService.getLandmarkY();
    ValueValidator.checkNumber(element);
    
    element = document.getElementById("direct-landmark-direction");
    element.value = this.#directService.getLandmarkDirection();
    ValueValidator.checkHorisontalAngle(element);

    element = document.getElementById("direct-base-height");
    element.value = this.#directService.getBaseHeight();
    ValueValidator.checkNumber(element);
    
    element = document.getElementById("direct-target-direction");
    element.value = this.#directService.getTargetDirection();
    ValueValidator.checkHorisontalAngle(element);

    element = document.getElementById("direct-target-distance");
    element.value = this.#directService.getTargetInclinedDistance();
    ValueValidator.checkPositiveNumber(element);

    element = document.getElementById("direct-target-tilt");
    element.value = this.#directService.getTargetTiltAngle();
    ValueValidator.checkTiltAngle(element);

    element = document.getElementById("direct-target-height");
    element.value = this.#directService.getTargetHeight();
    ValueValidator.checkNumber(element);
    
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
    const panelDirectBasis = document.getElementById("panel-direct-basis");
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
    panelDirectBasis.append(listBasePoints);
  }

  /**
   * Verifies the validity of data for solving a direct geodetic task
   * @returns {boolean}
   */
  #isValidData() {
    let rezult = true;
    if (
      !ValueValidator.isValidDigitalNumber(this.#directService.getLandmarkX()) ||
      !ValueValidator.isValidDigitalNumber(this.#directService.getLandmarkY()) ||
      !ValueValidator.isValidHorizontalAngle(this.#directService.getLandmarkDirection()) ||
      !ValueValidator.isValidDigitalNumber(this.#directService.getBaseX()) ||
      !ValueValidator.isValidDigitalNumber(this.#directService.getBaseY()) ||
      !ValueValidator.isValidDigitalNumber(this.#directService.getBaseZ()) ||
      !ValueValidator.isValidDigitalNumber(this.#directService.getBaseHeight()) ||
      !ValueValidator.isValidHorizontalAngle(this.#directService.getTargetDirection()) ||
      !ValueValidator.isValidPositiveNumber(this.#directService.getTargetInclinedDistance()) ||
      !ValueValidator.isValidTiltAngle(this.#directService.getTargetTiltAngle()) ||
      !ValueValidator.isValidDigitalNumber(this.#directService.getTargetHeight())
    ) {
      rezult = false;
    }

    return rezult;
  }

}
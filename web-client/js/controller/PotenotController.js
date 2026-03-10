import {PotenotService} from '../service/PotenotService.js';
import {ValueValidator} from './ValueValidator.js';
import { Informer } from './Informer.js';

/**
 * Displays the "Potenot task" screen and 
 * adds event handlers for its components.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class PotenotController {
  #potenotService;
  #basePointService;
  #insertCoordinatesTarget;
  #resultActual;

  /**
   * @constructor
   * @param {BasePointService} basePointService 
   */
    constructor(basePointService) {
        this.#potenotService = new PotenotService();
        this.#basePointService = basePointService;
        this.#insertCoordinatesTarget = "first";
        this.#resultActual = false;
    }

    /**
     * Loaded Potenot task page and adds listeners to components
     */
    loadPagePotenot() {
        let content  = document.getElementById("content");

        content.innerHTML = `
          <div class="panel toolbar" id="potenot-toolbar">
            <a href="#" class="button clear" id="potenot-clear" title="Очистить">Очистить</a>
            <a href="#" class="button run" id="potenot-run" title="Решить задачу">Обработать</a>
          </div>

          <div class="panel" id="potenot-panel">

            <div class="panel" id="panel-potenot-basis">

              <div class="panel-title">Исходные данные</div>

              <div class="frame">
                <a href="#" class="frame-label menu-item" id="potenot-first-button" title="Вставить из каталога">Координаты точки</a>
                <div class="frame-value"><span>X: </span><input type="text" id="potenot-first-x"></div>
                <div class="frame-value"><span>Y: </span><input type="text" id="potenot-first-y"></div>
                <div class="frame-label">Направление на точку</div>
                <div class="frame-value"><input type="text" id="potenot-first-direction"></div>
              </div>

              <div class="frame">
                <a href="#" class="frame-label menu-item" id="potenot-second-button" title="Вставить из каталога">Координаты точки</a>
                <div class="frame-value"><span>X: </span><input type="text" id="potenot-second-x"></div>
                <div class="frame-value"><span>Y: </span><input type="text" id="potenot-second-y"></div>
                <div class="frame-label">Направление на точку</div>
                <div class="frame-value"><input type="text" id="potenot-second-direction"></div>
              </div>

              <div class="frame">
                <a href="#" class="frame-label menu-item" id="potenot-third-button" title="Вставить из каталога">Координаты точки</a>
                <div class="frame-value"><span>X: </span><input type="text" id="potenot-third-x"></div>
                <div class="frame-value"><span>Y: </span><input type="text" id="potenot-third-y"></div>
                <div class="frame-label">Направление на точку</div>
                <div class="frame-value"><input type="text" id="potenot-third-direction"></div>
              </div>

            </div>

            <div class="panel" id="panel-potenot-result">

              <div class="panel-title">Результаты обработки</div>

              <div class="frame">
                <div class="frame-label">Координаты определяемой точки</div>
                <div id="potenot-base-x" class="frame-value">X: 2333444.555</div>
                <div id="potenot-base-y" class="frame-value">Y: 2333444.555</div>
              </div>

            </div>

          </div>
        
        `;
        
        this.#setData();
        this.#setResult();
        this.#setListBasePoints();

        this.#addListenersToolbarPotenot();
        this.#addListenersPanelPotenot();



    }

    /**
     * Sets data of gui components from model
     */
    #setData() {
      let element = document.getElementById("potenot-first-x");
      element.value = this.#potenotService.getFirstX();
      ValueValidator.checkNumber(element);

      element = document.getElementById("potenot-first-y");
      element.value = this.#potenotService.getFirstY();
      ValueValidator.checkNumber(element);

      element = document.getElementById("potenot-first-direction");
      element.value = this.#potenotService.getFirstDirection();
      ValueValidator.checkHorisontalAngle(element);

      element = document.getElementById("potenot-second-x");
      element.value = this.#potenotService.getSecondX();
      ValueValidator.checkNumber(element);

      element = document.getElementById("potenot-second-y");
      element.value = this.#potenotService.getSecondY();
      ValueValidator.checkNumber(element);

      element = document.getElementById("potenot-second-direction");
      element.value = this.#potenotService.getSecondDirection();
      ValueValidator.checkHorisontalAngle(element);

      element = document.getElementById("potenot-third-x");
      element.value = this.#potenotService.getThirdX();
      ValueValidator.checkNumber(element);

      element = document.getElementById("potenot-third-y");
      element.value = this.#potenotService.getThirdY();
      ValueValidator.checkNumber(element);

      element = document.getElementById("potenot-third-direction");
      element.value = this.#potenotService.getThirdDirection();
      ValueValidator.checkHorisontalAngle(element);
    }

    /**
     * Sets result of gui components from model
     */
    #setResult() {
      document.getElementById("potenot-base-x").innerHTML = `X: ${this.#potenotService.getBaseX()}`;
      document.getElementById("potenot-base-y").innerHTML = `X: ${this.#potenotService.getBaseY()}`;
    }

    /**
     * Creates and adds a list of base stations to the DOM
     */
    #setListBasePoints() {
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
      document.getElementById("panel-potenot-basis").append(listBasePoints);
    }


    /**
     * Adds event listeners for potenot-toolbar
     */
    #addListenersToolbarPotenot() {
        document.getElementById("potenot-toolbar").addEventListener('click', (event) => {

          switch(event.target.id) {

            case "potenot-clear":
              this.#potenotService.clearAll();
              this.#setData();
              this.#setResult();              
              break;

            case "potenot-run":
              if (this.#isValidData()) {
                this.#potenotService.solvePotenotTask().then(() => {
                  this.#setResult();
                  this.#resultActual = true;
                });
              } else {
                Informer.showMessage("Данные содержат ошибки");
              }
              break;
          }
        });

    }

    /**
     * Adds event listeners for potenot-panel
     */
    #addListenersPanelPotenot() {
      const panelPotenot = document.getElementById("potenot-panel");

      panelPotenot.addEventListener('input', () => {
        if (this.#resultActual) {
          this.#potenotService.clearResults();
          this.#setResult();
          this.#resultActual = false;
        }
      });

      panelPotenot.addEventListener('click', (event) => {
        const element = event.target;
        let toggleRect = element.getBoundingClientRect(); 
        let panelPotenotRect = panelPotenot.getBoundingClientRect();
        let listBasePoints = document.getElementById("list-base-point");      
        let overlay = document.getElementById("overlay");

        if (element.hasAttribute("data-base-point-id")) {

          listBasePoints.classList.toggle("open");
          overlay.classList.toggle("open");

          let basePointId = +element.dataset.basePointId;

          switch (this.#insertCoordinatesTarget) {

            case "first":
              document.getElementById("potenot-first-button").classList.toggle("toggle");
              this.#potenotService.saveFirstX(this.#basePointService.getBasePointX(basePointId));
              this.#potenotService.saveFirstY(this.#basePointService.getBasePointY(basePointId));
              break;

            case "second":
              document.getElementById("potenot-second-button").classList.toggle("toggle");
              this.#potenotService.saveSecondX(this.#basePointService.getBasePointX(basePointId));
              this.#potenotService.saveSecondY(this.#basePointService.getBasePointY(basePointId));
              break;

            case "third":
              document.getElementById("potenot-third-button").classList.toggle("toggle");
              this.#potenotService.saveThirdX(this.#basePointService.getBasePointX(basePointId));
              this.#potenotService.saveThirdY(this.#basePointService.getBasePointY(basePointId));
              break;
          }

          this.#setData();

        }


        switch (element.id) {

          case "potenot-first-button":
            listBasePoints.style.top = `${toggleRect.top - panelPotenotRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelPotenotRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinatesTarget = "first";
            element.classList.toggle("toggle");
            break;

          case "potenot-second-button":
            listBasePoints.style.top = `${toggleRect.top - panelPotenotRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelPotenotRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinatesTarget = "second";
            element.classList.toggle("toggle");
            break;

          case "potenot-third-button":
            listBasePoints.style.top = `${toggleRect.top - panelPotenotRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelPotenotRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinatesTarget = "third";
            element.classList.toggle("toggle");
            break;
          }
      });

      panelPotenot.addEventListener('change', (event) => {
        let element = event.target;

        switch(element.id) {

          case "potenot-first-x":
            ValueValidator.checkNumber(element);
            this.#potenotService.saveFirstX(element.value);
            break;

          case "potenot-first-y":
            ValueValidator.checkNumber(element);
            this.#potenotService.saveFirstY(element.value);
            break;

          case "potenot-first-direction":
            ValueValidator.checkHorisontalAngle(element);
            this.#potenotService.saveFirstDirection(element.value);
            break;

          case "potenot-second-x":
            ValueValidator.checkNumber(element);
            this.#potenotService.saveSecondX(element.value);
            break;

          case "potenot-second-y":
            ValueValidator.checkNumber(element);
            this.#potenotService.saveSecondY(element.value);
            break;

          case "potenot-second-direction":
            ValueValidator.checkHorisontalAngle(element);
            this.#potenotService.saveSecondDirection(element.value);
            break;

          case "potenot-third-x":
            ValueValidator.checkNumber(element);
            this.#potenotService.saveThirdX(element.value);
            break;

          case "potenot-third-y":
            ValueValidator.checkNumber(element);
            this.#potenotService.saveThirdY(element.value);
            break;

          case "potenot-third-direction":
            ValueValidator.checkHorisontalAngle(element);
            this.#potenotService.saveThirdDirection(element.value);
            break;

        }
      });

    }

    /**
     * Verifies the validity of data for solving a Potenot geodetic task
     * @returns {boolean}
     */
    #isValidData() {
      let result = true;
      if (
        !ValueValidator.isValidDigitalNumber(this.#potenotService.getFirstX()) ||
        !ValueValidator.isValidDigitalNumber(this.#potenotService.getFirstY()) ||
        !ValueValidator.isValidHorizontalAngle(this.#potenotService.getFirstDirection()) ||
        !ValueValidator.isValidDigitalNumber(this.#potenotService.getSecondX()) ||
        !ValueValidator.isValidDigitalNumber(this.#potenotService.getSecondY()) ||
        !ValueValidator.isValidHorizontalAngle(this.#potenotService.getSecondDirection()) ||
        !ValueValidator.isValidDigitalNumber(this.#potenotService.getThirdX()) ||
        !ValueValidator.isValidDigitalNumber(this.#potenotService.getThirdY()) ||
        !ValueValidator.isValidHorizontalAngle(this.#potenotService.getThirdDirection())
      ) {
        result = false;
      }

      return result;
    }

}
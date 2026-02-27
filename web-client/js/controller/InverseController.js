import {InverseService} from '../service/InverseService.js';

/**
 * Displays the Inverse Geodetic Task screen and 
 * adds event handlers for its components.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class InverseController {
  #inverseService;
  #basePointService;
  #insertCoordinateToBase;

  /**
   * @constructor
   * @param {BasePointService} basePointService 
   */
    constructor(basePointService) {
        this.#inverseService = new InverseService();
        this.#basePointService = basePointService;
        this.#insertCoordinateToBase = true;
    }

    /**
     * Loads page Inverse to panel content
     * sets listeners
     */
    loadPageInverse() {
        let content = document.getElementById("content");

        content.innerHTML = `
          <div class="panel toolbar" id="inverse-toolbar">
            <a href="#" class="button clear" id="inverse-clear" title="Очистить">Очистить</a>
            <a href="#" class="button run" id="inverse-run" title="Решить задачу">Обработать</a>
          </div>

          <div class="panel" id="inverse-panel">

            <div class="panel" id="panel-inverse-base">

              <a href="#" class="panel-title menu-item" id="inverse-base-button" title="Вставить из каталога">Координаты базы</a>

              <div class="frame">
                <div class="frame-value"><span>X: </span><input type="text" id="inverse-base-x"></div>
                <div class="frame-value"><span>Y: </span><input type="text" id="inverse-base-y"></div>
                <div class="frame-value"><span>Z: </span><input type="text" id="inverse-base-z"></div>
              </div>

            </div>

            <div class="panel" id="panel-inverse-target">

              <a href="#" class="panel-title menu-item" id="inverse-target-button" title="Вставить из каталога">Координаты цели</a>

              <div class="frame">
                <div class="frame-value"><span>X: </span><input type="text" id="inverse-target-x"></div>
                <div class="frame-value"><span>Y: </span><input type="text" id="inverse-target-y"></div>
                <div class="frame-value"><span>Z: </span><input type="text" id="inverse-target-z"></div>            
              </div>

            </div>

            <div class="panel" id="panel-inverse-result">

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

        this.#setData();
        this.#setResult();
        this.#setListBasePoints();

        this.#addInverseToolbarListeners();
        this.#addInversePanelListeners();



    }

    /**
     * Sets data of gui components from model
     */
    #setData() {

      document.getElementById("inverse-base-x").value = this.#inverseService.getBaseX();

      document.getElementById("inverse-base-y").value = this.#inverseService.getBaseY();

      document.getElementById("inverse-base-z").value = this.#inverseService.getBaseZ();

      document.getElementById("inverse-target-x").value = this.#inverseService.getTargetX();

      document.getElementById("inverse-target-y").value = this.#inverseService.getTargetY();

      document.getElementById("inverse-target-z").value = this.#inverseService.getTargetZ();

    }

    /**
     * Sets result of gui components from model
     */
    #setResult() {

      document.getElementById("inverse-direction").innerHTML = this.#inverseService.getDirection();

      document.getElementById("inverse-hor-distance").innerHTML = this.#inverseService.getHorDistance();

      document.getElementById("inverse-inc-distance").innerHTML = this.#inverseService.getInclinedDistance();

      document.getElementById("inverse-tilt").innerHTML = this.#inverseService.getTiltAngle();

      document.getElementById("inverse-elevation").innerHTML = this.#inverseService.getElevation();

    }

    /**
     * Creates and adds a list of base stations to the DOM
     */
    #setListBasePoints() {
      const panelInverseBase = document.getElementById("panel-inverse-base");
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
      panelInverseBase.append(listBasePoints);
    }

    /**
     * Adds event listeners for inverse-toolbar
     */
    #addInverseToolbarListeners() {
      document.getElementById("inverse-toolbar").addEventListener('click', (event) => {

        switch(event.target.id) {

          case "inverse-clear":
            this.#inverseService.clearAll();
            this.#setData();
            this.#setResult();              
            break;

          case "inverse-run":
            this.#inverseService.solveInverseTask().then(() => this.#setResult());
            break;
        }
      });

    }

    /**
     * Adds event listeners for inverse-panel
     */
    #addInversePanelListeners() {
      const panelInverse = document.getElementById("inverse-panel");

      panelInverse.addEventListener('click', (event) => {
        let element = event.target;
        let toggleRect = element.getBoundingClientRect(); 
        let panelInverseRect = panelInverse.getBoundingClientRect();
        let listBasePoints = document.getElementById("list-base-point");      
        let overlay = document.getElementById("overlay");

        if (element.hasAttribute("data-base-point-id")) {

          listBasePoints.classList.toggle("open");
          overlay.classList.toggle("open");

          let basePointId = +element.dataset.basePointId;

          if (this.#insertCoordinateToBase) {
            document.getElementById("inverse-base-button").classList.toggle("toggle");

            this.#inverseService.saveBaseX(this.#basePointService.getBasePointX(basePointId));
            this.#inverseService.saveBaseY(this.#basePointService.getBasePointY(basePointId));
            this.#inverseService.saveBaseZ(this.#basePointService.getBasePointZ(basePointId));
          } else {
            document.getElementById("inverse-target-button").classList.toggle("toggle");

            this.#inverseService.saveTargetX(this.#basePointService.getBasePointX(basePointId));
            this.#inverseService.saveTargetY(this.#basePointService.getBasePointY(basePointId));
            this.#inverseService.saveTargetZ(this.#basePointService.getBasePointZ(basePointId));
          }

          this.#setData();

        }



        switch (element.id) {

          case "inverse-base-button":
            listBasePoints.style.top = `${toggleRect.top - panelInverseRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelInverseRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinateToBase = true;
            element.classList.toggle("toggle");
            break;

          case "inverse-target-button":
            listBasePoints.style.top = `${toggleRect.top - panelInverseRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelInverseRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinateToBase = false;
            element.classList.toggle("toggle");
            break;
        }
      });
      
      panelInverse.addEventListener('change', (event) => {
        let element = event.target;

        switch(element.id) {

          case "inverse-base-x":
            this.#inverseService.saveBaseX(element.value);
            break;

          case "inverse-base-y":
            this.#inverseService.saveBaseY(element.value);
            break;

          case "inverse-base-z":
            this.#inverseService.saveBaseZ(element.value);
            break;

          case "inverse-target-x":
            this.#inverseService.saveTargetX(element.value);
            break;

          case "inverse-target-y":
            this.#inverseService.saveTargetY(element.value);
            break;

          case "inverse-target-z":
            this.#inverseService.saveTargetZ(element.value);
            break;

        }
      });

    }


}
import {PotenotService} from '../service/PotenotService.js';

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

  /**
   * @constructor
   * @param {BasePointService} basePointService 
   */
    constructor(basePointService) {
        this.#potenotService = new PotenotService();
        this.#basePointService = basePointService;
        this.#insertCoordinatesTarget = "first";
    }

    /**
     * Loaded Potenot task page and adds listeners to components
     */
    loadPagePotenot() {
        let content  = document.getElementById("content");

        content.innerHTML = `
      <div class="toolbar" id="potenot-toolbar">
        <div class="button clear" id="potenot-clear" title="Очистить">Очистить</div>
        <div class="button run" id="potenot-run" title="Решить задачу">Обработать</div>
      </div>

      <div class="panel-potenot" id="potenot-panel">

        <div class="panel-potenot-basis" id="panel-potenot-basis">

          <div class="panel-title">Исходные данные</div>

          <div class="frame">
            <div class="frame-label toggle menu-item" id="potenot-first-button" title="Вставить из каталога">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" id="potenot-first-x"></div>
            <div class="frame-value"><span>Y: </span><input type="text" id="potenot-first-y"></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" id="potenot-first-direction"></div>
          </div>

          <div class="frame">
            <div class="frame-label toggle menu-item" id="potenot-second-button" title="Вставить из каталога">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" id="potenot-second-x"></div>
            <div class="frame-value"><span>Y: </span><input type="text" id="potenot-second-y"></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" id="potenot-second-direction"></div>
          </div>

          <div class="frame">
            <div class="frame-label toggle menu-item" id="potenot-third-button" title="Вставить из каталога">Координаты точки</div>
            <div class="frame-value"><span>X: </span><input type="text" id="potenot-third-x"></div>
            <div class="frame-value"><span>Y: </span><input type="text" id="potenot-third-y"></div>
            <div class="frame-label">Направление на точку</div>
            <div class="frame-value"><input type="text" id="potenot-third-direction"></div>
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
        this.setListBasePoints();

        this.addListenersToolbarPotenot();
        this.addListenersPanelPotenot();



    }

    /**
     * Sets data of gui components from model
     */
    setData() {
      document.getElementById("potenot-first-x").value = this.#potenotService.getFirstX();
      document.getElementById("potenot-first-y").value = this.#potenotService.getFirstY();
      document.getElementById("potenot-first-direction").value = this.#potenotService.getFirstDirection();
      document.getElementById("potenot-second-x").value = this.#potenotService.getSecondX();
      document.getElementById("potenot-second-y").value = this.#potenotService.getSecondY();
      document.getElementById("potenot-second-direction").value = this.#potenotService.getSecondDirection();
      document.getElementById("potenot-third-x").value = this.#potenotService.getThirdX();
      document.getElementById("potenot-third-y").value = this.#potenotService.getThirdY();
      document.getElementById("potenot-third-direction").value = this.#potenotService.getThirdDirection();
    }

    /**
     * Sets result of gui components from model
     */
    setResult() {
      document.getElementById("potenot-base-x").innerHTML = `X: ${this.#potenotService.getBaseX()}`;
      document.getElementById("potenot-base-y").innerHTML = `X: ${this.#potenotService.getBaseY()}`;
    }

    /**
     * Creates and adds a list of base stations to the DOM
     */
    setListBasePoints() {
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
    addListenersToolbarPotenot() {
        document.getElementById("potenot-toolbar").addEventListener('click', (event) => {

          switch(event.target.id) {

            case "potenot-clear":
              this.#potenotService.clearAll();
              this.setData();
              this.setResult();              
              break;

            case "potenot-run":
              this.#potenotService.solvePotenotTask().then(() => this.setResult());
              break;
          }
        });

    }

    /**
     * Adds event listeners for potenot-panel
     */
    addListenersPanelPotenot() {
      const panelPotenot = document.getElementById("potenot-panel");

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
              this.#potenotService.saveFirstX(this.#basePointService.getBasePointX(basePointId));
              this.#potenotService.saveFirstY(this.#basePointService.getBasePointY(basePointId));
              break;

            case "second":
              this.#potenotService.saveSecondX(this.#basePointService.getBasePointX(basePointId));
              this.#potenotService.saveSecondY(this.#basePointService.getBasePointY(basePointId));
              break;

            case "third":
              this.#potenotService.saveThirdX(this.#basePointService.getBasePointX(basePointId));
              this.#potenotService.saveThirdY(this.#basePointService.getBasePointY(basePointId));
              break;
          }

          this.setData();

        }


        switch (element.id) {

          case "potenot-first-button":
            listBasePoints.style.top = `${toggleRect.top - panelPotenotRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelPotenotRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinatesTarget = "first";
            break;

          case "potenot-second-button":
            listBasePoints.style.top = `${toggleRect.top - panelPotenotRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelPotenotRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinatesTarget = "second";
            break;

          case "potenot-third-button":
            listBasePoints.style.top = `${toggleRect.top - panelPotenotRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelPotenotRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertCoordinatesTarget = "third";
            break;
          }
      });

      panelPotenot.addEventListener('change', (event) => {
        let element = event.target;

        switch(element.id) {

          case "potenot-first-x":
            this.#potenotService.saveFirstX(element.value);
            break;

          case "potenot-first-y":
            this.#potenotService.saveFirstY(element.value);
            break;

          case "potenot-first-direction":
            this.#potenotService.saveFirstDirection(element.value);
            break;

          case "potenot-second-x":
            this.#potenotService.saveSecondX(element.value);
            break;

          case "potenot-second-y":
            this.#potenotService.saveSecondY(element.value);
            break;

          case "potenot-second-direction":
            this.#potenotService.saveSecondDirection(element.value);
            break;

          case "potenot-third-x":
            this.#potenotService.saveThirdX(element.value);
            break;

          case "potenot-third-y":
            this.#potenotService.saveThirdY(element.value);
            break;

          case "potenot-third-direction":
            this.#potenotService.saveThirdDirection(element.value);
            break;

        }
      });

    }

}
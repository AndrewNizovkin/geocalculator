import {SurveyService} from "../service/SurveyService.js";

/**
 * Encapsulates the components of the "Survey" screen 
 * and the methods for their interaction with 
 * the user and the model.
 * @author Nizovkin A.V.
 * @copyright 2025 Nizovkin A.V.
 */
export class SurveyController {
  #basePointService;
  #polygonService;
  #surveyService;
  #currentSurveyStation;
  #currentMeasurement;
  #insertBasePointToStation;
  #surveyImportType;
  #reportsActual;

  /**
   * @constructor
   * @param {BasePoinService} basePointService 
   * @param {PolygonService} polygonService
   */
  constructor(basePointService, polygonService) {
    this.#basePointService = basePointService;
    this.#polygonService = polygonService;
    this.#surveyService = new SurveyService();
    this.#surveyService.addNewStation();
    this.#surveyService.addNewMeasurement(0);
    this.#currentSurveyStation = 0;
    this.#currentMeasurement = 0;
    this.#insertBasePointToStation = true;
    this.#surveyImportType = "leica";
    this.#reportsActual = false;
  }

    /**
     * Loads survey page and adds listeners to components
     */
    loadPageSurvey() {
        const content = document.getElementById("content");

        content.innerHTML = `
          <div class="survey-toolbar" id="toolbar-survey">
            <div class="survey-button new" id="survey-new" title="Новая съёмка"></div>
            <div class="survey-button open" id="survey-open" title="Открыть"></div>
            <div class="survey-button import" id="survey-import" title="Импорт из файла"></div>
            <div class="survey-toolbar-separator"></div>
            <div class="survey-button run" id="survey-run" title="Обработать"></div>
            <div class="survey-button view" id="survey-view" title="Просмотр результатов"></div>
            <div class="survey-button extract" id="survey-extract" title="Извлечь полигон"></div>
            <input type="file" id="survey-open-input" name="file" accept=".tah">
            <input type="file" id="survey-import-input" accept=".txt">
            <input type="file" id="survey-report-input" accept=".txt">
            <input type="file" id="survey-extract-input" accept=".txt">

          </div>

          <div class="panel" id="panel-survey">
            
            <div class="panel" id="panel-basis">
              <div class="panel" id="panel-stations">
                <div class="survey-toolbar" id="toolbar-survey-stations">
                  <div class="survey-button delete" id="delete-station" title="Удалить станцию"></div>
                  <div class="survey-button insert-before" id="before-station" title="Вставить перед выбранной"></div>
                  <div class="survey-button insert-after" id="after-station" title="Вставить после выбранной"></div>
                </div>
                <div class="panel" id="scrollpanel-stations">
                  <div class="list-stations" id="list-stations">
                  </div>
                </div>
              </div>

              <div class="panel" id="panel-station">
                <div class="station-row">
                  <div class="toggle menu-item" id="button-station-name" title="Вставить из каталога"class="">Станция</div>
                  <input type="text" id="survey-station-name" size="12" placeholder="noname">
                </div>
                <div class="station-row">
                  <div>X:</div>
                  <input type="text" id="survey-station-x" size="12" placeholder="0.000">
                </div>
                <div class="station-row">
                  <div>Y:</div>
                  <input type="text" id="survey-station-y" size="12" placeholder="0.000">
                </div>
                <div class="station-row">
                  <div>Z:</div>
                  <input type="text" id="survey-station-z" size="12" placeholder="0.000">
                </div>
                <div class="station-row">
                  <div>i:</div>
                  <input type="text" id="survey-station-height" size="12" placeholder="0.000">
                </div>
                <div class="station-row">
                  <div>Ор.Напр.</div>
                  <input type="text" id="survey-or-direction" size="12" value="0.0000">
                </div>
                <div class="station-row">
                  <div class="toggle menu-item" id="button-or-name" title="Вставить из каталога">Ориентир</div>
                  <input type="text" id="survey-or-name" size="12" placeholder="noname">
                </div>
                <div class="station-row">
                  <div>X:</div>
                  <input type="text" id="survey-or-x" size="12" placeholder="0.000">
                </div>
                <div class="station-row">
                  <div>Y:</div>
                  <input type="text" id="survey-or-y" size="12" placeholder="0.000">
                </div>          

              </div>

            </div>

            <div class="panel" id="panel-measurements">
              <div class="survey-toolbar" id="toolbar-survey-measurements">
                <div class="survey-button delete" id="delete-measurement" title="Удалить выбранную"></div>
                <div class="survey-button insert-before" id="before-measurement" title="Вставить перед выбранной"></div>
                <div class="survey-button insert-after" id="after-measurement" title="Вставить после выбранной"></div>
              </div>
              <div class="panel" id="scrollpanel-measurements">
                <table class="table-measurements">
                  <thead>
                    <th>Название</th>
                    <th>Направление</th>
                    <th>Расстояние</th>
                    <th>Наклон</th>
                    <th>Выс.Цели</th>
                  </thead>
                  <tbody id="list-measurements">
                  </tbody>

                </table>
              </div>
            </div>

          </div>

      
        `;
        
        this.#setSurveyStation();
        this.#setListSurveyStations();
        this.#setTableMeasurements();
        this.#setListBasePoints();
        this.#setMenuImport();
        this.#addListenersMainToolbar()
        this.#addListenersPanelStations();
        this.#addListenersPanelStation();
        this.#addListenersPanelMeasurements();

    }

    /**
     * Loads report survey page and adds listeners to components
     */
    #loadPageReportSurvey() {
      const content = document.getElementById("content");

      content.innerHTML = `
        <div class="panel" id="panel-processing-report">
            <div class="panel-title">Ведомость вычисления координат</div>
            <div class="frame">
                <textarea class="text-report" cols="130" id="text-processing-report" placeholder="Ведомость вычисления координат"></textarea>
            </div>

        </div>

        <div class="panel" id="panel-survey-tah-report">

            <div class="panel" id="panel-survey-report">
                <div class="panel-title">Каталог координат точек</div>
                <div class="frame">
                    <textarea class="text-report" id="text-survey-report" placeholder="Каталог координат точек"></textarea>
                </div>

            </div>

            <div class="panel" id="panel-tah-report">
                <div class="panel-title">Исходные данные (*.tah)</div>
                <div class="frame">
                    <textarea class="text-report" id="text-pol-report" placeholder="Исходные данные в формате '*.tah'"></textarea>
                </div>

            </div>
        </div>      
      `;

      this.#setSurveyReports();

    }

    /**
     * Loads report of extracting polygon from survey
     */
    #loadPageExtract() {
      const content = document.getElementById("content");

      content.innerHTML = `
        <div class="panel" id="panel-extract-report">
            <div class="panel-title">Ведомость вычисления средних горизонтальных проложений и превышений</div>
            <div class="frame">
                <textarea class="text-report" cols="130" id="text-extract-report" placeholder="Ведомость вычисления средних горизонтальных проложений и превышений"></textarea>
            </div>

        </div>

        <div class="panel" id="panel-extract-pol-report">
            <div class="panel-title">Результат извлечения полигона (*.pol)</div>
            <div class="frame">
                <textarea class="text-report" id="text-pol-report" placeholder="Результат извлечения полигона (*.pol)"></textarea>
            </div>

        </div>
      
      `;

      this.#setExtractReports();
    }

    /**
     * Adds event listeners for main-toolbar
     */
    #addListenersMainToolbar() {

      document.getElementById("toolbar-survey").addEventListener('change', event => {
        let element = event.target;

        switch(element.id) {

          case "survey-open-input":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.#surveyService.readFromDevice(file).then(() => {
                this.#currentSurveyStation = 0;
                this.#currentMeasurement = 0;
                this.#setListSurveyStations();
                this.#setSurveyStation();
                this.#setTableMeasurements();
                });
             } catch (error) {
              console.error(error.message);
            }
            break;

          case "survey-import-input":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.#surveyService.importFromTotalStation(file, this.#surveyImportType).then(() => {
                  this.#currentSurveyStation = 0;
                  this.#currentMeasurement = 0;              
                  this.#setListSurveyStations();
                  this.#setSurveyStation();
                  this.#setTableMeasurements();
              });

             } catch (error) {
              console.error(error.message);
            }
            break;

          case "survey-report-input":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.#surveyService.calculateSurvey(file).then(() => {
                this.#reportsActual = true;
                let countMeasurements = 0;
                for (let i = 0; i < this.#surveyService.size(); i++) {
                  countMeasurements += this.#surveyService.measurementSize(i);
                }
                alert(`${countMeasurements} измерений успешно обработаны`);
              });      
            } catch (error) {
              console.error(error.message);
            }
            break;

          case "survey-extract-input":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.#surveyService.extractPolygon(file).then(() => {
                this.#polygonService.readArrayPol(this.#surveyService.getReportExtractPol());
                this.#loadPageExtract();
              });      
            } catch (error) {
              console.error(error.message);
            }
            break;

        }

        
      });

      document.getElementById("toolbar-survey").addEventListener('click', (event) => {
        let element = event.target;
        let importFileInput = document.getElementById("survey-import-input");
        let surveyOpenInput = document.getElementById("survey-open-input");
        let overlay = document.getElementById("overlay");
        let menuImport = document.getElementById("menu-import");        
        //test-mode
        let surveyReportInput = document.getElementById("survey-report-input");
        let extractInput = document.getElementById("survey-extract-input");
        //test-mode
        
        switch(element.id) {

          case "survey-new":
            this.#surveyService.clearAll();
            this.#surveyService.addNewStation();
            this.#surveyService.addNewMeasurement(0);
            this.#currentSurveyStation = 0;
            this.#currentMeasurement = 0;
            this.#setListSurveyStations();
            this.#setSurveyStation();
            this.#setTableMeasurements();
            break;

          case "survey-open":
            surveyOpenInput.click();
            break;

          case "survey-import":
            let toggleRect = element.getBoundingClientRect(); 
            let toolbarSurvey = document.getElementById("toolbar-survey").getBoundingClientRect();

            menuImport.style.top = `${toggleRect.top - toolbarSurvey.top + toggleRect.height + window.scrollY}px`;
            menuImport.style.left = `${toggleRect.left - toolbarSurvey.left + window.scrollX}px`;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");

          break;

          case "import-leica":
            this.#surveyImportType = element.id;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");
            importFileInput.setAttribute("accept", ".gsi");
            importFileInput.click();
            break;

          case "import-nikon":
            this.#surveyImportType = element.id;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");
            importFileInput.setAttribute("accept", ".raw");
            importFileInput.click();
            break;

          case "import-topcon":
            this.#surveyImportType = element.id;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");
            importFileInput.setAttribute("accept", ".txt");
            importFileInput.click();
            break;


          case "survey-run":
            surveyReportInput.click();
            break;

          case "survey-view":
            this.#loadPageReportSurvey();
            break;

          case "survey-extract":
            extractInput.click();
            break;
        }
      });

    }

    

    /**
     * Adds event listeners for panel-stations
     */
    #addListenersPanelStations() {

      document.getElementById("list-stations").addEventListener('click', (event) => {
        let element = event.target;

        if(element.hasAttribute('data-station-id')) {
          this.#currentSurveyStation = +element.dataset.stationId;
          this.#currentMeasurement = 0;
          this.#setSurveyStation();
          this.#setTableMeasurements();
        }

      });


      document.getElementById("toolbar-survey-stations").addEventListener('click', (event) => {
        let element = event.target;

        switch(element.id) {
          
          case "delete-station":
            if(this.#surveyService.size() > 1) {
              this.#surveyService.removeStation(this.#currentSurveyStation);
              // document.getElementById("list-stations").innerHTML = '';
              this.#setListSurveyStations();
              if(this.#currentSurveyStation == this.#surveyService.size()){
                this.#currentSurveyStation--;
              }
              this.#setSurveyStation();
              this.#setTableMeasurements();
            }
            break;
          
          case "before-station":
            this.#surveyService.insertNewStation(this.#currentSurveyStation);
            this.#surveyService.addNewMeasurement(this.#currentSurveyStation);
            this.#setListSurveyStations();
            this.#setSurveyStation();
            this.#setTableMeasurements();
            break;

          case "after-station":
            this.#currentSurveyStation++;
            this.#surveyService.insertNewStation(this.#currentSurveyStation);
            this.#surveyService.addNewMeasurement(this.#currentSurveyStation);
            this.#setListSurveyStations();
            this.#setSurveyStation();
            this.#setTableMeasurements();
            break;
        }
      });

    }

    /**
     * Adds event listeners for panel-station
     */
    #addListenersPanelStation() {
      let surveyPanelStation = document.getElementById("panel-station");
      let overlay = document.getElementById("overlay");

      surveyPanelStation.addEventListener('input', () => {
        if (this.#reportsActual) {
          this.#surveyService.clearSurveyReports();
          this.#reportsActual = false;
        }
      });

      surveyPanelStation.addEventListener('click', (event) => {
        let element = event.target;
        let toggleRect = element.getBoundingClientRect(); 
        let panelStationRect = document.getElementById("panel-station").getBoundingClientRect();
        let listBasePoints = document.getElementById("list-base-point"); 

        if (element.hasAttribute("data-base-point-id")) {

            document.getElementById("list-base-point").classList.toggle("open");
            document.getElementById("overlay").classList.toggle("open");
            let i = +element.dataset.basePointId;

            if (this.#insertBasePointToStation) {
              this.#surveyService.saveStationName(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointName(i));
              this.#surveyService.saveStationX(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointX(i)
              );
              this.#surveyService.saveStationY(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointY(i)
              );
              this.#surveyService.saveStationZ(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointZ(i)
              );
            } else {
              this.#surveyService.saveOrName(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointName(i)
              );
              this.#surveyService.saveOrX(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointX(i)
              );
              this.#surveyService.saveOrY(
                this.#currentSurveyStation, 
                this.#basePointService.getBasePointY(i)
              );

            }
            this.#setListSurveyStations();
            this.#setSurveyStation();
        }
        
        switch (element.id) {

          case "button-station-name":
            listBasePoints.style.top = `${toggleRect.top - panelStationRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelStationRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertBasePointToStation = true;
            break;

          case "button-or-name":
            listBasePoints.style.top = `${toggleRect.top - panelStationRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelStationRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.#insertBasePointToStation = false;
            break;

        }
      });

      surveyPanelStation.addEventListener('change', (event) => {
        let element = event.target;

        switch(element.id) {

          case "survey-station-name":
            this.#surveyService.saveStationName(this.#currentSurveyStation, element.value);
            document.getElementById("list-stations").innerHTML = '';
            this.#setListSurveyStations();
            break;

          case "survey-station-x":
            this.#surveyService.saveStationX(this.#currentSurveyStation, element.value);
            break;

          case "survey-station-y":
            this.#surveyService.saveStationY(this.#currentSurveyStation, element.value);
            break;
            
          case "survey-station-z":
            this.#surveyService.saveStationZ(this.#currentSurveyStation, element.value);
            break;

          case "survey-station-height":
            this.#surveyService.saveStationHeight(this.#currentSurveyStation, element.value);
            break;

          case "survey-or-direction":
            this.#surveyService.saveOrDirection(this.#currentSurveyStation, element.value);
            break;

          case "survey-or-name":
            this.#surveyService.saveOrName(this.#currentSurveyStation, element.value);
            break;

          case "survey-or-x":
            this.#surveyService.saveOrX(this.#currentSurveyStation, element.value);
            break;

          case "survey-or-y":
            this.#surveyService.saveOrY(this.#currentSurveyStation, element.value);
            break;

        }
      });

    }

    /**
     * Adds event listeners for panel-measurement
     */
    #addListenersPanelMeasurements() {

      const panelMeasurements = document.getElementById("panel-measurements");

      panelMeasurements.addEventListener('input', () => {
        if (this.#reportsActual) {
          this.#surveyService.clearSurveyReports();
          this.#reportsActual = false;
        }
      });

      document.getElementById("toolbar-survey-measurements").addEventListener('click', (event) => {
        let element = event.target;

        switch(element.id) {

          case "delete-measurement":
            if(this.#surveyService.measurementSize() > 1) {
              this.#surveyService.removeMeasurement(this.#currentSurveyStation, this.#currentMeasurement);
              this.#setTableMeasurements();
              if(this.#currentMeasurement == this.#surveyService.measurementSize(this.#currentSurveyStation)) {
                this.#currentMeasurement--;
              }
            }
            break;

          case "before-measurement":
            this.#surveyService.insertNewMeasurement(this.#currentSurveyStation, this.#currentMeasurement);
            this.#setTableMeasurements();
            break;

          case "after-measurement":
            this.#currentMeasurement++;
            this.#surveyService.insertNewMeasurement(this.#currentSurveyStation, this.#currentMeasurement);
            this.#setTableMeasurements();
            break;
        }
      });

      document.getElementById("list-measurements").addEventListener('click', (event) => {
        let element = event.target;

        if(element.hasAttribute("data-measurement-id")) {
          this.#currentMeasurement = +element.dataset.measurementId;
        }
      });

      document.getElementById("list-measurements").addEventListener('change', (event) => {
        let element = event.target;

        if(element.hasAttribute('data-measurement-id')) {
          switch(element.dataset.target) {

            case "name":
              this.#surveyService.saveTargetName(
                this.#currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);              
              break;

            case "direction":
              this.#surveyService.saveTargetDirection(
                this.#currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;

            case "distance":
              this.#surveyService.saveTargetDistance(
                this.#currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;

            case "tilt":
              this.#surveyService.saveTargetTiltAngle(
                this.#currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;

            case "height":
              this.#surveyService.saveTargetHeight(
                this.#currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;
          }
        }
      });

    }




    /**
     * Sets the values of DOM elements for a station 
     * with the currentStation index
     */
    #setSurveyStation() {
      if(this.#surveyService.size() > 0) {
        document.getElementById("survey-station-name").value = this.#surveyService.getStationName(this.#currentSurveyStation);
        document.getElementById("survey-station-x").value = this.#surveyService.getStationX(this.#currentSurveyStation);
        document.getElementById("survey-station-y").value = this.#surveyService.getStationY(this.#currentSurveyStation);
        document.getElementById("survey-station-z").value = this.#surveyService.getStationZ(this.#currentSurveyStation);
        document.getElementById("survey-station-height").value = this.#surveyService.getStationHeight(this.#currentSurveyStation);
        document.getElementById("survey-or-direction").value = this.#surveyService.getOrDirection(this.#currentSurveyStation);
        document.getElementById("survey-or-name").value = this.#surveyService.getOrName(this.#currentSurveyStation);
        document.getElementById("survey-or-x").value = this.#surveyService.getOrX(this.#currentSurveyStation);
        document.getElementById("survey-or-y").value = this.#surveyService.getOrY(this.#currentSurveyStation);
      }

    }

    /**
     * Creates and displays a list of DOM elements 
     * of all survey stations from the survey repository
     */
    #setListSurveyStations() {
      if(this.#surveyService.size() > 0) {
        let listSurveyStations = document.getElementById("list-stations");
        listSurveyStations.innerHTML = '';

        for(let i = 0; i < this.#surveyService.size(); i++) {
          let station = this.#getElementSurveyStation(i);
          listSurveyStations.append(station);
        }
      }
    }

    /**
     * Sets value of survey reports
     */
    #setSurveyReports() {
      const reportSurveyProcessing = document.getElementById("text-processing-report");
      reportSurveyProcessing.value = this.#surveyService.getReportSurveyProcessing().join('\n');

      const reportSurveyCatalog = document.getElementById("text-survey-report");
      reportSurveyCatalog.value = this.#surveyService.getReportSurveyCatalog().join('\n');

      const reportSurveyTah = document.getElementById("text-pol-report");
      reportSurveyTah.value = this.#surveyService.getTahArray().join('\n');

    }

    /**
     * Sets value of extract reports
     */
    #setExtractReports() {
      const reportExtractProcessing = document.getElementById("text-extract-report");
      reportExtractProcessing.value = this.#surveyService.getReportExtractProcessing().join('\n');

      const reportExtractPol = document.getElementById("text-pol-report");
      reportExtractPol.value = this.#surveyService.getReportExtractPol().join('\n');
    }


    /**
     * Gets element for list of survey stations
     * @param {number} indexStation 
     * @returns {HtmlElement} 
     */
    #getElementSurveyStation(indexStation) {
          let station = document.createElement('li');
          let menuItem = document.createElement('a');
          menuItem.className = "menu-item";
          menuItem.href = "#";
          menuItem.setAttribute('data-station-id', indexStation);
          menuItem.innerHTML = this.#surveyService.getStationName(indexStation);
          station.append(menuItem);
          return station;

    }

    /**
     * Creates and displays a list of DOM elements 
     * of all measurements for a station with the currentStation index
     */
    #setTableMeasurements() {
      if(this.#surveyService.measurementSize(this.#currentSurveyStation) > 0) {
        let listMeasurements = document.getElementById("list-measurements");
        listMeasurements.innerHTML = '';
        for(let i = 0; i < this.#surveyService.measurementSize(this.#currentSurveyStation); i++) {
          let row = this.#getElementMeasurement(this.#currentSurveyStation, i);
          listMeasurements.append(row);

        }

      }
    }

    /**
     * Gets row for measurement table
     * @param {number} indexStation 
     * @param {number} indexMeasurement 
     * @returns 
     */
    #getElementMeasurement(indexStation, indexMeasurement) {
          let row = document.createElement('tr');

          let sell = document.createElement('td');
          let item = document.createElement('input');
          item.type = "text";
          // item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'name');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.#surveyService.getTargetName(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          // item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'direction');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.#surveyService.getTargetDirection(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          // item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'distance');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.#surveyService.getTargetDistance(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          // item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'tilt');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.#surveyService.getTargetTiltAngle(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          // item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'height');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.#surveyService.getTargetHeight(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          return row;
    }

    /**
     * Creates and adds a list of base stations to the DOM
     */
    #setListBasePoints() {
      const panelStation = document.getElementById("panel-station");
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
      panelStation.append(listBasePoints);
    }

    /**
     * Creates and adds pop-up 'menu import' to the DOM
     */
    #setMenuImport() {
      const menuImport = document.createElement('div');
      menuImport.id = "menu-import";
      menuImport.className = "menu-import";

      let menuItem = document.createElement('div');
      menuItem.id = "import-leica";
      menuItem.className = "menu-item";
      menuItem.innerHTML = "Leica";
      menuImport.append(menuItem);

      menuItem = document.createElement('div');
      menuItem.id = "import-nikon";
      menuItem.className = "menu-item";
      menuItem.innerHTML = "Nikon";
      menuImport.append(menuItem);

      menuItem = document.createElement('div');
      menuItem.id = "import-topcon";
      menuItem.className = "menu-item";
      menuItem.innerHTML = "Topcon";
      menuImport.append(menuItem);

      document.getElementById("toolbar-survey").append(menuImport);

    }

}
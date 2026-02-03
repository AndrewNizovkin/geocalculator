import {SurveyService} from "../service/SurveyService.js";

/**
 * 
 */
export class SurveyController {

  constructor(basePointService) {
    this.basePointService = basePointService;
    this.surveyService = new SurveyService();
    this.surveyService.addNewStation();
    this.surveyService.addNewMeasurement(0);
    this.currentSurveyStation = 0;
    this.currentMeasurement = 0;
    this.currentBasePoint = 0;
    this.insertBasePointToStation = true;
    this.surveyImportType = "leica";
  }

    /**
     * Loaded survey page and adds listeners to components
     */
    loadPageSurvey() {
        let content = document.getElementById("content");

        content.innerHTML = `

    
    <div class="survey-toolbar" id="toolbar-survey">
      <div class="survey-button new" id="survey-new" title="Новая съёмка"></div>
      <div class="survey-button open" id="survey-open" title="Открыть">
        <input type="file" id="survey-open-input" name="file" accept=".tah">
      </div>
      <div class="survey-button import" id="survey-import" title="Импорт из файла"></div>
      <div class="survey-toolbar-separator"></div>
      <div class="survey-button run" id="survey-run" title="Обработать"></div>
      <div class="survey-button view" id="survey-view" title="Просмотр результатов"></div>
      <input type="file" id="survey-import-input" accept=".txt">
    </div>

    <div class="panel-survey">
      
      <div class="panel-basis">
        <div class="panel-stations">
          <div class="survey-toolbar" id="toolbar-survey-stations">
            <div class="survey-button delete" id="delete-station" title="Удалить станцию"></div>
            <div class="survey-button insert-before" id="before-station" title="Вставить перед выбранной"></div>
            <div class="survey-button insert-after" id="after-station" title="Вставить после выбранной"></div>
          </div>
          <div class="scrollpanel-stations">
            <ul class="list-stations" id="list-stations">
            </ul>
          </div>
        </div>

        <div class="panel-station" id="panel-station">

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

      <div class="panel-measurements">
        <div class="survey-toolbar" id="toolbar-survey-measurements">
          <div class="survey-button delete" id="delete-measurement" title="Удалить выбранную"></div>
          <div class="survey-button insert-before" id="before-measurement" title="Вставить перед выбранной"></div>
          <div class="survey-button insert-after" id="after-measurement" title="Вставить после выбранной"></div>
        </div>
        <div class="scrollpanel-measurements">
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
        
        this.setSurveyStation();
        this.setListSurveyStations();
        this.setTableMeasurements();
        this.setListBasePoints();
        this.setMenuImport();
        this.addListenersMainToolbar()
        this.addListenersPanelStations();
        this.addListenersPanelStation();
        this.addListenersPanelMeasurements();

    }

    /**
     * Adds event listeners for main-toolbar
     */
    addListenersMainToolbar() {

      document.getElementById("toolbar-survey").addEventListener('change', event => {
        let element = event.target;

        switch(element.id) {

          case "survey-open-input":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.surveyService.readFromDevice(file).then(() => {
                this.currentSurveyStation = 0;
                this.currentMeasurement = 0;
                this.setListSurveyStations();
                this.setSurveyStation();
                this.setTableMeasurements();
                });
             } catch (error) {
              console.error(error.message);
            }
            break;

          case "survey-import-input":
            let file = element.files[0];
            if (!file) throw new Error("Select a file!");
            this.surveyService.importFromTotalStation(file, this.surveyImportType).then(() => {
                this.currentSurveyStation = 0;
                this.currentMeasurement = 0;              
                this.setListSurveyStations();
                this.setSurveyStation();
                this.setTableMeasurements();
            });
            break;
        }

        
      });

      document.getElementById("toolbar-survey").addEventListener('click', (event) => {
        let element = event.target;
        let importFileInput = document.getElementById("survey-import-input");
        let surveyOpenInput = document.getElementById("survey-open-input");
        let overlay = document.getElementById("overlay");
        let menuImport = document.getElementById("menu-import");        

        switch(element.id) {

          case "survey-new":
            this.surveyService.clearAll();
            this.surveyService.addNewStation();
            this.surveyService.addNewMeasurement(0);
            this.currentSurveyStation = 0;
            this.currentMeasurement = 0;
            this.setListSurveyStations();
            this.setSurveyStation();
            this.setTableMeasurements();
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
            this.surveyImportType = element.id;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");
            importFileInput.setAttribute("accept", ".gsi");
            importFileInput.click();
            break;

          case "import-nikon":
            this.surveyImportType = element.id;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");
            importFileInput.setAttribute("accept", ".raw");
            importFileInput.click();
            break;

          case "import-topcon":
            this.surveyImportType = element.id;
            menuImport.classList.toggle("open");
            overlay.classList.toggle("open");
            importFileInput.setAttribute("accept", ".txt");
            importFileInput.click();
            break;


          case "survey-run":
            break;


        }
      });

    }

    

    /**
     * Adds event listeners for panel-stations
     */
    addListenersPanelStations() {

      document.getElementById("list-stations").addEventListener('click', (event) => {
        let element = event.target;

        if(element.hasAttribute('data-station-id')) {
          this.currentSurveyStation = +element.dataset.stationId;
          this.currentMeasurement = 0;
          this.setSurveyStation();
          this.setTableMeasurements();
        }

      });


      document.getElementById("toolbar-survey-stations").addEventListener('click', (event) => {
        let element = event.target;

        switch(element.id) {
          
          case "delete-station":
            if(this.surveyService.size() > 1) {
              this.surveyService.removeStation(this.currentSurveyStation);
              // document.getElementById("list-stations").innerHTML = '';
              this.setListSurveyStations();
              if(this.currentSurveyStation == this.surveyService.size()){
                this.currentSurveyStation--;
              }
              this.setSurveyStation();
              this.setTableMeasurements();
            }
            break;
          
          case "before-station":
            this.surveyService.insertNewStation(this.currentSurveyStation);
            this.surveyService.addNewMeasurement(this.currentSurveyStation);
            this.setListSurveyStations();
            this.setSurveyStation();
            this.setTableMeasurements();
            break;

          case "after-station":
            this.currentSurveyStation++;
            this.surveyService.insertNewStation(this.currentSurveyStation);
            this.surveyService.addNewMeasurement(this.currentSurveyStation);
            this.setListSurveyStations();
            this.setSurveyStation();
            this.setTableMeasurements();
            break;
        }
      });

    }

    /**
     * Adds event listeners for panel-station
     */
    addListenersPanelStation() {
      let surveyPanelStation = document.getElementById("panel-station");
      let overlay = document.getElementById("overlay");

      surveyPanelStation.addEventListener('click', (event) => {
        let element = event.target;
        let toggleRect = element.getBoundingClientRect(); 
        let panelStationRect = document.getElementById("panel-station").getBoundingClientRect();
        let listBasePoints = document.getElementById("list-base-point");      

        if (element.hasAttribute("data-base-point-id")) {

            document.getElementById("list-base-point").classList.toggle("open");
            document.getElementById("overlay").classList.toggle("open");
            let i = +element.dataset.basePointId;

            if (this.insertBasePointToStation) {
              this.surveyService.saveStationName(
                this.currentSurveyStation, 
                this.basePointService.getBasePointName(i));
              this.surveyService.saveStationX(
                this.currentSurveyStation, 
                this.basePointService.getBasePointX(i)
              );
              this.surveyService.saveStationY(
                this.currentSurveyStation, 
                this.basePointService.getBasePointY(i)
              );
              this.surveyService.saveStationZ(
                this.currentSurveyStation, 
                this.basePointService.getBasePointZ(i)
              );
            } else {
              this.surveyService.saveOrName(
                this.currentSurveyStation, 
                this.basePointService.getBasePointName(i)
              );
              this.surveyService.saveOrX(
                this.currentSurveyStation, 
                this.basePointService.getBasePointX(i)
              );
              this.surveyService.saveOrY(
                this.currentSurveyStation, 
                this.basePointService.getBasePointY(i)
              );

            }
            this.setListSurveyStations();
            this.setSurveyStation();
        }
        
        switch (element.id) {

          case "button-station-name":
            listBasePoints.style.top = `${toggleRect.top - panelStationRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelStationRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.insertBasePointToStation = true;
            break;

          case "button-or-name":
            listBasePoints.style.top = `${toggleRect.top - panelStationRect.top +toggleRect.height + window.scrollY}px`;
            listBasePoints.style.left = `${toggleRect.left - panelStationRect.left + window.scrollX}px`;
            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");
            this.insertBasePointToStation = false;
            break;

        }
      });

      surveyPanelStation.addEventListener('input', (event) => {
        let element = event.target;

        switch(element.id) {

          case "survey-station-name":
            this.surveyService.saveStationName(this.currentSurveyStation, element.value);
            document.getElementById("list-stations").innerHTML = '';
            this.setListSurveyStations();
            break;

          case "survey-station-x":
            this.surveyService.saveStationX(this.currentSurveyStation, element.value);
            break;

          case "survey-station-y":
            this.surveyService.saveStationY(this.currentSurveyStation, element.value);
            break;
            
          case "survey-station-z":
            this.surveyService.saveStationZ(this.currentSurveyStation, element.value);
            break;

          case "survey-station-height":
            this.surveyService.saveStationHeight(this.currentSurveyStation, element.value);
            break;

          case "survey-or-direction":
            this.surveyService.saveOrDirection(this.currentSurveyStation, element.value);
            break;

          case "survey-or-name":
            this.surveyService.saveOrName(this.currentSurveyStation, element.value);
            break;

          case "survey-or-x":
            this.surveyService.saveOrX(this.currentSurveyStation, element.value);
            break;

          case "survey-or-y":
            this.surveyService.saveOrY(this.currentSurveyStation, element.value);
            break;

        }
      });

    }

    /**
     * Adds event listeners for panel-measurement
     */
    addListenersPanelMeasurements() {

      document.getElementById("toolbar-survey-measurements").addEventListener('click', (event) => {
        let element = event.target;

        switch(element.id) {

          case "delete-measurement":
            if(this.surveyService.measurementSize() > 1) {
              this.surveyService.removeMeasurement(this.currentSurveyStation, this.currentMeasurement);
              this.setTableMeasurements();
              if(this.currentMeasurement == this.surveyService.measurementSize(this.currentSurveyStation)) {
                this.currentMeasurement--;
              }
            }
            break;

          case "before-measurement":
            this.surveyService.insertNewMeasurement(this.currentSurveyStation, this.currentMeasurement);
            this.setTableMeasurements();
            break;

          case "after-measurement":
            this.currentMeasurement++;
            this.surveyService.insertNewMeasurement(this.currentSurveyStation, this.currentMeasurement);
            this.setTableMeasurements();
            break;
        }
      });

      document.getElementById("list-measurements").addEventListener('click', (event) => {
        let element = event.target;

        if(element.hasAttribute("data-measurement-id")) {
          this.currentMeasurement = +element.dataset.measurementId;
        }
      });

      document.getElementById("list-measurements").addEventListener('input', (event) => {
        let element = event.target;

        if(element.hasAttribute('data-measurement-id')) {
          switch(element.dataset.target) {

            case "name":
              this.surveyService.saveTargetName(
                this.currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);              
              break;

            case "direction":
              this.surveyService.saveTargetDirection(
                this.currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;

            case "distance":
              this.surveyService.saveTargetDistance(
                this.currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;

            case "tilt":
              this.surveyService.saveTargetTiltAngle(
                this.currentSurveyStation, 
                +element.dataset.measurementId, 
                element.value);
              break;

            case "height":
              this.surveyService.saveTargetHeight(
                this.currentSurveyStation, 
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
    setSurveyStation() {
      if(this.surveyService.size() > 0) {
        document.getElementById("survey-station-name").value = this.surveyService.getStationName(this.currentSurveyStation);
        document.getElementById("survey-station-x").value = this.surveyService.getStationX(this.currentSurveyStation);
        document.getElementById("survey-station-y").value = this.surveyService.getStationY(this.currentSurveyStation);
        document.getElementById("survey-station-z").value = this.surveyService.getStationZ(this.currentSurveyStation);
        document.getElementById("survey-station-height").value = this.surveyService.getStationHeight(this.currentSurveyStation);
        document.getElementById("survey-or-direction").value = this.surveyService.getOrDirection(this.currentSurveyStation);
        document.getElementById("survey-or-name").value = this.surveyService.getOrName(this.currentSurveyStation);
        document.getElementById("survey-or-x").value = this.surveyService.getOrX(this.currentSurveyStation);
        document.getElementById("survey-or-y").value = this.surveyService.getOrY(this.currentSurveyStation);
      }

    }

    /**
     * Creates and displays a list of DOM elements 
     * of all survey stations from the survey repository
     */
    setListSurveyStations() {
      if(this.surveyService.size() > 0) {
        let listSurveyStations = document.getElementById("list-stations");
        listSurveyStations.innerHTML = '';

        for(let i = 0; i < this.surveyService.size(); i++) {
          let station = this.getElementSurveyStation(i);
          listSurveyStations.append(station);
        }
      }
    }


    /**
     * Gets element for list of survey stations
     * @param {number} indexStation 
     * @returns {HtmlElement} 
     */
    getElementSurveyStation(indexStation) {
          let station = document.createElement('li');
          let menuItem = document.createElement('a');
          menuItem.className = "menu-item";
          menuItem.href = "#";
          menuItem.setAttribute('data-station-id', indexStation);
          menuItem.innerHTML = this.surveyService.getStationName(indexStation);
          station.append(menuItem);
          return station;

    }

    /**
     * Creates and displays a list of DOM elements 
     * of all measurements for a station with the currentStation index
     */
    setTableMeasurements() {
      if(this.surveyService.measurementSize(this.currentSurveyStation) > 0) {
        let listMeasurements = document.getElementById("list-measurements");
        listMeasurements.innerHTML = '';
        for(let i = 0; i < this.surveyService.measurementSize(this.currentSurveyStation); i++) {
          let row = this.getElementMeasurement(this.currentSurveyStation, i);
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
    getElementMeasurement(indexStation, indexMeasurement) {
          let row = document.createElement('tr');

          let sell = document.createElement('td');
          let item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'name');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetName(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'direction');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetDirection(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'distance');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetDistance(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'tilt');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetTiltAngle(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.size = "10";
          item.setAttribute('data-target', 'height');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetHeight(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          return row;
    }

    /**
     * Creates and adds a list of base stations to the DOM
     */
    setListBasePoints() {
      const panelStation = document.getElementById("panel-station");
      const listBasePoints = document.createElement('div');

      listBasePoints.className = "pop-up";
      listBasePoints.id = "list-base-point";

      if (this.basePointService.size() > 0) {
        for (let i = 0; i < this.basePointService.size(); i++) {
          let row = document.createElement('div');
          row.className = "menu-item";
          row.setAttribute("data-base-point-id", i);
          row.innerHTML = this.basePointService.getBasePointName(i);
          listBasePoints.append(row);
        }
      }
      panelStation.append(listBasePoints);
    }

    /**
     * Creates and adds pop-up 'menu import' to the DOM
     */
    setMenuImport() {
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
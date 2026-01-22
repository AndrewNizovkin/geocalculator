import {SurveyService} from "../service/SurveyService.js";

/**
 * 
 */
export class SurveyController {

  constructor() {
    this.surveyService = new SurveyService();
    this.currentSurveyStation = 0;
    this.currentMeasurement = 0;
  }

    /**
     * Loaded survey page and adds listeners to components
     */
    loadPageSurvey() {
        let content = document.getElementById("content");

        content.innerHTML = `
    <div class="survey-toolbar" id="toolbar-survey">
      <div class="survey-button new" id="survey-new" title="Новая съёмка"></div>
      <div class="survey-button import" id="survey-import" title="Импорт из файла"></div>
      <div class="survey-button save" id="survey-save" title="Сохранить"></div>
      <div class="survey-toolbar-separator"></div>
      <div class="survey-button run" id="survey-run" title="Обработать"></div>
      <div class="survey-button catalog" id="survey-catalog" title="Установить каталог"></div>
      <input type="file" id="survey-open" name="file" accept=".tah">
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

        <div class="panel-station">

          <table class="table-station" id="survey-table-station">
            <!-- <caption>Параметры станции</caption> -->
            <tbody>
              <tr>
                <td class="menu-item" title="Вставить из каталога">Станция</td>
                <td><input type="text" id="survey-station-name" size="12" placeholder="noname"></td>
              </tr>
              <tr>
                <td>X:</td>
                <td><input type="text" id="survey-station-x" size="12" placeholder="0.000"></td>
              </tr>
              <tr>
                <td>Y:</td>
                <td><input type="text" id="survey-station-y" size="12" placeholder="0.000"></td>
              </tr>
              <tr>
                <td>Z:</td>
                <td><input type="text" id="survey-station-z" size="12" placeholder="0.000"></td>
              </tr>
              <tr>
                <td>i:</td>
                <td><input type="text" id="survey-station-height" size="12" placeholder="0.000"></td>
              </tr>
              <tr>
                <td>Ор.Напр.</td>
                <td><input type="text" id="survey-or-direction" size="12" value="0.0000"></td>
              </tr>
              <tr>
                <td class="menu-item" title="Вставить из каталога">Ориентир</td>
                <td><input type="text" id="survey-or-name" size="12" placeholder="noname"></td>
              </tr>
              <tr>
                <td>X:</td>
                <td><input type="text" id="survey-or-x" size="12" placeholder="0.000"></td>
              </tr>
              <tr>
                <td>Y:</td>
                <td><input type="text" id="survey-or-y" size="12" placeholder="0.000"></td>
              </tr>

            </tbody>
          </table>

        </div>

      </div>

      <div class="panel-measurements">
        <div class="survey-toolbar" id="toolbar-survey-measurements">
          <div class="survey-button delete" id="delete-measurement" title="Удалить выбранную"></div>
          <div class="survey-button insert-before" id="before-measurement" title="Вставить перед выбранной"></div>
          <div class="survey-button insert-after" id="after-measurement" title="Вставить после выбранной"></div>
          <div class="survey-toolbar-separator"></div>
          <div class="survey-button change-distance"></div>
          <div class="survey-button change-direction"></div>
          <div class="survey-button change-tilt"></div>
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

          case "survey-open":
            try {
              let file = element.files[0];
              if (!file) throw new Error("Select a file!");
              this.surveyService.readFromDevice(file).then(() => {
                this.setListSurveyStations();
                this.setSurveyStation();
                this.setTableMeasurements();
                this.currentSurveyStation = 0;
                this.currentMeasurement = 0;
                });
             } catch (error) {
              console.error(error.message);
            }
            break;
        }

        
      });

      document.getElementById("toolbar-survey").addEventListener('click', (event) => {
        let element = event.target;

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
            break;

          case "survey-import":
            break;

          case "survey-save":
            this.surveyService.writeToDevice("hello");            
            break;

          case "survey-run":
            break;

          case "survey-catalog":
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
      document.getElementById("survey-table-station").addEventListener('input', (event) => {
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
          item.setAttribute('data-target', 'name');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetName(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target', 'direction');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetDirection(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target', 'distance');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetDistance(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target', 'tilt');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetTiltAngle(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target', 'height');
          item.setAttribute('data-measurement-id', indexMeasurement);
          item.value = this.surveyService.getTargetHeight(indexStation, indexMeasurement);
          sell.append(item);
          row.append(sell);

          return row;
    }

    /**
     * Loades demo survey
     */
    loadDemoData() {
    this.surveyService.addNewStation();
    this.surveyService.saveStationName(0, "1301");
    this.surveyService.saveStationX(0, "478676.113");
    this.surveyService.saveStationY(0, "2296967.264");
    this.surveyService.saveStationZ(0, "11.220");
    this.surveyService.saveStationHeight(0, "1.538");
    this.surveyService.saveOrDirection(0, "0.0000");
    this.surveyService.saveOrName(0, "1302");
    this.surveyService.saveOrX(0, "478685.352");
    this.surveyService.saveOrY(0, "2296938.168");

    this.surveyService.addNewMeasurement(0);
    this.surveyService.saveTargetName(0, 0, "1302");
    this.surveyService.saveTargetDirection(0, 0, "359.5953");
    this.surveyService.saveTargetDistance(0, 0, "30.562");
    this.surveyService.saveTargetTiltAngle(0, 0, "0.3009");
    this.surveyService.saveTargetHeight(0, 0, "1.600");

    this.surveyService.addNewMeasurement(0);
    this.surveyService.saveTargetName(0, 1, "T100");
    this.surveyService.saveTargetDirection(0, 1, "185.4548");
    this.surveyService.saveTargetDistance(0, 1, "39.878");
    this.surveyService.saveTargetTiltAngle(0, 1, "0.0646");
    this.surveyService.saveTargetHeight(0, 1, "1.600");

    this.surveyService.addNewMeasurement(0);
    this.surveyService.saveTargetName(0, 2, "T101");
    this.surveyService.saveTargetDirection(0, 2, "164.3757");
    this.surveyService.saveTargetDistance(0, 2, "68.564");
    this.surveyService.saveTargetTiltAngle(0, 2, "-0.1338");
    this.surveyService.saveTargetHeight(0, 2, "1.600");



    // add station
    this.surveyService.addNewStation();
    this.surveyService.saveStationName(1, "100");
    this.surveyService.saveStationX(1, "478660.283");
    this.surveyService.saveStationY(1, "2297003.862");
    this.surveyService.saveStationZ(1, "11.231");
    this.surveyService.saveStationHeight(1, "1.58");
    this.surveyService.saveOrDirection(1, "0.0100");
    this.surveyService.saveOrName(1, "1301");
    this.surveyService.saveOrX(1, "478676.113");
    this.surveyService.saveOrY(1, "2296967.264");

    this.surveyService.addNewMeasurement(1);
    this.surveyService.saveTargetName(1, 0, "1301");
    this.surveyService.saveTargetDirection(1, 0, "359.5955");
    this.surveyService.saveTargetDistance(1, 0, "39.884");
    this.surveyService.saveTargetTiltAngle(1, 0, "0.0054");
    this.surveyService.saveTargetHeight(1, 0, "1.600");

    this.surveyService.addNewMeasurement(1);
    this.surveyService.saveTargetName(1, 1, "T101");
    this.surveyService.saveTargetDirection(1, 1, "164.3757");
    this.surveyService.saveTargetDistance(1, 1, "68.564");
    this.surveyService.saveTargetTiltAngle(1, 1, "-0.1338");
    this.surveyService.saveTargetHeight(1, 1, "1.600");

    this.surveyService.addNewMeasurement(1);
    this.surveyService.saveTargetName(1, 2, "74");
    this.surveyService.saveTargetDirection(1, 2, "161.2650");
    this.surveyService.saveTargetDistance(1, 2, "43.653");
    this.surveyService.saveTargetTiltAngle(1, 2, "0.0041");
    this.surveyService.saveTargetHeight(1, 2, "1.600");

    // add station
    this.surveyService.addNewStation();
    this.surveyService.saveStationName(2, "101");
    this.surveyService.saveStationX(2, "478650.714");
    this.surveyService.saveStationY(2, "2297071.740");
    this.surveyService.saveStationZ(2, "10.930");
    this.surveyService.saveStationHeight(2, "1.58");
    this.surveyService.saveOrDirection(2, "0.0100");
    this.surveyService.saveOrName(2, "100");
    this.surveyService.saveOrX(2, "478660.283");
    this.surveyService.saveOrY(2, "2297003.862");

    this.surveyService.addNewMeasurement(2);
    this.surveyService.saveTargetName(2, 0, "T100");
    this.surveyService.saveTargetDirection(2, 0, "359.5954");
    this.surveyService.saveTargetDistance(2, 0, "68.557");
    this.surveyService.saveTargetTiltAngle(2, 0, "0.1741");
    this.surveyService.saveTargetHeight(2, 0, "0.000");

    this.surveyService.addNewMeasurement(2);
    this.surveyService.saveTargetName(2, 1, "T102");
    this.surveyService.saveTargetDirection(2, 1, "141.4810");
    this.surveyService.saveTargetDistance(2, 1, "49.147");
    this.surveyService.saveTargetTiltAngle(2, 1, "-0.0948");
    this.surveyService.saveTargetHeight(2, 1, "1.600");

    }

}
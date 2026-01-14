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
    <div class="survey-toolbar">
      <div class="survey-button new" title="Создать"></div>
      <div class="survey-button open" title="Открыть"></div>
      <div class="survey-button import" title="Импорт из файла"></div>
      <div class="survey-button save" title="Сохранить"></div>
      <div class="survey-toolbar-separator"></div>
      <div class="survey-button run" title="Обработать"></div>
      <div class="survey-button catalog" title="Установить каталог"></div>
    </div>

    <div class="panel-survey">
      
      <div class="panel-basis">
        <div class="panel-stations">
          <div class="survey-toolbar">
            <div class="survey-button delete"></div>
            <div class="survey-button insert-before"></div>
            <div class="survey-button insert-after"></div>
          </div>
          <div class="scrollpanel-stations">
            <ul class="list-stations" id="list-stations">
              <li><a class="menu-item" href="#" data-station-id="0">1301</a></li>
              <li><a class="menu-item" href="#" data-station-id="1">100</a></li>
              <li><a class="menu-item" href="#" data-station-id="2">101</a></li>
            </ul>
          </div>
        </div>

        <div class="panel-station">

          <table class="table-station">
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
        <div class="survey-toolbar">
          <div class="survey-button delete"></div>
          <div class="survey-button insert-before"></div>
          <div class="survey-button insert-after"></div>
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
              <!-- Демо данные -->
              <tr>
                <td><input type="text" class="menu-item" data-target-name="0" value="1302"/></td>
                <td><input type="text" class="menu-item" data-target-direction="0" value="359.5953"/></td>
                <td><input type="text" class="menu-item" data-target-distance="0" value="30.526"/></td>
                <td><input type="text" class="menu-item" data-target-tilt="0" value="-0.5959"/></td>
                <td><input type="text" class="menu-item" data-target-height="0" value="1302"/></td>
              </tr>
              <tr>
                <td><input type="text" class="menu-item" data-target-name="1" value="1302"/></td>
                <td><input type="text" class="menu-item" data-target-direction="1" value="359.5953"/></td>
                <td><input type="text" class="menu-item" data-target-distance="1" value="30.526"/></td>
                <td><input type="text" class="menu-item" data-target-tilt="1" value="-0.5959"/></td>
                <td><input type="text" class="menu-item" data-target-height="1" value="1302"/></td>
              </tr>
              <!-- Демо данные -->
            </tbody>

          </table>
        </div>
      </div>

    </div>

      
        `;

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
        for(let i = 0; i < this.surveyService.size(); i++) {
          let station = document.createElement('li');
          let menuItem = document.createElement('a');
          menuItem.className = "menu-item";
          menuItem.href = "#";
          menuItem.setAttribute('data-station-id', i);
          menuItem.innerHTML = this.surveyService.getStationName(i);
          station.append(menuItem);
          listSurveyStations.append(station);
        }
      }
      

    }

    /**
     * Creates and displays a list of DOM elements 
     * of all measurements for a station with the currentStation index
     */
    setTableMeasurements() {
      if(this.surveyService.measurementSize(this.currentSurveyStation) > 0) {
        let listMeasurements = document.getElementById("list-measurements");
        for(let i = 0; i < this.surveyService.measurementSize(this.currentSurveyStation); i++) {
          let row = document.createElement('tr');

          let sell = document.createElement('td');
          let item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target-name', i);
          item.value = this.surveyService.getTargetName(this.currentSurveyStation, i);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target-direction', i);
          item.value = this.surveyService.getTargetDirection(this.currentSurveyStation, i);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target-distance', i);
          item.value = this.surveyService.getTargetDistance(this.currentSurveyStation, i);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target-tilt', i);
          item.value = this.surveyService.getTargetTiltAngle(this.currentSurveyStation, i);
          sell.append(item);
          row.append(sell);

          sell = document.createElement('td');
          item = document.createElement('input');
          item.type = "text";
          item.className = "menu-item";
          item.setAttribute('data-target-height', i);
          item.value = this.surveyService.getTargetHeight(this.currentSurveyStation, i);
          sell.append(item);
          row.append(sell);

          listMeasurements.append(row);

        }

      }
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

    }

}
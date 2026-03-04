import { PolygonService } from "../service/PolygonService.js";

/**
 * Encapsulates the components of the "Polygon" screen 
 * and the methods for their interaction with 
 * the user and the model.
 * @author Nizovkin A.V.
 * @copyright 2025 Nizovkin A.V.
 */
export class PolygonContoller {
    #currentStation;
    #reportsIsActual;
    #polygonService;
    #basePointService;

    /**
     * @constructor
     * @param {BasePointService} basePointService 
     */
    constructor(basePointService) {
        this.#polygonService = new PolygonService();
        this.#polygonService.addNewStation();
        this.#currentStation = 0;
        this.#reportsIsActual = false;
        this.#basePointService = basePointService;
    }

    /**
     * Gets a reference to the object #polygonService
     * @returns {PolygonService}
     */
    getPolygonService() {
        return this.#polygonService;
    }

    /**
     * Loads page Polygon
     */
    loadPagePolygon() {
        const content = document.getElementById("content");

        content.innerHTML = `
        <div class="survey-toolbar" id="toolbar-polygon">
            <div class="survey-button new" id="polygon-new" title="Новый полигон"></div>
            <div class="survey-button open" id="polygon-open" title="Открыть"></div>

            <div class="survey-toolbar-separator"></div>

            <div class="survey-button run" id="polygon-run" title="Уравнять полигон"></div>
            <div class="survey-button view" id="polygon-view" title="Просмотр результатов"></div>
            <input type="file" id="polygon-open-input" accept=".pol">
            <input type="file" id="polygon-open-response" accept=".txt">
        </div>

        <div class="panel" id="panel-polygon">

            <div class="survey-toolbar" id="toolbar-polygon-table">
                <div class="survey-button delete" id="delete-station" title="Удалить станцию"></div>
                <div class="survey-button insert-before" id="before-station" title="Вставить перед выбранной"></div>
                <div class="survey-button insert-after" id="after-station" title="Вставить после выбранной"></div>
                <div class="survey-button catalog toggle" id="catalog" title="Вставить из каталога"></div>
            </div>

            <div class="panel" id="scrollpanel-polygon">

                <table class="table-polygon">
                    <thead>
                        <th title="Название точки">Название</th>
                        <th title="Горизонтальный угол левый, г.ммсс">&#946;<span>лев</span></th>
                        <th title="Горизонтальное проложение, м.">L<span>гор</span></th>
                        <th title="Превышение, м.">&#916;Z</th>
                        <th title="Координата X, м.">X</th>
                        <th title="Координата Y, м.">Y</th>
                        <th title="Координата Z, м.">Z</th>
                        <th title="Планово-высотная опорная сеть">ПВОС</th>
                    </thead>
                    <tbody id="list-poligon-stations">
                    </tbody>

                </table>

            </div>
        </div>

        <div class="panel" id="panel-polygon-processing">

            <div class="panel" id="panel-polygon-residuals">
                
                <div class="panel-title">Невязки фактические</div>

                <div class="row-residual">
                    <div>Высотная, м.</div>
                    <div id="residual-height"></div>
                </div>

                <div class="row-residual">
                    <div>Угловая, сек.</div>
                    <div id="residual-direction"></div>
                </div>
                
                <div class="row-residual">
                    <div>Линейная Fx, м.</div>
                    <div id="residual-x"></div>
                </div>                

                <div class="row-residual">
                    <div>Линейная Fy, м.</div>
                    <div id="residual-y"></div>
                </div>                

                <div class="row-residual">
                    <div>Абсолютная, м.</div>
                    <div id="residual-absolute"></div>
                </div>                

                <div class="row-residual">
                    <div>Относительная</div>
                    <div id="residual-relative"></div>
                </div>                

                <div class="row-residual">
                    <div>Периметр</div>
                    <div id="residual-perimeter"></div>
                </div>                


            </div>

            <div class="panel" id="panel-valid-residuals">


            </div>

        </div>
        
        `;

        this.#setListPolygonStations();
        this.#setListBasePoints();
        this.#setResiduals();
        this.#setValidResiduals();

        this.#addListenersPanelPolygon();
        this.#addListenersToolbarPolygon();
        this.#addListenersPanelValidResiduals();

    }

    /**
     * Loads page polygon report to content
     */
    #loadPageReportPolygon() {
        const panelReports = document.getElementById("reports");

        panelReports.innerHTML = `
            <a href="#" class="button" title="Закрыть" id="reports-close">Закрыть</a>        
            <div class="panel" id="panel-plan-report">
                <div class="panel-title">Ведомость вычисления координат</div>
                <div class="frame">
                    <textarea class="text-report" cols="130" id="text-plan-report" placeholder="Ведомость уравнивания плановая"></textarea>
                </div>

            </div>

            <div class="panel" id="panel-level-report">
                <div class="panel-title">Ведомость вычисления высот</div>
                <div class="frame">
                    <textarea class="text-report" id="text-elevation-report" placeholder="Ведомость уравнивания высотная"></textarea>
                </div>

            </div>

            <div class="panel" id="panel-catalog-pol-report">

                <div class="panel" id="panel-catalog-report">
                    <div class="panel-title">Каталог координат точек полигона</div>
                    <div class="frame">
                        <textarea class="text-report" id="text-catalog-report" placeholder="Каталог координат тахеометрического хода"></textarea>
                    </div>

                </div>

                <div class="panel" id="panel-pol-report">
                    <div class="panel-title">Исходные данные (*.pol)</div>
                    <div class="frame">
                        <textarea class="text-report" id="text-pol-report" placeholder="Исходные данные в формате '*.pol'"></textarea>
                    </div>

                </div>
            </div>

        `;
        panelReports.classList.toggle("open");
        this.#setReports();

    }

    /**
     * Gets list of table row for table-polygon
     */
    #setListPolygonStations() {
        const listPoligonStations = document.getElementById("list-poligon-stations");
        listPoligonStations.innerHTML = '';

        for (let i = 0; i < this.#polygonService.size(); i++) {
            const row = document.createElement('tr');
            row.setAttribute("data-row-id", i);

            let status = this.#polygonService.getStatus(i);
            // if (this.#polygonService.getStationX(i) != "Not") status = true;
            
            let cell = document.createElement('td');
            cell.className = "line";
            let item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "name");
            item.size = "8";
            item.value = this.#polygonService.getStationName(i);
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "hor-angle");
            item.size = "7";
            if (this.#polygonService.getHorAngle(i) == "Not") {
                item.value = '';
            } else {
                item.value = this.#polygonService.getHorAngle(i);
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line-shift";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "hor-distance");
            item.size = "5";
            if (this.#polygonService.getHorDistance(i) == "Not") {
                item.value = '';
            } else {
                item.value = this.#polygonService.getHorDistance(i);
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line-shift";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "elevation");
            item.size = "5";
            if (this.#polygonService.getElevation(i) == "Not") {
                item.value = '';
            } else {
                item.value = this.#polygonService.getElevation(i);
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "x");
            item.size = "10";
            item.disabled = true;
            if (this.#polygonService.getStatus(i)) {
                if ( (i > 1) && ( i < this.#polygonService.size() - 2) ) {
                    this.#polygonService.saveStationX(i, "Not");
                    this.#polygonService.saveStatus(i, false);
                } else {
                    item.value = this.#polygonService.getStationX(i);
                    item.disabled = false;
                }                
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "y");
            item.size = "10";
            item.disabled = true;
            if (this.#polygonService.getStatus(i)) {
                if ( (i > 1) && ( i < this.#polygonService.size() - 2) ) {
                    this.#polygonService.saveStationY(i, "Not");
                    this.#polygonService.saveStatus(i, false);
                } else {
                    item.value = this.#polygonService.getStationY(i);
                    item.disabled = false;
                }                
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "z");
            item.size = "6";
            item.disabled = true;
            if (this.#polygonService.getStatus(i)) {
                if ( (i > 1) && ( i < this.#polygonService.size() - 2) ) {
                    this.#polygonService.saveStationZ(i, "Not");
                    this.#polygonService.saveStatus(i, false);
                } else {
                    item.value = this.#polygonService.getStationZ(i);
                    item.disabled = false;
                }                
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line";
            let cellContainer = document.createElement('div');
            cellContainer.className = "table-cell";
            item = document.createElement('input')
            item.type = "checkbox";
            item.setAttribute("data-station-id", i);
            item.setAttribute("data-property", "status");
            if (this.#polygonService.getStatus(i)) item.setAttribute("checked", this.#polygonService.getStatus(i));
            if ( (i > 1) && ( i < this.#polygonService.size() - 2) ) {
                item.unchecked = true;
                item.disabled = true;
            }
            cellContainer.append(item);
            cell.append(cellContainer);
            row.append(cell);

            listPoligonStations.append(row);

        }
    }

    /**
     * Creates and adds a list of base stations to the DOM
     */
    #setListBasePoints() {
      const toolbarPolygonTable = document.getElementById("toolbar-polygon-table");
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
      toolbarPolygonTable.append(listBasePoints);
    }

    /**
     * Sets value of elemnts on panel-polygon-residuals
     */
    #setResiduals() {
        document.getElementById("residual-height").innerHTML = this.#polygonService.getResidualElevation();
        document.getElementById("residual-direction").innerHTML = this.#polygonService.getResidualAngle();
        document.getElementById("residual-x").innerHTML = this.#polygonService.getResidualX();
        document.getElementById("residual-y").innerHTML = this.#polygonService.getResidualY();
        document.getElementById("residual-absolute").innerHTML = this.#polygonService.getResidualAbsolute();
        document.getElementById("residual-relative").innerHTML = this.#polygonService.getResidualRelative();
        document.getElementById("residual-perimeter").innerHTML = this.#polygonService.getPerimeter();
    }

    /**
     * Creates and append to DOM panel-valid-residuals
     */
    #setValidResiduals() {
        const panelValidResiduals = document.getElementById("panel-valid-residuals");

        let panelTitle = document.createElement('div');
        panelTitle.className = "panel-title";
        panelTitle.innerHTML = "Невязки допустимые";
        panelValidResiduals.append(panelTitle);

        //#region vilid-height
        let rowResidual = document.createElement('div');
        rowResidual.className = "row-residual";

        let lblRow = document.createElement('div');
        lblRow.innerHTML = "Высотная, мм.";
        rowResidual.append(lblRow);

        let valueRow = document.createElement('select');
        valueRow.id = "vilid-height";
        let valueArray = ["5", "10", "20", "50"];
        for (let value of valueArray) {
            let option = document.createElement('option');
            option.value = value;
            option.innerHTML = `${value}&#8730;L`;
            if (value === this.#polygonService.getValidElevation()) {
                option.selected = true;
            }
            valueRow.append(option);
        }
        rowResidual.append(valueRow);
        panelValidResiduals.append(rowResidual);
        //#endregion

        //#region valid-direction
        rowResidual = document.createElement('div');
        rowResidual.className = "row-residual";

        lblRow = document.createElement('div');
        lblRow.innerHTML = "Угловая, сек.";
        rowResidual.append(lblRow);

        valueRow = document.createElement('select');
        valueRow.id = "vilid-direction";
        valueArray = ["2", "5", "10", "20", "30"];
        for (let value of valueArray) {
            let option = document.createElement('option');
            option.value = value;
            option.innerHTML = `${value}&#8730;n`;
            if (value === this.#polygonService.getValidAngle()) {
                option.selected = true;
            }
            valueRow.append(option);
        }
        rowResidual.append(valueRow);
        panelValidResiduals.append(rowResidual);
        //#endregion

        //#region valid-absolute
        rowResidual = document.createElement('div');
        rowResidual.className = "row-residual";

        lblRow = document.createElement('div');
        lblRow.innerHTML = "Абсолютная, м.";
        rowResidual.append(lblRow);

        valueRow = document.createElement('select');
        valueRow.id = "vilid-absolute";
        valueArray = ["0.01", "0.05", "0.10", "0.20", "0.30"];
        for (let value of valueArray) {
            let option = document.createElement('option');
            option.value = value;
            option.innerHTML = value;
            if (value === this.#polygonService.getValidAbsolute()) {
                option.selected = true;
            }
            valueRow.append(option);
        }
        rowResidual.append(valueRow);
        panelValidResiduals.append(rowResidual);
        //#endregion

        //#region valid-relative
        rowResidual = document.createElement('div');
        rowResidual.className = "row-residual";

        lblRow = document.createElement('div');
        lblRow.innerHTML = "Относительная";
        rowResidual.append(lblRow);

        valueRow = document.createElement('select');
        valueRow.id = "vilid-relative";
        valueArray = ["1:1000", "1:2000", "1:5000", "1:10000", "1:20000"];
        for (let value of valueArray) {
            let option = document.createElement('option');
            option.value = value;
            option.innerHTML = value;
            if (value === this.#polygonService.getValidRelative()) {
                option.selected = true;
            }
            valueRow.append(option);
        }
        rowResidual.append(valueRow);
        panelValidResiduals.append(rowResidual);
        //#endregion


    }

    /**
     * Sets value of polygon reports 
     */
    #setReports() {
        const reportPlan = document.getElementById("text-plan-report");
        reportPlan.value = this.#polygonService.getReportPlan().join('\n');

        const reportElevation = document.getElementById("text-elevation-report");
        reportElevation.value = this.#polygonService.getReportElevation().join('\n');

        const reportCatalog = document.getElementById("text-catalog-report");
        reportCatalog.value = this.#polygonService.getReportCatalog().join('\n');

        const reportPol = document.getElementById("text-pol-report");
        reportPol.value = this.#polygonService.getLinesPolArray().join('\n');
    }

    /**
     * Adds event listeners to toolar-polygon
     */
    #addListenersToolbarPolygon() {
        const toolbarPolygon = document.getElementById("toolbar-polygon");

        toolbarPolygon.addEventListener('change', (event) => {
            let element = event.target;

            switch (element.id) {

                case "polygon-open-input":
                    try {
                    let file = element.files[0];
                    if (!file) throw new Error("Select a file!");
                        this.#polygonService.readFromDevice(file).then(() =>{
                            this.#currentStation = 0;
                            this.#setListPolygonStations();
                            this.#setResiduals();
                            this.#reportsIsActual = file;
                        });
                    } catch (error) {
                    console.error(error.message);
                    }
                    break;

                case "polygon-open-response":
                    try {
                    let file = element.files[0];
                    if (!file) throw new Error("Select a file!");
                        this.#polygonService.calculatePolygon(file).then(() =>{
                            this.#setResiduals();
                            this.#reportsIsActual = true;
                        });
                    } catch (error) {
                    console.error(error.message);
                    }
                    break;

            }
        });

        toolbarPolygon.addEventListener('click', (event) => {
            const element = event.target;
            switch (element.id) {

                case "polygon-new":
                    this.#polygonService.clearAll();
                    this.#polygonService.clearReports();
                    this.#polygonService.addNewStation();
                    this.#setListPolygonStations();
                    this.#setResiduals();
                    this.#currentStation = 0;
                    this.#reportsIsActual = false;
                    break;

                case "polygon-open":
                    document.getElementById("polygon-open-input").click();
                    break;

                case "polygon-run":
                    document.getElementById("polygon-open-response").click();
                    break;

                case "polygon-view":
                    this.#loadPageReportPolygon();
                    break;
            }
            
        });

    }

    /**
     * Adds event listeners to panel-polygon
     */
    #addListenersPanelPolygon() {
        const panelPolygon = document.getElementById("panel-polygon");
        const toolbarPolygonTable = document.getElementById("toolbar-polygon-table");

        panelPolygon.addEventListener('click', (event) => {
            const element = event.target;
            let selector = `input[data-station-id="${this.#currentStation}"]`;
            let currentStatus = document.querySelector(selector).checked;
            let toggleRect = element.getBoundingClientRect(); 
            let toolbarPolygonRect = toolbarPolygonTable.getBoundingClientRect();

            let listBasePoints = document.getElementById("list-base-point");      
            let overlay = document.getElementById("overlay");

            if (element.hasAttribute("data-base-point-id")) {

                listBasePoints.classList.toggle("open");
                overlay.classList.toggle("open");

                if ( ((this.#currentStation <= 1) || (this.#currentStation >= this.#polygonService.size() - 2)) && (currentStatus) ) {
                    let basePointId = +element.dataset.basePointId;
                    this.#polygonService.saveStationName(this.#currentStation, this.#basePointService.getBasePointName(basePointId));
                    this.#polygonService.saveStationX(this.#currentStation, this.#basePointService.getBasePointX(basePointId));
                    this.#polygonService.saveStationY(this.#currentStation, this.#basePointService.getBasePointY(basePointId));
                    this.#polygonService.saveStationZ(this.#currentStation, this.#basePointService.getBasePointZ(basePointId));
                }
                this.#setListPolygonStations();
            }
            
            

            if (element.hasAttribute('data-polygon-station-id')) {
                this.#currentStation = +element.dataset.polygonStationId;
            }

            switch (element.id) {

                case "delete-station":
                    if (this.#polygonService.size() > 1) {
                        this.#polygonService.removeStation(this.#currentStation);
                        this.#setListPolygonStations();
                        if (this.#currentStation == this.#polygonService.size()) {
                            this.#currentStation--;
                        }
                    }
                    break;

                case "before-station":
                    this.#polygonService.insertNewStation(this.#currentStation);
                    this.#setListPolygonStations();
                    break;

                case "after-station":
                    this.#currentStation++;
                    this.#polygonService.insertNewStation(this.#currentStation);
                    this.#setListPolygonStations();
                    break;

                case "catalog":
                    listBasePoints.style.top = `${toggleRect.top - toolbarPolygonRect.top +toggleRect.height + window.scrollY}px`;
                    listBasePoints.style.left = `${toggleRect.left - toolbarPolygonRect.left + window.scrollX}px`;
                    listBasePoints.classList.toggle("open");
                    overlay.classList.toggle("open");
                    break;
            }

            
        });

        panelPolygon.addEventListener('input', (event) => {
            const element = event.target;

            if (element.hasAttribute('data-property')) {
                if (this.#reportsIsActual) {
                    this.#polygonService.clearReports();
                    this.#reportsIsActual = false;
                    this.#setResiduals();
                }
            }
        });

        panelPolygon.addEventListener('change', (event) => {
            const element = event.target;

            if (element.hasAttribute('data-property')) {
                switch (element.dataset.property) {

                    case "name":
                        this.#polygonService.saveStationName(+element.dataset.polygonStationId, element.value);
                        break;

                    case "hor-angle":
                        this.#polygonService.saveHorAngle(+element.dataset.polygonStationId, element.value);
                        break;

                    case "hor-distance":
                        this.#polygonService.saveHorDistance(+element.dataset.polygonStationId, element.value);
                        break;

                    case "elevation":
                        this.#polygonService.saveElevation(+element.dataset.polygonStationId, element.value);
                        break;

                    case "x":
                        this.#polygonService.saveStationX(+element.dataset.polygonStationId, element.value);
                        break;

                    case "y":
                        this.#polygonService.saveStationY(+element.dataset.polygonStationId, element.value);
                        break;

                    case "z":
                        this.#polygonService.saveStationZ(+element.dataset.poliygonStationId, element.value);
                        break;

                    case "status":
                        this.#currentStation = +element.dataset.stationId;
                        if (element.checked) {
                            this.#polygonService.saveStationX(+element.dataset.stationId, "0.000");
                            this.#polygonService.saveStationY(+element.dataset.stationId, "0.000");
                            this.#polygonService.saveStationZ(+element.dataset.stationId, "0.000");
                            this.#polygonService.saveStatus(+element.dataset.stationId, true);

                        } else {
                            this.#polygonService.saveStationX(+element.dataset.stationId, "Not");
                            this.#polygonService.saveStationY(+element.dataset.stationId, "Not");
                            this.#polygonService.saveStationZ(+element.dataset.stationId, "Not");
                            this.#polygonService.saveStatus(+element.dataset.stationId, false);

                        }
                        this.#setListPolygonStations();
                        break;
                }
            }
        }); 

    }

    /**
     * Adds event listeners to panel-valid-residuals
     */
    #addListenersPanelValidResiduals() {
        const panelValidResiduals = document.getElementById("panel-valid-residuals");

        panelValidResiduals.addEventListener('change', (event) => {
            const element = event.target;

            switch (element.id) {

                case "vilid-height":
                    this.#polygonService.saveValidElevation(element.value);
                    break;

                case "vilid-direction":
                    this.#polygonService.saveValidAngle(element.value);
                    break;

                case "vilid-absolute":
                    this.#polygonService.saveValidAbsolute(element.value);
                    break;

                case "vilid-relative":
                    this.#polygonService.saveValidRelative(element.value);
                    break;
            }

        });

    }

}
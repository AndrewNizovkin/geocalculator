import { PolygonService } from "../service/PolygonService.js";

/**
 * Encapsulates the components of the "Polygon" screen 
 * and the methods for their interaction with 
 * the user and the model.
 */
export class PolygonContoller {
    #currentStation = 0;
    constructor(basePointService) {
        this.polygonService = new PolygonService();
        this.polygonService.addNewStation();
        this.#currentStation = 0;
        this.basePointService = basePointService;

        // this.#fillDemo();

    }

    /**
     * Заполняет репозиторий демо данными
     */
    #fillDemo() {
    for (let i = 0; i < 5; i++) {
        this.polygonService.addNewStation();
        
        this.polygonService.saveStationName(-1, `station-${i + 1}`);
        this.polygonService.saveHorAngle(-1, "359.5959");
        this.polygonService.saveHorDistance(-1, "69.999");
        this.polygonService.saveElevation(-1, "-1.356");
        if (i == 0) {
            this.polygonService.saveStationX(-1, "478676.113");
            this.polygonService.saveStationY(-1, "2297003.862");
            this.polygonService.saveStationZ(-1, "12.630");
        } else {
            this.polygonService.saveStationX(-1, "Not");
            this.polygonService.saveStationY(-1, "Not");
            this.polygonService.saveStationZ(-1, "Not");
        }
        
    }

    }


    /**
     * Loads page Polygon
     */
    loadPagePolygon() {
        const content = document.getElementById("content");

        content.innerHTML = `
        <div class="survey-toolbar" id="toolbar-polygon">
            <div class="survey-button delete" id="delete-station" title="Удалить станцию"></div>
            <div class="survey-button insert-before" id="before-station" title="Вставить перед выбранной"></div>
            <div class="survey-button insert-after" id="after-station" title="Вставить после выбранной"></div>
            <div class="survey-button catalog toggle" id="catalog" title="Вставить из каталога"></div>
            <div class="survey-toolbar-separator"></div>

            <div class="survey-button new" id="polygon-new" title="Новый полигон"></div>
            <div class="survey-button open" id="polygon-open" title="Открыть"></div>
            <div class="survey-toolbar-separator"></div>
            <div class="survey-button run" id="polygon-run" title="Уравнять полигон"></div>
            <div class="survey-button view" id="polygon-view" title="Просмотр результатов"></div>
            <input type="file" id="polygon-open-input" accept=".pol">
        </div>

        <div class="panel-polygon" id="panel-polygon">

            <div class="scrollpanel-polygon">

                <table class="table-polygon">
                    <thead>
                        <th>Название</th>
                        <th>Гор.Угол</th>
                        <th>Гор.Длина</th>
                        <th>Превышение</th>
                        <th>X</th>
                        <th>Y</th>
                        <th>Z</th>
                        <th>ПВОС</th>
                    </thead>
                    <tbody id="list-poligon-stations">

                    </tbody>

                </table>

            </div>
        </div>

        <div class="panel-polygon-processing">

            <div class="panel-polygon-residuals">
                
                <div class="panel-title">Невязки</div>

                <div class="row-residual">
                    <div>Высотная, м.</div>
                    <div id="residual-height">-.-</div>
                </div>

                <div class="row-residual">
                    <div>Угловая, сек.</div>
                    <div id="residual-direction">-.-</div>
                </div>
                
                <div class="row-residual">
                    <div>Линейная Fx, м.</div>
                    <div id="residual-x">-.-</div>
                </div>                

                <div class="row-residual">
                    <div>Линейная Fy, м.</div>
                    <div id="residual-y">-.-</div>
                </div>                

                <div class="row-residual">
                    <div>Абсолютная, м.</div>
                    <div id="residual-absolute">-.-</div>
                </div>                

                <div class="row-residual">
                    <div>Относительная</div>
                    <div id="residual-relative">-.-</div>
                </div>                

                <div class="row-residual">
                    <div>Периметр</div>
                    <div id="residual-perimeter">-.-</div>
                </div>                


            </div>

            <div class="panel-polygon-plan">
                <h4>Схема полигона</h4>
            </div>

        </div>
        
        `;

        this.#setListPolygonStations();
        this.#setListBasePoints();

        this.#addListenersPanelPolygon();
        this.#addListenersToolbarPolygon();
        // this.#toggleSelectedRow(this.#currentStation);

    }

    /**
     * Gets list of table row for table-polygon
     */
    #setListPolygonStations() {
        const listPoligonStations = document.getElementById("list-poligon-stations");
        listPoligonStations.innerHTML = '';

        for (let i = 0; i < this.polygonService.size(); i++) {
            const row = document.createElement('tr');
            row.setAttribute("data-row-id", i);

            let status = false;
            if (this.polygonService.getStationX(i) != "Not") status = true;
            
            let cell = document.createElement('td');
            cell.className = "line";
            let item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "name");
            item.size = "10";
            item.value = this.polygonService.getStationName(i);
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "hor-angle");
            item.size = "8";
            if (this.polygonService.getHorAngle(i) == "Not") {
                item.value = '';
            } else {
                item.value = this.polygonService.getHorAngle(i);
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line-shift";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "hor-distance");
            item.size = "8";
            if (this.polygonService.getHorDistance(i) == "Not") {
                item.value = '';
            } else {
                item.value = this.polygonService.getHorDistance(i);
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            cell.className = "line-shift";
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-polygon-station-id", i);
            item.setAttribute("data-property", "elevation");
            item.size = "7";
            if (this.polygonService.getElevation(i) == "Not") {
                item.value = '';
            } else {
                item.value = this.polygonService.getElevation(i);
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
            if (status) {
                if ( (i > 1) && ( i < this.polygonService.size() - 2) ) {
                    this.polygonService.saveStationX(i, "Not");
                    status = false;
                } else {
                    item.value = this.polygonService.getStationX(i);
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
            if (status) {
                if ( (i > 1) && ( i < this.polygonService.size() - 2) ) {
                    this.polygonService.saveStationY(i, "Not");
                    status = false;
                } else {
                    item.value = this.polygonService.getStationY(i);
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
            item.size = "10";
            item.disabled = true;
            if (status) {
                if ( (i > 1) && ( i < this.polygonService.size() - 2) ) {
                    this.polygonService.saveStationZ(i, "Not");
                    status = false;
                } else {
                    item.value = this.polygonService.getStationZ(i);
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
            // item.size = "10";
            if (status) item.setAttribute("checked", true);
            if ( (i > 1) && ( i < this.polygonService.size() - 2) ) {
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
      const toolbarPolygon = document.getElementById("toolbar-polygon");
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
      toolbarPolygon.append(listBasePoints);
    }


    /**
     * Toggles the selected row
     * @param {number} selectedRow 
     */
    #toggleSelectedRow(indexRow) {
        // let selector = `tr [data-row-id="${indexRow}"]`;
        let selector = `tr[data-row-id="${indexRow}"]`;
        let selectedRow = document.querySelector(selector);
        selectedRow.classList.toggle("selected");
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
                this.polygonService.readFromDevice(file).then(() =>{
                    this.#currentStation = 0;
                    this.#setListPolygonStations();
                });
             } catch (error) {
              console.error(error.message);
            }
                    break;
            }
        });

        toolbarPolygon.addEventListener('click', (event) => {
            const elem = event.target;
            let toggleRect = elem.getBoundingClientRect(); 
            let toolbarPolygonRect = toolbarPolygon.getBoundingClientRect();
            let listBasePoints = document.getElementById("list-base-point");      
            let overlay = document.getElementById("overlay");

            let selector = `input[data-station-id="${this.#currentStation}"]`;
            let currentStatus = document.querySelector(selector).checked;


            if (elem.hasAttribute("data-base-point-id")) {

            listBasePoints.classList.toggle("open");
            overlay.classList.toggle("open");

            if ( ((this.#currentStation <= 1) || (this.#currentStation >= this.polygonService.size() - 2)) && (currentStatus) ) {
                let basePointId = +elem.dataset.basePointId;
                this.polygonService.saveStationName(this.#currentStation, this.basePointService.getBasePointName(basePointId));
                this.polygonService.saveStationX(this.#currentStation, this.basePointService.getBasePointX(basePointId));
                this.polygonService.saveStationY(this.#currentStation, this.basePointService.getBasePointY(basePointId));
                this.polygonService.saveStationZ(this.#currentStation, this.basePointService.getBasePointZ(basePointId));
            }
            this.#setListPolygonStations();

            // if (this.insertCoordinateToBase) {
            //     this.directService.saveBaseX(this.basePointService.getBasePointX(basePointId));
            //     this.directService.saveBaseY(this.basePointService.getBasePointY(basePointId));
            //     this.directService.saveBaseZ(this.basePointService.getBasePointZ(basePointId));
            // } else {
            //     this.directService.saveLandmarkX(this.basePointService.getBasePointX(basePointId));
            //     this.directService.saveLandmarkY(this.basePointService.getBasePointY(basePointId));
            // }

            // this.setData();

        }



            switch (elem.id) {

                case "delete-station":
                    if (this.polygonService.size() > 1) {
                        this.polygonService.removeStation(this.#currentStation);
                        this.#setListPolygonStations();
                        if (this.#currentStation == this.polygonService.size()) {
                            this.#currentStation--;
                        }
                    }
                    break;

                case "before-station":
                    this.polygonService.insertNewStation(this.#currentStation);
                    this.#setListPolygonStations();
                    break;

                case "after-station":
                    this.#currentStation++;
                    this.polygonService.insertNewStation(this.#currentStation);
                    this.#setListPolygonStations();
                    break;

                case "catalog":
                    listBasePoints.style.top = `${toggleRect.top - toolbarPolygonRect.top +toggleRect.height + window.scrollY}px`;
                    listBasePoints.style.left = `${toggleRect.left - toolbarPolygonRect.left + window.scrollX}px`;
                    listBasePoints.classList.toggle("open");
                    overlay.classList.toggle("open");

                    break;

                case "polygon-new":
                    this.polygonService.clearAll();
                    this.polygonService.addNewStation();
                    this.#setListPolygonStations();
                    break;

                case "polygon-open":
                    document.getElementById("polygon-open-input").click();
                    break;

                case "polygon-run":
                    break;

                case "polygon-view":
                    break;
            }
            
        });

    }

    /**
     * Adds event listeners to panel-polygon
     */
    #addListenersPanelPolygon() {
        const panelPolygon = document.getElementById("panel-polygon");

        panelPolygon.addEventListener('click', (event) => {
            const elem = event.target;
            let selector = `input[data-station-id="${this.#currentStation}"]`;
            let currentStatus = document.querySelector(selector).checked;
            // let buttonCatalog = document.getElementById('catalog');

            if (elem.hasAttribute('data-polygon-station-id')) {
                // this.#toggleSelectedRow(this.#currentStation);
                this.#currentStation = +elem.dataset.polygonStationId;

                // if ( ((this.#currentStation <= 1) || (this.#currentStation >= this.polygonService.size() - 2)) && (currentStatus) ) {
                //     buttonCatalog.disabled = false;
                // } else {
                //     buttonCatalog.disabled = true;
                // }
                // this.#toggleSelectedRow(this.#currentStation);
            }
        });

        panelPolygon.addEventListener('change', (event) => {
            const elem = event.target;

            if (elem.hasAttribute('data-property')) {
                switch (elem.dataset.property) {

                    case "name":
                        this.polygonService.saveStationName(+elem.dataset.polygonStationId, elem.value);
                        break;

                    case "hor-angle":
                        this.polygonService.saveHorAngle(+elem.dataset.polygonStationId, elem.value);
                        break;

                    case "hor-distance":
                        this.polygonService.saveHorDistance(+elem.dataset.polygonStationId, elem.value);
                        break;

                    case "elevation":
                        this.polygonService.saveElevation(+elem.dataset.polygonStationId, elem.value);
                        break;

                    case "x":
                        this.polygonService.saveStationX(+elem.dataset.polygonStationId, elem.value);
                        break;

                    case "y":
                        this.polygonService.saveStationY(+elem.dataset.polygonStationId, elem.value);
                        break;

                    case "z":
                        this.polygonService.saveStationZ(+elem.dataset.poliygonStationId, elem.value);
                        break;

                    case "status":
                        this.#currentStation = +elem.dataset.stationId;
                        if (elem.checked) {
                            this.polygonService.saveStationX(+elem.dataset.stationId, "0.000");
                            this.polygonService.saveStationY(+elem.dataset.stationId, "0.000");
                            this.polygonService.saveStationZ(+elem.dataset.stationId, "0.000");

                        } else {
                            this.polygonService.saveStationX(+elem.dataset.stationId, "Not");
                            this.polygonService.saveStationY(+elem.dataset.stationId, "Not");
                            this.polygonService.saveStationZ(+elem.dataset.stationId, "Not");

                        }
                        this.#setListPolygonStations();
                        break;
                }
            }
        }); 

    }
    


}
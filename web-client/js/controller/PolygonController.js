import { PolygonService } from "../service/PolygonService.js";

/**
 * Encapsulates the components of the "Polygon" screen 
 * and the methods for their interaction with 
 * the user and the model.
 */
export class PolygonContoller {
    constructor() {
        this.polygonService = new PolygonService();

        this.#fillDemo();

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


    loadPagePolygon() {
        const content = document.getElementById("content");

        content.innerHTML = `
        <div class="survey-toolbar" id="toolbar-polygon">
            <div class="survey-button delete" id="delete-station" title="Удалить станцию"></div>
            <div class="survey-button insert-before" id="before-station" title="Вставить перед выбранной"></div>
            <div class="survey-button insert-after" id="after-station" title="Вставить после выбранной"></div>
            <div class="survey-button catalog" id="catalog" title="Вставить из каталога"></div>
            <div class="survey-toolbar-separator"></div>

            <div class="survey-button new" id="polygon-new" title="Новый полигон"></div>
            <div class="survey-button open" id="polygon-open" title="Открыть"></div>
            <div class="survey-toolbar-separator"></div>
            <div class="survey-button run" id="polygon-run" title="Уравнять полигон"></div>
            <div class="survey-button view" id="polygon-view" title="Просмотр результатов"></div>
            <input type="file" id="polygon-open-input" accept=".pol">
        </div>

        <div class="panel-polygon">

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

    }

    /**
     * Gets list of table row for table-polygon
     */
    #setListPolygonStations() {
        const listPoligonStations = document.getElementById("list-poligon-stations");
        listPoligonStations.innerHTML = '';

        for (let i = 0; i < this.polygonService.size(); i++) {
            const row = document.createElement('tr');

            let status = false;
            if (this.polygonService.getStationX(i) != "Not") status = true;
            
            let cell = document.createElement('td');
            let item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "name");
            item.size = "10";
            item.value = this.polygonService.getStationName(i);
            item.addEventListener("blur", (event) => {
                let elem = event.target;
                this.polygonService.saveStationName(+elem.dataset.poligonStationId, elem.value);
            });
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "hor-angle");
            item.size = "8";
            item.value = this.polygonService.getHorAngle(i);
            item.addEventListener("blur", (event) => {
                let elem = event.target;
                this.polygonService.saveHorAngle(+elem.dataset.poligonStationId, elem.value);
            });
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "hor-distance");
            item.size = "8";
            item.value = this.polygonService.getHorDistance(i);
            item.addEventListener("blur", (event) => {
                let elem = event.target;
                this.polygonService.saveHorDistance(+elem.dataset.poligonStationId, elem.value);
            });
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "elevation");
            item.size = "7";
            item.value = this.polygonService.getElevation(i);
            item.addEventListener("blur", (event) => {
                let elem = event.target;
                this.polygonService.saveElevation(+elem.dataset.poligonStationId, elem.value);
            });
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "x");
            item.size = "10";
            item.disabled = true;
            if (status) {
                item.value = this.polygonService.getStationX(i);
                item.disabled = false;
                item.addEventListener("blur", (event) => {
                    let elem = event.target;
                    this.polygonService.saveStationX(+elem.dataset.poligonStationId, elem.value);
                });
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "y");
            item.size = "10";
            item.disabled = true;
            if (status) {
                item.value = this.polygonService.getStationY(i);
                item.disabled = false;
                item.addEventListener("blur", (event) => {
                    let elem = event.target;
                    this.polygonService.saveStationY(+elem.dataset.poligonStationId, elem.value);
                });
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            item = document.createElement('input')
            item.type = "text";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "z");
            item.size = "10";
            item.disabled = true;
            if (status) {
                item.value = this.polygonService.getStationZ(i);
                item.disabled = false;
                item.addEventListener("blur", (event) => {
                    let elem = event.target;
                    this.polygonService.saveStationZ(+elem.dataset.poligonStationId, elem.value);
                });
            }
            cell.append(item);
            row.append(cell);

            cell = document.createElement('td');
            let cellContainer = document.createElement('div');
            cellContainer.className = "table-cell";
            item = document.createElement('input')
            item.type = "checkbox";
            item.setAttribute("data-poligon-station-id", i);
            item.setAttribute("data-station-property", "status");
            // item.size = "10";
            if (status) item.setAttribute("checked", true);
            cellContainer.append(item);
            cell.append(cellContainer);
            row.append(cell);

            listPoligonStations.append(row);

        }

    }

    


}
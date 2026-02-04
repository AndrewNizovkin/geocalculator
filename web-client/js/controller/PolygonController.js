/**
 * Encapsulates the components of the "Polygon" screen 
 * and the methods for their interaction with 
 * the user and the model.
 */
export class PolygonContoller {

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
                        <!-- Демо данные -->
                        <tr>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="name" size="10" value="1302"></td>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="hor-angle" size="10" value="359.5959"></td>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="hor-distanse" size="10" value="150.999"/></td>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="elevation" size="10" value="-0.999"></td>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="x" size="10" value="2222222.222"></td>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="y" size="10" value="444444.444"></td>
                            <td><input type="text" data-poligon-station-id="0" data-poligon-station="y" size="10" value="297.356"></td>
                            <td>
                                <div class="table-cell">
                                    <input type="checkbox" data-poligon-station-id="0" data-poligon-station="status" checked>
                                </div>
                                
                            </td>
                        </tr>

                        <!-- Демо данные -->
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
    }

}
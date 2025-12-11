/**
 * 
 */
export class SurveyController {

    /**
     * Loaded survey page and adds listeners to components
     */
    loadPageSurvey() {
        let content = document.getElementById("content");

        content.innerHTML = `
      <div class="toolbar">
        <div class="button new" title="Создать"></div>
        <div class="button open" title="Открыть"></div>
        <div class="button import" title="Импорт из файла"></div>
        <div class="button save" title="Сохранить"></div>
        <div class="toolbar-separator"></div>
        <div class="button run" title="Обработать"></div>
        <div class="button catalog" title="Установить каталог"></div>
      </div>

      <div class="panel-survey">
        
        <div class="panel-basis">
          <div class="panel-stations">
            <div class="toolbar">
              <div class="button delete"></div>
              <div class="button insert-before"></div>
              <div class="button insert-after"></div>
            </div>
            <div class="scrollpanel-stations">
              <ul class="list-stations">
                <li><a class="menu-item" href="#">1301</a></li>
                <li><a class="menu-item" href="#">100</a></li>
                <li><a class="menu-item" href="#">101</a></li>
                <li><a class="menu-item" href="#">102</a></li>
                <li><a class="menu-item" href="#">103</a></li>
                <li><a class="menu-item" href="#">104</a></li>
                <li><a class="menu-item" href="#">105</a></li>
                <li><a class="menu-item" href="#">106</a></li>
                <li><a class="menu-item" href="#">107</a></li>
                <li><a class="menu-item" href="#">108</a></li>
                <li><a class="menu-item" href="#">109</a></li>
                <li><a class="menu-item" href="#">1303</a></li>
                <li><a class="menu-item" href="#">1304</a></li>
              </ul>
            </div>
          </div>

          <div class="panel-station">
            <table class="table-station">
              <caption>Параметры станции</caption>
              <tbody>
                <tr>
                  <td class="menu-item" title="Вставить из каталога">Станция</td>
                  <td><input type="text" name="stationName" placeholder="noname"/></td>
                </tr>
                <tr>
                  <td>X:</td>
                  <td><input type="text" name="stationX" placeholder="0.000"/></td>
                </tr>
                <tr>
                  <td>Y:</td>
                  <td><input type="text" name="stationY" placeholder="0.000"/></td>
                </tr>
                <tr>
                  <td>Z:</td>
                  <td><input type="text" name="stationZ" placeholder="0.000"/></td>
                </tr>
                <tr>
                  <td>i:</td>
                  <td><input type="text" name="stationI" placeholder="0.000"/></td>
                </tr>
                <tr>
                  <td class="menu-item" title="Вставить из каталога">Ориентир</td>
                  <td><input type="text" name="orName" placeholder="noname"/></td>
                </tr>
                <tr>
                  <td>X:</td>
                  <td><input type="text" name="orX" placeholder="0.000"/></td>
                </tr>
                <tr>
                  <td>Y:</td>
                  <td><input type="text" name="orY" placeholder="0.000"/></td>
                </tr>

              </tbody>
            </table>
          </div>

        </div>

        <div class="panel-measurements">
          <div class="toolbar">
            <div class="button delete"></div>
            <div class="button insert-before"></div>
            <div class="button insert-after"></div>
            <div class="toolbar-separator"></div>
            <div class="button change-distance"></div>
            <div class="button change-direction"></div>
            <div class="button change-tilt"></div>
          </div>
          <div class="scrollpanel-measurements">
            <table class="table-measurements">
              <thead>
                <th>Название</th>
                <th>Расстояние</th>
                <th>Направление</th>
                <th>Наклон</th>
                <th>Выс.Цели</th>
              </thead>
              <tbody>
                <!-- Демо данные -->
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <tr>
                  <td><input type="text" class="menu-item picket name" name="picketName-0" value="1302"/></td>
                  <td><input type="text" class="menu-item picket distance" name="picketDistance-0" value="30.526"/></td>
                  <td><input type="text" class="menu-item picket direction" name="picketDirection-0" value="359.5953"/></td>
                  <td><input type="text" class="menu-item picket tilt" name="picketTilt-0" value="-0.5959"/></td>
                  <td><input type="text" class="menu-item picket heigth" name="picketHeigth-0" value="1302"/></td>
                </tr>
                <!-- Демо данные -->
              </tbody>

            </table>
          </div>
        </div>

      </div>
        
        `;

    }
}
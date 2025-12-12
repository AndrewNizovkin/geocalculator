import {DirectController} from './DIrectController.js';
import {InverseController} from './InverseController.js';
import {PotenotController} from './PotenotController.js';
import { SurveyController } from './SurveyController.js';

/**
 * 
 */
export class MainController {
    constructor() {
        this.directController = new DirectController();
        this.inverseController = new InverseController();
        this.potenotController = new PotenotController();
        this.surveyController = new SurveyController();
    }

    /**
     * Load user interface and adds listeners to components
     */
    start() {

        document.body.innerHTML = `
  <div class="header">
    <ul class="menu">
      <li><a href="#">Файл</a></li>
      <li><a href="#">Инструменты</a></li>
      <li><a href="#">Поддержка</a></li>
      <li><a href="#">Контакты</a></li>
      <li><a href="#" id="reg">Вход/Регистрация</a></li>
    </ul>
  </div>
  <div class="main">
    <div class="nav">
      <div class="menu-item" id="main-page">Главная</div>
      <div class="menu-item" id="direct-page">Прямая задача</div>
      <div class="menu-item" id="inverse-page">Обратная задача</div>
      <div class="menu-item" id="potenot-page">Задача Потенота</div>
      <div class="menu-item" id="survey-page">Съёмка</div>
    </div>
    <div class="content" id="content"></div>

    <div class="sidebar">Sidebar</div>
  </div>
  <div class="footer">Footer</div>

        `;

        let menuItemMainPage = document.getElementById("main-page");
        menuItemMainPage.addEventListener('click', this.loadMainPage);

        let menuItemDirectPage = document.getElementById("direct-page");
        menuItemDirectPage.addEventListener('click', this.directController.loadPageDirect.bind(this.directController));

        let menuItemInversePage = document.getElementById("inverse-page");
        menuItemInversePage.addEventListener('click', this.inverseController.loadPageInverse.bind(this.inverseController));

        let menuItemPotenotPage = document.getElementById("potenot-page");
        menuItemPotenotPage.addEventListener('click', this.potenotController.loadPagePotenot.bind(this.potenotController));

        let menuItemSurveyPage = document.getElementById("survey-page");
        menuItemSurveyPage.addEventListener('click', this.surveyController.loadPageSurvey);

        this.loadMainPage();

    }

    /**
     * Loads main page
     */
    loadMainPage() {
        let content = document.getElementById("content");

        content.innerHTML = `
        <h3>Hello! I am main page</h3>
        `;

    }

}
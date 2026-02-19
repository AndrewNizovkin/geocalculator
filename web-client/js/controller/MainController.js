import {DirectController} from './DirectController.js';
import {InverseController} from './InverseController.js';
import {PotenotController} from './PotenotController.js';
import { SurveyController } from './SurveyController.js';
import { BasePointController } from './BasePointController.js';
import { PolygonContoller } from './PolygonController.js';

/**
 * Displays the main screen of the application and 
 * adds event handlers for its components
 * @author Nizovkin A.V.
 * @copyright 2025 Nizovkin A.V.
 */
export class MainController {
  #basePointController;
  #directController;
  #inverseController;
  #potenotController;
  #surveyController;
  #polygonController;

    constructor() {
      this.#basePointController = new BasePointController();
      this.#directController = new DirectController(this.#basePointController.getBasePointService());
      this.#inverseController = new InverseController(this.#basePointController.getBasePointService());
      this.#potenotController = new PotenotController(this.#basePointController.getBasePointService());
      this.#polygonController = new PolygonContoller(this.#basePointController.getBasePointService());
      this.#surveyController = new SurveyController(this.#basePointController.getBasePointService(), this.#polygonController.getPolygonService());
      
    }

    /**
     * Load user interface and adds listeners to components
     */
    start() {

        document.body.innerHTML = `
  <div class="overlay" id="overlay"></div>        

  <div class="header" id="header">

    <div class="title" id="title">
      <div class="logo menu-item" id="logo">Тахеопорт</div>  
      <div class="separator" id="title-separator">Геодезический калькулятор</div>
      <div class="menu-item" id="icon-menu">☰</div>
    </div>

    

    <div class="main-menu" id="main-menu">
      <div class="menu-item" id="main-tools">Обратная связь</div>
      <div class="menu-item" id="main-support">Поддержать проект</div>
      <div class="menu-item" id="main-support">Руководство пользователя</div>
      <div class="menu-item" id="main-login">Вход/Регистрация</div>
    </div>

  </div>
  <div class="main">
    <div class="nav"  id="nav-menu">
      <div class="menu-item" id="main-page">Главная</div>
      <div class="menu-item" id="points-page">Каталог</div>
      <div class="menu-item" id="survey-page">Съёмка</div>
      <div class="menu-item" id="poligon-page">Полигон</div>      
      <div class="menu-item" id="direct-page">Прямая</div>
      <div class="menu-item" id="inverse-page">Обратная</div>
      <div class="menu-item" id="potenot-page">Потенота</div>

    </div>
    <div class="content" id="content">
      
      <div class="main-title">
        <h3>Задачи</h3><br>
        Геодезический калькулятор Тахеопорт предназначен для решения прикладных 
        задач, встречающихся в практике топографов, геодезистов и смежных с ними профессий.
      </div>

      <div class="panel-main">

        <div class="main-banner"></div>
        <div class="main-banner"></div>
        <div class="main-banner"></div>
        <div class="main-banner"></div>

      </div>


    </div>

    <div class="sidebar">Sidebar</div>
  </div>
  <div class="footer">Footer</div>



        `;
        this.loadMainPage();
        this.#addListenersMainMenu();
        this.#addListenersNavMenu();
    }

    /**
     * Loads main page
     */
    loadMainPage() {
        let content = document.getElementById("content");

        content.innerHTML = `
      <div class="main-title">
        <h3>Задачи</h3>
        Геодезический калькулятор Тахеопорт предназначен для решения прикладных 
        задач, встречающихся в практике топографов, геодезистов и смежных с ними профессий.
      </div>

      <div class="panel-main">

        <div class="main-banner"></div>
        <div class="main-banner"></div>
        <div class="main-banner"></div>
        <div class="main-banner"></div>

      </div>

        `;

    }

    /**
     * Adds listeners to main menu
     */
    #addListenersMainMenu() {
      document.getElementById('header').addEventListener('click', (event) => {

        switch(event.target.id) {

          case "icon-menu":
            document.getElementById("main-menu").classList.toggle("open");
            break;

          case "logo":
            this.loadMainPage();
            break;

          case "main-direct":
            this.#directController.loadPageDirect();
            subMenuTools.classList.toggle("open");
            break;

          case "main-inverse":
            this.#inverseController.loadPageInverse();
            subMenuTools.classList.toggle("open");
            break;

          case "main-potenot":
            this.#potenotController.loadPagePotenot();
            subMenuTools.classList.toggle("open");
            break;

          case "main-survey":
            this.#surveyController.loadPageSurvey();
            subMenuTools.classList.toggle("open");
            break;              
        }

      });

    }

    /**
     * Adds listeners to nav menu
     */
    #addListenersNavMenu() {
      document.getElementById("nav-menu").addEventListener('click', (event) => {
        let titleSeparator = document.getElementById("title-separator");

        switch(event.target.id) {

          case "main-page":
            this.loadMainPage();
            titleSeparator.innerHTML = "Геодезический калькулятор";
            break;
          
          case "direct-page":
            this.#directController.loadPageDirect();
            titleSeparator.innerHTML = "Прямая геодезическая задача";
            break;

          case "inverse-page":
            this.#inverseController.loadPageInverse();
            titleSeparator.innerHTML = "Обратная геодезическая задача";
            break;

          case "potenot-page":
            this.#potenotController.loadPagePotenot();
            titleSeparator.innerHTML = "Задача Потенота";
            break;

          case "survey-page":
            this.#surveyController.loadPageSurvey();
            titleSeparator.innerHTML = "Тахеометрическая съёмка";
            break;

          case "points-page":
            this.#basePointController.loadPageBasePoints();
            titleSeparator.innerHTML = "Каталог координат";
            break;

          case "poligon-page":
            this.#polygonController.loadPagePolygon();
            titleSeparator.innerHTML = "Уравнивание тахеометрического хода";
            break;
        }

      });

    }

}
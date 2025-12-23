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
  <div class="header" id="header">

    <div class="logo menu-item" id="logo">Тахеопорт</div>

    <div class="main-menu">
      <div class="menu-item" id="main-tools">Инструменты</div>

      <div class="menu-item" id="main-support">Поддержка</div>
      <div class="menu-item" id="main-login">Вход/Регистрация</div>
    </div>

    <div class="mobile-menu">
      <div class="menu-item" id="icon-menu">☰</div>
    </div>


  </div>
  <div class="main">
    <div class="nav"  id="nav-menu">
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

        let subMenuTools = document.createElement('div');
        subMenuTools.className = "sub-menu-tools";
        subMenuTools.innerHTML = `

        <div class="menu-item" id="main-direct">Прямая задача</div>
          <div class="menu-item" id="main-inverse">Обратная задача</div>
          <div class="menu-item" id="main-potenot">Задача Потенота</div>
          <div class="menu-item" id="main-survey">Съёмка</div>
        
        `;

      //   let subMenuIcon = document.createElement('div');
      //   subMenuIcon.className = "sub-menu-icon";
      //   subMenuIcon.innerHTML = `
        
      // <div class="menu-item" id="main-tools">Инструменты</div>

      // <div class="menu-item" id="main-support">Поддержка</div>
      // <div class="menu-item" id="main-login">Вход/Регистрация</div>        
      //   `;






        document.getElementById("nav-menu").addEventListener('click', (event) => {

          switch(event.target.id) {

            case "main-page":
              this.loadMainPage();
              break;
            
            case "direct-page":
              this.directController.loadPageDirect();
              break;

            case "inverse-page":
              this.inverseController.loadPageInverse();
              break;

            case "potenot-page":
              this.potenotController.loadPagePotenot();
              break;

            case "survey-page":
              this.surveyController.loadPageSurvey();
              break;
          }

        });

        document.getElementById('header').addEventListener('click', (event) => {
          let menuTools = document.getElementById("main-tools");
          let menuIcon = document.getElementById("icon-menu");
          let coords;


          switch(event.target.id) {

            // case "icon-menu":
            //   menuIcon.after(subMenuIcon);
            //   menuTools = document.getElementById("main-tools");
            //   coords = event.target.getBoundingClientRect();
            //   subMenuIcon.style.left = (coords.left - subMenuIcon.offsetWidth) + "px";
            //   subMenuIcon.style.top = coords.bottom + "px";
            //   subMenuIcon.classList.toggle("open");
            //   break;

            case "logo":
              this.loadMainPage();
              break;

            case "main-tools":
              menuTools.after(subMenuTools);
              coords = menuTools.getBoundingClientRect();
              subMenuTools.style.left = coords.left + "px";
              subMenuTools.style.top = coords.bottom + "px"

              subMenuTools.classList.toggle("open");
              break;

            case "main-direct":
              this.directController.loadPageDirect();
              subMenuTools.classList.toggle("open");
              break;

            case "main-inverse":
              this.inverseController.loadPageInverse();
              subMenuTools.classList.toggle("open");
              break;

            case "main-potenot":
              this.potenotController.loadPagePotenot();
              subMenuTools.classList.toggle("open");
              break;

            case "main-survey":
              this.surveyController.loadPageSurvey();
              subMenuTools.classList.toggle("open");
              break;              
          }

        });

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
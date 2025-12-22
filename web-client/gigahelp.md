Создание меню 

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application Header</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Flex header -->
    <header class="header">
        <div class="logo">application</div>
        <nav class="menu">
            <ul class="menu-list">
                <li><a href="#">Tools ⬇️</a>
                    <ul class="sub-menu">
                        <li><a href="#">First Task</a></li>
                        <li><a href="#">Second Task</a></li>
                        <li><a href="#">Third Task</a></li>
                    </ul>
                </li>
                <li><a href="#">Support</a></li>
                <li><a href="#">Login</a></li>
            </ul>
        </nav>
        <button id="toggleMenuBtn" aria-label="Toggle Menu"><i class="fas fa-bars"></i></button>
    </header>

    <script src="scripts.js"></script>
</body>
</html>
```

### CSS

```css
/* Общие стили */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #f8f9fa;
    position: relative;
    z-index: 1000;
    height: 60px;
    transition: all .3s ease-in-out;
}

.logo {
    color: #0d6efd;
    font-size: 1.5em;
    text-decoration: none;
    cursor: pointer;
    white-space: nowrap;
}

.menu {
    list-style-type: none;
    display: flex;
    gap: 1rem;
    line-height: 60px;
}

.menu a {
    text-decoration: none;
    color: black;
    font-weight: bold;
}

.sub-menu {
    display: none;
    position: absolute;
    top: 60px;
    left: 0;
    width: max-content;
    min-width: 150px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    padding: 10px 0;
    z-index: 1001;
}

.sub-menu li {
    padding: 5px 15px;
}

.sub-menu li a {
    display: block;
    opacity: 0.8;
    transition: opacity 0.3s linear;
}

.sub-menu li a:hover {
    opacity: 1;
}

.menu > ul > li:hover .sub-menu {
    display: block;
}

#toggleMenuBtn {
    display: none;
    outline: none;
    border: none;
    background-color: transparent;
    color: #0d6efd;
    font-size: 1.5em;
    cursor: pointer;
}

@media screen and (max-width: 768px) {
    /* Адаптация дизайна для маленьких экранов */
    
    .header {
        position: fixed;
        top: 0;
        right: 0;
        left: 0;
        background-color: #ffffff;
        padding-left: 10px;
        padding-right: 10px;
        z-index: 1000;
    }

    .menu {
        display: none;
        flex-direction: column;
        position: fixed;
        top: 60px;
        bottom: 0;
        left: 0;
        width: 100%;
        background-color: #f8f9fa;
        transform: translateX(-100%);
        transition: transform 0.3s cubic-bezier(.4,.01,.165,1);
    }

    .menu.active {
        transform: translateX(0);
    }

    #toggleMenuBtn {
        display: inline-block;
    }

    .sub-menu {
        display: none !important;
    }

    .menu-list > li > a {
        display: block;
        padding: 15px 20px;
        font-size: 1.2em;
        text-align: center;
    }

    .menu-list > li > a:hover,
    .menu-list > li > a:focus {
        background-color: #dee2e6;
    }

    .menu-list > li::after {
        content: '';
        clear: both;
        display: table;
    }
}
```

### JS

```js
// Скрипт для отображения мобильного меню
document.addEventListener('DOMContentLoaded', function() {
    const toggleButton = document.getElementById('toggleMenuBtn');
    const menuList = document.querySelector('.menu');

    // Показываем меню при нажатии кнопки гамбургера
    toggleButton.addEventListener('click', () => {
        menuList.classList.toggle('active');
    });
});
```
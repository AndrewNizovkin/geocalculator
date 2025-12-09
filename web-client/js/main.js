let page = "Hello! I am main page!";

addToContent(page);

function addToContent(page) {
    let content = document.getElementsByClassName('content');
    content[0].innerHTML = content[0].innerHTML + page;    
}

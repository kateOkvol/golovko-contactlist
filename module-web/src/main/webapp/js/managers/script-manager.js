function manageScripts(element, pageId) {
//pageId - id of page to go
    switch (element) {
        case("phones-window"):
            document.getElementById(element).style.display = 'none';
            break;
        case("attach-window"):
            document.getElementById(element).style.display = 'none';
            break;
        case("search-page"):
        case("contact-editor"):
        case("email-page"):
            document.getElementById(element).style.display = 'none';
            document.getElementById("main-contact").style.display = 'block';
            document.getElementById('h1').innerHTML = 'Contacts List';
            loadMain();
            break;
        case("main-contact"):
            document.getElementById(element).style.display = 'none';
            document.getElementById(pageId).style.display = 'block';
            switch (pageId) {
                case 'contact-editor':
                    loadEditor(event, 0);
                    break;
                case'email-page':
                    loadEmail(event);
                    break;
                case'search-page':
                    loadSearch(event);
                    break;
            }
    }
}
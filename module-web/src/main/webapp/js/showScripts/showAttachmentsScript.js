let ava;

function showAttaches(promise) {
    checkPromise(promise);
}

function checkPromise(promise) {
    if (promise === null) {
        addAvatarComponents();
        showAttachTable(0);
    } else {
        showAttachTable(promise.id);
        addAvatarComponents();
        fillAvatarComponent(promise);
    }
}


function addAvatarComponents() {
    let textHTML = "<form><label for='avatarId'>" +
        // "<img src='http://localhost:8080/application?getAvatar' height='170px' alt='No photo' id='avaImg' style='cursor:pointer'><br>" +
        "<img src='' height='170px' alt='No photo' id='avaImg' style='cursor:pointer'><br>" +
        "<input type='file' onchange='setAvatar()' " +
        "name='avatarButton' id='avatarId' style='display:none'></label></form>";
    document.getElementById('avatar').innerHTML = textHTML;
}

function fillAvatarComponent(promise) {
    let id = {};
    id['id'] = promise.id;
    fetch('application?getAvatar', {
        method: 'POST',
        dataType : 'binary',
        body: JSON.stringify(id)
    })
        .then(response => {
            response.json().then(function (data) {
                let path = 'data:image/jpeg;base64,' + data;
                let img =  document.getElementById('avaImg');
                img.src = path;
                console.log('ok');
            })
                .catch(function (error) {
                    console.log(new Error(error.message));
                });
        });
}

function setAvatar() {
    ava = document.getElementById('avatarId').files[0];
    const preview = document.getElementById('avaImg');
    const reader = new FileReader();

    console.log('reader is created');

    reader.addEventListener("load", function () {
        console.log('reader is onload');
        preview.src = reader.result;
        console.log(preview.src);
    }, false);

    if (ava) {
        reader.readAsDataURL(ava);
        console.log(ava);
    } else {
        preview.src = '';
        console.log('else :(');
    }
}

function uploadAvaFetch(file) {
    let form = new FormData();
    form.append('file', file);
    let promise = new Promise((resolve, reject) => {
        return fetch('application?uploadAva', {
            method: 'POST',
            body: form
        })
            .then(() => {
                ava = null;
            })
            .catch(function (error) {
                reject(new Error(error.message));
            });
    });
    console.log(promise);
}
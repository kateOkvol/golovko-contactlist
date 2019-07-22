let ava;

function showAttaches(promise) {
    checkPromise(promise);
}

function checkPromise(promise) {
    if (promise === null) {
        addAvatarComponents('images/noAva.jpg');
        showAttachTable(0);
    } else {
        showAttachTable(promise.id);
        addAvatarComponents(promise.avatar);
    }
}

function addAvatarComponents(path) {
    let textHTML = "<label for='avatarId'>" +
        "<img src='" + path + "' height='170px' alt='No photo' id='avaImg' style='cursor:pointer'><br>" +
        "<input type='file' onchange='setAvatar()'" +
        "name='avatarButton' id='avatarId' style='display:none'></label>";
    document.getElementById('avatar').innerHTML = textHTML;
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
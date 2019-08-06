let ava;
let file;

function showAttaches(promise) {
    checkPromise(promise);
}

function checkPromise(promise) {
    if (promise === null) {
        addAvatarComponents();
        ava = '/resource/noAva.jpg';
        document.getElementById('avaImg').src = ava;
        showAttachTable(0);
    } else {
        showAttachTable(promise.id);
        addAvatarComponents();
        fillAvatarComponent(promise);
    }
}

function addAvatarComponents() {
    let textHTML = "<label for='avatarId'>" +
        "<img src='' height='170px' alt='No photo' id='avaImg' style='cursor:pointer'><br>" +
        "<input type='file' onchange='setAvatar()' " +
        "name='avatarButton' id='avatarId' style='display:none'></label>";
    document.getElementById('avatar').innerHTML = textHTML;
}

function fillAvatarComponent(promise) {
    let id = {};
    id['id'] = promise.id;
    ava = promise['avatar'];
    fetch('application?getAvatar', {
        method: 'POST',
        dataType: 'binary',
        body: JSON.stringify(id)
    })
        .then(response => {
            response.json().then(function (data) {
                let path = 'data:image/jpeg;base64,' + data;
                let img = document.getElementById('avaImg');
                img.src = path;
                console.log('ok');
            })
                .catch(function (error) {
                    console.log(new Error(error.message));
                });
        });
}

function setAvatar() {
    let avaElement = document.getElementById('avatarId').files[0];
    const preview = document.getElementById('avaImg');
    const reader = new FileReader();

    console.log('reader is created');

    reader.addEventListener("load", function () {
        console.log('reader is onload');
        preview.src = reader.result;
        console.log(preview.src);
    }, false);

    if (avaElement) {
        reader.readAsDataURL(avaElement);
        console.log(avaElement);
    } else {
        preview.src = '';
        console.log('else :(');
    }
    file = avaElement;
    ava = avaElement.name;
}
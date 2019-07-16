function showAttach(contactId) {
    addAvatarComponents();
    showAttachTable(contactId);
}

function addAvatarComponents() {
    let textHTML = "<img src='' height='150px' alt='No photo' id='avaImg'><br>" +
        "<input type='file' onchange='showPhoto()' " +
        "name='avatarButton' id='avatarId'>";
    document.getElementById('avatar').innerHTML = textHTML;
}

function showPhoto() {
    console.log('method is run');
    const preview = document.getElementById('avaImg');
    const file = document.getElementById('avatarId').files[0];
    const reader = new FileReader();

    console.log('reader is created');

    reader.addEventListener("load", function () {
            console.log('reader is onload');
            preview.src = reader.result;
            console.log(preview.src);
    } ,false);

    if (file) {
        reader.readAsDataURL(file);
        console.log(file);
    } else {
        preview.src = '';
        console.log('else :(');
    }
}

// function showNoPhoto() {
//     let textHTML = "<img src='' alt='No photo'>";
//     document.getElementById('avatar').innerHTML = textHTML;
// }
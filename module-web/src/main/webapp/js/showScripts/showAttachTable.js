function showAttachTable(contactId) {
    addAttachButtons();
    if (contactId === 0) {
        document.getElementById("attach-table").innerHTML = "<tr><th>&#10004</th><th>File name</th><th>Date of download</th><th>Note</th></tr>";
    } else {
        createAttachTable(contactId);
    }
}

function AttachTableHTML() {
    let tableHTML = "<script type='text/html-template' id='template-attach-table'>";
    tableHTML += "<tr>";
    tableHTML += "<th>&#10004</th><th>File name</th><th>Date of download</th><th>Note</th>";
    tableHTML += "<tbody id='attach-table-body'>";
    tableHTML += "{{#.}}";
    tableHTML += "<tr>";
    tableHTML += "<td><input class='messageCheckbox' type='checkbox' name='delete' id='attach{{id}}'></td>";
    tableHTML += "<td id='attachName{{id}}' style='cursor: pointer' onclick='downloadAttachFetch(id)'>{{attachName}}</td> " +
        "<a id='a{{id}}' style='display: none' href=''>href</a>" +
        "<td id='date{{id}}'> {{date}}</td>" +
        "<td id='attachNote{{id}}'> {{note}}</td>";
    tableHTML += "</tr>";
    tableHTML += "{{/.}}";
    tableHTML += "</tbody>";
    tableHTML += "</tr>";
    tableHTML += "</script>";

    document.getElementById("attach-table").innerHTML = tableHTML;
}

function createAttachTable(contactId) {
    return fetch("application?mainAttaches", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({"contactId": contactId})
        }
    )
        .then(response => {
            return response.json().catch(error => {
                return Promise.reject(new Error('Invalid JSON: ' + error.message));
            });
        })
        .then(response => {
            const attachesList = response;
            console.log(attachesList);
            AttachTableHTML();
            const template = document.getElementById("template-attach-table").innerHTML;
            console.log(template);
            document.getElementById("attach-table").innerHTML = Mustache.to_html(template, attachesList);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addAttachButtons() {
    const url = "application?deleteAttach";
    const tableBodyId = "attach-table-body";

    let buttonHTML = "<label for='createAttach' class='input-label'>Create</label>" +
        "<input type='file' id='createAttach' onchange='createAttach()' style='display: none'>";
    buttonHTML += "<button type='button' onclick='loadAttachPopup(event)' id='edit' title='Отметьте галочкой контакт для редактирования'>Edit</button>";
    buttonHTML += "<button type='submit' id='delete' " +
        "onclick='deleteManager(\"" + tableBodyId + "\",\"" + url + "\")'>" +
        "Delete" +
        "</button>";
    document.getElementById("attach-buttons").innerHTML = buttonHTML;
}

function downloadAttachFetch(attachmentId) {
    let number = attachmentId.replace(/\D+/g, "");
    let id = {};
    id['id'] = number;
    console.log(JSON.stringify(id));
    if (id !== 0) {
        let header;
        return fetch('application?downloadAttach', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
        }).then(response => {
            header = response.headers.get("Content-Disposition");
            return response.text();
        }).then(text=>{
            return atob(text);
        }).then(bytes => {
            let byteArrays = [];
            const sliceSize = 512;
            for (let offset = 0; offset < bytes.length; offset += sliceSize) {
                const slice = bytes.slice(offset, offset + sliceSize);

                let byteNumbers = new Array(slice.length);
                for (let i = 0; i < slice.length; i++) {
                    byteNumbers[i] = slice.charCodeAt(i);
                }

                let byteArray = new Uint8Array(byteNumbers);

                byteArrays.push(byteArray);
            }

            let name = header.split('=', 2)[1];
            let file = new Blob(byteArrays, {type: 'application/octet-stream'});
            let aTeg = document.getElementById('a' + number);
            aTeg.href = URL.createObjectURL(file);
            aTeg.download = name;
            aTeg.click();
            URL.revokeObjectURL(aTeg.href);
        })
            .catch(function (error) {
                console.log(new Error(error.message));
            });

    } else {
        alert("Save changes to download file");
    }
}
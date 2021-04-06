$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    $('#user-posting').empty();
    $('#admin-posting').empty();

    var isAdmin = false;
    if ($('#admin').length === 1) {
        isAdmin = true
    }

    if(!isAdmin){
        let id = $('#dataId').val();
        console.log(id);
        $.ajax({
            type: 'POST',
            url: '/api/user',
            contentType: "application/json",
            data: JSON.stringify(id),
            async: false,
            success: function (response) {
                let user = response;
                let id = user.id;
                let username = user.username;
                let password = user.password;
                let name = user.name;
                let email = user.email;
                let phone = user.phone;
                let role = user.role;
                let html = `<p>${id} ${username} ${name} ${password} ${role} ${email} ${phone}<button type="button" onclick="editOne('${id}')">수정</button></p> `
                $('#user-posting').append(html);
            }
        })
    }

    else if(isAdmin){
        $.ajax({
            type: 'GET',
            url: '/admin/users',
            async: false,
            success: function (response) {
                for(let i = 0; i < response.length; i++){
                    let user = response[i];
                    let id = user.id;
                    let username = user.username;
                    let password = user.password;
                    let name = user.name;
                    let email = user.email;
                    let phone = user.phone;
                    let role = user.role;
                    let html = `<p>${id} ${username} ${name} ${password} ${role} ${email} ${phone}<button type="button" onclick="deleteOne('${id}')">삭제</button></p> `
                    $('#admin-posting').append(html);
                }
            }
        })
    }
})

function deleteOne(id){
    $.ajax({
        type: "DELETE",
        url: `/admin/users/${id}`,
        success: function (response) {
            alert('계정 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}

function editOne(id) {
    console.log("entered editOne")
    $('#user-posting').empty();
    $.ajax({
        type: 'POST',
        url: '/api/edit_user',
        contentType: "application/json",
        data: JSON.stringify(id),
        async: false,
        success: function (response) {
            let user = response;
            let username = user.username;
            let password = user.password;
            let name = user.name;
            let email = user.email;
            let phone = user.phone;
            let html = `<div id="${id}-id">${id}</div>
                        <div id="${id}-name">${name}</div>
                        <div id="${id}-username">${username}</div>
                        <input id="${id}-password" type="text" placeholder="${password}">
                        <input id="${id}-email" type="text" placeholder="${email}">
                        <input id="${id}-phone" type="text" placeholder="${phone}">
                        <button type="button" onclick="submitEdit('${id}')">입력</button>
                        <button type="button" onclick="window.location.reload()">취소</button>`;
            $('#user-posting').append(html);
        }
    })
}

function submitEdit(id){
    let name = $(`#${id}-name`).text();
    let username = $(`#${id}-username`).text();
    let password = $(`#${id}-password`).val();
    let email = $(`#${id}-email`).val();
    let phone = $(`#${id}-phone`).val();
    let data = {'username': username, 'password': password, 'email': email, 'phone': phone};
    $.ajax({
        type: "PUT",
        url: `/api/users/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('회원정보 변경에 성공하였습니다.');
            $('#user-posting').empty();
            window.location.reload();
        }
    });
}

function tokensend(){
    let token = $('#token-box').val();
    console.log(token);
    $.ajax({
        type: 'POST',
        url: '/api/getUser',
        contentType: "application/json",
        data: JSON.stringify(token),
        async: false,
        success: function (response) {
            console.log(response);
        }
    })
}
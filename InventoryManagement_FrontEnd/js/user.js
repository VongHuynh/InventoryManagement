// register
function registerUser() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/registerAccount",
        type: "POST",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        data: JSON.stringify({
            userName: $('#userNameCrt').val(),
            fullName: $('#fullNameCrt').val(),
            email: $('#emailCrt').val(),
            phoneNumber: $('#phoneNumberCrt').val()
        }),
        dataType: 'json',
        success: function (data) {
            alert('Register successfully');
        },
        error: function (jqXHR, exception) {
            alert('error')
        },
    })
}

// load user is active
getUserIsNotDeleted()
function getUserIsNotDeleted() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/isNotDeleted",
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            let users = data.data
            let response = "";
            users.forEach(el => {
                response += `<tr>
                <td>${el.userID}</td>
                <td>${el.userName}</td>
                <td>${el.fullName}</td>
                <td>${el.email}</td>
                <td>${el.phoneNumber}</td>
                <td>
                <button class="btn btn-primary ml-2" onclick="deleteUser(${el.userID})">Delete</button>
             </td>
            </tr>`
            })
            document.getElementById("listUserNotDelete").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}
// load user is Delete
getUserIsDeleted()
function getUserIsDeleted() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/isDeleted",
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            let users = data.data
            let response = "";
            users.forEach(el => {
                response += `<tr>
                <td>${el.userID}</td>
                <td>${el.userName}</td>
                <td>${el.fullName}</td>
                <td>${el.email}</td>
                <td>${el.phoneNumber}</td>
               
            </tr>`
            })
            document.getElementById("listUserDelete").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

//delete user
function deleteUser(id) {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/" + id,
        type: "DELETE",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length-1)
        },
        success: function (data) {
           alert('Delete sucessfully')
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('error')
        },
    })
}




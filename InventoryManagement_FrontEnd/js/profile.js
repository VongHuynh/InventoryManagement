getInfo(localStorage.getItem("userID"));
function getInfo(id) {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/" + id,
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length-1)
        },
        success: function (data) {
            $('#userID').val(data.userID)
            $('#userName').val(data.userName)
            $('#userNameC').val(data.userName)
            $('#fullName').val(data.fullName)
            $('#email').val(data.email)
            $('#phoneNumber').val(data.phoneNumber)
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

// edit user
function updateInfo() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount",
        type: "PUT",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length-1)
        },
        data: JSON.stringify({
            userID:$('#userID').val(),
            userName:$('#userName').val(),
            fullName: $('#fullName').val(),
            email: $('#email').val(),
            phoneNumber: $('#phoneNumber').val()
        }),
        dataType: 'json',
        success: function(data) {
            alert('update successfully');
            getInfo(localStorage.getItem("userID"));
        },
        error: function (jqXHR, exception) {
           alert('error')
        },
    })
}

//change pass
function updatePass() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/changePassword",
        type: "POST",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length-1)
        },
        data: JSON.stringify({
            username: $('#userNameC').val(),
            oldPassword: $('#oldPass').val(),
            newPassword: $('#newPass').val()
        }),
        dataType: 'json',
        success: function(data) {
            alert('change pass successfully');
        },
        error: function (jqXHR, exception) {
           alert('error')
        },
    })
}



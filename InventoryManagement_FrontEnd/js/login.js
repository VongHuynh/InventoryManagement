function login() {
    $.ajax({
        url: "http://localhost:8080/api/v1/UserAccount/login",
        type: "Post",
        contentType: "application/json;",
        data: JSON.stringify({
            userName: $('#username').val(),
            password: $('#password').val()
        }),
        dataType: 'json',
        success: function(data) {
            console.log(data);
            if(data.success === true) {
                localStorage.setItem('token', JSON.stringify(data.token));
                localStorage.setItem('userID', JSON.stringify(data.userId));
                localStorage.setItem('username', JSON.stringify(data.username));
                location.href='profile.html'
        
            }
        },
        error: function (jqXHR, exception) {
            alert('username or password invalid')
          console.log(jqXHR, exception);
            }

    })
}
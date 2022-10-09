// load authority
getAllAuthority()
function getAllAuthority() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/authorities",
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            let response = "";
            data.forEach(el => {
                response += `<tr>
                <td>${el.authorityId}</td>
                <td>${el.roleId}</td>
                <td>${el.userId}</td>
                <td>
                <button class="btn btn-info" onclick="getTrans(${el.productId})">Edit</button>
             </td>
            </tr>`
            })
            document.getElementById("AuthorityTb").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

// inser author
function insertAuthor() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/authorities",
        type: "POST",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        data: JSON.stringify({
            userId: $('#userID').val(),
            roleId: $('#Author').val()
        }),
        dataType: 'json',
        success: function (data) {
            console.log(data)
            alert('Insert successfully');
        },
        error: function (jqXHR, exception) {
            alert('error')
        },
    })
}
// load user is active
getProductNoEnterStockYet()
function getProductNoEnterStockYet() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/productTrans/NoEnterStockYet",
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            let response = "";
            data.forEach(el => {
                response += `<tr>
                <td>${el.productTransID}</td>
                <td>${el.amount}</td>
                <td>${el.statusName}</td>
                <td>${el.productName}</td>
                <td>
                <button class="btn btn-info ml-2" onclick="approveTrans(${el.productTransID})">Approve</button>
             </td>
            </tr>`
            })
            document.getElementById("productTrans").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

// create trans
function enterStock() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/productTrans",
        type: "POST",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        data: JSON.stringify({
            amount: $('#amount').val(),
            productID: $('#productID').val()
        }),
        dataType: 'json',
        success: function (data) {
            alert('Enter successfully, waiting for approve');
            getProductNoEnterStockYet()
        },
        error: function (jqXHR, exception) {
            alert('error')
        },
    })
}

//delete product
function approveTrans(id) {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/productTrans/" + id,
        type: "PUT",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length-1)
        },
        success: function (data) {
           alert('Approve sucessfully')
           getProductNoEnterStockYet();
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('Approved or error')
        },
    })
}
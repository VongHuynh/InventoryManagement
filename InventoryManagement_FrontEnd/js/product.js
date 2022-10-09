// create product
function createProduct() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/product",
        type: "POST",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        data: JSON.stringify({
            title: $('#title').val(),
            description: $('#description').val(),
            price: $('#price').val(),
            amount: $('#amount').val(),
            categoryID: $('#categoryID').val()
        }),
        dataType: 'json',
        success: function (data) {
            alert('Create successfully');
            getProductIsNotDeleted()
            let response = data.data
            let img = []
            img.push(document.getElementById('imgProduct').value)
            if(document.getElementById('imgProduct').value != null){
                updateProductIMG(img, response[0].productId)
            }
        },
        error: function (jqXHR, exception) {
            alert('error')
        },
    })
}

// load user is active
getProductIsNotDeleted()
function getProductIsNotDeleted() {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/product/isNoDeleted",
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
                <td>${el.productId}</td>
                <td>${el.title}</td>
                <td>${el.price}</td>
                <td>${el.amount}</td>
                <td>${new Date(el.importDate).toISOString().slice(0, 10)}</td>
                <td>
                <button class="btn btn-info ml-2" onclick="getTrans(${el.productId})">View Trans</button>
                <button class="btn btn-primary ml-2" onclick="deleteProduct(${el.productId})">Delete</button>
             </td>
            </tr>`
            })
            document.getElementById("producsNotDelete").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}
//delete product
function deleteProduct(id) {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/product/" + id,
        type: "DELETE",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            alert('Delete sucessfully')
            getProductIsNotDeleted()
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('error')
        },
    })
}

//get trans
function getTrans(id) {
    // document.getElementById("productTrans").innerHTML = "";
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/productTrans/" + id,
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            let trans = data.data
            let response = "";
            trans.forEach(el => {
                response += `<tr>
                <td>${el.productName}</td>
                <td>${el.amount}</td>
                <td>${el.statusName}</td>
                <td>${el.productTransID}</td>
                <td>
                <button class="btn btn-info ml-2" onclick="viewLogDetail(${el.productTransID})">View Details</button>
             </td>
            </tr>`
            })
            document.getElementById("productTrans").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('error')
        },
    })
}

// view Log detal
function viewLogDetail(id) {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/productTrans/log/" + id,
        type: "GET",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        success: function (data) {
            let transDetail = data.data
            let response = "";
            transDetail.forEach(el => {
                response +=
                    'LogProductID: ' + el.logProductTransID
                    + '  -  Date: ' + el.dateLog
                    
            })
            alert(response)
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('error')
        },
    })
}

// upload img
function uploadIMG() {

    let formData = new FormData();
    // Attach file
    formData.append('file', $('input[type=file]')[0].files[0]);

    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/upload",
        type: "POST",
        contentType: false,
        processData: false,
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        data: formData,
        success: function (data) {
            document.getElementById("imgProduct").value = data.join(',');
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('error')
        },
    })
}

//  update img for product
function updateProductIMG(imgs = [], id) {
    let jwt = localStorage.getItem("token")
    $.ajax({
        url: "http://localhost:8080/api/v1/upload/" + id,
        type: "PUT",
        contentType: "application/json;",
        headers: {
            "Authorization": "Bearer " + jwt.substring(1, jwt.length - 1)
        },
        data:  JSON.stringify(imgs),
        success: function (data) {
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            alert('update img for product fail')
        },
    })
}

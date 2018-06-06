var ajaxUrl = 'ajax/superuser/credapp/';
var datatableApi;
var editTitleKey = "superuser.credapp";
var arrayProductCredAppId;
var arrayProductCredAppGroupElement;

var useridproduct;
var useridcreatedappcr;
var listProductId;
var listProduct;

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "fio"
            },
            {
                "data": "user.email"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    })
});

function renderEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-xs edit" onclick="updateRow(' + row.id + ');">' +
            '<span class="material-icons" aria-hidden="true">&#xE254;</span></a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-xs delete" onclick="deleteRow(' + row.id + ');">' +
            '<span class="material-icons" aria-hidden="true">&#xE872;</span></a>';
    }
}

function save() {
    var form = $('#detailsForm');
    var text = "";
    var prodlistaddyes = new Array(arrayProductCredAppId.length);
    for (var i = 0; i < arrayProductCredAppId.length; i++) {
        prodlistaddyes[i] = document.getElementById("product" + arrayProductCredAppId[i]).checked;
        text += arrayProductCredAppId[i] + "[checked=" + document.getElementById("product" + arrayProductCredAppId[i]).checked + "],";
    }
    if (!prodlistaddyes.includes(true)) {
        successButNo('superuser.products.error');
        return false;
    }
    text += "[useridcreatecrrap=" + useridcreatedappcr + "]";
    document.getElementById('listproductid').value = text;


    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('common.saved');
        }
    });
}


function updateRow(id) {
    if (arrayProductCredAppGroupElement !== undefined) {
        for (var i = 0; i < arrayProductCredAppGroupElement.length; i++) {
            $("#" + arrayProductCredAppGroupElement[i]).remove();
        }
    }

    $.ajax({
        url: ajaxUrl + id,
        type: "get",
        async: true,
        success: function (data) {
            $.each(data, function (key, value) {
                if (key === "productList") {
                    useridproduct = value[0].user.id;
                }
                if (key === "user") {
                    useridcreatedappcr = value.id;
                }
            });
        }
    }).then(function () {
        $.ajax({
            url: 'ajax/superuser/product/all/' + useridproduct,
            type: "get",
            async: true,
            success: function (data) {
                listProduct = new Array(data.length);
                listProductId = new Array(data.length);
                var j = 0;
                $.each(data, function (key, value) {
                    listProductId[j] = value.id;
                    listProduct[j++] = value;
                });
            }
        }).then(function () {
            $.ajax({
                url: ajaxUrl + id,
                type: "get",
                async: true,
                success: function (data) {
                    $.each(data, function (key, value) {
                        $('#detailsForm').find("input[name='" + key + "']").val(value);
                        if (key === "productList") {
                            var product = value;
                            var text = "";

                            arrayProductCredAppGroupElement = new Array(listProductId.length);
                            arrayProductCredAppId = new Array(listProductId.length);

                            var prodIdChecked = new Array(product.length);
                            for (var k = 0; k < product.length; k++) prodIdChecked[k] = product[k].id;

                            for (var i = 0; i < listProduct.length; i++) {
                                var checked = "";
                                if (prodIdChecked.includes(listProduct[i].id)) checked = "checked";
                                text += "<div class=\"form-group\" id=\"group" + (6 + i) + "\">" +
                                    "<label class=\"control-label col-xs-3 form-check-label\" for=\"product" + listProduct[i].id + "\">" + listProduct[i].name + "</label>" +
                                    "<div class=\"col-xs-6\">" +
                                    "<input class=\"form-control\" type=\"checkbox\" " + checked + " id=\"product" + listProduct[i].id + "\">" +
                                    "</div>" +
                                    "</div>";
                                arrayProductCredAppGroupElement[i] = "group" + (6 + i);
                                arrayProductCredAppId[i] = listProduct[i].id;
                            }
                            $("#group4").after(text);
                        }
                    });
                    $('#editRow').modal();
                }
            });
        });
    });
}
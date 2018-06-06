var ajaxUrl = 'ajax/superuser/product/';
var datatableApi;
var editTitleKey = "superuser.product.edit";
var userid;

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
                "data": "user.name"
            },
            {
                "data": "name"
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
    document.getElementById('description').value = document.getElementById('description').value + userid;

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

        $.ajax({
            url: ajaxUrl + id,
            type: "get",
            async: true,
            success: function (data) {
                $.each(data, function (key, value) {
                    if(key==="description") $('#detailsForm').find("textarea[name='" + key + "']").val(value);
                    $('#detailsForm').find("input[name='" + key + "']").val(value);
                    if(key==="user") userid = value.id;
                });
                $('#editRow').modal();
            }
        });
}
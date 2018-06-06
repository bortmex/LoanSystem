var ajaxUrl = 'ajax/admin/users/';
var datatableApi;
var editTitleKey ="users.edit";

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if(row.id===100002 || row.id===100007) return "";
                    if (type === 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (date, type, row) {
                        return '<span>' + getMounth(moment().format(date.substring(0, 10), "dd-MM-YYYY")) + '</span>';
            }
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
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        },
        "initComplete": makeEditable
    });
});

function getMounth(str) {
    var mounth = ['янв','фев','март','апр','май','июнь','июль','авг','сен','окт','нояб','дек'];
    var number = parseInt(str.substring(5,7));
    return str.substring(8,10) +'-'+ mounth[number] + '-'+ str.substring(0,4);
}

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
var ajaxUrl = 'ajax/repres/part/';
var datatableApi;

$(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

    datatableApi = $('#datatablepart').DataTable({
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
                "data": "email"
            },
            {
                "data": "password"
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

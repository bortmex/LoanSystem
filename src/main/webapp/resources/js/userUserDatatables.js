var ajaxUrl = '';
var datatableApi;

$(function () {


    datatableApi = $('#datatablepart').DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "productList"
            },
            {
                "data": "statusOfApplicationParner"
            },
            {
                "data": "statusOfApplicationRepresentative"
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

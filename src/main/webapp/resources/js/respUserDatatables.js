var ajaxUrl = 'ajax/repres/partner/';
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatablepart').DataTable({
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

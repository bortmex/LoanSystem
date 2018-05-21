var ajaxUrl = 'ajax/admin/users/';
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
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
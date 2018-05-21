var ajaxUrl = 'ajax/partner/credapp/';
var datatableApi;

$(function () {
    datatableApi = $('#detCredAppForm').DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "fio"
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderButton
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });

    $("#button1Ok").click(function () {
        enable(document.getElementById("name1").innerHTML, true);
        $('#showCredApp').modal('hide');
        return false;
    });

    $("#button2NoOk").click(function () {
        enable(document.getElementById("name1").innerHTML, false);
        $('#showCredApp').modal('hide');
        return false;
    });

    $('#detailsPartnerCredForm').button(function () {
        $('#showCredApp').modal('hide');
        return false;
    });

});

function renderButton(data, type, row) {
        return '<button type="button" class="btn btn-info" onclick="get('+row.id+')">' + i18n['credapps.more'] + ' </button>';
}

function get(id) {
    $.get('ajax/partner/creditapp/' + id, function(data){
        $("#name1").html(data.id);
        $("#name2").html(data.fio);
        $("#name3").html(data.phoneNumber);
        getProdWithId(data.id);
        $('#showCredApp').modal();
    });
}

function getProdWithId(idcredapp) {
    $.get('ajax/partner/product/all/idcred/' + idcredapp, function(dataProd){
        var text1 = dataProd.toString();
        var arr = text1.split(",");
        var text = "";
        for(var i =0; i<arr.length; i++){
            text+=arr[i] + "\n";
        }

        $("textarea[id='name4']").height(arr.length *20);
        $("textarea[id='name4']").val(text);
    });
}

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function enable(ids, bools) {
    $.ajax({
        url: ajaxUrl,
        type: 'POST',
        data:{id:ids, bool:bools},
        error: function(xhr, status, error){
            var errorMessage = xhr.status + ': ' + xhr.statusText;
            alert('Error - ' + errorMessage);
        },
        success: function() {
            updateTable();
            if(bools) successNoty('common.enable');
            else successButNo('common.dontenable');
        }
    });
}

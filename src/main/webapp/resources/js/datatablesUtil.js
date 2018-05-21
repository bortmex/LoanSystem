
function makeEditable() {

    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#detailsFormProduct').button(function () {
        $('#editRowProduct').modal('hide');
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    })
}

function add() {
    $('#id').val(null);
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (key, item) {
            datatableApi.row.add(item);
        });
        datatableApi.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    if(document.getElementById("role1")!==null){
    var x1 = document.getElementById("role1");
    var x2 = document.getElementById("role2");
    var x3 = document.getElementById("role3");
    if(x1.checked)
    form.find("input[name='roles']").val("ROLE_USER");
    if(x2.checked)
    form.find("input[name='roles']").val("ROLE_PARTNER");
    if(x3.checked)
        form.find("input[name='roles']").val("ROLE_REPRESENTATIVE");
    }
    // document.getElementById("roles").value = "Role.ROLE_USER";

    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}


function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    iziToast.show({
        color: 'green',
        position: 'bottomRight',
        timeout: 2000,
        message: i18n[key]
    });
}

function successButNo(key) {
    closeNoty();
    iziToast.show({
        color: 'red',
        position: 'bottomRight',
        timeout: 2000,
        message: i18n[key]
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = iziToast.show({
        message: i18n['common.failed'] + ': ' + jqXHR.statusText + "<br>" + jqXHR.responseJSON,
        color: 'red',
        position: 'bottomRight',
        timeout: 2000
    });
}

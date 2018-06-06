var form;

function makeEditable() {
    form = $('#detailsForm');

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#detailsFormProduct').button(function () {
        $('#editRowProduct').modal('hide');
        return false;
    });

    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    })
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            chkbox.closest('tr').toggleClass('disabled');
            successNoty(enabled ? 'common.enabled' : 'common.disabled');
        }
    });
}


function add() {
    ifnoneblock();
    $('#editRow').modal();
}

function add(title) {
    $('#modalTitle').html(title);
    ifnoneblock();
    $('#editRow').modal();
}

function deleteRow(id) {

    if (id === 100002) {
        successButNo('common.deleted.admin');
    } else if (id === 100004 || id === 100005) {
        successButNo('common.deleted.partner');
    } else if (id === 100006) {
        successButNo('common.deleted.representative');
    } else if (id === 100000) {
        successButNo('common.deleted.user');
    } else if (id === 100007) {
        successButNo('common.deleted.superuser');
    } else

        $.ajax({
            url: ajaxUrl + id,
            type: 'DELETE',
            success: function () {
                updateTable();
                successNoty('common.deleted');
            }
        });
}

function updateRow(id) {
    $('#modalTitle').html(i18n[editTitleKey]);
    $.get(ajaxUrl + id, function (data) {
        var yes = false;
        var docGroup1 = document.getElementById("group1");
        var docGroup2 = document.getElementById("group2");
        var docGroup3 = document.getElementById("group3");
        var docGroup5 = document.getElementById("group5");
        var docRolus = document.getElementById("group4");
        ifnoneblock();

        $.each(data, function (key, value) {
            if (key === "id") {
                if (value === 100000 || value === 100004 || value === 100005 || value === 100006 || value === 100007) yes = true;
            }
            if (key === "roles" && !yes) {
                if (arrayHaveValue(value, "ROLE_ADMIN")) yes = true;
                else if (arrayHaveValue(value, "ROLE_USER")) document.getElementById("role1").checked = true;
                else if (arrayHaveValue(value, "ROLE_PARTNER")) document.getElementById("role2").checked = true;
                else if (arrayHaveValue(value, "ROLE_REPRESENTATIVE")) document.getElementById("role3").checked = true;
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();

        if (yes) {
            docGroup5.style.display = "none";
            docGroup3.style.display = "none";
            docGroup2.style.display = "none";
            docGroup1.style.display = "none";
            docRolus.style.display = "none";
            $("#group1").after("<div  class=\"form-group\" id=\"group1war\">" +
                "<label for=\"warning\" class=\"control-label col-xs-3\">" + i18n['common.note'] + "</label>" +
                "                        <div class=\"col-xs-9\">" +
                "                            <textarea style=\"resize: none;height:8%;overflow:  hidden;\" class=\"form-control input-md\" id=\"warning\" readonly  name=\"warning\">" + i18n['common.note.description'] + "</textarea>" +
                "                        </div></div>");
        }
    });
}

function ifnoneblock() {
    var docRolus = document.getElementById("group4");
    var docGroup5 = document.getElementById("group5");
    var docGroup3 = document.getElementById("group3");
    var docGroup2 = document.getElementById("group2");
    var docGroup6 = document.getElementById("group1");
    var docGroup1 = document.getElementById("group1war");
    form.find(":input").val("");
    if (docRolus !== null) {
        if (docGroup1 !== null) $("#group1war").remove();
        if (docRolus.style.display === "none") docRolus.style.display = "block";
        if (docGroup2.style.display === "none") docGroup2.style.display = "block";
        if (docGroup3.style.display === "none") docGroup3.style.display = "block";
        if (docGroup5.style.display === "none") docGroup5.style.display = "block";
        if (docGroup6.style.display === "none") docGroup6.style.display = "block";
    }
}

function arrayHaveValue(str, text) {
    return str.indexOf(text) !== -1;
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
    if (document.getElementById('id').value !== "100002") {
        var x1 = document.getElementById("role1");
        var x2 = document.getElementById("role2");
        var x3 = document.getElementById("role3");
        if (x1 !== null || x2 !== null || x3 !== null) {
            if (x1.checked)
                form.find("input[name='roles']").val("ROLE_USER");
            if (x2.checked)
                form.find("input[name='roles']").val("ROLE_PARTNER");
            if (x3.checked)
                form.find("input[name='roles']").val("ROLE_REPRESENTATIVE");
        } else form.find("input[name='roles']").val("ROLE_PARTNER");
    } else form.find("input[name='roles']").val("ROLE_ADMIN");

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


function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


function successNoty(key) {
    iziToast.show({
        color: 'green',
        position: 'bottomRight',
        timeout: 2000,
        message: i18n[key]
    });
}

function successButNo(key) {
    iziToast.show({
        color: 'red',
        position: 'bottomRight',
        timeout: 2000,
        message: i18n[key]
    });
}

function failNoty(event, jqXHR, options, jsExc) {

    var errorInfo = $.parseJSON(jqXHR.responseText);
    iziToast.show({
        message: i18n['common.status'] + ': ' + jqXHR.status + "<br>" + errorInfo.cause + "<br>" + errorInfo.details.join("<br>"),
        color: 'red',
        position: 'bottomRight',
        timeout: 2000
    });
}


$(function () {
    $("#singlebutton").click(function () {
        var text = "" /*new Array($('#selectmultiple').val().length)*/;
        for (var i = 0; i < $('#selectmultiple').val().length; i++) text += $('#selectmultiple').val()[i] + ",";

        document.getElementById('listproductid').value = text;

        postapp();
        /*$.post("ajax/user/credapp/", $('#detForm').serialize()
        ).success( function(data){alert("success");alert(JSON.stringify(data)); $(location).attr('href', 'mycreditapplication'); })
            .error(function() { showAlert("Failed"); });*/
        /*alert($('#detForm').serialize());*/
        return false;
    });
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    })
    /*$.post("ajax/user/credapp/", function (data) {

        $('#id').val(null);
        alert(data);
        $(location).attr('href', 'mycreditapplication')
    })*/
});

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = iziToast.show({
        message: i18n['common.failed'] + ': ' + jqXHR.statusText + "<br>" + jqXHR.responseJSON,
        color: 'red',
        position: 'bottomRight',
        timeout: 2000
    });
}


function postapp() {
    $.ajax({
        type: "POST",
        url: "ajax/user/credapp/",
        data: {fio: $('#fio').val(), databirth: $('#databirth').val(), phonenumber: $('#phonenumber').val(),
            aninitialfee: $('#aninitialfee').val(), listproductid: $('#listproductid').val()},
        success: function () {
            $(location).attr('href', 'mycreditapplication?_addCredApp');
        }
    });
}

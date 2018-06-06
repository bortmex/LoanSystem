
$(function () {

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

    $("#singlebutton").click(function () {
        var text = "";
        for (var i = 0; i < $('#selectmultiple').val().length; i++) text += $('#selectmultiple').val()[i] + ",";
        document.getElementById('listproductid').value = text;
        if(checkDate()) {
            setValueDate();
            return false;
        }
        $.ajax({
            type: "POST",
            url: "ajax/user/credapp/",
            data: $('#detForm').serialize(),
            success: function () {
                $(location).attr('href', 'mycreditapplication?_addCredApp');
            }
        });
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        $("#help1").text("");
        $("#help2").text("");
        $("#help3").text("");
        $("#help4").text("");
        $("#help5").text("");
        try {
            var textError = jqXHR.responseJSON.split("<br>");
            for(var i = 0; i<textError.length-1; i++){
                var textLocalErr = textError[i];
                if(textLocalErr.split(" ")[0]==="fio") $("#help1").text(textLocalErr.substring(3, textLocalErr.length));
                else if(textLocalErr.split(" ")[0] === "phoneNumber") $("#help2").text(textLocalErr.substring(11, textLocalErr.length));
                else if(textLocalErr.split(" ")[0] === "listproductid") $("#help3").text(textLocalErr.substring(13, textLocalErr.length));
                else if(textLocalErr.split(" ")[0] === "dateBirth") $("#help5").text(textLocalErr.substring(9, textLocalErr.length));
                else if(textLocalErr.split(" ")[0] === "anInitialFee") $("#help4").text(textLocalErr.substring(12, textLocalErr.length));
            }
            setValueDate();
        }catch (error){
            /*failNoty(event, jqXHR, options, jsExc);*/
        }
    })
});


function checkDate() {
    var databirth1strArray = $('#detForm').find("input[name='dateBirth']").val().split("-");
    var databirth1 = new Date(databirth1strArray[0],databirth1strArray[1]-1,databirth1strArray[2]);
    var dataNow = new Date();
    var dataNow = new Date(dataNow.getUTCFullYear(), dataNow.getUTCMonth(), dataNow.getUTCDate() + 1);
    return databirth1.getTime()>=dataNow.getTime();
}

function setValueDate() {
    var databirth1strArray = $('#detForm').find("input[name='dateBirth']").val().split("-");
    var databirth1 = new Date(databirth1strArray[0], databirth1strArray[1]-1, databirth1strArray[2]);
    var dataNow = new Date();
    var dataNow = new Date(dataNow.getUTCFullYear(), dataNow.getUTCMonth(), dataNow.getUTCDate() + 1);
    if(databirth1.getTime()>=dataNow.getTime()){
        if($("#help5").val()==="")
            $("#help5").text(i18n['common.date.birthday']);
    }
}
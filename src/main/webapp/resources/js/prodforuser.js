var form;

function makeEditable1() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

    $('#detProductForm').submit(function () {
        form = $('#detProductForm');

        $.ajax({
            type: "POST",
            url: 'ajax/partner/product',
            data: form.serialize(),
            success: function () {
                location.href = "showproductlistforpartner" + "?_addProduct";
            }
        });
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        $("#help1").text("");
        $("#help2").text("");
        $("#help3").text("");
        try {
            var textError = jqXHR.responseJSON.split("<br>");
            for(var i = 0; i<textError.length-1; i++){
                var textLocalErr = textError[i];
                if(textLocalErr.split(" ")[0]==="description") $("#help3").text(textLocalErr.substring(12, textLocalErr.length));
                else if(textLocalErr.split(" ")[0] === "name") {if(textLocalErr.split(" ")[1]==="null") $("#help1").text(i18n['products.datetime.duplicate']); else $("#help1").text(textLocalErr.substring(5, textLocalErr.length));}
                else if(textLocalErr.split(" ")[0] === "price") $("#help2").text(textLocalErr.substring(6, textLocalErr.length));
            }
        }catch (error){
            failNoty(event, jqXHR, options, jsExc);
        }
    })
}

function addProduct(bool) {
    if(bool) successNoty('common.saved');
}

$(function () {
    makeEditable1();
});

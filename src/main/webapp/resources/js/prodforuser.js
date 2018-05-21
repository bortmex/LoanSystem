function makeEditable1() {
    $('#detProductForm').submit(function () {
        var form = $('#detProductForm');

        $.ajax({
            type: "POST",
            url: 'ajax/partner/product',
            data: form.serialize(),
            success: function () {
                location.href = "showCreditAppListAndProductListForPartner" + "?_addProduct";
            }
        });
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    })
}

function addProduct(bool) {
    if(bool) successNoty('common.saved');
}

$(function () {
    makeEditable1();
});

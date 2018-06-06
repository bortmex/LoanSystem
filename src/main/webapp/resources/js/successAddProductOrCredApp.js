$(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

    function successNoty(key) {
        iziToast.show({
            color: 'green',
            position: 'bottomRight',
            timeout: 2000,
            message: i18n[key]
        });
    }
    successNoty('common.saved');

});
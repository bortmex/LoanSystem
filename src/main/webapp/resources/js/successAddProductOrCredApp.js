$(function () {
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
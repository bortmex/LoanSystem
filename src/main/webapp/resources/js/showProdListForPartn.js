$(function () {
    $.get('ajax/partner/product/getall', function(data){
        var textHtml = "";
        for(var i = 0; i<data.length; i++){
            textHtml+="<li id=\"detailsLiProduct\" class=\"clearfix\"><section class=\"left\">" +
                "                <img src=\"resources/images/list-default-thumb.png\" alt=\"default thumb\" class=\"thumb\">" +
                "                <h3>"+data[i].name+"</h3>" +
                "                <span class=\"meta\">"+ i18n['product.id']+" "+ data[i].id+"</span>" +
                "                </section>" +
                "                <section class=\"right\">" +
                "                <span class=\"price\">"+ data[i].price+" "+ i18n['product.currency']+"</span>" +
                "                <span class=\"darkview\">" +
                "                <a onclick="+'get('+data[i].id+','+document.getElementById("partnerid").value+')'+"><img src=\"resources/images/read-more-btn.png\" alt=\"Read More...\"></a>" +
                "                </span>" +
                "                </section></li>"
        }
        $("#products").html(textHtml);
    });


        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

});
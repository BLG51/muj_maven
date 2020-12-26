$(document).ready(function () {
    $('.header').height($(window).height());
});

$(function () {
    $(".navbar a").on('click', function () {
        $("html, body").animate({
            scrollTop: $('#' + $(this).data('value')).offset().top
        }, 1000)
    });
});

$(function () {
    $('#btn1').on('click', function () {
        var myjson = {"text": $('#text1').val()};
        $.ajax({
            url: "/gettext",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(myjson),
            success: function (data) {
                $('#textarea').val(data["text"]);
            }
        });
    });
});
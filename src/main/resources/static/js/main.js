function clearSessionCookie() {
    if (document.cookie.length !== 0) {
        document.cookie += "JSESSIONID" + ";max-age=0"
    } else {
        console.log("cookie is  null")
    }

}

VanillaTilt.init(document.querySelectorAll(".card"), {
    max: 25,
    speed: 400,
    glare: true,
    "max-glare": 0.3
});

$(function () {
    $('[data-toggle="popover"]').popover();

    $('#cvc').on('click', function () {
        if ($('.cvc-preview-container').hasClass('hide')) {
            $('.cvc-preview-container').removeClass('hide');
        } else {
            $('.cvc-preview-container').addClass('hide');
        }
    });

    $('.cvc-preview-container').on('click', function () {
        $(this).addClass('hide');
    });
});
var swiper = new Swiper('.swiper-container', {
    slidesPerView: 3,
    spaceBetween: 10,
    //slidesPerColumn: 1,
    loop: true,
    speed:333,
   // effect : 'fade',
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
        renderBullet: function (index, className) {
            return '<span class="' + className + '">' + (index + 1) + '</span>';
        },
        //type: 'fraction',
        //type: 'progressbar',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    autoplay : {
        delay: 3000,
        disableOnInteraction: false,

    },
});

;(function($, window, undefined) {
    // outside the scope of the jQuery plugin to
    // keep track of all dropdowns
    var $allDropdowns = $();

    // if instantlyCloseOthers is true, then it will instantly
    // shut other nav items when a new one is hovered over
    $.fn.dropdownHover = function(options) {

        // the element we really care about
        // is the dropdown-toggle's parent
        $allDropdowns = $allDropdowns.add(this.parent());

        return this.each(function() {
            var $this = $(this).parent(),
                defaults = {
                    delay: 100,
                    instantlyCloseOthers: true
                },
                data = {
                    delay: $(this).data('delay'),
                    instantlyCloseOthers: $(this).data('close-others')
                },
                options = $.extend(true, {}, defaults, options, data),
                timeout;

            $this.hover(function() {
                if(options.instantlyCloseOthers === true)
                    $allDropdowns.removeClass('open');

                window.clearTimeout(timeout);
                $(this).addClass('open');
            }, function() {
                timeout = window.setTimeout(function() {
                    $this.removeClass('open');
                }, options.delay);
            });
        });
    };
    $('[data-hover="dropdown"]').dropdownHover();
})(jQuery, this);

if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
    new WOW().init();
};
(function ($) {
    "use strict";
    $(window).on('load', function () {
        nhPreloader();
        bpMenuareaFixed();
    });
    function nhPreloader() {
        if ($('#preloader').length) {
            $('#preloader').fadeOut('slow', function () {
                $(this).remove();
            });
        }
    }
    function bpMenuareaFixed() {
        if ($('.header').length) {
            $(window).on('scroll', function () {
                if ($(window).scrollTop() > 0) {
                    $('.header').addClass('navbar-fixed-top');
                } else {
                    $('.header').removeClass('navbar-fixed-top');
                }
            });
        }
    }
}(jQuery));
$(document).ready(function(){
    $('.counter-value').each(function(){
        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        },{
            duration: 1000,
            easing: 'swing',
            step: function (now){
                $(this).text(Math.ceil(now).toLocaleString());
            }
        });
    });
});
$(document).ready(function(){
    $('.counter-values').each(function(){
        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        },{
            duration: 1000,
            easing: 'swing',
            step: function (now){
                $(this).text(Math.ceil(now).toLocaleString());
            }
        });
    });
});
function pwdStrength(pwdStr) {
    var hasNumber = 0;
    var hasLetter = 0;
    var hasSymbol = 0
    if (pwdStr.length >= 6) {
        for (var i = 0; i < pwdStr.length; i++) {
            var item = pwdStr[i];
            if (item >= '0' && item <= '9') { hasNumber = 1; }
            else if ((item >= 'a' && item <= "z") || (item >= 'A' && item < "Z")) { hasLetter = 1; }
            else { hasSymbol = 1; }
        }
    }
    return hasLetter + hasNumber + hasSymbol;
}
//显示颜色
function pwStrength(pwd) {
    O_color = "#eeeeee";
    L_color = "#ff0000";
    M_color = "#ff9900";
    H_color = "#33cc00";
    if (pwd == null || pwd == '') {
        Lcolor = Mcolor = Hcolor = O_color;
    }
    else {
        S_level = pwdStrength(pwd);
        switch (S_level) {
            case 0:
                Lcolor = Mcolor = Hcolor = O_color;
            case 1:
                Lcolor = L_color;
                Mcolor = Hcolor = O_color;
                break;
            case 2:
                Lcolor = L_color;
                Mcolor = M_color;
                Hcolor = O_color;
                break;
            default:
                Lcolor = L_color;
                Mcolor = M_color;
                Hcolor = H_color;
        }
    }
    document.getElementById("strength_L").style.background = Lcolor;
    document.getElementById("strength_M").style.background = Mcolor;
    document.getElementById("strength_H").style.background = Hcolor;
    return;
}
$("#password").keyup(function(e){
    pwStrength($(e.target).val());
});

/* 计算页面加载速度 */
var t1 = new Date().getTime();
window.onload = function()
{
    document.getElementById("TimeShow").innerHTML = "加载本页耗时 "+  (new Date().getTime() - t1)/1000 +" 秒";
}
/* vue日期 */
var clock = new Vue({
    el: '#clock',
    data: {
        time: '',
        date: ''
    }
});
var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
var timerID = setInterval(updateTime, 1000);
updateTime();
function updateTime() {
    var cd = new Date();
    clock.time = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2);
    clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
};

function zeroPadding(num, digit) {
    var zero = '';
    for(var i = 0; i < digit; i++) {
        zero += '0';
    }
    return (zero + num).slice(-digit);
}

/* 设置 - 鼠标动画事件 开始 */


/* 设置 - 本站运行时间 开始 */



var text =' Youth博客主题 v3.5 ';
var youth ='%c' + text;
console.log('\n' + ' %c (づ￣ ³￣)づヾ 作者：CUI ' + youth + ' ' + '\n' + '\n', 'color: #fadfa3; background: #030307; padding:5px 0;', 'background: #fadfa3; padding:5px 0;');

var time = 600; //时间,秒
function Redirect() {
    window.location.href = "/lockScreen";
}
var i = 0;
function dis() {
    console.log("还剩" + (time - i) + "秒");
    i++;
}
timer = setInterval('dis()', 1000); //显示时间
timer = setTimeout('Redirect()', time * 1000); //跳转
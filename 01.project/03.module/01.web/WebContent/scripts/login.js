/**
 * 初始化
 */
$(document).ready(function() {
    //不知道为毛不显示
});

/**
* 按钮监听
*/
function keyPress(e){
    if( 13 == e.keyCode){
        login();
    }
}

/**
* 登陆
*/
function login(){
    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    //判非空
    if (name == EMPTY) {
        showAttention("请输入用户名!");
        return;
    }
    if (password == EMPTY) {
        showAttention("请输入密码!");
        return;
    }
    //md5签名
    var md5Pwd = MD5(password + md5Key);
    document.getElementById("password").value = md5Pwd;

    document.forms["loginForm"].submit();
}

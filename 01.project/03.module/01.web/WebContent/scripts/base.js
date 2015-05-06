//空
var EMPTY = "";

//符号集合
var SYMBOL_BLANK = " ";
var SYMBOL_COMMA = ",";
var SYMBOL_EQUAL = "=";
var SYMBOL_BIT_AND = "&";
var SYMBOL_SINGLE_QUOT = "'";
var SYMBOL_DOUBLE_QUOT = "\"";
var SYMBOL_LOGIC_AND = "&&";
var SYMBOL_WAVE = "~";
var SYMBOL_EXCLAMATION = "!";
var SYMBOL_MOUSE = "@";
var SYMBOL_WELL = "#";
var SYMBOL_DOLLAR = "$";
var SYMBOL_PERCENT = "%";
var SYMBOL_BIT_DIFF = "^";
var SYMBOL_STAR = "*";
var SYMBOL_SLASH = "/";
var SYMBOL_DOT = ".";
var SYMBOL_COLON = ":";
var SYMBOL_NEW_LINE = "\r\n";
var SYMBOL_NEW_LINE2 = "\n";
var SYMBOL_STAND = "|";
var SYMBOL_ARRAY_1 = new Array(SYMBOL_BIT_AND,SYMBOL_SINGLE_QUOT,SYMBOL_DOUBLE_QUOT,SYMBOL_SLASH);
var SYMBOL_ARRAY_2_CHECK_URL = new Array(SYMBOL_SINGLE_QUOT,SYMBOL_DOUBLE_QUOT);

//按键值
var KEY_CODE_ENTER = 13;
var KEY_CODE_CTRL = 17;
var KEY_CODE_DELETE = 46;

//默认信息提示框ID
var DEFAULT_MESSAGE_ID = "message_id";

/**
 * 计算str1中还有几个str2
 * @param str1
 * @param str2
 */
function containCount(str1, str2) {
    var count = 0;
    while(str1.indexOf(str2) > -1) {
         count ++;
        str1 = str1.substr(str1.indexOf(str2) + str2.length);
    }
    return count;
}

/**
 * 判断字符串是否含有非法字符
 * 校验通过返回result["isSuccess"]=true
 * 校验失败返回result["isSuccess"]=false,result["symbol"]=包含的非法字符
 * @param value
 * @param symbolArray
 */
function checkStr(value, symbolArray) {
    var result = EMPTY;
    for(var i=0;i<symbolArray.length;i++){
        if(value.indexOf(symbolArray[i]) > -1) {
            if("'" == symbolArray[i]){
                //对单引号特殊处理
                result = "{isSuccess:false,symbol:\"\'\"}";
            } else {
                result = "{isSuccess:false,symbol:'" + symbolArray[i] + "'}";
            }
            break;
        }
    }
    if(result == EMPTY){
        result = "{isSuccess:true}";
    }
    result = eval("(" + result + ")");
    return result;
}

/**
 * email格式校验
 * @param email
 */
function isEmail(email){
    var re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return re.test(email);
}

/**
 * num格式校验
 * @param num
 */
function isNum(num){
    var re = /^[\d]+$/
    return re.test(num);
}

/**
 * 判是否是移动电话
 * @param num
 */
function isMobilePhone(str){
    if(str.length != 11 || isNum(str) == false){
        return false;
    }
    return true;
}

/**
 * 显示信息
 * @param messageId
 * @param message
 */
function showMessage(messageId, message){
    $("#" + messageId + "_content").html(message);
    $("#" + messageId).css("opacity", 1);
    $("#" + messageId).show(300);
}

/**
 * 显示警告消息
 * @param message
 */
function showAttention(message){
    showAttentionMessage(DEFAULT_MESSAGE_ID, message);
}

/**
 * 显示警告消息
 * @param messageId
 * @param message
 */
function showAttentionMessage(messageId, message){
    $("#" + messageId).attr("class", "notification attention png_bg");
    showMessage(messageId, message);
}

/**
 * 显示消息
 * @param message
 */
function showInformation(message){
    showInformationMessage(DEFAULT_MESSAGE_ID, message);
}

/**
 * 显示消息
 * @param messageId
 * @param message
 */
function showInformationMessage(messageId, message){
    $("#" + messageId).attr("class", "notification information png_bg");
    showMessage(messageId, message);
}

/**
 * 显示成功消息
 * @param message
 */
function showSuccess(message){
    showSuccessMessage(DEFAULT_MESSAGE_ID, message);
}

/**
 * 显示成功消息
 * @param messageId
 * @param message
 */
function showSuccessMessage(messageId, message){
    $("#" + messageId).attr("class", "notification success png_bg");
    showMessage(messageId, message);
}

/**
 * 显示错误消息
 * @param message
 */
function showError(message){
    showErrorMessage(DEFAULT_MESSAGE_ID, message);
}

/**
 * 显示错误消息
 * @param messageId
 * @param message
 */
function showErrorMessage(messageId, message){
    $("#" + messageId).attr("class", "notification error png_bg");
    showMessage(messageId, message);
}

/**
 * 字符串编码
 * 采用Ajax传递参数加号(+)和与符号(&)时候，服务端获取到的参数并不如意！
 * 解决办法：在传到服务端之前先将参数中的"+"和"&"符号都编码一下
 * @param str
 */
function filterStr(str){
    str = filePlus(str);
    str = fileBitAnd(str);
    return str;
}

/**
 * 编码+号
 * @param str
 * @return {*}
 */
function filePlus(str){
    str = str.replace(/\+/g,"%2B");
    return str;
}

/**
 * 编码&号
 * @param str
 * @return {*}
 */
function fileBitAnd(str){
    str = str.replace(/\&/g,"%26");
    return str;
}

/**
 * 从array数组中把所有等于one的删掉
 * @param array
 * @param one
 */
function removeAllOneFromArray(array, one){
    var newArray = new Array();
    for(var i=0;i<array.length;i++){
        if(array[i] != one){
            newArray[newArray.length] = array[i];
        }
    }
    return newArray;
}

/**
 * 去掉前后空格
 * @param s
 * @return {*}
 */
function trim(s){
    return trimRight(trimLeft(s));
}
//去掉左边的空格
function trimLeft(s){
    if(s == null) {
        return "";
    }
    var whitespace = new String(" \t\n\r");
    var str = new String(s);
    if (whitespace.indexOf(str.charAt(0)) != -1) {
        var j=0, i = str.length;
        while (j < i && whitespace.indexOf(str.charAt(j)) != -1){
            j++;
        }
        str = str.substring(j, i);
    }
    return str;
}
//去掉右边的空格 www.2cto.com
function trimRight(s){
    if(s == null) return "";
    var whitespace = new String(" \t\n\r");
    var str = new String(s);
    if (whitespace.indexOf(str.charAt(str.length-1)) != -1){
        var i = str.length - 1;
        while (i >= 0 && whitespace.indexOf(str.charAt(i)) != -1){
            i--;
        }
        str = str.substring(0, i+1);
    }
    return str;
}
/**
 * 退出
 * 注意：用到$.ajax，所以得依赖jquery-min.js
 * 注意：用到变量baseUrl，所以得依赖header.jsp
 */
function logOut(){
    //ajax退出
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "logOut.jsp",
        data:EMPTY,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判退出是否成功
                if (false == data["isSuccess"]) {
                    alert(data["message"]);
                    return;
                } else {
                    //退出成功
                    //alert(data["message"]);
                }
                //是否跳转页面
                if (data["isRedirect"]) {
                    var redirectUrl = data["redirectUrl"];
                    location.href = redirectUrl;
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}

/**
 * 看不清，换一张验证码
 * @param t
 */
function changeSecurityCode(t){
    $(t).attr("src","securityCodeImage.do?timestamp="+new Date().getTime());
}
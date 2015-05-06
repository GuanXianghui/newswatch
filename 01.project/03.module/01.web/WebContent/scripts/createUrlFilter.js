/**
 * 新建网址过滤
 */
function createUrlFilter(){
    var filterUrlPart = $("#filterUrlPart").val();
    if(filterUrlPart == EMPTY){
        showAttention("模糊过滤网址 不能为空！");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(filterUrlPart, SYMBOL_ARRAY_2_CHECK_URL);
    if(result["isSuccess"] == false) {
        showAttention("模糊过滤网址包含非法字符："  + result["symbol"]);
        return;
    }
    var remark = $("#remark").val();
    // 判断字符串是否含有非法字符
    result = checkStr(remark, SYMBOL_ARRAY_2_CHECK_URL);
    if(result["isSuccess"] == false) {
        showAttention("备注包含非法字符："  + result["symbol"]);
        return;
    }
    var website = $("#website").val();
    var type = $("#type").val();
    var filterType = $("#filterType").val();

    //创建用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "createUrlFilter.do",
        data:"website=" + filterStr(website) + "&type=" + filterStr(type) + "&filterType=" + filterStr(filterType) + "&filterUrlPart=" + filterStr(filterUrlPart) + "&remark=" + filterStr(remark) + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //成功
                    showSuccess(data["message"]);
                    //location.href = "urlFilterConfig.jsp";
                    return;
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
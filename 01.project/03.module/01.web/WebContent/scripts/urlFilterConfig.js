/**
 * 初始化
 */
$(document).ready(function() {
    //如果message非空则显示
    if(EMPTY != message){
        showInformation(message);
    }
});

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    var html = "urlFilterConfig.jsp?pageNum=" + pageNum + "&website="+ $("#website").val()
        + "&type=" + $("#type").val() + "&filterType=" + $("#filterType").val()
        + "&filterUrlPart=" + $("#filterUrlPart").val() + "&remark=" + $("#remark").val();
    location.href = html;
}

/**
 * 带信息跳到某一页
 *
 * @param pageNum
 */
function jump2pageWithMessage(message) {
	var html = "urlFilterConfig.jsp?pageNum=" + pageNum + "&website="+ $("#website").val()
	    + "&type=" + $("#type").val() + "&filterType=" + $("#filterType").val()
	    + "&filterUrlPart=" + $("#filterUrlPart").val() + "&remark=" + $("#remark").val() + "&message=" + message;
	location.href = html;
}

/**
 * 删除网址过滤
 */
function deleteUrlFilter(id){
    //创建用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "deleteUrlFilter.do",
        data:"id=" + id + "&token=" + token,
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
                	jump2pageWithMessage(data["message"]);
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
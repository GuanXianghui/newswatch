var remarkCount = 0;//备注个数

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
    var html = "domainFilterConfig.jsp?pageNum=" + pageNum + "&website="+ $("#website").val()
        + "&type=" + $("#type").val() + "&domain=" + $("#domain").val() + "&remark=" + $("#remark").val();
    location.href = html;
}

/**
 * 带信息跳到某一页
 *
 * @param pageNum
 */
function jump2pageWithMessage(message) {
	var html = "domainFilterConfig.jsp?pageNum=" + pageNum + "&website="+ $("#website").val()
	    + "&type=" + $("#type").val() + "&domain=" + $("#domain").val() + "&remark=" + $("#remark").val() + "&message=" + message;
	location.href = html;
}

/**
 * 删除域名过滤
 */
function deleteDomainFilter(id){
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "deleteDomainFilter.do",
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

/**
 * 刷新需要配置的域名
 */
function refreshNeedConfigDomain(){
	var website = $("#website0").val();
	$("#cwr_table0").css("display", "none");
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "refreshNeedConfigDomain.do",
        data:"website=" + website + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
                //判是否成功
                if (false == data["isSuccess"]) {
                    showErrorMessage("message_id_0", data["message"]);
                } else {
                    //成功
                	showSuccessMessage("message_id_0", data["message"]);
                	var html = EMPTY;
                	for(var i=0;i<data["tenDomains"].length;i++){
                		html += "<tr id=\"config_tr_" + (++remarkCount) + "\"><td>" + $("#website0").val() + 
                		"</td><td><a id=\"config_a_" + (remarkCount) + "\" href='" + data["tenDomains"][i] + "' target='_blank' onclick=\"$(this).css('color', 'blue')\">" + data["tenDomains"][i] + "</a>" +
                		"</td><td><input type=\"text\" class=\"text-input large-input\" id=\"config_remark_" + (remarkCount) + "\">" + 
                		"</td><td>" + 
                		"<input class=\"button\" type=\"button\" onclick=\"createDomainFilter('" + website + "',true," + remarkCount + ");\" value=\"白名单\" />" + 
                    	"<input class=\"button\" type=\"button\" onclick=\"createDomainFilter('" + website + "',false," + remarkCount + ");\" value=\"黑名单\" />" + 
                		"</td></tr>"
                	}
                	$("#cwr_tbody0").html(html);
                	$("#cwr_table0").css("display", "block");
                	$('tbody tr:even').addClass("alt-row");
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

/**
 * 创建域名过滤
 * @param isWhite
 * @param index
 */
function createDomainFilter(website, isWhite, index){
	var configTr = $("#config_tr_" + index);
	var domain = $("#config_a_" + index).html();
	var remark = $("#config_remark_" + index).val();
	if(remark.indexOf("'") > -1){
		showAttentionMessage("message_id_0", "备注含有非法字符");
		return;
	}
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "createDomainFilter.do",
        data:"website=" + website + "&domain=" + domain + "&isWhite=" + isWhite + "&remark=" + remark + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
                //判是否成功
                if (false == data["isSuccess"]) {
                    showErrorMessage("message_id_0", data["message"]);
                } else {
                    //成功
                	showSuccessMessage("message_id_0", data["message"]);
                	configTr.css("display", "none");
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

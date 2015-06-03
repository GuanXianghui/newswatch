var remarkCount = 0;//备注个数

var faceboxWebsite = EMPTY;
var faceboxUrl = EMPTY;
var faceboxLikeUrl = EMPTY;
var faceboxNotLikeUrl = EMPTY;
var faceboxOrderIndex = EMPTY;
var faceboxTitlePre = EMPTY;
var faceboxTitlePost = EMPTY;
var faceboxTimePre = EMPTY;
var faceboxTimePost = EMPTY;
var faceboxSourcePre = EMPTY;
var faceboxSourcePost = EMPTY;
var faceboxAuthorPre = EMPTY;
var faceboxAuthorPost = EMPTY;
var faceboxContentPre = EMPTY;
var faceboxContentPost = EMPTY;


/**
 * 初始化
 */
$(document).ready(function() {
	//facebox浮层控件
    $('a[rel*=facebox]').facebox({
        loadingImage : baseUrl + '/images/loading.gif',
        closeImage : baseUrl + '/images/closelabel.png'
    });
});

/**
 * 刷新需要内容抓取方案配置的新闻
 */
function refreshNeedFetchPlanNews(){
	var website = $("#website0").val();
	$("#cwr_table0").css("display", "none");
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "refreshNeedFetchPlanNews.do",
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
                    showError(data["message"]);
                } else {
                    //成功
                	showSuccess(data["message"]);
                	var html = EMPTY;
                	for(var i=0;i<data["newsList"].length;i++){
                		html += "<tr id=\"config_tr_" + (++remarkCount) + "\"><td>" + (i+1) + 
                		"</td><td>" + $("#website0").val() + 
                		"</td><td><a id=\"config_a_" + (remarkCount) + "\" href='" + data["newsList"][i] + "' target='_blank' onclick=\"$(this).css('color', 'blue')\">" + data["newsList"][i] + "</a>" +
                		"</td><td>" + 
                		"<input class=\"button\" type=\"button\" onclick=\"setOnlyDisplayPre('" + website + "'," + remarkCount + ");\" value=\"仅展示\" />" + 
                    	"<input class=\"button\" type=\"button\" onclick=\"fetchPlanConfig('" + website + "'," + remarkCount + ");\" value=\"配置抓取方案\" />" + 
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
 * 设置仅展示准备
 * @param website
 * @param remarkCount
 */
function setOnlyDisplayPre(website, remarkCount){
	document.getElementById("set_only_display_a").click();
	var url = $("#config_a_" + remarkCount).attr("href");
	$("#facebox #set_only_display_like_url").val(url);
	$("#facebox #set_only_display_website").html(website);
}

/**
 * 设置仅展示
 */
function setOnlyDisplay(){
	if(confirm("您确定要设置为仅展示吗？") == false){
		return;
	}
	var website = $("#facebox #set_only_display_website").html();
	var likeUrl = $("#facebox #set_only_display_like_url").val();
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "setOnlyDisplay.do",
        data:"website=" + website + "&likeUrl=" + filterStr(likeUrl) + "&token=" + token,
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
                	$.facebox.close();
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
 * 配置抓取方案
 * @param website
 * @param remarkCount
 */
function fetchPlanConfig(website, remarkCount){
	var url = $("#config_a_" + remarkCount).attr("href");
	document.getElementById("fetch_plan_config_a").click();
	$("#facebox #website").html(website);
	$("#facebox #url").html("<a href='" + url + "' target='_blank' onclick=\"$(this).css('color', 'blue')\">" + url + "</a>");
	$("#facebox #like_url").val(url);
	$("#facebox #order_index").val("12345");
	$('#facebox').css('left', $(window).width() / 2 - ($('#facebox .popup').width() / 2));
	$("tr:even").css("background", "#f3f3f3");
	$("td").css("text-align", "center");
}

/**
 * 测试抓取方案配置
 */
function testFetchPlanConfig(){
	faceboxWebsite = $("#facebox #website").html();
	faceboxUrl = $("#facebox #url").html();
	faceboxLikeUrl = $("#facebox #like_url").val();
	faceboxNotLikeUrl = $("#facebox #not_like_url").val();
	faceboxOrderIndex = $("#facebox #order_index").val();
	faceboxTitlePre = $("#facebox #title_pre").val();
	faceboxTitlePost = $("#facebox #title_post").val();
	faceboxTimePre = $("#facebox #time_pre").val();
	faceboxTimePost = $("#facebox #time_post").val();
	faceboxSourcePre = $("#facebox #source_pre").val();
	faceboxSourcePost = $("#facebox #source_post").val();
	faceboxAuthorPre = $("#facebox #author_pre").val();
	faceboxAuthorPost = $("#facebox #author_post").val();
	faceboxContentPre = $("#facebox #content_pre").val();
	faceboxContentPost = $("#facebox #content_post").val();
	if(EMPTY == faceboxWebsite || EMPTY == faceboxUrl || 
			EMPTY == faceboxLikeUrl || EMPTY == faceboxOrderIndex){
		alert("必输字段为空");
		return;
	}
	//测试抓取方案查询网址
	testFetchPlanQueryUrl();
}

/**
 * 测试抓取方案查询网址
 */
function testFetchPlanQueryUrl(){
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "testFetchPlanQueryUrl.do",
        data:"website=" + faceboxWebsite + "&likeUrl=" + filterStr(faceboxLikeUrl) + "&notLikeUrl=" + filterStr(faceboxNotLikeUrl) + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
            	data = eval("(" + data + ")");
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
                //判是否成功
                if (false == data["isSuccess"]) {
                    alert(data["message"]);
            		return;
                } else {
                	if(data["urlList"].length == 0){
                		alert("查询不到对应的网址！");
                		return;
                	}
                	document.getElementById("test_fetch_plan_a").click();
                    //成功
                	var html = EMPTY;
                	for(var i=0;i<data["urlList"].length;i++){
                		html += "<option value='" + data["urlList"][i] + "'>" + data["urlList"][i] + "</option>"
                	}
                	$("#facebox #testFetchPlanUrl").html(html);
                	$("option:even").css("background", "#f3f3f3");
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
 * 返回配置抓取方案
 */
function backFetchPlanConfig(){
	document.getElementById("fetch_plan_config_a").click();
	$("#facebox #website").html(faceboxWebsite);
	$("#facebox #url").html(faceboxUrl);
	$("#facebox #like_url").val(faceboxLikeUrl);
	$("#facebox #not_like_url").val(faceboxNotLikeUrl);
	$("#facebox #order_index").val(faceboxOrderIndex);
	$("#facebox #title_pre").val(faceboxTitlePre);
	$("#facebox #title_post").val(faceboxTitlePost);
	$("#facebox #time_pre").val(faceboxTimePre);
	$("#facebox #time_post").val(faceboxTimePost);
	$("#facebox #source_pre").val(faceboxSourcePre);
	$("#facebox #source_post").val(faceboxSourcePost);
	$("#facebox #author_pre").val(faceboxAuthorPre);
	$("#facebox #author_post").val(faceboxAuthorPost);
	$("#facebox #content_pre").val(faceboxContentPre);
	$("#facebox #content_post").val(faceboxContentPost);
	
	$('#facebox').css('left', $(window).width() / 2 - ($('#facebox .popup').width() / 2));
	$("tr:even").css("background", "#f3f3f3");
	$("td").css("text-align", "center");
}

/**
 * 测试抓取方案
 */
function testFetchPlan(){
	var testUrl = $("#facebox #testFetchPlanUrl").val();
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "testFetchPlan.do",
        data:"website=" + faceboxWebsite + "&url=" + filterStr(faceboxUrl) + "&likeUrl=" + filterStr(faceboxLikeUrl)
	        + "&notLikeUrl=" + filterStr(faceboxNotLikeUrl) + "&orderIndex=" + faceboxOrderIndex
	        + "&titlePre=" + filterStr(faceboxTitlePre) + "&titlePost=" + filterStr(faceboxTitlePost)
	        + "&timePre=" + filterStr(faceboxTimePre) + "&timePost=" + filterStr(faceboxTimePost)
	        + "&sourcePre=" + filterStr(faceboxSourcePre) + "&sourcePost=" + filterStr(faceboxSourcePost)
	        + "&authorPre=" + filterStr(faceboxAuthorPre) + "&authorPost=" + filterStr(faceboxAuthorPost)
	        + "&contentPre=" + filterStr(faceboxContentPre) + "&contentPost=" + filterStr(faceboxContentPost)
	        + "&testUrl=" + filterStr(testUrl) + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
                //判是否成功
                if (false == data["isSuccess"]) {
                    alert(data["message"]);
                } else {
                    //成功
                	$("#facebox #test_title").val(data["title"]);
                	$("#facebox #test_date").val(data["date"]);
                	$("#facebox #test_time").val(data["time"]);
                	$("#facebox #test_source").val(data["source"]);
                	$("#facebox #test_author").val(data["author"]);
                	$("#facebox #test_content").val(data["content"]);
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
 * 保存抓取方案
 */
function saveFetchPlan(){
	if(confirm("您确定要保存该抓取方案吗？") == false){
		return;
	}
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "saveFetchPlan.do",
        data:"website=" + faceboxWebsite + "&url=" + filterStr(faceboxUrl) + "&likeUrl=" + filterStr(faceboxLikeUrl)
	        + "&notLikeUrl=" + filterStr(faceboxNotLikeUrl) + "&orderIndex=" + faceboxOrderIndex
	        + "&titlePre=" + filterStr(faceboxTitlePre) + "&titlePost=" + filterStr(faceboxTitlePost)
	        + "&timePre=" + filterStr(faceboxTimePre) + "&timePost=" + filterStr(faceboxTimePost)
	        + "&sourcePre=" + filterStr(faceboxSourcePre) + "&sourcePost=" + filterStr(faceboxSourcePost)
	        + "&authorPre=" + filterStr(faceboxAuthorPre) + "&authorPost=" + filterStr(faceboxAuthorPost)
	        + "&contentPre=" + filterStr(faceboxContentPre) + "&contentPost=" + filterStr(faceboxContentPost)
	        + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
                //判是否成功
                if (false == data["isSuccess"]) {
                    showAttention(data["message"]);
                } else {
                    //成功
                	showSuccess(data["message"]);
                	$.facebox.close();
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





/**
 * 初始化
 */
$(document).ready(function() {
    //如果message非空则显示
    if(EMPTY != message){
        showInformation(message);
    }
    //日期控件初始化
    $("#startDate").datepicker();
    $( "#startDate" ).datepicker( "option", "dateFormat", "yymmdd" );
    $( "#startDate" ).datepicker( "option", "showAnim", "clip" );
    $( "#startDate" ).datepicker( "option", "onSelect", function(dateText, inst){
    });
    $("#endDate").datepicker();
    $( "#endDate" ).datepicker( "option", "dateFormat", "yymmdd" );
    $( "#endDate" ).datepicker( "option", "showAnim", "clip" );
    $( "#endDate" ).datepicker( "option", "onSelect", function(dateText, inst){
    });
    //赋值起始日期
    $("#startDate").val(initStartDate);
    //赋值终止日期
    $("#endDate").val(initEndDate);
});

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    var html = "newsSearch.jsp?pageNum=" + pageNum + "&uuid="+ $("#uuid").val()
        + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
    location.href = html;
}
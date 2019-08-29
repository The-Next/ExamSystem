//获取到添加一行按钮
$("#add").click(function () {
    var form = layui.form;
    console.log("1111");
    var numLast  = $("#list div:last-child").children("span").text();//字符串格式的最后一列的数字
    if (numLast==undefined||numLast==null||numLast==''){
        numLast=-1;
        console.log(numLast);
    }
    var numNow = ++numLast;
    console.log(numNow);

    $("#list").append('<div class="layui-form-item">' +
        '            <span class="neon-text" style="display: none">'+numNow+'</span>' +
        '            <label class="layui-form-label required">选项</label>' +
        '            <div class="layui-input-inline">' +
        '                <input class="layui-input" type="text" name="multipleOptions['+numNow+'].m_option"  placeholder="请输入选项信息"/>' +
        '<a onclick="deleteLine(this);">删除</a>'+
        '            </div>' +
        '            <div class="layui-form-item">' +
        '                <label class="layui-form-label">是否正确</label>' +
        '                <div class="layui-input-block">' +
        '                    <input type="radio" name="multipleOptions['+numNow+'].isTrue" value="1" title="对" checked="checked">' +
        '                    <input type="radio" name="multipleOptions['+numNow+'].isTrue" value="0" title="错">' +
        '                </div>' +
        '            </div>' +
        '        </div>');
    form.render();
    console.log('子节点数'+$("#list").children.length);
}
);

//获取到删除一行按钮
$("#delete").click(function () {
    console.log($("#list div:last-child").html());
    $("#list>div:last-child").remove();
});

var deleteLine = function (a) {
    var msg = "你确认要删除这一条信息吗？";
    if (confirm(msg) == true){
        $(a.parentNode.parentNode).remove();
        return true;
    } else {
        return false;
    }

}
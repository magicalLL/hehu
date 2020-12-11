/**
 * 添加或者修改页面
 */
var WyhcInfoDlg = {
    data: {
        id: "",
        tbId: "",
        url: "",
        shr: "",
        shsj: "",
        status: "",
        shyy: "",
        description: "",
        sbsj: "",
        sbr: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;





























    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wyhc/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/wyhc'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/wyhc'
    });

});
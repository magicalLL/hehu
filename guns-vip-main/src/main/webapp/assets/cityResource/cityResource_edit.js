/**
 * 详情对话框
 */
var CityResourceInfoDlg = {
    data: {
        id:"",
        resourceAddress: "",
        resourceType: "",
        resourceName: "",
        resourceAliasName: "",
        resourceAbstract: "",
        resourceKeyword: "",
        resourceRunstate: "",
        publishUserid: "",
        publishUserName: "",
        publishDate: "",
        phoneNumber: "",
        verifyState: 0,
        fieldSubject: "",
        layerFiled: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate=layui.laydate;
    //初始化时间选择器
    laydate.render({
        elem: '#publishDate'
        , type: 'datetime'
    });

    console.log(Feng.getUrlParam("id"));
    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/cityResource/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();


    form.val('cityResourceForm', result.data);

    if(result.data.resourceRunstate===0)
        $('#resourceRunstate').prop("checked",true);
    else
        $('#resourceRunstate').prop('checked',false);

    form.render("checkbox");

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {

        if(data.field.resourceRunstate==='on')
            data.field.resourceRunstate=0;
        else
            data.field.resourceRunstate=1;

        var ajax = new $ax(Feng.ctxPath + "/cityResource/editItem", function (data) {
            Feng.success("更新成功！");
            // window.location.href = Feng.ctxPath + '/cityResource';
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

});
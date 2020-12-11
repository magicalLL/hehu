/**
 * 详情对话框
 */
var ServiceMatedataInfoDlg = {
    data: {
        id: "",
        resourceid: "",
        name: "",
        title: "",
        serviceTypeid: "",
        typeVersion: "",
        publishUrl: "",
        rawUrl: "",
        fileid: "",
        west: "",
        east: "",
        north: "",
        south: "",
        resourceOwnerDept: "",
        resourceOwnerDeptUrl: "",
        resourceOwner: "",
        resourceOwnerJob: "",
        country: "",
        city: "",
        address: "",
        postcode: "",
        tele: "",
        fax: "",
        email: "",
        accessRestriction: "",
        useRestriction: "",
        spatialReference: "",
        spatialType: "",
        spatialResolution: "",
        subjectType: "",
        otherDescription: "",
        layermetadataid: "",
        manifactureDate: "",
        timemark: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;








































































































    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/serviceMatedata/detail?=" + Feng.getUrlParam(""));
    var result = ajax.start();
    form.val('serviceMatedataForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/serviceMatedata/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/serviceMatedata'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/serviceMatedata'
    });
});
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

});
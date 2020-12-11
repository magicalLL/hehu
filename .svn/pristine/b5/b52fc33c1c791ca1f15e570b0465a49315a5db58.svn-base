/**
 * 添加或者修改页面
 */
var CityResourceInfoDlg = {
    data:{
        resourceAddress:"",
        resourceType:"",
        resourceName:"",
        resourceAliasName:"",
        resourceAbstract:"",
        resourceKeyword:"",
        resourceRunstate:"",
        publishUserid:"",
        deptId:"",
        publishUserName:"",
        deptName:"",
        publishDate:"",
        phoneNumber:"",
        verifyState:0,
        fieldSubject:"",
        layerFiled:""
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
        elem:'#publishDate'
        , type:'datetime'
    });


    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {

        if(data.field.resourceAddress===""||data.field.resourceType===""||
            data.field.resourceName===""||data.field.resourceAliasName===""||
            data.field.resourceAbstract===""||data.field.resourceKeyword===""||
            data.field.resourceRunstate===""||data.field.publishUserid===""||
            data.field.publishUserName===""||data.field.publishDate===""||
            data.field.phoneNumber==="")
        {
            Feng.error("必填选项不能为空!!!");
            return false;
        }
        
        if(data.field.resourceRunstate==='on')
            data.field.resourceRunstate=0;
        else
            data.field.resourceRunstate=1;
        console.log(JSON.stringify(data.field));
        var ajax = new $ax(Feng.ctxPath + "/cityResource/addItem", function (data) {

            Feng.success("新增成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        //关掉对话框
        admin.closeThisDialog();
    });

});
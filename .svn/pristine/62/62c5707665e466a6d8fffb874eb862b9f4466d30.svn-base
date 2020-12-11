/**
 * 添加或者修改页面
 */
var CityResultsInfoDlg = {
    data: {
        id: "",
        name: "",
        img: "",
        url: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //上传文件
    upload.render({
        elem: '#fileBtn'
        , url: Feng.ctxPath + '/cityResults/uploadResultImg'
        , accept: 'file'
        , before: function (obj) {
            obj.preview(function (index, file, result) {

            });
        }
        , done: function (res) {
            console.log(res);
            Feng.success(res.message);
            $("#img").val(res.finalName);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/cityResults/saveResult", function (data) {
            Feng.success("新增成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("新增失败！" + data.responseJSON.message)
        });
        console.log('请求参数: '+JSON.stringify(data.field));
        ajax.set(data.field);
        ajax.start();

        return false;
    });


});
/**
 * 详情对话框
 */
var CityFileInfoDlg = {
    data: {
        id: "",
        filename: "",
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

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/cityFile/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    // console.log('<<<<<<< '+JSON.stringify(result.data));
    form.val('cityFileForm', result.data);

    //上传文件
    upload.render({
        elem: '#fileBtn'
        , url: Feng.ctxPath + '/cityFile/uploadFile'
        , accept: 'file'
        , before: function (obj) {
            obj.preview(function (index, file, result) {

            });
        }
        , done: function (res) {
            console.log(res);
            Feng.success(res.message);
            $("#filename").val(res.finalName);
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/cityFile/editItem", function (data) {
            Feng.success("更新成功！");
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
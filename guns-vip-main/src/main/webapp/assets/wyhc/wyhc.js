layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Wyhc = {
        tableId: "wyhcTable"
    };

    /**
     * 初始化表格的列
     */
    Wyhc.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'tbId', sort: true, title: '图班id'},
            {field: 'url', sort: true, title: '上报图片地址'},
            {field: 'shr', sort: true, title: '审核人'},
            {field: 'shsj', sort: true, title: '审核时间'},
            {field: 'status', sort: true, title: '审核状态：0待审核，1已通过审核，2审核不通过'},
            {field: 'shyy', sort: true, title: '审核原因'},
            {field: 'description', sort: true, title: '上报原因'},
            {field: 'sbsj', sort: true, title: '上报时间'},
            {field: 'sbr', sort: true, title: '上报人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Wyhc.search = function () {
        var queryData = {};


        table.reload(Wyhc.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Wyhc.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/wyhc/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Wyhc.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/wyhc/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Wyhc.exportExcel = function () {
        var checkRows = table.checkStatus(Wyhc.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Wyhc.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wyhc/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Wyhc.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Wyhc.tableId,
        url: Feng.ctxPath + '/wyhc/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Wyhc.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Wyhc.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Wyhc.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Wyhc.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Wyhc.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Wyhc.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Wyhc.onDeleteItem(data);
        }
    });
});

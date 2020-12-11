layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Test2 = {
        tableId: "test2Table"
    };

    /**
     * 初始化表格的列
     */
    Test2.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'test1', sort: true, title: ''},
            {field: 'test2', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Test2.search = function () {
        var queryData = {};


        table.reload(Test2.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Test2.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/test2/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Test2.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/test2/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Test2.exportExcel = function () {
        var checkRows = table.checkStatus(Test2.tableId);
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
    Test2.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/test2/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Test2.tableId);
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
        elem: '#' + Test2.tableId,
        url: Feng.ctxPath + '/test2/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Test2.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Test2.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Test2.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Test2.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Test2.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Test2.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Test2.onDeleteItem(data);
        }
    });
});

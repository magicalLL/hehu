layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Test = {
        tableId: "testTable"
    };

    /**
     * 初始化表格的列
     */
    Test.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'test1', sort: true, title: ''},
            {field: 'test2', sort: true, title: ''},
            {field: 'test3', sort: true, title: '测试'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Test.search = function () {
        var queryData = {};


        table.reload(Test.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Test.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/test/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Test.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/test/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Test.exportExcel = function () {
        var checkRows = table.checkStatus(Test.tableId);
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
    Test.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/test/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Test.tableId);
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
        elem: '#' + Test.tableId,
        url: Feng.ctxPath + '/test/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Test.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Test.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Test.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Test.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Test.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Test.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Test.onDeleteItem(data);
        }
    });
});

layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Bzresource = {
        tableId: "bzresourceTable"
    };

    /**
     * 初始化表格的列
     */
    Bzresource.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'userid', sort: true, title: '用户id'},
            {field: 'tablename', sort: true, title: '表名'},
            {field: 'createtime', sort: true, title: '创建时间'},
            {field: 'tablealias', sort: true, title: '表的别名'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Bzresource.search = function () {
        var queryData = {};


        table.reload(Bzresource.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Bzresource.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/bzresource/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Bzresource.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/bzresource/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Bzresource.exportExcel = function () {
        var checkRows = table.checkStatus(Bzresource.tableId);
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
    Bzresource.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/bzresource/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Bzresource.tableId);
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
        elem: '#' + Bzresource.tableId,
        url: Feng.ctxPath + '/bzresource/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Bzresource.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Bzresource.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Bzresource.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Bzresource.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Bzresource.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Bzresource.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Bzresource.onDeleteItem(data);
        }
    });
});

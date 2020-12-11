layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Bzfield = {
        tableId: "bzfieldTable"
    };

    /**
     * 初始化表格的列
     */
    Bzfield.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'name', sort: true, title: '字段名'},
            {field: 'alias', sort: true, title: '字段别名'},
            {field: 'type', sort: true, title: '字段类型'},
            {field: 'tableid', sort: true, title: '所属表id'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Bzfield.search = function () {
        var queryData = {};


        table.reload(Bzfield.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Bzfield.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/bzfield/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Bzfield.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/bzfield/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Bzfield.exportExcel = function () {
        var checkRows = table.checkStatus(Bzfield.tableId);
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
    Bzfield.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/bzfield/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Bzfield.tableId);
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
        elem: '#' + Bzfield.tableId,
        url: Feng.ctxPath + '/bzfield/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Bzfield.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Bzfield.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Bzfield.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Bzfield.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Bzfield.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Bzfield.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Bzfield.onDeleteItem(data);
        }
    });
});

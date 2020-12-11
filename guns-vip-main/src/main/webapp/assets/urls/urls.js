layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Urls = {
        tableId: "urlsTable"
    };

    /**
     * 初始化表格的列
     */
    Urls.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true, title: ''},
            {field: 'name', sort: true, title: ''},
            {field: 'url', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Urls.search = function () {
        var queryData = {};


        table.reload(Urls.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Urls.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/urls/add',
            tableId: Urls.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Urls.openEditDlg = function (data) {
          func.open({
              title: '修改',
              content: Feng.ctxPath + '/urls/edit?=' + data.id,
              tableId: Urls.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    Urls.exportExcel = function () {
        var checkRows = table.checkStatus(Urls.tableId);
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
    Urls.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/urls/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Urls.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Urls.tableId,
        url: Feng.ctxPath + '/urls/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Urls.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Urls.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Urls.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Urls.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Urls.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Urls.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Urls.onDeleteItem(data);
        }
    });
});

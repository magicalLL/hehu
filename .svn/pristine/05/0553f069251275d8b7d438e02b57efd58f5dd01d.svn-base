layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var CityFile = {
        tableId: "cityFileTable"
    };

    /**
     * 初始化表格的列
     */
    CityFile.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true,align: "center", title: '编号'},
            {field: 'filename', sort: true,align: "center", title: '文件名称'},
            {field: 'filepath', sort: true,align: "center", title: '文件路径'},
            {field: 'createtime', sort: true,align: "center", title: '创建时间'},
            {align: 'center', toolbar: '#tableBar',align: "center", title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CityFile.search = function () {
        var queryData = {};

        queryData['filename'] = $('#FILENAME').val();

        table.reload(CityFile.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    CityFile.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/cityFile/add',
            tableId: CityFile.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      CityFile.openEditDlg = function (data) {
          func.open({
              title: '修改',
              content: Feng.ctxPath + '/cityFile/edit?id=' + data.id,
              tableId: CityFile.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    CityFile.exportExcel = function () {
        var checkRows = table.checkStatus(CityFile.tableId);
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
    CityFile.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/cityFile/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CityFile.tableId);
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
        elem: '#' + CityFile.tableId,
        url: Feng.ctxPath + '/cityFile/listForSys',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CityFile.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CityFile.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    CityFile.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        CityFile.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + CityFile.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CityFile.openEditDlg(data);
        } else if (layEvent === 'delete') {
            CityFile.onDeleteItem(data);
        }
    });
});

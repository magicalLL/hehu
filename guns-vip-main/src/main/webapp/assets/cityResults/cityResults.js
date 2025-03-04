layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var CityResults = {
        tableId: "cityResultsTable"
    };

    /**
     * 初始化表格的列
     */
    CityResults.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', align: 'center',sort: true, title: '编号'},
            {field: 'name', align: 'center',sort: true, title: '名称'},
            {
                field: 'img',align: 'center', templet: function (d) {
                    return '<img src="' + d.img + '" class="tdImg" />';
                }, title: '缩略图',  unresize: false
            },
            {field: 'url', sort: true,align: 'center', title: '服务地址'},
            {align: 'center', align: 'center',toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CityResults.search = function () {
        var queryData = {};

        queryData['name'] = $('#NAME').val();

        table.reload(CityResults.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    CityResults.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/cityResults/add',
            tableId: CityResults.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      CityResults.openEditDlg = function (data) {
          func.open({
              title: '修改',
              content: Feng.ctxPath + '/cityResults/edit?id=' + data.id,
              tableId: CityResults.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    CityResults.exportExcel = function () {
        var checkRows = table.checkStatus(CityResults.tableId);
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
    CityResults.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/cityResults/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CityResults.tableId);
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
        elem: '#' + CityResults.tableId,
        url: Feng.ctxPath + '/cityResults/listForSys',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CityResults.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CityResults.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    CityResults.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        CityResults.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + CityResults.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CityResults.openEditDlg(data);
        } else if (layEvent === 'delete') {
            CityResults.onDeleteItem(data);
        }
    });
});

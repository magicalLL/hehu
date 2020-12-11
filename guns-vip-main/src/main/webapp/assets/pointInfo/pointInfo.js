layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 变化点位管理管理
     */
    var PointInfo = {
        tableId: "pointInfoTable"
    };

    /**
     * 初始化表格的列
     */
    PointInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'objectid', sort: true, title: '序号'},
            {field: 'fid', hide: true, title: ''},
            {field: 'hdmc', sort: true, title: '河道名称'},
            {field: 'shape', sort: true, title: '类型'},
            {field: 'Id', hide: true, title: ''},
            {field: 'mj', sort: true, title: '面积'},
            {field: 'mj2', sort: true, title: '面积2'},
            {field: 'xh', hide: true, title: '图片序号'},
            {field: 'hzb', sort: true, title: '横坐标'},
            {field: 'zzb', sort: true, title: '纵坐标'},
            {field: 'sjdq', sort: true, title: '涉及地区'},
            {field: 'xmwz', sort: true, title: '项目位置'},
            {field: 'bhlx', sort: true, title: '变化类型'},
            {field: 'bhdgk', sort: true, title: '变化点概况'},
            {field: 'xmmc', sort: true, title: '项目名称'},
            {field: 'jszt', sort: true, title: '建设主体'},
            {field: 'jssj', sort: true, title: '建设时间'},
            {field: 'yxzt', sort: true, title: '运行状态'},
            {field: 'jggc', sort: true, title: '监管过程'},
            {field: 'bz', sort: true, title: '备注'},
            {field: 'shapeLeng', sort: true, title: '周长'},
            {field: 'shapeArea', sort: true, title: '面积'},
            {field: 'status', sort: true, title: '审核状态'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '修改时间'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'updateUser', sort: true, title: '修改人'},
            {field: 'version', sort: true, title: '版本'},
            {field: 'deleted', hide: true, title: '是否删除(逻辑删除)'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    PointInfo.search = function () {
        var queryData = {};

        queryData['hdmc'] = $('#hdmc').val();
        queryData['xmmc'] = $('#xmmc').val();

        table.reload(PointInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    PointInfo.openAddDlg = function () {
        func.open({
            title: '添加变化点位管理',
            content: Feng.ctxPath + '/pointInfo/add',
            tableId: PointInfo.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      PointInfo.openEditDlg = function (data) {
          func.open({
              title: '修改变化点位管理',
              content: Feng.ctxPath + '/pointInfo/edit?objectid=' + data.objectid,
              tableId: PointInfo.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    PointInfo.exportExcel = function () {
        var checkRows = table.checkStatus(PointInfo.tableId);
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
    PointInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/pointInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PointInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("objectid", data.objectid);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + PointInfo.tableId,
        url: Feng.ctxPath + '/pointInfo/sysList',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: PointInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PointInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    PointInfo.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        PointInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PointInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PointInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            PointInfo.onDeleteItem(data);
        }
    });
});

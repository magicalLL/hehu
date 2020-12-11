layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var ServiceMatedata = {
        tableId: "serviceMatedataTable"
    };

    /**
     * 初始化表格的列
     */
    ServiceMatedata.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true, title: ''},
            {field: 'resourceid', sort: true, title: ''},
            {field: 'name', sort: true, title: ''},
            {field: 'title', sort: true, title: '服务类型id'},
            {field: 'serviceTypeid', sort: true, title: ''},
            {field: 'typeVersion', sort: true, title: ''},
            {field: 'publishUrl', sort: true, title: '发布地址'},
            {field: 'rawUrl', sort: true, title: ''},
            {field: 'fileid', sort: true, title: ''},
            {field: 'west', sort: true, title: ''},
            {field: 'east', sort: true, title: ''},
            {field: 'north', sort: true, title: ''},
            {field: 'south', sort: true, title: ''},
            {field: 'resourceOwnerDept', sort: true, title: ''},
            {field: 'resourceOwnerDeptUrl', sort: true, title: ''},
            {field: 'resourceOwner', sort: true, title: ''},
            {field: 'resourceOwnerJob', sort: true, title: ''},
            {field: 'country', sort: true, title: '国家'},
            {field: 'city', sort: true, title: '城市'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'postcode', sort: true, title: '邮编'},
            {field: 'tele', sort: true, title: '电话'},
            {field: 'fax', sort: true, title: '传真'},
            {field: 'email', sort: true, title: '邮箱'},
            {field: 'accessRestriction', sort: true, title: ''},
            {field: 'useRestriction', sort: true, title: ''},
            {field: 'spatialReference', sort: true, title: ''},
            {field: 'spatialType', sort: true, title: ''},
            {field: 'spatialResolution', sort: true, title: ''},
            {field: 'subjectType', sort: true, title: ''},
            {field: 'otherDescription', sort: true, title: ''},
            {field: 'layermetadataid', sort: true, title: ''},
            {field: 'manifactureDate', sort: true, title: '生产日期'},
            {field: 'timemark', sort: true, title: '1带时空标签，2不带'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ServiceMatedata.search = function () {
        var queryData = {};


        table.reload(ServiceMatedata.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ServiceMatedata.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/serviceMatedata/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ServiceMatedata.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/serviceMatedata/edit?=' + data.id
    };

    /**
     * 导出excel按钮
     */
    ServiceMatedata.exportExcel = function () {
        var checkRows = table.checkStatus(ServiceMatedata.tableId);
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
    ServiceMatedata.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/serviceMatedata/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ServiceMatedata.tableId);
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
        elem: '#' + ServiceMatedata.tableId,
        url: Feng.ctxPath + '/serviceMatedata/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ServiceMatedata.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ServiceMatedata.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ServiceMatedata.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ServiceMatedata.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ServiceMatedata.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ServiceMatedata.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ServiceMatedata.onDeleteItem(data);
        }
    });
});

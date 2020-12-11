layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var Department = {
        tableId: "departmentTable"
    };

    /**
     * 初始化表格的列
     */
    Department.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true, title: ''},
            {field: 'pid', sort: true, title: '上级id'},
            {field: 'name', sort: true, title: ''},
            {field: 'address', sort: true, title: ''},
            {field: 'tele', sort: true, title: ''},
            {field: 'description', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Department.search = function () {
        var queryData = {};


        table.reload(Department.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    Department.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/department/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    Department.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/department/edit?=' + data.id
    };

    /**
     * 导出excel按钮
     */
    Department.exportExcel = function () {
        var checkRows = table.checkStatus(Department.tableId);
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
    Department.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/department/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Department.tableId);
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
        elem: '#' + Department.tableId,
        url: Feng.ctxPath + '/department/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Department.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Department.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Department.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Department.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Department.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Department.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            Department.onDeleteItem(data);
        }
    });
});

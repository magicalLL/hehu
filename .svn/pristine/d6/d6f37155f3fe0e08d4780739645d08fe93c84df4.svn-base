layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var CityResource = {
        tableId: "cityResourceTable"
    };

    /**
     * 初始化表格的列
     */
    CityResource.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'name', sort: true, title: '服务名称'},
            {
                field: 'resourcetype', align: "center", sort: true, title: '服务类型', templet: function (d) {
                    if (d.resourcetype === '1') {
                        return "在线服务";
                    } else {
                        return "离线服务";
                    }
                }
            },
            {
                field: 'runstate', align: "center", sort: true, title: '运行状态', templet: function (d) {
                    if (d.runstate === 0) {
                        return "启动";
                    } else {
                        return "停止";
                    }
                }
            },
            {
                field: 'publishuserid', align: "center", sort: true, title: '申请人'
            },
            {
                field: 'catalogDeptid', align: "center", sort: true, title: '申请单位', templet: function (d) {
                    if (d.catalogDeptid === '25') {
                        return "水利局";
                    } else {
                        return d.catalogDeptid;
                    }
                }
            },
            {field: 'publishdate', sort: true, title: '申请日期'},
            {
                field: 'state', align: "center", sort: true, title: '审核状态', templet: function (d) {
                    if (d.state === 1) {
                        return "<span style='color: #009688;'>审核通过</span>";
                    } else if (d.state === 2) {
                        return "<span style='color: #ff5722;'>审核未通过</span>";
                    }
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作'},
        ]];
    };

    /**
     * 点击查询按钮
     */
    CityResource.search = function () {
        var queryData = {};

        queryData['name'] = $('#name').val();
        console.log('图层服务名称: '+$('#name').val());
        table.reload(CityResource.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    CityResource.jumpAddPage = function () {
        // window.location.href = Feng.ctxPath + '/cityResource/add'
        func.open(
            {
                title: '新增',
                content: Feng.ctxPath+'/cityResource/add',
                tableId: CityResource.tableId
            }
        );
    };

    /**
     * 跳转到查看详情页面
     *
     * @param data 点击按钮时候的行数据
     */
    CityResource.jumpViewPage = function (data) {
        // window.location.href = Feng.ctxPath + '/cityResource/edit?=' + data.id;
        func.open({
            title: '查看',
            content: Feng.ctxPath + '/cityResource/view?id=' + data.id,
            tableId: CityResource.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    CityResource.exportExcel = function () {
        var checkRows = table.checkStatus(CityResource.tableId);
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
    CityResource.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/cityResource/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(CityResource.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    var userid=$('#publishUserId').val();
    var userName=$('#publishUserName').val();
    var requestUrl=Feng.ctxPath + '/cityResource/searchList?';
    console.log(userid+' '+userName);
    if(userName==='admin')
        requestUrl=requestUrl+"";
    else
        requestUrl=requestUrl+"&userid="+userid;

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CityResource.tableId,
        url: requestUrl,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CityResource.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CityResource.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

        CityResource.jumpAddPage();
    });

    // 导出excel
    $('#btnExp').click(function () {
        CityResource.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + CityResource.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'view') {
            CityResource.jumpViewPage(data);
        } else if (layEvent === 'delete') {
            CityResource.onDeleteItem(data);
        }
    });
});

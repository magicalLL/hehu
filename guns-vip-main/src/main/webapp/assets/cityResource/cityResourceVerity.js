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
     * 跳转到编辑页面
     *
     * @param data 点击按钮时候的行数据
     */
    CityResource.jumpEditPage = function (data) {
        // window.location.href = Feng.ctxPath + '/cityResource/edit?=' + data.id;
        func.open({
            title: '审核',
            content: Feng.ctxPath + '/cityResource/verityDetail?id=' + data.id,
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

    var userid=$('#publishUserId').val();
    var userName=$('#publishUserName').val();
    var requestUrl=Feng.ctxPath + '/cityResource/list?state=0';
    console.log(userid+' '+userName);
    if(userName ==="admin")
        requestUrl=requestUrl+"";
    else
        requestUrl=requestUrl+"&userid="+userid;

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CityResource.tableId,
        url: requestUrl,//获取状态为0的图层服务列表数据
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CityResource.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CityResource.search();
    });

    // 导出excel
    $('#btnExp').click(function () {
        CityResource.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + CityResource.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            CityResource.jumpEditPage(data);
        }
    });
});

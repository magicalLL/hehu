package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Bzfield;
import cn.stylefeng.guns.modular.hhsys.entity.Bzresource;
import cn.stylefeng.guns.modular.hhsys.model.result.BzresourceResult;
import cn.stylefeng.guns.modular.hhsys.service.BzfieldService;
import cn.stylefeng.guns.modular.hhsys.service.BzresourceService;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/rest/marker")
@Api(tags = "标注")
public class MarkerController {
    @Autowired
    private BzresourceService bzresourceService;

    @Autowired
    private BzfieldService bzfieldService;

//    @Autowired
//    private TableDataService tableDataService;

    /**
     * 查询用户创建的列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/tablelist", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation("查询标注列表")
    public ResponseData tableList(@RequestParam String userid) {
//        List tableList = this.bzresourceService.findPageByUserid(userid).getData();
//        List tableNameList = new ArrayList();
//        for(int i=0; i<tableList.size(); i++){
//            BzresourceResult br = (BzresourceResult) tableList.get(i);
//            tableNameList.add(br.getTablename());
//        }

        JSONObject marker = new JSONObject();
        marker.put("marker",this.bzresourceService.findPageByUserid(userid).getData());
        return new SuccessResponseData(marker);
    }

    /**
     * 根据表名查询表字段列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getFieldsByTableID", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation("根据tableid查询表字段信息")
    public ResponseData getFieldsByTableID(@RequestParam(value = "tableID") String tableID) {
        LayuiPageInfo fieldList = this.bzfieldService.findPageByTableid(tableID);

        JSONObject field = new JSONObject();
        field.put("fields",fieldList.getData());
        return new SuccessResponseData(field);
    }

    /**
     * 根据表名查询表字段列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getTableDataByTableName", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation("根据tablename查询表数据")
    public ResponseData getTableDataByTableName(@RequestParam(value = "tableName") String tableName) {
        List<Map<String,Object>> tableMsg = this.bzresourceService.getDatas(tableName);

        JSONObject tableData = new JSONObject();
        tableData.put("tableData",tableMsg);
        return new SuccessResponseData(tableData);
    }

    /**
     * 根据表名添加标注
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/addMarker", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("添加标注")
    public ResponseData addMarker(@RequestBody Map<String,Object> data) {
//        String tableName = (String) data.get("tableName");
//        data.remove("tableName");
        Map<String, Object> result = this.bzresourceService.addDatas(data);

        if(result.get("bool").equals(false)){
            return new ErrorResponseData((String) result.get("detail"));
        }else {
            return new SuccessResponseData(result);
        }
    }

    /**
     * 根据表名添加标注
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/replaceMarker", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("替换标注信息")
    public ResponseData replaceMarker(@RequestBody Map<String,Object> data) {
//        String tableName = (String) data.get("tableName");
//        data.remove("tableName");
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String, Object> deleteResult = this.bzresourceService.deleteData(data.get("tableName").toString(),data.get("id").toString());
        Map<String, Object> addResult = this.bzresourceService.addDatas(data);
        if(deleteResult.get("bool").equals("false")){
            result.put("delete",deleteResult.get("detail"));
        }
        if(addResult.get("bool").equals("false")){
            result.put("flag",addResult.get("bool"));
        }else {
            result.put("flag",addResult.get("bool"));
            result.put("data",addResult.get("data"));
        }
        return new SuccessResponseData(result);
    }

    /**
     * 根据表名和ID删除标注
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMarker", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("删除标注")
    public ResponseData deleteMarker(@RequestBody Map<String, Object> markerConfig) {

        Map<String,Object> result = this.bzresourceService.deleteData(markerConfig.get("tableName").toString(), markerConfig.get("ID").toString());

        return new SuccessResponseData(result);
    }

    /**
     * 删除标注表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTableByTableName", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation("删除标注表")
    public ResponseData deleteTableByTableName(@RequestParam(value = "tableName") String tableName) {

        Map<String,Object> result = new HashMap<>();
        try {
            this.bzresourceService.deleteMarkerTable(tableName);
            BzresourceResult bz = this.bzresourceService.findByTablename(tableName);
            String id = bz.getId();
            result = this.bzfieldService.deleteByTableid(id);
            result = this.bzresourceService.deleteById(id);
        }catch (Exception e){
            result.put("flag",false);
            result.put("msg","删除失败");
        }


        return new SuccessResponseData(result);
    }

    /**
     * 新增标注表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/addTableFields", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("新增标注表")
    //@Transactional
    public ResponseData createMarkerTable(@RequestBody Map<String,Object> tableFieldsParam) throws ParseException {
        Map<String,Object> result = new HashMap<String,Object>();
        /**
         * 创建表
         */
        Map<String,Object> params = new HashMap<String,Object>();
        if(tableFieldsParam.get("tablename") == null || tableFieldsParam.get("tablename") == ""){
            result.put("bool", false);
            result.put("detail", "表名为空");
        }
        params.put("tableName",tableFieldsParam.get("tablename"));
        List<Object> itemList = new ArrayList<>();
        List<Map<String,Object>> tablefields = new ArrayList<>();
        tablefields = (List<Map<String, Object>>) tableFieldsParam.get("fields");
        for(Map<String,Object> field: tablefields){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("name",field.get("name").toString());
            if(field.get("type").toString().equals("character") || field.get("type").toString().equals("varchar")){
                item.put("type","varchar(100)");
            }else{
                item.put("type",field.get("type").toString());
            }

            itemList.add(item);
        }
        if(itemList != null) {
            params.put("fields", itemList);
        }
        Map<String,Object> createResult = new HashMap<String,Object>();
        createResult = this.bzresourceService.createMarkerTable(params);

        /**
         * 插入表信息
         */
        if (createResult.get("bool").equals(true)) {
            Map<String, Object> tableData = new HashMap<String, Object>();
            tableData = tableFieldsParam;
            UUID uuid = UUID.randomUUID();
            tableData.put("id", uuid);

            Bzresource bzresource = new Bzresource();
            bzresource.setId(uuid.toString());
            bzresource.setTablename(tableData.get("tablename").toString());
            bzresource.setCreatetime(new SimpleDateFormat( "yyyy-MM-dd ").parse(tableData.get("createtime").toString()));
            bzresource.setUserid(tableData.get("userid").toString());
            bzresource.setTablealias(tableData.get("tablealias").toString());

            Map<String, Object> insertSResult = new HashMap<String, Object>();
            try {
                this.bzresourceService.addtBzresource(bzresource);
                insertSResult.put("msg","插入成功");
                insertSResult.put("flag",true);
            }catch (Exception e){
                insertSResult.put("msg","插入失败");
            }

            /**
             * 插入表字段
             */
            if (insertSResult.get("flag").equals(true)) {
                Map<String, Object> insertFResult = new HashMap<String, Object>();
                Bzfield bzfield = new Bzfield();
                List<Map<String, Object>> fields = (List<Map<String, Object>>) tableFieldsParam.get("fields");
                for (int i = 0; i < fields.size(); i++) {

                    bzfield.setId(UUID.randomUUID().toString());
                    bzfield.setName(fields.get(i).get("name").toString());
                    bzfield.setAlias(fields.get(i).get("alias").toString());
                    bzfield.setType(fields.get(i).get("type").toString());
                    bzfield.setTableid(uuid.toString());

                    try {
                        this.bzfieldService.addBzfield(bzfield);
                        insertFResult.put("msg","插入成功");
                        result.put("msg","创建成功");
                    }catch (Exception e){
                        insertFResult.put("msg","插入失败");
                        result.put("result",createResult.get("detail"));
                    }
                }
            }
        }

        return new SuccessResponseData(result);
    }

    @ResponseBody
    @RequestMapping(value = "/insertBzresource", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ApiOperation("插入表信息")
    public ResponseData insertBzresource(@RequestBody Map<String,Object> tableFieldsParam) throws ParseException {
        Map<String, Object> tableData = new HashMap<String, Object>();
        tableData = tableFieldsParam;
        UUID uuid = UUID.randomUUID();
        tableData.put("id", uuid);
        tableData.remove("fields");

        Bzresource bzresource = new Bzresource();
        bzresource.setId(uuid.toString());
        bzresource.setTablename(tableData.get("tablename").toString());
        bzresource.setCreatetime(new SimpleDateFormat( "yyyy-MM-dd ").parse(tableData.get("createtime").toString()));
        bzresource.setUserid(tableData.get("userid").toString());
        bzresource.setTablealias(tableData.get("tablealias").toString());

        Map<String, Object> insertSResult = new HashMap<String, Object>();
        try {
            this.bzresourceService.addtBzresource(bzresource);
            insertSResult.put("msg","插入成功");
        }catch (Exception e){
            insertSResult.put("msg","插入失败");
        }


        return new SuccessResponseData(insertSResult);
    }
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ApiOperation("插入表字段")
    public ResponseData insert(@RequestBody Map<String,Object> tableFieldsParam) {
        Map<String, Object> insertFResult = new HashMap<String, Object>();
        Bzfield bzfield = new Bzfield();
        List<Map<String, Object>> fields = (List<Map<String, Object>>) tableFieldsParam.get("fields");
        for (int i = 0; i < fields.size(); i++) {

            bzfield.setId(UUID.randomUUID().toString());
            bzfield.setName(fields.get(i).get("name").toString());
            bzfield.setAlias(fields.get(i).get("alias").toString());
            bzfield.setType(fields.get(i).get("type").toString());
            bzfield.setTableid("02");

            try {
                this.bzfieldService.addBzfield(bzfield);
                insertFResult.put("msg","插入成功");
            }catch (Exception e){
                insertFResult.put("msg","插入失败");
            }
        }
        return new SuccessResponseData(insertFResult);
    }

}

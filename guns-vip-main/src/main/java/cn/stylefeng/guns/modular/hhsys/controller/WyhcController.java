package cn.stylefeng.guns.modular.hhsys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Wyhc;
import cn.stylefeng.guns.modular.hhsys.model.params.WyhcParam;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
import cn.stylefeng.guns.modular.hhsys.model.result.WyhcResult;
import cn.stylefeng.guns.modular.hhsys.service.ServiceMatedataService;
import cn.stylefeng.guns.modular.hhsys.service.UrlsService;
import cn.stylefeng.guns.modular.hhsys.service.WyhcService;
import cn.stylefeng.guns.modular.hhsys.utils.ResultInfo;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-10-26 14:47:16
 */
@RestController
@RequestMapping("/wyhc")
@Api(tags = "图班")
public class WyhcController extends BaseController {

    private String PREFIX = "/wyhc";

    @Autowired
    private WyhcService wyhcService;

    @Autowired
    private UrlsService urlsService;

    @Autowired
    private ServiceMatedataService serviceMatedataService;



    /**
     *
     * 获取图斑信息
     *
     *
     * @param searchPatchMap
     * @return
     */
    @RequestMapping(value = "/getPatchReportInfoByTBID",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("获取图斑信息")
    public ResponseData getPatchReportInfoByTBID(@RequestBody Map<String,Object> searchPatchMap){

        Map<String, Object> result = new HashMap<>();
        try {

            String tbid=searchPatchMap.get("TBID").toString();
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("tbid",tbid);
            List<Wyhc> patchReportList = wyhcService.getPatchReportInfoByTBID(requestParamMap);
            if(patchReportList.size() != 0){

                result.put("total",patchReportList.size());
                result.put("rows",patchReportList);
            }
            else{
                result.put("total",patchReportList.size());
                result.put("rows",patchReportList);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            result.put("total","0");
            result.put("rows",new ArrayList<>());
            return new ErrorResponseData(e.getMessage());
        }
        System.out.println("》》》》》》》》》》》》》》》》》"+result);
        return new SuccessResponseData(result);

    }

    /**
     *
     * 获取图斑信息列表（分页）
     *
     * map{page, limit, TBID, STATUS, projectName, szqy}
     *
     * @param searchPatchMap
     * @return
     */
    @RequestMapping(value = "/getPatchReportList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("获取图斑信息列表")
    public ResponseData getPatchReportList(@RequestBody Map<String,Object> searchPatchMap){

        Map<String, Object> result = new HashMap<>();
//
//        try {
//            List<Map<String, Object>> patchReportList = this.wyhcService.findList(new WyhcParam());
//            result.put("patchReportList",patchReportList);
//        }catch (Exception e){
//            result.put("msg","获取失败");
//            result.put("flag",false);
//        }

        try {

            Integer pageIndex=Integer.parseInt(searchPatchMap.get("page").toString());
            Integer pageSize=Integer.parseInt(searchPatchMap.get("limit").toString());
            String tbid=searchPatchMap.get("TBID").toString();
            String status=searchPatchMap.get("STATUS").toString();
            String xmmc=searchPatchMap.get("projectName").toString();
            String szqy = searchPatchMap.get("szqy").toString();
//            if(status.indexOf(',')>-1)
//            {
            String[] statusArray=status.split(",");
//
            /*所需参数*/
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("pageIndex",(pageIndex-1)*pageSize);
            requestParamMap.put("pageSize",pageSize);
            requestParamMap.put("tbid",tbid);
            requestParamMap.put("xmmc", xmmc);
            if(!szqy.equals("水利局部门"))
                requestParamMap.put("szqy", szqy);
//            requestParamMap.put("status",Integer.parseInt(status));
            requestParamMap.put("statusList",statusArray);
            List<WyhcResult> resources = this.wyhcService.getPatchReportList(requestParamMap);
            for(WyhcResult wyhcResult: resources){
                String imgUrl = wyhcResult.getUrl();
                if(!imgUrl.equals(null)){
                    if(imgUrl.contains(",")) {
                        imgUrl = this.urlsService.getById("6").getUrl()+"/accessFile/" + imgUrl.substring(0, imgUrl.lastIndexOf(","));
                    }else {
                        imgUrl = this.urlsService.getById("6").getUrl()+"/accessFile/" +imgUrl;
                    }
                }
                wyhcResult.setImgUrl(imgUrl);
            }
            List<WyhcResult> patchReportListCount = this.wyhcService.getPatchReportListCount(requestParamMap);
            if(resources.size() != 0){

                result.put("total",patchReportListCount.size());
                result.put("rows",resources);
                return new SuccessResponseData(result);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            result.put("total","");
            result.put("rows",new ArrayList<>());
            return new SuccessResponseData(result);
        }
        return new SuccessResponseData(result);

    }


    /**
     *
     * 获取图斑信息列表(不分页)
     *
     * map {TBID, STATUS, projectName, szqy}
     *
     * @param searchPatchMap
     * @return
     */
    @RequestMapping(value = "/exportTBInfoList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("获取图斑信息列表")
    public ResponseData exportTBInfoList(@RequestBody Map<String,Object> searchPatchMap){

        Map<String, Object> result = new HashMap<>();
//
//        try {
//            List<Map<String, Object>> patchReportList = this.wyhcService.findList(new WyhcParam());
//            result.put("patchReportList",patchReportList);
//        }catch (Exception e){
//            result.put("msg","获取失败");
//            result.put("flag",false);
//        }

        try {
            String tbid=searchPatchMap.get("TBID").toString();
            String status=searchPatchMap.get("STATUS").toString();
            String xmmc=searchPatchMap.get("projectName").toString();
            String szqy = searchPatchMap.get("szqy").toString();
//            if(status.indexOf(',')>-1)
//            {
            String[] statusArray=status.split(",");
//
            /*所需参数*/
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("tbid",tbid);
            requestParamMap.put("xmmc", xmmc);
            if(!szqy.equals("水利局部门"))
                requestParamMap.put("szqy", szqy);
//            requestParamMap.put("status",Integer.parseInt(status));
            requestParamMap.put("statusList",statusArray);
            List<WyhcResult> resources = this.wyhcService.getPatchReportListBak(requestParamMap);
            for(WyhcResult wyhcResult: resources){
                String imgUrl = wyhcResult.getUrl();
                if(!imgUrl.equals(null)){
                    if(imgUrl.contains(",")) {
                        imgUrl = this.urlsService.getById("6").getUrl()+"/accessFile/" + imgUrl.substring(0, imgUrl.lastIndexOf(","));
                    }else {
                        imgUrl = this.urlsService.getById("6").getUrl()+"/accessFile/" +imgUrl;
                    }
                }
                wyhcResult.setImgUrl(imgUrl);
            }
            List<WyhcResult> patchReportListCount = this.wyhcService.getPatchReportListCount(requestParamMap);
            if(resources.size() != 0){

                result.put("total",patchReportListCount.size());
                result.put("rows",resources);
                return new SuccessResponseData(result);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            result.put("total","");
            result.put("rows",new ArrayList<>());
            return new SuccessResponseData(result);
        }
        return new SuccessResponseData(result);

    }

    /**
     *
     *
     * 新增图斑上报
     * @param patchReportMap
     * @return
     */
    @RequestMapping(value = "/addPatchReport", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("新增图斑上报")
    public ResponseData addPatchReport(@RequestBody Map<String,Object> patchReportMap) throws ParseException {

         ResultInfo resultInfo = new ResultInfo();

         String tbid = patchReportMap.get("TBID").toString();
        Map<String, Object> requestParamMap=new HashMap<String, Object>();
        requestParamMap.put("tbid",tbid);
        String szqy = patchReportMap.get("szqy").toString().trim();
        ServiceMatedataResult byName = this.serviceMatedataService.getByName(szqy + "变化点位");
        patchReportMap.put("resource_id",byName.getResourceid());

//         this.get
         if(this.wyhcService.getPatchReportInfoByTBID(requestParamMap).size() == 0) {//判断表中是否已存在此图斑

             patchReportMap.put("ID", UUID.randomUUID().toString().replace("-", ""));
             patchReportMap.put("SBSJ", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(patchReportMap.get("SBSJ").toString()));
             patchReportMap.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(patchReportMap.get("createtime").toString()));
             int insertRow = this.wyhcService.addPatchReport(patchReportMap);
             if (insertRow > 0) {
                 resultInfo.setResult(true);
                 resultInfo.setTotalCount(insertRow);
                 resultInfo.setMsg("图斑上报成功!!!");
             } else {
                 resultInfo.setResult(false);
                 resultInfo.setTotalCount(insertRow);
                 resultInfo.setMsg("图斑上报失败!!!");
             }

             //return resultInfo;
             return new SuccessResponseData(resultInfo);
         }else{
             Wyhc wyhcResult = this.wyhcService.getPatchReportInfoByTBID(requestParamMap).get(0);
             if(!wyhcResult.getStatus().equals("1")) {
                 patchReportMap.put("sbsj", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(patchReportMap.get("SBSJ").toString()));
                 patchReportMap.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(patchReportMap.get("createtime").toString()));

                 wyhcResult.setUrl(patchReportMap.get("URL").toString());
                 wyhcResult.setDescription(patchReportMap.get("DESCRIPTION").toString());
                 wyhcResult.setSbr(patchReportMap.get("SBR").toString());
                 wyhcResult.setSbsj(patchReportMap.get("SBSJ").toString());
                 wyhcResult.setSbdw(patchReportMap.get("sbdw").toString());
                 wyhcResult.setXmmc(patchReportMap.get("xmmc").toString());
                 wyhcResult.setHhmc(patchReportMap.get("hhmc").toString());
                 wyhcResult.setSzqy(patchReportMap.get("szqy").toString());
                 wyhcResult.setJggc(patchReportMap.get("jggc").toString());
                 wyhcResult.setBz(patchReportMap.get("bz").toString());
                 wyhcResult.setCreateBody(patchReportMap.get("createbody").toString());
                 wyhcResult.setRunState(patchReportMap.get("runstate").toString());
                 wyhcResult.setStatus("0");
                 wyhcResult.setResourceId(byName.getResourceid());
                 WyhcParam wyhcParam = new WyhcParam();
                 BeanUtil.copyProperties(wyhcResult, wyhcParam);

                 this.wyhcService.update(wyhcParam);
                 return new SuccessResponseData("重新上报成功");
             }
             return new SuccessResponseData("图斑已审核通过");
         }

    }

    /**
     *
     *
     * 审核图斑
     * @param patchReportMap(STATUS,SHR,SHYY,SHSJ)
     * @return
     */
    @RequestMapping(value = "/updatePatchReport", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("审核图斑信息")
    public ResponseData updatePatchReport(@RequestBody Map<String,Object> patchReportMap) throws ParseException {

        ResultInfo resultInfo = new ResultInfo();

        patchReportMap.put("SHSJ",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(patchReportMap.get("SHSJ").toString()));
        int updateRow = this.wyhcService.updatePatchReport(patchReportMap);
        if(updateRow>0)
        {
            resultInfo.setResult(true);
            resultInfo.setTotalCount(updateRow);
            resultInfo.setMsg("图斑审核成功!!!");
        }
        else
        {
            resultInfo.setResult(false);
            resultInfo.setTotalCount(updateRow);
            resultInfo.setMsg("图斑审核失败!!!");
        }

        return new SuccessResponseData(resultInfo);

    }


    @ResponseBody
    @ApiOperation("审核图斑信息")
    public ResponseData updateTB(@RequestBody WyhcParam patchReportMap) throws ParseException {

        ResultInfo resultInfo = new ResultInfo();


//        patchReportMap.setShyy(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(patchReportMap.getShyy("SHSJ").toString()));
        try {
            this.wyhcService.update(patchReportMap);
            resultInfo.setResult(true);
            resultInfo.setMsg("图斑审核成功!!!");
        }catch (Exception e){
            resultInfo.setResult(false);
            resultInfo.setMsg("图斑审核失败!!!");
        }

        return new SuccessResponseData(resultInfo);
    }

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wyhc.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wyhc_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wyhc_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WyhcParam wyhcParam) {
        this.wyhcService.add(wyhcParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WyhcParam wyhcParam) {
        this.wyhcService.update(wyhcParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WyhcParam wyhcParam) {
        this.wyhcService.delete(wyhcParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WyhcParam wyhcParam) {
        Wyhc detail = this.wyhcService.getById(wyhcParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseData list(WyhcParam wyhcParam) {
        LayuiPageInfo layuiPageInfo = this.wyhcService.findPageBySpec(wyhcParam);
        return new SuccessResponseData(layuiPageInfo.getData());
    }


    @ApiOperation("获取分组审核信息")
    @GetMapping("/queryGroupByStatus")
    public ResponseData queryGroupByStatus( String  resourceId){
        Map<String,String> result= this.wyhcService.queryGroupByStatus(resourceId);
        Map<String,Object> data=new HashMap<String, Object>();
        data.put("status",new String[]{"zero","one","all"});
        data.put("group",result);
        return new SuccessResponseData(data);


    }


}



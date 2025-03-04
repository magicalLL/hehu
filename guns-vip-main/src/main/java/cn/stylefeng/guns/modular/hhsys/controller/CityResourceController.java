package cn.stylefeng.guns.modular.hhsys.controller;

import cn.hutool.core.convert.Convert;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityResource;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceInfo;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceMatedata;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResourceParam;
import cn.stylefeng.guns.modular.hhsys.model.params.ServiceMatedataParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResourceResult;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
import cn.stylefeng.guns.modular.hhsys.service.CityResourceService;
import cn.stylefeng.guns.modular.hhsys.service.ServiceMatedataService;
import cn.stylefeng.guns.modular.hhsys.service.TokenService;
import cn.stylefeng.guns.modular.hhsys.utils.Convertor;
import cn.stylefeng.guns.modular.hhsys.utils.ServiceUtil;
import cn.stylefeng.guns.sys.modular.rest.entity.RestUser;
import cn.stylefeng.guns.sys.modular.rest.service.RestUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-11-03 09:35:52
 */
@Controller
@RequestMapping("/cityResource")
public class CityResourceController extends BaseController {

    private String PREFIX = "/cityResource";

    @Autowired
    private CityResourceService cityResourceService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ServiceMatedataService serviceMatedataService;

    @Autowired
    private RestUserService restUserService;

    /**
     * 跳转到主页面-申请清单
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/cityResource.html";
    }

    /**
     * 跳转到主页面-资源管理
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("manager")
    public String indexManager() {
        return PREFIX + "/cityResourceManager.html";
    }

    /**
     * 跳转到主页面-审核清单
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("verity")
    public String indexVerity() {
        return PREFIX + "/cityResourceVerity.html";
    }

    /**
     * 跳转到主页面-审核清单
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/view")
    public String view() {
        return PREFIX + "/cityResourceManagerDetail.html";
    }

    /**
     * 跳转到主页面-审核详细清单
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/verityDetail")
    public String verityDetail() {
        return PREFIX + "/cityResourceVerityDetail.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/cityResource_add.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping(value = "/getLayersSize", method = RequestMethod.GET,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseData getLayersSize(String url){
        int size = ServiceUtil.getMAPSERVERLayersSize(url);
        return new SuccessResponseData(size);
    }


    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/cityResource_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping(value = "/addItem", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseData addItem(@RequestParam Map<String,Object> params) throws ParseException {

        String userId = params.get("publishUserid").toString();
        RestUser restUser = this.restUserService.getById(Long.parseLong(userId));
        String resourceId = UUID.randomUUID().toString();
        resourceId = resourceId.replaceAll("-","");
        String resourceType = params.get("resourceType").toString();

        CityResourceParam cityResourceParam = new CityResourceParam();
        cityResourceParam.setState(0);
        cityResourceParam.setId(resourceId);
        cityResourceParam.setPublishdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.get("publishDate").toString()));
        cityResourceParam.setAliasname(params.get("resourceAliasName").toString());
        cityResourceParam.setResourcetype("1");
        cityResourceParam.setServiceTypeId(resourceType);
        cityResourceParam.setCatalogDeptid(restUser.getDeptId().toString());
        cityResourceParam.setCatalogThemeid("0001");
        cityResourceParam.setAbstractValue(params.get("resourceAbstract").toString());
        cityResourceParam.setKeyword(params.get("resourceKeyword").toString());
        cityResourceParam.setPublishuserid(userId);
        cityResourceParam.setName(params.get("resourceName").toString());
        cityResourceParam.setRunstate(Integer.valueOf( params.get("resourceRunstate").toString()));
        this.cityResourceService.add(cityResourceParam);

        String rawUrl = params.get("resourceAddress").toString();
        ServiceInfo serviceInfo = new ServiceInfo();
        switch (resourceType){
            case "0001":
                serviceInfo = ServiceUtil.getWMSBaseInfo(rawUrl);
                break;
            case "0002":
                serviceInfo = ServiceUtil.getWMTSBaseInfo(rawUrl);
                break;
            case "0004":
                serviceInfo = ServiceUtil.getWFSBaseInfo(rawUrl);
                break;
            case "0005":
                serviceInfo = ServiceUtil.getWCSBaseInfo(rawUrl);
                break;
            case "0007":
            case "0008":
                serviceInfo = ServiceUtil.getMAPSERVERBaseInfo(rawUrl);
                break;
            case "0009":
            case "0026":
            case "0027":
        }

//        ServiceInfo serviceInfo = ServiceUtil.getMAPSERVERBaseInfo(params.get("resourceAddress").toString());
        ServiceMatedata serviceMatedata = serviceInfo.getServiceMetaData();
        Convertor convertor = new Convertor(3, 120, 0, 500000, 0);
        double[] minjw = convertor.GKgetJW(Convert.toDouble(serviceMatedata.getWest()),Convert.toDouble(serviceMatedata.getSouth()));
        double[] maxjw = convertor.GKgetJW(Convert.toDouble(serviceMatedata.getEast()),Convert.toDouble(serviceMatedata.getNorth()));
        serviceMatedata.setWest(minjw[0]+"");
        serviceMatedata.setEast(minjw[1]+"");
        serviceMatedata.setNorth(maxjw[0]+"");
        serviceMatedata.setSouth(maxjw[1]+"");
        serviceMatedata.setResourceid(resourceId);
        serviceMatedata.setTele(params.get("phoneNumber").toString());
        serviceMatedata.setSubjectType(params.get("fieldSubject").toString());
        serviceMatedata.setOtherDescription(params.get("layerFiled").toString());
        serviceMatedata.setRawUrl(rawUrl);
        serviceMatedata.setName(params.get("resourceName").toString());
        serviceMatedata.setServiceTypeId(resourceType);
        serviceMatedata.setResourceOwner(params.get("publishUserName").toString());
        serviceMatedata.setResourceOwnerDept(params.get("deptName").toString());

        this.serviceMatedataService.add(serviceMatedata);

        this.tokenService.add(resourceId,userId,restUser.getDeptId().toString());
        if(!userId.equals("1"))
        this.tokenService.add(resourceId,"1","25");
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping(value = "/editItem", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseData editItem(@RequestParam Map<String, Object> params) throws ParseException {
        String resourceId = params.get("id").toString();

        //获取mateData数据
        ServiceMatedataResult serviceMatedataResult = this.serviceMatedataService.findByResourceId(resourceId);
        //插入resourceMateData新数据
        serviceMatedataResult.setServiceTypeId(params.get("resourceType").toString());
        serviceMatedataResult.setName(params.get("resourceName").toString());
        serviceMatedataResult.setRawUrl(params.get("resourceAddress").toString());
        serviceMatedataResult.setResourceOwner(params.get("publishUserName").toString());
        serviceMatedataResult.setTele(params.get("phoneNumber").toString());
        serviceMatedataResult.setSubjectType(params.get("fieldSubject").toString());
        serviceMatedataResult.setOtherDescription(params.get("layerFiled").toString());
        //获取resource数据
        CityResource cityResource = this.cityResourceService.getById(resourceId);
        //插入resource数据
        cityResource.setAliasname(params.get("resourceAliasName").toString());
        cityResource.setAbstractValue(params.get("resourceAbstract").toString());
        cityResource.setKeyword(params.get("resourceKeyword").toString());
        cityResource.setName(params.get("resourceName").toString());
        cityResource.setPublishuserid(params.get("publishUserid").toString());
        cityResource.setServiceTypeId("resourceType");
        cityResource.setRunstate(Integer.valueOf(params.get("resourceRunstate").toString()));
        cityResource.setPublishdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.get("publishDate").toString()));
        cityResource.setState(Integer.parseInt(params.get("verifyState").toString()));

        ServiceMatedataParam serviceMatedataParam = new ServiceMatedataParam();
        //转换插入对象类型
        ToolUtil.copyProperties(serviceMatedataResult, serviceMatedataParam);
        this.serviceMatedataService.update(serviceMatedataParam);

        CityResourceParam cityResourceParam = new CityResourceParam();
        //转换插入对象类型
        ToolUtil.copyProperties(cityResource,cityResourceParam);
        this.cityResourceService.update(cityResourceParam);

        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping(value = "/verityItem", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseData verityItem(@RequestParam Map<String, Object> params) {
        CityResourceResult cityResourceResult = this.cityResourceService.findByResourceId(params.get("id").toString());
        cityResourceResult.setState(Integer.parseInt(params.get("verifyState").toString()));

        CityResourceParam cityResourceParam = new CityResourceParam();
        ToolUtil.copyProperties(cityResourceResult,cityResourceParam);

        this.cityResourceService.update(cityResourceParam);
        return ResponseData.success();
    }


    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CityResourceParam cityResourceParam) {
        String resourceId = cityResourceParam.getId();
        this.serviceMatedataService.deleteByResourceId(resourceId);
        this.tokenService.deleteByResourceId(resourceId);
        this.cityResourceService.delete(cityResourceParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseData detail(@RequestParam String id) {
        CityResource crdetail = this.cityResourceService.getById(id);
        ServiceMatedataResult smdetail = this.serviceMatedataService.findByResourceId(id);
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("id",id);
        result.put("resourceAddress",smdetail.getRawUrl());
        result.put("resourceType",smdetail.getServiceTypeId());
        result.put("resourceName",crdetail.getName());
        result.put("resourceAliasName",crdetail.getAliasname());
        result.put("resourceAbstract",crdetail.getAbstractValue());
        result.put("resourceKeyword",crdetail.getKeyword());
        result.put("resourceRunstate",crdetail.getRunstate());
        result.put("publishUserid",crdetail.getPublishuserid());
        result.put("publishUserName",smdetail.getResourceOwner());
        result.put("publishDate",crdetail.getPublishdate());
        result.put("phoneNumber",smdetail.getTele());
        result.put("verifyState",crdetail.getState());
        result.put("fieldSubject",smdetail.getSubjectType());
        result.put("layerFiled", smdetail.getOtherDescription());

        return ResponseData.success(result);
    }

    /**
     * 查询已审核列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    @ResponseBody
    @RequestMapping(value = "/searchList", method =  RequestMethod.GET)
    public LayuiPageInfo searchList(@RequestParam(value = "name",defaultValue = "") String name, @RequestParam(value = "userid",defaultValue = "") String userid) {
        CityResourceParam cityResourceParam = new CityResourceParam();
        cityResourceParam.setName(name);
        cityResourceParam.setPublishuserid(userid);
        RestUser restUser = new RestUser();
        if(!userid.equals("") && userid != null){
            restUser = this.restUserService.getById(Long.parseLong(userid));
            cityResourceParam.setCatalogDeptid(restUser.getDeptId().toString());
        }
        List<CityResourceResult> li = this.cityResourceService.searchList(cityResourceParam);

        for(int i=0; i<li.size(); i++){
//            List<CityResourceResult> li = layuiPageInfo.getData();
            CityResourceResult cityResourceResult = li.get(i);
            String userName;
            if(cityResourceResult.getPublishuserid().equals("1320917958325932034")){
                userName = "水利局部门";
            }else {
                Long userId = Long.parseLong(cityResourceResult.getPublishuserid());
                userName = this.restUserService.getUserInfo(userId).get("name").toString();
            }
            cityResourceResult.setPublishuserid(userName);
        }
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        layuiPageInfo.setData(li);
        return layuiPageInfo;
    }

    /**
     * 渲染列表
     *
     * @author lls
     * @Date 2020-11-03
     */

    @ResponseBody
    @RequestMapping(value = "/list", method =  RequestMethod.GET)
    public LayuiPageInfo list(@RequestParam(value = "state") String state,@RequestParam(value = "name",defaultValue = "") String name,@RequestParam(value = "userid",defaultValue = "")String userid) {
        CityResourceParam cityResourceParam = new CityResourceParam();
        cityResourceParam.setState(Integer.valueOf(state));
        cityResourceParam.setName(name);
        cityResourceParam.setPublishuserid(userid);
        RestUser restUser = new RestUser();
        if(!userid.equals("") && userid != null){
            restUser = this.restUserService.getById(Long.parseLong(userid));
            cityResourceParam.setCatalogDeptid(restUser.getDeptId().toString());
        }
        List<CityResourceResult> li = this.cityResourceService.findListBySpec(cityResourceParam);
//        List<CityResourceResult> li = new ArrayList<CityResourceResult>();
//        li = layuiPageInfo.getData();
//        System.out.println("layuiPageInfo.getCount()"+layuiPageInfo.getCount());
            for(int i=0; i<li.size(); i++){
            CityResourceResult cityResourceResult = li.get(i);
            Long userId = Long.parseLong(cityResourceResult.getPublishuserid());
            String userName = this.restUserService.getUserInfo(userId).get("name").toString();
            cityResourceResult.setPublishuserid(userName);
        }
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        layuiPageInfo.setData(li);
        return layuiPageInfo;
    }

}



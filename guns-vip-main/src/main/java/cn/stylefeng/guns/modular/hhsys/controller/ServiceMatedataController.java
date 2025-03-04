package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceMatedata;
import cn.stylefeng.guns.modular.hhsys.model.params.ServiceMatedataParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResourceResult;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
import cn.stylefeng.guns.modular.hhsys.model.result.TokenResult;
import cn.stylefeng.guns.modular.hhsys.service.CityResourceService;
import cn.stylefeng.guns.modular.hhsys.service.ServiceMatedataService;
import cn.stylefeng.guns.modular.hhsys.service.TokenService;
import cn.stylefeng.guns.sys.modular.rest.entity.RestDept;
import cn.stylefeng.guns.sys.modular.rest.entity.RestUser;
import cn.stylefeng.guns.sys.modular.rest.service.RestDeptService;
import cn.stylefeng.guns.sys.modular.rest.service.RestUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-10-27 11:29:32
 */
@Controller
@RestController
@RequestMapping("/rest")
@Api(tags = "部门服务树")
public class ServiceMatedataController extends BaseController {

    private String PREFIX = "/serviceMatedata";

    @Autowired
    private ServiceMatedataService serviceMatedataService;

    @Autowired
    private CityResourceService cityResourceService;

    @Autowired
    private RestUserService restUserService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestDeptService restDeptService;


    @ResponseBody
    @RequestMapping(value = "/getDeptTree",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation("获取部门树")
        public ResponseData getDeptTree(@RequestBody Map<String,Object> user){
        Map<String, Object> tree = new LinkedHashMap<String, Object>();
        List childrenResult = new ArrayList();
        String userId = user.get("userId").toString();
//        try {
//            Long userid = DecimalUtil.getLong(userId);
//            Long l = Long.parseLong(userId);
//            System.out.println(userid + ":::" + l);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //所有用户可见的服务
        List<TokenResult> publicResults = this.tokenService.findListByUserid(Long.parseLong("1320917958325932034"));

        List publicServices = new ArrayList();
        for (TokenResult tokenResult : publicResults) {
            CityResourceResult cityResourceResult = this.cityResourceService.findByResourceId(tokenResult.getResourceid());
            if(cityResourceResult.getState().equals(1)) {//判断服务是否为已审核通过
                ServiceMatedataResult servirce = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());
                if(!userId.equals("1"))
                    if(servirce.getName().equals("市管理范围线") || servirce.getName().equals("全部变化点位"))
                        continue;
                Map<String, Object> serResult = new LinkedHashMap<String, Object>();//子节点服务
                serResult.put("id", servirce.getResourceid());
                serResult.put("text",servirce.getName());
                serResult.put("value",servirce.getName());
                serResult.put("showcheck",true);
                serResult.put("complete",true);
                serResult.put("isexpand",true);
                serResult.put("checkstate",0);
                serResult.put("hasChildren",false);
                serResult.put("pid", "1");
                childrenResult.add(serResult);
            }
        }



        //需判断用户类型（总部或子部门）
        if(userId.equals("1")){
            //通过createUser获取子用户
//            ArrayList<RestUser> childrenT = restUserService.getUsersByCreateUser(Long.parseLong(userId));
            ArrayList<RestDept> childrenT = restDeptService.selectDeptsByCreateUser(Long.parseLong(userId));
            for(int i=0; i<childrenT.size(); i++) {
                RestDept childT = childrenT.get(i);
                /**
                 * 在token表中根据userid查找所拥有resourceid
                 * 遍历resourceid，在serviece_matedata表中根据根据resourceid查找单条信息
                 */
//                List<TokenResult> tokenResults = this.tokenService.findListByUserid(childT.getDeptId());
                List<TokenResult> tokenResults = this.tokenService.findListByDeptid(childT.getDeptId().toString());
                //服务数据结果集
                List servicesResult = new ArrayList();
                for (TokenResult tokenResult : tokenResults) {
                    CityResourceResult cityResourceResult = this.cityResourceService.findByResourceId(tokenResult.getResourceid());
                    if(cityResourceResult.getState().equals(1)) {//判断服务是否为已审核通过
                        ServiceMatedataResult servirce = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());
                        Map<String, Object> serResult = getServiceResult(servirce, childT);
                        servicesResult.add(serResult);
                    }
                }

                //部门子节点，服务节点加入到子节点
                Map<String, Object> childResult = getDeptChildResult(childT,servicesResult);
                childrenResult.add(childResult);
            }
        }else{
//            List<TokenResult> tokenResults = this.tokenService.findListByUserid(Long.parseLong(userId));
//            RestUser childT = this.restUserService.getById(userId);
            RestUser childT = this.restUserService.getById(userId);
            RestDept restDept = this.restDeptService.getById(childT.getDeptId());
            List<TokenResult> tokenResults = this.tokenService.findListByDeptid(childT.getDeptId().toString());

            //服务数据结果集
            List servicesResult = new ArrayList();
            for (TokenResult tokenResult : tokenResults) {
                ServiceMatedataResult servirceT = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());

                Map<String, Object> serResult = getServiceResult(servirceT,restDept);
                servicesResult.add(serResult);
            }
            //部门子节点，服务节点加入到子节点
            Map<String, Object> childResult = getDeptChildResult(restDept,servicesResult);
            childrenResult.add(childResult);
        }



        //树根节点,部门子节点加入到根节点
        tree.put("id", "0001");
        tree.put("text", "市政府");
        tree.put("value", "市政府");
        tree.put("showcheck",false);
        tree.put("complete",true);
        tree.put("isexpand",true);
        tree.put("checkstate",0);
        tree.put("hasChildren",true);
        tree.put("ChildNodes",childrenResult);

        List treeList = new ArrayList();
        treeList.add(tree);

        if(tree == null){
            return new ErrorResponseData("未获取到tree");
        }else {
            JSONObject tok = new JSONObject();
            tok.put("treeDataProvider", treeList);
            return new SuccessResponseData(tok);
        }
    }

    /**
     * 获取服务节点信息
     * @param user 用户信息
     * @param servirce,服务信息
     * @return
     */
    public Map<String, Object> getServiceResult(ServiceMatedataResult servirce, RestDept dept){
        Map<String, Object> serResult = new LinkedHashMap<String, Object>();//子节点服务
        serResult.put("id", servirce.getResourceid());
        serResult.put("text",servirce.getName());
        serResult.put("value",servirce.getName());
        serResult.put("showcheck",true);
        serResult.put("complete",true);
        serResult.put("isexpand",true);
        serResult.put("checkstate",0);
        serResult.put("hasChildren",false);
        serResult.put("pid", dept.getDeptId());
        return serResult;
    }

    /**
     * 获取部门子节点信息
     * @param user 用户信息
     * @param servicesResult,服务信息列表
     * @return
     */
    public Map<String, Object> getDeptChildResult(RestDept dept, List servicesResult){
        Map<String, Object> childResult = new LinkedHashMap<String, Object>();
        childResult.put("id", dept.getDeptId());
        childResult.put("text",dept.getSimpleName());
        childResult.put("value",dept.getSimpleName());
        childResult.put("showcheck",false);
        childResult.put("complete",true);
        childResult.put("isexpand",true);
        childResult.put("checkstate",0);
        childResult.put("hasChildren",true);
        childResult.put("pid",restUserService.getByAccount("admin").getUserId());
        childResult.put("ChildNodes",servicesResult);
        return childResult;
    }


    @ResponseBody
    @RequestMapping(value = "/getDeptTreeOn",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation("获取部门树")
    public ResponseData getDeptTreeOn(@RequestBody Map<String,Object> user){
        Map<String, Object> tree = new LinkedHashMap<String, Object>();
        List childrenResult = new ArrayList();
        String userId = user.get("userId").toString();
//        try {
//            Long userid = DecimalUtil.getLong(userId);
//            Long l = Long.parseLong(userId);
//            System.out.println(userid + ":::" + l);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //所有用户可见的服务
        List<TokenResult> publicResults = this.tokenService.findListByUserid(Long.parseLong("1320917958325932034"));


        for (TokenResult tokenResult : publicResults) {
            CityResourceResult cityResourceResult = this.cityResourceService.findByResourceId(tokenResult.getResourceid());
            if(cityResourceResult.getState().equals(1)) {//判断服务是否为已审核通过
                ServiceMatedataResult servirce = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());
                if(!userId.equals("1"))
                    if(servirce.getName().equals("市管理范围线") || servirce.getName().equals("全部变化点位"))
                        continue;
                Map<String, Object> serResult = new LinkedHashMap<String, Object>();//子节点服务
                serResult.put("id", servirce.getResourceid());
                serResult.put("text",servirce.getName());
                serResult.put("value",servirce.getName());
                serResult.put("showcheck",true);
                serResult.put("complete",true);
                serResult.put("isexpand",true);
                serResult.put("checkstate",1);
                serResult.put("hasChildren",false);
                serResult.put("pid", "1");
                childrenResult.add(serResult);
            }
        }



        //需判断用户类型（总部或子部门）
        if(userId.equals("1")){
            //通过createUser获取子用户
//            ArrayList<RestUser> childrenT = restUserService.getUsersByCreateUser(Long.parseLong(userId));
            ArrayList<RestDept> childrenT = restDeptService.selectDeptsByCreateUser(Long.parseLong(userId));
            for(int i=0; i<childrenT.size(); i++) {
                RestDept childT = childrenT.get(i);
                /**
                 * 在token表中根据userid查找所拥有resourceid
                 * 遍历resourceid，在serviece_matedata表中根据根据resourceid查找单条信息
                 */
//                List<TokenResult> tokenResults = this.tokenService.findListByUserid(childT.getDeptId());
                List<TokenResult> tokenResults = this.tokenService.findListByDeptid(childT.getDeptId().toString());
                //服务数据结果集
                List servicesResult = new ArrayList();
                for (TokenResult tokenResult : tokenResults) {
                    CityResourceResult cityResourceResult = this.cityResourceService.findByResourceId(tokenResult.getResourceid());
                    if(cityResourceResult.getState().equals(1)) {//判断服务是否为已审核通过
                        ServiceMatedataResult servirce = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());
                        Map<String, Object> serResult = getServiceResultOn(servirce, childT);
                        servicesResult.add(serResult);
                    }
                }

                //部门子节点，服务节点加入到子节点
                Map<String, Object> childResult = getDeptChildResultOn(childT,servicesResult);
                childrenResult.add(childResult);
            }
        }else{
//            List<TokenResult> tokenResults = this.tokenService.findListByUserid(Long.parseLong(userId));
//            RestUser childT = this.restUserService.getById(userId);
            RestUser childT = this.restUserService.getById(userId);
            RestDept restDept = this.restDeptService.getById(childT.getDeptId());
            List<TokenResult> tokenResults = this.tokenService.findListByDeptid(childT.getDeptId().toString());

            //服务数据结果集
            List servicesResult = new ArrayList();
            for (TokenResult tokenResult : tokenResults) {
                ServiceMatedataResult servirceT = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());

                Map<String, Object> serResult = getServiceResultOn(servirceT,restDept);
                servicesResult.add(serResult);
            }
            //部门子节点，服务节点加入到子节点
            Map<String, Object> childResult = getDeptChildResultOn(restDept,servicesResult);
            childrenResult.add(childResult);
        }



        //树根节点,部门子节点加入到根节点
        tree.put("id", "0001");
        tree.put("text", "市政府");
        tree.put("value", "市政府");
        tree.put("showcheck",false);
        tree.put("complete",true);
        tree.put("isexpand",true);
        tree.put("checkstate",0);
        tree.put("hasChildren",true);
        tree.put("ChildNodes",childrenResult);

        List treeList = new ArrayList();
        treeList.add(tree);

        if(tree == null){
            return new ErrorResponseData("未获取到tree");
        }else {
            JSONObject tok = new JSONObject();
            tok.put("treeDataProvider", treeList);
            return new SuccessResponseData(tok);
        }
    }

    /**
     * 获取服务节点信息
     * @param user 用户信息
     * @param servirce,服务信息
     * @return
     */
    public Map<String, Object> getServiceResultOn(ServiceMatedataResult servirce, RestDept dept){
        Map<String, Object> serResult = new LinkedHashMap<String, Object>();//子节点服务
        serResult.put("id", servirce.getResourceid());
        serResult.put("text",servirce.getName());
        serResult.put("value",servirce.getName());
        serResult.put("showcheck",true);
        serResult.put("complete",true);
        serResult.put("isexpand",true);
        serResult.put("checkstate",1);
        serResult.put("hasChildren",false);
        serResult.put("pid", dept.getDeptId());
        return serResult;
    }

    /**
     * 获取部门子节点信息
     * @param user 用户信息
     * @param servicesResult,服务信息列表
     * @return
     */
    public Map<String, Object> getDeptChildResultOn(RestDept dept, List servicesResult){
        Map<String, Object> childResult = new LinkedHashMap<String, Object>();
        childResult.put("id", dept.getDeptId());
        childResult.put("text",dept.getSimpleName());
        childResult.put("value",dept.getSimpleName());
        childResult.put("showcheck",false);
        childResult.put("complete",true);
        childResult.put("isexpand",true);
        childResult.put("checkstate",1);
        childResult.put("hasChildren",true);
        childResult.put("pid",restUserService.getByAccount("admin").getUserId());
        childResult.put("ChildNodes",servicesResult);
        return childResult;
    }


    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/serviceMatedata.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/serviceMatedata_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/serviceMatedata_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ServiceMatedataParam serviceMatedataParam) {


        this.serviceMatedataService.add(serviceMatedataParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ServiceMatedataParam serviceMatedataParam) {
        this.serviceMatedataService.update(serviceMatedataParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ServiceMatedataParam serviceMatedataParam) {
        this.serviceMatedataService.delete(serviceMatedataParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-10-27
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ServiceMatedataParam serviceMatedataParam) {
        ServiceMatedata detail = this.serviceMatedataService.getById(serviceMatedataParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-10-27
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ServiceMatedataParam serviceMatedataParam) {
        return this.serviceMatedataService.findPageBySpec(serviceMatedataParam);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-10-27
     */
    @ResponseBody
    @RequestMapping("/listByUser")
    public LayuiPageInfo listByUser(@RequestParam(value = "userId") String userId) {
        ServiceMatedataParam serviceMatedataParam = new ServiceMatedataParam();
        RestUser restUser = this.restUserService.getById(Long.parseLong(userId));
        RestDept restDept = this.restDeptService.getById(restUser.getDeptId());
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        LayuiPageInfo layuiPageInfo1 = new LayuiPageInfo();
        if(!userId.equals("1")) {
            serviceMatedataParam.setResourceOwnerDept("水利局部门");
            layuiPageInfo1 = this.serviceMatedataService.findPageBySpec(serviceMatedataParam);
            serviceMatedataParam.setResourceOwnerDept(restDept.getFullName());
        }
        layuiPageInfo = this.serviceMatedataService.findPageBySpec(serviceMatedataParam);
        if(layuiPageInfo1.getCount() != 0 && layuiPageInfo.getCount() != 0) {
            List data = new ArrayList();
            data.addAll(layuiPageInfo1.getData());
            data.addAll(layuiPageInfo.getData());
            long count = layuiPageInfo.getCount() + layuiPageInfo1.getCount();
            layuiPageInfo.setCount(count);
            layuiPageInfo.setData(data);
        }
        return layuiPageInfo;
    }

    @ApiOperation("转换数据")
    @GetMapping("convertDate")
    public ResponseData convertDate(){
        this.serviceMatedataService.convertDate();
        return ResponseData.success();
    }

}



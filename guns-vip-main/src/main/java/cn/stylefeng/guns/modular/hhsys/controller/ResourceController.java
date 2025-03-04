package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.modular.hhsys.model.params.RasterlayersParam;
import cn.stylefeng.guns.modular.hhsys.model.result.TokenResult;
import cn.stylefeng.guns.modular.hhsys.service.CityResourceService;
import cn.stylefeng.guns.modular.hhsys.service.RasterlayersService;
import cn.stylefeng.guns.modular.hhsys.service.ServiceMatedataService;
import cn.stylefeng.guns.modular.hhsys.service.TokenService;
import cn.stylefeng.guns.sys.modular.rest.service.RestUserService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/resource")
@Api(tags = "资源服务")
public class ResourceController {
    @Autowired
    private ServiceMatedataService serviceMatedataService;

    @Autowired
    private CityResourceService cityResourceService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RasterlayersService rasterlayersService;

    @Autowired
    private RestUserService restUserService;

    /**
     * 根据服务资源ID与用户ID查询图层服务相关信息
     * @param {*} resourceId 
     * @param {*} userid 
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getLayerMetaDataResourceDetails", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("根据服务资源ID与用户ID查询图层服务相关信息")
    public ResponseData getLayerMetaDataResourceDetails(@RequestBody Map<String,Object> ids){
        Map<String,Object> result = new HashMap<>();
        String resourceId = ids.get("resourceId").toString();
        String userid = ids.get("userid").toString();
        String deptid = this.restUserService.getById(Long.parseLong(userid)).getDeptId().toString();
        ids.put("deptid",deptid);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deptid","24");
        map.put("resourceId",resourceId);

        try {
            result.put("metadata",this.serviceMatedataService.findByResourceId(resourceId));
            result.put("resource",this.cityResourceService.findByResourceId(resourceId));
            TokenResult tr = this.tokenService.findByIds(map);
            if(tr != null)
                result.put("token",tr);
            else
            result.put("token",this.tokenService.findByIds(ids));
        }catch (Exception e){
            result.put("flag",true);
            result.put("msg","查询失败");
        }

        return new SuccessResponseData(result);
    }

    /**
     * 根据服务资源ID查询图层服务ResourceDetails信息
     * @param {*} resourceId 
     * @param {*} userid 
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getResourceDetails", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("根据服务资源ID与用户ID查询图层服务相关信息")
    public ResponseData getResourceDetails(@RequestBody Map<String,Object> ids){
        Map<String,Object> result = new HashMap<>();
        String resourceId = ids.get("resourceId").toString();

        try {
            Map<String,Object> cityResourceResult = this.cityResourceService.findByResourceIdDetails(resourceId);
            if(cityResourceResult.get("value").equals(false)){
                result.put("resource",cityResourceResult.get("msg"));
            }else{
                result.put("resource",cityResourceResult.get("value"));
            }
//            result.put("resource",this.cityResourceService.findByResourceIdDetails(resourceId).get("value"));
            result.put("metadata",this.serviceMatedataService.findByResourceId(resourceId));
        }catch (Exception e){
            result.put("flag",false);
            result.put("msg","查询失败");
        }

        return new SuccessResponseData(result);
    }

    /**
     * 根据服务资源ID查询图层服务MetaData信息
     * @param {*} resourceId 
     * @param {*} userid 
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getMetaDataDetails", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("根据服务资源ID与用户ID查询图层服务相关信息")
    public ResponseData getMetaDataDetails(@RequestBody Map<String,Object> ids){
        Map<String,Object> result = new HashMap<>();
        String resourceId = ids.get("resourceId").toString();

        try {
            result.put("metadata",this.serviceMatedataService.findByResourceId(resourceId));

        }catch (Exception e){
            result.put("flag",true);
            result.put("msg","查询失败");
        }

        return new SuccessResponseData(result);
    }

    /**
     * 根据服务资源ID查询图层服务MetaData信息
     * @param {*} resourceId 
     * @param {*} userid 
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getRasterlayers", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("影像对比信息")
    public ResponseData getRasterlayers(@RequestBody Map<String,Object> rasterlayers){
        Map<String,Object> result = new HashMap<>();

        try {
            result.put("rasterlayers",this.rasterlayersService.findPageBySpec(new RasterlayersParam()));

        }catch (Exception e){
            result.put("flag",true);
            result.put("msg","查询失败");
        }

        return new SuccessResponseData(result);
    }





}

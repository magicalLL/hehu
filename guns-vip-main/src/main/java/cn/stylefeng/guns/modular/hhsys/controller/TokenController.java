package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Token;
import cn.stylefeng.guns.modular.hhsys.model.params.TokenParam;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
import cn.stylefeng.guns.modular.hhsys.model.result.TokenResult;
import cn.stylefeng.guns.modular.hhsys.service.ServiceMatedataService;
import cn.stylefeng.guns.modular.hhsys.service.TokenService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-11-03 09:35:28
 */
@Controller
@RequestMapping("/token")
public class TokenController extends BaseController {

    private String PREFIX = "/token";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ServiceMatedataService serviceMatedataService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/token.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-03
     * @param s
     */
    @RequestMapping("/add")
    public String add(String s) {
        return PREFIX + "/token_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/token_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TokenParam tokenParam) {
        this.tokenService.add(tokenParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(TokenParam tokenParam) {
        this.tokenService.update(tokenParam);
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
    public ResponseData delete(TokenParam tokenParam) {
        this.tokenService.delete(tokenParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-11-03
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(TokenParam tokenParam) {
        Token detail = this.tokenService.getById(tokenParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TokenParam tokenParam) {
        return this.tokenService.findPageBySpec(tokenParam);
    }

    /**
     * 根据userId获取对应的resourceId list
     *
     * @author lls
     * @Date 2020-10-26
     */
    @ResponseBody
    @RequestMapping("/getResourceIdsByUserId")
    public ResponseData getResourceIdsByUserId(@RequestParam("userId") String userId) {
        List<TokenResult> tokenResults = this.tokenService.findListByUserid(Long.parseLong(userId));
        List resourceIds = new ArrayList();
        for(TokenResult tokenResult: tokenResults){
            resourceIds.add(tokenResult.getResourceid());
        }
        return new SuccessResponseData(resourceIds);
    }

    /**
     * 根据userId获取对应的resourceId url name
     *  resource : list
     * @author lls
     * @Date 2020-10-26
     */
    @ResponseBody
    @RequestMapping("/getResourceMsgByUserId")
    public ResponseData getResourceMsgByUserId(@RequestParam("userId") String userId) {
        List<TokenResult> tokenResults = this.tokenService.findListByUserid(Long.parseLong(userId));
        List<Map<String,Object>> results = new ArrayList();
        for(TokenResult tokenResult: tokenResults){
            Map<String,Object> map = new HashMap<>();
            ServiceMatedataResult serviceMatedata = this.serviceMatedataService.findByResourceId(tokenResult.getResourceid());
            map.put("resourceId",tokenResult.getResourceid());
            map.put("name", serviceMatedata.getName());
            map.put("url", serviceMatedata.getRawUrl());
            results.add(map);
        }
        return new SuccessResponseData(results);
    }

}



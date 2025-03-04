package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Urls;
import cn.stylefeng.guns.modular.hhsys.model.params.UrlsParam;
import cn.stylefeng.guns.modular.hhsys.service.UrlsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-11-17 08:54:29
 */
@Controller
@RequestMapping("/urls")
public class UrlsController extends BaseController {

    private String PREFIX = "/urls";

    @Autowired
    private UrlsService urlsService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/urls.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/urls_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/urls_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(UrlsParam urlsParam) {
        this.urlsService.add(urlsParam);
        return ResponseData.success();
    }


    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(UrlsParam urlsParam) {
        this.urlsService.update(urlsParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(UrlsParam urlsParam) {
        this.urlsService.delete(urlsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-11-17
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(UrlsParam urlsParam) {
        Urls detail = this.urlsService.getById(urlsParam.getId());
        return ResponseData.success(detail);
    }


    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-17
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseData list(UrlsParam urlsParam) {
        LayuiPageInfo layuiPageInfo = this.urlsService.findPageBySpec(urlsParam);
//        return this.urlsService.findPageBySpec(urlsParam);
        return new SuccessResponseData(layuiPageInfo.getData());
    }


}



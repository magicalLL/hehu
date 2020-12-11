package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Rasterlayers;
import cn.stylefeng.guns.modular.hhsys.model.params.RasterlayersParam;
import cn.stylefeng.guns.modular.hhsys.service.RasterlayersService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 影像对比信息模块控制器
 *
 * @author lls
 * @Date 2020-11-06 10:03:07
 */
@Controller
@RequestMapping("/rasterlayers")
@Api(tags = "影像对比信息")
public class RasterlayersController extends BaseController {

    private String PREFIX = "/hhsys/rasterlayers";

    @Autowired
    private RasterlayersService rasterlayersService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/rasterlayers.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/rasterlayers_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/rasterlayers_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(RasterlayersParam rasterlayersParam) {
        this.rasterlayersService.add(rasterlayersParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(RasterlayersParam rasterlayersParam) {
        this.rasterlayersService.update(rasterlayersParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(RasterlayersParam rasterlayersParam) {
        this.rasterlayersService.delete(rasterlayersParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(RasterlayersParam rasterlayersParam) {
        Rasterlayers detail = this.rasterlayersService.getById(rasterlayersParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("获取rasterlayers")
    public ResponseData list() {
        LayuiPageInfo layuiPageInfo = this.rasterlayersService.findPageBySpec(new RasterlayersParam());
//        return layuiPageInfo;
        return new SuccessResponseData(layuiPageInfo.getData());
    }

}



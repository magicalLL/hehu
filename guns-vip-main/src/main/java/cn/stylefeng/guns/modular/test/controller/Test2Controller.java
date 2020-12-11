package cn.stylefeng.guns.modular.test.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Test2;
import cn.stylefeng.guns.modular.test.model.params.Test2Param;
import cn.stylefeng.guns.modular.test.service.Test2Service;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-10-26 12:00:12
 */
@Controller
@RequestMapping("/test2")
public class Test2Controller extends BaseController {

    private String PREFIX = "/test/test2";

    @Autowired
    private Test2Service test2Service;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/test2.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/test2_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/test2_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(Test2Param test2Param) {
        this.test2Service.add(test2Param);
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
    public ResponseData editItem(Test2Param test2Param) {
        this.test2Service.update(test2Param);
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
    public ResponseData delete(Test2Param test2Param) {
        this.test2Service.delete(test2Param);
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
    public ResponseData detail(Test2Param test2Param) {
        Test2 detail = this.test2Service.getById(test2Param.getId());
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
    public LayuiPageInfo list(Test2Param test2Param) {
        return this.test2Service.findPageBySpec(test2Param);
    }

}



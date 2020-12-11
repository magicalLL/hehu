package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.model.params.DepartmentParam;
import cn.stylefeng.guns.modular.hhsys.service.DepartmentService;
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
 * @Date 2020-10-28 08:57:22
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    private String PREFIX = "/hhsys/department";


    @Autowired
    private DepartmentService departmentService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/department.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/department_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/department_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DepartmentParam departmentParam) {
        this.departmentService.add(departmentParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DepartmentParam departmentParam) {
        this.departmentService.update(departmentParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DepartmentParam departmentParam) {
        this.departmentService.delete(departmentParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-10-28
     */
//    @RequestMapping("/detail")
//    @ResponseBody
//    public ResponseData detail(DepartmentParam departmentParam) {
//        Department detail = this.departmentService.getById(departmentParam.get());
//        return ResponseData.success(detail);
//    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DepartmentParam departmentParam) {
        return this.departmentService.findPageBySpec(departmentParam);
    }

}



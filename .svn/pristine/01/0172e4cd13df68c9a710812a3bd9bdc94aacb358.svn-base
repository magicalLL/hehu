package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Bzfield;
import cn.stylefeng.guns.modular.hhsys.model.params.BzfieldParam;
import cn.stylefeng.guns.modular.hhsys.service.BzfieldService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-10-28 17:00:19
 */
@Controller
@RequestMapping("/bzfield")
//@Api(tags = "字段")
public class BzfieldController extends BaseController {

    private String PREFIX = "/hhsys/bzfield";

    @Autowired
    private BzfieldService bzfieldService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/bzfield.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/bzfield_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/bzfield_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BzfieldParam bzfieldParam) {
        this.bzfieldService.add(bzfieldParam);
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
    public ResponseData editItem(BzfieldParam bzfieldParam) {
        this.bzfieldService.update(bzfieldParam);
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
    public ResponseData delete(BzfieldParam bzfieldParam) {
        this.bzfieldService.delete(bzfieldParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(BzfieldParam bzfieldParam) {
        Bzfield detail = this.bzfieldService.getById(bzfieldParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(BzfieldParam bzfieldParam) {
        return this.bzfieldService.findPageBySpec(bzfieldParam);
    }

    /**
     * 根据表名查询表字段列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/getFieldsByTableID", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ApiOperation("根据tableid查询表字段信息")
    public ResponseData getFieldsByTableID(@RequestParam(value = "tableId") String tableId) {
        LayuiPageInfo fieldList = this.bzfieldService.findPageByTableid(tableId);

        JSONObject field = new JSONObject();
        field.put("fields",fieldList.getData());
        return new SuccessResponseData(field);
    }

}



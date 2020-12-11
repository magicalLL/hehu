package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.modular.hhsys.entity.Bzresource;
import cn.stylefeng.guns.modular.hhsys.model.params.BzresourceParam;
import cn.stylefeng.guns.modular.hhsys.service.BzresourceService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
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
 * @Date 2020-10-28 17:01:01
 */
@Controller
@RequestMapping("/bzresource")
//@Api(tags = "标注")
public class BzresourceController extends BaseController {

    private String PREFIX = "/hhsys/bzresource";

    @Autowired
    private BzresourceService bzresourceService;

//    @Autowired
//    BzresourceParam bzresourceParam;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/bzresource.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/bzresource_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/bzresource_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-28
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BzresourceParam bzresourceParam) {
        this.bzresourceService.add(bzresourceParam);
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
    public ResponseData editItem(BzresourceParam bzresourceParam) {
        this.bzresourceService.update(bzresourceParam);
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
    public ResponseData delete(BzresourceParam bzresourceParam) {
        this.bzresourceService.delete(bzresourceParam);
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
    public ResponseData detail(BzresourceParam bzresourceParam) {
        Bzresource detail = this.bzresourceService.getById(bzresourceParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseData list(BzresourceParam bzresourceParam) {

        return new SuccessResponseData(this.bzresourceService.findPageBySpec(bzresourceParam));

    }

    /**
     * 查询用户创建的列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    @ResponseBody
    @RequestMapping(value = "/tablelist", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ApiOperation("查询标注列表")
    public ResponseData tableList(@RequestParam String userid) {
//        List tableList = this.bzresourceService.findPageByUserid(userid).getData();
//        List tableNameList = new ArrayList();
//        for(int i=0; i<tableList.size(); i++){
//            BzresourceResult br = (BzresourceResult) tableList.get(i);
//            tableNameList.add(br.getTablename());
//        }

        return new SuccessResponseData(this.bzresourceService.findPageByUserid(userid).getData());
    }

}



package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Patchedreportid;
import cn.stylefeng.guns.modular.hhsys.model.params.PatchedreportidParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PatchedreportidResult;
import cn.stylefeng.guns.modular.hhsys.service.PatchedreportidService;
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

import java.util.List;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-11-06 10:03:07
 */
@Controller
@RequestMapping("/patchedreportid")
@Api(tags = "影像对比信息")
public class PatchedreportidController extends BaseController {

    private String PREFIX = "/hhsys/patchedreportid";

    @Autowired
    private PatchedreportidService patchedreportidService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/patchedreportid.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/patchedreportid_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/patchedreportid_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PatchedreportidParam patchedreportidParam) {
        this.patchedreportidService.add(patchedreportidParam);
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
    public ResponseData editItem(PatchedreportidParam patchedreportidParam) {
        this.patchedreportidService.update(patchedreportidParam);
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
    public ResponseData delete(PatchedreportidParam patchedreportidParam) {
        this.patchedreportidService.delete(patchedreportidParam);
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
    public ResponseData detail(PatchedreportidParam patchedreportidParam) {
        Patchedreportid detail = this.patchedreportidService.getById(patchedreportidParam.getId());
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
    @ApiOperation("获取patchedreportid")
    public LayuiPageInfo list(PatchedreportidParam patchedreportidParam) {
        return this.patchedreportidService.findPageBySpec(patchedreportidParam);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    @ResponseBody
    @RequestMapping(value = "/getPatchedreportid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation("获取patchedreportid")
    public ResponseData getPatchedreportid() {
        String ids = "";
       LayuiPageInfo layuiPageInfo = this.patchedreportidService.findPageBySpec(new PatchedreportidParam());
       for(int i=0; i<layuiPageInfo.getData().size(); i++){
           try {
                List<PatchedreportidResult> idList =  layuiPageInfo.getData();
                PatchedreportidResult patchedreportid = idList.get(i);
               if (i == layuiPageInfo.getCount() - 1) {
                   ids = ids + (patchedreportid.getPatchedreportid());
               }else {
                   ids = ids + (patchedreportid.getPatchedreportid()) + ",";
               }
           }catch (Exception e){
               return new SuccessResponseData("类型转换异常");
           }
       }
       return new SuccessResponseData(ids);
    }

}



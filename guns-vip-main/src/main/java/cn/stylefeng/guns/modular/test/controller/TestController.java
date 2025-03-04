package cn.stylefeng.guns.modular.test.controller;

import cn.hutool.http.ContentType;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.controller.FileController;
import cn.stylefeng.guns.modular.test.entity.Test;
import cn.stylefeng.guns.modular.test.model.params.TestParam;
import cn.stylefeng.guns.modular.test.service.TestService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-10-26 11:55:53
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private String PREFIX = "/test";

    @Autowired
    private TestService testService;


    @RequestMapping("/downloadFileTo")
    @ResponseBody
    public ResponseData downloadFileTo(@RequestParam(value = "filename") String filename, HttpServletRequest request, ModelMap model) throws IOException {
        FileController fileController = new FileController();
        String type = "pdf";
        String filePath = "C:/Users/Administrator/Documents/";
        filePath = filePath +filename;
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),filePath, ContentType.MULTIPART.toString(), fileInputStream);
//        fileController.upload(multipartFile,type,request,model);
        return new SuccessResponseData();
    }


    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/test.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/test_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/test_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-10-26
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TestParam testParam) {
        this.testService.add(testParam);
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
    public ResponseData editItem(TestParam testParam) {
        this.testService.update(testParam);
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
    public ResponseData delete(TestParam testParam) {
        this.testService.delete(testParam);
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
    public ResponseData detail(TestParam testParam) {
        Test detail = this.testService.getById(testParam.getId());
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
    public LayuiPageInfo list(TestParam testParam) {
        return this.testService.findPageBySpec(testParam);
    }

}



package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityResults;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResultsParam;
import cn.stylefeng.guns.modular.hhsys.service.CityResultsService;
import cn.stylefeng.guns.modular.hhsys.service.UrlsService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.system.model.UploadResult;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-11-13 11:24:54
 */
@Controller
@RequestMapping("/cityResults")
public class CityResultsController extends BaseController {

    private String PREFIX = "/cityResults";

    @Autowired
    private CityResultsService cityResultsService;

    @Autowired
    private UrlsService urlsService;

    @Value("${web.image-path}")
    private String imagePath;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/cityResults.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/cityResults_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/cityResults_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CityResultsParam cityResultsParam) {
        this.cityResultsService.add(cityResultsParam);
        return ResponseData.success();
    }

    /**
     * 上传图片（指定上传路径）
     *
     * @author fengshuonan
     * @Date 2019-05-04 17:18
     */

    @RequestMapping("/uploadResultImg")
    @ResponseBody
    public UploadResult uploadResultImg(@RequestParam MultipartFile file) {

        String fileSavePath = this.imagePath;
        UploadResult uploadResult = new UploadResult();

        //生成文件的唯一id
        String fileId = IdWorker.getIdStr();
        uploadResult.setFileId(fileId);

        //获取文件后缀
        String fileSuffix = ToolUtil.getFileSuffix(file.getOriginalFilename());
        uploadResult.setFileSuffix(fileSuffix);

        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        uploadResult.setOriginalFilename(originalFilename);

        //生成文件的最终名称
        String finalName = fileId + "_" +originalFilename;
        uploadResult.setFinalName(finalName);
        uploadResult.setFileSavePath(fileSavePath + finalName);

        try {
            //保存文件到指定目录
            File newFile = new File(fileSavePath + finalName);

            //创建父目录
            System.out.println(newFile.getParentFile().exists());
            System.out.println(newFile);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }


            //保存文件
            file.transferTo(newFile);

            uploadResult.setMessage("上传成功");
        } catch (Exception e) {
//            log.error("上传文件错误！", e);
            uploadResult.setMessage("上传失败");
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);

        }

        return uploadResult;
    }

    /**
     * 规划成功插入数据库接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/saveResult")
    @ResponseBody
    public ResponseData saveResult(CityResultsParam cityResultsParam, HttpServletRequest request) throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        int port = request.getLocalPort();
        String imgpath = this.urlsService.getById("6").getUrl()+ "/accessFile/"+ cityResultsParam.getImg();
        String name = cityResultsParam.getName();
        String url = cityResultsParam.getUrl();
        CityResultsParam cityResults = new CityResultsParam();
        cityResults.setId(UUID.randomUUID().toString());
        cityResults.setName(name);
        cityResults.setImg(imgpath);
        cityResults.setUrl(url);

        try {
            this.cityResultsService.add(cityResults);
            return ResponseData.success(name);
        }catch (Exception e){
            return ResponseData.error("保存失败");
        }

    }



    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(CityResultsParam cityResultsParam, HttpServletRequest request) throws UnknownHostException {
        String id = cityResultsParam.getId();
        CityResults cityResults= this.cityResultsService.findById(id);
        String imgName = cityResultsParam.getImg();
        InetAddress addr = InetAddress.getLocalHost();
        String imgpath = this.urlsService.getById("6").getUrl()+"/accessFile/"+imgName;
        if(!imgpath.equals(cityResults.getImg())) {
            String delImg = this.imagePath + cityResults.getImg().substring(cityResults.getImg().lastIndexOf("/"+1));
            File file = new File(delImg);
            if (file.exists() && file.isFile()) {
                try {
                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String name = cityResultsParam.getName();
        String newFileName = cityResults.getImg();
        String url = cityResultsParam.getUrl();

        cityResults.setName(name);
        cityResults.setImg(imgpath);
        cityResults.setUrl(url);
        CityResultsParam crp = new CityResultsParam();
        ToolUtil.copyProperties(cityResults,crp);

        this.cityResultsService.update(crp);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CityResultsParam cityResultsParam) {
        this.cityResultsService.delete(cityResultsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(CityResultsParam cityResultsParam) {
        CityResults detail = this.cityResultsService.getById(cityResultsParam.getId());
        String imgName = detail.getImg();
        imgName = imgName.substring(imgName.lastIndexOf("/") + 1);
        detail.setImg(imgName);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseData list(CityResultsParam cityResultsParam) {
        LayuiPageInfo layuiPageInfo = this.cityResultsService.findPageBySpec(cityResultsParam);
//        return this.cityResultsService.findPageBySpec(cityResultsParam);
        return new SuccessResponseData(layuiPageInfo.getData());
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-17
     */
    @ResponseBody
    @RequestMapping("/listForSys")
    public LayuiPageInfo listForSys(CityResultsParam cityResultsParam) {
        return this.cityResultsService.findPageBySpec(cityResultsParam);
    }


}



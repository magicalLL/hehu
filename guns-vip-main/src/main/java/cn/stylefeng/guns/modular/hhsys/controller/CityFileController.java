package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityFile;
import cn.stylefeng.guns.modular.hhsys.model.params.CityFileParam;
import cn.stylefeng.guns.modular.hhsys.service.CityFileService;
import cn.stylefeng.guns.modular.hhsys.service.UrlsService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.rest.service.RestNoticeService;
import cn.stylefeng.guns.sys.modular.system.model.UploadResult;
import cn.stylefeng.guns.sys.modular.system.warpper.NoticeWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/**
 * 控制器
 *
 * @author lls
 * @Date 2020-11-13 11:24:54
 */
@Controller
@RequestMapping("/cityFile")
public class CityFileController extends BaseController {

    private String PREFIX = "/cityFile";

    @Autowired
    private CityFileService cityFileService;

    @Autowired
    private RestNoticeService restNoticeService;

    @Autowired
    private UrlsService urlsService;

    @Value("${web.file-path}")
    private String filePath;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/cityFile.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/cityFile_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/cityFile_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(String filepath) throws ParseException {
        String filename = filepath.substring(filepath.lastIndexOf("/") +1);
        CityFileParam cityFileParam = new CityFileParam();
        cityFileParam.setFilename(filename);
        cityFileParam.setFilepath(filepath);
        cityFileParam.setFiletype("");
//        cityFileParam.setCreatetime(new SimpleDateFormat("yyyy-MM-dd ").parse(""));

        this.cityFileService.add(cityFileParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(CityFileParam cityFileParam) {

        String id = cityFileParam.getId();
        CityFile cityFile= this.cityFileService.findById(id);
        String delfile = this.filePath + cityFile.getFilename();
        File  file = new File(delfile);
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        String fileName = cityFile.getFilename();
        String filePath = cityFile.getFilepath();
        String newFileName = cityFileParam.getFilename();
        filePath = fileName.substring(0,filePath.lastIndexOf("/")) + newFileName;

        cityFile.setFilename(newFileName);
        cityFile.setFilepath(filePath);
        cityFile.setCreatetime(new Date());
        CityFileParam cfp = new CityFileParam();
        ToolUtil.copyProperties(cityFile,cfp);

        this.cityFileService.update(cfp);
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
    public ResponseData delete(CityFileParam cityFileParam) {
        String fileId = cityFileParam.getId();
        CityFile cityFile= this.cityFileService.findById(fileId);
        String delfile = this.filePath + cityFile.getFilename();
        File  file = new File(delfile);
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.cityFileService.delete(cityFileParam);
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
    public ResponseData detail(CityFileParam cityFileParam) {
        CityFile detail = this.cityFileService.getById(cityFileParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 上传文件（指定上传路径）
     *
     * @author fengshuonan
     * @Date 2019-05-04 17:18
     */

    @RequestMapping("/uploadFile")
    @ResponseBody
    public UploadResult uploadFile(@RequestParam MultipartFile file) {

        String fileSavePath = this.filePath;
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
        originalFilename = originalFilename.replaceAll("_","");
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
     * 文件信息插入数据库接口
     *
     * @author lls
     * @Date 2020-11-13
     */
    @RequestMapping("/saveFile")
    @ResponseBody
    public ResponseData saveFile(@RequestParam(value = "fileName") String fileName, HttpServletRequest request) throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        int port = request.getLocalPort();
        String path = this.urlsService.getById("6").getUrl()+"/accessFile/"+ fileName;
        String name = fileName;
        String type = fileName.substring(fileName.lastIndexOf(".")+1);

        CityFileParam cityFileParam = new CityFileParam();
        cityFileParam.setId(UUID.randomUUID().toString());
        cityFileParam.setFilename(name);
        cityFileParam.setFilepath(path);
        cityFileParam.setFiletype(type);
        cityFileParam.setCreatetime(new Date());

        try {
            this.cityFileService.add(cityFileParam);
            return ResponseData.success(name);
        }catch (Exception e){
            return ResponseData.error("保存失败");
        }

    }


    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseData list(CityFileParam cityFileParam) {
        LayuiPageInfo layuiPageInfo = this.cityFileService.findPageBySpec(cityFileParam);
        return new SuccessResponseData(layuiPageInfo.getData());
    }
    @ResponseBody
    @RequestMapping("/listLong")
    public ResponseData listCopy(CityFileParam cityFileParam) {
        LayuiPageInfo layuiPageInfo = this.cityFileService.findPageBySpecCopy(cityFileParam);
        return new SuccessResponseData(layuiPageInfo.getData());
    }

    /**
     * 查询列表(运维系统)
     *
     * @author lls
     * @Date 2020-11-17
     */
    @ResponseBody
    @RequestMapping("/listForSys")
    public LayuiPageInfo listForSys(CityFileParam cityFileParam) {
        return this.cityFileService.findPageBySpec(cityFileParam);
    }


    /**
     * 获取通知列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @ResponseBody
    @RequestMapping(value = "/noticeList")
    public ResponseData list(String condition) {
        Page<Map<String, Object>> list = this.restNoticeService.list(condition);
        Page<Map<String, Object>> wrap = new NoticeWrapper(list).wrap();
//        return LayuiPageFactory.createPageInfo(wrap);
        return new SuccessResponseData(LayuiPageFactory.createPageInfo(wrap).getData());
    }

    @ResponseBody
    @RequestMapping(value = "/findByNoticeId" , method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    public ResponseData findByNoticeId(@RequestParam(value = "noticeId")String noticeId){
        Map<String,Object> notice = this.restNoticeService.findByNoticeId(noticeId);
        return new SuccessResponseData(notice);
    }


}



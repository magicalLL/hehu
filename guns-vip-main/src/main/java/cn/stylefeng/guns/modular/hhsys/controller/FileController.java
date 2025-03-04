package cn.stylefeng.guns.modular.hhsys.controller;

import cn.hutool.http.ContentType;
import cn.stylefeng.guns.modular.hhsys.entity.Wyhc;
import cn.stylefeng.guns.modular.hhsys.mapper.WyhcMapper;
import cn.stylefeng.guns.modular.hhsys.service.CityFileService;
import cn.stylefeng.guns.modular.hhsys.utils.Convertor;
import cn.stylefeng.guns.modular.hhsys.utils.ImgUtils;
import cn.stylefeng.guns.modular.hhsys.utils.ResultInfo;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


/**
 * Created by dell on 2016/3/9.
 */
@Controller
@RequestMapping("/file")
@Api(tags = "文件")
public class FileController {

    @Autowired
    private WyhcMapper wyhcMapper;

    @Autowired
    private CityFileService cityFileService;

    @Value("${web.wyhcimg-path}")
    private String wyhcimg;



//    @Autowired
//    private FileDao fileDao;

//    @Autowired
//    private ResourceDao resourceDao;
//
//    @Autowired
//    private FileService fileService;
//
//    @Autowired
//    private LogService logService;
//
    /**
     *
     *
     * 日志打印
     *
     */
    //private static Logger logger = LogManager.getLogger(FileController.class);


    /**
     *
     * 获取图斑信息
     *
     *
     * @param searchPatchMap
     * @return
     */
    @RequestMapping(value = "/getPatchReportInfoByTBID",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> getPatchReportInfoByTBID(@RequestBody Map<String,Object> searchPatchMap){

        Map<String, Object> result = new HashMap<>();
        try {

            String tbid=searchPatchMap.get("TBID").toString();
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("tbid",tbid);
            List<Wyhc> patchReportList = wyhcMapper.getPatchReportInfoByTBID(requestParamMap);
            if(patchReportList.size() != 0){

                result.put("total",patchReportList.size());
                result.put("rows",patchReportList);
                return result;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            return result;
        }
        return result;

    }






    /**
     *
     *
     * 高斯投影坐标 转为 经纬度坐标
     * @param patchReportMap
     * @return
     */
    @RequestMapping(value = "/convertCoordinate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultInfo convertCoordinate(@RequestBody Map<String,Object> patchReportMap) {

        ResultInfo resultInfo = new ResultInfo();
        double xmin=Double.parseDouble(patchReportMap.get("xmin").toString());
        double ymin=Double.parseDouble(patchReportMap.get("ymin").toString());
        double xmax=Double.parseDouble(patchReportMap.get("xmax").toString());
        double ymax=Double.parseDouble(patchReportMap.get("ymax").toString());
        Convertor convertor = new Convertor(3, 120, 0, 500000, 0);
        double[] minjw = convertor.GKgetJW(xmin,ymin);
        double[] maxjw = convertor.GKgetJW(xmax,ymax);
        Map<String,Object> resultCoordinate=new HashMap<String,Object>();
        resultCoordinate.put("xmin",minjw[0]);
        resultCoordinate.put("ymin",minjw[1]);
        resultCoordinate.put("xmax",maxjw[0]);
        resultCoordinate.put("ymax",maxjw[1]);
        List<Map<String,Object>> resultCoordinateList=new ArrayList<>();
        resultCoordinateList.add(resultCoordinate);
        resultInfo.setResult(true);
        resultInfo.setList(resultCoordinateList);
        resultInfo.setMsg("坐标转换成功!!!");

        return resultInfo;

    }


    @RequestMapping(value = "/addPicture", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("上传图片到服务器指定目录")
    public ResponseData uploadPicture(@RequestParam String filename) throws IOException {
        String filePath = "C:\\Users\\Administrator\\Pictures\\";
        filePath = filePath +filename;
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        MultipartFile multipartFile = new MockMultipartFile(file.getName(),filePath, ContentType.MULTIPART.toString(), fileInputStream);
        MultipartFile[] ms = new MultipartFile[1];
        ms[0] = multipartFile;
        ResponseData result = uploadPicture(ms);

        return result;

    }

//  resultInfo
//
    /**
     * 上传图片到服务器指定目录
     * @param fileListParam
     * @return
     */
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation("上传图片到服务器指定目录")
    public ResponseData uploadPicture(@RequestParam(value = "file", required = false) MultipartFile[] fileListParam){

        List<cn.stylefeng.guns.modular.hhsys.entity.File> list = new ArrayList<cn.stylefeng.guns.modular.hhsys.entity.File>();
        ResultInfo resultInfo = new ResultInfo();

        if(fileListParam.length>0)
        {
            for (MultipartFile m_fileObj : fileListParam) {
                String fileName = m_fileObj.getOriginalFilename();
                String pic_path = this.wyhcimg;
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                fileName = uuid + getExtention(fileName);//UUID+文件后
                if(checkIsImage(getExtention(fileName))){
                boolean m = ImgUtils.checkFileSize(m_fileObj.getSize(), 1024, "k");
                //如果文件大于1000k则进行图片压缩
                if(!m){
                    try {
                        byte[] bytes = ImgUtils.commpressPicCycle(m_fileObj.getBytes(), 1000, 0.7);
                        InputStream inputStream = new ByteArrayInputStream(bytes);
                        BASE64Decoder decoder = new BASE64Decoder();
                        OutputStream os = new FileOutputStream(fileName);
                        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(pic_path  + fileName));//打开输入流
                        imageOutput.write(bytes, 0, bytes.length);//将byte写入硬盘
//                        imageOutput.close();
//                        os.write(bytes, 0, bytes.length);
//                        os.flush();
//                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    //logger.info("UUID处理之后图片文件: "+fileName);
                    File targetFile = new File(pic_path  + fileName);
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    //保存
                    try {
                        m_fileObj.transferTo(targetFile);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                    //返回文件信息
                    cn.stylefeng.guns.modular.hhsys.entity.File fileInfo = new cn.stylefeng.guns.modular.hhsys.entity.File();
                    fileInfo.setId(uuid);
                    fileInfo.setFilename(fileName);
//                    String webPath = path + "/" + fileName;
                    String webPath = pic_path+ fileName;
                    fileInfo.setFilepath(webPath);
                    list.add(fileInfo);
                }
                else
                {
                    resultInfo.setList(list);
                    resultInfo.setResult(false);
                    resultInfo.setTotalCount(list.size());
                    resultInfo.setMsg("文件类型必须是图片,请重新上传!!!");
                    break;
                }

            }

            resultInfo.setList(list);
            resultInfo.setResult(true);
            resultInfo.setTotalCount(list.size());
            resultInfo.setMsg("success");
        }
        else
        {
            resultInfo.setList(list);
            resultInfo.setResult(false);
            resultInfo.setTotalCount(list.size());
            resultInfo.setMsg("未选中图片资源文件!!!");
        }

        return new SuccessResponseData(resultInfo);

    }

    /**
     *
     *
     * 设置外业核查图片所在服务器目录
     *
     *
     * @return 图片所在路径
     */
    private String getServerPatchReportPicDirectory() {
        String pic_path;//获取Tomcat服务器所在的路径
        String tomcat_path = System.getProperty( "user.dir" );
        //获取Tomcat服务器所在路径的最后一个文件目录
        String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());
        //若最后一个文件目录为bin目录，则服务器为手动启动
        if(("bin").equals(bin_path)){//手动启动Tomcat时获取路径为：E:
            //获取保存上传图片的文件路径
            pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\resources"+"\\image\\";
        }else{//获取路径为：E:\idea-projects\server-master\resources\image
            pic_path = tomcat_path+"\\resources"+"\\image\\";
        }
        return pic_path;
    }

    /**
     *
     *
     * 设置pdf文件所在服务器目录
     *
     *
     * @return pdf所在路径
     */
    private String getServerPatchReportPdfDirectory() {
        String pic_path;//获取Tomcat服务器所在的路径
        String tomcat_path = System.getProperty( "user.dir" );
        //获取Tomcat服务器所在路径的最后一个文件目录
        String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());
        //若最后一个文件目录为bin目录，则服务器为手动启动
        if(("bin").equals(bin_path)){//手动启动Tomcat时获取路径为：E:
            //获取保存上传图片的文件路径
            pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\resources"+"\\pdf\\";
        }else{//获取路径为：E:\idea-projects\server-master\resources\image
            pic_path = tomcat_path+"\\resources"+"\\pdf\\";
        }
        return pic_path;
    }

    /**
     *
     * 截取文件后缀名
     *
     * @param fileName
     * @return
     */
    private static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos).toLowerCase();
    }

    /**
     *
     * 检查是否是图片格式
     * @param imgStr
     * @return
     */
    public static boolean checkIsImage(String imgStr) {
        boolean flag = false;
        if (imgStr != null) {
            if (imgStr.equalsIgnoreCase(".gif")
                    || imgStr.equalsIgnoreCase(".jpg")
                    || imgStr.equalsIgnoreCase(".jpeg")
                    || imgStr.equalsIgnoreCase(".png")) {
                flag = true;
            }
        }
        return flag;
    }

    @RequestMapping("/downloadFileTo")
    @ResponseBody
    public ResponseData downloadFileTo(@RequestParam(value = "filename") String filename, HttpServletRequest request, ModelMap model) throws IOException {

        String type = "pdf";
        String filePath = "C:/Users/Administrator/Documents/";
        filePath = filePath +filename;
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),filePath, ContentType.MULTIPART.toString(), fileInputStream);
//        upload(multipartFile,type,request,model);
        return new SuccessResponseData();
    }


    /**
     * 获取图片流返回客户端 根据图片名称
     * @param request
     * @param response
     * @param picName
     * @throws Exception
     */
    @RequestMapping(value = "/getPatchReportPicByName", method = RequestMethod.GET)
    @ApiOperation("获取图片流返回客户端 根据图片名称")
    public void getPatchReportPicByName(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam("picName") String picName) throws Exception{

        response.setHeader("Access-Control-Allow-Origin", "*");

//        String picUrlDecoder=fileDao.getPictureUrlByUUID(uuid);

//        String picUrlDecoder= URLDecoder.decode(picUrlParam, "UTF-8");
        if(!picName.isEmpty()){

            String suffix = picName.substring(picName.lastIndexOf(".") + 1).trim();
//            logger.info("suffix: "+suffix);
            String fileName=picName;

            if (suffix.equalsIgnoreCase("png") ||
                    suffix.equalsIgnoreCase("jpg") ||
                    suffix.equalsIgnoreCase("jpeg") ||
                    suffix.equalsIgnoreCase("bmp")
                    || suffix.equalsIgnoreCase("tiff") || suffix.equalsIgnoreCase("gif")) {
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", -1);
                response.setContentType("application/x-msdownload");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                try {
//                    String path = request.getSession().getServletContext().getRealPath("site")+"/Map/js/shapefile/uploadPatchReportImage";
//                    String path = getServerPatchReportPicDirectory();
                    String path = this.wyhcimg;
                    String realFilePath=path+fileName;
                    InputStream fis = new FileInputStream(realFilePath);
                    ServletOutputStream outputStream=response.getOutputStream();
                    byte[] buf=new byte[2048];
                    int n=0;
                    while ((n=fis.read(buf))!=-1){

                        outputStream.write(buf,0,n);
                    }
                    fis.close();
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
//                    logger.error(ex.getMessage());
//                    logger.error(ex.getLocalizedMessage());
//                    return new ErrorResponseData("未找到指定路径的图片");
                }
            }
        }
//        return  new SuccessResponseData("成功下载图片");



    }

////    private final BASE64Decoder decoder = new BASE64Decoder();
//    /**
//     *
//     *
//     * 获取图片流返回客户端
//     * @param response
//     * @param uuid
//     */
//    @RequestMapping(value = "/getPatchReportPic", method = RequestMethod.GET)
//    public void getPatchReportPic(HttpServletResponse response, @RequestParam("uuid") String uuid) throws Exception{
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//
//        String picUrlDecoder=fileDao.getPictureUrlByUUID(uuid);
//
////        String picUrlDecoder= URLDecoder.decode(picUrlParam, "UTF-8");
//        if(!picUrlDecoder.isEmpty()){
//
//            String suffix = picUrlDecoder.substring(picUrlDecoder.lastIndexOf(".") + 1).trim();
////            logger.info("suffix: "+suffix);
////            logger.info("fileName: "+picUrlDecoder.substring(picUrlDecoder.lastIndexOf("/") + 1));
//            String fileName=picUrlDecoder.substring(picUrlDecoder.lastIndexOf("/") + 1).trim();
//
//            if (suffix.equalsIgnoreCase("png") ||
//                    suffix.equalsIgnoreCase("jpg") ||
//                    suffix.equalsIgnoreCase("jpeg") ||
//                    suffix.equalsIgnoreCase("bmp")
//                    || suffix.equalsIgnoreCase("tiff") || suffix.equalsIgnoreCase("gif")) {
//                response.setHeader("Cache-Control", "no-cache");
//                response.setHeader("Pragma", "no-cache");
//                response.setDateHeader("Expires", -1);
//                response.setContentType("application/x-msdownload");
//                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//                try {
//                    InputStream fis = new FileInputStream(picUrlDecoder);
//                    ServletOutputStream outputStream=response.getOutputStream();
//                    byte[] buf=new byte[2048];
//                    int n=0;
//                    while ((n=fis.read(buf))!=-1){
//
//                        outputStream.write(buf,0,n);
//                    }
//                    fis.close();
//                    outputStream.flush();
//                    outputStream.close();
//
//                } catch (IOException ex) {
//                    ex.printStackTrace();
////                    logger.error(ex.getMessage());
////                    logger.error(ex.getLocalizedMessage());
//                    return;
//                }
//            }
//        }
//
//    }


//    /**
//     * 查看图片接口，只有图片格式可以查看
//     *
//     * @param response
//     * @param fileId
//     */
//    @RequestMapping(value = "/viewImage", method = RequestMethod.GET)
//    public void viewImage(HttpServletResponse response, @RequestParam("fileId") String fileId) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        // 判断fileId是否为空
//        if (fileId == null || fileId.isEmpty()) {
//            try {
//                OutputStream outputStream = response.getOutputStream();
//                String data = "{failure:Id不能为空！}";
//                byte[] dataByteArr = data.getBytes("UTF-8");
//                outputStream.write(dataByteArr);
//                outputStream.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            return;
//        }
//
//        com.smartcity.entity.File fileInfo = fileDao.getFileById(fileId);
//        String fileName = fileInfo.getFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        if (suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("bmp")
//                || suffix.equalsIgnoreCase("tiff") || suffix.equalsIgnoreCase("gif")) {
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", -1);
//            String filePath = IPUtil.getRemoteServerMapping() + ":/" + fileInfo.getFilepath();
//            try {
//                InputStream inputStream = new FileInputStream(filePath);
//                FileCopyUtils.copy(inputStream, response.getOutputStream());
//                inputStream.close();
//                response.getOutputStream().close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            try {
//                OutputStream outputStream = response.getOutputStream();
//                String data = "{failure:只有指定格式才可以浏览！}";
//                byte[] dataByteArr = data.getBytes("UTF-8");
//                outputStream.write(dataByteArr);
//                outputStream.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            return;
//        }
//    }
//
//    /**
//     * 根据文件ID获取物理路径
//     *
//     * @param httpSession
//     * @param fileId
//     * @return
//     */
//    @RequestMapping(value = "/getPhysicalPath", method = RequestMethod.GET)
//    public @ResponseBody
//    ResultInfo getPhysicalPath(HttpSession httpSession, @RequestParam("fileId") String fileId) {
//        ResultInfo resultInfo = new ResultInfo();
//        String str = httpSession.getServletContext().getRealPath("/");
//        com.smartcity.entity.File fileInfo = fileService.getFileById(fileId);
//        //String path = str + fileInfo.getFilepath();
//        String path = IPUtil.getRemoteServerMapping() + ":" + fileInfo.getFilepath();
//        path = path.replace("\\", "/");
//        resultInfo.setResult(true);
//        resultInfo.setMsg(path);
//        return resultInfo;
//    }
//
//    @RequestMapping(value = "/delFile", method = RequestMethod.GET)
//    public @ResponseBody
//    ResultInfo delFile(HttpSession httpSession, @RequestParam("fileId") String fileId) {
//        ResultInfo resultInfo = new ResultInfo();
//        String realPath = IPUtil.getRemoteServerMapping();
//        boolean r = fileService.delFile(fileId, realPath);
//        if (r) {
//            resultInfo.setResult(true);
//            resultInfo.setMsg("success:删除成功");
//        } else {
//            resultInfo.setResult(false);
//            resultInfo.setMsg("failure:删除失败");
//        }
//        return resultInfo;
//    }
//
//    @Autowired
//    private CityResourceService cityResourceService;
//    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
//    public @ResponseBody
//    ResultInfo downloadFile(HttpSession httpSession,
//                            @RequestParam("resourceId") String resourceId,
//                            @RequestParam("type") String type,
//                            HttpServletRequest request, HttpServletResponse response) {
//        ResultInfo resultInfo = new ResultInfo();
//        boolean canDownload = false;
//        // 根据resourceId和用户ID判断是否有权限下载
//        String userId = (String) httpSession.getAttribute("userId");
//        if (userId == null || userId.isEmpty()) {
//            resultInfo.setResult(false);
//            resultInfo.setMsg("请登录后再进行下载！");
//            return resultInfo;
//        }
//        CityResourceResult resource = this.cityResourceService.findByResourceId(resourceId);
//        if (resource.getPublishuserid().equals(userId)) {
//            canDownload = true;
//        } else {
//            ResourceApply resourceApply = (resourceDao.getResourceApplyUser(resourceId, userId)).get(0);
//            if (resourceApply.getApplyState().intValue() == 2 && resourceApply.getState().intValue() == 1) {
//                canDownload = true;
//            }
//        }
//        // 根据resourceId获取数据路径
//        if (canDownload) {
//            Log log = new Log();
//            log.setResourceId(resourceId);
//            log.setUserId(userId);
//            log.setRequestUrl(request.getRequestURI());
//
//            String filePath = fileService.getFilePathByResourceId(resourceId);
//            if (filePath.isEmpty()) {
//                resultInfo.setResult(false);
//                resultInfo.setMsg("没有找到下载文件！");
//                log.setResult("failure");
//                if (type.equals("data"))
//                    writeLog(log);
//                return resultInfo;
//            }
//            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
//            filePath = IPUtil.getRemoteServerMapping() + ":" + filePath;
//            System.out.println("filePath-------------------->" + filePath);
//            try {
//                InputStream inputStream = new FileInputStream(new File(filePath));
//                response.setContentType("application/octet-stream");
//                response.setHeader("Content-disposition", "attachment; filename="
//                        + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//                FileCopyUtils.copy(inputStream, response.getOutputStream());
//                inputStream.close();
//                response.getOutputStream().close();
//                resultInfo.setResult(true);
//                resultInfo.setMsg("下载成功！");
//                log.setResult("success");
//                if (type.equals("data"))
//                    writeLog(log);
//                return null;
//            } catch (FileNotFoundException fex) {
//                resultInfo.setResult(false);
//                resultInfo.setMsg("没有找到下载文件！");
//                log.setResult("failure");
//                if (type.equals("data"))
//                    writeLog(log);
//                return resultInfo;
//            } catch (IOException ioex) {
//                resultInfo.setResult(false);
//                resultInfo.setMsg("文件IO出错！");
//                log.setResult("failure");
//                if (type.equals("data"))
//                    writeLog(log);
//                return resultInfo;
//            }
//        }
//        // 下载
//        return resultInfo;
//    }

//    @RequestMapping(value = "/downloadApplyFile", method = RequestMethod.GET)
//    public @ResponseBody
//    ResultInfo downloadApplyFile(HttpSession httpSession,
//                                 @RequestParam("applyId") String applyId,
//                                 HttpServletRequest request, HttpServletResponse response) {
//        ResultInfo resultInfo = new ResultInfo();
//        ResourceApply resourceApply = resourceDao.getResourceApplyById(applyId);
//        String fileId = resourceApply.getFileid();
//        com.smartcity.entity.File file = fileService.getFileById(fileId);
//        String filePath = file.getFilepath();
//        if (filePath.isEmpty()) {
//            resultInfo.setResult(false);
//            resultInfo.setMsg("没有找到下载文件！");
//            return resultInfo;
//        }
//        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
//        filePath = IPUtil.getRemoteServerMapping() + ":" + filePath;
//        System.out.println("filePath---------->" + filePath);
//        try {
//            InputStream inputStream = new FileInputStream(new File(filePath));
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-disposition", "attachment; filename="
//                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//            FileCopyUtils.copy(inputStream, response.getOutputStream());
//            inputStream.close();
//            response.getOutputStream().close();
//            resultInfo.setResult(true);
//            resultInfo.setMsg("下载成功！");
//            return null; // 这样就不会报异常了，如果返回resultInfo会报Closed异常
//        } catch (FileNotFoundException fex) {
//            resultInfo.setResult(false);
//            resultInfo.setMsg("没有找到下载文件！");
//            return resultInfo;
//        } catch (IOException ioex) {
//            resultInfo.setResult(false);
//            resultInfo.setMsg("文件IO出错！");
//            return resultInfo;
//        }
//    }
//
//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public @ResponseBody
//    ResultInfo testDownload(@RequestParam("fileName") String fileName, HttpSession httpSession,
//                            HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ResultInfo resultInfo = new ResultInfo();
//        String filePath = "";
//        String str = httpSession.getServletContext().getRealPath("/");
//        System.out.println("str--->" + str);
//        filePath = "http://" + IPUtil.getIP() + ":" + IPUtil.getPort() + request.getContextPath() + "/upload/" + fileName;
//        filePath = str + "/upload/" + fileName;
//        InputStream inputStream = new FileInputStream(new File(filePath));
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment; filename="
//                + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//        //response.setHeader("Content-Length", String.valueOf(downFile.getFileSize()));
//        FileCopyUtils.copy(inputStream, response.getOutputStream());
//        inputStream.close();
//        response.getOutputStream().close();
//        resultInfo.setResult(true);
//        resultInfo.setMsg("下载成功！");
//        return resultInfo;
//    }
//
//    private void writeLog(Log logInfo) {
//        try {
//            logService.writeLog(logInfo);
//        } catch (Exception ex) {
//            logger.error("写日志错误！请检查" + ex.getMessage());
//        }
//    }
}
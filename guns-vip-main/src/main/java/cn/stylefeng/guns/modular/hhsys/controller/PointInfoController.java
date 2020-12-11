package cn.stylefeng.guns.modular.hhsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.PointInfo;
import cn.stylefeng.guns.modular.hhsys.model.params.PointInfoParam;
import cn.stylefeng.guns.modular.hhsys.service.PointInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;


/**
 * 变化点位管理控制器
 *
 * @author lls
 * @Date 2020-11-24 14:56:37
 */
@Controller
@RequestMapping("/pointInfo")
public class PointInfoController extends BaseController {

    private String PREFIX = "/pointInfo";

    @Autowired
    private PointInfoService pointInfoService;

    /**
     * 跳转到主页面
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/pointInfo.html";
    }

    /**
     * 新增页面
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/pointInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/pointInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PointInfoParam pointInfoParam) {
        pointInfoParam.setObjectid(UUID.randomUUID().toString());
        boolean flag = this.pointInfoService.add(pointInfoParam);
        if(flag == true){
            return ResponseData.success();
        }else {
            return ResponseData.error("插入失败");
        }
    }

    /**
     * 新增接口
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/addPoint")
    @ResponseBody
    public ResponseData addPoint(@RequestBody Map<String, Object> pointInfo) {
        pointInfo.put("objectid",UUID.randomUUID().toString());
        boolean flag = this.pointInfoService.addPoint(pointInfo);

        if(flag == true){
            return ResponseData.success();
        }else {
            return ResponseData.error("插入失败");
        }
    }

    /**
     * 编辑接口
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(PointInfoParam pointInfoParam) {
        this.pointInfoService.update(pointInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PointInfoParam pointInfoParam) {
        this.pointInfoService.delete(pointInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lls
     * @Date 2020-11-24
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PointInfoParam pointInfoParam) {
        PointInfo detail = this.pointInfoService.getById(pointInfoParam.getObjectid());
        return ResponseData.success(detail);
    }


    /**
     * 查看详情接口
     *
     * param id
     * @author lls
     * @Date 2020-11-20
     */
    @RequestMapping("/getDetailById")
    @ResponseBody
    public ResponseData getDetailById(@RequestParam("id") String id) {
        PointInfoParam pointInfoParam = new PointInfoParam();
        pointInfoParam.setObjectid(id);
        PointInfo detail = this.pointInfoService.getById(pointInfoParam.getObjectid());

        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lls
     * @Date 2020-11-20
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseData list(PointInfoParam pointInfoParam) {
        LayuiPageInfo layuiPageInfo = this.pointInfoService.findPageBySpec(pointInfoParam);
        return new SuccessResponseData(layuiPageInfo.getData());
    }
    /**
     * 查询列表（供运维前端使用）
     *
     * @author lls
     * @Date 2020-11-20
     */
    @ResponseBody
    @RequestMapping("/sysList")
    public LayuiPageInfo sysList(PointInfoParam pointInfoParam) {
        LayuiPageInfo layuiPageInfo = this.pointInfoService.findPageBySpec(pointInfoParam);
        return layuiPageInfo;
    }

    /**
     * 根据dept查询点位列表
     *
     * @author lls
     * @Date 2020-11-20
     */
    @ResponseBody
    @RequestMapping("/listByDept")
    public ResponseData listByDept(@RequestParam("dept") String dept) {
        LayuiPageInfo layuiPageInfo = this.pointInfoService.findPageByDept(dept);
        return new SuccessResponseData(layuiPageInfo.getData());
    }




    @RequestMapping(value = "query",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("query")
    public ResponseData query(){
        Map<String,Object> map = new HashMap<>();
        //创建一个http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个POST请求
        HttpPost request=new HttpPost("http://112.82.123.170:6080/arcgis/rest/services/HHYG/NYHCDW/MapServer/0/query");
        //设置HTTP POST请求参数必须用NameValuePair
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));//format设置成json
        params.add(new BasicNameValuePair("where","1=1"));
        params.add(new BasicNameValuePair("outFields","*"));

        map.put("params",params);
        try {
            //设置http Post请求参数
            HttpEntity entity = new UrlEncodedFormEntity(params);
            map.put("entity",entity);
            request.setEntity(entity);
            HttpResponse response=client.execute(request);
            if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
                System.out.println("=============================="+ JSONObject.toJSON(response.getEntity()));
                System.out.println("=============================="+ JSONObject.toJSONString(response.getEntity()));
                String result=EntityUtils.toString(response.getEntity());
                System.out.println(result);
                //需要对返回的结果进行分析，判断新增记录是成功还是失败
                //如果成功，则进行后续的分析
                return new SuccessResponseData(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return new SuccessResponseData(map);
            //进行处理操作
        } catch (IOException e) {
            //进行处理操作
            return new SuccessResponseData(map);
        }
        return new SuccessResponseData(map);
    }


    @RequestMapping(value = "addFeature",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("addFeature")
    public ResponseData addFeature(){
        Map<String,Object> map = new HashMap<>();
        //创建一个http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        map.put("client",client);
        //创建一个POST请求
        HttpPost request=new HttpPost("http://112.82.123.170:6080/arcgis/rest/services/HHYG/NYHCDW/MapServer/0/addFeatures");
        //设置HTTP POST请求参数必须用NameValuePair
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));//format设置成json
//        params.add(new BasicNameValuePair("features","[{'geometry':{'rings'[[111,2222],[6666666,000009999999999]]},'attributes':{'BZ':''}}]"));
        params.add(new BasicNameValuePair("features",makeJson()));
        map.put("params",params);

        try {
            //设置http Post请求参数
            HttpEntity entity = new UrlEncodedFormEntity(params);
            request.setEntity(entity);
            HttpResponse response=client.execute(request);
            if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
                String result= EntityUtils.toString(response.getEntity());
                System.out.println(result);
                //需要对返回的结果进行分析，判断新增记录是成功还是失败
                //如果成功，则进行后续的分析
                return new SuccessResponseData(result);
            }
            return new SuccessResponseData(map);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            //进行处理操作
            return new SuccessResponseData(map);
        } catch (IOException e) {
            //进行处理操作
            return new SuccessResponseData(map);
        }
    }

    public String makeJson(){
        //构造空间数据
        Map<String,Object> geometry = new HashMap<String,Object>();
        geometry.put("rings", "666666666666666666666666666666666666666666666666666666666666666666666666666666666666666");
//        geometry.put("y", "34");

        //构造属性数据
        Map<String,Object> attributes = new HashMap<String,Object>();
        attributes.put("description", "addpoint");

        //构造一个feature
        Map<String,Object> feature = new HashMap<String,Object>();
        feature.put("geometry", geometry);
        feature.put("attributes", attributes);

        List<Map<String,Object>> features = new ArrayList<Map<String,Object>>();
        features.add(feature);

        //利用jackson工具将对象转换成json字符串
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(features);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }

}



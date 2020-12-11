package cn.stylefeng.guns.modular.hhsys.controller.rest;

import cn.stylefeng.guns.modular.hhsys.mapper.WyhcMapper;
import cn.stylefeng.guns.modular.hhsys.utils.Convertor;
import cn.stylefeng.guns.modular.hhsys.utils.ResultInfo;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rest")
@Api(tags = "坐标")
public class FileRestController {

    @Autowired
    private WyhcMapper wyhcMapper;

    @ResponseBody
    @ApiOperation("坐标接口")
    @RequestMapping(value = "/convertCoordinate", method = RequestMethod.POST)
    public ResponseData convertCoordinate(@RequestBody Map<String,Object> patchReportMap) {

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

        //return resultInfo;
        return new SuccessResponseData(resultInfo);

    }



}

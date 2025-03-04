package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Wyhc;
import cn.stylefeng.guns.modular.hhsys.mapper.WyhcMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.WyhcParam;
import cn.stylefeng.guns.modular.hhsys.model.result.TBStatusResult;
import cn.stylefeng.guns.modular.hhsys.model.result.WyhcResult;
import cn.stylefeng.guns.modular.hhsys.service.WyhcService;
import cn.stylefeng.guns.modular.hhsys.utils.ModelConverterUtils;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-10-26
 */
@Service
public class WyhcServiceImpl extends ServiceImpl<WyhcMapper, Wyhc> implements WyhcService {

    @Autowired
    private WyhcMapper wyhcMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void add(WyhcParam param){
        Wyhc entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public int addPatchReport(Map<String,Object> patchReportMap){
//        if(sqlSessionFactory!=null) {
//            try {
//                SqlSession session = sqlSessionFactory.openSession();
//                int filePath = session.insert("addPatchReport",patchReportMap);
//                //session.close();
//                return filePath;
//            }catch(Exception ex) {
//                ex.printStackTrace();
//                return 0;
//            }
//        }
        try {
            int filePath = this.baseMapper.addPatchReport(patchReportMap);
            return filePath;
        }catch (Exception e){
            e.printStackTrace();
             return 0;
        }
    }

    /**
     *
     * 获取图斑列表
     * @param requestParamMap
     * @return
     */
    public List<WyhcResult> getPatchReportList(Map<String, Object> requestParamMap){
        return this.baseMapper.getPatchReportList(requestParamMap);
    }
    public List<WyhcResult> getPatchReportListBak(Map<String, Object> requestParamMap){
        return this.baseMapper.getPatchReportListBak(requestParamMap);
    }

    /**
     *
     *
     * 审核图斑
     * @param patchReportMap
     * @return
     */
    public int updatePatchReport(Map<String, Object> patchReportMap){
        return this.baseMapper.updatePatchReport(patchReportMap);
    }

    public List<WyhcResult> getPatchReportListCount(Map<String, Object> requestParamMap){
        return this.baseMapper.getPatchReportListCount(requestParamMap);
    }

    /**
     * 根据状态值分组返回 图斑id resourceid status
     * @param resourceId
     * @return
     */
    @Override
    public Map<String,  String> queryGroupByStatus(String resourceId) {
        QueryWrapper<Wyhc> wrapper = new QueryWrapper();
        wrapper.eq("resource_id",resourceId);
        List<Wyhc> wyhcs = this.baseMapper.selectList(wrapper);
            Map<String, String> collect = wyhcs.stream().collect(Collectors.groupingBy(Wyhc::getStatus, Collectors.mapping(Wyhc::getTbId, Collectors.joining(","))));
            if(StringUtils.isNotEmpty(collect.get("1"))){
                collect.put("one",collect.get("1"));
                collect.remove("1");
            }
            String tbIdsStr = collect.get("2");
            if(StringUtils.isNotEmpty(tbIdsStr)){
                tbIdsStr=tbIdsStr+","+collect.get("one");
                collect.put("one",tbIdsStr);
                collect.remove("2");
            }

            String tbIds0Str = collect.get("0");
            if(StringUtils.isNotEmpty(tbIds0Str)){
                collect.remove("0");
                collect.put("zero",tbIds0Str);
            }
        collect.put("all",wyhcs.stream().map(Wyhc::getTbId).collect(Collectors.joining(",")));

        return collect;
    }

    @Override
    public List<TBStatusResult> QueryAllByResourceId(String resourceId) {
        QueryWrapper<Wyhc> wrapper = new QueryWrapper();
        wrapper.eq("resource_id",resourceId);
        List<Wyhc> wyhcs = this.baseMapper.selectList(wrapper);
        return ModelConverterUtils.convert(wyhcs, TBStatusResult.class);
    }

    @Override
    public void delete(WyhcParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void  update(WyhcParam param){
        Wyhc oldEntity = getOldEntity(param);
        Wyhc newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public WyhcResult findBySpec(WyhcParam param){
        return null;
    }

    @Override
    public List<WyhcResult> findListBySpec(WyhcParam param){
        return null;
    }

    @Override
    public List<Map<String,Object>> findList(WyhcParam param){
        return this.baseMapper.customMapList(param);
    }

    @Override
    public LayuiPageInfo findPageBySpec(WyhcParam param){
        Page pageContext = getPageContext();
        try {
            IPage page = this.baseMapper.customPageList(pageContext, param);
            return LayuiPageFactory.createPageInfo(page);

        }catch (Exception ex){
            throw ex;
        }
    }

    private Serializable getKey(WyhcParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Wyhc getOldEntity(WyhcParam param) {
        return this.getById(getKey(param));
    }

    private Wyhc getEntity(WyhcParam param) {
        Wyhc entity = new Wyhc();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public List<Wyhc> getPatchReportInfoByTBID(Map<String, Object> requestParamMap) {
        return wyhcMapper.getPatchReportInfoByTBID(requestParamMap);
    }

//    @Override
//    public int updatePatchReport(Map<String, Object> patchReportMap) {
//        Wyhc wyhc = new Wyhc();
//        wyhc.setId(patchReportMap.get("ID").toString());
//        wyhc.setId(patchReportMap.get("TBID").toString());
//        wyhc.setUrl(patchReportMap.get("URL").toString());
//        wyhc.setStatus(patchReportMap.get("STATUS").toString());
//        wyhc.setDescription(patchReportMap.get("DESCRIPTION").toString());
//        wyhc.setSbr(patchReportMap.get("SBR").toString());
//        wyhc.setShr(patchReportMap.get("SHR").toString());
//        wyhc.setShyy(patchReportMap.get("SHYY").toString());
//        try {
//            wyhc.setSbsj(new SimpleDateFormat("yyyy-MM-dd ").parse(patchReportMap.get("SBSJ").toString()));
//            wyhc.setShsj(new SimpleDateFormat("yyyy-MM-dd ").parse(patchReportMap.get("SHSJ").toString()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        this.baseMapper.updateById(wyhc);
//        return this.baseMapper.updateById(wyhc);
//    }
}

package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.hutool.core.convert.Convert;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceMatedata;
import cn.stylefeng.guns.modular.hhsys.mapper.ServiceMatedataMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.ServiceMatedataParam;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
import cn.stylefeng.guns.modular.hhsys.service.ServiceMatedataService;
import cn.stylefeng.guns.modular.hhsys.utils.Convertor;
import cn.stylefeng.guns.modular.hhsys.utils.ModelConverterUtils;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-10-27
 */
@Service
public class ServiceMatedataServiceImpl extends ServiceImpl<ServiceMatedataMapper, ServiceMatedata> implements ServiceMatedataService {

    @Autowired
    private ServiceMatedataMapper serviceMatedataMapper;

    public List<ServiceMatedataResult> findServicesList(){
        return serviceMatedataMapper.customList();
    }

    @Override
    public void add(ServiceMatedataParam param){
        ServiceMatedata entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void add(ServiceMatedata serviceMatedata){
        String publishUrl = "/services/" + serviceMatedata.getResourceid() + "/arcgis/rest";
        serviceMatedata.setId(UUID.randomUUID().toString().replaceAll("-",""));
        serviceMatedata.setPublishUrl(publishUrl);
        try {
            this.save(serviceMatedata);
        }catch (Exception e){

        }
    }

    @Override
    public void delete(ServiceMatedataParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void deleteByResourceId(String resourceId){
        this.baseMapper.deleteByResourceId(resourceId);
    }

    @Override
    public void update(ServiceMatedataParam param){
        ServiceMatedata oldEntity = getOldEntity(param);
        ServiceMatedata newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ServiceMatedataResult findBySpec(ServiceMatedataParam param){
        return null;
    }

    @Override
    public ServiceMatedataResult findByResourceId(String resourceid){
        return this.serviceMatedataMapper.findByResourceId(resourceid);
    }


    @Override
    public List<ServiceMatedataResult> findListBySpec(ServiceMatedataParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ServiceMatedataParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public void convertDate() {
        List<ServiceMatedata> serviceMatedataList = this.baseMapper.selectList(new QueryWrapper());
        serviceMatedataList.forEach(e->{
            Convertor convertor = new Convertor(3, 120, 0, 500000, 0);
            double[] minjw = convertor.GKgetJW(Convert.toDouble(e.getWest()),Convert.toDouble(e.getSouth()));
            double[] maxjw = convertor.GKgetJW(Convert.toDouble(e.getEast()),Convert.toDouble(e.getNorth()));
            e.setWest(minjw[0]+"");
            e.setEast(minjw[1]+"");
            e.setNorth(maxjw[0]+"");
            e.setSouth(maxjw[1]+"");
            this.baseMapper.updateById(e);

        });
    }

    @Override
    public ServiceMatedataResult getByName(String name) {
        QueryWrapper<ServiceMatedata> wrapper = new QueryWrapper();
        wrapper.like("name",name);
        ServiceMatedata serviceMatedata = this.baseMapper.selectOne(wrapper);
        return ModelConverterUtils.convert(serviceMatedata,ServiceMatedataResult.class);
    }

    private Serializable getKey(ServiceMatedataParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ServiceMatedata getOldEntity(ServiceMatedataParam param) {
        return this.getById(getKey(param));
    }

    private ServiceMatedata getEntity(ServiceMatedataParam param) {
        ServiceMatedata entity = new ServiceMatedata();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

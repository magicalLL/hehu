package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityResource;
import cn.stylefeng.guns.modular.hhsys.mapper.CityResourceMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResourceParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResourceResult;
import cn.stylefeng.guns.modular.hhsys.service.CityResourceService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-11-03
 */
@Service
public class CityResourceServiceImpl extends ServiceImpl<CityResourceMapper, CityResource> implements CityResourceService {

    @Autowired
    private CityResourceMapper cityResourceMapper;

    @Override
    public void add(CityResourceParam param){
        CityResource entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CityResourceParam param){
        this.removeById(param.getId());
    }

    @Override
    public void update(CityResourceParam param){
        CityResource oldEntity = getOldEntity(param);
        CityResource newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CityResourceResult findBySpec(CityResourceParam param){
        return null;
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public CityResourceResult findByResourceId(String resourceid){
        return this.baseMapper.findByResourceId(resourceid);
    }

    @Override
    public Map<String,Object> findByResourceIdDetails(String resourceid){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("msg", "");
        CityResourceResult cityResourceResult = this.baseMapper.findByResourceId(resourceid);
        String serviceTypeId = cityResourceResult.getServiceTypeId();
        String resourceType = cityResourceResult.getResourcetype();
        String publishuserid = cityResourceResult.getPublishuserid();
        SqlSession session = sqlSessionFactory.openSession();
        if(cityResourceResult != null) {
            try {
                Connection conn = session.getConnection();
                Statement statement = conn.createStatement();
                ResultSet serviceRs = statement.executeQuery("select * from service_type where id = '" + serviceTypeId + "'");
                while (serviceRs.next()) {
                    cityResourceResult.setServiceTypeId(serviceRs.getString(2));
                }
                result.put("msg", result.get("msg").toString() + "成功获取serviceTypeId");
                ResultSet resourceRs = statement.executeQuery("select * from resource_type where id = '" +resourceType + "'");
                while (resourceRs.next()) {
                    cityResourceResult.setResourcetype(resourceRs.getString(2));
                }
                result.put("msg", result.get("msg").toString() + ",成功获取resourceType");
                ResultSet userRs = statement.executeQuery("select * from sys_user where user_id = '" +publishuserid + "'");
                while (userRs.next()) {
                    cityResourceResult.setPublishuserid(userRs.getString(3));
                }
                result.put("msg", result.get("msg").toString() + ",成功获取publishuserid");
                session.close();
                result.put("value", cityResourceResult);
            } catch (Exception e) {
                result.put("msg", result.get("msg").toString() + "***获取失败");
                result.put("value", false);
            }
        }else {
            result.put("msg", "resourceDetails获取失败");
            result.put("value", false);
        }
        return result;
    }

    @Override
    public List<CityResourceResult> findListBySpec(CityResourceParam param){
        String name = param.getName();
        Integer state = param.getState();
        String catalogDeptid = param.getCatalogDeptid();
        try {
            List<CityResourceResult> list = this.baseMapper.customList(name, state, catalogDeptid);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CityResourceResult> searchList(CityResourceParam param){
        String name = param.getName();
        Integer state = param.getState();
        String catalogDeptid = param.getCatalogDeptid();
        try {
            List<CityResourceResult> list = this.baseMapper.customlist(name, catalogDeptid);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CityResourceParam param){
        Page pageContext = getPageContext();
        String name = param.getName();
        Integer state = param.getState();
        String publishuserid = param.getPublishuserid();
        try {
            IPage page = this.baseMapper.customPageList(pageContext, name, state, publishuserid);
            return LayuiPageFactory.createPageInfo(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LayuiPageInfo findPageList(CityResourceParam param){
        Page pageContext = getPageContext();
        String name = param.getName();
        String publishuserid = param.getPublishuserid();
        try {
            IPage page = this.baseMapper.customPagelist(pageContext, name, publishuserid);
            return LayuiPageFactory.createPageInfo(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Serializable getKey(CityResourceParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private CityResource getOldEntity(CityResourceParam param) {
        return this.getById(getKey(param));
    }

    private CityResource getEntity(CityResourceParam param) {
        CityResource entity = new CityResource();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Rasterlayers;
import cn.stylefeng.guns.modular.hhsys.mapper.RasterlayersMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.RasterlayersParam;
import cn.stylefeng.guns.modular.hhsys.model.result.RasterlayersResult;
import  cn.stylefeng.guns.modular.hhsys.service.RasterlayersService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 影像对比信息模块 服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-11-06
 */
@Service
public class RasterlayersServiceImpl extends ServiceImpl<RasterlayersMapper, Rasterlayers> implements RasterlayersService {

    @Override
    public void add(RasterlayersParam param){
        Rasterlayers entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(RasterlayersParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(RasterlayersParam param){
        Rasterlayers oldEntity = getOldEntity(param);
        Rasterlayers newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RasterlayersResult findBySpec(RasterlayersParam param){
        return null;
    }

    @Override
    public List<RasterlayersResult> findListBySpec(RasterlayersParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(RasterlayersParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(RasterlayersParam param){
        return null;
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Rasterlayers getOldEntity(RasterlayersParam param) {
        return this.getById(getKey(param));
    }

    private Rasterlayers getEntity(RasterlayersParam param) {
        Rasterlayers entity = new Rasterlayers();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

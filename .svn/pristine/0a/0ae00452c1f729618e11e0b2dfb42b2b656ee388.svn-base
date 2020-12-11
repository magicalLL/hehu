package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Patchedreportid;
import cn.stylefeng.guns.modular.hhsys.mapper.PatchedreportidMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.PatchedreportidParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PatchedreportidResult;
import  cn.stylefeng.guns.modular.hhsys.service.PatchedreportidService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-11-06
 */
@Service
public class PatchedreportidServiceImpl extends ServiceImpl<PatchedreportidMapper, Patchedreportid> implements PatchedreportidService {

    @Override
    public void add(PatchedreportidParam param){
        Patchedreportid entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PatchedreportidParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PatchedreportidParam param){
        Patchedreportid oldEntity = getOldEntity(param);
        Patchedreportid newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PatchedreportidResult findBySpec(PatchedreportidParam param){
        return null;
    }

    @Override
    public List<PatchedreportidResult> findListBySpec(PatchedreportidParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PatchedreportidParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PatchedreportidParam param){
        return null;
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Patchedreportid getOldEntity(PatchedreportidParam param) {
        return this.getById(getKey(param));
    }

    private Patchedreportid getEntity(PatchedreportidParam param) {
        Patchedreportid entity = new Patchedreportid();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

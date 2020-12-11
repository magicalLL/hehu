package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Department;
import cn.stylefeng.guns.modular.hhsys.mapper.DepartmentMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.DepartmentParam;
import cn.stylefeng.guns.modular.hhsys.model.result.DepartmentResult;
import  cn.stylefeng.guns.modular.hhsys.service.DepartmentService;
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
 * @since 2020-10-28
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public void add(DepartmentParam param){
        Department entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DepartmentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(DepartmentParam param){
        Department oldEntity = getOldEntity(param);
        Department newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DepartmentResult findBySpec(DepartmentParam param){
        return null;
    }

    @Override
    public List<DepartmentResult> findListBySpec(DepartmentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DepartmentParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DepartmentParam param){
        return null;
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Department getOldEntity(DepartmentParam param) {
        return this.getById(getKey(param));
    }

    private Department getEntity(DepartmentParam param) {
        Department entity = new Department();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

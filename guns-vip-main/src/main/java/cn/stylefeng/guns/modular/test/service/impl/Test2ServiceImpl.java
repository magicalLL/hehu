package cn.stylefeng.guns.modular.test.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Test2;
import cn.stylefeng.guns.modular.test.mapper.Test2Mapper;
import cn.stylefeng.guns.modular.test.model.params.Test2Param;
import cn.stylefeng.guns.modular.test.model.result.Test2Result;
import  cn.stylefeng.guns.modular.test.service.Test2Service;
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
 * @since 2020-10-26
 */
@Service
public class Test2ServiceImpl extends ServiceImpl<Test2Mapper, Test2> implements Test2Service {

    @Override
    public void add(Test2Param param){
        Test2 entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(Test2Param param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(Test2Param param){
        Test2 oldEntity = getOldEntity(param);
        Test2 newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public Test2Result findBySpec(Test2Param param){
        return null;
    }

    @Override
    public List<Test2Result> findListBySpec(Test2Param param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(Test2Param param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(Test2Param param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Test2 getOldEntity(Test2Param param) {
        return this.getById(getKey(param));
    }

    private Test2 getEntity(Test2Param param) {
        Test2 entity = new Test2();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

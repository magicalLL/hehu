package cn.stylefeng.guns.modular.test.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Test;
import cn.stylefeng.guns.modular.test.mapper.TestMapper;
import cn.stylefeng.guns.modular.test.model.params.TestParam;
import cn.stylefeng.guns.modular.test.model.result.TestResult;
import  cn.stylefeng.guns.modular.test.service.TestService;
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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Override
    public void add(TestParam param){
        Test entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TestParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TestParam param){
        Test oldEntity = getOldEntity(param);
        Test newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TestResult findBySpec(TestParam param){
        return null;
    }

    @Override
    public List<TestResult> findListBySpec(TestParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TestParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(TestParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Test getOldEntity(TestParam param) {
        return this.getById(getKey(param));
    }

    private Test getEntity(TestParam param) {
        Test entity = new Test();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

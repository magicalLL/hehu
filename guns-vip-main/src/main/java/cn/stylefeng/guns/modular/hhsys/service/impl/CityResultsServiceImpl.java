package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityResults;
import cn.stylefeng.guns.modular.hhsys.mapper.CityResultsMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResultsParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResultsResult;
import cn.stylefeng.guns.modular.hhsys.service.CityResultsService;
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
 * @since 2020-11-13
 */
@Service
public class CityResultsServiceImpl extends ServiceImpl<CityResultsMapper, CityResults> implements CityResultsService {

    @Override
    public void add(CityResultsParam param){
        CityResults entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(CityResultsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CityResultsParam param){
        CityResults oldEntity = getOldEntity(param);
        CityResults newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CityResultsResult findBySpec(CityResultsParam param){
        return null;
    }
    @Override
    public CityResults findById(String id){
        CityResults cityResults = this.baseMapper.selectById(id);
        return cityResults;
    }

    @Override
    public List<CityResultsResult> findListBySpec(CityResultsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CityResultsParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CityResultsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private CityResults getOldEntity(CityResultsParam param) {
        return this.getById(getKey(param));
    }

    private CityResults getEntity(CityResultsParam param) {
        CityResults entity = new CityResults();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

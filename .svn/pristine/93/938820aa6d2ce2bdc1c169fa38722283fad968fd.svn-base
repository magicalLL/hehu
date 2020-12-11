package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Urls;
import cn.stylefeng.guns.modular.hhsys.mapper.UrlsMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.UrlsParam;
import cn.stylefeng.guns.modular.hhsys.model.result.UrlsResult;
import  cn.stylefeng.guns.modular.hhsys.service.UrlsService;
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
 * @since 2020-11-17
 */
@Service
public class UrlsServiceImpl extends ServiceImpl<UrlsMapper, Urls> implements UrlsService {

    @Override
    public void add(UrlsParam param){
        Urls entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(UrlsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(UrlsParam param){
        Urls oldEntity = getOldEntity(param);
        Urls newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public UrlsResult findBySpec(UrlsParam param){
        return null;
    }

    @Override
    public List<UrlsResult> findListBySpec(UrlsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(UrlsParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(UrlsParam param){
        return null;
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Urls getOldEntity(UrlsParam param) {
        return this.getById(getKey(param));
    }

    private Urls getEntity(UrlsParam param) {
        Urls entity = new Urls();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

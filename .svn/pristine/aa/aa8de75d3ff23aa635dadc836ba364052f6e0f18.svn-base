package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityFile;
import cn.stylefeng.guns.modular.hhsys.mapper.CityFileMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.CityFileParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityFileResult;
import  cn.stylefeng.guns.modular.hhsys.service.CityFileService;
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
public class CityFileServiceImpl extends ServiceImpl<CityFileMapper, CityFile> implements CityFileService {

    @Override
    public void add(CityFileParam param){
        CityFile entity = getEntity(param);
        this.save(entity);
    }
    @Override
    public boolean addFile(CityFile param){
        return this.save(param);
    }

    @Override
    public void delete(CityFileParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(CityFileParam param){
        CityFile oldEntity = getOldEntity(param);
        CityFile newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public CityFile findBySpec(CityFileParam param){
        CityFile cityFile = this.baseMapper.selectById(param.getId());
        return cityFile;
    }

    @Override
    public CityFile findById(String id){
        CityFile cityFile = this.baseMapper.selectById(id);
        return cityFile;
    }

    @Override
    public List<CityFileResult> findListBySpec(CityFileParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(CityFileParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }
    @Override
    public LayuiPageInfo findPageBySpecCopy(CityFileParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageListCopy(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CityFileParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private CityFile getOldEntity(CityFileParam param) {
        return this.getById(getKey(param));
    }

    private CityFile getEntity(CityFileParam param) {
        CityFile entity = new CityFile();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

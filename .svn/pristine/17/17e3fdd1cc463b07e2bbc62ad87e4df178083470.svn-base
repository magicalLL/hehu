package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Bzfield;
import cn.stylefeng.guns.modular.hhsys.mapper.BzfieldMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.BzfieldParam;
import cn.stylefeng.guns.modular.hhsys.model.result.BzfieldResult;
import cn.stylefeng.guns.modular.hhsys.service.BzfieldService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-10-28
 */
@Service
public class BzfieldServiceImpl extends ServiceImpl<BzfieldMapper, Bzfield> implements BzfieldService {

    @Override
    public void add(BzfieldParam param){
        Bzfield entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void addBzfield(Bzfield param){
        this.save(param);
    }

    @Override
    public Map<String,Object> deleteByTableid(String tableid){
        Map<String,Object> result = new HashMap<>();
        try {
            this.baseMapper.deleteByTableid(tableid);
            result.put("flag",true);
        }catch (Exception e){
            result.put("flag",false);
            result.put("msg","字段删除失败");
        }
        return result;
    }

    @Override
    public Map<String,Object> insertBzfield(Map<String, Object> param){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            this.baseMapper.insertBzfield(param);
            result.put("flag",true);
            result.put("detail","插入成功");
        }catch (Exception e){
            result.put("flag",false);
            result.put("detail","插入失败");
        }
        return result;
    }


    @Override
    public void delete(BzfieldParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BzfieldParam param){
        Bzfield oldEntity = getOldEntity(param);
        Bzfield newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BzfieldResult findBySpec(BzfieldParam param){
        return null;
    }

    @Override
    public List<BzfieldResult> findListBySpec(BzfieldParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageByTableid(String tableid){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageListByTableid(pageContext, tableid);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public LayuiPageInfo findPageBySpec(BzfieldParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BzfieldParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Bzfield getOldEntity(BzfieldParam param) {
        return this.getById(getKey(param));
    }

    private Bzfield getEntity(BzfieldParam param) {
        Bzfield entity = new Bzfield();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

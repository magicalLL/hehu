package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.PointInfo;
import cn.stylefeng.guns.modular.hhsys.mapper.PointInfoMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.PointInfoParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PointInfoResult;
import cn.stylefeng.guns.modular.hhsys.service.PointInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 变化点位管理 服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-11-24
 */
@Service
public class PointInfoServiceImpl extends ServiceImpl<PointInfoMapper, PointInfo> implements PointInfoService {

    @Override
    public boolean add(PointInfoParam param){
        PointInfo entity = getEntity(param);
        try {
            this.save(entity);
            System.out.println("================================插入成功==============================");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addPoint(Map<String,Object> pointInfo){
        try {
            this.baseMapper.addPoint(pointInfo);
            System.out.println("================================插入成功==============================");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void delete(PointInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PointInfoParam param){
        PointInfo oldEntity = getOldEntity(param);
        PointInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PointInfoResult findBySpec(PointInfoParam param){
        return null;
    }

    @Override
    public List<PointInfoResult> findListBySpec(PointInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PointInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public LayuiPageInfo findPageByDept(String dept){
        Page pageContext = getPageContext();
        try {
            IPage page = this.baseMapper.customPageListByDept(pageContext, dept);
            return LayuiPageFactory.createPageInfo(page);
        }catch (Exception e){
            return null;
        }
    }

    private Serializable getKey(PointInfoParam param){
        return param.getObjectid();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PointInfo getOldEntity(PointInfoParam param) {
        return this.getById(getKey(param));
    }

    private PointInfo getEntity(PointInfoParam param) {
        PointInfo entity = new PointInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

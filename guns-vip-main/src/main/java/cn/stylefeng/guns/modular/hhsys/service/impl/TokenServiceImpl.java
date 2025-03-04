package cn.stylefeng.guns.modular.hhsys.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Token;
import cn.stylefeng.guns.modular.hhsys.mapper.TokenMapper;
import cn.stylefeng.guns.modular.hhsys.model.params.TokenParam;
import cn.stylefeng.guns.modular.hhsys.model.result.TokenResult;
import cn.stylefeng.guns.modular.hhsys.service.TokenService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-11-03
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Override
    public void add(TokenParam param){
        Token entity = getEntity(param);
        this.save(entity);
    }

    public void add(String resourceId, String userId, String deptid){
        TokenParam param = new TokenParam();
        String id = UUID.randomUUID().toString().replaceAll("-","");
        param.setId(id);
        param.setUserid(userId);
        param.setResourceid(resourceId);
        param.setDeptid(deptid);
        param.setToken(id);
        add(param);
    }

    @Override
    public void delete(TokenParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void deleteByResourceId(String resourceId){
        this.baseMapper.removeByResourceId(resourceId);
    }

    @Override
    public void update(TokenParam param){
        Token oldEntity = getOldEntity(param);
        Token newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TokenResult findBySpec(TokenParam param){
        return null;
    }

    @Override
    public List<TokenResult> findListByUserid(Long userid){
        return this.baseMapper.customPageListByUserid(userid);
    }

    @Override
    public List<TokenResult> findListByDeptid(String deptid){
        return this.baseMapper.customPageListByDeptid(deptid);
    }

    @Override
    public List<TokenResult> getListByToken(String token){
        return this.baseMapper.customPageListByToken(token);
    }

    @Override
    public TokenResult findByIds(Map<String,Object> params){
        String resourceid = params.get("resourceId").toString();
        String deptid = params.get("deptid").toString();
//        try {
//            TokenResult ts = this.baseMapper.findByIds(resourceid, userid);
//            String id = ts.getId();
//        }catch (Exception e){
//            Map<String,Object> result = new HashMap<>();
//            result.put("flag",false);
//        }

        return this.baseMapper.findByIds(resourceid,deptid);
    }

    @Override
    public List<TokenResult> findListBySpec(TokenParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TokenParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(TokenParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Token getOldEntity(TokenParam param) {
        return this.getById(getKey(param));
    }

    private Token getEntity(TokenParam param) {
        Token entity = new Token();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Token;
import cn.stylefeng.guns.modular.hhsys.model.params.TokenParam;
import cn.stylefeng.guns.modular.hhsys.model.result.TokenResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-03
 */
public interface TokenService extends IService<Token> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-03
     */
    void add(TokenParam param);
    void add(String resourceId, String userId,String deptid);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-03
     */
    void delete(TokenParam param);
    void deleteByResourceId(String resourceId);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-03
     */
    void update(TokenParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-03
     */
    TokenResult findBySpec(TokenParam param);

    TokenResult findByIds(Map<String,Object> params);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-03
     */
    List<TokenResult> findListBySpec(TokenParam param);
    List<TokenResult> findListByUserid(Long userid);
    List<TokenResult> findListByDeptid(String deptid);
    List<TokenResult> getListByToken(String token);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-03
     */
     LayuiPageInfo findPageBySpec(TokenParam param);

}

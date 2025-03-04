package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Token;
import cn.stylefeng.guns.modular.hhsys.model.params.TokenParam;
import cn.stylefeng.guns.modular.hhsys.model.result.TokenResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-11-03
 */
public interface TokenMapper extends BaseMapper<Token> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    List<TokenResult> customList(@Param("paramCondition") TokenParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TokenParam paramCondition);

    TokenResult findByIds(@Param("resourceid") String resourceid, @Param("deptid") String deptid);

    int removeByResourceId(@Param("resourceId") String resourceId);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    Page<TokenResult> customPageList(@Param("page") Page page, @Param("paramCondition") TokenParam paramCondition);
    List<TokenResult> customPageListByUserid(@Param("userid") Long userid);
    List<TokenResult> customPageListByDeptid(@Param("deptid") String deptid);
    List<TokenResult> customPageListByToken(@Param("token") String token);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TokenParam paramCondition);

}

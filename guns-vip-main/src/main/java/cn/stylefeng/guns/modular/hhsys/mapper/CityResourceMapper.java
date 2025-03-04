package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.CityResource;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResourceParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResourceResult;
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
public interface CityResourceMapper extends BaseMapper<CityResource> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-03
     */
//    List<CityResourceResult> customList(@Param("paramCondition") CityResourceParam paramCondition);

    List<CityResourceResult> customList( @Param("name") String name, @Param("state") Integer state, @Param("catalogDeptid") String catalogDeptid);
    List<CityResourceResult> customlist( @Param("name") String name, @Param("catalogDeptid") String catalogDeptid);

    CityResourceResult findByResourceId(@Param("id") String id);
    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CityResourceParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    Page<CityResourceResult> customPageList(@Param("page") Page page, @Param("name") String name, @Param("state") Integer state, @Param("publishuserid") String publishuserid);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    Page<CityResourceResult> customPagelist(@Param("page") Page page, @Param("name") String name, @Param("publishuserid")String publishuserid);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-03
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CityResourceParam paramCondition);

}

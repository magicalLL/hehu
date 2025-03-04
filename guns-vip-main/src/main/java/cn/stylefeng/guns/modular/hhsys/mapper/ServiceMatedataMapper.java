package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.ServiceMatedata;
import cn.stylefeng.guns.modular.hhsys.model.params.ServiceMatedataParam;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
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
 * @since 2020-10-27
 */
public interface ServiceMatedataMapper extends BaseMapper<ServiceMatedata> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-27
     */
//    List<ServiceMatedataResult> customList(@Param("paramCondition") ServiceMatedataParam paramCondition);

    List<ServiceMatedataResult> customList();

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-27
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ServiceMatedataParam paramCondition);

    ServiceMatedataResult findByResourceId(@Param("resourceid") String resourceid);

    int deleteByResourceId(@Param("resourceId") String resourceId);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-27
     */
    Page<ServiceMatedataResult> customPageList(@Param("page") Page page, @Param("paramCondition") ServiceMatedataParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-27
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ServiceMatedataParam paramCondition);

}

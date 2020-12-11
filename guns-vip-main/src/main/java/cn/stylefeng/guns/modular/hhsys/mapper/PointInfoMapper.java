package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.PointInfo;
import cn.stylefeng.guns.modular.hhsys.model.params.PointInfoParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PointInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 变化点位管理 Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-11-24
 */
public interface PointInfoMapper extends BaseMapper<PointInfo> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-24
     */
    List<PointInfoResult> customList(@Param("paramCondition") PointInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-24
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PointInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-24
     */
    Page<PointInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") PointInfoParam paramCondition);
    Page<PointInfoResult> customPageListByDept(@Param("page") Page page, @Param("dept") String dept);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-24
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PointInfoParam paramCondition);

    /**
     * 插入表
     *
     * @author lls
     * @Date 2020-11-24
     */
    void addPoint(Map<String, Object> pointInfo);
}

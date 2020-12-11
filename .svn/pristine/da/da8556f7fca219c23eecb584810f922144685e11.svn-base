package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Rasterlayers;
import cn.stylefeng.guns.modular.hhsys.model.params.RasterlayersParam;
import cn.stylefeng.guns.modular.hhsys.model.result.RasterlayersResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 影像对比信息模块 Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-11-06
 */
public interface RasterlayersMapper extends BaseMapper<Rasterlayers> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    List<RasterlayersResult> customList(@Param("paramCondition") RasterlayersParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") RasterlayersParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    Page<RasterlayersResult> customPageList(@Param("page") Page page, @Param("paramCondition") RasterlayersParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") RasterlayersParam paramCondition);

}

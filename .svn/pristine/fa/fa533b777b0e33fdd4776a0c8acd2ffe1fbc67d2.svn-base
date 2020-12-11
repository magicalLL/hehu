package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Patchedreportid;
import cn.stylefeng.guns.modular.hhsys.model.params.PatchedreportidParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PatchedreportidResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @since 2020-11-06
 */
public interface PatchedreportidMapper extends BaseMapper<Patchedreportid> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    List<PatchedreportidResult> customList(@Param("paramCondition") PatchedreportidParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PatchedreportidParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    Page<PatchedreportidResult> customPageList(@Param("page") Page page, @Param("paramCondition") PatchedreportidParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-06
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PatchedreportidParam paramCondition);

}

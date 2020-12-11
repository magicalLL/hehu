package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Bzfield;
import cn.stylefeng.guns.modular.hhsys.model.params.BzfieldParam;
import cn.stylefeng.guns.modular.hhsys.model.result.BzfieldResult;
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
 * @since 2020-10-28
 */
public interface BzfieldMapper extends BaseMapper<Bzfield> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<BzfieldResult> customList(@Param("paramCondition") BzfieldParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") BzfieldParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<BzfieldResult> customPageList(@Param("page") Page page, @Param("paramCondition") BzfieldParam paramCondition);

    /**
     * 根据tableid获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<BzfieldResult> customPageListByTableid(@Param("page") Page page, @Param("tableid") String tableid);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") BzfieldParam paramCondition);

    int insertBzfield(Map<String, Object> param);

    int deleteByTableid(String tableid);
}

package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.CityResults;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResultsParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResultsResult;
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
 * @since 2020-11-13
 */
public interface CityResultsMapper extends BaseMapper<CityResults> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    List<CityResultsResult> customList(@Param("paramCondition") CityResultsParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CityResultsParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    Page<CityResultsResult> customPageList(@Param("page") Page page, @Param("paramCondition") CityResultsParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CityResultsParam paramCondition);

}

package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.CityFile;
import cn.stylefeng.guns.modular.hhsys.model.params.CityFileParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityFileResult;
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
 * @since 2020-11-13
 */
public interface CityFileMapper extends BaseMapper<CityFile> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    List<CityFileResult> customList(@Param("paramCondition") CityFileParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") CityFileParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    Page<CityFileResult> customPageList(@Param("page") Page page, @Param("paramCondition") CityFileParam paramCondition);
    Page<CityFileResult> customPageListCopy(@Param("page") Page page, @Param("paramCondition") CityFileParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-13
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") CityFileParam paramCondition);

}

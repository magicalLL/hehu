package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Urls;
import cn.stylefeng.guns.modular.hhsys.model.params.UrlsParam;
import cn.stylefeng.guns.modular.hhsys.model.result.UrlsResult;
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
 * @since 2020-11-17
 */
public interface UrlsMapper extends BaseMapper<Urls> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-11-17
     */
    List<UrlsResult> customList(@Param("paramCondition") UrlsParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-11-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") UrlsParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-11-17
     */
    Page<UrlsResult> customPageList(@Param("page") Page page, @Param("paramCondition") UrlsParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-11-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") UrlsParam paramCondition);

}

package cn.stylefeng.guns.modular.test.mapper;

import cn.stylefeng.guns.modular.test.entity.Test2;
import cn.stylefeng.guns.modular.test.model.params.Test2Param;
import cn.stylefeng.guns.modular.test.model.result.Test2Result;
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
 * @since 2020-10-26
 */
public interface Test2Mapper extends BaseMapper<Test2> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<Test2Result> customList(@Param("paramCondition") Test2Param paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") Test2Param paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    Page<Test2Result> customPageList(@Param("page") Page page, @Param("paramCondition") Test2Param paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") Test2Param paramCondition);

}

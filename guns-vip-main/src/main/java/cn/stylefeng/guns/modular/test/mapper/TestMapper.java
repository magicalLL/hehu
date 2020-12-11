package cn.stylefeng.guns.modular.test.mapper;

import cn.stylefeng.guns.modular.test.entity.Test;
import cn.stylefeng.guns.modular.test.model.params.TestParam;
import cn.stylefeng.guns.modular.test.model.result.TestResult;
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
public interface TestMapper extends BaseMapper<Test> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<TestResult> customList(@Param("paramCondition") TestParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TestParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    Page<TestResult> customPageList(@Param("page") Page page, @Param("paramCondition") TestParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TestParam paramCondition);

}

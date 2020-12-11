package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Department;
import cn.stylefeng.guns.modular.hhsys.model.params.DepartmentParam;
import cn.stylefeng.guns.modular.hhsys.model.result.DepartmentResult;
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
 * @since 2020-10-28
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<DepartmentResult> customList(@Param("paramCondition") DepartmentParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") DepartmentParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<DepartmentResult> customPageList(@Param("page") Page page, @Param("paramCondition") DepartmentParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") DepartmentParam paramCondition);

}

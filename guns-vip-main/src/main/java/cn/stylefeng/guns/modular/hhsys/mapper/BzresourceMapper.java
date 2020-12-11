package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Bzresource;
import cn.stylefeng.guns.modular.hhsys.model.params.BzresourceParam;
import cn.stylefeng.guns.modular.hhsys.model.result.BzresourceResult;
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
public interface BzresourceMapper extends BaseMapper<Bzresource> {

    /**
     * 根据tablename查询单条信息
     *
     * @author lls
     * @Date 2020-10-28
     */
    BzresourceResult findByTableName(@Param("tablename") String tablename);

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<BzresourceResult> customList(@Param("paramCondition") BzresourceParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") BzresourceParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<BzresourceResult> customPageList(@Param("page") Page page, @Param("paramCondition") BzresourceParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<BzresourceResult> customPageListById(@Param("page") Page page, @Param("userid") String userid);


    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-28
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") BzresourceParam paramCondition);

    /**
     * 根据tablename获取表数据
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<Map<String, Object>> getDatas(String tablename);

    /**
     * 添加单条表数据
     *
     * @author lls
     * @Date 2020-10-28
     */
    int addData(Map<String,Object> params);

    /**
     * 删除单条表数据
     *
     * @author lls
     * @Date 2020-10-28
     */
    int deleteData(@Param("tableName") String tableName, @Param("id") String id);

    /**
     * 删除标注表
     *
     * @author lls
     * @Date 2020-10-28
     */
    int deleteMarkerTable(String tableName);

    /**
     * 新建标注表
     *
     * @author lls
     * @Date 2020-10-28
     */
    int createMarkerTable(Map<String,Object> params);

    int insertBzresource(Map<String, Object> param);

    Map<String,Object> selectByTablename(String tablename);
}

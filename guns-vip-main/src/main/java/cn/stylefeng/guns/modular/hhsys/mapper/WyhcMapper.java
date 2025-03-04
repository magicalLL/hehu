package cn.stylefeng.guns.modular.hhsys.mapper;

import cn.stylefeng.guns.modular.hhsys.entity.Wyhc;
import cn.stylefeng.guns.modular.hhsys.model.params.WyhcParam;
import cn.stylefeng.guns.modular.hhsys.model.result.WyhcResult;
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
 * @since 2020-10-26
 */
public interface WyhcMapper extends BaseMapper<Wyhc> {

    /**
     * 获取列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<WyhcResult> customList(@Param("paramCondition") WyhcParam paramCondition);

    /**
     * 获取map列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") WyhcParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    Page<WyhcResult> customPageList(@Param("page") Page page, @Param("paramCondition") WyhcParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author lls
     * @Date 2020-10-26
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") WyhcParam paramCondition);

    /**
     *
     *
     * 根据图斑参数查询图斑信息
     *
     * @param requestParamMap
     * @return
     */
    List<Wyhc> getPatchReportInfoByTBID(Map<String, Object> requestParamMap);

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-26
     */
    int addPatchReport(Map<String,Object> patchReportMap);
    /**
     *
     * 获取图斑列表
     * @param requestParamMap
     * @return
     */
    List<WyhcResult> getPatchReportList(Map<String, Object> requestParamMap);
    List<WyhcResult> getPatchReportListBak(Map<String, Object> requestParamMap);

    /**
     *
     *
     * 审核图斑
     * @param patchReportMap
     * @return
     */
    int updatePatchReport(Map<String, Object> patchReportMap);

    List<WyhcResult> getPatchReportListCount(Map<String, Object> requestParamMap);

}

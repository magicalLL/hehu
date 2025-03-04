package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Wyhc;
import cn.stylefeng.guns.modular.hhsys.model.params.WyhcParam;
import cn.stylefeng.guns.modular.hhsys.model.result.TBStatusResult;
import cn.stylefeng.guns.modular.hhsys.model.result.WyhcResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-10-26
 */
public interface WyhcService extends IService<Wyhc> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-26
     */
    void add(WyhcParam param);

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-26
     */
    int addPatchReport(Map<String,Object> patchReportMap);
    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-26
     */
    void delete(WyhcParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-26
     */
    void update(WyhcParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
    WyhcResult findBySpec(WyhcParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<WyhcResult> findListBySpec(WyhcParam param);

    List<Map<String,Object>> findList(WyhcParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
     LayuiPageInfo findPageBySpec(WyhcParam param);

    /**
     * 获取图班信息
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<Wyhc> getPatchReportInfoByTBID(Map<String, Object> requestParamMap);

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


    /**
     * 根据状态值分组返回 图斑id resourceid status
     * @param resourceId
     * @return
     */
    Map<String,String> queryGroupByStatus(String resourceId);

    /**
     * 根据resourceId获取所有
     * @param resourceId
     * @return
     */
    List<TBStatusResult> QueryAllByResourceId(String resourceId);

}

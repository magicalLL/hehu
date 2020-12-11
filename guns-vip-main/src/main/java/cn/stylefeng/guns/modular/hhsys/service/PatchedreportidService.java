package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Patchedreportid;
import cn.stylefeng.guns.modular.hhsys.model.params.PatchedreportidParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PatchedreportidResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-06
 */
public interface PatchedreportidService extends IService<Patchedreportid> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-06
     */
    void add(PatchedreportidParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-06
     */
    void delete(PatchedreportidParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-06
     */
    void update(PatchedreportidParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-06
     */
    PatchedreportidResult findBySpec(PatchedreportidParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-06
     */
    List<PatchedreportidResult> findListBySpec(PatchedreportidParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-06
     */
     LayuiPageInfo findPageBySpec(PatchedreportidParam param);

}

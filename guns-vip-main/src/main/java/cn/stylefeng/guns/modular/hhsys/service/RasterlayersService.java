package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Rasterlayers;
import cn.stylefeng.guns.modular.hhsys.model.params.RasterlayersParam;
import cn.stylefeng.guns.modular.hhsys.model.result.RasterlayersResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 影像对比信息模块 服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-06
 */
public interface RasterlayersService extends IService<Rasterlayers> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-06
     */
    void add(RasterlayersParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-06
     */
    void delete(RasterlayersParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-06
     */
    void update(RasterlayersParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-06
     */
    RasterlayersResult findBySpec(RasterlayersParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-06
     */
    List<RasterlayersResult> findListBySpec(RasterlayersParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-06
     */
     LayuiPageInfo findPageBySpec(RasterlayersParam param);

}

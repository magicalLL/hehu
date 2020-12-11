package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.PointInfo;
import cn.stylefeng.guns.modular.hhsys.model.params.PointInfoParam;
import cn.stylefeng.guns.modular.hhsys.model.result.PointInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 变化点位管理 服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-24
 */
public interface PointInfoService extends IService<PointInfo> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-24
     */
    boolean add(PointInfoParam param);

    boolean addPoint(Map<String,Object> pointInfo);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-24
     */
    void delete(PointInfoParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-24
     */
    void update(PointInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-24
     */
    PointInfoResult findBySpec(PointInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-24
     */
    List<PointInfoResult> findListBySpec(PointInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-24
     */
     LayuiPageInfo findPageBySpec(PointInfoParam param);

     LayuiPageInfo findPageByDept(String dept);

}

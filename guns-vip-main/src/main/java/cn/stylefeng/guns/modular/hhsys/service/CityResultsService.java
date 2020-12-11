package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityResults;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResultsParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResultsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-13
 */
public interface CityResultsService extends IService<CityResults> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-13
     */
    void add(CityResultsParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-13
     */
    void delete(CityResultsParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-13
     */
    void update(CityResultsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-13
     */
    CityResultsResult findBySpec(CityResultsParam param);
    CityResults findById(String id);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-13
     */
    List<CityResultsResult> findListBySpec(CityResultsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-13
     */
     LayuiPageInfo findPageBySpec(CityResultsParam param);

}

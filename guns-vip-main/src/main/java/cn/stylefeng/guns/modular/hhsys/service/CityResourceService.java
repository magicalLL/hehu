package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityResource;
import cn.stylefeng.guns.modular.hhsys.model.params.CityResourceParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityResourceResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-03
 */
public interface CityResourceService extends IService<CityResource> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-03
     */
    void add(CityResourceParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-03
     */
    void delete(CityResourceParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-03
     */
    void update(CityResourceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-03
     */
    CityResourceResult findBySpec(CityResourceParam param);
    Map<String,Object> findByResourceIdDetails(String resourceid);
    CityResourceResult findByResourceId(String resourceid);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-03
     */
    List<CityResourceResult> findListBySpec(CityResourceParam param);
    List<CityResourceResult> searchList(CityResourceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-03
     */
     LayuiPageInfo findPageBySpec(CityResourceParam param);
    LayuiPageInfo findPageList(CityResourceParam param);

}

package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Bzfield;
import cn.stylefeng.guns.modular.hhsys.model.params.BzfieldParam;
import cn.stylefeng.guns.modular.hhsys.model.result.BzfieldResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-10-28
 */
public interface BzfieldService extends IService<Bzfield> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-28
     */
    void add(BzfieldParam param);
    void addBzfield(Bzfield param);

    Map<String,Object> insertBzfield(Map<String, Object> param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-28
     */
    void delete(BzfieldParam param);

    Map<String,Object> deleteByTableid(String tableid);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-28
     */
    void update(BzfieldParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    BzfieldResult findBySpec(BzfieldParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<BzfieldResult> findListBySpec(BzfieldParam param);

    /**
     * 根据tableid查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    LayuiPageInfo findPageByTableid(String tableid);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
     LayuiPageInfo findPageBySpec(BzfieldParam param);

}

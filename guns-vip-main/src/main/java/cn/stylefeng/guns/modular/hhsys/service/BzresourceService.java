package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Bzresource;
import cn.stylefeng.guns.modular.hhsys.model.params.BzresourceParam;
import cn.stylefeng.guns.modular.hhsys.model.result.BzresourceResult;
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
public interface BzresourceService extends IService<Bzresource> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-28
     */
    void add(BzresourceParam param);
    void addtBzresource(Bzresource param);

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-28
     */
    Map<String,Object> insertBzresource(Map<String,Object> param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-28
     */
    void delete(BzresourceParam param);

    Map<String,Object> deleteById(String id);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-28
     */
    void update(BzresourceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    BzresourceResult findBySpec(BzresourceParam param);

    /**
     * 根据tablename查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    BzresourceResult findByTablename(String tablename);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<BzresourceResult> findListBySpec(BzresourceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
     LayuiPageInfo findPageBySpec(BzresourceParam param);
    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    LayuiPageInfo findPageByUserid(String userid);

    /**
     * 根据tablename查询数据列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     * @return
     */
    List<Map<String, Object>> getDatas(String tablename);

    /**
     * 根据tablename添加单条数据
     *
     * @author lls
     * @Date 2020-10-28
     * @return
     */
    Map<String, Object> addDatas(String tableName,Map<String,Object> data);
    Map<String, Object> addDatas(Map<String,Object> data);

    /**
     * 根据tablename，ID删除单条表数据
     *
     * @author lls
     * @Date 2020-10-28
     * @return
     */
    Map<String, Object> deleteData(String tableName, String ID);

    /**
     * 删除标注表
     *
     * @author lls
     * @Date 2020-10-28
     * @return
     */
    Map<String, Object> deleteMarkerTable(String tableName);

    /**
     * 删除标注表
     *
     * @author lls
     * @Date 2020-10-28
     * @return
     */
    Map<String, Object> createMarkerTable(Map<String,Object> params);
}

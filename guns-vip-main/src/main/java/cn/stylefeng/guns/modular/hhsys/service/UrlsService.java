package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Urls;
import cn.stylefeng.guns.modular.hhsys.model.params.UrlsParam;
import cn.stylefeng.guns.modular.hhsys.model.result.UrlsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-11-17
 */
public interface UrlsService extends IService<Urls> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-17
     */
    void add(UrlsParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-17
     */
    void delete(UrlsParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-17
     */
    void update(UrlsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-17
     */
    UrlsResult findBySpec(UrlsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-17
     */
    List<UrlsResult> findListBySpec(UrlsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-17
     */
     LayuiPageInfo findPageBySpec(UrlsParam param);

}

package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceMatedata;
import cn.stylefeng.guns.modular.hhsys.model.params.ServiceMatedataParam;
import cn.stylefeng.guns.modular.hhsys.model.result.ServiceMatedataResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-10-27
 */
public interface ServiceMatedataService extends IService<ServiceMatedata> {


    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-27
     */
    void add(ServiceMatedataParam param);
    void add(ServiceMatedata param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-27
     */
    void delete(ServiceMatedataParam param);
    void deleteByResourceId(String resourceId);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-27
     */
         void update(ServiceMatedataParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-27
     */
    ServiceMatedataResult findBySpec(ServiceMatedataParam param);
    ServiceMatedataResult findByResourceId(String resourceid);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-27
     */
    List<ServiceMatedataResult> findListBySpec(ServiceMatedataParam param);

    List<ServiceMatedataResult> findServicesList();

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-27
     */
     LayuiPageInfo findPageBySpec(ServiceMatedataParam param);

     void convertDate();

    /**
     * 根据名称查询数据
     * @param name
     * @return
     */
    ServiceMatedataResult getByName(String name);

}

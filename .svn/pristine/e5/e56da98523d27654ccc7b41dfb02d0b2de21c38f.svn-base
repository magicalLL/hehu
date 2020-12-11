package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.Department;
import cn.stylefeng.guns.modular.hhsys.model.params.DepartmentParam;
import cn.stylefeng.guns.modular.hhsys.model.result.DepartmentResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-10-28
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-28
     */
    void add(DepartmentParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-28
     */
    void delete(DepartmentParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-28
     */
    void update(DepartmentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    DepartmentResult findBySpec(DepartmentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
    List<DepartmentResult> findListBySpec(DepartmentParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-28
     */
     LayuiPageInfo findPageBySpec(DepartmentParam param);

}

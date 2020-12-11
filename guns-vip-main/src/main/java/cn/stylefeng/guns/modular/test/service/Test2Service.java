package cn.stylefeng.guns.modular.test.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Test2;
import cn.stylefeng.guns.modular.test.model.params.Test2Param;
import cn.stylefeng.guns.modular.test.model.result.Test2Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-10-26
 */
public interface Test2Service extends IService<Test2> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-26
     */
    void add(Test2Param param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-26
     */
    void delete(Test2Param param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-26
     */
    void update(Test2Param param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
    Test2Result findBySpec(Test2Param param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<Test2Result> findListBySpec(Test2Param param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
     LayuiPageInfo findPageBySpec(Test2Param param);

}

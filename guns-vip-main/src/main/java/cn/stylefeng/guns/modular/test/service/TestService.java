package cn.stylefeng.guns.modular.test.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Test;
import cn.stylefeng.guns.modular.test.model.params.TestParam;
import cn.stylefeng.guns.modular.test.model.result.TestResult;
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
public interface TestService extends IService<Test> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-10-26
     */
    void add(TestParam param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-10-26
     */
    void delete(TestParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-10-26
     */
    void update(TestParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
    TestResult findBySpec(TestParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
    List<TestResult> findListBySpec(TestParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-10-26
     */
     LayuiPageInfo findPageBySpec(TestParam param);

}

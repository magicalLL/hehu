package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.hhsys.entity.CityFile;
import cn.stylefeng.guns.modular.hhsys.model.params.CityFileParam;
import cn.stylefeng.guns.modular.hhsys.model.result.CityFileResult;
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
public interface CityFileService extends IService<CityFile> {

    /**
     * 新增
     *
     * @author lls
     * @Date 2020-11-13
     */
    void add(CityFileParam param);
    boolean addFile(CityFile param);

    /**
     * 删除
     *
     * @author lls
     * @Date 2020-11-13
     */
    void delete(CityFileParam param);

    /**
     * 更新
     *
     * @author lls
     * @Date 2020-11-13
     */
    void update(CityFileParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-13
     */
    CityFile findBySpec(CityFileParam param);
    CityFile findById(String id);

    /**
     * 查询列表，Specification模式
     *
     * @author lls
     * @Date 2020-11-13
     */
    List<CityFileResult> findListBySpec(CityFileParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author lls
     * @Date 2020-11-13
     */
     LayuiPageInfo findPageBySpec(CityFileParam param);
     LayuiPageInfo findPageBySpecCopy(CityFileParam param);

}

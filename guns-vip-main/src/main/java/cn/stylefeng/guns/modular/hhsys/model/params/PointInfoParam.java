package cn.stylefeng.guns.modular.hhsys.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 变化点位管理
 * </p>
 *
 * @author lls
 * @since 2020-11-24
 */
@Data
public class PointInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 序号
     */
    private String objectid;

    private String fid;

    /**
     * 河道名称
     */
    private String hdmc;

    /**
     * 类型
     */
    private String shape;

    private String Id;

    /**
     * 面积
     */
    private String mj;

    /**
     * 面积2
     */
    private String mj2;

    /**
     * 序号
     */
    private String xh;

    /**
     * 横坐标
     */
    private String hzb;

    /**
     * 纵坐标
     */
    private String zzb;

    /**
     * 涉及地区
     */
    private String sjdq;

    /**
     * 项目位置
     */
    private String xmwz;

    /**
     * 变化类型
     */
    private String bhlx;

    /**
     * 变化点概况
     */
    private String bhdgk;

    /**
     * 项目名称
     */
    private String xmmc;

    /**
     * 建设主体
     */
    private String jszt;

    /**
     * 上报时间
     */
    private String sbsj;

    /**
     * 运行状态
     */
    private String runState;

    /**
     * 监管过程
     */
    private String jggc;

    /**
     * 备注
     */
    private String bz;

    /**
     * 周长
     */
    private String shapeLeng;

    /**
     * 面积
     */
    private String shapeArea;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date shsj;

    /**
     * 创建人（上报人）
     */
    private Long sbr;

    /**
     * 修改人（审核人）
     */
    private Long shr;

    /**
     * 版本（乐观锁保留字段）
     */
    private Integer version;

    /**
     * 是否删除(逻辑删除)
     */
    private Integer deleted;

    /**
     * 上报原因
     */
    private String sbyy;

    /**
     * 审核原因
     */
    private String shyy;

    @Override
    public String checkParam() {
        return null;
    }

}

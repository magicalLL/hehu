package cn.stylefeng.guns.modular.hhsys.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lls
 * @since 2020-10-26
 */
@Data
public class WyhcParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 图班id
     */
    private String tbId;

    /**
     * 上报图片地址
     */
    private String url;

    /**
     * 审核人
     */
    private String shr;

    /**
     * 审核时间
     */
    private String shsj;

    /**
     * 审核状态：0待审核，1已通过审核，2审核不通过
     */
    private String status;

    /**
     * 审核原因
     */
    private String shyy;

    /**
     * 上报原因
     */
    private String description;

    /**
     * 上报时间
     */
    private String sbsj;

    /**
     * 上报人
     */
    private String sbr;

    /**
     * 建设主体
     */
    private String createBody;

    /**
     * 建设时间
     */
    private String createTime;

    /**
     * 运行状态
     */
    private String runState;

    /**
     * 监管过程
     */
    private String jggc;

    /**
     * 上报单位
     */
    private String sbdw;

    /**
     * 河湖名称
     */
    private String hhmc;

    /**
     * 项目名称
     */
    private String xmmc;

    /**
     * 所在区域
     */
    private String szqy;

    /**
     * 备注
     */
    private String bz;

    private String resourceId;

    @Override
    public String checkParam() {
        return null;
    }

}

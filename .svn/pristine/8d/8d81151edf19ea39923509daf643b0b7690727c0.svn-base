package cn.stylefeng.guns.modular.hhsys.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lls
 * @since 2020-11-03
 */
@Data
public class CityResourceParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    private String name;

    /**
     * 0离线数据，1在线服务，2北斗服务，3手机服务，4视频服务
     */
    private String resourcetype;

    private String aliasname;

    private String abstractValue;

    private String keyword;

    /**
     * 0启动，1停止
     */
    private Integer runstate;

    private String publishuserid;

    private Date publishdate;

    private String catalogDeptid;

    private String catalogThemeid;

    private String serviceTypeId;

    private Integer state;

    /**
     * 缩略图ID
     */
    private String thumbnailId;

    @Override
    public String checkParam() {
        return null;
    }

}

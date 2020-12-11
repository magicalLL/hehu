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
 * @since 2020-11-13
 */
@Data
public class CityFileParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    private String filename;

    private String filepath;

    /**
     * 1离线数据，2新闻附件，3公告附件，4邮件附件，5其他附件
     */
    private String filetype;

    /**
     * 备注
     */
    private Date createtime;

    @Override
    public String checkParam() {
        return null;
    }

}

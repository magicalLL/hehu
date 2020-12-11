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
 * @since 2020-11-03
 */
@Data
public class TokenParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 资源使用者ID
     */
    private String userid;

    /**
     * 资源ID
     */
    private String resourceid;

    /**
     * 部门ID
     */
    private String deptid;

    /**
     * TOKEN值
     */
    private String token;

    @Override
    public String checkParam() {
        return null;
    }

}

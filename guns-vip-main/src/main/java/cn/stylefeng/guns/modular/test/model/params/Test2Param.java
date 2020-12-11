package cn.stylefeng.guns.modular.test.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author lls
 * @since 2020-10-26
 */
@Data
public class Test2Param implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String test1;

    private String test2;

    @Override
    public String checkParam() {
        return null;
    }

}

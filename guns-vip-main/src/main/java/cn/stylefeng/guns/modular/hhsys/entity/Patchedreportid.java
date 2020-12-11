package cn.stylefeng.guns.modular.hhsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lls
 * @since 2020-11-06
 */
@TableName("patchedreportid")
public class Patchedreportid implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private String id;

    @TableField("patchedreportid")
    private String patchedreportid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatchedreportid() {
        return patchedreportid;
    }

    public void setPatchedreportid(String patchedreportid) {
        this.patchedreportid = patchedreportid;
    }

    @Override
    public String toString() {
        return "Patchedreportid{" +
        "id=" + id +
        ", patchedreportid=" + patchedreportid +
        "}";
    }
}

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
 * @since 2020-11-03
 */
@TableName("token")
public class Token implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "ID", type = IdType.ID_WORKER)
    private String id;

    /**
     * 资源使用者ID
     */
    @TableField("USERID")
    private String userid;

    /**
     * 资源ID
     */
    @TableField("RESOURCEID")
    private String resourceid;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private String deptid;

    /**
     * TOKEN值
     */
    @TableField("TOKEN")
    private String token;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    @Override
    public String toString() {
        return "Token{" +
        "id=" + id +
        ", userid=" + userid +
        ", resourceid=" + resourceid +
        ", deptid=" + deptid +
        ", token=" + token +
        "}";
    }
}

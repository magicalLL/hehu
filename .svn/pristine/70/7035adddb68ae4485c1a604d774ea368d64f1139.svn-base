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
 * @since 2020-10-28
 */
@TableName("department")
public class Department implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private String id;

    /**
     * 上级id
     */
    @TableField("pid")
    private String pid;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

    @TableField("tele")
    private String tele;

    @TableField("description")
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{" +
        "id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", address=" + address +
        ", tele=" + tele +
        ", description=" + description +
        "}";
    }
}

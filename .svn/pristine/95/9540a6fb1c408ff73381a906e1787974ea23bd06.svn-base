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
@TableName("dc_bzfield")
public class Bzfield implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 字段名
     */
    @TableField("name")
    private String name;

    /**
     * 字段别名
     */
    @TableField("alias")
    private String alias;

    /**
     * 字段类型
     */
    @TableField("type")
    private String type;

    /**
     * 所属表id
     */
    @TableField("tableid")
    private String tableid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    @Override
    public String toString() {
        return "Bzfield{" +
        "id=" + id +
        ", name=" + name +
        ", alias=" + alias +
        ", type=" + type +
        ", tableid=" + tableid +
        "}";
    }
}

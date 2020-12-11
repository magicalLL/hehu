package cn.stylefeng.guns.modular.hhsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("dc_bzresource")
public class Bzresource implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 用户id
     */
    @TableField("userid")
    private String userid;

    /**
     * 表名
     */
    @TableField("tablename")
    private String tablename;

    /**
     * 创建时间
     */
    @TableField("createtime")
    private Date createtime;

    /**
     * 表的别名
     */
    @TableField("tablealias")
    private String tablealias;


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

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTablealias() {
        return tablealias;
    }

    public void setTablealias(String tablealias) {
        this.tablealias = tablealias;
    }

    @Override
    public String toString() {
        return "Bzresource{" +
        "id=" + id +
        ", userid=" + userid +
        ", tablename=" + tablename +
        ", createtime=" + createtime +
        ", tablealias=" + tablealias +
        "}";
    }
}

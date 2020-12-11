package cn.stylefeng.guns.modular.hhsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("city_file")
public class CityFile implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "ID", type = IdType.ID_WORKER)
    private String id;

    @TableField("FILENAME")
    private String filename;

    @TableField("FILEPATH")
    private String filepath;

    /**
     * 1离线数据，2新闻附件，3公告附件，4邮件附件，5其他附件
     */
    @TableField("FILETYPE")
    private String filetype;

    /**
     * 备注
     */
    @TableField("createtime")
    private Date createtime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "CityFile{" +
        "id=" + id +
        ", filename=" + filename +
        ", filepath=" + filepath +
        ", filetype=" + filetype +
        ", createtime=" + createtime +
        "}";
    }
}

package cn.stylefeng.guns.modular.hhsys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("city_resource")
public class CityResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private String id;

    @TableField("name")
    private String name;

    /**
     * 0离线数据，1在线服务，2北斗服务，3手机服务，4视频服务
     */
    @TableField("resourcetype")
    private String resourcetype;

    @TableField("aliasname")
    private String aliasname;

    @TableField("abstract")
    private String abstractValue;

    @TableField("keyword")
    private String keyword;

    /**
     * 0启动，1停止
     */
    @TableField("runstate")
    private Integer runstate;

    @TableField("publishuserid")
    private String publishuserid;

    @TableField("publishdate")
    private Date publishdate;

    @TableField("catalog_deptid")
    private String catalogDeptid;

    @TableField("catalog_themeid")
    private String catalogThemeid;

    @TableField("service_typeid")
    private String serviceTypeId;

    @TableField("state")
    private Integer state;

    /**
     * 缩略图ID
     */
    @TableField("thumbnail_id")
    private String thumbnailId;


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

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getAliasname() {
        return aliasname;
    }

    public void setAliasname(String aliasname) {
        this.aliasname = aliasname;
    }

    public String getAbstractValue() {
        return abstractValue;
    }

    public void setAbstractValue(String abstractValue) {
        this.abstractValue = abstractValue;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getRunstate() {
        return runstate;
    }

    public void setRunstate(Integer runstate) {
        this.runstate = runstate;
    }

    public String getPublishuserid() {
        return publishuserid;
    }

    public void setPublishuserid(String publishuserid) {
        this.publishuserid = publishuserid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getCatalogDeptid() {
        return catalogDeptid;
    }

    public void setCatalogDeptid(String catalogDeptid) {
        this.catalogDeptid = catalogDeptid;
    }

    public String getCatalogThemeid() {
        return catalogThemeid;
    }

    public void setCatalogThemeid(String catalogThemeid) {
        this.catalogThemeid = catalogThemeid;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeid) {
        this.serviceTypeId = serviceTypeid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    @Override
    public String toString() {
        return "CityResource{" +
        "id=" + id +
        ", name=" + name +
        ", resourcetype=" + resourcetype +
        ", aliasname=" + aliasname +
        ", abstract=" + abstractValue +
        ", keyword=" + keyword +
        ", runstate=" + runstate +
        ", publishuserid=" + publishuserid +
        ", publishdate=" + publishdate +
        ", catalogDeptid=" + catalogDeptid +
        ", catalogThemeid=" + catalogThemeid +
        ", serviceTypeId=" + serviceTypeId +
        ", state=" + state +
        ", thumbnailId=" + thumbnailId +
        "}";
    }
}

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
 * @since 2020-10-27
 */
@TableName("service_matedata")
public class ServiceMatedata implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private String id;

    @TableField("resourceid")
    private String resourceid;

    @TableField("name")
    private String name;

    /**
     * 服务类型id
     */
    @TableField("title")
    private String title;

    @TableField("service_typeid")
    private String serviceTypeId;

    @TableField("type_version")
    private String typeVersion;

    /**
     * 发布地址
     */
    @TableField("publish_url")
    private String publishUrl;

    @TableField("raw_url")
    private String rawUrl;

    @TableField("fileid")
    private String fileid;

    @TableField("west")
    private String west;

    @TableField("east")
    private String east;

    @TableField("north")
    private String north;

    @TableField("south")
    private String south;

    @TableField("resource_owner_dept")
    private String resourceOwnerDept;

    @TableField("resource_owner_dept_url")
    private String resourceOwnerDeptUrl;

    @TableField("resource_owner")
    private String resourceOwner;

    @TableField("resource_owner_job")
    private String resourceOwnerJob;

    /**
     * 国家
     */
    @TableField("country")
    private String country;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 邮编
     */
    @TableField("postcode")
    private String postcode;

    /**
     * 电话
     */
    @TableField("tele")
    private String tele;

    /**
     * 传真
     */
    @TableField("fax")
    private String fax;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    @TableField("access_restriction")
    private String accessRestriction;

    @TableField("use_restriction")
    private String useRestriction;

    @TableField("spatial_reference")
    private String spatialReference;

    @TableField("spatial_type")
    private String spatialType;

    @TableField("spatial_resolution")
    private String spatialResolution;

    @TableField("subject_type")
    private String subjectType;

    @TableField("other_description")
    private String otherDescription;

    @TableField("layermetadataid")
    private String layermetadataid;

    /**
     * 生产日期
     */
    @TableField("manifacture_date")
    private Date manifactureDate;

    /**
     * 1带时空标签，2不带
     */
    @TableField("timemark")
    private Integer timemark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeid) {
        this.serviceTypeId = serviceTypeid;
    }

    public String getTypeVersion() {
        return typeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }

    public String getPublishUrl() {
        return publishUrl;
    }

    public void setPublishUrl(String publishUrl) {
        this.publishUrl = publishUrl;
    }

    public String getRawUrl() {
        return rawUrl;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getResourceOwnerDept() {
        return resourceOwnerDept;
    }

    public void setResourceOwnerDept(String resourceOwnerDept) {
        this.resourceOwnerDept = resourceOwnerDept;
    }

    public String getResourceOwnerDeptUrl() {
        return resourceOwnerDeptUrl;
    }

    public void setResourceOwnerDeptUrl(String resourceOwnerDeptUrl) {
        this.resourceOwnerDeptUrl = resourceOwnerDeptUrl;
    }

    public String getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(String resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public String getResourceOwnerJob() {
        return resourceOwnerJob;
    }

    public void setResourceOwnerJob(String resourceOwnerJob) {
        this.resourceOwnerJob = resourceOwnerJob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessRestriction() {
        return accessRestriction;
    }

    public void setAccessRestriction(String accessRestriction) {
        this.accessRestriction = accessRestriction;
    }

    public String getUseRestriction() {
        return useRestriction;
    }

    public void setUseRestriction(String useRestriction) {
        this.useRestriction = useRestriction;
    }

    public String getSpatialReference() {
        return spatialReference;
    }

    public void setSpatialReference(String spatialReference) {
        this.spatialReference = spatialReference;
    }

    public String getSpatialType() {
        return spatialType;
    }

    public void setSpatialType(String spatialType) {
        this.spatialType = spatialType;
    }

    public String getSpatialResolution() {
        return spatialResolution;
    }

    public void setSpatialResolution(String spatialResolution) {
        this.spatialResolution = spatialResolution;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
    }

    public String getLayermetadataid() {
        return layermetadataid;
    }

    public void setLayermetadataid(String layermetadataid) {
        this.layermetadataid = layermetadataid;
    }

    public Date getManifactureDate() {
        return manifactureDate;
    }

    public void setManifactureDate(Date manifactureDate) {
        this.manifactureDate = manifactureDate;
    }

    public Integer getTimemark() {
        return timemark;
    }

    public void setTimemark(Integer timemark) {
        this.timemark = timemark;
    }

    @Override
    public String toString() {
        return "ServiceMatedata{" +
        "id=" + id +
        ", resourceid=" + resourceid +
        ", name=" + name +
        ", title=" + title +
        ", serviceTypeId=" + serviceTypeId +
        ", typeVersion=" + typeVersion +
        ", publishUrl=" + publishUrl +
        ", rawUrl=" + rawUrl +
        ", fileid=" + fileid +
        ", west=" + west +
        ", east=" + east +
        ", north=" + north +
        ", south=" + south +
        ", resourceOwnerDept=" + resourceOwnerDept +
        ", resourceOwnerDeptUrl=" + resourceOwnerDeptUrl +
        ", resourceOwner=" + resourceOwner +
        ", resourceOwnerJob=" + resourceOwnerJob +
        ", country=" + country +
        ", city=" + city +
        ", address=" + address +
        ", postcode=" + postcode +
        ", tele=" + tele +
        ", fax=" + fax +
        ", email=" + email +
        ", accessRestriction=" + accessRestriction +
        ", useRestriction=" + useRestriction +
        ", spatialReference=" + spatialReference +
        ", spatialType=" + spatialType +
        ", spatialResolution=" + spatialResolution +
        ", subjectType=" + subjectType +
        ", otherDescription=" + otherDescription +
        ", layermetadataid=" + layermetadataid +
        ", manifactureDate=" + manifactureDate +
        ", timemark=" + timemark +
        "}";
    }
}

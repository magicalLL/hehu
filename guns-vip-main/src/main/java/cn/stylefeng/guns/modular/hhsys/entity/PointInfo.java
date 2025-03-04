package cn.stylefeng.guns.modular.hhsys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 变化点位管理
 * </p>
 *
 * @author lls
 * @since 2020-11-24
 */
@TableName("dc_point_info")
public class PointInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 序号
     */
      @TableId(value = "objectid", type = IdType.ID_WORKER)
    private String objectid;

    @TableField("fid")
    private String fid;

    /**
     * 河道名称
     */
    @TableField("hdmc")
    private String hdmc;

    /**
     * 类型
     */
    @TableField("shape")
    private String shape;

    @TableField("Id")
    private String Id;

    /**
     * 面积
     */
    @TableField("mj")
    private String mj;

    /**
     * 面积2
     */
    @TableField("mj2")
    private String mj2;

    /**
     * 序号
     */
    @TableField("xh")
    private String xh;

    /**
     * 横坐标
     */
    @TableField("hzb")
    private String hzb;

    /**
     * 纵坐标
     */
    @TableField("zzb")
    private String zzb;

    /**
     * 涉及地区
     */
    @TableField("sjdq")
    private String sjdq;

    /**
     * 项目位置
     */
    @TableField("xmwz")
    private String xmwz;

    /**
     * 变化类型
     */
    @TableField("bhlx")
    private String bhlx;

    /**
     * 变化点概况
     */
    @TableField("bhdgk")
    private String bhdgk;

    /**
     * 项目名称
     */
    @TableField("xmmc")
    private String xmmc;

    /**
     * 建设主体
     */
    @TableField("jszt")
    private String jszt;

    /**
     * 建设时间（上报时间）
     * 已修改别名
     */
    @TableField("sbsj")
    private String sbsj;

    /**
     * 运行状态
     * 已修改别名
     */
    @TableField("runstate")
    private String runState;

    /**
     * 监管过程
     */
    @TableField("jggc")
    private String jggc;

    /**
     * 备注
     */
    @TableField("bz")
    private String bz;

    /**
     * 周长
     */
    @TableField("Shape_Leng")
    private String shapeLeng;

    /**
     * 面积
     */
    @TableField("Shape_Area")
    private String shapeArea;

    /**
     * 审核状态
     */
    @TableField("status")
    private Integer status;

    /*
     * 上报原因
     */
    @TableField("sbyy")
    private String sbyy;

    /*
     * 审核原因
     */
    @TableField("shyy")
    private String shyy;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间(审核时间)
     * 已修改别名
     */
      @TableField(value = "shsj", fill = FieldFill.UPDATE)
    private Date shsj;

    /**
     * 创建人（上报人）
     * 已修改别名
     */
      @TableField(value = "sbr", fill = FieldFill.INSERT)
    private Long sbr;

    /**
     * 修改人（审核人）
     * 已修改别名
     */
      @TableField(value = "shr", fill = FieldFill.UPDATE)
    private Long shr;

    /**
     * 版本（乐观锁保留字段）
     */
    @TableField("version")
    private Integer version;

    /**
     * 是否删除(逻辑删除)
     */
    @TableField("deleted")
    private Integer deleted;


    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public String getMj2() {
        return mj2;
    }

    public void setMj2(String mj2) {
        this.mj2 = mj2;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getHzb() {
        return hzb;
    }

    public void setHzb(String hzb) {
        this.hzb = hzb;
    }

    public String getZzb() {
        return zzb;
    }

    public void setZzb(String zzb) {
        this.zzb = zzb;
    }

    public String getSjdq() {
        return sjdq;
    }

    public void setSjdq(String sjdq) {
        this.sjdq = sjdq;
    }

    public String getXmwz() {
        return xmwz;
    }

    public void setXmwz(String xmwz) {
        this.xmwz = xmwz;
    }

    public String getBhlx() {
        return bhlx;
    }

    public void setBhlx(String bhlx) {
        this.bhlx = bhlx;
    }

    public String getBhdgk() {
        return bhdgk;
    }

    public void setBhdgk(String bhdgk) {
        this.bhdgk = bhdgk;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getJggc() {
        return jggc;
    }

    public void setJggc(String jggc) {
        this.jggc = jggc;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getShapeLeng() {
        return shapeLeng;
    }

    public void setShapeLeng(String shapeLeng) {
        this.shapeLeng = shapeLeng;
    }

    public String getShapeArea() {
        return shapeArea;
    }

    public void setShapeArea(String shapeArea) {
        this.shapeArea = shapeArea;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getShsj() {
        return shsj;
    }

    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }

    public Long getSbr() {
        return sbr;
    }

    public void setSbr(Long sbr) {
        this.sbr = sbr;
    }

    public Long getShr() {
        return shr;
    }

    public void setShr(Long shr) {
        this.shr = shr;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "PointInfo{" +
        "objectid=" + objectid +
        ", fid=" + fid +
        ", hdmc=" + hdmc +
        ", shape=" + shape +
        ", Id=" + Id +
        ", mj=" + mj +
        ", mj2=" + mj2 +
        ", xh=" + xh +
        ", hzb=" + hzb +
        ", zzb=" + zzb +
        ", sjdq=" + sjdq +
        ", xmwz=" + xmwz +
        ", bhlx=" + bhlx +
        ", bhdgk=" + bhdgk +
        ", xmmc=" + xmmc +
        ", jszt=" + jszt +
        ", jssj=" + sbsj +
        ", yxzt=" + runState +
        ", jggc=" + jggc +
        ", bz=" + bz +
        ", shapeLeng=" + shapeLeng +
        ", shapeArea=" + shapeArea +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + shsj +
        ", createUser=" + sbr +
        ", updateUser=" + shr +
        ", version=" + version +
        ", deleted=" + deleted +
        "}";
    }
}

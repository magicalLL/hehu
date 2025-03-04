package cn.stylefeng.guns.modular.hhsys.service;

import cn.stylefeng.guns.modular.hhsys.entity.*;
//import cn.stylefeng.guns.modular.hhsys.proxy.ServiceType;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/3/7.
 */
public interface ResourceService {

    public boolean hasResource(String resourceName);

//    public DataInfo getDataInfo(String resourceId);
//
//    public boolean updateDataInfo(DataInfo dataInfo);
//
//    public boolean publishDataResource(DataInfo dataInfo);

    public boolean hasService(String resourceName);

    public ServiceInfo getServiceInfo(String serviceId);

    public String getAutoPublishServiceUrl(String serviceName, String fileId, String serviceType, String type);

    public boolean publishServiceResource(ServiceInfo serviceInfo, boolean share);

    public boolean updateServiceInfo(ServiceInfo serviceInfo);

    public boolean publishTimeServiceResource(TimeServiceInfo timeServiceInfo, boolean share);

    public boolean editTimeServiceResource(TimeServiceInfo timeServiceInfo);

    public TimeServiceMetaData getTimeServiceMetaData(String id);

    public TimeServiceInfo getTimeServiceInfo(String serviceId);

//    public List<ServiceType> getServiceTypeList(String resourceType);

//    public List<ResourceType> getResourceTypeList();

    public boolean isGuestService(String resourceId, String userId);

    public boolean isCanApply(String resourceId, String userId);

//    public List<ResourceApply> getPreApplyList(String userId, String resourceType, String keyword, String pageIndex, String pageNumber);

    public int getPreApplyListCount(String userId, String resourceType, String keyword);

//    public boolean preApply(ResourceApply resourceApply);

    public boolean realApply(String resourceApplyId);

    public boolean delPreApply(String resourceApplyId);

    public List<ServiceMatedata> getAllServiceMetaData();

    public List<String> getGuestAccessResourceList();

    public List<String> getCanAccessResourceList(String userId);

    public List<Token> getTokenInfo(String token);

    public boolean hasBeidouService(String resourceName);

//    public boolean publishBeidouResource(BeidouInfo beidouInfo);
//
//    public boolean editBeidouResource(BeidouInfo beidouInfo);
//
//    public BeidouInfo getBeidouInfo(String resourceId);
//
//    public boolean hasPhoneService(String resourceName);
//
//    public boolean publishPhoneResource(PhoneInfo phoneInfo);
//
//    public boolean editPhoneResource(PhoneInfo phoneInfo);

//    public PhoneInfo getPhoneInfo(String resourceId);

    public boolean hasVideoService(String resourceName);

//    public boolean publishVideoResource(VideoInfo videoInfo);

//    public boolean editVideoResource(VideoInfo videoInfo);

//    public VideoInfo getVideoInfo(String resourceId);

    public int getResourceCount(String deptId, String userId, String themeId, String resourceType, String serviceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate, String staticDynamicType, List<String> resourceIds, String xmin, String xmax, String ymin, String ymax);
    public List<Map<String, Object>> getResourceList(String deptId, String userId, String themeId, String resourceType, String serviceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate, String staticDynamicType, List<String> resourceIds, String xmin, String xmax, String ymin, String ymax, String sortField, String sortType, int pageNo, int pageSize);
    public List<Map<String, Object>> getDeptResourceList(String deptId, String staticDynamicType, int pageNo, int pageSize);
    public List<Map<String, Object>> getUserResourceList(String userId, int pageNo, int pageSize);

    public int getDeptResourceCount(String deptId, String staticDynamicType);
    public int getUserResourceCount(String userId);

//    public Map<String, Object> getResourceMetadataService(String resourceId);
//    public Map<String, Object> getResourceMetadataFile(String resourceId);
    public Map<String, Object> getResourceDetails(String resourceId, String userId);

    public int deleteResourceById(String resourceId, String fileFolder);
    public int updateResourceRunStatus(String resourceId, int status);

    public int getUserApplyCount(String userId, String resourceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate);
    public List<Map<String, Object>> getUserApplyList(String userId, String resourceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate, String sortField, String sortType, int pageNo, int pageSize);

    public Map<String, Object> getResourceApplyDetails(String resourceApplyId);
    public int deleteResourceApply(String userId, String resourceId);
    public int deleteToken(String userId, String resourceId);

    public List<Map<String, Object>> getResourceAppliedList(String resourceId, int pageNo, int pageSize);

    public Map<String, Object> getCatalogListByDeptId(String deptId, String userId);
    public Map<String, Object> getCatalogListByThemeId(String themeId, String userId);

//    public VideoMetaData getVideoMetadata(String resourceId, String getUserId);

    public List<String> getAuthorizedResourceIdList(String userId);

}

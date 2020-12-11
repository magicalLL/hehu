//package cn.stylefeng.guns.modular.hhsys.service.impl;
//
//import cn.stylefeng.guns.modular.hhsys.service.ResourceService;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.smartcity.dao.*;
//import com.smartcity.entity.*;
//import com.smartcity.util.EHCacheUtil;
//import com.smartcity.util.IPUtil;
//import com.smartcity.util.MultipleDataSource;
//import com.smartcity.util.ServiceUtil;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * Created by dell on 2016/3/7.
// */
//public class ResourceServiceImpl implements ResourceService {
//
//    @Autowired
//    private ResourceDao resourceDao;
//
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private DepartmentDao departmentDao;
//    @Autowired
//    private ThemeDao themeDao;
//    @Autowired
//    private FileDao fileDao;
//    @Autowired
//    private TaskDao taskDao;
//    @Autowired
//    private TokenDao tokenDao;
//
//    @Autowired
//    private FileService fileService;
//
//    @Override
//    public boolean hasResource(String resourceName) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getDataResourceByName(resourceName);
//        if(resource==null) return false;
//        else return true;
//    }
//
////    @Override
//////    public DataInfo getDataInfo(String resourceId) {
//////        MultipleDataSource.setDataSourceKey("dataSource1");
//////        Resource resource = resourceDao.getResourceById(resourceId);
//////        DataMetaData dataMetaData = resourceDao.getDataMetaDataById(resourceId);
//////        DataInfo dataInfo = new DataInfo();
//////        dataInfo.setResource(resource);
//////        dataInfo.setDataMetaData(dataMetaData);
//////        return dataInfo;
//////    }
//////
//////    @Override
//////    @Transactional
//////    public boolean updateDataInfo(DataInfo dataInfo) {
//////        MultipleDataSource.setDataSourceKey("dataSource1");
//////        Resource resource = dataInfo.getResource();
//////        DataMetaData dataMetaData = dataInfo.getDataMetaData();
////////        try {
////////            resourceDao.updateResource(resource);
////////            resourceDao.updateDataMetaData(dataMetaData);
////////            return true;
////////        }catch(Exception ex) {
////////            ex.printStackTrace();
////////            return false;
////////        }
//////        resourceDao.updateResource(resource);
//////        resourceDao.updateDataMetaData(dataMetaData);
//////        return true;
//////    }
//////
//////    @Override
//////    @Transactional
//////    public boolean publishDataResource(DataInfo dataInfo) {
//////        MultipleDataSource.setDataSourceKey("dataSource1");
//////        Resource resource = dataInfo.getResource();
//////        // 发布用户的部门ID作为来源目录部门ID
//////        String userId = resource.getPublishuserid();
//////        User user = userDao.getUserById(userId);
//////        resource.setCatalogDeptid(user.getDepId());
//////        // 默认开启
//////        if(resource.getRunstate()==null) {
//////            resource.setRunstate(new BigDecimal(0));
//////        }
//////        DataMetaData dataMetaData = dataInfo.getDataMetaData();
//////
//////        String resourceId = resource.getId();
//////        if(resourceId==null||resourceId.isEmpty()) {
//////            resourceId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//////            resource.setId(resourceId);
//////            dataMetaData.setResourceid(resourceId);
//////        }
//////        String dataMetaDataId = dataMetaData.getId();
//////        if(dataMetaDataId==null||dataMetaDataId.isEmpty()) {
//////            dataMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
//////            dataMetaData.setId(dataMetaDataId);
//////        }
//////        ServiceUtil.RESOURCE_ID = resourceId;
////////        try {
////////            resourceDao.addResource(resource);
////////            resourceDao.addDataMetaData(dataMetaData);
////////            return true;
////////        }catch(Exception ex) {
////////            ex.printStackTrace();
////////            return false;
////////        }
//////
//////        resourceDao.addResource(resource);
//////        resourceDao.addDataMetaData(dataMetaData);
//////        // 记录任务信息
//////        Task task = new Task();
//////        String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//////        task.setId(taskId);
//////        task.setApproveState(new BigDecimal(0));
//////        task.setTaskType(new BigDecimal(1));
//////        task.setCaseId(resourceId);
//////        taskDao.addTask(task);
//////
//////        return true;
//////    }
//
//    @Override
//    public boolean hasService(String resourceName) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getServiceByName(resourceName);
//        if(resource==null) return false;
//        else return true;
//    }
//
//    @Override
//    public ServiceInfo getServiceInfo(String serviceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getResourceById(serviceId);
//        String thumbnailId = resource.getThumbnailId();
//        if(thumbnailId==null) {
//            resource.setThumbnailPath("");
//            resource.setThumbnailName("");
//        }else {
//            File fileInfo = fileDao.getFileById(thumbnailId);
//            if(fileInfo!=null) {
//                resource.setThumbnailName(fileInfo.getFilename());
//                resource.setThumbnailPath(fileInfo.getFilepath());
//            }
//        }
//        ServiceMetaData serviceMetaData = resourceDao.getServiceMetaDataById(serviceId);
//        if(resource!=null&&serviceMetaData!=null) {
//            List<ServiceLayerInfo> layerInfoList = resourceDao.getServiceLayerInfoList(serviceMetaData.getId());
//            ServiceInfo serviceInfo = new ServiceInfo();
//            serviceInfo.setResource(resource);
//            serviceInfo.setServiceMetaData(serviceMetaData);
//            serviceInfo.setLayerInfoList(layerInfoList);
//            return serviceInfo;
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public String getAutoPublishServiceUrl(String serviceName, String fileId, String serviceType,String type){
//        String url = IPUtil.getPythonPublishServiceUrl(serviceName,fileId,serviceType,type);
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpget = new HttpGet(url);
//        try {
//            HttpResponse response = httpClient.execute(httpget);
//            HttpEntity entity = response.getEntity();
//            System.out.println("----------------------------------------");
//            System.out.println(response.getStatusLine());
//            if (entity != null) {
//                String str = EntityUtils.toString(entity);
//                System.out.println(str);
//                // 解析JSON
//                JSONObject jsonObj = JSON.parseObject(str);
//                String result = jsonObj.getString("result");
//                boolean success = jsonObj.getBoolean("success");
//                if(success) {
//                    return result;
//                }else {
//                    return null;
//                }
//            }else {
//                return null;
//            }
//        }catch(Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
////    @Override
////    @Transactional
////    public boolean publishServiceResource(ServiceInfo serviceInfo,boolean share) {
////        MultipleDataSource.setDataSourceKey("dataSource1");
////        Resource resource = serviceInfo.getResource();
////        // 发布用户的部门ID作为来源目录部门ID
////        String userId = resource.getPublishuserid();
////        User user = userDao.getUserById(userId);
////        resource.setCatalogDeptid(user.getDepId());
////        // 默认开启
////        if(resource.getRunstate()==null) {
////            resource.setRunstate(new BigDecimal(0));
////        }
////        ServiceMetaData serviceMetaData = serviceInfo.getServiceMetaData();
////        List<ServiceLayerInfo> serviceLayerInfoList = serviceInfo.getLayerInfoList();
////
////        String resourceId = resource.getId();
////        if(resourceId==null||resourceId.isEmpty()) {
////            resourceId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            resource.setId(resourceId);
////            serviceMetaData.setResourceid(resourceId);
////        }
////        String serviceMetaDataId = serviceMetaData.getId();
////        if(serviceMetaDataId==null||serviceMetaDataId.isEmpty()) {
////            serviceMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
////            serviceMetaData.setId(serviceMetaDataId);
////        }
////        // 存相对路径
////        String proxyUrl = EHCacheUtil.toProxyUrl("", resourceId, serviceMetaData.getRawUrl());
////        serviceMetaData.setPublishUrl(proxyUrl);
////
/////*        try {
////            resourceDao.addResource(resource);
////            resourceDao.addServiceMetaData(serviceMetaData);
////            for(ServiceLayerInfo serviceLayerInfo : serviceLayerInfoList) {
////                String serviceLayerInfoId = serviceLayerInfo.getId();
////                if(serviceLayerInfoId==null||serviceLayerInfoId.isEmpty()) {
////                    serviceLayerInfoId = UUID.randomUUID().toString().trim().replaceAll("-","");
////                    serviceLayerInfo.setId(serviceLayerInfoId);
////                }
////                serviceLayerInfo.setServiceMetadataid(serviceMetaDataId);
////                resourceDao.addServiceLayerInfo(serviceLayerInfo);
////            }
////            return true;
////        }catch(Exception ex) {
////            ex.printStackTrace();
////            return false;
////        }*/
////        ServiceUtil.RESOURCE_ID = resourceId;
////        resourceDao.addResource(resource);
////        resourceDao.addServiceMetaData(serviceMetaData);
////        if(serviceMetaData.getTimeMark()==0) serviceMetaData.setTimeMark(2);
////        for(ServiceLayerInfo serviceLayerInfo : serviceLayerInfoList) {
////            String serviceLayerInfoId = serviceLayerInfo.getId();
////            if(serviceLayerInfoId==null||serviceLayerInfoId.isEmpty()) {
////                serviceLayerInfoId = UUID.randomUUID().toString().trim().replaceAll("-","");
////                serviceLayerInfo.setId(serviceLayerInfoId);
////            }
////            serviceLayerInfo.setServiceMetadataid(serviceMetaDataId);
////            resourceDao.addServiceLayerInfo(serviceLayerInfo);
////        }
////        // 超级管理员有权限看
////        TokenInfo tokenInfo = new TokenInfo();
////        String tokenId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////        tokenInfo.setId(tokenId);
////        tokenInfo.setUserId("0001");
////        tokenInfo.setResourceId(resourceId);
////        String token = UUID.randomUUID().toString().trim().replaceAll("-", "");
////        tokenInfo.setToken(token);
////        tokenDao.addToken(tokenInfo);
////        // 自己也有权限看，如果不是超级管理员就加入，如果是超级管理员就不加了
////        if(!"0001".equals(userId)) {
////            TokenInfo tokenInfo2 = new TokenInfo();
////            String tokenId2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo2.setId(tokenId2);
////            tokenInfo2.setUserId(userId);
////            tokenInfo2.setResourceId(resourceId);
////            String token2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo2.setToken(token2);
////            tokenDao.addToken(tokenInfo2);
////        }
////        // 记录任务信息
////        if(share) {
////            // 自动审批通过
////            Task task = new Task();
////            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            task.setId(taskId);
////            task.setApproveState(new BigDecimal(1));
////            task.setTaskType(new BigDecimal(1));
////            task.setCaseId(resourceId);
////            taskDao.addTask(task);
////            resource.setState(new BigDecimal(1));
////            resourceDao.updateResource(resource);
////            // 自动加入TOKEN表
////            TokenInfo tokenInfo3 = new TokenInfo();
////            String tokenId3 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo3.setId(tokenId3);
////            tokenInfo3.setUserId("-1");
////            tokenInfo3.setResourceId(resourceId);
////            String token3 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo3.setToken(token3);
////            tokenDao.addToken(tokenInfo3);
////        }else {
////            Task task = new Task();
////            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            task.setId(taskId);
////            task.setApproveState(new BigDecimal(0));
////            task.setTaskType(new BigDecimal(1));
////            task.setCaseId(resourceId);
////            taskDao.addTask(task);
////        }
////
////        return true;
////    }
////
////    @Override
////    @Transactional
////    public boolean updateServiceInfo(ServiceInfo serviceInfo) {
////        MultipleDataSource.setDataSourceKey("dataSource1");
////        Resource resource = serviceInfo.getResource();
////        ServiceMetaData serviceMetaData = serviceInfo.getServiceMetaData();
////        List<ServiceLayerInfo> serviceLayerInfoList = serviceInfo.getLayerInfoList();
////        /*try {
////            if(resource!=null)
////                resourceDao.updateResource(resource);
////            if(serviceMetaData!=null&&serviceMetaData.getId()!=null&&(!serviceMetaData.getId().isEmpty()))
////                resourceDao.updateServiceMetaData(serviceMetaData);
////            if(serviceLayerInfoList!=null&&serviceLayerInfoList.size()>0)
////                for(ServiceLayerInfo serviceLayerInfo : serviceLayerInfoList) {
////                    resourceDao.updateServiceLayerInfo(serviceLayerInfo);
////                }
////            return true;
////        }catch(Exception ex) {
////            ex.printStackTrace();
////            return false;
////        }*/
////
////        if(resource!=null)
////            resourceDao.updateResource(resource);
////        if(serviceMetaData!=null&&serviceMetaData.getId()!=null&&(!serviceMetaData.getId().isEmpty()))
////            resourceDao.updateServiceMetaData(serviceMetaData);
////        if(serviceLayerInfoList!=null&&serviceLayerInfoList.size()>0)
////            for(ServiceLayerInfo serviceLayerInfo : serviceLayerInfoList) {
////                resourceDao.updateServiceLayerInfo(serviceLayerInfo);
////            }
////        return true;
////    }
////
////    @Override
////    public boolean publishTimeServiceResource(TimeServiceInfo timeServiceInfo, boolean share) {
////        MultipleDataSource.setDataSourceKey("dataSource1");
////        Resource resource = timeServiceInfo.getResource();
////        // 发布用户的部门ID作为来源目录部门ID
////        String userId = resource.getPublishuserid();
////        User user = userDao.getUserById(userId);
////        resource.setCatalogDeptid(user.getDepId());
////        // 默认开启
////        if(resource.getRunstate()==null) {
////            resource.setRunstate(new BigDecimal(0));
////        }
////        ServiceMetaData serviceMetaData = timeServiceInfo.getServiceMetaData();
////        List<ServiceLayerInfo> serviceLayerInfoList = timeServiceInfo.getLayerInfoList();
////
////        String resourceId = resource.getId();
////        if(resourceId==null||resourceId.isEmpty()) {
////            resourceId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            resource.setId(resourceId);
////            serviceMetaData.setResourceid(resourceId);
////        }
////        String serviceMetaDataId = serviceMetaData.getId();
////        if(serviceMetaDataId==null||serviceMetaDataId.isEmpty()) {
////            serviceMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
////            serviceMetaData.setId(serviceMetaDataId);
////        }
////        // 存相对路径
////        String proxyUrl = EHCacheUtil.toProxyUrl("", resourceId, serviceMetaData.getRawUrl());
////        serviceMetaData.setPublishUrl(proxyUrl);
////
////        ServiceUtil.RESOURCE_ID = resourceId;
////        resourceDao.addResource(resource);
////        resourceDao.addServiceMetaData(serviceMetaData);
////        TimeServiceMetaData timeServiceMetaData = timeServiceInfo.getTimeServiceMetaData();
////        String timeServiceMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
//////        String startTime = timeServiceInfo.getStartTime();
//////        String endTime = timeServiceInfo.getEndTime();
//////        String dateType = timeServiceInfo.getDateType();
////        timeServiceMetaData.setId(timeServiceMetaDataId);
////        timeServiceMetaData.setResourceId(resourceId);
////        timeServiceMetaData.setServiceId(serviceMetaDataId);
//////        timeServiceMetaData.setStartTime(startTime);
//////        timeServiceMetaData.setEndTime(endTime);
//////        timeServiceMetaData.setDateType(dateType);
////        resourceDao.addTimeServiceMetaData(timeServiceMetaData);
////
////        if(serviceMetaData.getTimeMark()==0) serviceMetaData.setTimeMark(2);
////        for(ServiceLayerInfo serviceLayerInfo : serviceLayerInfoList) {
////            String serviceLayerInfoId = serviceLayerInfo.getId();
////            if(serviceLayerInfoId==null||serviceLayerInfoId.isEmpty()) {
////                serviceLayerInfoId = UUID.randomUUID().toString().trim().replaceAll("-","");
////                serviceLayerInfo.setId(serviceLayerInfoId);
////            }
////            serviceLayerInfo.setServiceMetadataid(serviceMetaDataId);
////            resourceDao.addServiceLayerInfo(serviceLayerInfo);
////        }
////
////        // 超级管理员有权限看
////        TokenInfo tokenInfo = new TokenInfo();
////        String tokenId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////        tokenInfo.setId(tokenId);
////        tokenInfo.setUserId("0001");
////        tokenInfo.setResourceId(resourceId);
////        String token = UUID.randomUUID().toString().trim().replaceAll("-", "");
////        tokenInfo.setToken(token);
////        tokenDao.addToken(tokenInfo);
////        // 自己也有权限看，如果不是超级管理员就加入，如果是超级管理员就不加了
////        if(!"0001".equals(userId)) {
////            TokenInfo tokenInfo2 = new TokenInfo();
////            String tokenId2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo2.setId(tokenId2);
////            tokenInfo2.setUserId(userId);
////            tokenInfo2.setResourceId(resourceId);
////            String token2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo2.setToken(token2);
////            tokenDao.addToken(tokenInfo2);
////        }
////
////        // 记录任务信息
////        if(share) {
////            // 自动审批通过
////            Task task = new Task();
////            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            task.setId(taskId);
////            task.setApproveState(new BigDecimal(1));
////            task.setTaskType(new BigDecimal(1));
////            task.setCaseId(resourceId);
////            taskDao.addTask(task);
////            resource.setState(new BigDecimal(1));
////            resourceDao.updateResource(resource);
////            // 自动加入TOKEN表
////            TokenInfo tokenInfo3 = new TokenInfo();
////            String tokenId3 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo3.setId(tokenId3);
////            tokenInfo3.setUserId("-1");
////            tokenInfo3.setResourceId(resourceId);
////            String token3 = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            tokenInfo3.setToken(token3);
////            tokenDao.addToken(tokenInfo3);
////        }else {
////            Task task = new Task();
////            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
////            task.setId(taskId);
////            task.setApproveState(new BigDecimal(0));
////            task.setTaskType(new BigDecimal(1));
////            task.setCaseId(resourceId);
////            taskDao.addTask(task);
////        }
////
////        return true;
////    }
////
////    @Override
////    @Transactional
////    public boolean editTimeServiceResource(TimeServiceInfo timeServiceInfo) {
////        MultipleDataSource.setDataSourceKey("dataSource1");
////        Resource resource = timeServiceInfo.getResource();
////        ServiceMetaData serviceMetaData = timeServiceInfo.getServiceMetaData();
////        List<ServiceLayerInfo> serviceLayerInfoList = timeServiceInfo.getLayerInfoList();
////        TimeServiceMetaData timeServiceMetaData = timeServiceInfo.getTimeServiceMetaData();
////
////        if(resource!=null)
////            resourceDao.updateResource(resource);
////        if(serviceMetaData!=null&&serviceMetaData.getId()!=null&&(!serviceMetaData.getId().isEmpty()))
////            resourceDao.updateServiceMetaData(serviceMetaData);
////        if(serviceLayerInfoList!=null&&serviceLayerInfoList.size()>0)
////            for(ServiceLayerInfo serviceLayerInfo : serviceLayerInfoList) {
////                resourceDao.updateServiceLayerInfo(serviceLayerInfo);
////            }
////        if(timeServiceMetaData!=null)
////            resourceDao.updateTimeServiceMetaData(timeServiceMetaData);
////        return true;
////    }
//
//    @Override
//    public TimeServiceMetaData getTimeServiceMetaData(String id) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        TimeServiceMetaData timeServiceMetaData = resourceDao.getTimeServiceMetaDataById(id);
//        return timeServiceMetaData;
//    }
//
//    @Override
//    public TimeServiceInfo getTimeServiceInfo(String serviceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getResourceById(serviceId);
//        String thumbnailId = resource.getThumbnailId();
//        if(thumbnailId==null) {
//            resource.setThumbnailPath("");
//            resource.setThumbnailName("");
//        }else {
//            File fileInfo = fileDao.getFileById(thumbnailId);
//            if(fileInfo!=null) {
//                resource.setThumbnailName(fileInfo.getFilename());
//                resource.setThumbnailPath(fileInfo.getFilepath());
//            }
//        }
//        ServiceMetaData serviceMetaData = resourceDao.getServiceMetaDataById(serviceId);
//        TimeServiceMetaData timeServiceMetaData = resourceDao.getTimeServiceMetaDataById(serviceId);
//        if(resource!=null&&serviceMetaData!=null) {
//            List<ServiceLayerInfo> layerInfoList = resourceDao.getServiceLayerInfoList(serviceMetaData.getId());
//            TimeServiceInfo timeServiceInfo = new TimeServiceInfo();
//            timeServiceInfo.setResource(resource);
//            timeServiceInfo.setServiceMetaData(serviceMetaData);
//            timeServiceInfo.setTimeServiceMetaData(timeServiceMetaData);
//            timeServiceInfo.setLayerInfoList(layerInfoList);
//            return timeServiceInfo;
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<ServiceType> getServiceTypeList(String resourceType) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        List<ServiceType> serviceTypeList = resourceDao.getServiceTypeList(resourceType);
//        return serviceTypeList;
//    }
//
//    @Override
//    public List<ResourceType> getResourceTypeList() {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getResourceTypeList();
//    }
//
//    @Override
//    public boolean isGuestService(String resourceId,String userId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        boolean isGuestService = false;
//        // 查询是否是游客服务，若是游客服务则不需要申请
//        List<TokenInfo> tokenInfoList = tokenDao.getGuestTokenInfoList();
//        for(TokenInfo tokenInfo : tokenInfoList) {
//            if(tokenInfo.getResourceId().equals(resourceId)) {
//                isGuestService = true;
//                break;
//            }
//        }
//        return isGuestService;
//    }
//
//    @Override
//    public boolean isCanApply(String resourceId, String userId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        // 查询是否是自己发布的服务
//        Resource resource = resourceDao.getResourceById(resourceId);
//        if(userId.equals(resource.getPublishuserid())) {
//            return false;
//        }else {
//            // 查询是否已经申请过了
//            List<ResourceApply> resourceApplyList = resourceDao.getResourceApplyUser(resourceId,userId);
//            if(resourceApplyList!=null&&resourceApplyList.size()>0) {
//                return false;
//            }else {
//                return true;
//            }
//        }
//    }
//
//    @Override
//    public List<ResourceApply> getPreApplyList(String userId, String resourceType, String keyword, String pageIndex, String pageNumber) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        List<ResourceApply> resourceApplyList =  resourceDao.getPreApplyList(userId,resourceType,keyword,pageIndex,pageNumber);
//        for(ResourceApply resourceApply : resourceApplyList) {
//            String thumbnailId = resourceApply.getThumbnailId();
//            if(thumbnailId==null||thumbnailId.isEmpty()) {
//                resourceApply.setThumbnailFilePath("");
//            }else {
//                File fileInfo = fileDao.getFileById(thumbnailId);
//                resourceApply.setThumbnailFilePath(fileInfo.getFilepath());
//            }
//        }
//        return resourceApplyList;
//    }
//
//    @Override
//    public int getPreApplyListCount(String userId, String resourceType, String keyword) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getPreApplyListCount(userId,resourceType,keyword);
//    }
//
//    @Override
//    @Transactional
//    public boolean preApply(ResourceApply resourceApply) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        String resourceApplyId = resourceApply.getId();
//        if(resourceApplyId==null||resourceApplyId.isEmpty()) {
//            resourceApplyId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            resourceApply.setId(resourceApplyId);
//        }
//        resourceApply.setApplyTime(new Date());
//        resourceApply.setState(new BigDecimal(0));
//        resourceApply.setApplyState(new BigDecimal(1));
//        return resourceDao.preApply(resourceApply);
//    }
//
//    @Override
//    @Transactional
//    public boolean realApply(String resourceApplyId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        boolean r1 = resourceDao.realApply(resourceApplyId);
//        // 记录任务信息
//        Task task = new Task();
//        String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        task.setId(taskId);
//        task.setApproveState(new BigDecimal(0));
//        task.setTaskType(new BigDecimal(2));
//        task.setCaseId(resourceApplyId);
//        boolean r2 = taskDao.addTask(task);
//        return r1&&r2;
//    }
//
//    @Override
//    public boolean delPreApply(String resourceApplyId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.delPreApply(resourceApplyId);
//    }
//
//    @Override
//    public List<ServiceMetaData> getAllServiceMetaData() {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getAllServiceMetaData();
//    }
//
//    @Override
//    public List<String> getGuestAccessResourceList() {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        List<String> list2 = resourceDao.getGuestResourceUrl();
//        return list2;
//    }
//
//    @Override
//    public boolean hasBeidouService(String resourceName) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.hasBeidouService(resourceName);
//    }
//
//    @Override
//    @Transactional
//    public boolean publishBeidouResource(BeidouInfo beidouInfo) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = beidouInfo.getResource();
//        BeidouMetaData beidouMetaData = beidouInfo.getBeidouMetaData();
//        // 发布用户的部门ID作为来源目录部门ID
//        String userId = resource.getPublishuserid();
//        User user = userDao.getUserById(userId);
//        resource.setCatalogDeptid(user.getDepId());
//        // 默认开启
//        if(resource.getRunstate()==null) {
//            resource.setRunstate(new BigDecimal(0));
//        }
//
//        String resourceId = resource.getId();
//        if(resourceId==null||resourceId.isEmpty()) {
//            resourceId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            resource.setId(resourceId);
//            beidouMetaData.setResourceId(resourceId);
//        }
//        String beidouMetaDataId = beidouMetaData.getId();
//        if(beidouMetaDataId==null||beidouMetaDataId.isEmpty()) {
//            beidouMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
//            beidouMetaData.setId(beidouMetaDataId);
//        }
//        // 存相对路径
//        String proxyUrl = EHCacheUtil.toBeidouProxyUrl("", resourceId, beidouMetaData.getRawUrl());
//        beidouMetaData.setPublishUrl(proxyUrl);
//
//        // 超级管理员有权限看
//        TokenInfo tokenInfo = new TokenInfo();
//        String tokenId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        tokenInfo.setId(tokenId);
//        tokenInfo.setUserId("0001");
//        tokenInfo.setResourceId(resourceId);
//        String token = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        tokenInfo.setToken(token);
//        tokenDao.addToken(tokenInfo);
//        // 自己也有权限看，如果不是超级管理员就加入，如果是超级管理员就不加了
//        if(!("0001".equals(userId))) {
//            TokenInfo tokenInfo2 = new TokenInfo();
//            String tokenId2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            tokenInfo2.setId(tokenId2);
//            tokenInfo2.setUserId(userId);
//            tokenInfo2.setResourceId(resourceId);
//            String token2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            tokenInfo2.setToken(token2);
//            tokenDao.addToken(tokenInfo2);
//        }
//
//        ServiceUtil.RESOURCE_ID = resourceId;
//        boolean r1 = resourceDao.addResource(resource);
//        boolean r2 = resourceDao.addBeidouMetaData(beidouMetaData);
//        if(r1&&r2) {
//            Task task = new Task();
//            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            task.setId(taskId);
//            task.setApproveState(new BigDecimal(0));
//            task.setTaskType(new BigDecimal(1));
//            task.setCaseId(resourceId);
//            return taskDao.addTask(task);
//        }else {
//            return false;
//        }
//    }
//
//    @Override
//    @Transactional
//    public boolean editBeidouResource(BeidouInfo beidouInfo) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = beidouInfo.getResource();
//        BeidouMetaData beidouMetaData = beidouInfo.getBeidouMetaData();
//        boolean r1 = resourceDao.updateResource(resource);
//        boolean r2 = resourceDao.updateBeidouMetaData(beidouMetaData);
//        return r1&&r2;
//    }
//
//    @Override
//    @Transactional
//    public BeidouInfo getBeidouInfo(String resourceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getResourceById(resourceId);
//        BeidouMetaData beidouMetaData = resourceDao.getBeidouMetaData(resourceId);
//        if(resource!=null&&beidouMetaData!=null) {
//            BeidouInfo beidouInfo = new BeidouInfo();
//            beidouInfo.setResource(resource);
//            beidouInfo.setBeidouMetaData(beidouMetaData);
//            return beidouInfo;
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public boolean hasPhoneService(String resourceName) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.hasPhoneService(resourceName);
//    }
//
//    @Override
//    @Transactional
//    public boolean publishPhoneResource(PhoneInfo phoneInfo) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = phoneInfo.getResource();
//        PhoneMetaData phoneMetaData = phoneInfo.getPhoneMetaData();
//        // 发布用户的部门ID作为来源目录部门ID
//        String userId = resource.getPublishuserid();
//        User user = userDao.getUserById(userId);
//        resource.setCatalogDeptid(user.getDepId());
//        // 默认开启
//        if(resource.getRunstate()==null) {
//            resource.setRunstate(new BigDecimal(0));
//        }
//
//        String resourceId = resource.getId();
//        if(resourceId==null||resourceId.isEmpty()) {
//            resourceId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            resource.setId(resourceId);
//            phoneMetaData.setResourceId(resourceId);
//        }
//        String phoneMetaDataId = phoneMetaData.getId();
//        if(phoneMetaDataId==null||phoneMetaDataId.isEmpty()) {
//            phoneMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
//            phoneMetaData.setId(phoneMetaDataId);
//        }
//        // 存相对路径
//        String proxyUrl = EHCacheUtil.toPhoneProxyUrl("", resourceId, phoneMetaData.getRawUrl());
//        phoneMetaData.setPublishUrl(proxyUrl);
//        phoneMetaData.setCreateTime(new Date());
//        ServiceUtil.RESOURCE_ID = resourceId;
//        boolean r1 = resourceDao.addResource(resource);
//        boolean r2 = resourceDao.publishPhoneMetaData(phoneMetaData);
//
//        // 超级管理员有权限看
//        TokenInfo tokenInfo = new TokenInfo();
//        String tokenId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        tokenInfo.setId(tokenId);
//        tokenInfo.setUserId("0001");
//        tokenInfo.setResourceId(resourceId);
//        String token = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        tokenInfo.setToken(token);
//        tokenDao.addToken(tokenInfo);
//        // 自己也有权限看，如果不是超级管理员就加入，如果是超级管理员就不加了
//        if(!"0001".equals(userId)) {
//            TokenInfo tokenInfo2 = new TokenInfo();
//            String tokenId2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            tokenInfo2.setId(tokenId2);
//            tokenInfo2.setUserId(userId);
//            tokenInfo2.setResourceId(resourceId);
//            String token2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            tokenInfo2.setToken(token2);
//            tokenDao.addToken(tokenInfo2);
//        }
//
//        if(r1&&r2) {
//            Task task = new Task();
//            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            task.setId(taskId);
//            task.setApproveState(new BigDecimal(0));
//            task.setTaskType(new BigDecimal(1));
//            task.setCaseId(resourceId);
//            return taskDao.addTask(task);
//        }else {
//            return false;
//        }
//    }
//
//    @Override
//    @Transactional
//    public boolean editPhoneResource(PhoneInfo phoneInfo) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = phoneInfo.getResource();
//        PhoneMetaData phoneMetaData = phoneInfo.getPhoneMetaData();
//        boolean r1 = resourceDao.updateResource(resource);
//        boolean r2 = resourceDao.editPhoneMetaData(phoneMetaData);
//        return r1&&r2;
//    }
//
//    @Override
//    @Transactional
//    public PhoneInfo getPhoneInfo(String resourceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getResourceById(resourceId);
//        PhoneMetaData phoneMetaData = resourceDao.getPhoneMetaData(resourceId);
//        if(resource!=null&&phoneMetaData!=null) {
//            PhoneInfo phoneInfo = new PhoneInfo();
//            phoneInfo.setResource(resource);
//            phoneInfo.setPhoneMetaData(phoneMetaData);
//            return phoneInfo;
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    @Transactional
//    public boolean hasVideoService(String resourceName) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.hasVideoService(resourceName);
//    }
//
//    @Override
//    @Transactional
//    public boolean publishVideoResource(VideoInfo videoInfo) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = videoInfo.getResource();
//        VideoMetaData videoMetaData = videoInfo.getVideoMetaData();
//        // 发布用户的部门ID作为来源目录部门ID
//        String userId = resource.getPublishuserid();
//        User user = userDao.getUserById(userId);
//        resource.setCatalogDeptid(user.getDepId());
//        // 默认开启
//        if(resource.getRunstate()==null) {
//            resource.setRunstate(new BigDecimal(0));
//        }
//
//        String resourceId = resource.getId();
//        if(resourceId==null||resourceId.isEmpty()) {
//            resourceId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            resource.setId(resourceId);
//            videoMetaData.setResourceId(resourceId);
//        }
//        String videoMetaDataId = videoMetaData.getId();
//        if(videoMetaDataId==null||videoMetaDataId.isEmpty()) {
//            videoMetaDataId = UUID.randomUUID().toString().trim().replaceAll("-","");
//            videoMetaData.setId(videoMetaDataId);
//        }
//        // 存相对路径
//        String proxyUrl = EHCacheUtil.toVideoProxyUrl("", resourceId, videoMetaData.getRawUrl());
//        videoMetaData.setPublishUrl(proxyUrl);
//        ServiceUtil.RESOURCE_ID = resourceId;
//        boolean r1 = resourceDao.addResource(resource);
//        boolean r2 = resourceDao.publishVideoMetaData(videoMetaData);
//
//        // 超级管理员有权限看
//        TokenInfo tokenInfo = new TokenInfo();
//        String tokenId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        tokenInfo.setId(tokenId);
//        tokenInfo.setUserId("0001");
//        tokenInfo.setResourceId(resourceId);
//        String token = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        tokenInfo.setToken(token);
//        tokenDao.addToken(tokenInfo);
//        // 自己也有权限看，如果不是超级管理员就加入，如果是超级管理员就不加了
//        if(!"0001".equals(userId)) {
//            TokenInfo tokenInfo2 = new TokenInfo();
//            String tokenId2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            tokenInfo2.setId(tokenId2);
//            tokenInfo2.setUserId(userId);
//            tokenInfo2.setResourceId(resourceId);
//            String token2 = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            tokenInfo2.setToken(token2);
//            tokenDao.addToken(tokenInfo2);
//        }
//
//        if(r1&&r2) {
//            Task task = new Task();
//            String taskId = UUID.randomUUID().toString().trim().replaceAll("-", "");
//            task.setId(taskId);
//            task.setApproveState(new BigDecimal(0));
//            task.setTaskType(new BigDecimal(1));
//            task.setCaseId(resourceId);
//            return taskDao.addTask(task);
//        }else {
//            return false;
//        }
//    }
//
//    @Override
//    @Transactional
//    public boolean editVideoResource(VideoInfo videoInfo) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = videoInfo.getResource();
//        VideoMetaData videoMetaData = videoInfo.getVideoMetaData();
//        boolean r1 = resourceDao.updateResource(resource);
//        boolean r2 = resourceDao.editVideoMetaData(videoMetaData);
//        return r1&&r2;
//    }
//
//    @Override
//    @Transactional
//    public VideoInfo getVideoInfo(String resourceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Resource resource = resourceDao.getResourceById(resourceId);
//        VideoMetaData videoMetaData = resourceDao.getVideoMetaData(resourceId);
//        if(resource!=null&&videoMetaData!=null) {
//            VideoInfo videoInfo = new VideoInfo();
//            videoInfo.setResource(resource);
//            videoInfo.setVideoMetaData(videoMetaData);
//            return videoInfo;
//        }else {
//            return null;
//        }
//    }
//
//    /**
//     * 根据userId获取可以访问的资源地址列表
//     * @param userId
//     * @return
//     */
//    @Override
//    public List<String> getCanAccessResourceList(String userId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        List<String> urlList = new ArrayList<String>();
//        //1. 自己的资源
//        List<String> list1 = resourceDao.getResourceUrlByUserId(userId);
//        //2. 公共的资源，guest的资源
//        List<String> list2 = resourceDao.getGuestResourceUrl();
//        //3. 以及正式申请的资源，并且以及被批准
//        List<String> list3 = resourceDao.getApplyResourceUrlByApplyUserId(userId);
//        //4. 如果是部门管理员，这该部门所有的资源都可以访问
//        List<String> list4 = new ArrayList<String>();
//        User userInfo = userDao.getUserById(userId);
//        String roleId = userInfo.getRoleId();
//        if(roleId.equals("5")||roleId.equals("6")) {
//            ;
//        }else {
//            String depId = userInfo.getDepId();
//            list4 = resourceDao.getResourceUrlByDept(depId);
//        }
//        urlList.addAll(list1);
//        urlList.addAll(list2);
//        urlList.addAll(list3);
//        urlList.addAll(list4);
//        return urlList;
//    }
//
//    /**
//     * 一个部门是否是另一个部门的后代，不一定是直接子部门，隔代也可以
//     * @param parentId 祖先部门ID
//     * @param depId 后代部门ID
//     * @return
//     */
//    private boolean isChildren(String parentId, String depId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        String id = departmentDao.getParentId(depId);
//        if(id.equals(parentId)) {
//            return true;
//        }else {
//            return isChildren(parentId,id);
//        }
//    }
//
//    @Override
//    public List<TokenInfo> getTokenInfo(String token) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getTokenInfo(token);
//    }
//
//    @Override
//    public int getResourceCount(String deptId, String userId, String themeId, String resourceType, String serviceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate, String staticDynamicType, List<String> resourceIds, String xmin, String xmax, String ymin, String ymax) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getResourceCount(deptId, userId, themeId, resourceType, serviceType, keyword, approveStatus, runStatus, startDate, endDate, staticDynamicType, resourceIds, xmin, xmax, ymin, ymax);
//    }
//
//    @Override
//    public List<Map<String, Object>> getResourceList(String deptId, String userId, String themeId, String resourceType, String serviceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate, String staticDynamicType, List<String> resourceIds, String xmin, String xmax, String ymin, String ymax, String sortField, String sortType, int pageNo, int pageSize) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        List<Map<String, Object>> list = resourceDao.getResourceListPaged(deptId, userId, themeId, resourceType, serviceType, keyword, approveStatus, runStatus, startDate, endDate, staticDynamicType, resourceIds, xmin, xmax, ymin, ymax, sortField, sortType, pageNo, pageSize);
//        return list;
//    }
//
//    @Override
//    public List<Map<String, Object>> getDeptResourceList(String deptId, String staticDynamicType, int pageNo, int pageSize) {
//        List<Map<String, Object>> list = resourceDao.getDeptResourceListPaged(deptId, staticDynamicType, pageNo, pageSize);
//        return list;
//    }
//
//    @Override
//    public List<Map<String, Object>> getUserResourceList(String userId, int pageNo, int pageSize) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        List<Map<String, Object>> list = resourceDao.getUserResourceListPaged(userId, pageNo, pageSize);
//        return list;
//    }
//
//    @Override
//    public int getDeptResourceCount(String deptId, String staticDynamicType) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getDeptResourceCount(deptId, staticDynamicType);
//    }
//
//    @Override
//    public int getUserResourceCount(String userId) {
//        return resourceDao.getUserResourceCount(userId);
//    }
//
////    @Override
////    public Map<String, Object> getResourceMetadataService(String resourceId) {
////        Map<String, Object> metadata = resourceDao.getResourceMetadataService(resourceId);
////        metadata.put("layerInfo", resourceDao.getResourceMetadataServiceSublayer(resourceId));
////        return metadata;
////    }
////    @Override
////    public Map<String, Object> getResourceMetadataFile(String resourceId) {
////        return resourceDao.getResourceMetadataFile(resourceId);
////    }
//
//    @Override
//    public Map<String, Object> getResourceDetails(String resourceId, String getUserId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Map<String, Object> resourceDetails = new HashMap<>();
//
//        //基本信息
//        Resource resource = resourceDao.getResourceById(resourceId);
//        resourceDetails.put("resource", resource);
//
//        if (resource == null)
//            return null;
//
//        //资源的类型
//        String serviceTypeId = resource.getServiceTypeId();
//        if (serviceTypeId != null && serviceTypeId != "") {
//            ServiceType resourceType = resourceDao.getServiceTypeById(serviceTypeId);
//            resourceDetails.put("resourceType", resourceType);
//        }
//
//        //用户与部门信息
//        String userId = resource.getPublishuserid();
//        if (userId != null && userId != "") {
//            User user = userDao.getUserById(userId);
//            resourceDetails.put("user", user);
//
//            if (user != null) {
//                String deptId = user.getDepId();
//                if (deptId != null && deptId != "") {
//                    Department department = departmentDao.getDepartmentInfo(deptId);
//                    resourceDetails.put("department", department);
//                }
//            }
//        }
//
//        //专题信息
//        String themeId = resource.getCatalogThemeid();
//        if (themeId != null && themeId != "") {
//            Theme theme = themeDao.getThemeInfo(themeId);
//            resourceDetails.put("theme", theme);
//        }
//
//        //资源元数据
//        int resourceType = resource.getResourcetype() != null ? resource.getResourcetype().intValue() : -1;
//        if (resourceType == 0) {         //文件
//            DataMetaData metadata = resourceDao.getDataMetaDataById(resourceId);
//            resourceDetails.put("metadata", metadata);
//        }
//        else if (resourceType == 1) {   //在线服务
//            ServiceMetaData metadata = resourceDao.getServiceMetaDataById(resourceId);
//            metadata.setRawUrl("");
//            resourceDetails.put("metadata", metadata);
//            List<ServiceLayerInfo> sublayers = resourceDao.getServiceLayerInfoListByResourceId(resourceId);
//            resourceDetails.put("sublayers", sublayers);
//        }
//        else if (resourceType == 2) {   //北斗
//            BeidouMetaData metadata = resourceDao.getBeidouMetaData(resourceId);
//            metadata.setRawUrl("");
//            resourceDetails.put("metadata", metadata);
//        }
//        else if (resourceType == 3) {   //手机
//            PhoneMetaData metadata = resourceDao.getPhoneMetaData(resourceId);
//            metadata.setRawUrl("");
//            resourceDetails.put("metadata", metadata);
//        }
//        else if (resourceType == 4) {   //视频
//            VideoMetaData metadata = resourceDao.getVideoMetaData(resourceId);
//            metadata.setRawUrl("");
//            metadata.setIp("");
//            metadata.setPort("");
//            resourceDetails.put("metadata", metadata);
//        }
//        else if (resourceType == 5) {   //时态信息
////            TimeServiceMetaData metadata = resourceDao.getTimeServiceMetaDataById(resourceId);
//            TimeServiceInfo metadata = getTimeServiceInfo(resourceId);
//            resourceDetails.put("metadata", metadata);
//        }
//
//        //统计信息: 申请量、访问量
//        Map<String, Object> statInfo = resourceDao.getResourceStatInfoById(resourceId);
//        resourceDetails.put("statInfo", statInfo);
//
//        // token
//        if (getUserId != null && getUserId != "") {
//            TokenInfo tokenInfo = resourceDao.getTokenByIds(resourceId, getUserId);
//            if (tokenInfo == null)
//                tokenInfo = resourceDao.getTokenByIds(resourceId, "-1");
//            resourceDetails.put("token", tokenInfo);
//        }
//
//        return resourceDetails;
//    }
//
//    @Override
//    public int deleteResourceById(String resourceId, String fileFolder) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//
//        //资源元数据
//        Resource resource = resourceDao.getResourceById(resourceId);
//        int resourceType = resource.getResourcetype() != null ? resource.getResourcetype().intValue() : -1;
//        if (resourceType == 0) {         //文件
//            //获取文件的fileId
//            String dataFileId = resourceDao.getDataFileIdByResourceId(resourceId);
//            if (dataFileId != null && dataFileId != "") {
//                //删除文件的元数据
//                resourceDao.deleteDataMetadataByResourceId(resourceId);
//                //删除文件
//                fileService.delFile(dataFileId, fileFolder);
//            }
//        }
//        else if (resourceType == 1) {   //在线服务
//            //获取服务的元数据metadataId
//            String metadataId = resourceDao.getServiceMetadataIdByResourceId(resourceId);
//            if (metadataId != null && metadataId != "") {
//                ServiceMetaData metadata = resourceDao.getServiceMetaDataById(resourceId);
//                // 删除ArcGIS服务（用shapefile发布的服务多了一个arcgis server服务，将该arcgis server服务删除，对其他服务不影响，比如正常的在线类型服务、文件类型服务）
//                String rawUrl = metadata.getRawUrl();
//                if (rawUrl != null && rawUrl != "") {
//                    deleteArcgisServerService(rawUrl);
//                }
//
//                //删除服务元数据
//                resourceDao.deleteServiceMetadataByResourceId(resourceId);
//                //删除服务的图层信息
//                resourceDao.deleteServiceLayerInfoByMetadataId(metadataId);
//            }
//        }
//        else if (resourceType == 2) {   //北斗
//            // 北斗
//            resourceDao.deleteBeidouMetadataByResourceId(resourceId);
//        }
//        else if (resourceType == 3) {   //手机
//            // 手机
//            resourceDao.deletePhoneMetadataByResourceId(resourceId);
//        }
//        else if (resourceType == 4) {   //视频
//            // 视频
//            resourceDao.deleteVideoMetadataByResourceId(resourceId);
//        }
//        else if (resourceType == 5) {   //时态信息
//            // 时态信息
//            resourceDao.deleteTimeServiceMetadataByResourceId(resourceId);
//        }
//
//        //获取资源的缩略图fileId
//        String thumbsFileId = resourceDao.getThumbsFileIdByResourceId(resourceId);
//        //删除资源
//        int result = resourceDao.deleteResourceById(resourceId);
//        //删除资源的缩略图
//        if (thumbsFileId != null && thumbsFileId != "")
//            fileService.delFile(thumbsFileId, fileFolder);
//
//        //删除token
//        resourceDao.deleteTokenByResourceId(resourceId);
//
//        return result;
//    }
//    public String deleteArcgisServerService(String serviceUrl) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        String url = IPUtil.getPythonDeleteServiceUrl(serviceUrl);
//        HttpClient httpClient = new DefaultHttpClient();
//        try {
//            HttpGet httpget = new HttpGet(url);
//            HttpResponse response = httpClient.execute(httpget);
//            HttpEntity entity = response.getEntity();
//            System.out.println("----------------------------------------");
//            System.out.println(response.getStatusLine());
//            if (entity != null) {
//            }
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public int updateResourceRunStatus(String resourceId, int status) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.updateResourceRunStatus(resourceId, status);
//    }
//
//    @Override
//    public int getUserApplyCount(String userId, String resourceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getUserApplyCount(userId, resourceType, keyword, approveStatus, runStatus, startDate, endDate);
//    }
//
//    @Override
//    public List<Map<String, Object>> getUserApplyList(String userId, String resourceType, String keyword, String approveStatus, String runStatus, String startDate, String endDate, String sortField, String sortType, int pageNo, int pageSize) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getUserApplyListPaged(userId, resourceType, keyword, approveStatus, runStatus, startDate, endDate, sortField, sortType, pageNo, pageSize);
//    }
//
//    @Override
//    public Map<String, Object> getResourceApplyDetails(String resourceApplyId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        Map<String, Object> applyDetails = new HashMap<>();
//
//        //基本信息
//        ResourceApply resourceApply = resourceDao.getResourceApplyById(resourceApplyId);
//        applyDetails.put("resourceApply", resourceApply);
//
//        //资源信息
//        String resourceId = resourceApply.getResourceid();
//        if (resourceId != null && resourceId != "") {
//            Resource resource = resourceDao.getResourceById(resourceId);
//            applyDetails.put("resource", resource);
//        }
//
//        //用户与部门信息
//        String userId = resourceApply.getApplyUserid();
//        if (userId != null && userId != "") {
//            User user = userDao.getUserById(userId);
//            applyDetails.put("user", user);
//
//            if (user != null) {
//                String deptId = user.getDepId();
//                if (deptId != null && deptId != "") {
//                    Department department = departmentDao.getDepartmentInfo(deptId);
//                    applyDetails.put("department", department);
//                }
//            }
//        }
//
//        return applyDetails;
//    }
//
//    @Override
//    public int deleteResourceApply(String userId, String resourceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.deleteResourceApply(userId, resourceId);
//
//    }
//
//    @Override
//    public int deleteToken(String userId, String resourceId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.deleteToken(userId, resourceId);
//    }
//
//    @Override
//    public List<Map<String, Object>> getResourceAppliedList(String resourceId, int pageNo, int pageSize) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getResourceAppliedListPaged(resourceId, pageNo, pageSize);
//    }
//
//    @Override
//    public Map<String, Object> getCatalogListByDeptId(String deptId, String userId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        //获取子部门
//        List<Department> departmentList = departmentDao.getDeptListByParentId(deptId);
//
//        List<Map<String, Object>> resourceList = getResourceList(deptId, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 10000);
//
//        //筛选有权限的资源
//        //自己发布列表或已获得授权列表
//        List<String> authorizedIdList = resourceDao.getAuthorizedResourceIdList(userId);
//        //面向所有用户免费使用列表
//        List<String> guestAuthList = resourceDao.getAuthorizedResourceIdList("-1");
//        List<Map<String, Object>> userResourceList = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < resourceList.size(); i++) {
//            Map<String, Object> resource = resourceList.get(i);
//
//            String resourceType = (String) resource.get("RESOURCETYPE").toString();
//            if (resourceType == null || resourceType != "") {
//                if (resourceType.equals("0") || resourceType.equals("3")) {
//                    continue;
//                }
//            }
//            else {
//                continue;
//            }
//
//            boolean flag = false;
//            String resourceId = (String) resource.get("ID");
//            if (resourceId != null && resourceId != "") {
//                for (int j = 0; j < authorizedIdList.size(); j++) {
//                    String tempId = authorizedIdList.get(j);
//                    if (resourceId.equals(tempId)) {
//                        userResourceList.add(resourceList.get(i));
//                        flag = true;
//                        break;
//                    }
//                }
//            }
//            if (!flag) {
//                for (int j = 0; j < guestAuthList.size(); j++) {
//                    String tempId = guestAuthList.get(j);
//                    if (resourceId.equals(tempId)) {
//                        userResourceList.add(resourceList.get(i));
//                        break;
//                    }
//                }
//            }
//        }
//
//        Map<String, Object> catalogList = new HashMap<>();
//        catalogList.put("deptId", deptId);
//        catalogList.put("departmentList", departmentList);
//        catalogList.put("resourceList", userResourceList);
//
//        return catalogList;
//    }
//
//    @Override
//    public Map<String, Object> getCatalogListByThemeId(String themeId, String userId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        //获取子专题
//        List<Theme> themeList = themeDao.getSecondThemes(themeId);
//        //获取资料列表
////        List<Resource> resourceList = resourceDao.getResourceListByThemeId(themeId);
//        List<Map<String, Object>> resourceList = getResourceList(null, null, themeId, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 10000);
//
//        //筛选有权限的资源
//        List<String> authorizedIdList = resourceDao.getAuthorizedResourceIdList(userId);
////        List<Resource> userResourceList = new ArrayList<Resource>();
//        List<Map<String, Object>> userResourceList = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < resourceList.size(); i++) {
////            Resource resource = resourceList.get(i);
////            String resourceId = resource.getId();
//            Map<String, Object> resource = resourceList.get(i);
//
//            String resourceType = (String) resource.get("RESOURCETYPE").toString();
//            if (resourceType == null || resourceType != "") {
//                if (resourceType.equals("0") || resourceType.equals("3")) {
//                    continue;
//                }
//            }
//            else {
//                continue;
//            }
//
//            String resourceId = (String) resource.get("ID");
//            if (resourceId != null && resourceId != "") {
//                for (int j = 0; j < authorizedIdList.size(); j++) {
//                    String tempId = authorizedIdList.get(j);
//                    if (resourceId.equals(tempId)) {
//                        userResourceList.add(resourceList.get(i));
//                        break;
//                    }
//                }
//            }
//        }
//
//        Map<String, Object> catalogList = new HashMap<>();
//        catalogList.put("themeId", themeId);
//        catalogList.put("themeList", themeList);
//        catalogList.put("resourceList", userResourceList);
//
//        return catalogList;
//    }
//
//    @Override
//    public VideoMetaData getVideoMetadata(String resourceId, String getUserId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        // token
//        if (getUserId != null && getUserId != "") {
//            TokenInfo tokenInfo = resourceDao.getTokenByIds(resourceId, getUserId);
//            if (tokenInfo != null) {
//                VideoMetaData metadata = resourceDao.getVideoMetaData(resourceId);
//                metadata.setRawUrl("");
//                return metadata;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<String> getAuthorizedResourceIdList(String userId) {
//        MultipleDataSource.setDataSourceKey("dataSource1");
//        return resourceDao.getAuthorizedResourceIdList(userId);
//    }
//
//}

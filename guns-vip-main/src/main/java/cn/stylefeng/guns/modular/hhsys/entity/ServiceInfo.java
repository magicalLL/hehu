package cn.stylefeng.guns.modular.hhsys.entity;

import java.util.List;

/**
 * Created by dell on 2016/3/10.
 */
public class ServiceInfo {

    private CityResource resource;
    private ServiceMatedata serviceMetaData;
    private List<ServiceLayerInfo> layerInfoList;

    public CityResource getResource() {
        return resource;
    }

    public void setResource(CityResource resource) {
        this.resource = resource;
    }

    public ServiceMatedata getServiceMetaData() {
        return serviceMetaData;
    }

    public void setServiceMetaData(ServiceMatedata serviceMetaData) {
        this.serviceMetaData = serviceMetaData;
    }

    public List<ServiceLayerInfo> getLayerInfoList() {
        return layerInfoList;
    }

    public void setLayerInfoList(List<ServiceLayerInfo> layerInfoList) {
        this.layerInfoList = layerInfoList;
    }
}

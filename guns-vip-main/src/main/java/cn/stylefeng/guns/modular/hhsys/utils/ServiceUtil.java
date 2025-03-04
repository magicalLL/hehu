package cn.stylefeng.guns.modular.hhsys.utils;

import cn.stylefeng.guns.modular.hhsys.entity.CityResource;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceInfo;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceLayerInfo;
import cn.stylefeng.guns.modular.hhsys.entity.ServiceMatedata;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by dell on 2016/3/10.
 */
public class ServiceUtil {

    public static String RESOURCE_ID = "";

    //获取layers个数（图层数）
    public static Integer getMAPSERVERLayersSize(String url) {
        String baseUrl = url;
        int size=0;
        url = url + "?f=json&pretty=true";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String str = EntityUtils.toString(entity);
                JSONObject jsonObj = JSON.parseObject(str);
                JSONArray array = jsonObj.getJSONArray("layers");
                size = array.size();
                return size;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }


    /**
     * 获取 WMS 类型服务的基本信息
     * @param url
     * @return
     */
    public static ServiceInfo getWMSBaseInfo(String url) {
        ServiceInfo serviceInfo = new ServiceInfo();
        url = url + "?request=GetCapabilities&service=WMS";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                String str = EntityUtils.toString(entity);
                System.out.println(str);
                // 解析XML
                Document document = DocumentHelper.parseText(str);
                Element root = document.getRootElement();
                Element element=root.element("Service");
                if(element==null) return null;
                String name =element.element("Name").getText();
                String title = element.element("Title").getText();
                String abstractValue = element.element("Abstract").getText();
                Element keywordElement = element.element("KeywordList");
                Element k = (Element)keywordElement.elements("Keyword").get(0);
                String keyword = k.getText();
                Element capability = root.element("Capability");
                Element layerElement = capability.element("Layer");
                Element box = layerElement.element("EX_GeographicBoundingBox");
                String west = box.element("westBoundLongitude").getText();
                String east = box.element("eastBoundLongitude").getText();
                String south = box.element("southBoundLatitude").getText();
                String north = box.element("northBoundLatitude").getText();
                List nodes = layerElement.elements("Layer");
                List<ServiceLayerInfo> serviceLayerInfoList = new ArrayList<ServiceLayerInfo>();
                for (Iterator it = nodes.iterator(); it.hasNext();) {
                    Element elm = (Element) it.next();
                    String layerName = elm.element("Name").getText();
                    String layerTitle = elm.element("Title").getText();
                    String layerAbstract = elm.element("Abstract").getText();
                    String spatialReference = "";
                    List<Element> elementList = elm.elements("CRS");
                    if(elementList.size()>1) {
                        String temp = elementList.get(0).getText();
                        String temp2 = elementList.get(1).getText();
                        if(temp.contains("CRS")) {
                            spatialReference = temp2;
                        }else {
                            spatialReference = temp;
                        }
                    }else {
                        spatialReference = elementList.get(0).getText();
                    }
                    ServiceLayerInfo serviceLayerInfo = new ServiceLayerInfo();
                    serviceLayerInfo.setLayerName(layerName);
                    serviceLayerInfo.setLayerAliasname(layerTitle);
                    serviceLayerInfo.setLayerDesc(layerAbstract);
                    serviceLayerInfoList.add(serviceLayerInfo);
                    serviceLayerInfo.setSpatialReference(spatialReference);
                    serviceLayerInfo.setGeometryType("");
                }
                System.out.println("name----------->"+name);
                System.out.println("title----------->"+title);
                System.out.println("abstractDesc----------->"+abstractValue);
                System.out.println("keyword----------->"+keyword);
                System.out.println("west----------->"+west);
                System.out.println("east----------->"+east);
                System.out.println("south----------->"+south);
                System.out.println("north----------->"+north);
                ServiceMatedata serviceMetaData = new ServiceMatedata();
                serviceMetaData.setName(name);
                serviceMetaData.setTitle(title);
                serviceMetaData.setWest(west);
                serviceMetaData.setEast(east);
                serviceMetaData.setSouth(south);
                serviceMetaData.setNorth(north);
                serviceMetaData.setTypeVersion("1.0");
                CityResource resource = new CityResource();
                resource.setName(name);
                resource.setKeyword(keyword);
                resource.setAbstractValue(abstractValue);
                resource.setAliasname(name);
                serviceInfo.setResource(resource);
                serviceInfo.setServiceMetaData(serviceMetaData);
                serviceInfo.setLayerInfoList(serviceLayerInfoList);
            }else {
                return null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return serviceInfo;
    }

    /**
     * 获取MAPSERVER类型服务的基本信息
     * @param url
     * @return
     */
    public static ServiceInfo getMAPSERVERBaseInfo(String url) {
        ServiceInfo serviceInfo = new ServiceInfo();
        String baseUrl = url;
        url = url + "?f=json&pretty=true";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                String str = EntityUtils.toString(entity);
                System.out.println(str);
                // 解析JSON
                JSONObject jsonObj = JSON.parseObject(str);
                String version = jsonObj.getString("currentVersion");
                //if(version==null) return null;
                String description = jsonObj.getString("description");
                String spatialReference = jsonObj.getJSONObject("spatialReference").getString("wkid");
                String west = jsonObj.getJSONObject("fullExtent").getString("xmin");
                String east = jsonObj.getJSONObject("fullExtent").getString("xmax");
                String south = jsonObj.getJSONObject("fullExtent").getString("ymin");
                String north = jsonObj.getJSONObject("fullExtent").getString("ymax");
                String name = jsonObj.getJSONObject("documentInfo").getString("Title");
                String abstractDesc = jsonObj.getJSONObject("documentInfo").getString("Comments");
                String keyword = jsonObj.getJSONObject("documentInfo").getString("Keywords");
                JSONArray array = jsonObj.getJSONArray("layers");
                List<ServiceLayerInfo> serviceLayerInfoList = new ArrayList<ServiceLayerInfo>();
                for (Iterator it = array.iterator(); it.hasNext();) {
                    JSONObject json = (JSONObject)it.next();
                    String layerName = json.getString("name");
                    String layerIndex = json.getString("id");
                    ServiceLayerInfo serviceLayerInfo = new ServiceLayerInfo();
                    serviceLayerInfo.setLayerName(layerName);
                    serviceLayerInfo.setLayerAliasname(layerName);
                    serviceLayerInfo.setLayerIndex(new BigDecimal(layerIndex));
                    String url2 = baseUrl + "/" + layerIndex + "?f=json&pretty=true";
                    System.out.println("url2---------->"+url2);
                    HttpClient httpClient2 = new DefaultHttpClient();
                    HttpGet httpget2 = new HttpGet(url2);
                    HttpResponse response2 = httpClient2.execute(httpget2);
                    HttpEntity entity2 = response2.getEntity();
                    System.out.println("----------------------------------------");
                    System.out.println(response2.getStatusLine());
                    if (entity2 != null) {
                        String str2 = EntityUtils.toString(entity2);
                        System.out.println(str2);
                        // 解析JSON
                        JSONObject jsonObj2 = JSON.parseObject(str2);
                        String geometryType = jsonObj2.getString("geometryType");
                        String description2 = jsonObj2.getString("description");
                        String spatialReference2 = jsonObj2.getJSONObject("extent").getJSONObject("spatialReference").getString("wkid");
                        serviceLayerInfo.setGeometryType(geometryType);
                        serviceLayerInfo.setLayerDesc(description2);
                        serviceLayerInfo.setSpatialReference(spatialReference2);
                    }
                    serviceLayerInfoList.add(serviceLayerInfo);
                }
                CityResource resource = new CityResource();
                resource.setName(name);
                resource.setKeyword(keyword);
                resource.setAbstractValue(abstractDesc);
                resource.setAliasname(name);
                ServiceMatedata serviceMetaData = new ServiceMatedata();
                serviceMetaData.setName(name);
                serviceMetaData.setTitle(name);
                serviceMetaData.setWest(west);
                serviceMetaData.setEast(east);
                serviceMetaData.setSouth(south);
                serviceMetaData.setNorth(north);
                serviceMetaData.setTypeVersion(version);
                serviceMetaData.setOtherDescription(description);
                serviceMetaData.setTypeVersion(version);

                serviceInfo.setResource(resource);
                serviceInfo.setServiceMetaData(serviceMetaData);
                serviceInfo.setLayerInfoList(serviceLayerInfoList);
            }else {
                return null;
            }
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return serviceInfo;
    }

    /**
     * 获取WMTS类型服务的基本信息
     * @param url
     * @return
     */
    public static ServiceInfo getWMTSBaseInfo(String url) {
        ServiceInfo serviceInfo = new ServiceInfo();
        url = url + "?request=GetCapabilities&service=WMTS";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                String str = EntityUtils.toString(entity,"UTF-8");
                System.out.println(str);
                str=str.substring(str.indexOf("<"));
                System.out.println("str--------->"+str);
                // 解析XML
                Document document = DocumentHelper.parseText(str);
                Element root = document.getRootElement();
                Element element=root.element("ServiceIdentification");
                if(element==null) return null;
                String name =element.element("Title").getText();
                System.out.println("name--->"+name);
                String title = element.element("Title").getText();
                String abstractDesc = element.element("Abstract").getText();
                String serviceTypeVersion = element.element("ServiceTypeVersion").getText();
                Element keywordElement = element.element("Keywords");
                List nodes = keywordElement.elements("Keyword");
                StringBuilder sb = new StringBuilder();
                for(Iterator it = nodes.iterator(); it.hasNext();) {
                    Element e = (Element)it.next();
                    sb.append(e.getText());
                    sb.append(",");
                }
                String keyword = sb.substring(0,sb.length()-1);
                Element contents = root.element("Contents");
                String lower = contents.element("Layer").element("BoundingBox").element("LowerCorner").getText();
                String upper = contents.element("Layer").element("BoundingBox").element("UpperCorner").getText();
                String west = lower.split(" ")[0];
                String south = lower.split(" ")[1];
                String east = upper.split(" ")[0];
                String north = upper.split(" ")[1];
                CityResource resource = new CityResource();
                resource.setName(name);
                resource.setKeyword(keyword);
                resource.setAbstractValue(abstractDesc);
                resource.setAliasname(name);
                ServiceMatedata serviceMetaData = new ServiceMatedata();
                serviceMetaData.setName(name);
                serviceMetaData.setTitle(title);
                serviceMetaData.setWest(west);
                serviceMetaData.setEast(east);
                serviceMetaData.setSouth(south);
                serviceMetaData.setNorth(north);
                serviceMetaData.setTypeVersion(serviceTypeVersion);
                //Element layerElement = contents.element("Layer");
                List layerNodes = contents.elements("Layer");
                List<ServiceLayerInfo> serviceLayerInfoList = new ArrayList<ServiceLayerInfo>();
                for (Iterator it = layerNodes.iterator(); it.hasNext();) {
                    Element elm = (Element) it.next();
                    String layerName = elm.element("Title").getText();
                    String layerTitle = elm.element("Title").getText();
                    String layerAbstract = elm.element("Abstract").getText();
                    String crs = "";
                    String tile = elm.element("TileMatrixSetLink").element("TileMatrixSet").getText();
                    List tileNodes = contents.elements("TileMatrixSet");
                    for(Iterator iterator = tileNodes.iterator();iterator.hasNext();) {
                        Element element1 = (Element)iterator.next();
                        String identifier = element1.element("Identifier").getText();
                        if(identifier.equals(tile)) {
                            crs = element1.element("SupportedCRS").getText();
                            break;
                        }
                    }
                    ServiceLayerInfo serviceLayerInfo = new ServiceLayerInfo();
                    serviceLayerInfo.setLayerName(layerName);
                    serviceLayerInfo.setLayerAliasname(layerTitle);
                    serviceLayerInfo.setLayerDesc(layerAbstract);
                    serviceLayerInfoList.add(serviceLayerInfo);
                    serviceLayerInfo.setSpatialReference(crs);
                }
                serviceInfo.setResource(resource);
                serviceInfo.setServiceMetaData(serviceMetaData);
                serviceInfo.setLayerInfoList(serviceLayerInfoList);
            }else {
                return null;
            }
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return serviceInfo;
    }

    public static ServiceInfo getWCSBaseInfo(String url) {
        ServiceInfo serviceInfo = new ServiceInfo();
        url = url + "?request=GetCapabilities&service=WCS";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                String str = EntityUtils.toString(entity, "UTF-8");
                System.out.println(str);
                // 解析XML
                Document document = DocumentHelper.parseText(str);
                Element root = document.getRootElement();
                Element element=root.element("ServiceIdentification");
                if(element==null) return null;
                String name =element.element("Title").getText();
                System.out.println("name--->"+name);
                String title = element.element("Title").getText();
                String abstractDesc = element.element("Abstract").getText();
                String serviceTypeVersion = element.element("ServiceTypeVersion").getText();
                Element keywordElement = element.element("Keywords");
                List nodes = keywordElement.elements("Keyword");
                StringBuilder sb = new StringBuilder();
                for(Iterator it = nodes.iterator(); it.hasNext();) {
                    Element e = (Element)it.next();
                    sb.append(e.getText());
                    sb.append(",");
                }
                String keyword = sb.substring(0,sb.length()-1);
                System.out.println("keyword---------->"+keyword);
                List layerNodes = root.element("Contents").elements("CoverageSummary");
                List<ServiceLayerInfo> serviceLayerInfoList = new ArrayList<ServiceLayerInfo>();
                for(Iterator iterator = layerNodes.iterator(); iterator.hasNext();) {
                    Element ele = (Element)iterator.next();
                    String layerName = ele.element("CoverageId").getText();
                    String layerTitle = ele.element("CoverageId").getText();
                    ServiceLayerInfo serviceLayerInfo = new ServiceLayerInfo();
                    serviceLayerInfo.setLayerName(layerName);
                    serviceLayerInfo.setLayerAliasname(layerTitle);
                    serviceLayerInfo.setLayerDesc("");
                    String crs = ele.element("BoundingBox").attributeValue("crs");
                    String lower = ele.element("BoundingBox").element("LowerCorner").getText();
                    String upper = ele.element("BoundingBox").element("UpperCorner").getText();
                    String west = lower.split(" ")[0];
                    String south = lower.split(" ")[1];
                    String east = upper.split(" ")[0];
                    String north = upper.split(" ")[1];
                    serviceLayerInfo.setWest(west);
                    serviceLayerInfo.setEast(east);
                    serviceLayerInfo.setNorth(north);
                    serviceLayerInfo.setSouth(south);
                    serviceLayerInfo.setSpatialReference(crs);
                    serviceLayerInfoList.add(serviceLayerInfo);
                }
                CityResource resource = new CityResource();
                resource.setName(name);
                resource.setKeyword(keyword);
                resource.setAbstractValue(abstractDesc);
                resource.setAliasname(name);
                ServiceMatedata serviceMetaData = new ServiceMatedata();
                serviceMetaData.setName(name);
                serviceMetaData.setTitle(title);
                serviceMetaData.setWest("");
                serviceMetaData.setEast("");
                serviceMetaData.setSouth("");
                serviceMetaData.setNorth("");
                serviceMetaData.setTypeVersion(serviceTypeVersion);
                serviceInfo.setResource(resource);
                serviceInfo.setServiceMetaData(serviceMetaData);
                serviceInfo.setLayerInfoList(serviceLayerInfoList);
            }else {
                return null;
            }
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return serviceInfo;
    }

    public static ServiceInfo getWFSBaseInfo(String url) {
        ServiceInfo serviceInfo = new ServiceInfo();
        url = url + "?request=GetCapabilities&service=WFS";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                String str = EntityUtils.toString(entity, "UTF-8");
                System.out.println(str);
                // 解析XML
                Document document = DocumentHelper.parseText(str);
                Element root = document.getRootElement();
                Element element=root.element("ServiceIdentification");
                if(element==null) return null;
                String name =element.element("Title").getText();
                System.out.println("name--->"+name);
                String title = element.element("Title").getText();
                String abstractDesc = element.element("Abstract").getText();
                Element keywordElement = element.element("Keywords");
                List nodes = keywordElement.elements("Keyword");
                StringBuilder sb = new StringBuilder();
                for(Iterator it = nodes.iterator(); it.hasNext();) {
                    Element e = (Element)it.next();
                    sb.append(e.getText());
                    sb.append(",");
                }
                String keyword = sb.substring(0,sb.length()-1);
                System.out.println("keyword---------->"+keyword);
                String serviceTypeVersion = element.element("ServiceTypeVersion").getText();
                List layerNodes = root.element("FeatureTypeList").elements("FeatureType");
                List<ServiceLayerInfo> serviceLayerInfoList = new ArrayList<ServiceLayerInfo>();
                for(Iterator iterator = layerNodes.iterator(); iterator.hasNext();) {
                    Element ele = (Element)iterator.next();
                    String layerName = ele.element("Name").getText();
                    String layerTitle = ele.element("Title").getText();
                    String layerAbstract = ele.element("Abstract").getText();
                    String crs = ele.element("DefaultCRS").getText();
                    ServiceLayerInfo serviceLayerInfo = new ServiceLayerInfo();
                    serviceLayerInfo.setLayerName(layerName);
                    serviceLayerInfo.setLayerAliasname(layerTitle);
                    serviceLayerInfo.setLayerDesc(layerAbstract);
                    serviceLayerInfo.setSpatialReference(crs);
                    String lower = ele.element("WGS84BoundingBox").element("LowerCorner").getText();
                    String upper = ele.element("WGS84BoundingBox").element("UpperCorner").getText();
                    String west = lower.split(" ")[0];
                    String south = lower.split(" ")[1];
                    String east = upper.split(" ")[0];
                    String north = upper.split(" ")[1];
                    serviceLayerInfo.setWest(west);
                    serviceLayerInfo.setEast(east);
                    serviceLayerInfo.setNorth(north);
                    serviceLayerInfo.setSouth(south);
                    serviceLayerInfoList.add(serviceLayerInfo);
                }
                CityResource resource = new CityResource();
                resource.setName(name);
                resource.setKeyword(keyword);
                resource.setAbstractValue(abstractDesc);
                resource.setAliasname(name);
                ServiceMatedata serviceMetaData = new ServiceMatedata();
                serviceMetaData.setName(name);
                serviceMetaData.setTitle(title);
                serviceMetaData.setWest("");
                serviceMetaData.setEast("");
                serviceMetaData.setSouth("");
                serviceMetaData.setNorth("");
                serviceMetaData.setTypeVersion(serviceTypeVersion);
                serviceInfo.setResource(resource);
                serviceInfo.setServiceMetaData(serviceMetaData);
                serviceInfo.setLayerInfoList(serviceLayerInfoList);
            }else {
                return null;
            }
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return serviceInfo;
    }

    public static ServiceInfo getDefaultServiceInfo() {
        CityResource resource = new CityResource();
        resource.setName("service");
        resource.setKeyword("");
        resource.setAbstractValue("");
        resource.setAliasname("服务");
        ServiceMatedata serviceMetaData = new ServiceMatedata();
        serviceMetaData.setTypeVersion("1.0");
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setResource(resource);
        serviceInfo.setServiceMetaData(serviceMetaData);
        serviceInfo.setLayerInfoList(null);
        return serviceInfo;
    }

    // 测试类
//    @Test
    public void jUnitTest() {
//        ServiceInfo serviceInfo = getWMSBaseInfo("https://sampleserver1.arcgisonline.com/ArcGIS/services/Specialty/ESRI_StatesCitiesRivers_USA/MapServer/WMSServer");
//        assert(serviceInfo.getResource().getName().equals("WMS"));
//        ServiceInfo serviceInfo = getMAPSERVERBaseInfo("http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/Demographics/ESRI_Census_USA/MapServer");
//        assert(serviceInfo.getResource().getName().equals("USCensus"));
//        ServiceInfo serviceInfo = getWMTSBaseInfo("http://map4.shanghai-map.net/WMTS/kvp/services/SHMAP_D/MapServer/TDTWMTSServer");
//        ServiceInfo serviceInfo = getWMTSBaseInfo("http://t0.tianditu.com/vec_c/wmts");
//        assert(serviceInfo.getResource().getName().equals("在线地图服务"));
//        ServiceInfo serviceInfo = getWFSBaseInfo("http://gisserver.tianditu.com/TDTService/wfs");
//        assert(serviceInfo.getResource().getName().equals("My GISServer WFS"));
        ServiceInfo serviceInfo = getWCSBaseInfo("http://192.168.1.155:9090/geoserver/nurc/wcs");
        assert(serviceInfo.getResource().getName().equals("Web Coverage Service"));
    }
}

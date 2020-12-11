package cn.stylefeng.guns.modular.hhsys.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dell on 2016/3/9.
 */
public class IPUtil {

    public static String getIP() {
        try {
            Properties prop = new Properties();
            //InputStream in = new BufferedInputStream(new FileInputStream("ipconfig.properties"));
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            return prop.getProperty("ip");
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getPort() {
        try {
            Properties prop = new Properties();
            //InputStream in = new BufferedInputStream(new FileInputStream("ipconfig.properties"));
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            return prop.getProperty("port");
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取远程存储服务器地址
     * @return
     */
    public static String getRemotePath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServer")+":"+prop.getProperty("remoteServerPort");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取远程存储服务器盘符
     * @return
     */
    public static String getRemoteServerMapping() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取缩略图存储地址
     * @return
     */
    public static String getThumbnailPath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping") + ":/" + prop.getProperty("thumbnailFolder");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取新闻图片地址
     * @return
     */
    public static String getNewsPath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping") + ":/" + prop.getProperty("newsFolder");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取公告图片地址
     * @return
     */
    public static String getNoticePath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping") + ":/" + prop.getProperty("noticeFolder");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取消息图片地址
     * @return
     */
    public static String getMessagePath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping") + ":/" + prop.getProperty("messageFolder");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取离线数据地址
     * @return
     */
    public static String getDataPath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping") + ":/" + prop.getProperty("dataFolder");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取其他文件地址
     * @return
     */
    public static String getOtherFilePath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("remoteServerMapping") + ":/" + prop.getProperty("otherFolder");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取Python服务发布的地址
     * @param serviceName
     * @param fileId
     * @param serviceType
     * @param type
     * @return
     */
    public static String getPythonPublishServiceUrl(String serviceName,String fileId,String serviceType,String type) {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("pythonServiceUrl") + "?serviceName="+serviceName+"&fileId="+
                    fileId+"&serviceType="+serviceType+"&type="+type;
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取Python删除arcgis server服务的地址
     * @param serviceUrl
     * @return
     */
    public static String getPythonDeleteServiceUrl(String serviceUrl) {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("pythonDeleteServiceUrl") + "?serverUrl=" + serviceUrl;
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getBeiDouPrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("beidou");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getMapTourPrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("maptour");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getPhonePrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("phone");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getVideoPrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("video");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getLocationServicePrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("locationService");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getPositionPrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("position");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getTrailPrefix() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("trail");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getShapeFilePath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("trail");
            return url;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }


    /**
     * 获取规划成果结果路径
     * @return
     */
    public static String getPlanResultsPath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("planresults");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    /**
     * 获取文件下载上传路径
     * @return
     */
    public static String getCityDfileWebPath() {
        try {
            Properties prop = new Properties();
            InputStream in = IPUtil.class.getClassLoader().getResourceAsStream("ipconfig.properties");
            prop.load(in);
            String url = prop.getProperty("cityDfileWebPath");
            return url;
        }catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


}

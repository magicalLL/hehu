package cn.stylefeng.guns.modular.test;

import cn.stylefeng.guns.modular.hhsys.service.impl.UrlsServiceImpl;
import org.springframework.boot.json.GsonJsonParser;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class Test {



    public static void main(String args[]) throws UnknownHostException {
//        this.testInetAddress();
//        this.getUUIDs(10);
//        this.oprerateString();

        testGson();

    }

    private static void testGson(){
        GsonJsonParser gson = new GsonJsonParser();
    }

    public void oprerateString(){
        String file = "1234_56.pdf";
        String filename = file.substring(0,file.lastIndexOf("."));
        String filetype = file.substring(file.lastIndexOf(".") + 1);
        System.out.println("filename : " + filename + " filetype: " + filetype);
        System.out.println(file.replaceAll("_",""));

//        String fileSavePath = "/assets/expend/file/";
//
//        String path = System.getProperty("user.dir");
//        path = path + "\\guns-base-support\\guns-sys\\src\\main\\webapp\\assets\\expend\\file\\";
////        FileUtil.mkParentDirs(newFile);
//        System.out.println("path:  "+path);
//
//        String filePath = ConstantsContext.getFileUploadPath();
//        System.out.println("filepath:  "+filePath);
    }

    public void getUUIDs(int size){
        for(int i = 0;i<size; i++) {
            String id = UUID.randomUUID().toString();
            id = id.replaceAll("-", "");
            System.out.println(id);
        }

    }

    public void testInetAddress() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        UrlsServiceImpl urlsService = new UrlsServiceImpl();
        System.out.println(addr);
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getAddress());
        System.out.println(addr.getHostName());
        System.out.println(addr.getCanonicalHostName());
        System.out.println(urlsService.getById("6").getUrl());
    }


}

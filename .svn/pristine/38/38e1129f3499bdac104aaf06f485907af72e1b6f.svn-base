package cn.stylefeng.guns.modular.hhsys.utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class ImgUtils {

    /**
     * 根据给定大小压缩图片
     * @param bytes
     * @param desFileSize 文件大小
     * @param accuracy 文件精度 小于0.9
     * @return
     * @throws IOException
     */
    public static byte[] commpressPicCycle(byte[] bytes, long desFileSize, double accuracy) throws IOException {
        // 获取目标图片
//        File imgFile = new File(desPath);
//        long fileSize = imgFile.length();
        long fileSize = bytes.length;
        System.out.println("=====fileSize======== "+fileSize);
        // 判断图片大小是否小于指定图片大小
        if(fileSize <= desFileSize * 1024){
            return bytes;
        }
        //计算宽高
        BufferedImage bim = ImageIO.read(new ByteArrayInputStream(bytes));
        int imgWidth = bim.getWidth();
        System.out.println(imgWidth+"====imgWidth=====");
        int imgHeight = bim.getHeight();
        int desWidth = new BigDecimal(imgWidth).multiply( new BigDecimal(accuracy)).intValue();
        System.out.println(desWidth+"====desWidth=====");
        int desHeight = new BigDecimal(imgHeight).multiply( new BigDecimal(accuracy)).intValue();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
        Thumbnails.of(new ByteArrayInputStream(bytes)).size(desWidth, desHeight).outputQuality(accuracy).toOutputStream(baos);
        //如果不满足要求,递归直至满足要求
        return commpressPicCycle(baos.toByteArray(), desFileSize, accuracy);
    }

    /**
     *
     * @param len 文件长度
     * @param size 大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}

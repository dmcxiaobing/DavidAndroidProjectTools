package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import java.io.UnsupportedEncodingException;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.base64.encoder.BASE64Decoder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.base64.encoder.BASE64Encoder;

/**
 * base64的加密以及解密（转换）
 */
public class Base64Utils {

    /**
     * 加密 此方法和getBase64一样的只是没有返回null而是""而已
     */
    public static String encodeBase(String src) {
        if (src == null) {
            return "";
        }
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            //设置utf-8编码
            return encoder.encode(src.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

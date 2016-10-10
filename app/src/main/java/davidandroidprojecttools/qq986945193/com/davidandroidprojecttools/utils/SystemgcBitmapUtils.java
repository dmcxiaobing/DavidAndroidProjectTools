package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.graphics.Bitmap;

/**
 * @author ：程序员小冰
 *
 * @新浪微博 ：http://weibo.com/mcxiaobing
 *
 * @GitHub: https://github.com/QQ986945193
 *
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 *
 * 封装一个简单的 回收Bitmap工具类
 *
 */
public class SystemgcBitmapUtils {
    /**
     * 回收bitmap
     * @param bitmapTakePhoto
     */
    public static void SystemGcBitmap(Bitmap bitmapTakePhoto) {
        if (bitmapTakePhoto != null && !bitmapTakePhoto.isRecycled()) {
            // 回收并且置为null
            bitmapTakePhoto.recycle();
            bitmapTakePhoto = null;
        }
        System.gc();
    }
}

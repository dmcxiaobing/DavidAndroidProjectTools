package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * ScreenUtils
 */
public class ScreenUtils {
    private static int screenW;
    private static int screenH;
    private static float screenDensity;

    /**
     * 获取设备屏幕宽度
     */
    public static int getScreenW(Context context) {
        if (screenW == 0) {
            initScreen(context);
        }
        return screenW;
    }

    public static int getScreenWidth(Context c) {
        return c.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获取设备屏幕高度
     */
    public static int getScreenH(Context context) {
        if (screenH == 0) {
            initScreen(context);
        }
        return screenH;
    }

    /**
     * 获取设备屏幕密度
     */
    public static float getScreenDensity(Context context) {
        if (screenDensity == 0) {
            initScreen(context);
        }
        return screenDensity;
    }

    private static void initScreen(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        screenW = metric.widthPixels;
        screenH = metric.heightPixels;
        screenDensity = metric.density;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) (dpValue * getScreenDensity(context) + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        return (int) (pxValue / getScreenDensity(context) + 0.5f);
    }
}

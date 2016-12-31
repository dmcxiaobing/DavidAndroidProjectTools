package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 弹出Toast 封装类
 */
public class ToastUtils {
    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast  这里message 代表的是的CharSequence路径
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast 这里message 代表的是的CharSequence路径
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间  这里message 代表的是的CharSequence路径
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * Toast中间显示
     **/
    public static void middleShow(Context content, String message) {
        Toast toast = Toast.makeText(content, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        /* LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(getApplicationContext());
		imageCodeProject.setImageResource(R.drawable.icon);
		toastView.addView(imageCodeProject, 0);*/
        toast.show();
    }
}

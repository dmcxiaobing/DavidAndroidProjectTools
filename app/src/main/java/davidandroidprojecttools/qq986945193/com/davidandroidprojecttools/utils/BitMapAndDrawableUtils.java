package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * bitmap和drawable相互转换代码
 */
public class BitMapAndDrawableUtils {

    // 从资源中获取Bitmap
    public static Bitmap getBitmapFromResources(Activity act, int resId) {
        Resources res = act.getResources();
        return BitmapFactory.decodeResource(res, resId);
    }

    /**
     * 从资源中获取 drawable
     *
     * @return drawable
     */
    public static Drawable getDrawableFromResources(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    // byte[] → Bitmap
    public static Bitmap convertBytes2Bimap(byte[] b) {
        if (b.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    // Bitmap → byte[]
    public static byte[] convertBitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    // 1)Drawable → Bitmap
    public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * drawable转换为bitmap
     *
     * @param drawable
     * @return
     */
    // )Drawable → Bitmap
    public static Bitmap convertDrawable2BitmapSimple(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    /**
     * bitmap转换为 drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable convertBitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        // 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        return bd;
    }
}

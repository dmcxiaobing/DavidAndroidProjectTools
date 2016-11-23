package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * Picasso与Imageloader显示图片的功能封装
 */
public class PicassoWithImageLoaderImageViewUtils {
    /**
     * Picasso加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void withImageView(Context context, String imageUrl, ImageView imageView) {
        if (imageView != null && context != null && imageUrl != null) {

            Picasso.with(context).load(imageUrl)
                    .fit()
                    //设置加载之前的默认图片
                    .placeholder(R.mipmap.ic_launcher)
                    //设置加载失败的默认图片
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }

    }

    /**
     * Picasso加载图片 将图像进行变换，以更好的适应布局控件等，减小内存开销。
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void withImageViewResize(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (imageView != null && context != null && imageUrl != null) {
            Picasso.with(context)
                    .load(imageUrl)
                    .fit()
                    //设置加载之前的默认图片
                    .placeholder(R.mipmap.ic_launcher)
                    //设置加载失败的默认图片
                    .error(R.mipmap.ic_launcher)
                    .resize(width, height) //200,200
                    .centerCrop()
                    .into(imageView);
        }

    }

    /**
     * Picasso加载图片 将图像进行变换，实现图像缩小为原来的一半，减小内存开销。
     * https://github.com/QQ986945193
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void withImageViewCustomResize(Context context, String imageUrl, ImageView imageView, int width, int height) {
        if (imageView != null && context != null && imageUrl != null) {
            Picasso.with(context).load(imageUrl)
                    .fit()
                    //设置加载之前的默认图片
                    .placeholder(R.mipmap.ic_launcher)
                    //设置加载失败的默认图片
                    .error(R.mipmap.ic_launcher)
                    .transform(new CropSquareTransformation()).into(imageView);
        }

    }


    /**
     * 自定义接口，实现图像缩小为原来的一半
     */
    public static class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }


    /**
     * Imageloader加载图片
     *
     * @param imageUrl
     * @param imageView
     */
    public static void displayImage(String imageUrl, ImageView imageView) {
        if (imageView != null && imageUrl != null) {
            ImageLoader.getInstance().displayImage(imageUrl, imageView);
        }

    }


}

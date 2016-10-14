package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.PicassoAndImageloaderFrescoActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */
public class GlideAndFrescoUtils {
    /**
     * Glide加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void withImageView(Context context, String imageUrl, ImageView imageView) {
        if (imageView != null && context != null && imageUrl != null) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView);
        }


    }

    /**
     * Fresco显示网络中的图片功能
     *
     * @param mContext          上下文
     * @param imageUrl          图片的url
     * @param mSimpleDraweeView setPlaceHolderImage()加在之前的图片，setRetryImage()重新加载的图片
     *                          <p/>
     *                          setFailureImage()加载失败的图片,setIsCircle()是否进行圆角模式
     *                          <p/>
     *                          <p/>
     *                          这里全部使用同一张，具体看项目来进行更改，这里是直接设置。
     */
    public static void frescoDisplayInternetImage(Context mContext, String imageUrl, SimpleDraweeView mSimpleDraweeView) {
        if (mSimpleDraweeView != null && mContext != null && imageUrl != null) {
            /*显示和网络中的一张图片*/
            new FrescoImageUtils.LoadImageFrescoBuilder(mContext, mSimpleDraweeView, imageUrl).setPlaceHolderImage(BitMapAndDrawableUtils.getDrawableFromResources(mContext, R.mipmap.ic_launcher))
                    .setRetryImage(BitMapAndDrawableUtils.getDrawableFromResources(mContext, R.mipmap.ic_launcher)).setFailureImage(BitMapAndDrawableUtils.getDrawableFromResources(mContext, R.mipmap.ic_launcher)).setIsCircle(true).build();
        }

    }



}

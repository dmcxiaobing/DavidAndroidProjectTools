package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.GlideAndFrescoUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.FrescoImageUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PicassoWithImageLoaderImageViewUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

/**
 * 显示图片的Picasso,UniversalImageloader,Fresco的使用教程
 */
public class PicassoAndImageloaderFrescoActivity extends Activity {
    /**
     * Fresco的使用,1初始化fresco，2,布局中引用SimpleDraweeView 3,java代码中进行使用。
     * <p/>
     * 渐进式JPEG图仅仅支持网络图。本地图片会一次解码完成，所以没必要渐进式加载。
     */
    private SimpleDraweeView simple_image_view_one;
    private Button btn_picasso_img;
    private Button btn_imageloader_img;
    private Button btn_fresco_img;
    private Button btn_glide_img;
    private ImageView iv_picasso_fresco_glide_imageloader;
    private Button btn_fresco_two_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_imageloader_fresco);
        initView();
        setOnclickListener();
    }


    private void setOnclickListener() {
        /**
         * Picasso显示图片
         */
        btn_picasso_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicassoWithImageLoaderImageViewUtils.withImageView(PicassoAndImageloaderFrescoActivity.this,
                        Urls.GET_IMAGE_URL, iv_picasso_fresco_glide_imageloader);
            }
        });
        /**
         * UniversalImageloader显示图片
         */
        btn_imageloader_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicassoWithImageLoaderImageViewUtils.displayImage(Urls.GET_IMAGE_URL_IMAGELOADER, iv_picasso_fresco_glide_imageloader);
            }
        });
        /**
         * Glide显示图片
         */

        btn_glide_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideAndFrescoUtils.withImageView(PicassoAndImageloaderFrescoActivity.this,
                        Urls.GET_IMAGE_URL_GLIDE, iv_picasso_fresco_glide_imageloader);
            }
        });

        /**
         *Fresco显示一张基本的网络图片
         */

        btn_fresco_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Urls.FRESCO_IMAGE_LOGO);
                simple_image_view_one.setImageURI(uri);
            }
        });

        /**
         * fresco简单封装，显示网络图片
         */
        btn_fresco_two_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideAndFrescoUtils.frescoDisplayInternetImage(PicassoAndImageloaderFrescoActivity.this,Urls.FRESCO_IMAGE_LOGO_IMAGE_PIPELINE,simple_image_view_one);

            }
        });


    }

    private void initView() {
        btn_picasso_img = (Button) findViewById(R.id.btn_picasso_img);
        btn_imageloader_img = (Button) findViewById(R.id.btn_imageloader_img);
        btn_fresco_two_img = (Button) findViewById(R.id.btn_fresco_two_img);
        btn_fresco_img = (Button) findViewById(R.id.btn_fresco_img);
        btn_glide_img = (Button) findViewById(R.id.btn_glide_img);
        iv_picasso_fresco_glide_imageloader = (ImageView) findViewById(R.id.iv_picasso_fresco_glide_imageloader);
        simple_image_view_one = (SimpleDraweeView) findViewById(R.id.simple_image_view_one);

    }
}

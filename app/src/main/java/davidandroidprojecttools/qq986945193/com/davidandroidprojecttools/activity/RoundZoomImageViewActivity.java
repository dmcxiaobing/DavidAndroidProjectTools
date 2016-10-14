package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.facebook.drawee.view.SimpleDraweeView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.GlideAndFrescoUtils;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 圆形头像的实现
 */
public class RoundZoomImageViewActivity extends Activity{
    private SimpleDraweeView simple_image_view_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_zoom_imageview);
        simple_image_view_one = (SimpleDraweeView) findViewById(R.id.simple_image_view_one);
        GlideAndFrescoUtils.frescoDisplayInternetImage(this, Urls.FRESCO_IMAGE_LOGO_IMAGE_PIPELINE,simple_image_view_one);
    }
}

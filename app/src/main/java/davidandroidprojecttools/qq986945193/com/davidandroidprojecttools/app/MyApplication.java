package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app;

import android.app.Application;
import android.os.Environment;

import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * Application的配置
 */
public class MyApplication extends Application {
    private static MyApplication app;
    private HttpUtils httpUtils;
    private ImageLoader imageLoader = null;
    private DisplayImageOptions imageOptions;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        initHttpUtils();
        /**
         * 这里使用抽取出来的封装imageloder，当然，用本类中的也是可以的
         *
         * 如果用本类中的UniversalImageloader配置 调用方法如下：
         *
         * 1,ImageLoader imageLoader = MyApplication.getApp().getImageLoader();
         *
         * 2,DisplayImageOptions options = MyApplication.getApp().getImageOptions();
         *
         *        // 图片显示
         *
         *   3,     imageLoader.displayImage(图片的url,ImageView控件,options);
         */
        ImageLoaderConfig.initImageLoader(this, getCacheDir().getAbsolutePath());

//        initUniversalImageLoader();


    }

    public static MyApplication getApp() {
        return app;
    }

    /**
     * 初始化HttpUtils
     */
    private void initHttpUtils() {
        httpUtils = new HttpUtils();

        httpUtils.configRequestThreadPoolSize(5);

        httpUtils.configRequestRetryCount(3);

        httpUtils.configResponseTextCharset("utf-8");

        httpUtils.configSoTimeout(30 * 1000);

        httpUtils.configTimeout(60 * 1000);
    }

    public HttpUtils getHttpUtils() {
        return this.httpUtils;
    }

    /**
     * 初始化UniversalImageLoader
     */
    public void initUniversalImageLoader() {
        imageLoader = ImageLoader.getInstance();

        // 内存大小
        int cacheSize = (int) Runtime.getRuntime().maxMemory() / 8;

        String cachePath = null;

        // 磁盘缓存路径
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            cachePath = getApplicationContext().getExternalCacheDir().getPath();
        } else {
            cachePath = getApplicationContext().getCacheDir().getPath();
        }

        File cacheFileDir = new File(cachePath, "/david/images");
        cacheFileDir.mkdirs();

        // UniversalImageLoader配置
        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration
                        .Builder(getApplicationContext())
                        .memoryCacheSize(cacheSize)
                        .diskCache(new UnlimitedDiskCache(cacheFileDir))
                        .diskCacheFileCount(300)
                        .diskCacheSize(200 * 1024 * 1024)
                        .build();

        // 初始化配置
        ImageLoader.getInstance().init(configuration);

        // 全局图片选项
        imageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .build();
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public DisplayImageOptions getImageOptions() {
        return imageOptions;
    }


}

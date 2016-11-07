package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import java.io.File;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MainActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.TencentBuglyUpdateActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * Application的配置 继承MultiDexApplication是为了使项目能够突破65535限制
 */
public class MyApplication extends MultiDexApplication {
    private static MyApplication app;
    private HttpUtils httpUtils;
    private ImageLoader imageLoader = null;
    private DisplayImageOptions imageOptions;
    private BitmapUtils bitmapUtils;
    private OkHttpUtils mOkHttpUtils;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initTencentBugly();


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

        initOkHttpUtils();
        //最基本的初始化Fresco
        initFresco();

    }


    /**
     * 最基本的初始化Fresco
     */
    private void initFresco() {
        Fresco.initialize(this);
    }

    /**
     * 初始化OkHttp
     */
    private void initOkHttpUtils() {
        mOkHttpUtils = OkHttpUtils.getInstance();

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

        initBitmapUtils();
    }

    /**
     * 初始化BitmapUtils
     */
    private void initBitmapUtils() {
        // BitmapUtils

        // 获取磁盘缓存路径
        String diskCachePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            diskCachePath =
                    Environment.getExternalStorageDirectory().getPath()
                            + "/david/images";
            // /mnt/sdcard/xUtils_Cache/images
        } else {
            diskCachePath =
                    this.getCacheDir() + "/david/images";
            // /data/data/<包名>/cache/xUtils_Cache/images
        }

        //
        File file = new File(diskCachePath);
        // 创建磁盘缓存路径
        file.mkdirs();

        // 定义内存缓存大小
        int memoryCacheSize = (int) Runtime.getRuntime().maxMemory() / 8;

        // 使用指定内存缓存和磁盘路径的方式创建BitmapUtils
        bitmapUtils = new BitmapUtils(
                this,
                diskCachePath,
                memoryCacheSize
        );

        // BitmapUtils进行配置

        // 设置线程池中线程的数量
        bitmapUtils.configThreadPoolSize(5);

        // 是否使用内存缓存
        bitmapUtils.configMemoryCacheEnabled(true);

        // 是否使用磁盘缓存
        bitmapUtils.configDiskCacheEnabled(true);

        // 设置加载中的图片
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher);

        // 设置加载失败的图片资源
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_launcher);

        // 设置图片的最大宽度和高度
        bitmapUtils.configDefaultBitmapMaxSize(100, 100);

        // 连接超时时间
        bitmapUtils.configDefaultConnectTimeout(20 * 1000);

        // 缓存存留时间,设置3小时
        bitmapUtils.configDefaultCacheExpiry(3 * 60 * 60 * 1000);

        // 设置图片显示属性
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
    }

    public HttpUtils getHttpUtils() {
        return this.httpUtils;
    }

    public BitmapUtils getBitmapUtils() {
        return this.bitmapUtils;
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

    /**
     * @return
     * @GitHub: https://github.com/QQ986945193
     * @CSDN博客: http://blog.csdn.net/qq_21376985
     */
    public OkHttpUtils getOkHttpUtils() {
        return this.mOkHttpUtils;
    }

    /**
     * 初始化配置腾讯的Bugly SDK
     */
    private void initTencentBugly() {
        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpdate(),如需增加自动检查时机可以使用Beta.checkUpdate(false,false);
         * 参数1： applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */


        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Beta.canShowUpgradeActs.add(TencentBuglyUpdateActivity.class);


        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
        // Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;

        /**
         * 设置自定义tip弹窗UI布局
         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/tips_dialog.xml
         */
//        Beta.tipsDialogLayoutId = R.layout.tips_dialog;

        /**
         *  如果想监听升级对话框的生命周期事件，可以通过设置OnUILifecycleListener接口
         *  回调参数解释：
         *  context - 当前弹窗上下文对象
         *  view - 升级对话框的根布局视图，可通过这个对象查找指定view控件
         *  upgradeInfo - 升级信息
         */
      /* Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onCreate");
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onStart");
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onResume");
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:

                ImageView imageView = (ImageView) view.findViewWithTag(Beta.TAG_IMG_BANNER);

                // 更多的操作：比如设置控件的点击事件
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onPause");
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onStop");
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onDestory");
            }
        };*/

//        /**
//         * 更新状态回调
//         */
//        Beta.upgradeStateListener = new UpgradeStateListener() {
//            @Override
//            public void onUpgradeSuccess(boolean isManual) {
//                LogUtil.E("UPGRADE_SUCCESS");
////                Toast.makeText(getApplicationContext(), "UPGRADE_SUCCESS", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgradeFailed(boolean isManual) {
//                LogUtil.E("UPGRADE_FAILED");
//
////                Toast.makeText(getApplicationContext(), "UPGRADE_FAILED", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgrading(boolean isManual) {
//                LogUtil.E("UPGRADE_CHECKING");
//
////                Toast.makeText(getApplicationContext(), "UPGRADE_CHECKING", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUpgradeNoVersion(boolean isManual) {
//                LogUtil.E("UPGRADE_NO_VERSION");
//
////                Toast.makeText(getApplicationContext(), "UPGRADE_NO_VERSION", Toast.LENGTH_SHORT)
////                        .show();
//            }
//        };

        /**
         * 更新监听，收到策略时回调
         */
         /*Beta.upgradeListener = new UpgradeListener() {
                    @Override
                    public boolean onUpgrade(GrayStrategyDetail strategy, int ret, String errMsg) {
                        return false;
                    }

                    @Override
                    public void onUpgrade(int ret, UpgradeInfo strategy, boolean isManual,
                            boolean isSilence) {
                        if (strategy != null) {
                            Intent i = new Intent();
                            i.setClass(getApplicationContext(), UpgradeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "没有更新", Toast.LENGTH_SHORT).show();
                        }
                    }
                };*/

        //调用Bugly的自动检测是否有新版本的功能
        Bugly.init(getApplicationContext(), Constants.TENCENT_BUGLY_APP_ID, false);
    }
}

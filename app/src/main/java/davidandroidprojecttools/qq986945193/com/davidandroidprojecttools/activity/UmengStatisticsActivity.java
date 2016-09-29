package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;


import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：umeng统计的用法
 * <p/>
 * 1,从友盟官方后台注册应用，2,创建一个基类。在activity onResume()和onPause()方法
 * <p/>
 * 注册。 3,引入友盟统计jar文件或者build添加
 */
public class UmengStatisticsActivity extends Activity {

//    这只是一个简单的使用统计，具体更多功能请看umeng官方sdk文档介绍：
//
//    地址：http://dev.umeng.com/analytics/android-doc/integration#
//
//    我写的这个demo只是简单的统计app下载量，启动次数，以及渠道安装量。


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

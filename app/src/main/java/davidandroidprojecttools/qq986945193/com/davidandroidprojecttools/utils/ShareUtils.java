package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Context;
import android.content.Intent;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;

/**
 * 一些分享的工具类封装
 */
public class ShareUtils {
    /**
     * 本地分享
     */
    public static void nativeShare(Context mContext) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, Urls.CSDN_BLOG_DAVID);
        mContext.startActivity(Intent.createChooser(intent, "程序员小冰"));

    }

}

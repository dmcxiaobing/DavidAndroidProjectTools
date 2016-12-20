package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.URLUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * WebView播放视频(真机测试)  记得要在manifest添加支持属性
 */
public class WebViewVideoActivity extends BaseActivity {
    private WebView webview;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_david_webview);
        webview = (WebView) findViewById(R.id.webview);
        // 设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);

        webview.setVisibility(View.VISIBLE);
        webview.getSettings().setUseWideViewPort(true);
        //这里添加要播放视频的URL地址,这里用优酷。当然也可以用淘宝或者.mp4等视频的地址
        webview.loadUrl(Urls.YOUKU_URL);
//        用webview加载网页时，点击网页中的视频进行播放，按返回键回到上一个页面，视频声音还在
//
//        处理方法：设置webview.loadUrl（""）；
        /**
         * 设置不会调用手机中的浏览器 会调用app中浏览器
         */
        webview.setWebViewClient(new WebViewClient() {


            /**
             * 加载中
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtils.isNetworkImageUrl(url)) {
                    LogUtil.E("loading" + url);
                    view.loadUrl(url);

                }
                return true;


            }


            /**
             * 加载完成
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtil.E("finish" + url);

            }


        });


        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_BACK)
                            && webview.canGoBack()) {
                        webview.goBack();
                        return true;
                    }
                }
                return false;


            }
        });

    }


    @Override
    protected void initData() {

    }
}

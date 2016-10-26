package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * WebView播放优酷视频(真机测试)
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
        webview.loadUrl(Urls.YOUKU_URL);

    }

    @Override
    protected void initData() {

    }
}

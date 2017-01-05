package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.FileUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.URLUtils;

/**
 * webview的功能使用详解
 */
public class WebViewDetailActivity extends BaseActivity {
    private WebView webview;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_david_webview);
        webview = (WebView) findViewById(R.id.webview);
        webview.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        WebSettings settings = webview.getSettings();
        /*设置支持js*/
        settings.setJavaScriptEnabled(true);
        /*设置是否支持缩放*/
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);//显示放大缩小 controler
        /*设置默认编码*/
        settings.setDefaultTextEncodingName("UTF-8");
        /*网页自适应*/
        settings.setUseWideViewPort(false);

        settings.setLoadWithOverviewMode(false);

//        initWebViewSettings();


        webview.loadUrl(Urls.CSDN_BLOG_DAVID);

        /**
         * 设置不会调用手机中的浏览器 会调用app中浏览器
         */
        webview.setWebViewClient(new WebViewClient() {
            /**
             * 开始加载
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                dialog.show()
            }

            /**
             * 加载中
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtils.isNetworkImageUrl(url)) {
                    view.loadUrl(url);
                    LogUtil.E("url" + url);
                }
                return true;
            }

            /**
             * 加载完成
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                /**
                 * 获取cookie
                 */
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
                Log.e("webview的cookie", "Cookies="+CookieStr);
                super.onPageFinished(view, url);
//                dialog.dismiss();
            }
        });


        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
                    webview.goBack();
                    return true;
                } else {
                    return false;
                }


            }
        });

    }

    /**
     * 常用的webview一些设置
     */
    private void initWebViewSettings() {
        // 设置可以访问文件
        webview.getSettings().setAllowFileAccess(true);
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setSavePassword(true);
        // User settings
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(false);
        webview.getSettings().setSaveFormData(true);
        CookieManager.getInstance().setAcceptCookie(true);
        webview.getSettings().setSupportZoom(true);
    }
}

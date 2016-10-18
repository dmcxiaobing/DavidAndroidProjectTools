package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;


import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OnRefreshWebViewListener;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 自定义的WebView 支持下拉刷新的功能
 */
public class DavidWebView extends RelativeLayout {

    private Context ctx;
    /**
     * 安全WebView
     */
    private SafeWebView webView;
    /**
     * 下拉刷新View
     */
    private WebViewPullToRefreshView refreshView;

    public DavidWebView(Context context) {
        super(context);
        initView(context);
        initWebView();
    }

    public DavidWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initWebView();
    }

    public DavidWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
        initWebView();
    }

    private void initView(Context context) {
        ctx = context;
        LayoutInflater.from(context).inflate(R.layout.david_webview_layout, this);
        refreshView = (WebViewPullToRefreshView) findViewById(R.id.refresh_view);
        webView = (SafeWebView) findViewById(R.id.safe_webview);
    }

    /**
     * 以后扩展，解决WebView各种问题
     */
    private void initWebView() {

        // 问题1：SDK11，开启硬件加速，会导致白屏。 这里取消硬件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        //问题2：基本都需要支持JS
        webView.getSettings().setJavaScriptEnabled(true);


        //问题3：加载任何url，直接跳到系统浏览器去了。覆写下面的函数，可以解决
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //问题4：onPageFinished回调，经常没有被调用。使用 onProgressChange替换
//        webView.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public void onProgressChanged(WebView view, final int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    //这里表示页面加载完成
//                }
//            }
//        });


//        //问题5：点击页面内的下载链接，无反应。这里直接监听，跳到系统浏览器去下载
//        webView.setDownloadListener(new DownloadListener() {
//
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
//                                        long contentLength) {
//                // 实现下载的代码
//                Uri uri = Uri.parse(url);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                ctx.startActivity(intent);
//            }
//        });
    }

    /**
     * 设置刷新成功
     */
    public void setRefreshSuccess() {
        refreshView.refreshFinish(WebViewPullToRefreshView.SUCCEED);
    }

    /**
     * 设置刷新失败
     */
    public void setRefreshFail() {
        refreshView.refreshFinish(WebViewPullToRefreshView.FAIL);
    }

    /**
     * 设置刷新是否启用
     *
     * @param isEnable
     */
    public void setRefreshEnable(boolean isEnable) {
        webView.setCanPullDown(isEnable);
    }

    /**
     * 设置刷新中回调
     *
     * @param listener
     */
    public void setOnRefreshWebViewListener(OnRefreshWebViewListener listener) {
        refreshView.setOnRefreshListener(listener);
    }

    /**
     * 获取WebView
     *
     * @return
     */
    public WebView getWebView() {
        return webView;
    }
}

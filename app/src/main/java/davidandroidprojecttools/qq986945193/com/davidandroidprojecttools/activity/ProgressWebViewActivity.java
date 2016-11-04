package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.URLUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.ProgressWebView;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 带有进度条的webview加载html网页
 */
public class ProgressWebViewActivity extends BaseActivity {
    private ProgressWebView progress_webview;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_progress_view);
        progress_webview = (ProgressWebView) findViewById(R.id.progress_webview);

        WebSettings settings = progress_webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        /**
         * 这样不会调用手机的浏览器
         */
        progress_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        progress_webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                if (url != null && URLUtils.isNetworkImageUrl(url))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        progress_webview.loadUrl(Urls.CSDN_BLOG_DAVID);
    }
}

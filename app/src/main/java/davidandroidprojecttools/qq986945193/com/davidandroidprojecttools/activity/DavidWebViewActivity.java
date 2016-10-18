package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OnRefreshWebViewListener;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.DavidWebView;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 自定义WebView支持下拉刷新的功能
 */
public class DavidWebViewActivity extends BaseActivity {
    /**
     * 若是需要设置一下功能 可在DavidWebView中进行设置
     */
    private DavidWebView davidWebView;
    private ToggleButton tb;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_david_webview);
        davidWebView = (DavidWebView) findViewById(R.id.davidWebView);
        tb = (ToggleButton) findViewById(R.id.tb);
    }

    @Override
    protected void initData() {
        davidWebView.setOnRefreshWebViewListener(new OnRefreshWebViewListener() {
            @Override
            public void onRefresh() {
                // 模拟接口调用3秒
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //调用接口结束
                        davidWebView.setRefreshSuccess();
//                        davidWebView.setRefreshFail();
                    }
                }.sendEmptyMessageDelayed(0, 3000);
            }
        });

        davidWebView.getWebView().loadUrl(Urls.CSDN_BLOG_DAVID);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //支持刷新
                    davidWebView.setRefreshEnable(true);
                } else {
                    //不支持刷新
                    davidWebView.setRefreshEnable(false);
                }
            }
        });
    }
}

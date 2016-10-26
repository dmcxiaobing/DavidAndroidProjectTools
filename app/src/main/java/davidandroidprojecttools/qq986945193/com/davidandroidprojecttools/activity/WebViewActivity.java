package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * WebView的使用功能大全
 */
public class WebViewActivity extends BaseActivity {
    private Button btn_01;
    private Button btn_02;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_webview);
        btn_01 = (Button) findViewById(R.id.btn_01);
        btn_02 = (Button) findViewById(R.id.btn_02);
    }

    @Override
    protected void initData() {
        /**
         * 自定义WebView支持下拉刷新的功能
         */
        btn_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DavidWebViewActivity.class);
            }
        });
        /**
         * WebView播放优酷视频(真机测试)
         */
        btn_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WebViewVideoActivity.class);
            }
        });
    }
}

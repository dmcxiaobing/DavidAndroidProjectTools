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
 * 调用ShareSDK进行分享
 */
public class ShareSdkActivity extends BaseActivity {
    private Button btn_share;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_share_sdk);
        btn_share = (Button) findViewById(R.id.btn_share);
    }

    @Override
    protected void initData() {
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    private void showShare() {

    }
}

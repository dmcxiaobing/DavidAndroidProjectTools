package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;

/**
 * 分享的一些功能与UI自定义效果实现
 */
public class ShareActivity extends BaseActivity {
    private Button btn_share_native;
    private Button btn_share_sdk;
    private Button btn_share_custom_ui;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_share);
        btn_share_native = (Button) findViewById(R.id.btn_share_native);
        btn_share_sdk = (Button) findViewById(R.id.btn_share_sdk);
        btn_share_custom_ui = (Button) findViewById(R.id.btn_share_custom_ui);

    }

    @Override
    protected void initData() {
        btn_share_native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nativeShare();
            }
        });

        btn_share_sdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShareSdkActivity.class);
            }
        });

        btn_share_custom_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShareUiActivity.class);
            }
        });
    }

    /**
     * 本地分享
     */
    private void nativeShare() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, Urls.GITHUB_DAVID);
        startActivity(Intent.createChooser(intent, "程序员小冰"));

    }

}

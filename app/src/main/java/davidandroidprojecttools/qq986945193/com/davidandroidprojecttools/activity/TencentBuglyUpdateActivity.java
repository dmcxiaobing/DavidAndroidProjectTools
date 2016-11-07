package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;

import com.tencent.bugly.beta.Beta;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 腾讯Bugly的检测升级更新
 */
public class TencentBuglyUpdateActivity extends BaseActivity {
    private Button btn_update;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_tencent_bugly_update);
        btn_update = (Button) findViewById(R.id.btn_update);
    }

    @Override
    protected void initData() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.checkUpgrade();
            }
        });
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;

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
                /**
                 * 更新状态回调
                 */
                Beta.upgradeStateListener = new UpgradeStateListener() {
                    @Override
                    public void onUpgradeSuccess(boolean isManual) {
//                        LogUtil.E("检测成功");
//                        Toast.makeText(getApplicationContext(), "检测成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpgradeFailed(boolean isManual) {
//                        LogUtil.E("检测失败");
//                        Toast.makeText(getApplicationContext(), "检测失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpgrading(boolean isManual) {
//                        LogUtil.E("检测中....");
//
//                        Toast.makeText(getApplicationContext(), "检测中....", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDownloadCompleted(boolean b) {

                    }

                    @Override
                    public void onUpgradeNoVersion(boolean isManual) {
//                        LogUtil.E("没有最新版本....");
//                        Toast.makeText(getApplicationContext(), "没有最新版本", Toast.LENGTH_SHORT)
//                                .show();
                    }
                };
            }
        });
    }


}

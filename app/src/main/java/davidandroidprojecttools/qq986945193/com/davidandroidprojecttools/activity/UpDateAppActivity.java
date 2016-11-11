package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * 升级APP软件的多种实现方式
 */
public class UpDateAppActivity extends BaseActivity {
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;

    @Override
    protected void initView() {
        setContentView(R.layout.update_app);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        /**
         * 腾讯Bugly的检测升级更新
         */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TencentBuglyUpdateActivity.class);
            }
        });

        /**
         * 调用本地的json数据进行更新apk
         */
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LocalJsonUpdateAppActivity.class);
            }
        });
    }

}

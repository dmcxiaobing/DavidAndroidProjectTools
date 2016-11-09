package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 各种各样的Tab切换功能效果的实现
 */
public class TabActivity extends BaseActivity {
    private Button btn_mui;
    private Button btn_tab_01;
    private Button btn_tab_02;
    private Button btn_tab_03;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_tab);
        btn_mui = (Button) findViewById(R.id.btn_mui);
        btn_tab_01 = (Button) findViewById(R.id.btn_tab_01);
        btn_tab_02 = (Button) findViewById(R.id.btn_tab_02);
        btn_tab_03 = (Button) findViewById(R.id.btn_tab_03);
    }

    @Override
    protected void initData() {
        /**
         * 仿MiUi的TAB切换
         */
        btn_mui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MiUiTabActivity.class);
            }
        });
        /**
         *不用fragment实现tab切换
         */
        btn_tab_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NoFragmentOneTabActivity.class);
            }
        });

        /**
         * 用fragment不用Viewpager实现tab切换
         */
        btn_tab_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NoViewpagerFragmentActivity.class);
            }
        });

        /**
         * 用fragment+Viewpager实现tab切换
         */
        btn_tab_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ViewPagerFragmentActivity.class);
            }
        });
    }
}

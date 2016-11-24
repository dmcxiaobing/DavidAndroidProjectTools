package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * 无限轮播滚动的banner(封装ViewPager)
 */
public class AutoViewPagerActivity extends BaseActivity {
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_auto_viewppager);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        /**
         * 展示自己内部封装的无限滚动
         */
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AutoViewPagerBannerActivity.class);
            }
        });
        /**
         * 展示开源十多种动画效果的滚动
         */
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ViewPagerAnimationActivity.class);
            }
        });
    }

}

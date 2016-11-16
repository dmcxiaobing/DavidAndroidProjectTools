package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.AboutDavidFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.TabFragmentIndicator;

/**
 * 顶部TAB切换功能第一种实现方法
 */
public class TabFragmentTopIndicatorActivity extends BaseFragmentActivity implements TabFragmentIndicator.OnTabClickListener {

    @BindView(R.id.tabFragmentIndicator)
    TabFragmentIndicator tabFragmentIndicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private View categoryTab;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tabFragmentIndicator.addFragment(0, AboutDavidFragment.class);
        tabFragmentIndicator.addFragment(1, AboutDavidFragment.class);
        tabFragmentIndicator.addFragment(2, AboutDavidFragment.class);

        tabFragmentIndicator.setTabContainerView(R.layout.layout_home_tabindicator);
        tabFragmentIndicator.setTabSliderView(R.layout.tab_slider);
        tabFragmentIndicator.setOnTabClickListener(this);
        tabFragmentIndicator.setViewPager(viewPager);

    }

    /**
     * 设置tab点击切换的功能
     */
    @Override
    public void onTabClick(View v) {
        if ((Integer) v.getTag() == 0) {
            categoryTab = v;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tap_fragment_top_indicator;
    }


}

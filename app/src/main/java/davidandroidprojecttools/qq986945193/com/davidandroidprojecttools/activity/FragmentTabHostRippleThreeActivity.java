package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MainFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.ViewPagerFragmentFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.tabHostFragment.TabItemBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.tabHostFragment.XFragmentTabHost;

/**
 * 使用fragmentTabHost第三种实现波纹
 */
public class FragmentTabHostRippleThreeActivity extends BaseFragmentActivity {

    @BindView(R.id.fmlayout)
    FrameLayout fmlayout;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    XFragmentTabHost tabhost;
    private String[] titles = {"程序员小冰", "DAVID", "安卓利器", "Tab"};
    private Class[] mFragClass = new Class[]{ViewPagerFragmentFragment.class, MainFragment.class,
            ViewPagerFragmentFragment.class, MainFragment.class};
    private int[] imgs = {R.drawable.selector_tab_activity_index, R.drawable.selector_tab_activity_friends,
            R.drawable.selector_tab_activity_im, R.drawable.selector_tab_activity_setting};

    @Override
    protected void initView() {
        ButterKnife.bind(this);


    }

    @Override
    protected void initData() {

        tabhost.setup(this, getSupportFragmentManager(), R.id.fmlayout);
        tabhost.setTabMode(XFragmentTabHost.TabMode.Ripple);
        /*设置选中的颜色*/
        tabhost.setTextActiveColor(Color.WHITE);
        /*设置没有选中的颜色*/
        tabhost.setTextInactiveColor(Color.GRAY);

        for (int i = 0; i < mFragClass.length; i++) {
            tabhost.addTabItem(new TabItemBean(titles[i], imgs[i]), mFragClass[i], Bundle.EMPTY);
        }


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fragment_tab_host_up_two;
    }

}

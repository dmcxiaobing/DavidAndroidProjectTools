package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MainFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.ViewPagerFragmentFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.tabHostFragment.TabItemBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.tabHostFragment.XFragmentTabHost;

/***
 * 使用fragmentTabHost第二种实现上移
 */
public class FragmentTapHostUpTwoActivity extends BaseFragmentActivity {
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
        tabhost.setup(this, getSupportFragmentManager(), R.id.fmlayout);

        for (int i = 0; i < titles.length; i++) {
            tabhost.addTabItem(new TabItemBean(titles[i], imgs[i]), mFragClass[i], Bundle.EMPTY);
        }

        tabhost.setTabMode(XFragmentTabHost.TabMode.MoveToTop);
    }

    /**
     * 初始化Indicator
     */
    private View initIndicator(int i) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.fragment_tab_indicator, null);
        TextView tv = (TextView) rootView.findViewById(R.id.tab_title);
        ImageView iv = (ImageView) rootView.findViewById(R.id.tab_icon);
        tv.setText(titles[i]);
        iv.setBackgroundResource(imgs[i]);
        return rootView;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fragment_tab_host_up_two;
    }

}

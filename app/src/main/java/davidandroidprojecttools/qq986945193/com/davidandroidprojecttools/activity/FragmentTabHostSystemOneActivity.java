package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.text.Layout;
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

/**
 * 使用系统第一种方法FragmentTabHost实现tab切换
 * <p/>
 * 使用系统自带的fragmentTabHost
 */
public class FragmentTabHostSystemOneActivity extends BaseFragmentActivity {
    @BindView(R.id.fmlayout)
    FrameLayout fmlayout;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private String[] titles = new String[]{"android", "java", "php", "ios"};

    private int[] icons = new int[]{R.drawable.selector_tab_activity_index, R.drawable.selector_tab_activity_friends,
            R.drawable.selector_tab_activity_im, R.drawable.selector_tab_activity_setting};
    private Class[] mFragClass = new Class[]{ViewPagerFragmentFragment.class, MainFragment.class,
            ViewPagerFragmentFragment.class, MainFragment.class};

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        tabhost.setup(this, getSupportFragmentManager(), R.id.fmlayout);

        for (int i = 0; i < titles.length; i++) {
            Bundle bundle = new Bundle();
//            bundle.putString(TabFragment.FRAG_KEY, mTabTitle[i]);
            tabhost.addTab(tabhost.newTabSpec(titles[i]).setIndicator(getIndicator(i)), mFragClass[i], bundle);
        }
        /**
         * 去掉FragmentTabHost中的分割线
         */
        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fragment_tab_host_system_one;
    }

    public View getIndicator(int i) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.fragment_tab_indicator, null);
        TextView tv = (TextView) rootView.findViewById(R.id.tab_title);
        ImageView iv = (ImageView) rootView.findViewById(R.id.tab_icon);
        tv.setText(titles[i]);
        iv.setBackgroundResource(icons[i]);
        return rootView;
    }
}

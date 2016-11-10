package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.ViewPagerFragmentFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.VpSimpleFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.ViewPagerIndicator;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 仿MiUi的TAB切换
 */
public class MiUiTabActivity extends BaseFragmentActivity {
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("短信1", "短信2", "短信3", "短信4");
    //	private List<String> mDatas = Arrays.asList("短信1", "短信2", "短信3", "短信4",
//			"短信5", "短信6", "短信7", "短信8", "短信9");
    private ViewPagerFragmentFragment mMainfragment = new ViewPagerFragmentFragment();
    private ViewPagerFragmentFragment mFamousFragment = new ViewPagerFragmentFragment();
    private ViewPagerFragmentFragment mPackageControlFragment = new ViewPagerFragmentFragment();
    private ViewPagerFragmentFragment commonlyUsedFunctionFragment = new ViewPagerFragmentFragment();
    private ViewPagerIndicator mIndicator;


    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);

    }

    private void initDatas() {
//        for (String data : mDatas) {
//            VpSimpleFragment fragment = VpSimpleFragment.newInstance(data);
//            mTabContents.add(fragment);
//        }

        mTabContents.add(mMainfragment);
        mTabContents.add(mFamousFragment);
        mTabContents.add(mPackageControlFragment);
        mTabContents.add(commonlyUsedFunctionFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    @Override
    protected void initData() {
//		mIndicator.setItemCount(4);
        mIndicator.setVisibleTabCount(4);
        mIndicator.setTabItemTitles(mDatas);
        initDatas();

        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    protected int getLayoutResId() {

        return R.layout.activity_miui_tab;
    }
}

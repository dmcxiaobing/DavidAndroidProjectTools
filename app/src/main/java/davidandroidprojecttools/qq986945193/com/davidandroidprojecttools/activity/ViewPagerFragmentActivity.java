package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.CommonlyUsedFunctionFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.FamousFrameFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MainFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.PackageControlFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.ViewPagerFragmentFragment;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 *
 */
public class ViewPagerFragmentActivity extends BaseFragmentActivity implements View.OnClickListener {
    private ViewPager vp;
    private LinearLayout ll_index;
    private LinearLayout ll_setting;
    private LinearLayout ll_friends;
    private LinearLayout ll_im;
    private ViewPagerFragmentFragment mMainfragment = new ViewPagerFragmentFragment();
    private ViewPagerFragmentFragment mFamousFragment = new ViewPagerFragmentFragment();
    private ViewPagerFragmentFragment mPackageControlFragment = new ViewPagerFragmentFragment();
    private ViewPagerFragmentFragment commonlyUsedFunctionFragment = new ViewPagerFragmentFragment();

    private List<Fragment> listFragments = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        ll_index = (LinearLayout) findViewById(R.id.ll_index);
        ll_im = (LinearLayout) findViewById(R.id.ll_im);
        ll_friends = (LinearLayout) findViewById(R.id.ll_friends);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);


    }

    @Override
    protected void initData() {
        listFragments.add(mMainfragment);
        listFragments.add(mFamousFragment);
        listFragments.add(mPackageControlFragment);
        listFragments.add(commonlyUsedFunctionFragment);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int CurrentItem = vp.getCurrentItem();
                setSelectFragment(CurrentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTab(0);


    }

    private void setTab(int i) {
        setSelectFragment(i);
        vp.setCurrentItem(i);
    }

    private void setSelectFragment(int i) {
        resertSelect();
        switch (i) {
            case 0:
                ll_index.setSelected(true);
                break;
            case 1:
                ll_friends.setSelected(true);
                break;
            case 2:
                ll_im.setSelected(true);
                break;
            case 3:
                ll_setting.setSelected(true);
                break;
        }

    }


    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        ll_index.setOnClickListener(this);
        ll_im.setOnClickListener(this);
        ll_friends.setOnClickListener(this);
        ll_setting.setOnClickListener(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_no_fragment_one_tab;
    }

    private void resertSelect() {
        ll_index.setSelected(false);
        ll_friends.setSelected(false);
        ll_im.setSelected(false);
        ll_setting.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        resertSelect();
        switch (v.getId()) {
            case R.id.ll_index:
                setTab(0);
                break;
            case R.id.ll_friends:
                setTab(1);

                break;
            case R.id.ll_im:
                setTab(2);

                break;
            case R.id.ll_setting:
                setTab(3);

                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return listFragments.get(position);
        }

        @Override
        public int getCount() {
            return listFragments.size();

        }


    }

}

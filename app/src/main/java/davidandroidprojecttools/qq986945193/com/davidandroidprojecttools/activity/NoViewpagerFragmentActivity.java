package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.CommonlyUsedFunctionFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.FamousFrameFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MainFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.PackageControlFragment;

/**
 * 用fragment不用Viewpager实现tab切换
 */
public class NoViewpagerFragmentActivity extends BaseFragmentActivity implements View.OnClickListener {
    private LinearLayout ll_index;
    private LinearLayout ll_setting;
    private LinearLayout ll_friends;
    private LinearLayout ll_im;
    private MainFragment mMainfragment;
    private FamousFrameFragment mFamousFragment;
    private PackageControlFragment mPackageControlFragment;
    private CommonlyUsedFunctionFragment commonlyUsedFunctionFragment;

    @Override
    protected void initView() {
        ll_index = (LinearLayout) findViewById(R.id.ll_index);
        ll_im = (LinearLayout) findViewById(R.id.ll_im);
        ll_friends = (LinearLayout) findViewById(R.id.ll_friends);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);



    }

    /**
     * 设置显示的fragment
     */
    private void setSelectedFragment(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (i) {
            case 0:
                if (mMainfragment == null) {
                    mMainfragment = new MainFragment();
                    ft.add(R.id.fl_container, mMainfragment);
                } else {
                    ft.show(mMainfragment);
                }
                break;
            case 1:
                if (mFamousFragment == null) {
                    mFamousFragment = new FamousFrameFragment();
                    ft.add(R.id.fl_container, mFamousFragment);
                } else {
                    ft.show(mFamousFragment);
                }
                break;
            case 2:
                if (mPackageControlFragment == null) {
                    mPackageControlFragment = new PackageControlFragment();
                    ft.add(R.id.fl_container, mPackageControlFragment);
                } else {
                    ft.show(mPackageControlFragment);
                }
                break;
            case 3:
                if (commonlyUsedFunctionFragment == null) {
                    commonlyUsedFunctionFragment = new CommonlyUsedFunctionFragment();
                    ft.add(R.id.fl_container, commonlyUsedFunctionFragment);
                } else {
                    ft.show(commonlyUsedFunctionFragment);
                }
                break;

        }

        ft.commit();
    }

    @Override
    protected void initData() {
        setSelectedFragment(0);
        ll_index.setSelected(true);
    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        ll_index.setOnClickListener(this);
        ll_im.setOnClickListener(this);
        ll_friends.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_no_viewpager_fragment;
    }

    /**
     * 设置点击事件
     */
    @Override
    public void onClick(View v) {

        resertSelect();
        switch (v.getId()) {
            case R.id.ll_index:

                ll_index.setSelected(true);
                setSelectedFragment(0);
                break;
            case R.id.ll_im:

                ll_im.setSelected(true);
                setSelectedFragment(1);


                break;
            case R.id.ll_friends:
                ll_friends.setSelected(true);
                setSelectedFragment(2);


                break;
            case R.id.ll_setting:
                ll_setting.setSelected(true);
                setSelectedFragment(3);


                break;
        }
    }

    private void resertSelect() {
        ll_index.setSelected(false);
        ll_friends.setSelected(false);
        ll_im.setSelected(false);
        ll_setting.setSelected(false);
    }

    private void hideFragment(FragmentTransaction ft) {
        if (mMainfragment != null) {
            ft.hide(mMainfragment);
        }
        if (mFamousFragment != null) {
            ft.hide(mFamousFragment);
        }
        if (mPackageControlFragment != null) {
            ft.hide(mPackageControlFragment);
        }
        if (commonlyUsedFunctionFragment != null) {
            ft.hide(commonlyUsedFunctionFragment);
        }
    }
}

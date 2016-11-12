package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.AboutDavidFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.PackageControlFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MainFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.FamousFrameFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.CommonlyUsedFunctionFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ActivityManagerUtils;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 首次进入的主类
 */
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    //首页fragment 知识点
    public MainFragment mainFragment = new MainFragment();
    //常用功能
    public CommonlyUsedFunctionFragment mCommonlyUsedFunctionFragment = new CommonlyUsedFunctionFragment();
    //封装控件
    public PackageControlFragment mPackageControlFragment = new PackageControlFragment();
    //著名开源库
    public FamousFrameFragment mFamousFrameFragment = new FamousFrameFragment();
    //关于作者
    public AboutDavidFragment aboutDavidFragment = new AboutDavidFragment();

    /**
     * 底栏四个按钮的集合Tab
     */
    public LinearLayout[] mTabs;
    /**
     * 当前fragment的index
     */
    private int currentTabIndex;

    @Override
    protected void initView() {
        ActivityManagerUtils.getInstance().addActivity(MainActivity.this);
    }

    @Override
    protected void initData() {
        initViews();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    /**
     * 防止fragment重叠
     *
     * @param outState
     * @param
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }


    /**
     * 初始化控件布局
     */
    private void initViews() {
        mTabs = new LinearLayout[5];
        mTabs[0] = (LinearLayout) findViewById(R.id.main_knowledge_point);// 首页 知识点详解
        mTabs[1] = (LinearLayout) findViewById(R.id.main_package_control);// 封装控件
        mTabs[2] = (LinearLayout) findViewById(R.id.main_commonly_used_function);// 常用功能
        mTabs[3] = (LinearLayout) findViewById(R.id.mian_famous_frame);// 著名开源库
        mTabs[4] = (LinearLayout) findViewById(R.id.mian_about_david);// 关于作者

        pressFlag = 0;
        index = 0;

        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
        mTabs[2].setOnClickListener(this);
        mTabs[3].setOnClickListener(this);
        mTabs[4].setOnClickListener(this);
        mTabs[0].setSelected(true);
        switchContent(mainFragment);

    }

    /**
     * 双击两次退出程序提示
     */
    private boolean isState = true;//设置双击退出的变量

    private int pressFlag = 0;//判断点击的位置
    /**
     * 标识点击的位置，防止多次点击
     */
    private int index;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_knowledge_point:
                //首页fragment
                pressFlag = 0;
                index = 0;
                switchContent(mainFragment);
                break;
            case R.id.main_package_control:
                //封装控件fragment
                pressFlag = 1;
                index = 1;
                switchContent(mPackageControlFragment);
                break;
            case R.id.main_commonly_used_function:
                //常用功能fragment
                pressFlag = 2;
                index = 2;
                switchContent(mCommonlyUsedFunctionFragment);
                break;
            case R.id.mian_famous_frame:
                //著名开源库
                pressFlag = 3;
                index = 3;
                switchContent(mFamousFrameFragment);
                break;
            case R.id.mian_about_david:
                //关于作者
                pressFlag = 4;
                index = 4;
                switchContent(aboutDavidFragment);
                break;


        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;

    }

    public void onBackPressed() {
        if (isState) { //isState初始值为true
            isState = false;
            Toast.makeText(this, "再按一次退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    isState = true;
                }
            }, 2000);
        } else {
            finish();
        }
    }


    /**
     * 暂存Fragment
     */
    private Fragment mFragment = new Fragment();

    /**
     * 选择Fragment存放
     */
    public void switchContent(Fragment to) {

        if (to != null && mFragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.hide(mFragment).add(R.id.content, to)
                        .commitAllowingStateLoss();
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.hide(mFragment).show(to).commitAllowingStateLoss();
            }
            mFragment = to;
        }
    }
}

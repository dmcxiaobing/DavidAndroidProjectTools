package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jauker.widget.BadgeView;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.ClasslyFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MainFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.PersonalFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.ShoppingCartFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ActivityManagerUtils;
import de.greenrobot.event.EventBus;
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
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //首页fragment
    public MainFragment mainFragment = new MainFragment();
    //购物车fragment 购物车为空
    public ShoppingCartFragment mShoppingCartFragment = new ShoppingCartFragment();


    //商品分类
    public ClasslyFragment mClasslyFragment = new ClasslyFragment();
    //个人中心
    public PersonalFragment personalFragment = new PersonalFragment();

    /**
     * 底栏四个按钮的集合Tab
     */
    public LinearLayout[] mTabs;
    /**
     * 当前fragment的index
     */
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManagerUtils.getInstance().addActivity(MainActivity.this);
        initViews();
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
        mTabs = new LinearLayout[4];
        mTabs[0] = (LinearLayout) findViewById(R.id.new_main_page_rl);// 首页
        mTabs[1] = (LinearLayout) findViewById(R.id.main_page_rl);// 商品分类
        mTabs[2] = (LinearLayout) findViewById(R.id.find_page_rl);// 购物车
        mTabs[3] = (LinearLayout) findViewById(R.id.person_page_rl);// 我的
        mTabs[0].setSelected(true);

        pressFlag = 0;
        index = 0;

        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
        mTabs[2].setOnClickListener(this);
        mTabs[3].setOnClickListener(this);


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
            case R.id.new_main_page_rl:
                //首页fragment
                pressFlag = 0;
                index = 0;
                switchContent(mainFragment);
//                mTabs[3].setSelected(false);
                break;
            case R.id.main_page_rl:
                //商品分类fragment
                pressFlag = 1;
                index = 1;
                switchContent(mClasslyFragment);
//                mTabs[3].setSelected(false);
                break;
                  /*购物车*/
            case R.id.find_page_rl:
                switchContent(mShoppingCartFragment);
                //购物车fragment
                pressFlag = 2;
                index = 2;
                break;
            /**
             * 个人中心
             */
            case R.id.person_page_rl:
                pressFlag = 3;
                index = 3;
                switchContent(personalFragment);
                mTabs[3].setSelected(false);

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

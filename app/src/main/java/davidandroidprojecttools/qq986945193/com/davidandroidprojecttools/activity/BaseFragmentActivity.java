package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.umeng.analytics.MobclickAgent;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：FragmentActivity的基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initView();
        initData();
        setOnclickListener();

    }

    protected void setOnclickListener() {
    }



    /**
     * 绑定UI布局，以及初始化UI控件
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();



    /**
     * onResume与onPause()封装提取原因友盟统计
     */
    protected void onResume() { // Umeng 对处理事件的统计
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 添加片段
     *
     * @param layoutId
     * @param f
     * @param tag
     */
    protected void add(int layoutId, Fragment f, String tag) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();
        // 打开事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.add(layoutId, f, tag);
        // 关闭
        ft.commit();
    }

    protected void replace(int layoutId, Fragment f, String tag) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();

        // 打开事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.replace(layoutId, f, tag);
        // 关闭
        ft.commit();
    }

    protected void remove(Fragment f) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();
        // 打开事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.remove(f);
        // 关闭
        ft.commit();
    }

    protected Fragment find(String tag) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();

        Fragment f = fm.findFragmentByTag(tag);

        return f;
    }

    //    跳转类
    protected void startActivity(Class<?> activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }


}

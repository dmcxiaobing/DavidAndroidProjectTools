package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.SystemStatusManagerUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：activity的基类
 */
public abstract class BaseActivity extends Activity {
    protected Context mContext;
    protected OkHttpUtils okHttpUtils = MyApplication.getApp().getOkHttpUtils();

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

    //    跳转类
    protected void startActivity(Class<?> activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }

}

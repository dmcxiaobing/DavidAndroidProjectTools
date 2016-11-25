package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.XListView;

/**
 *XListView下拉刷新和上拉加载更多 使用详解
 */
public class XListViewActivity extends Activity implements XListView.IXListViewListener {
    private XListView mListView;

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler mHandler;
    private int mIndex = 0;
    private int mRefreshIndex = 0;

    public static void launch(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, XListViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlist_view);

        geneItems();
        initView();
    }

    private void initView() {
        mHandler = new Handler();
        mListView = (XListView) findViewById(R.id.list_view);
        //支持下拉刷新
        mListView.setPullRefreshEnable(true);
        //支持滚动加载
        mListView.setPullLoadEnable(true);
        //自动加载
        mListView.setAutoLoadEnable(true);
        //IXListViewListener:监听用户两个操作 1.下拉：重新获取首页 2.滚动底部:获取下一页
        mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());

        mAdapter = new ArrayAdapter<String>(this, R.layout.vw_list_item, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            mListView.autoRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIndex = ++mRefreshIndex;
                items.clear();
                geneItems();
                mAdapter = new ArrayAdapter<String>(XListViewActivity.this, R.layout.vw_list_item,
                        items);
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2500);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2500);
    }

    private void geneItems() {
        for (int i = 0; i != 20; ++i) {
            items.add("Test XListView item " + (++mIndex));
        }
    }

    private void onLoad() {
        mListView.stopRefresh();//隐藏顶部的等待状态
        mListView.stopLoadMore();//隐藏底部的等待状态
        mListView.setRefreshTime(getTime());//设置完成时间
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

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

}

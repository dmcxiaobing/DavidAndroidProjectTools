package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 *
 * @新浪微博 ：http://weibo.com/mcxiaobing
 *
 * @GitHub: https://github.com/QQ986945193
 *
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 *
 */
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.umeng.analytics.MobclickAgent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.XListView;

/**
 * XListView下拉刷新和上拉加载更多 这里使用的是模拟数据
 */
public class DavidXListViewActivity extends Activity {

    private XListView mListView;

    private int currentPage = 0;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlist_view);
        mListView = (XListView) findViewById(R.id.list_view);
        // 支持下拉刷新
        mListView.setPullRefreshEnable(true);
        // 支持滚动加载
        mListView.setPullLoadEnable(true);
        // 自动加载
        mListView.setAutoLoadEnable(true);
        // IXListViewListener:监听用户两个操作 1.下拉：重新获取首页 2.滚动底部:获取下一页
        // mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());
        // 获取第一页数据
        currentPage = 1;
        GetDataTask task = new GetDataTask();
        task.execute();// 打开一个线程 thread.start

        XListView.IXListViewListener listener = new MyIXListViewListener();
        mListView.setXListViewListener(listener);
    }

    private class MyIXListViewListener implements XListView.IXListViewListener {

        // 下拉
        @Override
        public void onRefresh() {
            currentPage = 1;
            GetDataTask task = new GetDataTask();
            task.execute();// 打开一个线程 thread.start
        }

        // 滚动
        @Override
        public void onLoadMore() {
            ++currentPage;
            GetDataTask task = new GetDataTask();
            task.execute();// 打开一个线程 thread.start
        }

    }

    @SuppressLint("NewApi")
    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        // run
        @Override
        protected Void doInBackground(Void... params) {
            //实际项目 可能从服务端取数据 或者从数据里面取数
            //模拟假数据
            if (currentPage == 1) {
                list.clear();
                for (int i = 0; i < 20; i++) {
                    list.add("页面数" + currentPage + " 序号" + i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    list.add("页面数" + currentPage + " 序号" + i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        // handleMessage

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            // 主线程
            // ListView
            // |----XListView Adapter
            //
            if (adapter == null) {
                adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.vw_list_item, list);
                mListView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
            stopWait();
        }

    }

    ;

    private void stopWait() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getTime());
    }

    ArrayAdapter<String> adapter;

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

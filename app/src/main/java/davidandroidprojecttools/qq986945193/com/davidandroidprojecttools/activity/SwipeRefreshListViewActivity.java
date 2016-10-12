package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * SwipeRefreshLayout的简单使用教程。下拉刷新控件炫酷效果。
 */
public class SwipeRefreshListViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_swipe_refresh_listview);
        mListView = (ListView) findViewById(R.id.listview);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        //绑定刷新时间
        mSwipeLayout.setOnRefreshListener(this);
        //设置颜色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    protected void initData() {
        /**
         * listview绑定adapter
         */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
        mListView.setAdapter(adapter);

    }

    /**
     * 监听swipeRefresh
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //停止刷新
                mSwipeLayout.setRefreshing(false);

            }
        }, 3000);
    }


    private ArrayList<String> getData() {
        list.add("Hello world!");
        list.add("CSDN:程序员小冰");
        list.add("An Android Developer");
        list.add("http://weibo.com/mcxiaobing");
        list.add("http://git.oschina.net/MCXIAOBING");
        list.add("https://github.com/QQ986945193");
        list.add("An Android Developer");
        list.add("http://weibo.com/mcxiaobing");
        list.add("http://git.oschina.net/MCXIAOBING");
        list.add("https://github.com/QQ986945193");
        list.add("An Android Developer");
        list.add("http://weibo.com/mcxiaobing");
        list.add("http://git.oschina.net/MCXIAOBING");
        list.add("https://github.com/QQ986945193");
        return list;
    }

}

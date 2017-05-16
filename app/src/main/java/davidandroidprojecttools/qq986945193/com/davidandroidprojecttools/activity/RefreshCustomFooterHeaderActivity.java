package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.RecyclerViewIntroduceAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OnTouchUpListener;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.DisplayUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.PullLoadRecyclerViewLayout;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.CustomDividerItemDecoration;

import static com.tencent.open.utils.ThreadManager.init;

/**
 * Created by david zheng
 * <p>
 * 可自动更改头部和底部的刷新与加载更多
 */
public class RefreshCustomFooterHeaderActivity extends BaseActivity implements OnTouchUpListener {
    private PullLoadRecyclerViewLayout mLayout;
    private RecyclerViewIntroduceAdapter mAdapter;

    private View mHeaderView;
    private View mFooterView;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_refresh_custom_footerheader);
        mLayout = (PullLoadRecyclerViewLayout) findViewById(R.id.pull_load_layout);

        init();
    }
    private List<String> lists = new ArrayList<>();

    @Override
    protected void initData() {
        lists.addAll(getData());
        mAdapter = new RecyclerViewIntroduceAdapter(this, lists);
        LayoutInflater inflater = LayoutInflater.from(RefreshCustomFooterHeaderActivity.this);
        /**
         * 引入头部和底部布局
         */
        mHeaderView = inflater.inflate(R.layout.co_refresh_header, null);
        mFooterView = inflater.inflate(R.layout.co_refresh_footer, null);
        mLayout.addHeaderView(mHeaderView, DisplayUtils.dip2px(RefreshCustomFooterHeaderActivity.this, 60));
        mLayout.addFooterView(mFooterView, DisplayUtils.dip2px(RefreshCustomFooterHeaderActivity.this, 40));
        mLayout.setMyRecyclerView(new LinearLayoutManager(RefreshCustomFooterHeaderActivity.this, LinearLayoutManager.VERTICAL, false),
                mAdapter, true);
        mLayout.setItemDivider(new CustomDividerItemDecoration(RefreshCustomFooterHeaderActivity.this, LinearLayoutManager.HORIZONTAL,
                1, getResources().getColor(android.R.color.black)));
        mLayout.addOnTouchUpListener(this);
    }
    @Override
    public void OnRefreshing() {
        mLayout.setHeaderState(PullLoadRecyclerViewLayout.HEADER_STATE_REFRESHING);
        onDataRefreshing();
    }

    @Override
    public void OnLoading() {
        mLayout.setFooterState(PullLoadRecyclerViewLayout.FOOTER_STATE_LOADING);
        onDataLoadingMore();
    }

    private void onDataRefreshing() {
        //自己的逻辑
        mLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                lists.clear();
                lists.addAll(getData());
                Log.e("onDataRefreshing", "onDataRefreshing");

                onRefreshFinish(true);
            }
        }, 3000);
    }

    private void onDataLoadingMore() {
        //自己的逻辑
        mLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("onDataLoadingMore", "onDataLoadingMore");

                lists.addAll(getData());
                onLoadMoreFinish(true);
            }
        }, 2000);
    }

    private void onRefreshFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setHeaderState(success ? PullLoadRecyclerViewLayout.HEADER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.HEADER_STATE_FAIL);
                mAdapter.notifyDataSetChanged();
            }
        }.sendEmptyMessageDelayed(0, 100);
    }

    private void onLoadMoreFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setFooterState(success ? PullLoadRecyclerViewLayout.FOOTER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.FOOTER_STATE_FAIL);
                mAdapter.notifyDataSetChanged();

            }
        }.sendEmptyMessageDelayed(0, 100);
    }


    private List<String> getData() {
        List<String> list = new ArrayList<>();
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

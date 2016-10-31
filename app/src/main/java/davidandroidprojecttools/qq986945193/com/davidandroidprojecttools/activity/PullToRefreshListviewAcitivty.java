package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.MyCommonAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pulltorefreshlibrarary.PullToRefreshBase;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pulltorefreshlibrarary.PullToRefreshGridView;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pulltorefreshlibrarary.PullToRefreshListView;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * pulltofreshListview上拉加载与下拉刷新
 */
public class PullToRefreshListviewAcitivty extends BaseActivity {
    @BindView(R.id.pgridview)
    PullToRefreshGridView mgridView;
    @BindView(R.id.plistview)
    PullToRefreshListView mlistview;
    private String url = "";
    private int page = 1;
    private List<TopListBean.TngouBean> lists = new ArrayList<>();
    private MyCommonAdapter mMyCommonAdapter;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_pulltorefresh_listview);
        ButterKnife.bind(this);
        mgridView.setVisibility(View.GONE);
        mlistview.setVisibility(View.VISIBLE);
        mlistview.setMode(PullToRefreshBase.Mode.BOTH);
        mlistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                lists.clear();
//                mMyCommonAdapter.notifyDataSetChanged();
                loadData();
            }

            /**
             * 上拉加载更多
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        loadData();
    }

    private void loadData() {
        url = Urls.AUTOLOADMORE_TOP_LIST + "?id=1" + "&page=" + page + "&rows=" + "16";
        okHttpUtils.get(url, null, new OkHttpStopCallback<TopListBean>() {

            @Override
            public void onSuccess(Response response, TopListBean mTopListBean) {
                if (mTopListBean != null) {
                    if (mTopListBean.getTngou() != null) {
                        if (mTopListBean.getTngou() != null) {
                            lists.addAll(mTopListBean.getTngou());
                            if (lists == null && lists.size() == 0) {
                                return;
                            }
                            if (mMyCommonAdapter == null) {
                                mMyCommonAdapter = new MyCommonAdapter(mContext, lists);

                                /**
                                 * listview绑定adapter
                                 */
                                mlistview.setAdapter(mMyCommonAdapter);
                            } else {
                                mMyCommonAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                }
                mlistview.onRefreshComplete();

            }

            @Override
            public void onFailure(Request request, Exception e) {
                super.onFailure(request, e);
                mlistview.onRefreshComplete();
            }
        });
    }
}

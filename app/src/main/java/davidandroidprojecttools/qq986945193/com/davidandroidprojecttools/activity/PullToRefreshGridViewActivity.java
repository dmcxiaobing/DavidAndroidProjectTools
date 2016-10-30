package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.view.View;
import android.widget.GridView;
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
 * pulltofreshGridview上拉加载与下拉刷新
 */
public class PullToRefreshGridViewActivity extends BaseActivity {
    @BindView(R.id.pgridview)
    PullToRefreshGridView mgridView;
    private String url = "";
    private int page = 1;
    private List<TopListBean.TngouBean> lists = new ArrayList<>();
    private MyCommonAdapter mMyCommonAdapter;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_pulltorefresh_listview);
        ButterKnife.bind(this);
        mgridView.setVisibility(View.VISIBLE);
        mgridView.setMode(PullToRefreshBase.Mode.BOTH);
        mgridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
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
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
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
        url = Urls.AUTOLOADMORE_TOP_LIST + "?id=1" + "&page=" + page + "&rows=" + "15";
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
                                mgridView.setAdapter(mMyCommonAdapter);
                            } else {
                                mMyCommonAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                }
                mgridView.onRefreshComplete();

            }

            @Override
            public void onFailure(Request request, Exception e) {
                super.onFailure(request, e);
                mgridView.onRefreshComplete();
            }
        });
    }
}

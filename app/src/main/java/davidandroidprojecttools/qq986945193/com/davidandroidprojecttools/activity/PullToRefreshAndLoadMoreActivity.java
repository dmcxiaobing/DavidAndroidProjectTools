package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.MyCommonAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TxApiAppleBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.XListviewAndTimeUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.PullToRefreshMyListView;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.core.PullToRefreshLayout;
import okhttp3.Response;

/**
 * 带有自动加载与上拉刷新的listview
 */
public class PullToRefreshAndLoadMoreActivity extends BaseActivity {
    @BindView(R.id.pl_listview)
    PullToRefreshMyListView pl_listview;
    private String url = "";
    private int page = 1;
    private List<TopListBean.TngouBean> lists = new ArrayList<>();
    private MyCommonAdapter mMyCommonAdapter;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_pull_to_refresh_and_load_more);
        ButterKnife.bind(this);
        mMyCommonAdapter = new MyCommonAdapter(mContext, lists);
        pl_listview.getListView().setAdapter(mMyCommonAdapter);

        pl_listview.setLoadDataListener(new PullToRefreshLayout.LoadDataListener() {
            @Override
            //下拉刷新调用
            public void onRefresh() {
                page = 1;
                lists.clear();
                mMyCommonAdapter.notifyDataSetChanged();
                loadData(true);

            }

            @Override
            //加载更多调用
            public void onLoadMore() {
                page += 500;
                initData();
                loadData(false);

            }
        });
    }

    @Override
    protected void initData() {
        loadData(false);
    }

    private void loadData(final boolean needFresh) {
        if (needFresh) {
            lists.clear();
        }
        LogUtil.E("size " + lists.size());
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
                                pl_listview.getListView().setAdapter(mMyCommonAdapter);
                            } else {
                                mMyCommonAdapter.notifyDataSetChanged();
                            }
                            //是否需要更新和是否还有更多内容
                            pl_listview.onLoadComplete(needFresh, true);
//                            XListviewAndTimeUtils.stopWait(mListView)
                        }
                    }

                }
            }

        });


    }


}

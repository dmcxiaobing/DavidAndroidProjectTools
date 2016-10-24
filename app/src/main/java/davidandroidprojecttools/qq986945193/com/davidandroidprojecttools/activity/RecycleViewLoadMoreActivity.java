package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.RecycleViewLoadMoreAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;
import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * RecycleView的自动加载更多数据
 */
public class RecycleViewLoadMoreActivity extends BaseActivity {
    private RecyclerView recycle_auto_loadmore;
    private RecycleViewLoadMoreAdapter mRecycleViewLoadMoreAdapter;
    private LinearLayoutManager layoutManager;

    private OkHttpUtils okHttpUtils = MyApplication.getApp().getOkHttpUtils();
    //页数
    int pageIndex = 1;

    private ArrayList<TopListBean.TngouBean> lists = new ArrayList<>();
    private AlertDialog githubDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycle_view_load_more);
        recycle_auto_loadmore = (RecyclerView) findViewById(R.id.recycle_auto_loadmore);
        layoutManager = new LinearLayoutManager(mContext);
        mRecycleViewLoadMoreAdapter = new RecycleViewLoadMoreAdapter();

        mRecycleViewLoadMoreAdapter.setOnItemClickListener(onItemClickListener);
        recycle_auto_loadmore.setLayoutManager(layoutManager);
        recycle_auto_loadmore.setAdapter(mRecycleViewLoadMoreAdapter);

        recycle_auto_loadmore.addOnScrollListener(onScrollListener);
        githubDialog = new SpotsDialog(mContext);
    }

    @Override
    protected void initData() {
        String url = Urls.AUTOLOADMORE_TOP_LIST + "?id=1" + "&page=" + pageIndex + "&rows=" + "20";
        if (!isFinishing()) {
            githubDialog.show();
            okHttpUtils.get(url, null, new OkHttpStopCallback<TopListBean>() {

                @Override
                public void onSuccess(Response response, TopListBean topListBean) {
                    hiddenGithubLoading();
                    if (topListBean != null) {
                        if (topListBean.getTngou() != null) {
                            lists.addAll(topListBean.getTngou());
                            mRecycleViewLoadMoreAdapter.setData(lists);
                            if (topListBean.getTngou().size() <= 0) {
                                mRecycleViewLoadMoreAdapter.isShowFooter(false);
                            }
                        }
                    }


                }

                @Override
                public void onFailure(Request request, Exception e) {
                    super.onFailure(request, e);
                    hiddenGithubLoading();
                }
            });
        }

    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //SCROLL_STATE_IDLE
            //The RecyclerView is not currently scrolling.
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mRecycleViewLoadMoreAdapter.getItemCount()
                    && mRecycleViewLoadMoreAdapter.isShowFooter()) {
                //加载更多新闻
                pageIndex += 1;
                initData();
            }

        }
    };


    private RecycleViewLoadMoreAdapter.OnItemClickListener onItemClickListener = new RecycleViewLoadMoreAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            ToastUtils.show(mContext, "pos" + position, Toast.LENGTH_SHORT);
        }
    };


    /**
     * 隐藏正在加载
     */
    private void hiddenGithubLoading() {
        if (githubDialog != null && githubDialog.isShowing()) {
            githubDialog.dismiss();
        }
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.BaseViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.PullRecycler;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.base.BaseListActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.ILayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.MyGridLayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.MyLinearLayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.MyStaggeredGridLayoutManager;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 带有自动加载与上拉刷新的activity recycleView
 */
public class PullRefreshRecycleViewActivity extends BaseListActivity<TopListBean.TngouBean> {
    //页数
    int pageIndex = 1;

    @Override
    protected void initView() {
        super.initView();
        recycler.setRefreshing();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    private int random;


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_introduce, parent, false);
        return new SampleViewHolder(view);
    }


    @Override
    protected ILayoutManager getLayoutManager() {
        /**
         * 这里利用的0 1 2的随机数进行设置item的列数的。
         *
         * random = 0 则表示为一列， 2则是两列。为3则是3列。我这里直接写成1列
         *
         * https://github.com/QQ986945193
         */
//        random = new Random().nextInt(3);
        random = new Random().nextInt(1);
        switch (random) {
            case 0:
                return new MyLinearLayoutManager(getApplicationContext());
            case 1:
                return new MyGridLayoutManager(getApplicationContext(), 2);
            case 2:
                return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        }
        return super.getLayoutManager();
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        if (random == 0) {
            return super.getItemDecoration();
        } else {
            return null;
        }
    }

    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<TopListBean.TngouBean>();
        }

        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            pageIndex = 1;
        } else if (action == PullRecycler.ACTION_LOAD_MORE_REFRESH) {
            pageIndex++;
        }
        String url = Urls.AUTOLOADMORE_TOP_LIST + "?id=1" + "&page=" + pageIndex + "&rows=" + "20";
        if (!isFinishing()) {
            okHttpUtils.get(url, null, new OkHttpStopCallback<TopListBean>() {

                @Override
                public void onSuccess(Response response, TopListBean topListBean) {
                    if (topListBean != null) {
                        if (topListBean.getTngou() != null) {
                            if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                                mDataList.clear();
                            }
                            recycler.enableLoadMore(true);
                            mDataList.addAll(topListBean.getTngou());
                            adapter.notifyDataSetChanged();


                        } else {
                            recycler.enableLoadMore(false);

                        }
                    }

                    recycler.onRefreshCompleted();


                }

                @Override
                public void onFailure(Request request, Exception e) {
                    super.onFailure(request, e);
                    recycler.onRefreshCompleted();

                }
            });
        }

    }

    class SampleViewHolder extends BaseViewHolder {

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.tv_introduce_name);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.iv_introduce_img);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setText(mDataList.get(position).getTitle());
//            Glide.with(mSampleListItemImg.getContext())
//                    .load(mDataList.get(position).url)
//                    .centerCrop()
//                    .placeholder(R.color.app_primary_color)
//                    .crossFade()
//                    .into(mSampleListItemImg);
        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }
}

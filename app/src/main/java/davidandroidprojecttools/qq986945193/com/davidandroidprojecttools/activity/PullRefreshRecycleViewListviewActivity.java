package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.MyCommonAdapter;
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
 * recycleView嵌套listview刷新与加载更多
 */
public class PullRefreshRecycleViewListviewActivity extends BaseListActivity<TopListBean.TngouBean> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull_refresh_recycle_view_listview, parent, false);
        return new PullRefreshRecycleViewListviewActivity.SampleViewHolder(view);
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

    private List<TopListBean.TngouBean> lists = new ArrayList<>();
    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<TopListBean.TngouBean>();
        }
        /**
         * 根据action判断是加载更多还是下拉刷新
         * ACTION_PULL_TO_REFRESH 下拉刷新
         * ACTION_LOAD_MORE_REFRESH 加载更多
         */
        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            pageIndex = 1;
        } else if (action == PullRecycler.ACTION_LOAD_MORE_REFRESH) {
            pageIndex++;
        }
        String url = Urls.AUTOLOADMORE_TOP_LIST + "?id=1" + "&page=" + pageIndex + "&rows=" + "5";
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
        davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.WrapHeightGridView gv_item_prview;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.iv_introduce_img);
            gv_item_prview = (davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.WrapHeightGridView) itemView.findViewById(R.id.gv_item_prview);
        }

        @Override
        public void onBindViewHolder(int position) {
//            mSampleListItemLabel.setText(mDataList.get(position).getTitle());
//            Glide.with(mSampleListItemImg.getContext())
//                    .load(mDataList.get(position).url)
//                    .centerCrop()
//                    .placeholder(R.color.app_primary_color)
//                    .crossFade()
//                    .into(mSampleListItemImg);
            if (mDataList!=null){
                MyCommonAdapter adapter = new MyCommonAdapter(mContext, mDataList);
                gv_item_prview.setAdapter(adapter);
            }

        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }


}

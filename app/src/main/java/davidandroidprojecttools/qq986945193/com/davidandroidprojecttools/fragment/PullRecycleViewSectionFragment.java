package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.ConstantValues;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.BaseViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.PullRecycler;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.base.BaseListFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.base.BaseSectionListFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.ILayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.MyGridLayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.MyLinearLayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager.MyStaggeredGridLayoutManager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.section.SectionData;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PicassoWithImageLoaderImageViewUtils;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class PullRecycleViewSectionFragment extends BaseSectionListFragment<String> {

    @Override
    protected BaseViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_introduce, parent, false);
        return new SportsViewHolder(view);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setRefreshing();

    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        if (random == 0) {
            return super.getItemDecoration();
        } else {
            return null;
        }
    }

    //页数
    int pageIndex = 1;
    int random = 1;

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
        random = new Random().nextInt(3);
        switch (random) {
            case 0:
                return new MyLinearLayoutManager(getContext());
            case 1:
                return new MyGridLayoutManager(getContext(), 3);
            case 2:
                return new MyStaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        }
        return super.getLayoutManager();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void onRefresh(final int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }

        recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
                    mDataList.clear();
                }
                int size = mDataList.size();
                mDataList.add(new SectionData(true, size, "header " + size));
                for (int i = size; i < size + 20; i++) {
                    mDataList.add(new SectionData(ConstantValues.images[i]));
                }
                adapter.notifyDataSetChanged();
                recycler.onRefreshCompleted();
                if (mDataList.size() < 100) {
                    recycler.enableLoadMore(true);
                } else {
                    recycler.enableLoadMore(false);
                }
            }
        }, 3000);

    }

    class SportsViewHolder extends BaseViewHolder {

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;

        public SportsViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.tv_introduce_name);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.iv_introduce_img);
        }

        @Override
        public void onBindViewHolder(int position) {
            mSampleListItemLabel.setVisibility(View.GONE);
            PicassoWithImageLoaderImageViewUtils.displayImage(mDataList.get(position).t, mSampleListItemImg);
        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {

    }

}

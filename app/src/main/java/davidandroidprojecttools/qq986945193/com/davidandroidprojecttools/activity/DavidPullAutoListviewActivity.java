package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.MyAdapter_CommonAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.ViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PicassoWithImageLoaderImageViewUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PullListViewCompleteUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.DavidAutoPullListView;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DavidPullListview的下拉刷新与上拉自动加载
 *
 * 若是想点击加载更多，可以修改DavidAutoPullListView
 */
public class DavidPullAutoListviewActivity extends BaseActivity {
    private DavidAutoPullListView pull_listview;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_david_pullautolistview);
        pull_listview = (DavidAutoPullListView) findViewById(R.id.pull_listview);
    }
    //页数
    private int pageIndex = 1;
    private String url = "http://www.tngou.net/api/top/list" + "?id=1" + "&page=" + pageIndex + "&rows=" + "20";
    private List<TopListBean.TngouBean> mDataList = new ArrayList<>();
    private MyCommonAdapter adapter;
    @Override
    protected void initData() {
        adapter = new MyCommonAdapter(DavidPullAutoListviewActivity.this, mDataList);
        pull_listview.setAdapter(adapter);
        pull_listview.performRefresh();
        getData();
    }

    @Override
    protected void setOnclickListener() {
        /**
         * 下拉刷新
         */
        pull_listview.setOnRefreshListener(new DavidAutoPullListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                getData();
            }
        });
        /**
         * 上拉加载更多
         */
        pull_listview.setOnGetMoreListener(new DavidAutoPullListView.OnGetMoreListener() {
            @Override
            public void onGetMore() {
                pageIndex++;
                getData();
            }
        });


    }

    private void getData() {
        okHttpUtils.get(url, null, new OkHttpStopCallback<TopListBean>() {

            @Override
            public void onSuccess(Response response, TopListBean topListBean) {
                if (topListBean != null) {
                    if (topListBean.getTngou() != null) {
                        if (pageIndex == 1){
                            mDataList.clear();
                        }
                        mDataList.addAll(topListBean.getTngou());
                        adapter.notifyDataSetChanged();
                    }
                }
                PullListViewCompleteUtils.pullListviewComplete(pull_listview);


            }

            @Override
            public void onFailure(Request request, Exception e) {
                super.onFailure(request, e);
                PullListViewCompleteUtils.pullListviewComplete(pull_listview);

            }
        });

    }

    class MyCommonAdapter extends MyAdapter_CommonAdapter<TopListBean.TngouBean> {
        private List<TopListBean.TngouBean> lists = new ArrayList<>();

        public MyCommonAdapter(Context context, List<TopListBean.TngouBean> datas) {
            super(context, datas);
            this.lists.clear();
            this.lists.addAll(datas);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = ViewHolder.getHolder(mContext, convertView, parent,
                    R.layout.item_listview_introduce, position);
            TopListBean.TngouBean bean = mDatas.get(position);

            TextView tv_introduce_name;
            ImageView iv_introduce_img;

            tv_introduce_name = holder.getView(R.id.tv_introduce_name);
            iv_introduce_img = holder.getView(R.id.iv_introduce_img);


            tv_introduce_name.setText(bean.getTitle());
//        PicassoWithImageLoaderImageViewUtils.withImageView(mContext, bean.getImg(), iv_introduce_img);
            PicassoWithImageLoaderImageViewUtils.displayImage(bean.getImg(), iv_introduce_img);
            return holder.getmConvertView();
        }

    }
}

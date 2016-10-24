package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
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
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.ViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.AutoLoadMoreListView;
import okhttp3.Response;

/**
 * 自动加载更多AutoLoadmore仿微博
 */
public class AutoLoadMoreActivity extends BaseActivity implements AutoLoadMoreListView.OnLoadMoreListener {
    private List<TopListBean.TngouBean> mLists = new ArrayList<>();
    private OkHttpUtils okHttpUtils = MyApplication.getApp().getOkHttpUtils();
    private MyAdapter myAdapter;

    private AutoLoadMoreListView auto_listview;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_auto_load_more);
        auto_listview = (AutoLoadMoreListView) findViewById(R.id.auto_listview);
        myAdapter = new MyAdapter(this, mLists);
        auto_listview.setAdapter(myAdapter);
        auto_listview.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        String url = Urls.AUTOLOADMORE_TOP_LIST + "?id=1" + "&page=" + page + "&rows=" + "15";
        okHttpUtils.get(url, null, new OkHttpStopCallback<TopListBean>() {

            @Override
            public void onSuccess(Response response, TopListBean topListBean) {
                if (topListBean != null) {
                    if (topListBean.getTngou() != null) {
                        mLists.addAll(topListBean.getTngou());
                        LogUtil.E("size" + mLists.size());
                        myAdapter.notifyDataSetChanged();
                        auto_listview.onLoadMoreComplete();

                        if (page == 1) {
                            if (mLists.size() < 15) {
                                isEnd = false;
                                auto_listview.showLoadComplete();
                            }

                        } else {
                            if (topListBean.getTngou().size() < 15) {
                                isEnd = false;
                                auto_listview.showLoadComplete();
                            }
                        }

                    } else {
                        isEnd = false;
                        auto_listview.showLoadComplete();
                    }
                }

            }
        });
    }

    private boolean isEnd = true;
    private int page = 1;

    /**
     * 加载更多的方法
     */
    @Override
    public void onLoadMore() {
        if (isEnd) {
            page+=1;
            initData();
        } else {
            auto_listview.showLoadComplete();
        }
    }

    private class MyAdapter extends MyAdapter_CommonAdapter<TopListBean.TngouBean> {

        public MyAdapter(Context context, List<TopListBean.TngouBean> datas) {
            super(context, datas);
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

            return holder.getmConvertView();
        }
    }

}

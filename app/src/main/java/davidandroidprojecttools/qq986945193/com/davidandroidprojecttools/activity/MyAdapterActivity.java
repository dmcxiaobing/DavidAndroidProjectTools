package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.ListViewIntroduceAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.MyAdapter_CommonAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.ViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TxApiAppleBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PicassoWithImageLoaderImageViewUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.XListviewAndTimeUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.XListView;
import okhttp3.Response;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 利用万能Listview的adapter进行展示数据
 */
public class MyAdapterActivity extends BaseActivity implements XListView.IXListViewListener {
    private XListView mListView;
    private MyAdapter mAdapter;
    private int page = 1;
    private OkHttpUtils mOkHttpUtils = MyApplication.getApp().getOkHttpUtils();
    private Map<String, String> params = new HashMap<>();
    private List<TxApiAppleBean.NewslistBean> lists = new ArrayList<>();
    private String url = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_adapter);
        mListView = (XListView) findViewById(R.id.xlv_my_adapter_activity);
        // 支持下拉刷新
        mListView.setPullRefreshEnable(true);
        // 支持滚动加载
        mListView.setPullLoadEnable(true);
        // 自动加载
        mListView.setAutoLoadEnable(true);
        // IXListViewListener:监听用户两个操作 1.下拉：重新获取首页 2.滚动底部:获取下一页
        // mListView.setXListViewListener(this);
        mListView.setRefreshTime(XListviewAndTimeUtils.getTime());
        mListView.setXListViewListener(this);
    }

    @Override
    protected void initData() {


        url = Urls.TXAPI_APPLE_APPLE_POST + "?num=10&page=" + page;
        mOkHttpUtils.get(url, null, new OkHttpStopCallback<TxApiAppleBean>() {

            @Override
            public void onSuccess(Response response, TxApiAppleBean txApiAppleBean) {
                if (txApiAppleBean != null) {
                    if (txApiAppleBean.getCode() != null && txApiAppleBean.getCode().equals("200")) {
                        if (txApiAppleBean.getNewslist() != null) {
                            lists.addAll(txApiAppleBean.getNewslist());
                            if (mAdapter == null) {
                                mAdapter = new MyAdapter(mContext, lists);
                                /**
                                 * listview绑定adapter
                                 */
                                mListView.setAdapter(mAdapter);
                            } else {
                                mAdapter.notifyDataSetChanged();
                            }
                            XListviewAndTimeUtils.stopWait(mListView);
                        } else {
                            XListviewAndTimeUtils.stopWait(mListView);
                        }
                    } else {
                        XListviewAndTimeUtils.stopWait(mListView);

                    }

                } else {
                    XListviewAndTimeUtils.stopWait(mListView);
                }
            }
        });
        /**
         * post请求，接口不支持，所以这里使用get
         */
        //        params.put("num", "10");
//        params.put("page", page + "");
//        mOkHttpUtils.post(Urls.TXAPI_APPLE_APPLE_POST, params, null, new OkHttpStopCallback<TxApiAppleBean>() {
//
//            @Override
//            public void onSuccess(Response response, TxApiAppleBean txApiAppleBean) {
//                if (txApiAppleBean != null) {
//                    if (txApiAppleBean.getCode() != null && txApiAppleBean.getCode().equals("200")) {
//                        if (txApiAppleBean.getNewslist() != null) {
//                            lists.addAll(txApiAppleBean.getNewslist());
//                            if (mAdapter == null) {
//                                mAdapter = new MyAdapter(mContext, lists);
//                                /**
//                                 * listview绑定adapter
//                                 */
//                                mListView.setAdapter(mAdapter);
//                            } else {
//                                mAdapter.notifyDataSetChanged();
//                            }
//                        }
//                    } else {
//                        XListviewAndTimeUtils.stopWait(mListView);
//
//                    }
//
//                } else {
//                    XListviewAndTimeUtils.stopWait(mListView);
//                }
//            }
//        });

    }

    /**
     * 下拉刷新 page最开始的
     */
    @Override
    public void onRefresh() {
        page = 1;
        lists.clear();
        initData();
    }

    /**
     * 加载更多，page++;
     */
    @Override
    public void onLoadMore() {
        page++;
        LogUtil.E("page" + page);
//        if (page == 3) {
//            page = 999999;
//            initData();
//            page = 2;
//        }
        initData();
    }


    private class MyAdapter extends MyAdapter_CommonAdapter<TxApiAppleBean.NewslistBean> {

        public MyAdapter(Context context, List<TxApiAppleBean.NewslistBean> datas) {
            super(context, datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = ViewHolder.getHolder(mContext, convertView, parent,
                    R.layout.item_listview_introduce, position);
            TxApiAppleBean.NewslistBean bean = mDatas.get(position);

            TextView tv_introduce_name;
            ImageView iv_introduce_img;

            tv_introduce_name = holder.getView(R.id.tv_introduce_name);
            iv_introduce_img = holder.getView(R.id.iv_introduce_img);


            tv_introduce_name.setText(bean.getTitle());
            PicassoWithImageLoaderImageViewUtils.withImageView(mContext, bean.getPicUrl(), iv_introduce_img);

            return holder.getmConvertView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.E("zouzhelile");
    }
}

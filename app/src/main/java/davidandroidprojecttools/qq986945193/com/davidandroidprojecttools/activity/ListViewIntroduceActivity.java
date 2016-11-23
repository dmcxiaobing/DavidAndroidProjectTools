package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.ListViewIntroduceAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TxApiAppleBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.ZhiHuBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.ListViewOnScrollListener;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;
import okhttp3.Response;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * ListView详解的类
 */
public class ListViewIntroduceActivity extends BaseActivity {
    private ListView mListView;
    private OkHttpUtils mOkHttpUtils = MyApplication.getApp().getOkHttpUtils();
    private ZhiHuBean mZhiHu;
    private List<ZhiHuBean.StoriesBean> mZhiHulistBeans = new ArrayList<>();
    private ListViewIntroduceAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_listview_introduce);
        mListView = (ListView) findViewById(R.id.listview);
        /**
         * 设置ListView中Item的点击事件
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(mContext, "点击了第" + position + "个", Toast.LENGTH_SHORT);
            }
        });

        /**
         * 监听ListView的滚动事件
         */
      /*  mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });*/

        mListView.setOnScrollListener(new ListViewOnScrollListener(mContext));


    }

    @Override
    protected void initData() {

        mOkHttpUtils.get(Urls.ZHIHU_URL, null, new OkHttpStopCallback<ZhiHuBean>() {

            @Override
            public void onSuccess(Response response, ZhiHuBean zhiHuBean) {
                if (zhiHuBean != null) {
                    mZhiHulistBeans.addAll(zhiHuBean.getStories());
                    if (mAdapter == null) {
                        mAdapter = new ListViewIntroduceAdapter(mZhiHulistBeans, mContext);
                        /**
                         * listview绑定adapter
                         */
                        mListView.setAdapter(mAdapter);
                    } else {
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    ToastUtils.show(mContext, OkHttpUtils.getOkHttpJsonValue(), Toast.LENGTH_SHORT);
                }
            }


        });


    }


}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.ListViewIntroduceAdapter;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TxApiAppleBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;
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
    private TxApiAppleBean mTxApiAppleBean;
    private List<TxApiAppleBean.NewslistBean> newslistBeans = new ArrayList<>();
    private ListViewIntroduceAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_listview_introduce);
        mListView = (ListView) findViewById(R.id.listview);

    }

    @Override
    protected void initData() {

        mOkHttpUtils.get(Urls.TXAPI_APPLE_APPLE, null, new OkHttpStopCallback<TxApiAppleBean>() {

            @Override
            public void onSuccess(Response response, TxApiAppleBean txApiAppleBean) {
                mTxApiAppleBean = txApiAppleBean;
                if (mTxApiAppleBean != null) {
                    if (mTxApiAppleBean.getCode()!=null && mTxApiAppleBean.getCode().equals("200")) {
                        if (mTxApiAppleBean.getNewslist() != null) {
                            newslistBeans.addAll(mTxApiAppleBean.getNewslist());
                            if (mAdapter == null) {
                                mAdapter = new ListViewIntroduceAdapter(newslistBeans, mContext);
                                /**
                                 * listview绑定adapter
                                 */
                                mListView.setAdapter(mAdapter);
                            } else {
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                }
            }
        });


    }


}

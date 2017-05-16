package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 上拉加载更多与下拉刷新的多种实现
 */
public class RefreshLoadMoreActivity extends BaseActivity {
    private Button btn_david_auto_pull_listview;
    private Button btn_pull_xlistview;
    private Button btn_pull_xlistview_two;
    private Button btn_auto_loadmore;
    private Button btn_recycleview_auto_loadmore;
    private Button btn_recycleview_auto_loadmore_swipe_ay;
    private Button btn_recycleview_auto_loadmore_swipe_fm;
    private Button btn_pulltorefresh_auto_loadmore_;
    private Button btn_android_pulltorefresh_listview_;
    private Button btn_android_pulltorefresh_gridview;
    private Button btn_recycleview_auto_loadmore_swipe_sectionay;
    private Button btn_recycleview_auto_loadmore_swipe_sectionfm;
    private Button btn_recycleview_auto_loadmore_listview;
    private Button btn_custom_footer_header_refresh;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_refresh_load_more);
        btn_david_auto_pull_listview = (Button) findViewById(R.id.btn_david_auto_pull_listview);
        btn_pull_xlistview = (Button) findViewById(R.id.btn_pull_xlistview);
        btn_pull_xlistview_two = (Button) findViewById(R.id.btn_pull_xlistview_two);
        btn_auto_loadmore = (Button) findViewById(R.id.btn_auto_loadmore);
        btn_recycleview_auto_loadmore = (Button) findViewById(R.id.btn_recycleview_auto_loadmore);
        btn_recycleview_auto_loadmore_swipe_ay = (Button) findViewById(R.id.btn_recycleview_auto_loadmore_swipe_ay);
        btn_recycleview_auto_loadmore_swipe_fm = (Button) findViewById(R.id.btn_recycleview_auto_loadmore_swipe_fm);
        btn_pulltorefresh_auto_loadmore_ = (Button) findViewById(R.id.btn_pulltorefresh_auto_loadmore_);
        btn_android_pulltorefresh_listview_ = (Button) findViewById(R.id.btn_android_pulltorefresh_listview_);
        btn_android_pulltorefresh_gridview = (Button) findViewById(R.id.btn_android_pulltorefresh_gridview);
        btn_recycleview_auto_loadmore_swipe_sectionay = (Button) findViewById(R.id.btn_recycleview_auto_loadmore_swipe_sectionay);
        btn_recycleview_auto_loadmore_swipe_sectionfm = (Button) findViewById(R.id.btn_recycleview_auto_loadmore_swipe_sectionfm);
        btn_recycleview_auto_loadmore_listview = (Button) findViewById(R.id.btn_recycleview_auto_loadmore_listview);
        btn_custom_footer_header_refresh = (Button) findViewById(R.id.btn_custom_footer_header_refresh);
    }

    @Override
    protected void initData() {
        /**
         * DavidPullListview的下拉刷新与上拉自动加载
         */
        btn_david_auto_pull_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DavidPullAutoListviewActivity.class);
            }
        });
        /**
         * pullXlistview的刷新与加载数据。真实数据
         */
        btn_pull_xlistview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyAdapterActivity.class);
            }
        });
        /**
         * PullXlistview的模拟数据实现效果
         */
        btn_pull_xlistview_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DavidXListViewActivity.class);
            }
        });
        /**
         * 自动加载更多AutoLoadmore仿微博
         */
        btn_auto_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AutoLoadMoreActivity.class);
            }
        });
        /**
         *RecycleView的自动加载更多数据
         */
        btn_recycleview_auto_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecycleViewLoadMoreActivity.class);
            }
        });
        /**
         * 带有自动加载与上拉刷新的listview
         */
        btn_pulltorefresh_auto_loadmore_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullToRefreshAndLoadMoreActivity.class);
            }
        });

        /**
         * 带有自动加载与上拉刷新的activity recycleView
         */
        btn_recycleview_auto_loadmore_swipe_ay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullRefreshRecycleViewActivity.class);
            }
        });
        /**
         * 可自动更改头部和底部的刷新与加载更多
         */
        btn_custom_footer_header_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RefreshCustomFooterHeaderActivity.class);
            }
        });

        /**
         * recycleView嵌套listview刷新与加载更多
         */
        btn_recycleview_auto_loadmore_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullRefreshRecycleViewListviewActivity.class);
            }
        });

        /**
         *带有自动加载与上拉刷新的fragment recycleView
         */
        btn_recycleview_auto_loadmore_swipe_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullRecycleViewFragmentActivity.class);
            }
        });



        /**
         *pulltofreshListview上拉加载与下拉刷新
         */
        btn_android_pulltorefresh_listview_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullToRefreshListviewAcitivty.class);
            }
        });
        /**
         * pulltofreshGridview上拉加载与下拉刷新
         */
        btn_android_pulltorefresh_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullToRefreshGridViewActivity.class);
            }
        });
        /**
         * btn_recycleview_auto_loadmore_swipe_sectionay
         */
        btn_recycleview_auto_loadmore_swipe_sectionay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullRecycleViewSectionActivity.class);
            }
        });

        /**
         * 带有自动加载与上拉刷新的sectionFragment recycleView
         */
        btn_recycleview_auto_loadmore_swipe_sectionfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PullRecycleViewSectionFragmentActivity.class);
            }
        });



    }
}

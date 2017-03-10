package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.support.v7.widget.RecyclerView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * RecycleView的详解
 */

public class RecycleViewIntroduceActivity extends BaseActivity {
    private RecyclerView recycleview;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycle_view_introduce);
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
    }

    @Override
    protected void initData() {

    }
}

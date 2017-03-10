package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter.RecyclerViewIntroduceAdapter;

import static android.R.id.list;


/**
 * RecycleView的详解
 */

public class RecycleViewIntroduceActivity extends BaseActivity {
    private RecyclerView recycleview;
    private RecyclerViewIntroduceAdapter adapter;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycle_view_introduce);
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
    }

    @Override
    protected void initData() {
        /**
         * listview绑定adapter
         */
        adapter = new RecyclerViewIntroduceAdapter();
        recycleview.setAdapter(adapter);
    }


    private List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("Hello world!");
        list.add("CSDN:程序员小冰");
        list.add("An Android Developer");
        list.add("http://weibo.com/mcxiaobing");
        list.add("http://git.oschina.net/MCXIAOBING");
        list.add("https://github.com/QQ986945193");
        list.add("An Android Developer");
        list.add("http://weibo.com/mcxiaobing");
        list.add("http://git.oschina.net/MCXIAOBING");
        list.add("https://github.com/QQ986945193");
        list.add("An Android Developer");
        list.add("http://weibo.com/mcxiaobing");
        list.add("http://git.oschina.net/MCXIAOBING");
        list.add("https://github.com/QQ986945193");
        return list;
    }

}

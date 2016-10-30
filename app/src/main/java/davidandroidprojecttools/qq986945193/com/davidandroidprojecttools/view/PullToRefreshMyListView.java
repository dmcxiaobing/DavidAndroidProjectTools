package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view;

import android.content.Context;
import android.util.AttributeSet;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.core.PullToRefreshLayout;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class PullToRefreshMyListView extends PullToRefreshLayout {
    private Context context;

    public PullToRefreshMyListView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PullToRefreshMyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PullToRefreshMyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    @Override
    public void init() {
        android.widget.ListView listView = new android.widget.ListView(context);
        listView.setDividerHeight(0);
        listView.setVerticalScrollBarEnabled(true);
        setContentView(listView);
        super.init();
    }

    public android.widget.ListView getListView() {
        return (android.widget.ListView) contentView;
    }
}

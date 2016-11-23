package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Context;
import android.widget.AbsListView;

import com.squareup.picasso.Picasso;

/**
 * 用来优化listview的滚动事件
 */
public class ListViewOnScrollListener implements AbsListView.OnScrollListener {
    private Context mContext;

    public ListViewOnScrollListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        /*调用with()方法可以得到picasso的单例对象*/
        final Picasso picasso = Picasso.with(mContext);
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            picasso.resumeTag(mContext);
        } else {
            picasso.pauseTag(mContext);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.layoutmanager;

import android.support.v7.widget.RecyclerView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.BaseListAdapter;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();
    int findLastVisiblePosition();
    void setUpAdapter(BaseListAdapter adapter);
}

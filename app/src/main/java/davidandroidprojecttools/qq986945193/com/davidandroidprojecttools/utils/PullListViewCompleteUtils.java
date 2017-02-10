package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;


/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 *
 * 加载更多与下拉刷新完成的类
 */

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.DavidAutoPullListView;

public class PullListViewCompleteUtils {
    /**
     * 加载更多与下拉刷新完成
     * @param pullListView 变量
     */
    public static void pullListviewComplete(DavidAutoPullListView pullListView) {
        pullListView.refreshComplete();
        pullListView.getMoreComplete();
    }
}

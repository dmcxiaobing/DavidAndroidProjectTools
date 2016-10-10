package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.XListView;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * <p/>
 * XlistView停止 的 类 以及时间类
 */

public class XListviewAndTimeUtils {
    /**
     * 停止
     *
     * @param mListView
     */
    public static void stopWait(XListView mListView) {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getTime());
    }

    /**
     * 获取到当前时间
     *
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
}



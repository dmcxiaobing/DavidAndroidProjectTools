package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView;

import android.support.v7.widget.GridLayoutManager;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class FooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private BaseListAdapter adapter;
    private int spanCount;

    public FooterSpanSizeLookup(BaseListAdapter adapter, int spanCount) {
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isLoadMoreFooter(position) || adapter.isSectionHeader(position)) {
            return spanCount;
        }
        return 1;
    }
}

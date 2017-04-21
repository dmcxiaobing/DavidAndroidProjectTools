package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.AboutDavidFragment;

/**
 * 底部菜单栏突出一部分
 */

public class TopBottomActivity extends BaseFragmentActivity {
    @Override
    protected void initView() {
        replace(R.id.fm_layout, new AboutDavidFragment(), "tabbottomactivity");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_bottom_top;
    }
}

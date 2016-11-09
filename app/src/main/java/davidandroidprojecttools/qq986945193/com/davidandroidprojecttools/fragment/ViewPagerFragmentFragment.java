package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment;

import android.content.Context;
import android.view.View;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class ViewPagerFragmentFragment extends BaseFragment {
    Context mContext;

    @Override
    protected void initData() {
        LogUtil.E("data" + mContext);

    }

    @Override
    protected void initView(View rootView) {
        mContext = getActivity();
        LogUtil.E("mcontext" + mContext);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_take_selector_photo;

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.E("onResume");
        initData();


    }
}

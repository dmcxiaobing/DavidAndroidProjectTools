package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.OkHttpUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：fragment的基类
 */
public abstract class BaseFragment extends Fragment {
    /**
     * ButterKnife的使用，官方在fragment中使用了解绑
     */
//    protected Unbinder unbinder;
    protected OkHttpUtils okHttpUtils = MyApplication.getApp().getOkHttpUtils();
    protected Context mContext;
    protected View rootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化布局文件
     *
     * @param rootView
     */
    protected abstract void initView(View rootView);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResId(), container, false);
//        unbinder = ButterKnife.bind(this, rootView);
        if (getActivity() != null) {
            mContext = getActivity();
        }
        initView(rootView);
        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }

    protected abstract int getLayoutResId();
}

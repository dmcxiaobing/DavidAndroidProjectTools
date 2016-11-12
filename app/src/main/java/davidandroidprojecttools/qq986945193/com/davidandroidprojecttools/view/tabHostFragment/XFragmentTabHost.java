package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.tabHostFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 自定义带有动画效果的fragmentTabHost
 */
public class XFragmentTabHost extends FragmentTabHost {

    private Context mContext;
    private List<View> mTabViews;
    private List<TabItemBean> mTabItems;
    // 字体激活颜色
    private int mTextActiveColor;
    private int mTextInactiveColor;
    // 字体激活大小
    private float mTextActiveSize;
    private float mTextInactiveSize;
    // 视图激活对顶部的偏移
    private int mViewActivePaddingTop;
    private int mViewInactivePaddingTop;
    // 波纹模式的前景颜色和后景颜色
    private int mFrontColor;
    private int mBehindColor;
    // TabHost模式
    private TabMode mTabMode;


    public XFragmentTabHost(Context context) {
        super(context);
        _init(context);
    }

    public XFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    private void _init(Context context) {
        mTabViews = new ArrayList<>();
        mTabItems = new ArrayList<>();
        mContext = context;
        mTextActiveColor = mContext.getResources().getColor(R.color.colorActive);
//                ContextCompat.getColor(mContext, R.color.colorActive);
        mTextInactiveColor = mContext.getResources().getColor(R.color.red);
//                ContextCompat.getColor(mContext, R.color.colorInactive);
        mFrontColor = mContext.getResources().getColor(R.color.colorFront);

//                ContextCompat.getColor(mContext, R.color.colorFront);
        mBehindColor = mContext.getResources().getColor(R.color.colorBehind);
//                ContextCompat.getColor(mContext, R.color.colorBehind);
        mTextActiveSize = getResources().getDimension(R.dimen.text_size_14);
        mTextInactiveSize = getResources().getDimension(R.dimen.text_size_12);
        mViewActivePaddingTop = (int) getResources().getDimension(R.dimen.search_edit_shape_corners_radius);
        mViewInactivePaddingTop = (int) getResources().getDimension(R.dimen.fillet_btn_shape_corners_radius);
        mTabMode = TabMode.MoveToTop;
    }

    /**
     * 覆写父类接口，并在这里做些动画特效
     *
     * @param index 当前选中的Tab项
     */
    @Override
    public void setCurrentTab(int index) {
        // 获取之前选中的index
        int lastIndex = getCurrentTab();
        super.setCurrentTab(index);
        // 选中不同的Tab项才做切换处理
        if (lastIndex != index) {
            _switchTab(lastIndex, index);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 部分机型TabHost带有分割线，同一将分割线设为透明
        getTabWidget().setDividerDrawable(android.R.color.transparent);
    }

    /**
     * 添加TabItem
     *
     * @param item      TabItem
     * @param fragClass fragment类名
     * @param bundle    传给fragment的参数
     */
    public void addTabItem(TabItemBean item, Class<?> fragClass, Bundle bundle) {
        mTabItems.add(item);
        View view = _getIndicator(item);
        mTabViews.add(view);
        this.addTab(newTabSpec(item.getTitle()).setIndicator(view), fragClass, bundle);
    }

    /**
     * 获取TabItem视图
     *
     * @param item TabItem
     * @return
     */
    private View _getIndicator(TabItemBean item) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        TextView title = (TextView) view.findViewById(R.id.tab_title);
        imageView.setImageResource(item.getImageRes());
        title.setText(item.getTitle());
        title.setTextColor(mTextInactiveColor);
        return view;
    }

    /**
     * 切换Tab
     *
     * @param lastIndex 上一个选中索引
     * @param nextIndex 下一个选中索引
     */
    private void _switchTab(int lastIndex, int nextIndex) {
        for (int i = 0; i < mTabViews.size(); i++) {
            if (i == lastIndex) {
                _switchView(i, false);
            } else if (i == nextIndex) {
                _switchView(i, true);
            }
        }
    }

    /**
     * 切换视图
     *
     * @param index       索引
     * @param isActivated 是否激活
     */
    private void _switchView(int index, boolean isActivated) {
        switch (mTabMode) {
            case MoveToTop:
                _doMoveToTop(index, isActivated);
                break;
            case ClipDrawable:
                _doClipDrawable(index, isActivated);
                break;
            case Ripple:
                _doRipple(index, isActivated);
                break;
        }
    }

    /**
     * 背景展开处理
     *
     * @param index       索引
     * @param isActivated 是否激活
     */
    private void _doClipDrawable(int index, boolean isActivated) {
        View view = mTabViews.get(index);
        View tabView = view.findViewById(R.id.tab_layout);
        if (isActivated) {
            TabAnimHelper.clipDrawable(tabView, mTabItems.get(index).getDrawable(), true);
        } else {
            TabAnimHelper.clipDrawable(tabView, mTabItems.get(index).getDrawable(), false);
        }
    }

    /**
     * 波纹处理
     *
     * @param index       索引
     * @param isActivated 是否激活
     */
    private void _doRipple(int index, boolean isActivated) {
        View view = mTabViews.get(index);
        View tabView = view.findViewById(R.id.tab_layout);
        TextView title = (TextView) view.findViewById(R.id.tab_title);
        if (index == 0) {
            TabAnimHelper.rippleDrawable(tabView, mFrontColor, mBehindColor, RippleDrawable.MODE_LEFT, isActivated);
        } else if (index == (mTabViews.size() - 1)) {
            TabAnimHelper.rippleDrawable(tabView, mFrontColor, mBehindColor, RippleDrawable.MODE_RIGHT, isActivated);
        } else {
            TabAnimHelper.rippleDrawable(tabView, mFrontColor, mBehindColor, RippleDrawable.MODE_MIDDLE, isActivated);
        }
        if (isActivated) {
            title.setTextColor(mTextActiveColor);
        } else {
            title.setTextColor(mTextInactiveColor);
        }
    }

    /**
     * 上移动画处理
     *
     * @param index       索引
     * @param isActivated 是否激活
     */
    private void _doMoveToTop(int index, boolean isActivated) {
        View view = mTabViews.get(index);
        TextView title = (TextView) view.findViewById(R.id.tab_title);
        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
        if (isActivated) {
            TabAnimHelper.changeTextColor(title, mTextInactiveColor, mTextActiveColor);
            TabAnimHelper.changeTextSize(title, mTextInactiveSize, mTextActiveSize);
            TabAnimHelper.changeTopPadding(view, mViewInactivePaddingTop, mViewActivePaddingTop);
            TabAnimHelper.changeImageSize(icon, 1.0f, 1.1f);
        } else {
            TabAnimHelper.changeTextColor(title, mTextActiveColor, mTextInactiveColor);
            TabAnimHelper.changeTextSize(title, mTextActiveSize, mTextInactiveSize);
            TabAnimHelper.changeTopPadding(view, mViewActivePaddingTop, mViewInactivePaddingTop);
            TabAnimHelper.changeImageSize(icon, 1.1f, 1.0f);
        }
    }


    /**
     * 属性设置
     *
     * @return
     */
    public int getTextActiveColor() {
        return mTextActiveColor;
    }

    public void setTextActiveColor(int textActiveColor) {
        mTextActiveColor = textActiveColor;
    }

    public int getTextInactiveColor() {
        return mTextInactiveColor;
    }

    public void setTextInactiveColor(int textInactiveColor) {
        mTextInactiveColor = textInactiveColor;
    }

    public int getFrontColor() {
        return mFrontColor;
    }

    public void setFrontColor(int frontColor) {
        mFrontColor = frontColor;
    }

    public int getBehindColor() {
        return mBehindColor;
    }

    public void setBehindColor(int behindColor) {
        mBehindColor = behindColor;
    }

    public TabMode getTabMode() {
        return mTabMode;
    }

    public void setTabMode(TabMode tabMode) {
        mTabMode = tabMode;
    }

    /**
     * Tab的模式
     * MoveToTop：向上偏移
     * Ripple：波纹
     * ClipDrawable：逐步展示背景
     */
    public enum TabMode {

        MoveToTop(1),
        Ripple(2),
        ClipDrawable(3);

        private int tabMode;

        TabMode(int tabMode) {
            this.tabMode = tabMode;
        }

        public int getTabMode() {
            return tabMode;
        }
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.core;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.VersionUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.core.header.FlipLoadingLayout;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class PullToRefreshLayout extends FrameLayout implements AbsListView.OnScrollListener {

    private Context context;

    private LinearLayout dragView;
    private ViewDragHelper mDragHelper;
    /**
     * 内容相关，头部，列表，底部
     */
    private FlipLoadingLayout headerView;
    private FooterLayout footerView;
    protected AbsListView contentView;
    /**
     * 加载中视图
     */
    private View loadingView;
    /**
     * 加载失败视图
     */
    private View errorView;
    private int paddingTop = 0;
    private int paddingBottom = 0;
    //用户按屏幕时的初始点坐标
    private float initY;
    //现在的屏幕坐标点
    private float currentY = 0;
    private float diffY = 0;
    //现在所处的状态
    private State currentState;
    //最大可以下滑距离
    private int maxDragRange = 0;
    //下拉刷新的临界值，超过该值，释放后刷新，否则不刷新
    private int refreshSlop = 0;
    //是否已经到达临界值
    private boolean isRefreshSlopReach;
    //滑动过程中，现在的顶部位置
    private float currentTop = 0;
    //是否在拖动
    private boolean isDragging;
    //是否还有更多数据需要加载
    private boolean hasMore;
    //是否允许下拉刷新
    private boolean isAllowPull = true;
    //是否显示加载更多
    private boolean isShowLoadMore = true;
    //是否现在初始加载界面
    private boolean isShowLoadView = true;
    private LoadDataListener loadDataListener;

    public PullToRefreshLayout(Context context) {
        super(context);
        this.context = context;
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void setContentView(AbsListView contentView) {
        this.contentView = contentView;
    }

    public void setLoadDataListener(LoadDataListener loadDataListener) {
        this.loadDataListener = loadDataListener;
    }

    public void setIsAllowPull(boolean isAllowPull) {
        this.isAllowPull = isAllowPull;
    }

    public void setIsShowLoadMore(boolean isShowLoadMore) {
        this.isShowLoadMore = isShowLoadMore;
    }

    public void setIsShowLoadView(boolean isShowLoadView) {
        this.isShowLoadView = isShowLoadView;
        if (loadingView == null) {
            return;
        }
        if (isShowLoadView) {
            loadingView.setVisibility(View.VISIBLE);
        } else {
            loadingView.setVisibility(View.GONE);
        }
    }


    public void init() {
        dragView = new LinearLayout(context);
        dragView.setOrientation(LinearLayout.VERTICAL);
        headerView = new FlipLoadingLayout(context);
        footerView = new FooterLayout(context);
        addView(dragView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dragView.addView(headerView, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        lp.weight = 1;
        dragView.addView(contentView, 1, lp);
        contentView.setOnScrollListener(this);
        if (contentView instanceof ListView) {
            ((ListView) contentView).addFooterView(footerView);
            footerView.setVisibility(View.GONE);

        } else {
            dragView.addView(footerView, 2, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            footerView.setVisibility(View.GONE);
        }
        if (isShowLoadView) {
            loadingView = View.inflate(context, R.layout.common_loading_data_view, null);
            addView(loadingView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        setState(State.CLOSE);

        ViewTreeObserver viewTreeObserver1 = headerView.getViewTreeObserver();
        viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (VersionUtil.hasJellyBean()) {
                    headerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    headerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                refreshSlop = headerView.getHeight() + 10;
                paddingTop = -headerView.getHeight();
                setPadding(getPaddingLeft(), paddingTop, getPaddingRight(), paddingBottom);
                //这样写只能呵呵
                scrollBy(0, -paddingTop);
            }
        });

        ViewTreeObserver viewTreeObserver2 = contentView.getViewTreeObserver();
        viewTreeObserver2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (VersionUtil.hasJellyBean()) {
                    contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                //listview高度2/3作为滑动范围
                maxDragRange = contentView.getHeight() * 2 / 3;
            }
        });


        mDragHelper = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {
            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                //System.out.println(top);
                if (top <= 0) {
                    setState(State.CLOSE);
                }
                if (top <= headerView.getHeight() && currentState == State.RELEASE_TO_REFRESH) {
                    setState(State.REFRESHING);
                    loadDataListener.onRefresh();
                }
                currentTop = top;
                invalidate();
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (isRefreshSlopReach) {
                    setState(State.RELEASE_TO_REFRESH);
                    smoothSlideTo(headerView.getHeight());
                    //setState(State.REFRESHING);
                    //loadDataListener.onRefresh();

                } else {
                    setState(State.RELEASE_TO_CLOSE);
                    smoothSlideTo(0);
                    setState(State.CLOSE);
                }

                invalidate();

            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

            @Override
            public boolean onEdgeLock(int edgeFlags) {
                return super.onEdgeLock(edgeFlags);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public int getOrderedChildIndex(int index) {
                return super.getOrderedChildIndex(index);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return maxDragRange;
            }

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == dragView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child, left, dx);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top >= refreshSlop && getDirection(dy) == Direction.DOWN) {
                    isRefreshSlopReach = true;
                    if (currentState == State.PULL_TO_REFRESH) {
                        setState(State.REFRESH_SLOP_REACH);
                        requestLayout();
                    }
                }
                if (top > maxDragRange) {
                    return maxDragRange;
                }
                // System.out.println(top);
                return top;
            }
        });

    }


    @Override
    //在diapatch中进行相应的事件的拦截
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isAllowPull) {
            return super.dispatchTouchEvent(event);
        }
        final int action = MotionEventCompat.getActionMasked(event);
        //System.out.println(action);
        //正在刷新，果断拦截事件
        if (currentState == State.REFRESHING) {
            mDragHelper.processTouchEvent(event);
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                initY = event.getRawY();
                //传入初始值，否则，直接传入ACTION_MOVE，会导致NullPointError
                mDragHelper.processTouchEvent(event);
                break;
            }
            //处理多指触控
            case MotionEvent.ACTION_POINTER_DOWN: {
                //传入初始值，否则，直接传入ACTION_MOVE，会导致NullPointError
                mDragHelper.processTouchEvent(event);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                currentY = event.getRawY();
                diffY = currentY - initY;
                //到顶部，并且是向下拉
                if (isTopReached() && getDirection(diffY) == Direction.DOWN) {
                    mDragHelper.processTouchEvent(event);
                    if (currentState == State.CLOSE) {
                        reset();
                        setState(State.PULL_TO_REFRESH);
                    }
                    //发送cancel事件，防止listview响应之前的事件，出现点击操作。
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    super.dispatchTouchEvent(event);
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (currentState != State.CLOSE) {
                    mDragHelper.processTouchEvent(event);
                }
                break;

            }

            case MotionEvent.ACTION_CANCEL: {

                break;

            }

            default:
                break;
        }

        return super.dispatchTouchEvent(event);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //不对事件做任何拦截
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (currentState == State.CLOSE || currentState == State.PULL_TO_REFRESH) {
            super.onLayout(changed, left, top, right, bottom);
        } else {
            dragView.layout(left, (int) currentTop, right, (int) currentTop - paddingTop + bottom - top);
        }
    }

    boolean smoothSlideTo(int targetPosition) {
        if (mDragHelper.smoothSlideViewTo(dragView, dragView.getLeft(), targetPosition)) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }

    //使smoothSlideViewTo起作用，必须要有此处
    @Override
    public void computeScroll() {

        if (mDragHelper != null && mDragHelper.continueSettling(true)) {

            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private boolean isTopReached() {
        final Adapter adapter = contentView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;

        } else {

            if (contentView.getFirstVisiblePosition() <= 1) {
                final View firstVisibleChild = contentView.getChildAt(0);
                if (firstVisibleChild != null) {
                    //第一项已经到头并且还在继续向下拉
                    return firstVisibleChild.getTop() >= 0;
                }
            }
        }

        return false;
    }

    private boolean isBottomReached() {
        final Adapter adapter = contentView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;

        } else {
            final int lastItemPosition = contentView.getCount() - 1;
            final int lastVisiblePosition = contentView.getLastVisiblePosition();

            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - contentView.getFirstVisiblePosition();
                final View lastVisibleChild = contentView.getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    //最后一项已经到头并继续向上拉
                    return lastVisibleChild.getBottom() <= contentView.getHeight();
                }
            }
        }

        return false;
    }

    public void reset() {
        headerView.reset();
        isRefreshSlopReach = false;
    }

    private void setState(State state) {

        currentState = state;

        switch (currentState) {
            case CLOSE: {
                break;
            }
            case PULL_TO_REFRESH: {
                headerView.onPullToRefresh();
                break;
            }
            case REFRESH_SLOP_REACH: {
                headerView.onRefreshSlopReach();
                break;
            }
            case RELEASE_TO_CLOSE: {
                headerView.onPullToRefresh();
                break;
            }
            case RELEASE_TO_REFRESH: {
                headerView.onRefresh();
                break;
            }
            case REFRESHING: {
                headerView.onRefresh();
               /* if(contentView instanceof ListView){
                    footerView.setVisibility(View.VISIBLE);
                }*/
                break;
            }
        }

    }

    Direction getDirection(float diffY) {
        if (diffY > 1) {
            return Direction.DOWN;
        }
        if (diffY < -1) {
            return Direction.UP;
        }

        return Direction.UNKNOWN;

    }

    public enum Direction {

        UNKNOWN(0),

        UP(1),

        DOWN(2);

        public int value;

        Direction(int intValue) {
            value = intValue;
        }

        int getValue() {
            return value;
        }

    }

    public enum State {

        CLOSE(0),

        PULL_TO_REFRESH(1),

        REFRESH_SLOP_REACH(2),

        RELEASE_TO_CLOSE(3),

        RELEASE_TO_REFRESH(4),

        REFRESHING(5);

        public int value;

        State(int intValue) {

            value = intValue;
        }

        int getValue() {

            return value;
        }

    }

    /**
     * 底部加载更多相关
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        //停止滑动
        if (scrollState == SCROLL_STATE_IDLE) {
            //到底部，加载更多
            if (isBottomReached()) {
                if (!hasMore) {
                    return;
                }

                loadDataListener.onLoadMore();
                if (!isShowLoadMore) {
                    return;
                }
                footerView.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }

    public interface LoadDataListener {

        public abstract void onRefresh();

        public abstract void onLoadMore();

    }

    public void onLoadComplete(boolean isFresh, boolean hasMore) {
        this.hasMore = hasMore;
        if (!hasMore && contentView instanceof ListView) {
            footerView.setVisibility(View.GONE);
        }
        if (loadingView != null && loadingView.getParent() == this) {
            removeView(loadingView);
        }
        if (isFresh) {
            if (!isAllowPull) {
                return;
            }
            smoothSlideTo(0);
            //setState(State.CLOSE);
        } else {
            if (!isShowLoadMore) {
                return;
            }
            footerView.setVisibility(View.GONE);
        }

    }

}

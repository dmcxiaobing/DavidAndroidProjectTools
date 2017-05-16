package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OnTouchUpListener;

/**
 * Created by david zheng
 */
public class PullLoadRecyclerViewLayout extends LinearLayout implements NestedScrollingParent {

    private static final int ANIM_INTERVAL = 300;

    public static final int HEADER_STATE_INIT = 0x10;
    public static final int HEADER_STATE_RELEASE_REFRESH = 0x11;
    public static final int HEADER_STATE_REFRESHING = 0x12;
    public static final int HEADER_STATE_COMPLETE = 0x13;
    public static final int HEADER_STATE_FAIL = 0x14;

    public static final int FOOTER_STATE_INIT = 0x20;
    public static final int FOOTER_STATE_RELEASE_LOAD = 0x21;
    public static final int FOOTER_STATE_LOADING = 0x22;
    public static final int FOOTER_STATE_COMPLETE = 0x23;
    public static final int FOOTER_STATE_FAIL = 0x24;

    private Context context = null;
    private NestedScrollingParentHelper helper = null;
    //move total
    private int totalY = 0;
    private LinearLayout headerLayout = null;
    private MyRecyclerView myRecyclerView = null;
    private LinearLayout footerLayout = null;
    private OnTouchUpListener onTouchUpListener = null;
    private boolean isfling = false;
    private int headerHeight = 0;
    private int footerHeight = 0;

    private ImageView mHeaderImg;
    private TextView mHeaderTextView;
    private ImageView mFooterImageView;
    private TextView mFooterTextView;
    private ProgressBar mFooterProgressBar;

    // 下拉箭头的转180°动画
    private RotateAnimation rotateAnimation;

    private boolean isFirst = true;

    public PullLoadRecyclerViewLayout(Context context) {
        super(context);
        this.context = context;
        inital();
    }

    public PullLoadRecyclerViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inital();
    }

    private void inital() {
        helper = new NestedScrollingParentHelper(this);
        headerLayout = new LinearLayout(context);
        myRecyclerView = new MyRecyclerView(context);
        footerLayout = new LinearLayout(context);
        setOrientation(VERTICAL);
        headerLayout.setOrientation(VERTICAL);
        footerLayout.setOrientation(VERTICAL);
        addView(this.headerLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(this.myRecyclerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(this.footerLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.reverse_anim);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
    }

    public void setMyRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        setMyRecyclerView(layoutManager, adapter, false);
    }

    public void setMyRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter, boolean fixed) {
        myRecyclerView.setMyLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setHasFixedSize(fixed);
    }

    public void addHeaderView(View headerView, int headerHeight) {
        this.headerHeight = headerHeight;
        this.headerLayout.removeAllViews();
        this.headerLayout.addView(headerView);
        mHeaderImg = (ImageView) headerView.findViewById(R.id.refresh_header_img);
        mHeaderTextView = (TextView) headerView.findViewById(R.id.refresh_header_txt);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, headerHeight);
        layoutParams.topMargin = -headerHeight;
        this.headerLayout.setLayoutParams(layoutParams);
        setHeaderState(HEADER_STATE_INIT);
    }

    public void addFooterView(View footerView, int footerHeight) {
        this.footerHeight = footerHeight;
        this.footerLayout.removeAllViews();
        this.footerLayout.addView(footerView);
        mFooterImageView = (ImageView) footerView.findViewById(R.id.co_pull_to_load_image);
        mFooterTextView = (TextView) footerView.findViewById(R.id.co_pull_to_load_text);
        mFooterProgressBar = (ProgressBar) footerView.findViewById(R.id.co_pull_to_load_progress);
        this.footerLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, footerHeight));
        setFooterState(FOOTER_STATE_INIT);
    }

    public void setScrollTo(int fromY, int toY) {
        smoothScrollTo((float) fromY, (float) toY);
    }

    public void setItemDivider(RecyclerView.ItemDecoration itemDecoration) {
        myRecyclerView.addItemDecoration(itemDecoration);
    }

    public int getTotal() {
        return -totalY;
    }


    public void setIsScrollLoad(boolean isScrollLoad) {
        myRecyclerView.isScrollLoad = isScrollLoad;
    }

    public boolean isScrollLoad() {
        return myRecyclerView.isScrollLoad;
    }

    public void setIsScrollRefresh(boolean isScrollRefresh) {
        myRecyclerView.isScrollRefresh = isScrollRefresh;
    }

    public boolean isScrollRefresh() {
        return myRecyclerView.isScrollRefresh;
    }

    public void setRecyclerViewScrollToPosition(int position) {
        myRecyclerView.scrollToPosition(position);
    }

    public void addOnTouchUpListener(OnTouchUpListener onTouchUpListener) {
        this.onTouchUpListener = onTouchUpListener;
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    public void onNestedScrollAccepted(View child, View target, int axes) {
        helper.onNestedScrollAccepted(child, target, axes);
    }

    //parent view intercept child view scroll
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (totalY < 0 && myRecyclerView.isOrientation(0) || totalY > 0 && myRecyclerView.isOrientation(1)) {
            isfling = true;
        }

        if (dy > 0) {//手指上滑,当recyclerview到底了，需要处理上拉加载
            if (!myRecyclerView.canScrollVertically(1)) {//不能继续上拉了，parent处理footerView
                totalY += dy;
                if (totalY >= footerHeight) {
                    setFooterState(FOOTER_STATE_RELEASE_LOAD);
                }
                scrollTo(0, totalY);
                consumed[1] = dy;
            } else {//可以继续下拉
                if (totalY < 0) {//header已出现,先要处理header
                    if (-totalY < headerHeight) {
                        setHeaderState(HEADER_STATE_INIT);
                    }
                    if (totalY + dy > 0) {//header消失，交由子View处理
                        consumed[1] = -totalY;
                        scrollTo(0, 0);
                    } else {//header依然存在，parent处理
                        totalY += dy;
                        scrollTo(0, totalY);
                        consumed[1] = dy;
                    }
                } else {
                    scrollTo(0, 0);
                    consumed[1] = 0;
                }
            }
            return;
        }
        if (dy < 0) {//下拉，当recyclerview到顶了，需要处理下拉刷新
            if (!myRecyclerView.canScrollVertically(-1)) {//不能继续下拉,parent处理headerView
                totalY += dy;
                if (-totalY >= headerHeight) {
                    setHeaderState(HEADER_STATE_RELEASE_REFRESH);
                }
                scrollTo(0, totalY);
                consumed[1] = dy;
            } else {
                if (totalY > 0) {//footer已出现
                    if (totalY < footerHeight) {
                        setFooterState(FOOTER_STATE_INIT);
                    }
                    if (totalY + dy < 0) {//footer消失，剩下的交由子View处理
                        consumed[1] = -totalY;
                        scrollTo(0, 0);
                    } else {//footer依然在，parent处理
                        totalY += dy;
                        scrollTo(0, totalY);
                        consumed[1] = dy;
                    }
                } else {
                    scrollTo(0, 0);
                    consumed[1] = 0;
                }
            }
            return;
        }
    }

    //while child view move finish
    //dyUnconsumed < 0 move down
    //dyUnconsumed > 0 move up
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyUnconsumed != 0) {
            totalY += dyUnconsumed;
            scrollTo(0, totalY);
        }
    }

    public void onStopNestedScroll(View child) {
        helper.onStopNestedScroll(child);
        if (onTouchUpListener != null) {
            isfling = false;
            if (this.getTotal() >= headerHeight) {
                this.setScrollTo(this.getTotal(), headerHeight);
                if (!this.isScrollRefresh()) {
                    this.setIsScrollRefresh(true);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onTouchUpListener.OnRefreshing();
                        }
                    }, ANIM_INTERVAL);
                }
            } else if (-this.getTotal() >= footerHeight) {
                this.setScrollTo(this.getTotal(), -footerHeight);
                if (!this.isScrollLoad()) {
                    this.setIsScrollLoad(true);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onTouchUpListener.OnLoading();
                        }
                    }, ANIM_INTERVAL);
                }
            } else {
                this.setScrollTo(0, 0);
            }
        }
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return isfling;
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return isfling;
    }

    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }

    private void smoothScrollTo(float fromY, float toY) {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{fromY, toY});
        if (fromY == toY) {
            animator.setDuration(0);
        } else {
            animator.setDuration(ANIM_INTERVAL);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int to = (int) (-((Float) animation.getAnimatedValue()).floatValue());
                scrollTo(0, to);
                totalY = to;
            }
        });
        animator.start();
    }

    private class MyRecyclerView extends RecyclerView {
        private StaggeredGridLayoutManager staggeredGridLayoutManager = null;
        private LinearLayoutManager linearLayoutManager = null;
        private GridLayoutManager gridLayoutManager = null;
        private boolean isScrollLoad = false;
        private boolean isScrollRefresh = false;

        public MyRecyclerView(Context context) {
            super(context);
            setVerticalFadingEdgeEnabled(false);
            setHorizontalFadingEdgeEnabled(false);
            setVerticalScrollBarEnabled(false);
            setHorizontalScrollBarEnabled(false);
            setOverScrollMode(OVER_SCROLL_NEVER);
            setItemAnimator(new DefaultItemAnimator());
        }

        private void setMyLayoutManager(LayoutManager layoutManager) {
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            } else if (layoutManager instanceof GridLayoutManager) {
                gridLayoutManager = (GridLayoutManager) layoutManager;
            } else if (layoutManager instanceof LinearLayoutManager) {
                linearLayoutManager = (LinearLayoutManager) layoutManager;
            }
            setLayoutManager(layoutManager);
            if (!isVertical()) {
                throw new NullPointerException("vertical!");
            }
        }

        //orientation
        // 0 menas down
        // 1 means up
        private boolean isOrientation(int orientation) {
            if (orientation == 0)
                return isCanPullDown();
            else if (orientation == 1)
                return isCanPullUp();
            return false;
        }

        private boolean isCanPullDown() {
            return !canScrollVertically(-1);
        }

        private boolean isCanPullUp() {
            return !canScrollVertically(1);
        }

        private boolean isVertical() {
            if (staggeredGridLayoutManager != null)
                return staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL;
            else if (linearLayoutManager != null)
                return linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL;
            else if (gridLayoutManager != null)
                return gridLayoutManager.getOrientation() == GridLayoutManager.VERTICAL;
            return false;
        }
    }

    public void setHeaderState(int state) {
        switch (state) {
            case HEADER_STATE_INIT:
                stopDrawableAnimation();
                mHeaderTextView.setText(context.getString(R.string.pull_to_refresh));
                break;
            case HEADER_STATE_RELEASE_REFRESH:
                mHeaderTextView.setText(context.getString(R.string.release_to_refresh));
                break;
            case HEADER_STATE_REFRESHING:
                startDrawableAnimation();
                mHeaderTextView.setText(context.getString(R.string.refreshing));
                break;
            case HEADER_STATE_COMPLETE:
                stopDrawableAnimation();
                mHeaderTextView.setText(context.getString(R.string.refresh_succeed));
                this.setIsScrollRefresh(false);
                setScrollTo(getTotal(), 0);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setHeaderState(HEADER_STATE_INIT);
                    }
                }, ANIM_INTERVAL);
                break;
        }
    }

    public void setFooterState(int state) {
        switch (state) {
            case FOOTER_STATE_INIT:
                mFooterTextView.setText(context.getString(R.string.pullup_to_load));
                mFooterImageView.clearAnimation();
                mFooterProgressBar.setVisibility(View.GONE);
                mFooterImageView.setVisibility(View.VISIBLE);
                isFirst = true;
                break;
            case FOOTER_STATE_LOADING:
                mFooterTextView.setText(context.getString(R.string.loading));
                mFooterImageView.clearAnimation();
                mFooterImageView.setVisibility(View.INVISIBLE);
                mFooterProgressBar.setVisibility(View.VISIBLE);
                break;
            case FOOTER_STATE_RELEASE_LOAD:
                mFooterTextView.setText(context.getString(R.string.release_to_load));
                if (isFirst) {//不控制，动画一直播
                    isFirst = false;
                    mFooterImageView.startAnimation(rotateAnimation);
                }
                break;
            case FOOTER_STATE_COMPLETE:
                mFooterTextView.setText(context.getString(R.string.load_succeed));
                this.setIsScrollLoad(false);
                setScrollTo(getTotal(), 0);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setFooterState(FOOTER_STATE_INIT);
                    }
                }, ANIM_INTERVAL);
                break;
            case FOOTER_STATE_FAIL:
                mFooterTextView.setText(context.getString(R.string.load_fail));
                this.setIsScrollLoad(false);
                setScrollTo(getTotal(), 0);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setFooterState(FOOTER_STATE_INIT);
                    }
                }, ANIM_INTERVAL);
                break;
        }
    }


    private void startDrawableAnimation() {
        if (mHeaderImg != null) {
            AnimationDrawable animationDrawable = (AnimationDrawable) mHeaderImg.getBackground();
            animationDrawable.start();
        }
    }

    private void stopDrawableAnimation() {
        if (mHeaderImg != null) {
            AnimationDrawable animationDrawable = (AnimationDrawable) mHeaderImg.getBackground();
            if (animationDrawable != null) {
                animationDrawable.stop();
                animationDrawable.selectDrawable(0);//最后停留在第一帧
            }
//            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//                animationDrawable.setVisible(true, true);
//            }else {
//                animationDrawable.setVisible(false, true);
//            }
        }
    }

}
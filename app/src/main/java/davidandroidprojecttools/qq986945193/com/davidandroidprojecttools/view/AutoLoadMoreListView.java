package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * ----------------------------------------------------------------------------
 * 功能描述：下拉自动加载更多
 * ----------------------------------------------------------------------------
 */

public class AutoLoadMoreListView extends ListView {
	protected static final String TAG = "LoadMoreListView";
	public View mFooterView;
	public TextView mText;
	public ProgressBar mBar;
	private LinearLayout ll_footer;

	private OnScrollListener mOnScrollListener;
	private OnLoadMoreListener mOnLoadMoreListener;

	/**
	 * If is loading now.
	 */
	private boolean mIsLoading;

	private int mCurrentScrollState;

	public AutoLoadMoreListView(Context context, AttributeSet attrs,
								int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public AutoLoadMoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AutoLoadMoreListView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mFooterView = View.inflate(context, R.layout.auto_load_more_footer,
				null);
		mBar = (ProgressBar) mFooterView.findViewById(R.id.load_more_pb_auto);
		ll_footer = (LinearLayout) mFooterView.findViewById(R.id.ll_footer);
		mText = (TextView) mFooterView.findViewById(R.id.load_more_tv_auto);

		addFooterView(mFooterView);
		hideFooterView();
		/*
		 * Must use super.setOnScrollListener() here to avoid override when call
		 * this view's setOnScrollListener method
		 */
		super.setOnScrollListener(superOnScrollListener);
	}



	public void showLoadComplete(){
		mBar.setVisibility(View.GONE);
		mText.setText("~~~已没有更多数据啦~~~");
		ll_footer.setVisibility(GONE);
	}


	/**
	 * Hide the load more view(footer view)
	 */
	private void hideFooterView() {
		mFooterView.setVisibility(View.GONE);
	}




	/**
	 * Show load more view
	 */
	private void showFooterView() {
		mFooterView.setVisibility(View.VISIBLE);
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mOnScrollListener = l;
	}

	/**
	 * Set load more listener, usually you should get more data here.
	 * 
	 * @param listener
	 *            OnLoadMoreListener
	 * @see OnLoadMoreListener
	 */
	public void setOnLoadMoreListener(OnLoadMoreListener listener) {
		mOnLoadMoreListener = listener;
	}

	/**
	 * When complete load more data, you must use this method to hide the footer
	 * view, if not the footer view will be shown all the time.
	 */
	public void onLoadMoreComplete() {
		mIsLoading = false;
		hideFooterView();
		//mFooterView.setVisibility(View.VISIBLE);

	}

	private OnScrollListener superOnScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			mCurrentScrollState = scrollState;
			// Avoid override when use setOnScrollListener
			if (mOnScrollListener != null) {
				mOnScrollListener.onScrollStateChanged(view, scrollState);
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
							 int visibleItemCount, int totalItemCount) {
			if (mOnScrollListener != null) {
				mOnScrollListener.onScroll(view, firstVisibleItem,
						visibleItemCount, totalItemCount);
			}
			// The count of footer view will be add to visibleItemCount also are
			// added to totalItemCount
			if (visibleItemCount == totalItemCount) {
				// If all the item can not fill screen, we should make the
				// footer view invisible.
				hideFooterView();
			} else if (!mIsLoading
					&& (firstVisibleItem + visibleItemCount >= totalItemCount)
					&& mCurrentScrollState != SCROLL_STATE_IDLE) {
				showFooterView();
				mIsLoading = true;
				if (mOnLoadMoreListener != null) {
					mOnLoadMoreListener.onLoadMore();
				}
			}
		}
	};

	/**
	 * Interface for load more
	 */
	public interface OnLoadMoreListener {
		/**
		 * Load more data.
		 */
		void onLoadMore();
	}
}

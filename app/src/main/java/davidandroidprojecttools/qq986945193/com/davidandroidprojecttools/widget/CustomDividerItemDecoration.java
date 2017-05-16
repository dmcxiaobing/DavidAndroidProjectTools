package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by david
 */
public class CustomDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int mDividerHeight = 2;
    private int mOrientation;
    private Paint paint;
    private static final int[] ATTRS = new int[] {android.R.attr.listDivider};

    public CustomDividerItemDecoration(Context context, int orientation) {
        if (orientation != LinearLayoutManager.VERTICAL &&
                orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("RecyclerView设置的LayoutManager不正确");
        }
        mOrientation = orientation;
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    public CustomDividerItemDecoration(Context context, String color) {
        this(context, LinearLayoutManager.VERTICAL, 1, Color.parseColor(color));
    }

    public CustomDividerItemDecoration(Context context, int orientation, int resId) {
        this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, resId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    public CustomDividerItemDecoration(Context context, int orientation, int dividerHeight, int dividerColor) {
       this(context, orientation);
        mDividerHeight = dividerHeight;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int childSize = parent.getAdapter().getItemCount();
        int childPostion = parent.getChildAdapterPosition(view);
        if(childPostion == childSize - 1) {// || view instance ArrowRefreshHeader
            outRect.set(0, 0, 0, 0);
        }else {
            outRect.set(0, 0, 0, mDividerHeight);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        }else {
            drawVertical(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for(int i=0;i<childSize;i++) {
            if(i == childSize -1) {
                continue;
            }
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if(mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (paint != null) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getAdapter().getItemCount();
        for(int i=0;i<childSize;i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;
            if(mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (paint != null) {
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }
}

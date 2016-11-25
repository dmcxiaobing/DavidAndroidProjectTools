package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.AnimationUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ScreenUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ViewUtils;

/**
 * 自定义分享界面的UI功能实现与详解
 */
public class ShareUiActivity extends AppCompatActivity {

    //用于标记页面顶端位置
    private View topView;

    private PopupWindow popupWindow;
    private int line1DeltaY, line2DeltaY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_ui);
        topView = findViewById(R.id.main_top);
    }

    //仿易信更多弹出框
    private void showPopup() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.yixin_pop_layout, null);
            //点击空白区域关闭
            View blankView = contentView.findViewById(R.id.yixin_more_blank);
            View blankView2 = contentView.findViewById(R.id.yixin_more_blank2);
            initItems(contentView);

            //测量高度
            int line2Height = ViewUtils.getViewMeasuredHeight(itemViews[0]);
            line1DeltaY = -getActionBarHeight() - 40;
            line2DeltaY = line1DeltaY - line2Height;

            blankView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissPopup();
                }
            });
            blankView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissPopup();
                }
            });

            popupWindow = new PopupWindow(contentView, ScreenUtils.getScreenW(this), ScreenUtils.getScreenH(this));
            //随便设置一个drawable作为背景
            popupWindow.setBackgroundDrawable(new ColorDrawable());
        }

        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(topView, 0, 0);

            for (int i = 0; i < itemViews.length; i++) {
                if (i < 3) {
                    //第一行
                    itemViews[i].startAnimation(AnimationUtils.createPopupAnimIn(this, line1DeltaY));
                } else {
                    //第二行
                    itemViews[i].startAnimation(AnimationUtils.createPopupAnimIn(this, line2DeltaY));
                }
            }
            popupWindow.getContentView().startAnimation(AnimationUtils.createPopupBgFadeInAnim());
        }
    }

    private void dismissPopup() {
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }

        ViewGroup contentView = (ViewGroup) popupWindow.getContentView();
        contentView.startAnimation(AnimationUtils.createPopupBgFadeOutAnim(AnimationUtils.TIME_OUT));

        for (int i = 0; i < itemViews.length; i++) {
            if (i < 3) {
                //第一行
                itemViews[i].startAnimation(AnimationUtils.createPopupAnimOut(this, line1DeltaY));
            } else {
                //第二行
                itemViews[i].startAnimation(AnimationUtils.createPopupAnimOut(this, line2DeltaY));
            }
        }

        //动画结束时隐藏popupWindow
        contentView.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, AnimationUtils.TIME_OUT + 10);

    }

    private View[] itemViews;

    //初始化popupWindow上的按钮
    private void initItems(View parent) {
        int[] viewIds = new int[]{R.id.yixin_more_item1, R.id.yixin_more_item2, R.id.yixin_more_item3,
                R.id.yixin_more_item4, R.id.yixin_more_item5, R.id.yixin_more_item6};

        itemViews = new View[viewIds.length];
        //算出宽度
        int itemWidth = ScreenUtils.getScreenW(this) / 4;

        OnClickImpl l = new OnClickImpl();
        for (int i = 0; i < viewIds.length; i++) {
            int id = viewIds[i];
            itemViews[i] = parent.findViewById(id);
            GridLayout.LayoutParams p = (GridLayout.LayoutParams) itemViews[i].getLayoutParams();
            p.width = itemWidth;
            itemViews[i].setLayoutParams(p);

            itemViews[i].setOnClickListener(l);
        }
    }

    private class OnClickImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final int viewId = v.getId();
            //背景动画
            popupWindow.getContentView().startAnimation(AnimationUtils.createPopupBgFadeOutAnim(AnimationUtils.TIME_OUT_CLICK));
            //动画结束时隐藏popupWindow
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    popupWindow.dismiss();

                    //动画结束时响应点击事件
                    handleEvent(viewId);
                }
            }, AnimationUtils.TIME_OUT_CLICK + 10);

            //按钮动画
            for (View item : itemViews) {
                if (item.getId() == v.getId()) {
                    //点击的按钮，放大
                    item.startAnimation(AnimationUtils.createPopupItemBiggerAnim(ShareUiActivity.this));
                } else {
                    //其它按钮，缩小
                    item.startAnimation(AnimationUtils.createPopupItemSmallerAnim(ShareUiActivity.this));
                }
            }
        }
    }

    //popupWindow上按钮的点击事件
    private void handleEvent(int viewId) {
        switch (viewId) {
            case R.id.yixin_more_item1:
                Toast.makeText(ShareUiActivity.this, "图片1", Toast.LENGTH_SHORT).show();
                break;
        }
//        Toast.makeText(this, "点击了按钮：" + viewId, Toast.LENGTH_SHORT).show();
    }

    private int getActionBarHeight() {
        return getSupportActionBar().getHeight();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_more) {
            if (popupWindow == null || !popupWindow.isShowing()) {
                showPopup();
            } else {
                dismissPopup();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //点击返回键时，如果popupWindow是显示状态，则关闭它
    @Override
    public void onBackPressed() {
        if (popupWindow != null && popupWindow.isShowing()) {
            dismissPopup();
            return;
        }

        super.onBackPressed();
    }

    /**
     * onResume与onPause()封装提取原因友盟统计
     */
    protected void onResume() { // Umeng 对处理事件的统计
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}

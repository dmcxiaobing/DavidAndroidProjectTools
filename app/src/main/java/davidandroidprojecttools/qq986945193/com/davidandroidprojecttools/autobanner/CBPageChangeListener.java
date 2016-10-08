package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.autobanner;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;
/*
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 翻页指示器适配器
 */
public class CBPageChangeListener implements ViewPager.OnPageChangeListener {
    private ArrayList<ImageView> pointViews;
    private int[] page_indicatorId;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    public CBPageChangeListener(ArrayList<ImageView> pointViews,int page_indicatorId[]){
        this.pointViews=pointViews;
        this.page_indicatorId = page_indicatorId;
    }
    @Override
    public void onPageScrollStateChanged(int state) {
        if(onPageChangeListener != null)onPageChangeListener.onPageScrollStateChanged(state);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(onPageChangeListener != null)onPageChangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int index) {
        for (int i = 0; i < pointViews.size(); i++) {
            pointViews.get(index).setImageResource(page_indicatorId[1]);
            if (index != i) {
                pointViews.get(i).setImageResource(page_indicatorId[0]);
            }
        }
        if(onPageChangeListener != null)onPageChangeListener.onPageSelected(index);

    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }
}

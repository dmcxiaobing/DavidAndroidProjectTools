package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

public class AnimationUtils {
    /**
     * 进入动画的时间
     */
    public static final int TIME_IN = 300;
    /**
     * 进入动画之后的反弹动画时间
     */
    public static final int TIME_IN_BACK = 100;
    /**
     * 退出动画的时间
     */
    public static final int TIME_OUT = 300;

    /**
     * 点击PopupWindow上菜单后退出动画的时间
     */
    public static final int TIME_OUT_CLICK = 500;

    /**
     * PopupWindow上菜单进入动画
     */
    public static Animation createPopupAnimIn(Context context, int fromYDelta) {
        AnimationSet animationSet = new AnimationSet(context, null);
//        animationSet.setInterpolator(new BounceInterpolator());  //结束时弹跳
        animationSet.setFillAfter(true);

        //移动
        TranslateAnimation translateAnim = new TranslateAnimation(0, 0, fromYDelta, 20);
        translateAnim.setDuration(TIME_IN);
        animationSet.addAnimation(translateAnim);

        //回弹效果
        TranslateAnimation translateAnim2 = new TranslateAnimation(0, 0, 0, -20);
        translateAnim2.setStartOffset(TIME_IN);
        translateAnim2.setDuration(TIME_IN_BACK);
        animationSet.addAnimation(translateAnim2);

        return animationSet;
    }

    /**
     * PopupWindow上菜单离开动画
     */
    public static Animation createPopupAnimOut(Context context, int toYDelta) {
        AnimationSet animationSet = new AnimationSet(context, null);
        animationSet.setFillAfter(true);

        TranslateAnimation translateAnim = new TranslateAnimation(0, 0, 0, toYDelta);
        translateAnim.setDuration(TIME_OUT);
        animationSet.addAnimation(translateAnim);

        return animationSet;
    }

    /**
     * PopupWindow背景进入动画（透明度渐变）
     */
    public static Animation createPopupBgFadeInAnim() {
        AlphaAnimation anim = new AlphaAnimation(0, 1.0f);
        anim.setDuration(TIME_IN);
        anim.setFillAfter(true);
        return anim;
    }

    /**
     * PopupWindow背景离开动画（透明度渐变）
     */
    public static Animation createPopupBgFadeOutAnim(int duration) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0);
        anim.setDuration(duration);
        anim.setFillAfter(true);
        return anim;
    }

    /**
     * PopupWindow按钮点击动画
     */
    public static Animation createPopupItemBiggerAnim(Context context) {
        AnimationSet animationSet = new AnimationSet(context, null);
        animationSet.setFillAfter(true);

        //放大(设置缩放的中心点为自己的中心)
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(TIME_OUT_CLICK);
        animationSet.addAnimation(scaleAnim);

        //渐变
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0);
        alphaAnim.setInterpolator(new AccelerateInterpolator());
        alphaAnim.setDuration(TIME_OUT_CLICK);
        animationSet.addAnimation(alphaAnim);

        return animationSet;
    }

    /**
     * PopupWindow按钮点击时其它按钮的动画
     */
    public static Animation createPopupItemSmallerAnim(Context context) {
        //放大(设置缩放的中心点为自己的中心)
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0, 1.0f, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(TIME_OUT_CLICK);
        scaleAnim.setFillAfter(true);

        return scaleAnim;
    }
}

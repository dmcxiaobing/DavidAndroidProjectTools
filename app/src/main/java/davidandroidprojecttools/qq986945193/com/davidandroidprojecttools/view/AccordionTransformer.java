package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.view.View;

import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.nineoldandroids.view.ViewHelper;

public class AccordionTransformer extends BaseTransformer {
    @Override
    protected void onTransform(View view, float position) {
        ViewHelper.setPivotX(view, position < 0 ? 0 : view.getWidth());
        ViewHelper.setScaleX(view, position < 0 ? 1f + position : 1f - position);
    }
}

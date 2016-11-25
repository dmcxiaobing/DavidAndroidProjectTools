package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

import android.app.Activity;
import android.os.Bundle;

import com.jauker.widget.BadgeView;
import com.umeng.analytics.MobclickAgent;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.RoundImageView;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.WaterDropView;

/**
 * 仿微信提示消息个数两种实现方式
 */
public class BadgeViewWaterDropActivity extends Activity {

    private RoundImageView iv_badge_view_water_drop_black;
    private RoundImageView iv_badge_view_water_drop_music;
    private WaterDropView mWaterDropView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badgeview_waterdrop);
        iv_badge_view_water_drop_black = (RoundImageView) findViewById(R.id.iv_badge_view_water_drop_black);
        iv_badge_view_water_drop_music = (RoundImageView) findViewById(R.id.iv_badge_view_water_drop_music);
        mWaterDropView = (WaterDropView) findViewById(R.id.mWaterDropView);

        oneMethod();

        twoMethod();
    }


    /**
     * 第一种方法 利用一个开源的badgeView库
     */
    private void oneMethod() {
        BadgeView mBadgeView = new BadgeView(this);
        mBadgeView.setTargetView(iv_badge_view_water_drop_black);
        mBadgeView.setBadgeCount(1);

    }

    /**
     * 第二种方法，利用一个自定义View
     */
    private void twoMethod() {
        mWaterDropView.setText("2");
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

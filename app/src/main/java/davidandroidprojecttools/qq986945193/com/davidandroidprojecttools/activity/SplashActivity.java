package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ActivityManagerUtils;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：splash闪屏页的四种实现方式
 */
public class SplashActivity extends Activity {
    private LinearLayout ll_splash;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startMainActivity();
                    break;
                case 2:
                    startMainActivity();
                    break;
            }
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ActivityManagerUtils.getInstance().addActivity(SplashActivity.this);


        ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
        /**
         * 第一种方法，利用handler自带的sendEmptyMessageDelayed()方法。
         */
        mHandler.sendEmptyMessageDelayed(1, 2000);

        /**
         * 第二种方法，其实实现原理和第一种是一样的，
         */
//
//        Message message = new Message();
//        message.what = 2;
//        mHandler.sendMessageDelayed(message, 2000);

        /**
         * 第三种方法，利用动画实现
         */
//        startAdimThree();
        /**
         * 第四种方法，利用动画实现
         */
//        StartAniFour();

    }

    private void StartAniFour() {
        AlphaAnimation start = new AlphaAnimation(0.0f, 1.0f);
        start.setDuration(1000);
        // findViewById(R.id.splash).startAnimation(start);
        start.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ll_splash.startAnimation(start);
    }


    /**
     * 开启动画
     */
    private void startAdimThree() {
        // 动画集合
        AnimationSet set = new AnimationSet(false);
        // 旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(180, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setDuration(2000);// 设置动画时间
        rotateAnimation.setFillAfter(true);// 保持动画状态

        // 缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnimation.setDuration(2000);// 设置动画时间
        scaleAnimation.setFillAfter(true);// 保持动画状态

        // 渐变动画

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);// 保持动画状态

        // 添加动画
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        /*
         * 设置动画的监听事件，当动画运行完成后，启动新的activity
		 */
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                startMainActivity();
            }
        });

        ll_splash.startAnimation(set);

    }


    /**
     * 跳转到主界面
     */
    private void startMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.removeMessages(1);
//        mHandler.removeMessages(2);
    }
}
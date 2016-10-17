package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.CountDownView;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.RoundProgressBar;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class SplashAdActivity extends BaseActivity {
    private RoundProgressBar mRoundProgressBar;
    private CountDownView count_down_view;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash_ad);
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
        mRoundProgressBar.setCountDownTimerListener(new RoundProgressBar.CountDownTimerListener() {
            @Override
            public void onStartCount() {
                LogUtil.E("dadadadddad");
            }

            @Override
            public void onFinishCount() {
                LogUtil.E("dfdsfffffffffff");

            }
        });
        mRoundProgressBar.start();
        mRoundProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.E("计时结束");
                Toast.makeText(getApplicationContext(), "计时结束", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void initData() {
        count_down_view = (CountDownView) findViewById(R.id.countDownView);
        count_down_view.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {
//                Toast.makeText(getApplicationContext(), "开始计时", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinishCount() {
//                Toast.makeText(getApplicationContext(), "计时结束", Toast.LENGTH_SHORT).show();
            }
        });
        count_down_view.start();


    }
}

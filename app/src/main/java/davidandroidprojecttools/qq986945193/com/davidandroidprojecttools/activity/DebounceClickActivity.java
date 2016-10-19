package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class DebounceClickActivity extends BaseActivity {
    private TextView tv_regiter_get_code;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_debounce_click);
        tv_regiter_get_code = (TextView) findViewById(R.id.tv_regiter_get_code);
    }

    @Override
    protected void initData() {
        tv_regiter_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
    }

    /**
     * 获取验证码后 六十秒重新获取
     */
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

            tv_regiter_get_code.setText((millisUntilFinished / 1000) + "秒");
            tv_regiter_get_code.setEnabled(false);//防止重复点击
        }

        @Override
        public void onFinish() {
            tv_regiter_get_code.setEnabled(true);
            tv_regiter_get_code.setText("获取验证码");
        }
    };
}

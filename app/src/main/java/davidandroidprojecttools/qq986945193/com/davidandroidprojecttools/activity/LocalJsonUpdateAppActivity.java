package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;

/**
 * 调用本地的json数据进行更新apk
 */
public class LocalJsonUpdateAppActivity extends BaseActivity{
    private Button btn_check_update_apk;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_local_json_update_app);
    }

    @Override
    protected void initData() {
        btn_check_update_apk = (Button) findViewById(R.id.btn_check_update_apk);

        btn_check_update_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void setTitle() {
        super.setTitle();
        tvTitle.setText(Constants.BASE_ACTIVITY_TITLE);

    }
}

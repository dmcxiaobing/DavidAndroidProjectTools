package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class SelectorPickerActivity extends BaseActivity {
    private Button btnTimeSelector;
    private Button btnPickerSelector;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_selector_picker);
        btnTimeSelector = (Button) findViewById(R.id.btn_time_selector);
        btnPickerSelector = (Button) findViewById(R.id.btn_picker_selector);
    }

    @Override
    protected void initData() {
        btnTimeSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TimeSelectorActivity.class);
            }
        });

        btnPickerSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PickerActivity.class);
            }
        });
    }
}

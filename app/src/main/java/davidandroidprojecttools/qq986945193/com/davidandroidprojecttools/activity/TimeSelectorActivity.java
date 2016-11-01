package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class TimeSelectorActivity extends BaseActivity {
    @BindView(R.id.btn_dateChoose)
    Button btn_dateChoose;
    @BindView(R.id.tv_dateDisplay)
    TextView tv_dateDisplay;

    int mYear, mMonth, mDay;
    final int DATE_DIALOG = 1;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_time_selector);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        btn_dateChoose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        tv_dateDisplay.setText(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日"));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }


    };

}

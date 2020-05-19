package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

/**
 * 获取走动的android时间
 */
public class TimeActivity extends BaseActivity implements View.OnClickListener{

    private TextView data_tv;
    private TextView data_tv2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    data_tv.setText(getData());
                    data_tv2.setText(getData2());
                    break;

                default:
                    break;
            }
        }

    };


    @Override
    protected void initView() {
        setContentView(R.layout.activity_time);

        data_tv = (TextView) findViewById(R.id.data_tv);
        data_tv2 = (TextView) findViewById(R.id.data_tv2);

        new Thread(){

            @Override
            public void run() {
                super.run();
                while(true){
                    handler.sendEmptyMessage(1);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }.start();

    }

    @Override
    protected void initData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_tv:
                break;

            default:
                break;
        }
    }

    public String getData(){
        long time=System.currentTimeMillis();
        final Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int mHour=mCalendar.get(Calendar.HOUR);
        int mMinuts=mCalendar.get(Calendar.MINUTE);
        String mMinString = null;
        if (mMinuts<10){
            mMinString = "0"+mMinuts;
        }else{
            mMinString = ""+mMinuts;
        }
        return mHour+":"+mMinString;
    }

    public String getData2(){
        Time time = new Time("GMT+8");
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        int minute = time.minute;
        int hour = time.hour+8;
        int sec = time.second;
        String mMinString = null;
        String secString = null;
        if (minute<10){
            mMinString = "0"+minute;
        }else{
            mMinString = ""+minute;
        }

        if (sec<10){
            secString = "0"+sec;
        }else{
            secString = ""+sec;
        }

        String str1 = "��ǰʱ��Ϊ��" + year +
                "�� " + month +
                "�� " + day +
                "�� " + hour +
                ":" + mMinString +
                ": " + secString;

        return str1;
    }

}

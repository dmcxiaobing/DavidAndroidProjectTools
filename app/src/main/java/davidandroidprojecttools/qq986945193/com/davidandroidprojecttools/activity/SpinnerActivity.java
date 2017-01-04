package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * spinner(下拉框)的知识点详解
 */
public class SpinnerActivity extends BaseActivity {
    private Spinner spinner_one;
    private Spinner spinner_two;
    private ArrayAdapter ArrayAdapter;
    private ArrayAdapter adapter2;
    private String[] titles = {"程序员小冰","JAVA","ANDROID","linux"};
    @Override
    protected void initView() {
        setContentView(R.layout.activity_spinner);
        spinner_one = (Spinner) findViewById(R.id.spinner_one);
        spinner_two = (Spinner) findViewById(R.id.spinner_two);

    }

    @Override
    protected void initData() {
        //将可选内容与ArrayAdapter连接，
        ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, titles);
        //设置下拉列表的风格
        ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到m_Spinner中
        spinner_one.setAdapter(ArrayAdapter);


        //将可选内容与ArrayAdapter连接起来
        adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_two, android.R.layout.simple_spinner_item);

        //设置下拉列表的风格
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter2 添加到spinner中
        spinner_two.setAdapter(adapter2);

        //添加事件Spinner事件监听
        spinner_two.setOnItemSelectedListener(new SpinnerXMLSelectedListener());

        //设置默认值
        spinner_two.setVisibility(View.VISIBLE);

    }

    //使用XML形式操作
    class SpinnerXMLSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            ToastUtils.showShort(mContext,"hello"+adapter2.getItem(arg2));

        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }

    @Override
    protected void setOnclickListener() {
        //添加Spinner事件监听
        spinner_one.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String title = titles[position];
                //设置显示当前选择的项
                parent.setVisibility(View.VISIBLE);
//                ToastUtils.showShort(mContext,title);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }
}

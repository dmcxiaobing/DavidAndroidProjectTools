package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.ProvinceBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.ProvinceModelBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pickerView.OptionsPickerView;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pickerView.TimePickerView;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.XmlParserHandlerUtils;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class PickerActivity extends BaseActivity {
    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    private TextView tvTime, tvOptions, tv_pro;
    TimePickerView pvTime;
    OptionsPickerView pvOptions, proOptions;
    View vMasker;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_picker);
        vMasker = findViewById(R.id.vMasker);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvOptions = (TextView) findViewById(R.id.tvOptions);
        tv_pro = (TextView) findViewById(R.id.tv_pro);
        tv_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proOptions.show();

            }
        });
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });


        initDatas();
        //选项选择器
        pvOptions = new OptionsPickerView(this);

        //选项1
        options1Items.add(new ProvinceBean("广东"));
        options1Items.add(new ProvinceBean("湖南"));
        options1Items.add(new ProvinceBean("广西"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<String>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("阳江");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<String>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        ArrayList<String> options2Items_03 = new ArrayList<String>();
        options2Items_03.add("桂林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        //选项3
        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();
        ArrayList<String> options3Items_01_01 = new ArrayList<String>();
        options3Items_01_01.add("白云");
        options3Items_01_01.add("天河");
        options3Items_01_01.add("海珠");
        options3Items_01_01.add("越秀");
        options3Items_01.add(options3Items_01_01);
        ArrayList<String> options3Items_01_02 = new ArrayList<String>();
        options3Items_01_02.add("南海");
        options3Items_01_02.add("高明");
        options3Items_01_02.add("顺德");
        options3Items_01_02.add("禅城");
        options3Items_01.add(options3Items_01_02);
        ArrayList<String> options3Items_01_03 = new ArrayList<String>();
        options3Items_01_03.add("其他");
        options3Items_01_03.add("常平");
        options3Items_01_03.add("虎门");
        options3Items_01.add(options3Items_01_03);
        ArrayList<String> options3Items_01_04 = new ArrayList<String>();
        options3Items_01_04.add("其他1");
        options3Items_01_04.add("其他2");
        options3Items_01_04.add("其他3");
        options3Items_01.add(options3Items_01_04);
        ArrayList<String> options3Items_01_05 = new ArrayList<String>();
        options3Items_01_05.add("其他1");
        options3Items_01_05.add("其他2");
        options3Items_01_05.add("其他3");
        options3Items_01.add(options3Items_01_05);

        ArrayList<String> options3Items_02_01 = new ArrayList<String>();
        options3Items_02_01.add("长沙长沙长沙长沙长沙长沙长沙长沙长沙1111111111");
        options3Items_02_01.add("长沙2");
        options3Items_02_01.add("长沙3");
        options3Items_02_01.add("长沙4");
        options3Items_02_01.add("长沙5");
        options3Items_02_01.add("长沙6");
        options3Items_02_01.add("长沙7");
        options3Items_02_01.add("长沙8");
        options3Items_02.add(options3Items_02_01);
        ArrayList<String> options3Items_02_02 = new ArrayList<String>();
        options3Items_02_02.add("岳1");
        options3Items_02_02.add("岳2");
        options3Items_02_02.add("岳3");
        options3Items_02_02.add("岳4");
        options3Items_02_02.add("岳5");
        options3Items_02_02.add("岳6");
        options3Items_02_02.add("岳7");
        options3Items_02_02.add("岳8");
        options3Items_02_02.add("岳9");
        options3Items_02.add(options3Items_02_02);
        ArrayList<String> options3Items_03_01 = new ArrayList<String>();
        options3Items_03_01.add("好山水");
        options3Items_03.add(options3Items_03_01);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                tvOptions.setText(tx);
//                vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }


    @Override
    protected void initData() {

    }

    List<List<List<String>>> distrList = new ArrayList<>();
    List<List<String>> cityList = new ArrayList<>();
    List<String> proList = new ArrayList<>();

    private void initDatas() {
        proOptions = new OptionsPickerView(this);
        AssetManager asset = getAssets();
        InputStream input = null;
        List<ProvinceModelBean> provinceList = null;
        try {
            input = asset.open("province_data.xml");

            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandlerUtils handler = new XmlParserHandlerUtils();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (provinceList != null && provinceList.size() > 0) {
            for (ProvinceModelBean list : provinceList) {
                proList.add(list.getPickerViewText());
                cityList.add(list.getCityNameList());
                distrList.add(list.getDisNameList());
            }
        }
        proOptions.setPicker(proList, cityList, distrList, true);
        proOptions.setCyclic(false);

        proOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                tv_pro.setText(proList.get(options1) + cityList.get(options1).get(option2) + distrList.get(options1).get(option2).get(options3));
            }
        });

    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}

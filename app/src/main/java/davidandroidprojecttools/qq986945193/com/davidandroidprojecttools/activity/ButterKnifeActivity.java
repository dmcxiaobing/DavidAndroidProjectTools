package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * ButterKnife的使用详解。在baseFragment中也有butterKnife的使用
 */
public class ButterKnifeActivity extends BaseActivity {
    @BindView(R.id.btn_1)
    Button btn_1;
    @BindView(R.id.btn_2)
    Button btn_2;
    @BindView(R.id.btn_3)
    Button btn_3;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "btn1", Toast.LENGTH_SHORT);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "btn2", Toast.LENGTH_SHORT);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "btn3", Toast.LENGTH_SHORT);
            }
        });
    }


}

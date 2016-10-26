package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.CodeRandomImageUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.EditTextTextViewUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.RandomFourNumUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class CheckCodeActivity extends BaseActivity {
    private TextView tv_code_one;
    private TextView tv_code_two;
    private ImageView iv_code_three;
    private EditText et_num;
    private Button btn_check_one;
    private Button btn_check_two;
    private Button btn_check_three;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_check_code);
        tv_code_one = (TextView) findViewById(R.id.tv_code_one);
        tv_code_two = (TextView) findViewById(R.id.tv_code_two);
        iv_code_three = (ImageView) findViewById(R.id.iv_code_three);
        btn_check_one = (Button) findViewById(R.id.btn_check_one);
        btn_check_two = (Button) findViewById(R.id.btn_check_two);
        btn_check_three = (Button) findViewById(R.id.btn_check_three);
        iv_code_three.setImageBitmap(CodeRandomImageUtils.getInstance().getBitmap());
        et_num = (EditText) findViewById(R.id.et_num);
        tv_code_one.setText(RandomFourNumUtils.getRandomFourNum());
        tv_code_two.setText(RandomFourNumUtils.getRandomFourNumString());

    }

    @Override
    protected void initData() {
        btn_check_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextTextViewUtils.getEditTextIsEmpty(et_num)) {
                    ToastUtils.show(mContext, "输入不能为空", Toast.LENGTH_SHORT);
                    return;
                } else {
                    if (EditTextTextViewUtils.getEditTextStr(et_num).equalsIgnoreCase(EditTextTextViewUtils.getTextViewStr(tv_code_one))) {
                        ToastUtils.show(mContext, "验证码正确", Toast.LENGTH_LONG);
                    } else {
                        ToastUtils.show(mContext, "验证码不正确", Toast.LENGTH_LONG);

                    }
                }
            }
        });

        btn_check_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextTextViewUtils.getEditTextIsEmpty(et_num)) {
                    ToastUtils.show(mContext, "输入不能为空", Toast.LENGTH_SHORT);
                    return;
                } else {
                    if (EditTextTextViewUtils.getEditTextStr(et_num).equalsIgnoreCase(EditTextTextViewUtils.getTextViewStr(tv_code_two))) {
                        ToastUtils.show(mContext, "验证码正确", Toast.LENGTH_LONG);
                    } else {
                        ToastUtils.show(mContext, "验证码不正确", Toast.LENGTH_LONG);

                    }
                }
            }
        });

        btn_check_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextTextViewUtils.getEditTextIsEmpty(et_num)) {
                    ToastUtils.show(mContext, "输入不能为空", Toast.LENGTH_SHORT);
                    return;
                } else {
                    if (EditTextTextViewUtils.getEditTextStr(et_num).equalsIgnoreCase(CodeRandomImageUtils.getInstance().getCode())) {
                        ToastUtils.show(mContext, "验证码正确", Toast.LENGTH_LONG);
                    } else {
                        ToastUtils.show(mContext, "验证码不正确", Toast.LENGTH_LONG);

                    }
                }
            }
        });
    }
}

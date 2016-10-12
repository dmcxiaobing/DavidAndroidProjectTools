package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * 拨打电话号码的详解代码(比较简单)
 */
public class CallphoneActivity extends BaseActivity {
    private EditText etPhone;
    private Button btnPhone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_callphone);
        etPhone = (EditText) findViewById(R.id.et_phone_num);
        btnPhone = (Button) findViewById(R.id.btn_call_phone);

    }

    @Override
    protected void initData() {
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().trim() == null || etPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(mContext, "对不起，电话不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (etPhone.getText().toString().trim() != null && !(etPhone.getText().toString().trim().equals(""))) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                            + etPhone.getText().toString().trim()));
//                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        return;
//                    }
                    mContext.startActivity(intent);

                }
            }
        });
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.DensityUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.EditTextTextViewUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.SystemgcBitmapUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.zxing.activity.CaptureActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.zxing.encoding.EncodingUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

/**
 * 利用Zxing实现二维码的生成与扫描功能
 */
public class BarCodeActivity extends BaseActivity {
    private Button btn_scan_barcode;

    private TextView tv_scan_result;

    private Button btn_add_qrcode;

    private ImageView iv_qr_image;

    private Bitmap mBitmap;

    private EditText et_qr_string;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bar_code);
        btn_scan_barcode = (Button) findViewById(R.id.btn_scan_barcode);
        btn_add_qrcode = (Button) findViewById(R.id.btn_add_qrcode);
        tv_scan_result = (TextView) findViewById(R.id.tv_scan_result);
        iv_qr_image = (ImageView) findViewById(R.id.iv_qr_image);
        et_qr_string = (EditText) findViewById(R.id.et_qr_string);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setOnclickListener() {
        /**
         * 打开摄像头进行扫描二维码
         */
        btn_scan_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                // TODO Auto-generated method stub
                // 启动扫描二维码的activity
                Intent startScan = new Intent(BarCodeActivity.this,
                        CaptureActivity.class);
                // startActivity(startScan);需要返回值，所以暂时不用此方法
                startActivityForResult(startScan, 1);
            }
        });

        /**
         *点击生成二维码
         */
        btn_add_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = EditTextTextViewUtils.getEditTextStr(et_qr_string);
                if (string.equals("")) {
                    Toast.makeText(BarCodeActivity.this, "内容为空，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        //得到二维码图片并且展示
                        mBitmap = EncodingUtils.createQRCode(string, DensityUtils.dip2px(BarCodeActivity.this, 240),DensityUtils.dip2px(BarCodeActivity.this, 240),mBitmap);
//                        mBitmap = EncodingHandler.createQRCode(string, 400);
                        if (mBitmap != null) {

                            iv_qr_image.setImageBitmap(mBitmap);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            tv_scan_result.setText(scanResult);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SystemgcBitmapUtils.SystemGcBitmap(mBitmap);
    }
}

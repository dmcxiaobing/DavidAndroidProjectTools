package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * 进入界面弹出一张大图
 */
public class StartDialogImageActivity extends BaseActivity {
    private TextView tv_gravity;
    private Dialog dia;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start_dialog_image);
        tv_gravity = (TextView) findViewById(R.id.tv_gravity);
        tv_gravity.setText("进入界面弹出一张大图");

    }

    @Override
    protected void initData() {
        dia = new Dialog(mContext, R.style.edit_AlertDialog_style);
        dia.setContentView(R.layout.activity_start_dialog);

        ImageView imageView = (ImageView) dia.findViewById(R.id.start_img);
        imageView.setBackgroundResource(R.mipmap.iv_splash);
        dia.show();

        dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is
        Window w = dia.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        dia.onWindowAttributesChanged(lp);
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.DialogThridUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.WeiboDialogUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class LoadingActivity extends BaseActivity {
    private Dialog mDialog;
    private Dialog mWeiboDialog;
    private Button btn_show_weibo_loading;
    private Button btn_show_thrid_loading;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogThridUtils.closeDialog(mDialog);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_loading);
        btn_show_weibo_loading = (Button) findViewById(R.id.btn_show_weibo_loading);
        btn_show_thrid_loading = (Button) findViewById(R.id.btn_show_thrid_loading);
//        mWeiboDialog.show();


    }

    @Override
    protected void initData() {
        btn_show_weibo_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(mContext, Constants.LOADING_DATA);
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });

        btn_show_thrid_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = DialogThridUtils.showWaitDialog(mContext, Constants.LOADING_DATA, false, true);
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });
    }
}

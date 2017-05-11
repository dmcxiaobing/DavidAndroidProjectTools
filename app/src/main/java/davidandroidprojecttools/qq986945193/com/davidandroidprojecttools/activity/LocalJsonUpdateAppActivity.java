package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.nostra13.universalimageloader.utils.L;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.UpdateAppBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpBaseCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.OkHttpStopCallback;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.services.UpdateVersionDownloadService;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.AppUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import okhttp3.Response;

import static com.tencent.bugly.beta.Beta.appVersionCode;
import static davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls.CHECK_UPDATE_APP;

/**
 * 调用本地的json数据进行更新apk
 */
public class LocalJsonUpdateAppActivity extends BaseActivity {
    private Button btn_check_update_apk;

    //    CHECK_UPDATE_APP
    @Override
    protected void initView() {
        setContentView(R.layout.activity_local_json_update_app);
    }

    private String descripton = "";
    private String downloadurl = "";
    private int versionCode = 0;

    @Override
    protected void initData() {
        btn_check_update_apk = (Button) findViewById(R.id.btn_check_update_apk);

        btn_check_update_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okHttpUtils.get(CHECK_UPDATE_APP, null, new OkHttpStopCallback<UpdateAppBean>() {
                    @Override
                    public void onSuccess(Response response, UpdateAppBean mUpdateAppBean) {
                        if (mUpdateAppBean != null) {
                            if (mUpdateAppBean.getMsg()!=null){
                                descripton = mUpdateAppBean.getMsg().get(0).getDescription();
                                downloadurl = mUpdateAppBean.getMsg().get(0).getDownloadApkUrl();
                                LogUtil.e("codel:"+mUpdateAppBean.getMsg().get(0).getVersionCode());
                                LogUtil.e("descripton:"+mUpdateAppBean.getMsg().get(0).getVersionCode());
                                LogUtil.e("downloadurl:"+mUpdateAppBean.getMsg().get(0).getVersionCode());
                                versionCode = Integer.parseInt(mUpdateAppBean.getMsg().get(0).getVersionCode());
                                LogUtil.e(" AppUtils.getVersionCode(LocalJsonUpdateAppActivity.this)" + AppUtils.getVersionCode(LocalJsonUpdateAppActivity.this));
                                if (versionCode > AppUtils.getVersionCode(LocalJsonUpdateAppActivity.this)) {
                                    //如果服务端code大于本地，则说明有更新。
                                    showUpdateDailog();
                                }
                            }
                            }

                    }
                });
            }
        });

    }


    /**
     * 升级更新 弹出对话框
     */
    private void showUpdateDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //这里最新版本和描述均为后台接口URL传来的
        builder.setTitle("更新最新版本：" + AppUtils.getVersionName(this));
        builder.setMessage(descripton);
//		builder.setCancelable(false);//禁止用户点击返回按钮 （尽量不要用）
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(mContext, UpdateVersionDownloadService.class);
                intent.putExtra("url", downloadurl);
                mContext.startService(intent);
            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        //设置用户取消的监听，用户点击返回键是会触发
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();

            }
        });
        builder.show();
    }

    @Override
    protected void setTitle() {
        super.setTitle();
        tvTitle.setText(Constants.BASE_ACTIVITY_TITLE);

    }
}

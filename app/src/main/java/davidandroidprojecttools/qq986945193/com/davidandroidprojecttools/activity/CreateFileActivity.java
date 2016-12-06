package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.FileUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 在手机中创建文件以及写入,读取,删除等功能
 */
public class CreateFileActivity extends BaseActivity {
    @BindView(R.id.btn_createfile)
    Button btnCreatefile;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.btn_delete)
    Button btn_delete;
    @BindView(R.id.btn_read)
    Button btn_read;
    public String filenameTemp = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_create_file);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


    }

    @Override
    protected void initData() {
        /**
         * 创建文件夹以及文件
         */
        btnCreatefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        filenameTemp =
                                Environment.getExternalStorageDirectory().getPath()
                                        + "/david/create_file/";
                        // /mnt/sdcard/david/create_file/
                    } else {
                        filenameTemp =
                                mContext.getCacheDir() + "/david/create_file/";
                        // /data/data/<包名>/cache/david/create_file/
                    }
                    LogUtil.E("filename" + filenameTemp);
                    FileUtils.CreateText(filenameTemp, mContext, "utf-5文字编码。呵呵。我就要安卓。我是程序员小冰" + Constants.LOADING_DATA);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * 写入内容
         */
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.print(Constants.LOADING_DATA, filenameTemp, mContext);

            }
        });
        /**
         * 删除文件
         */
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.E("filenameTemp" + filenameTemp);
                FileUtils.deleteAllFiles(new File(filenameTemp));

            }
        });

        /**
         * 读取文件的所有内容
         */
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.readContent(mContext, filenameTemp + "create_file.txt");
            }
        });

    }

    @Override
    protected void setTitle() {
        super.setTitle();
        tvTitle.setText(Constants.BASE_ACTIVITY_TITLE);
    }

}

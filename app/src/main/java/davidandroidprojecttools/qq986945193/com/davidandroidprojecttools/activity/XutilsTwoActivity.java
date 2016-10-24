package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.internal.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.MyApplication;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * Xutils2使用详解
 * <p/>
 * <p/>
 * xutils 四大模块：网络请求，bitmapUtils显示图片，注解，操作数据库
 */
public class XutilsTwoActivity extends Activity {
    private Button btn_normal_get;
    private Button btn_normal_post;
    private HttpUtils mHttUtils;
    private Context mContext;
    /**
     * 注解的使用 最后记得调用inject（）方法
     */
    @ViewInject(R.id.btn_normal_bitmaputils)
    private Button btn_normal_bitmaputils;


    @ViewInject(R.id.iv_bitmaputils)
    private ImageView iv_bitmaputils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils_two);
        initView();
        com.lidroid.xutils.ViewUtils.inject(this);
        setOnclickListener();
    }

    /**
     * 设置监听事件
     */
    private void setOnclickListener() {
        /**
         * 无封装，普通GET网络请求
         */
        btn_normal_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "由于最新版本已经将HttpClient删除了，所以具体实现请看代码", Toast.LENGTH_LONG);
                /*mHttUtils.send(HttpRequest.HttpMethod.GET, Urls.XUTILS_TWO_GET, new RequestCallBack<String>() {
                    *//**
                 * 请求成功，打印出所得到的字符串
                 * @param responseInfo
                 *//*
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (responseInfo.result != null) {
//                            LogUtil.E(responseInfo.result);
                            ToastUtils.show(mContext, responseInfo.result, Toast.LENGTH_SHORT);

                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        LogUtil.E("网络请求失败");
                        ToastUtils.show(mContext, "网络请求失败: ", Toast.LENGTH_SHORT);

                    }
                });*/
            }
        });

        /**
         * 无封装，普通POST网络请求
         */
        btn_normal_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "由于最新版本已经将HttpClient删除了，所以具体实现请看代码", Toast.LENGTH_LONG);

                RequestParams params = new RequestParams();
                /**
                 * 这里因为用的第三方接口平台，所以需要穿自己的header，项目中，按实际需要，
                 *
                 *第二个参数是一个id参数。实际项目中按实际情况来填写
                 */
                params.addHeader(Constants.API_KEY, Constants.API_KEY_SECRET);
                params.addBodyParameter("id", "642226199303107806");
                /*mHttUtils.send(HttpRequest.HttpMethod.POST, Urls.XUTILS_TWO_POST, params, new RequestCallBack<String>() {
                    *//**
                 * 请求成功，打印出所得到的字符串
                 * @param responseInfo
                 *//*
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (responseInfo.result != null) {
                            LogUtil.E(responseInfo.result);
                            ToastUtils.show(mContext,responseInfo.result, Toast.LENGTH_SHORT);

                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        LogUtil.E("网络请求失败");
                        ToastUtils.show(mContext, "网络请求失败: ", Toast.LENGTH_SHORT);

                    }
                });*/
            }
        });
        /**
         * BitmapUtils的使用
         */
        btn_normal_bitmaputils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtils mBitmapUtils = MyApplication.getApp().getBitmapUtils();

                // BitmapDisplayConfig用于替换系统全局设置的属性
                BitmapDisplayConfig displayConfig = new BitmapDisplayConfig();

                displayConfig.setBitmapConfig(Bitmap.Config.ARGB_8888);
                displayConfig.setLoadingDrawable(
                        getResources().getDrawable(R.mipmap.ic_launcher));
                displayConfig.setLoadFailedDrawable(
                        getResources().getDrawable(R.mipmap.ic_launcher));
                // 调用BitmapUtils.display方法显示图片

                // 目标ImageView
                // 图片的地址
                // 加载选项
                mBitmapUtils.display(iv_bitmaputils, Urls.GET_IMAGE_URL, displayConfig);
            }
        });
    }

    /**
     * 初始化UI控件
     */
    private void initView() {
        btn_normal_get = (Button) findViewById(R.id.btn_normal_get);
        btn_normal_post = (Button) findViewById(R.id.btn_normal_post);
        mHttUtils = MyApplication.getApp().getHttpUtils();
        mContext = this;
    }
}

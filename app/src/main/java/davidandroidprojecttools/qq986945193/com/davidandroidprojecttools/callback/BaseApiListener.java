package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.os.Message;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 实现回调 IRequestListener
 * 使用requestAsync、request等通用方法调用sdk未封装的接口时，例如上传图片、查看相册等，需传入该回调的实例。
 */

public class BaseApiListener implements IRequestListener {

    private String mScope = "all";
    private Boolean mNeedReAuth = false;
    private Activity mActivity;

    public BaseApiListener(String scope, boolean needReAuth, Activity activity) {
        mScope = scope;
        mNeedReAuth = needReAuth;
        mActivity = activity;
    }

    public BaseApiListener(String scope, boolean needReAuth) {
        mScope = scope;
        mNeedReAuth = needReAuth;
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Bundle params = msg.getData();
            String title = params.getString("title");
            String response = params.getString("response");
//            Util.showResultDialog(mActivity, response, title);
            super.handleMessage(msg);
//            Util.dismissDialog();
        }

    };

    @Override
    public void onComplete(final JSONObject response) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", response.toString());
        params.putString("title", "onComplete");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onIOException(final IOException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onIOException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onMalformedURLException(final MalformedURLException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onMalformedURLException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onJSONException(final JSONException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onJSONException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onConnectTimeoutException(ConnectTimeoutException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onConnectTimeoutException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onSocketTimeoutException(SocketTimeoutException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onSocketTimeoutException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onUnknowException(Exception e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onUnknowException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onHttpStatusException(HttpStatusException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onHttpStatusException");
        mHandler.sendMessage(msg);
    }

    @Override
    public void onNetworkUnavailableException(NetworkUnavailableException e) {
        Message msg = new Message();
        Bundle params = new Bundle();
        params.putString("response", e.getMessage());
        params.putString("title", "onNetworkUnavailableException");
        mHandler.sendMessage(msg);
    }

    public String getmScope() {
        return mScope;
    }

    public void setmScope(String mScope) {
        this.mScope = mScope;
    }

    public Boolean getmNeedReAuth() {
        return mNeedReAuth;
    }

    public void setmNeedReAuth(Boolean mNeedReAuth) {
        this.mNeedReAuth = mNeedReAuth;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.connect.UserInfo;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.QQUserInfoBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.BaseApiListener;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.BaseUiListener;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.ThridLoginConstants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback.UserInfoUiLIstener;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.AppUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.DialogThridUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ShareUtils;

/**
 * 分享的一些功能与UI自定义效果实现
 */
public class ShareActivity extends BaseActivity {
    private Button btn_share_native;
    private Button btn_share_sdk;
    private Button btn_share_custom_ui;
    private Button btn_qq_alone_login;
    private Button btn_weibo_alone_login;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_share);
        btn_share_native = (Button) findViewById(R.id.btn_share_native);
        btn_share_sdk = (Button) findViewById(R.id.btn_share_sdk);
        btn_share_custom_ui = (Button) findViewById(R.id.btn_share_custom_ui);
        btn_qq_alone_login = (Button) findViewById(R.id.btn_qq_alone_login);
        btn_weibo_alone_login = (Button) findViewById(R.id.btn_weibo_alone_login);

    }

    private Dialog mDialog;
    //QQ
    public static Tencent mTencent;
    //微博
    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;


    @Override
    protected void initData() {
        btn_share_native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.nativeShare(ShareActivity.this);
            }
        });

        btn_share_sdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShareSdkActivity.class);
            }
        });

        btn_share_custom_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShareUiActivity.class);
            }
        });
        /**
         * QQ登录(不借助第三方SDK)
         */
        btn_qq_alone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = DialogThridUtils.showWaitDialog(mContext, Constants.LOADING_DATA, false, true);
                mTencent = Tencent.createInstance(ThridLoginConstants.QQ.CLIENT_ID, ShareActivity.this.getApplicationContext());
                //首先判断是否已经安装了QQ,然后在进行启动
              /*  if (AppUtils.isAvilible(ShareActivity.this, "com.tencent.mobileqq")) {

                } else {
                    DialogThridUtils.closeDialog(mDialog);
                    Toast.makeText(getApplicationContext(), "请安装手机QQ", Toast.LENGTH_SHORT).show();
                }*/
                if (!mTencent.isSessionValid()) {
                    //调用QQ登录,然后监听IUiListener
                    mTencent.login(ShareActivity.this, ThridLoginConstants.QQ.SCOPE, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            DialogThridUtils.closeDialog(mDialog);
                            getUserInfo((JSONObject) o);

                        }

                        @Override
                        public void onError(UiError uiError) {
                            DialogThridUtils.closeDialog(mDialog);

                            Toast.makeText(getApplicationContext(), uiError.errorMessage, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancel() {
                            DialogThridUtils.closeDialog(mDialog);
                            Toast.makeText(getApplicationContext(), "授权取消", Toast.LENGTH_LONG).show();


                        }

                    });
//                    mTencent.login(ShareActivity.this, ThridLoginConstants.QQ.SCOPE, new BaseUiListener(ShareActivity.this,mDialog));

                } else {
                    DialogThridUtils.closeDialog(mDialog);
                    Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 微博登录(不借助第三方SDK)
         */
        btn_weibo_alone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = DialogThridUtils.showWaitDialog(mContext, Constants.LOADING_DATA, false, true);
                mAuthInfo = new AuthInfo(getApplicationContext(), ThridLoginConstants.SinaWeibo.CLIENT_ID, ThridLoginConstants.SinaWeibo.REDIRECT_URL, ThridLoginConstants.SinaWeibo.SCOPE);
                if (mAuthInfo == null) {
                    DialogThridUtils.closeDialog(mDialog);
                    Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();

                } else {
                    mSsoHandler = new SsoHandler(ShareActivity.this, mAuthInfo);
                    mSsoHandler.authorize(new WeiboAuthListener() {
                        @Override
                        public void onComplete(Bundle bundle) {
                            DialogThridUtils.closeDialog(mDialog);
                            // 从 Bundle 中解析 Token
                            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
                            if (mAccessToken.isSessionValid()) {
                                // 保存 Token 到 SharedPreferences
                                //得到微博返回的信息
                                LogUtil.E(" mAccessToken.getUid();" + mAccessToken.getUid());
                                LogUtil.E(" mAccessToken.gettoken;" + mAccessToken.getToken());
                                LogUtil.E("expires" + mAccessToken.getToken());
                                Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_LONG).show();
                                //http://open.weibo.com/wiki/2/users/show   获取用户信息
                            } else {
                                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                                String code = bundle.getString("code", "");
                                Toast.makeText(getApplicationContext(), "授权失败，错误代码：" + code, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onWeiboException(WeiboException e) {
                            DialogThridUtils.closeDialog(mDialog);

                            Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {
                            DialogThridUtils.closeDialog(mDialog);

                            Toast.makeText(getApplicationContext(), "授权取消", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
    }


    /**
     * 获取QQ用户的信息
     */
    private QQUserInfoBean userInfo; //qq用户信息

    /**
     * 获取QQ用户的信息.
     * <p/>
     * {
     * "ret":0,
     * "pay_token":"xxxxxxxxxxxxxxxx",
     * "pf":"openmobile_android",
     * "expires_in":"7776000",
     * "openid":"xxxxxxxxxxxxxxxxxxx",
     * "pfkey":"xxxxxxxxxxxxxxxxxxx",
     * "msg":"sucess",
     * "access_token":"xxxxxxxxxxxxxxxxxxxxx"
     * }
     */
    public void getUserInfo(JSONObject values) {
        if (values == null) {
            return;
        }
        try {
            JSONObject jo = (JSONObject) values;

            int ret = jo.getInt("ret");
            LogUtil.E("RET+SHAREACTIVITY" + "~ret" + ret + "~~~json" + jo.toString());
            if (ret == 0) {
                String openid = jo.getString("openid");
                String access_token = jo.getString("access_token");
                String expires = jo.getString("expires_in");
                //登录成功了。这里获取到了openid access_token等一大堆的信息
                LogUtil.E("openid" + openid + "  access_token" + access_token + "   expires" + expires);
                LogUtil.E("mTencent.getQQToken()" + mTencent.getQQToken());
                LogUtil.E("mTencent.getOpenId()" + mTencent.getOpenId());
//                mTencent.requestAsync(com.tencent.connect.common.Constants.LOGIN_INFO, null,
//                        com.tencent.connect.common.Constants.HTTP_GET, new BaseApiListener("get_simple_userinfo", false),
//                        null);
                Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
//                userInfo = new QQUserInfoBean(ShareActivity.this,mTencent.getQQToken());
//                userInfo.getUserInfo(new UserInfoUiLIstener());
                /**
                 * 获取用户的信息。{
                 "is_yellow_year_vip": "0",
                 "ret": 0,
                 "figureurl_qq_1":
                 "http://q.qlogo.cn/qqapp/222222/8C75BBE3DC6B0E9A64BD31449A3C8CB0/40",
                 "figureurl_qq_2":
                 "http://q.qlogo.cn/qqapp/222222/8C75BBE3DC6B0E9A64BD31449A3C8CB0/100",
                 "nickname": "小罗",
                 "yellow_vip_level": "0",
                 "msg": "",
                 "figureurl_1":
                 "http://qzapp.qlogo.cn/qzapp/222222/8C75BBE3DC6B0E9A64BD31449A3C8CB0/50",
                 "vip": "0",
                 "level": "0",
                 "figureurl_2":
                 "http://qzapp.qlogo.cn/qzapp/222222/8C75BBE3DC6B0E9A64BD31449A3C8CB0/100",
                 "is_yellow_vip": "0",
                 "gender": "男",
                 "figureurl":
                 "http://qzapp.qlogo.cn/qzapp/222222/8C75BBE3DC6B0E9A64BD31449A3C8CB0/30"
                 }
                 */
                //获取移动端应用的登录用户的简单个人信息，包括昵称、QQ空间头像、QQ头像以及黄钻信息。
                //https://graph.qq.com/user/get_simple_userinfo?access_token=qq986945193&openid=qq986945193&format=json&oauth_consumer_key=1105981856
                //access_token即access_token  openId即 openID  format即返回的格式，可以是json和xml  oauth_consumer_key即appID
            }

        } catch (Exception e) {
            // TODO: handle exception
        }


    }


    /**
     * 接受第三方返回来的信息
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * onActivityResultData接口中的listener为当前调用的Activity所实现的相应回调UIListener
         */
        if (mTencent != null) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener(ShareActivity.this, mDialog));
            return;
        }
        /**
         * 微博登陆信息
         */
        /**
         * 微博登陆信息
         */
        if (mSsoHandler != null) {
            super.onActivityResult(requestCode, resultCode, data);
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 调用QQ注销接口
     */
    public void logout() {
        mTencent.logout(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTencent != null) {
            mTencent.releaseResource();
            mTencent = null;
        }

        if (mAuthInfo != null) {
            mAuthInfo = null;
        }
    }

    /**
     * 本地分享
     */
    private void nativeShare() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, Urls.CSDN_BLOG_DAVID);
        startActivity(Intent.createChooser(intent, "程序员小冰"));

    }

}

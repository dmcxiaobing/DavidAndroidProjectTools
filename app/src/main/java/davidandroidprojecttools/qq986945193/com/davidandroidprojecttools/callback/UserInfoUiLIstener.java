package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;

/**
 * 获取到QQ用户的信息
 * 实现回调 IUiListener
 * 调用SDK已经封装好的接口时，例如：登录、快速支付登录、应用分享、应用邀请等接口，需传入该回调的实例。
 */
public class UserInfoUiLIstener implements IUiListener {
    @Override
    public void onComplete(Object o) {
        LogUtil.E("UserInfoUiLIsteneronComplete");
        doComplete((JSONObject) o);
    }

    protected void doComplete(JSONObject values) {
        if (values == null) {
            return;
        }

        try {
            JSONObject jo = values;
            int ret = jo.getInt("ret");
            String nickName = jo.getString("nickname");
            String gender = jo.getString("gender");
            LogUtil.E("nickname" + nickName + "~gender" + gender + "~ret" + ret);
        } catch (Exception e) {

        }


    }

    @Override
    public void onError(UiError uiError) {
    }

    @Override
    public void onCancel() {

    }

}
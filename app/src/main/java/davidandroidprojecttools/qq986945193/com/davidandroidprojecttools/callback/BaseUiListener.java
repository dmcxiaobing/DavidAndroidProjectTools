package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ShareActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.DialogThridUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;


/**
 * 获取到QQ用户的信息
 * 实现回调 IUiListener
 * 调用SDK已经封装好的接口时，例如：登录、快速支付登录、应用分享、应用邀请等接口，需传入该回调的实例。
 */
public class BaseUiListener implements IUiListener
{
    private Dialog mDialog;
    private Context context;
    //V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档
    public BaseUiListener(Context context,Dialog dialog)
    {
        this.mDialog = dialog;
        this.context = context;
    }
    @Override
    public void onComplete(Object o)
    {
        JSONObject object = (JSONObject)o;
        DialogThridUtils.closeDialog(mDialog);
        doComplete(object);
    }
    //在这里可以做一些登录成功的处理
    public void doComplete(JSONObject values)
    {

        UserInfo userInfo = new UserInfo(context, ShareActivity.mTencent.getQQToken());
        userInfo.getUserInfo(new UserInfoUiLIstener());
        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();

    }
    //在这里可以做登录失败的处理
    @Override
    public void onError(UiError e)
    {
        DialogThridUtils.closeDialog(mDialog);
        Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();

        LogUtil.E("object","error");
    }
    //在这里可以做登录被取消的处理
    @Override
    public void onCancel()
    {
        DialogThridUtils.closeDialog(mDialog);
        Toast.makeText(context, "登陆取消", Toast.LENGTH_SHORT).show();

        LogUtil.E("object","cancel");

    }
}


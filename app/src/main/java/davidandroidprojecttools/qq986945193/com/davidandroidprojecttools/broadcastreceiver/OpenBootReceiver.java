package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.broadcastreceiver;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MainActivity;

/**
 * 利用广播实现开机自启动app
 */
public class OpenBootReceiver extends BroadcastReceiver {
    /**
     * 可以实现开机自动打开软件并运行。
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("xiaobing", "BootReceiver.onReceive: " + intent.getAction());
        System.out.println("自启动程序即将执行");
        //MainActivity就是开机显示的界面
        Intent mBootIntent = new Intent(context, MainActivity.class);
        //下面这句话必须加上才能开机自动运行app的界面
        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mBootIntent);
    }
}
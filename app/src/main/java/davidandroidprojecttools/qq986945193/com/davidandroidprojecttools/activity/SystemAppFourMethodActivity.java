package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ActivityManagerUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.TipsToastView;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：退出app应用的多种实现方式
 * <p/>
 * 说明：我这里只是调用了finish()当前的activity，实际项目中，按需要更改即可。
 */
public class SystemAppFourMethodActivity extends Activity {
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_app_four_method);
        ActivityManagerUtils.getInstance().addActivity(this);
        toast = Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT);
    }


    /**
     * 双击两次退出程序提示  第一种方式
     */
   /* boolean isState = true;//设置双击退出的变量
    public void onBackPressed() {
        if (isState) { //isState初始值为true
            isState = false;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    isState = true;
                }
            }, 2000);
        } else {
            finish();
        }
    }*/

    /**
     * 双击两次退出程序提示  第二种方式
     */

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            quitToast();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void quitToast() {
//        if (null == toast.getView().getParent()) {
//            toast.show();
//        } else {
//            ActivityManagerUtils.getInstance().finishActivity(SystemAppFourMethodActivity.this);
////            System.exit(0);
//        }
//    }

    /**
     * 双击两次退出程序提示  第三种方式
     * <p/>
     * https://github.com/QQ986945193
     */
    // 1.on..2.开发者实现 3.系统调用
    // 条件 :点击物理键盘或者软件盘
    // int keyCode按键编号
  /*  long[] times = new long[2];
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // ①　找出回调函数
            // ②　点击时间 time1 time2
            // ③　时间 间隔 <2000 退出
            // ④　提示再点一次
            times[0] = times[1];
            times[1] = System.currentTimeMillis();
            if (times[1] - times[0] < 2000) {
                finish();
            } else {
                Toast.makeText(getBaseContext(), "再点一次退出", Toast.LENGTH_SHORT).show();
            }
            return true;// 消费事件
        }
        return super.onKeyDown(keyCode, event);
    }*/

    /**
     * 监听返回按钮，弹出对话框   退出应用
     * <p/>
     * https://github.com/QQ986945193
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }

        return false;

    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
//                    finish();
//                    ActivityManagerUtils.getInstance().finishActivityclass(SystemAppFourMethodActivity.class);
                    ActivityManagerUtils.getInstance().finishActivity(SystemAppFourMethodActivity.this);
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };


    //退出时间
    private long exitTime = 0;
    /**
     * 按两次退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showTips(R.drawable.tips_smile, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private TipsToastView tipsToast;

    *//**
     * 自定义toast
     *
     * @param iconResId 图片
     * @param tips      提示文字
     *//*
    private void showTips(int iconResId, String tips) {
        if (tipsToast != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                tipsToast.cancel();
            }
        } else {
            tipsToast = TipsToastView.makeText(SystemAppFourMethodActivity.this.getApplication()
                    .getBaseContext(), tips, TipsToastView.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }*/

    /**
     * onResume与onPause()封装提取原因友盟统计
     */
    protected void onResume() { // Umeng 对处理事件的统计
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}

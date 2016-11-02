package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.SystemStatusManagerUtils;

/**
 * 系统的沉浸式样式主题实现效果
 */
public class SystemStatusThemeActivity extends Activity {
    private TextView tv_theme;

    /**
     * 1、在你的BaseActivity中的onCreate方法中调用一下代码（如果只要某个页面一体化，可以在需要一体化的页面加入改语句）：
     * new SystemStatusManager(this).setTranslucentStatus(R.color.colorPrimary);//设置状态栏透明，参数为你要设置的颜色
     * 2、将你项目中的所有页面的根布局加载一个属性：(只在activity中加即可.不要在fragment里面加) android:fitsSystemWindows=”true”
     * 注意：如果状态栏与标题栏中间有一条线导致有过渡感，那么在你项目的主题（style文件）中加入去掉线条的语句即可：
     * <item name="android:windowContentOverlay">@null</item>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SystemStatusManagerUtils(this).setTranslucentStatus(R.color.colorPrimary);//设置状态栏透明，参数为你要设置的颜色
        setContentView(R.layout.activity_system_app_method);
        tv_theme = (TextView) findViewById(R.id.tv_theme);
        tv_theme.setText("系统的沉浸式样式主题实现效果");
    }
}

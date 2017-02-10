package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * Toolbar和DrawableLayout的用法
 */
public class ToolbarDrawerLayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_drawerlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置支持toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setToolbarBackMethod();
    }

    private void setToolbarBackMethod() {
//        想使用 toolbar 的返回按钮（都要在setSupportActionBar 后调用）实现 Toolbar 点击返回事件：
        toolbar.setNavigationIcon(R.mipmap.ic_back);//或者在布局中 app:navigationIcon="?attr/homeAsUpIndicator"
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
//        或者   重写
//       getSupportActionBar().setDisplayHomeAsUpEnable(true);
//       onOptionsItemSelected()
    }


}

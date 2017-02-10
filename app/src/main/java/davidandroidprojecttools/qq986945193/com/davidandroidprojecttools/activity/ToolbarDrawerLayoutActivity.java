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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * Toolbar和DrawableLayout的用法
 */
public class ToolbarDrawerLayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private DrawerLayout drawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_drawerlayout);
        initToolbar();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置支持toolbar
        setSupportActionBar(toolbar);


        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        btnThree = (Button) findViewById(R.id.btn_three);
        btnFour = (Button) findViewById(R.id.btn_four);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置toolbar的监听事件与图表
//        setToolbarBackMethod();

        /**
         * 设置drawerlayout
         */
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, 0, 0);
        actionBarDrawerToggle.syncState();
        //添加监听事件
        drawerlayout.addDrawerListener(actionBarDrawerToggle);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(ToolbarDrawerLayoutActivity.this, "btnOne", Toast.LENGTH_SHORT);
                drawerlayout.closeDrawers();
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(ToolbarDrawerLayoutActivity.this, "btnTwo", Toast.LENGTH_SHORT);
                drawerlayout.closeDrawers();

            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(ToolbarDrawerLayoutActivity.this, "btnThree", Toast.LENGTH_SHORT);
                drawerlayout.closeDrawers();

            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(ToolbarDrawerLayoutActivity.this, "btnFour", Toast.LENGTH_SHORT);
                drawerlayout.closeDrawers();

            }
        });

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

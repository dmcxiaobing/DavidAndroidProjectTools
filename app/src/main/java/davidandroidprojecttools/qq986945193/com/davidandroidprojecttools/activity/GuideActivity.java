package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ActivityManagerUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.SharedPreferencesUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：引导页介绍
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager vp_guide;
    private LinearLayout ll_point;

    private List<View> mPointListViews = new ArrayList<>();
    private GuideAdapter mAdapter;
    private TextView tv_start;
    private int mSelectIndex = 0;
    private List<ImageView> listImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //此两段代码必须设置在setContentView()方法之前
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ActivityManagerUtils.getInstance().addActivity(this);
        initView();
        initViewPager();
        initPoints();

    }


    /**
     * 动态添加导航圆点
     */
    private void initPoints() {
        View pointView = null;
        for (int i = 0; i < listImages.size(); i++) {
            pointView = new View(GuideActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20
                    , 20);
            params.rightMargin = 10;
            pointView.setLayoutParams(params);
            pointView.setSelected(false);
            pointView.setBackgroundResource(R.drawable.selector_splash_point);
            ll_point.addView(pointView);
            mPointListViews.add(pointView);
        }
        mPointListViews.get(0).setSelected(true);
    }

    /**
     * 添加ImageView到ViewPager中
     */
    private void initViewPager() {
        //第一张图片
        ImageView img1 = new ImageView(this);
        img1.setBackgroundResource(R.mipmap.slide1);
        listImages.add(img1);
        //第二张图片
        ImageView img2 = new ImageView(this);
        img2.setBackgroundResource(R.mipmap.slide2);
        listImages.add(img2);
        //第三张图片
        ImageView img3 = new ImageView(this);
        img3.setBackgroundResource(R.mipmap.slide3);
        listImages.add(img3);
        //第四张图片
        ImageView img4 = new ImageView(this);
        img4.setBackgroundResource(R.mipmap.slide4);
        listImages.add(img4);

        //创建viewpager的适配器
        mAdapter = new GuideAdapter();
        vp_guide.setAdapter(mAdapter);
        //添加滑动选中的监听事件
        vp_guide.setCurrentItem(0);
        vp_guide.addOnPageChangeListener(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.putBoolean(GuideActivity.this, "is_user_guide_showed", true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //当选中某一页时，导航圆点随之变化
    @Override
    public void onPageSelected(int position) {
        mPointListViews.get(mSelectIndex).setSelected(false);
        mSelectIndex = position;
        mPointListViews.get(position).setSelected(true);
        if (position == listImages.size() - 1) {
            tv_start.setVisibility(View.VISIBLE);
        } else {
            tv_start.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * pagerAdapter 需要实现四个方法。
     */
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(listImages.get(position));
            return listImages.get(position);
        }
    }


}

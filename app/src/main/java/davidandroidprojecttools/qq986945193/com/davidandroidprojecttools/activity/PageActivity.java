package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.Product_Bannar;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ActivityManagerUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.HackyViewPager;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.ZoomableImageView;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 文件名称：点击商品详情bannar条 查看图片
 */
public class PageActivity extends Activity {
    List<Product_Bannar> list;
    int position;
    TextView tv_all, tv_count;
    HackyViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ActivityManagerUtils.getInstance().addActivity(this);
        initView();
        setOnListener();
    }


    private void initView() {
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_count = (TextView) findViewById(R.id.tv_count);
        viewPager = (HackyViewPager) findViewById(R.id.view_pager);
        if (getIntent() != null) {
            list = getIntent().getExtras().getParcelableArrayList("list");
            position = getIntent().getExtras().getInt("position");
            tv_all.setText("/" + list.size() + "");
            tv_count.setText(position + 1 + "");
            SamplePagerAdapter adapter = new SamplePagerAdapter(list, this);
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(3);
            viewPager.setCurrentItem(position, false);
        }
    }


    private void setOnListener() {


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int p) {
                tv_count.setText(p + 1 + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    static class SamplePagerAdapter extends PagerAdapter {

        List<Product_Bannar> list;
        PageActivity pageActivity;

        public SamplePagerAdapter(List<Product_Bannar> list, PageActivity pageActivity) {
            this.list = list;
            this.pageActivity = pageActivity;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View instantiateItem(final ViewGroup container, int position) {
            ZoomableImageView photoView = new ZoomableImageView(container.getContext());
            ImageLoader.getInstance().displayImage(list.get(position).getPic(), photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            //点击bannar 退出当前页面
            photoView.setOnImageTouchedListener(new ZoomableImageView.OnImageTouchedListener() {
                @Override
                public void onImageTouched() {
                    ActivityManagerUtils.getInstance().finishActivityclass(PageActivity.class);
                }
            });


            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }


}

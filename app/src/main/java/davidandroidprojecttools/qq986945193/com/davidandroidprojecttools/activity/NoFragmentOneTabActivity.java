package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/**
 * 不用fragment实现tab切换
 */
public class NoFragmentOneTabActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager vp;
    private LinearLayout ll_index;
    private LinearLayout ll_setting;
    private LinearLayout ll_friends;
    private LinearLayout ll_im;

    private MyAdapter mAdapter;

    private List<View> listViews = new ArrayList<>();


    @Override
    protected void initView() {
        setContentView(R.layout.activity_no_fragment_one_tab);
        vp = (ViewPager) findViewById(R.id.vp);
        ll_index = (LinearLayout) findViewById(R.id.ll_index);
        ll_im = (LinearLayout) findViewById(R.id.ll_im);
        ll_friends = (LinearLayout) findViewById(R.id.ll_friends);
        ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

        LayoutInflater inflater = LayoutInflater.from(this);
        View viewIndex = inflater.inflate(R.layout.activity_butterknife, null);
        View viewFriends = inflater.inflate(R.layout.activity_callphone, null);
        View viewIm = inflater.inflate(R.layout.activity_flowlayout, null);
        View viewSetting = inflater.inflate(R.layout.activity_round_zoom_imageview, null);
        listViews.add(viewIndex);
        listViews.add(viewFriends);
        listViews.add(viewIm);
        listViews.add(viewSetting);

        mAdapter = new MyAdapter();
        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = vp.getCurrentItem();
                resertSelect();
                switch (currentItem) {
                    case 0:
                        ll_index.setSelected(true);
                        break;
                    case 1:
                        ll_friends.setSelected(true);
                        break;
                    case 2:
                        ll_im.setSelected(true);
                        break;
                    case 3:
                        ll_setting.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        ll_index.setSelected(true);

    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        ll_index.setOnClickListener(this);
        ll_friends.setOnClickListener(this);
        ll_im.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        resertSelect();
        switch (v.getId()) {
            case R.id.ll_index:
                ll_index.setSelected(true);
                vp.setCurrentItem(0);
                break;
            case R.id.ll_friends:
                ll_friends.setSelected(true);
                vp.setCurrentItem(1);
                break;
            case R.id.ll_im:
                ll_im.setSelected(true);
                vp.setCurrentItem(2);
                break;
            case R.id.ll_setting:
                ll_setting.setSelected(true);
                vp.setCurrentItem(3);
                break;
        }
    }

    private void resertSelect() {
        ll_index.setSelected(false);
        ll_friends.setSelected(false);
        ll_im.setSelected(false);
        ll_setting.setSelected(false);
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = listViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(listViews.get(position));
        }
    }


}

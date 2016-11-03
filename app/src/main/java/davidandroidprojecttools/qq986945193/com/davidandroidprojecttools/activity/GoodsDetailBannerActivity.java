package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.Product_Bannar;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment.MyItemFragment;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ScreenUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.URLUtils;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class GoodsDetailBannerActivity extends BaseFragmentActivity {
    @BindView(R.id.vp_goods_detials_shopping_bannar)
    ViewPager vpGoodsDetialsShoppingBannar;

    @BindView(R.id.tv_count_page)
    TextView tvCountPage;

    @BindView(R.id.tv_total_page)
    TextView tvTotalPage;

    @BindView(R.id.goods_detials_bannar)
    RelativeLayout goodsDetialsBannar;
    private List<String> listImages = new ArrayList<>();
    private MyAdapter mAdapter;
    private List<Product_Bannar> bannars = new ArrayList<>();
    private Product_Bannar bannar = null;


    @Override
    protected void initView() {
        ButterKnife.bind(this);

        //动态设置bannar条的高度
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight(this) / 2 - 40);
        vpGoodsDetialsShoppingBannar.setLayoutParams(lParams);

        /*bannar总数*/
        listImages.add(Urls.VIEWPAGE_BANNERIMAGE_ONE);
        listImages.add(Urls.VIEWPAGE_BANNERIMAGE_TWO);
        listImages.add(Urls.VIEWPAGE_BANNERIMAGE_THREE);
        tvTotalPage.setText("/" + listImages.size() + "");
        if (listImages.size() > 0) {
            tvCountPage.setText(1 + "");
        }
        for (int i = 0; i < listImages.size(); i++) {
            bannar = new Product_Bannar(listImages.get(i));
            bannars.add(bannar);
        }

    }

    @Override
    protected void initData() {

        vpGoodsDetialsShoppingBannar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvCountPage.setText(position + 1 + "");

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mAdapter = new MyAdapter(getSupportFragmentManager(), bannars);
        vpGoodsDetialsShoppingBannar.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_goods_detail_banner;
    }

    private class MyAdapter extends FragmentPagerAdapter {

        private List<Product_Bannar> mList;

        public MyAdapter(FragmentManager fm, List<Product_Bannar> mList) {
            super(fm);
            this.mList = mList;
        }


        @Override
        public Fragment getItem(int position) {
            MyItemFragment fragment = new MyItemFragment();
            fragment.setUrl(mList.get(position).getPic());
            fragment.setPosition(position);
            fragment.setList(mList);
            return fragment;
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

}

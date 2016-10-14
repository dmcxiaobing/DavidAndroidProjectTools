package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.AutoViewPagerBannerActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.BadgeViewWaterDropActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.BarCodeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.DavidXListViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ListViewIntroduceActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.RoundZoomImageViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.StartDialogImageActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SystemAppFourMethodActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.UpMarqueeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.XListViewActivity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 常用功能
 */
public class CommonlyUsedFunctionFragment extends Fragment {
    private ListView lv_commonly_used_function_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_commonly_used_function, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
        }
        initData();
    }

    private Intent intent;

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("三种方式圆形头像图片的实现");
        listDatas.add("底部Tab菜单栏五种实现方式");
        listDatas.add("SwipeRefreshLayout下拉刷新的详解");
        listDatas.add("无限轮播滚动的banner(封装ViewPager)");
        listDatas.add("仿微信提示消息个数两种实现方式");
        listDatas.add("利用Zxing实现二维码的生成与扫描功能");
        listDatas.add("XListView下拉刷新和上拉加载更多");
        listDatas.add("仿淘宝,百度外卖,饿了么等头条滚动效果");
        listDatas.add("进入界面弹出一张大图");

        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_commonly_used_function_fragment.setAdapter(mAdatper);

        lv_commonly_used_function_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * 三种方式圆形头像图片的实现
                     */
                    case 0:
                        intent = new Intent(mContext, RoundZoomImageViewActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, SystemAppFourMethodActivity.class);
                        break;

                    case 2:
                        intent = new Intent(mContext, SystemAppFourMethodActivity.class);
                        break;
                    /**
                     * 无限轮播滚动的banner(封装ViewPager)
                     */
                    case 3:
                        intent = new Intent(mContext, AutoViewPagerBannerActivity.class);
                        break;
                    /**
                     * 仿QQ微信提示消息个数两种实现方式
                     */
                    case 4:
                        intent = new Intent(mContext, BadgeViewWaterDropActivity.class);
                        break;
                    /**
                     * 利用Zxing实现二维码的生成与扫描功能
                     */
                    case 5:
                        intent = new Intent(mContext, BarCodeActivity.class);
                        break;
                    /**
                     * XListView下拉刷新和上拉加载更多
                     */
                    case 6:
//                        intent = new Intent(mContext, XListViewActivity.class);
                        intent = new Intent(mContext, DavidXListViewActivity.class);

                        break;
                    /**
                     * 仿淘宝,百度外卖,饿了么等头条滚动效果
                     */
                    case 7:
                        intent = new Intent(mContext, UpMarqueeActivity.class);

                        break;
                    /**
                     * 进入界面弹出一张大图
                     */
                    case 8:
                        intent = new Intent(mContext, StartDialogImageActivity.class);

                        break;

                }
                if (intent != null) {
                    mContext.startActivity(intent);
                }

            }
        });
    }

    /**
     * 初始化UI控件
     */
    private void initView(View rootView) {
        lv_commonly_used_function_fragment = (ListView) rootView.findViewById(R.id.lv_commonly_used_function_fragment);
    }
}

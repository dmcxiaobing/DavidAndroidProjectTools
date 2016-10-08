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
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ListViewIntroduceActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.RoundZoomImageViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SystemAppFourMethodActivity;

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
        if (getActivity() != null) {
            mContext = getActivity();
        }
        initView(rootView);
        initData();

        return rootView;
    }

    private Intent intent;

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("圆形头像的实现");
        listDatas.add("底部Tab菜单栏五种实现方式");
        listDatas.add("SwipeRefreshLayout下拉刷新的详解");
        listDatas.add("无限轮播滚动的banner(封装ViewPager)");
        listDatas.add("仿微信提示消息个数两种实现方式");

        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_commonly_used_function_fragment.setAdapter(mAdatper);

        lv_commonly_used_function_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * ListView详解的类
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
                     * 仿微信提示消息个数两种实现方式
                     */
                    case 4:
                        intent = new Intent(mContext, BadgeViewWaterDropActivity.class);
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

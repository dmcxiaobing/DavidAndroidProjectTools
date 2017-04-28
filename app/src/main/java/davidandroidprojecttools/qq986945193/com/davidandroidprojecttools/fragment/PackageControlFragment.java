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

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.CityPickerListActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.DavidWebViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.FlowLayoutActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.GoodsDetailBannerActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MyAdapterActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.RefreshLoadMoreActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SelectorPickerActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ShareActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.StarBarViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.WebViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 封装控件
 */
public class PackageControlFragment extends Fragment {
    private ListView lv_package_control_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_package_control, container, false);
        initView(rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
        }
    }

    private Intent intent;

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("封装显示星星个数 常用评论数");
        listDatas.add("利用万能Listview的adapter进行展示数据");
        listDatas.add("流式布局显示标签，以及热搜关键词等功能");
        listDatas.add("WebView的一些使用功能详解");
        listDatas.add("上拉加载更多与下拉刷新的多种实现");
        listDatas.add("日期以及地区等选择的多级联动");
        listDatas.add("仿美团饿了吗等选择城市列表，以及城市定位");
        listDatas.add("商品详情中图片展示轮播以及显示放大与缩小");
        listDatas.add("第三方分享与第三方登陆的一些功能实现");

        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_package_control_fragment.setAdapter(mAdatper);

        lv_package_control_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * 封装显示星星个数 常用评论数
                     */
                    case 0:
                        intent = new Intent(mContext, StarBarViewActivity.class);
                        break;
                    /**
                     * 利用万能Listview的adapter进行展示数据
                     */
                    case 1:
                        intent = new Intent(mContext, MyAdapterActivity.class);
                        break;
                    /**
                     * 流式布局显示标签，以及热搜关键词等功能
                     */
                    case 2:
                        intent = new Intent(mContext, FlowLayoutActivity.class);
                        break;
                    /**
                     * WebView的一些使用功能详解
                     */
                    case 3:
                        intent = new Intent(mContext, WebViewActivity.class);
                        break;
                    /**
                     *上拉加载更多与下拉刷新的多种实现
                     */
                    case 4:
                        intent = new Intent(mContext, RefreshLoadMoreActivity.class);
                        break;
                    /**
                     *日期以及地区等选择的多级联动
                     */
                    case 5:
                        intent = new Intent(mContext, SelectorPickerActivity.class);
                        break;
                    /**
                     *仿美团饿了吗等选择城市列表，以及城市定位
                     */
                    case 6:
                        intent = new Intent(mContext, CityPickerListActivity.class);
                        break;
                    /**
                     *商品详情中图片展示轮播以及显示放大与缩小
                     */
                    case 7:
                        intent = new Intent(mContext, GoodsDetailBannerActivity.class);
                        break;
                    /**
                     *第三方分享与第三方登陆的一些功能实现
                     */
                    case 8:
                        intent = new Intent(mContext, ShareActivity.class);
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
        lv_package_control_fragment = (ListView) rootView.findViewById(R.id.lv_package_control_fragment);
    }

    private boolean isInit; // 是否可以开始加载数据

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 每次切换fragment时调用的方法
        // 相当于Fragment的onResume
        if (isVisibleToUser) {
            isInit = true;
            showData();
        }
    }

    private void showData() {
        if (isInit) {
            isInit = false;// 加载数据完成
            listDatas.clear();
            initData();


        }
    }


    @Override
    public void onResume() {
        super.onResume();
        // 判断当前fragment是否显示
        if (getUserVisibleHint()) {
            isInit = true;
            showData();
        }
    }
}

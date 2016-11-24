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
import android.widget.Toast;

import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.AutoViewPagerActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.AutoViewPagerBannerActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.BadgeViewWaterDropActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.BarCodeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.CheckCodeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.DavidXListViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.DebounceClickActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ListViewIntroduceActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.LoadingActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MarqueeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MiUiTabActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.RoundZoomImageViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SplashAdActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.StartDialogImageActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SystemAppFourMethodActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.TabActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.UpMarqueeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.XListViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.MarqueeView;

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

    }

    private Intent intent;

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("三种方式圆形头像图片的实现");
        listDatas.add("Splash首页添加广告progressBar进度");
        listDatas.add("公告广告通知显示滚动，类似淘宝等头条滚动效果");
        listDatas.add("无限轮播滚动的banner(封装ViewPager)");
        listDatas.add("仿微信提示消息个数两种实现方式");
        listDatas.add("利用Zxing实现二维码的生成与扫描功能");
        listDatas.add("验证码的多种实现方法");
        listDatas.add("仿淘宝,百度外卖,饿了么等头条滚动效果");
        listDatas.add("进入界面弹出一张大图");
        listDatas.add("loading等待提示框多种实现方式");
        listDatas.add("各种各样的Tab切换功能效果的实现");
        listDatas.add("获取手机验证码，延迟六十秒，防止重复点击");

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
                    /**
                     * Splash首页添加广告progressBar进度
                     */
                    case 1:
                        intent = new Intent(mContext, SplashAdActivity.class);
                        break;
                    /**
                     * 公告广告通知显示滚动，类似淘宝等头条滚动效果
                     */
                    case 2:
                        intent = new Intent(mContext, MarqueeActivity.class);
                        break;
                    /**
                     * 无限轮播滚动的banner(封装ViewPager)
                     */
                    case 3:
                        intent = new Intent(mContext, AutoViewPagerActivity.class);
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
                     *验证码的多种实现方法
                     */
                    case 6:
                        intent = new Intent(mContext, CheckCodeActivity.class);
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
                    /**
                     * loading等待提示框多种实现方式
                     */
                    case 9:
                        intent = new Intent(mContext, LoadingActivity.class);

                        break;
                    /**
                     * 各种各样的Tab切换功能效果的实现
                     */
                    case 10:
                        intent = new Intent(mContext, TabActivity.class);

                        break;
                    /**
                     * 获取手机验证码，延迟六十秒，防止重复点击
                     */
                    case 11:
                        intent = new Intent(mContext, DebounceClickActivity.class);

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
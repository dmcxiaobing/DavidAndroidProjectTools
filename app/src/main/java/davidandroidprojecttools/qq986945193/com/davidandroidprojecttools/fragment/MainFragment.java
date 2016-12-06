package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lidroid.xutils.util.LogUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.CallphoneActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.CreateFileActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ListViewIntroduceActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MoreAnimationStartActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.RoundZoomImageViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SwipeRefreshListViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SystemAppFourMethodActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.SystemStatusThemeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.TakeSelectorPhotoActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Constants;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 首页fragment 知识点
 */
public class MainFragment extends Fragment {
    private ListView lv_main_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView(rootView);
        LogUtil.E("oncreateView");
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
            LogUtil.E("onActivityCreated");

        }


    }

    private Intent intent;

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("ListView与adapter的详解");
        listDatas.add("退出app应用的多种实现方式");
        listDatas.add("SwipeRefreshLayout下拉刷新的详解");
        listDatas.add("拨打电话号码的详解代码(比较简单)");
        listDatas.add("系统的沉浸式样式主题实现效果");
        listDatas.add("拍照以及选择相册手机照片");
        listDatas.add("Activity的多种跳转样式动画效果");
        listDatas.add("在手机中创建文件以及写入,读取,删除等功能");
        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_main_fragment.setAdapter(mAdatper);

        lv_main_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * ListView与adapter的详解
                     */
                    case 0:
                        intent = new Intent(mContext, ListViewIntroduceActivity.class);
                        break;
                    /**
                     * 退出app应用的多种实现方式
                     */
                    case 1:
                        intent = new Intent(mContext, SystemAppFourMethodActivity.class);
                        break;
                    /**
                     * SwipeRefreshLayout的简单使用教程。下拉刷新控件炫酷效果。
                     */
                    case 2:
                        intent = new Intent(mContext, SwipeRefreshListViewActivity.class);
                        break;
                    /**
                     * 拨打电话号码的详解代码(比较简单)
                     */
                    case 3:
                        intent = new Intent(mContext, CallphoneActivity.class);
                        break;
                    /**
                     *系统的沉浸式样式主题实现效果
                     */
                    case 4:
                        intent = new Intent(mContext, SystemStatusThemeActivity.class);
                        break;
                    /**
                     *拍照以及选择相册手机照片
                     */
                    case 5:
                        intent = new Intent(mContext, TakeSelectorPhotoActivity.class);
                        break;
                    /**
                     *Activity的多种跳转样式动画效果
                     */
                    case 6:
                        intent = new Intent(mContext, MoreAnimationStartActivity.class);
                        break;
                    /**
                     *在手机中创建文件以及写入,读取,删除等功能
                     */
                    case 7:
                        intent = new Intent(mContext, CreateFileActivity.class);
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
        lv_main_fragment = (ListView) rootView.findViewById(R.id.lv_main_fragment);
    }


    private boolean isInit; // 是否可以开始加载数据

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.E("setUserVisibleHint");

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
            LogUtil.E("showData");

            initData();


        }
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtil.E("onResume");

        // 判断当前fragment是否显示
        if (getUserVisibleHint()) {
            isInit = true;
            showData();
        }
    }
}

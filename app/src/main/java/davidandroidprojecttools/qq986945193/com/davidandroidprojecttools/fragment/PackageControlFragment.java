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
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.DavidWebViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.FlowLayoutActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.MyAdapterActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.OkHttpActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.PicassoAndImageloaderFrescoActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.StarBarViewActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.XutilsTwoActivity;
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
        LogUtil.E("oncreate+package");

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.E("onresume+package");

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
        listDatas.add("封装显示星星个数 常用评论数");
        listDatas.add("利用万能Listview的adapter进行展示数据");
        listDatas.add("流式布局显示标签，以及热搜关键词等功能");
        listDatas.add("自定义WebView支持下拉刷新的功能");

        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_package_control_fragment.setAdapter(mAdatper);

        lv_package_control_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.E("position == " + position);
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
                     * 自定义WebView支持下拉刷新的功能
                     */
                    case 3:
                        intent = new Intent(mContext, DavidWebViewActivity.class);
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
}

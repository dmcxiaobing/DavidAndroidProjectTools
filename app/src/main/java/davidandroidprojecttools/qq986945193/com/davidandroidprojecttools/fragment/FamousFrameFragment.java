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
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.ButterKnifeActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.OkHttpActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.PicassoAndImageloaderFrescoActivity;
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
 * 著名开源库
 */
public class FamousFrameFragment extends Fragment {
    private ListView lv_famous_frame_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_famous_frame, container, false);
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
        listDatas.add("Xutils2的四大模块使用详解");
        listDatas.add("Imageloader,Picasso,Fresco,Glide图片处理");
        listDatas.add("OkHttp的封装与网络请求详解");
        listDatas.add("ButterKnife的使用详解");
        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_famous_frame_fragment.setAdapter(mAdatper);

        lv_famous_frame_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.E("position == " + position);
                switch (position) {
                    /**
                     * Xutils2使用详解
                     */
                    case 0:
                        intent = new Intent(mContext, XutilsTwoActivity.class);
                        /**
                         * Imageloader,Picasso,Fresco,Glide图片处理
                         */
                        break;
                    case 1:
                        intent = new Intent(mContext, PicassoAndImageloaderFrescoActivity.class);
                        /**
                         * OkHttp的封装与网络请求详解
                         */
                        break;
                    /**
                     * OkHttp的封装与网络请求详解
                     */
                    case 2:
                        intent = new Intent(mContext, OkHttpActivity.class);
                        break;
                    /**
                     * ButterKnife的使用详解
                     */
                    case 3:
                        intent = new Intent(mContext, ButterKnifeActivity.class);
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
        lv_famous_frame_fragment = (ListView) rootView.findViewById(R.id.lv_famous_frame_fragment);
    }
}

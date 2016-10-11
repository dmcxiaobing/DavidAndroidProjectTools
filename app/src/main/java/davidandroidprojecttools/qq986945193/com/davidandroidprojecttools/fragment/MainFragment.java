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
        listDatas.add("ListView与adapter的详解");
        listDatas.add("退出app应用的四种实现方式");
        listDatas.add("SwipeRefreshLayout下拉刷新的详解");

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
                     * 退出app应用的四种实现方式
                     */
                    case 1:
                        intent = new Intent(mContext, SystemAppFourMethodActivity.class);
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
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity.PageActivity;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.ProductBannerBean;


/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class MyItemFragment extends Fragment implements View.OnClickListener {

    private int position;
    private ImageView imageView;
    private String url;
    List<ProductBannerBean> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_img, container, false);
        imageView = (ImageView) v.findViewById(R.id.fg_iv_1);
        ImageLoader.getInstance().displayImage(url, imageView);
        v.setOnClickListener(this);
        return v;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setList(List<ProductBannerBean> list) {
        this.list = list;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), PageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}

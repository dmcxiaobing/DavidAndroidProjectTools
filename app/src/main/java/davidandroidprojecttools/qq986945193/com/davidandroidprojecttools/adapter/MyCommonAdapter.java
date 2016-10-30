package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app.ViewHolder;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TopListBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.TxApiAppleBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PicassoWithImageLoaderImageViewUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 一些listview所用的adapter item
 */
public class MyCommonAdapter extends MyAdapter_CommonAdapter<TopListBean.TngouBean> {
    private List<TopListBean.TngouBean> lists = new ArrayList<>();

    public MyCommonAdapter(Context context, List<TopListBean.TngouBean> datas) {
        super(context, datas);
        this.lists.clear();
        this.lists.addAll(datas);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getHolder(mContext, convertView, parent,
                R.layout.item_listview_introduce, position);
        TopListBean.TngouBean bean = mDatas.get(position);

        TextView tv_introduce_name;
        ImageView iv_introduce_img;

        tv_introduce_name = holder.getView(R.id.tv_introduce_name);
        iv_introduce_img = holder.getView(R.id.iv_introduce_img);


        tv_introduce_name.setText(bean.getTitle());
//        PicassoWithImageLoaderImageViewUtils.withImageView(mContext, bean.getImg(), iv_introduce_img);
        PicassoWithImageLoaderImageViewUtils.displayImage(bean.getImg(), iv_introduce_img);
        return holder.getmConvertView();
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.ZhiHuBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.PicassoWithImageLoaderImageViewUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * ListView中的Adapter类
 */
public class ListViewIntroduceAdapter extends BaseAdapter {
    private List<ZhiHuBean.StoriesBean> lists = new ArrayList<>();
    private Context mContext;
    private LayoutInflater layoutInflater;


    public ListViewIntroduceAdapter(List<ZhiHuBean.StoriesBean> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 这里可以直接用View.引用布局,也可以不用ViewHolder类，当然效果都不好。最好的莫过于最下面的
         *
         * 这里引用了xutils的注解，当然，你可以不用。xutils注解相比之下不是很完美，不过我这里为了简单，直接用了。
         */
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_listview_introduce, null);
            holder = new ViewHolder();
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ZhiHuBean.StoriesBean bean = lists.get(position);
        holder.tv_introduce_name.setText(bean.getTitle());
        if (bean.getImages() != null && bean.getImages().size() != 0) {
            PicassoWithImageLoaderImageViewUtils.displayImage(bean.getImages().get(0), holder.iv_introduce_img);
        }
        return convertView;
    }

    protected class ViewHolder {
        @ViewInject(R.id.tv_introduce_name)
        private TextView tv_introduce_name;
        @ViewInject(R.id.iv_introduce_img)
        private ImageView iv_introduce_img;
    }
}

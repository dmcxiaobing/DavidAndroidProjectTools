package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.autobanner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;

/*
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

public class CBPageAdapter<T> extends RecyclingPagerAdapter{
    protected List<T> mDatas;
    protected CBViewHolderCreator holderCreator;
    private View.OnClickListener onItemClickListener;

    public CBPageAdapter(CBViewHolderCreator holderCreator,List<T> datas) {
        this.holderCreator = holderCreator;
        this.mDatas = datas;
    }

    @Override public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        if (view == null) {
            holder = (Holder) holderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.cb_item_tag,holder);
        } else {
            holder = (Holder<T>) view.getTag(R.id.cb_item_tag);
        }
        if(onItemClickListener != null) view.setOnClickListener(onItemClickListener);
        if(mDatas!=null&&!mDatas.isEmpty())
        holder.UpdateUI(container.getContext(), position, mDatas.get(position));
        return view;
    }

    @Override public int getCount() {
        if(mDatas==null)return 0;
        return mDatas.size();
    }

    /**
     * @param <T> 任何你指定的对象
     */
    public interface Holder<T>{
        View createView(Context context);
        void UpdateUI(Context context, int position, T data);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

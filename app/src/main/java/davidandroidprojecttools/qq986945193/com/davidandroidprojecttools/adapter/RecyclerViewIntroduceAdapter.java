package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;


/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * RecycleView的详解adapter
 */
public class RecyclerViewIntroduceAdapter extends RecyclerView.Adapter<RecyclerViewIntroduceAdapter.ViewHolder> {

    private List<String> datas;
    private Context mContext;

    public RecyclerViewIntroduceAdapter(Context mContext, List<String> data) {
        this.datas = data;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewIntroduceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_introduce, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.tv_introduce_name = (TextView) view.findViewById(R.id.tv_introduce_name);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_introduce_name.setText(datas.get(position));

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public TextView tv_introduce_name;

    }
}

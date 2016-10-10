package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyAdapter_CommonAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;

	public MyAdapter_CommonAdapter(Context context, List<T> datas) {
		this.mContext = context;
		this.mDatas = datas;
		mInflater = LayoutInflater.from(context);
	}

	public void onDateChange(List<T> list) {

		this.mDatas = list;
		this.notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mDatas == null){
			return 0;
		}
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView,
			ViewGroup parent);

}

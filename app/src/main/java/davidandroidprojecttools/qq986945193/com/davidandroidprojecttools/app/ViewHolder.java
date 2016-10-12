package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.app;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * @author ：程序员小冰
 *
 * @新浪微博 ：http://weibo.com/mcxiaobing
 *
 * @GitHub: https://github.com/QQ986945193
 *
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 *
 */
public class ViewHolder {

	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
					  int position) {
		this.mViews = new SparseArray<View>();
		this.mPosition = position;
		mConvertView=LayoutInflater.from(context).inflate(layoutId,parent,false);
		mConvertView.setTag(this);

	}

	public static ViewHolder getHolder(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {

		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);

		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition=position;
			return holder;
		}
	}
	
	public View getmConvertView() {
		return mConvertView;
	}
	
	public <T extends View > T getView(int viewId){
		View view=mViews.get(viewId);
		if (view==null) {
			view=mConvertView.findViewById(viewId);
			mViews.put(viewId, view);	
		}
		return (T) view;
		
	}
	
	
	
}

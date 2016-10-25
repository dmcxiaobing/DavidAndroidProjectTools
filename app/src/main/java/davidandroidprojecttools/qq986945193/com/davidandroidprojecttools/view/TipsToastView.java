package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;


/**
 * 自定义提示Toast
 */
public class TipsToastView extends Toast {

    public TipsToastView(Context context) {
        super(context);
    }

    public static TipsToastView makeText(Context context, CharSequence text,
                                     int duration) {
        TipsToastView result = new TipsToastView(context);

        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.view_tips, null);
        TextView tv = (TextView) v.findViewById(R.id.tips_msg);
        tv.setText(text);

        result.setView(v);
        // setGravity方法用于设置位置，此处为垂直居中
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);

        return result;
    }

    public static TipsToastView makeText(Context context, int resId, int duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId),
                duration);
    }

    public void setIcon(int iconResId) {
        if (getView() == null) {
            throw new RuntimeException(
                    "This Toast was not created with Toast.makeText()");
        }
        ImageView iv = (ImageView) getView().findViewById(R.id.tips_icon);
        if (iv == null) {
            throw new RuntimeException(
                    "This Toast was not created with Toast.makeText()");
        }
        iv.setImageResource(iconResId);
    }

    @Override
    public void setText(CharSequence s) {
        if (getView() == null) {
            throw new RuntimeException(
                    "This Toast was not created with Toast.makeText()");
        }
        TextView tv = (TextView) getView().findViewById(R.id.tips_msg);
        if (tv == null) {
            throw new RuntimeException(
                    "This Toast was not created with Toast.makeText()");
        }
        tv.setText(s);
    }
}

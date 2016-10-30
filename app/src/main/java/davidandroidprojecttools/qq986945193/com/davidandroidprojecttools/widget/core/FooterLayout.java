package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.widget.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;


/**
 * @author ：程序员小冰
 *
 * @新浪微博 ：http://weibo.com/mcxiaobing
 *
 * @GitHub: https://github.com/QQ986945193
 *
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 *
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 *
 */
public class FooterLayout extends FrameLayout {
    private Context context;
    public FooterLayout(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public FooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public FooterLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init();
    }

    public void init(){
        LayoutInflater.from(context).inflate(R.layout.auto_load_more_footer, this);
    }
}

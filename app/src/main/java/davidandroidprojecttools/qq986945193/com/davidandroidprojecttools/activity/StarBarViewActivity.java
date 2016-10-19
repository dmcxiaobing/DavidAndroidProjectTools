package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.StarBarView;

/**
 * 封装显示星星个数 常用评论数
 */
public class StarBarViewActivity extends BaseActivity {
    private StarBarView sbv_starbar;
    private Button btn_show_num_star;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start_bar_view);
        sbv_starbar = (StarBarView) findViewById(R.id.sbv_starbar);
        btn_show_num_star = (Button) findViewById(R.id.btn_show_num_star);
    }

    @Override
    protected void initData() {

        //拿到当前星星数量
        sbv_starbar.getStarRating();

    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        btn_show_num_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext,(int)sbv_starbar.getStarRating()+"", Toast.LENGTH_SHORT);
            }
        });
    }
}

package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.MarqueeView;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 公告广告通知显示滚动，类似淘宝等头条滚动效果
 */
public class MarqueeActivity extends BaseActivity {
    private MarqueeView marqueeView;
    private MarqueeView marqueeView1;
    private MarqueeView marqueeView3;
    private MarqueeView marqueeView2;
    private MarqueeView marqueeView4;
    private String notice = "@author ：程序员小冰";


    /**
     * xml属性 Attribute 属性	Description 描述
     * mvAnimDuration	一行文字动画执行时间
     * mvInterval	两行文字翻页时间间隔
     * mvTextSize	文字大小
     * mvTextColor	文字颜色
     * mvGravity	文字位置:left、center、right
     * mvSingleLine	单行设置
     */
    @Override
    protected void initView() {
        setContentView(R.layout.activity_marquee);
        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);
        marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);


    }

    @Override
    protected void initData() {
        /**
         * 设置一个集合
         */
        List<String> info = new ArrayList<>();
        info.add("1. @author ：程序员小冰");
        info.add("2. weibo.com/mcxiaobing");
        info.add("3. github.com/QQ986945193");
        info.add("4. CSDN：程序员小冰");
        info.add("5. 知乎：Mc小冰");
        info.add("6. 微信公众号：程序员小冰");
        marqueeView.startWithList(info);

        /**
         * 若是设置字符串 String notice = "心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！";
         * marqueeView.startWithText(notice);
         */

        marqueeView1.startWithText(notice);
        marqueeView2.startWithText(notice);
        marqueeView3.startWithText(notice);
        marqueeView4.startWithText(notice);
    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), String.valueOf(marqueeView.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), textView.getText() + "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

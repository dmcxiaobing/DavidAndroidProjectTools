package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.UpMarqueeAniView;

/**
 * 仿淘宝,百度外卖,饿了么等头条滚动效果
 */
public class UpMarqueeActivity extends BaseActivity {

    private UpMarqueeAniView up_view_marques;
    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_up_marquee);
        up_view_marques = (UpMarqueeAniView) findViewById(R.id.up_view_marques);
    }

    @Override
    protected void initData() {
        data.add("@author ：程序员小冰");
        data.add("http://weibo.com/mcxiaobing");
        data.add("weibo.com/mcxiaobing");
        data.add("https://github.com/QQ986945193");
        data.add("@CSDN博客: 程序员小冰");
        data.add("My name is David 程序员小冰");
    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        setView();
        up_view_marques.setViews(views);
        /**
         * 设置item_view的监听
         */
        up_view_marques.setOnItemClickListener(new UpMarqueeAniView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(mContext, "你点击了第几个items" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     * http://blog.csdn.net/qq_21376985
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_up_marquee_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }

}

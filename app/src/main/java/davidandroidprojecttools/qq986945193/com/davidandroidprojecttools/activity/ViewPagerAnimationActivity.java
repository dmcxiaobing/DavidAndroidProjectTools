package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.constant.Urls;

/**
 * 利用开源库实现viewpager的轮播效果展示
 */
public class ViewPagerAnimationActivity extends BaseActivity {


    @BindView(R.id.slider)
    SliderLayout slider;
    private List<String> imgeLists = new ArrayList<>();
    private List<String> datasLists = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_viewpager_animation);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        imgeLists.add(Urls.VIEWPAGE_BANNERIMAGE_ONE);
        imgeLists.add(Urls.VIEWPAGE_BANNERIMAGE_TWO);
        imgeLists.add(Urls.VIEWPAGE_BANNERIMAGE_THREE);

        for (int i = 0; i < 3; i++) {
            datasLists.add("这里有十多种效果,具体实现方式看源代码" + i + "个");
        }

        for (int i = 0; i < 3; i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    /*设置文字,可以不用设置，我这里设置一下*/
                    .description(datasLists.get(i))
                    /*设置图片*/
                    .image(imgeLists.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            slider.addSlider(textSliderView);
        }

           /*这里设置滚动的效果。有十五中效果进行展示，进行SliderLayout.Transformer.调用即可*/
        slider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
            /*这里可以设置不同样式的*/
//            slider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        /*这里设置tab圆点的位置*/
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        slider.setDuration(3000);

    }


    @Override
    protected void onStop() {
        slider.stopAutoCycle();
        super.onStop();
    }
}

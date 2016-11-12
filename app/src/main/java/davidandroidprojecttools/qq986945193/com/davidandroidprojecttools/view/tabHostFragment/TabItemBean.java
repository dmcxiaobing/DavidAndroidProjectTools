package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.view.tabHostFragment;

import android.graphics.drawable.Drawable;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class TabItemBean {

    private String title;
    private Drawable drawable;
    private int imageRes;

    public TabItemBean(String title, Drawable drawable, int imageRes) {
        this.title = title;
        this.drawable = drawable;
        this.imageRes = imageRes;
    }

    public TabItemBean(String title, int imageRes) {
        this.title = title;
        this.drawable = null;
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}

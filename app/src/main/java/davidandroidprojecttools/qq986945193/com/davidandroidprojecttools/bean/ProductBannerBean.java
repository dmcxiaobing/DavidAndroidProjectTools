package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class ProductBannerBean implements Parcelable {

    String pic;

    public ProductBannerBean(String pic) {
        super();
        this.pic = pic;
    }

    protected ProductBannerBean(Parcel in) {
        pic = in.readString();
    }

    public static final Creator<ProductBannerBean> CREATOR = new Creator<ProductBannerBean>() {
        @Override
        public ProductBannerBean createFromParcel(Parcel in) {
            return new ProductBannerBean(in);
        }

        @Override
        public ProductBannerBean[] newArray(int size) {
            return new ProductBannerBean[size];
        }
    };

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pic);
    }
}
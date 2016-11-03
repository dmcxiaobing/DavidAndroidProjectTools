package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class ProvinceModelBean {
    private String name;
    private List<CityModelBean> cityList;


    public List<List<String>> getDisNameList() {
        List<List<String>> list = new ArrayList<>();
        for (CityModelBean cityModelBean : cityList) {
            list.add(cityModelBean.getDisList());
        }
        return list;
    }

    public List<String> getCityNameList() {
        List<String> list = new ArrayList<>();
        for (CityModelBean cityModelBean : cityList) {
            list.add(cityModelBean.getPickerViewText());
        }
        return list;
    }

    public ProvinceModelBean() {
        super();
    }

    public ProvinceModelBean(String name, List<CityModelBean> cityList) {
        super();
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModelBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModelBean> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceModel [name=" + name + ", cityList=" + cityList + "]";
    }

    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return name;
    }
}

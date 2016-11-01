package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.CityModelBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.DistrictModelBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.ProvinceModelBean;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
public class XmlParserHandlerUtils extends DefaultHandler {

    /**
     * 存储所有的解析对象
     */
    private List<ProvinceModelBean> provinceList = new ArrayList<ProvinceModelBean>();

    public XmlParserHandlerUtils() {

    }

    public List<ProvinceModelBean> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
    }

    ProvinceModelBean provinceModelBean = new ProvinceModelBean();
    CityModelBean cityModelBean = new CityModelBean();
    DistrictModelBean districtModelBean = new DistrictModelBean();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals("province")) {
            provinceModelBean = new ProvinceModelBean();
            provinceModelBean.setName(attributes.getValue(0));
            provinceModelBean.setCityList(new ArrayList<CityModelBean>());
        } else if (qName.equals("city")) {
            cityModelBean = new CityModelBean();
            cityModelBean.setName(attributes.getValue(0));
            cityModelBean.setDistrictList(new ArrayList<DistrictModelBean>());
        } else if (qName.equals("district")) {
            districtModelBean = new DistrictModelBean();
            districtModelBean.setName(attributes.getValue(0));
            districtModelBean.setZipcode(attributes.getValue(1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 遇到结束标记的时候，会调用这个方法
        if (qName.equals("district")) {
            cityModelBean.getDistrictList().add(districtModelBean);
        } else if (qName.equals("city")) {
            provinceModelBean.getCityList().add(cityModelBean);
        } else if (qName.equals("province")) {
            provinceList.add(provinceModelBean);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }

}

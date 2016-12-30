package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import java.util.List;

/**
 * 批量操作手机号的姓名和电话号码实体类
 */
public class NameNumberBean {

    private List<NumberNameBean> list;


    public static class NumberNameBean{

        public NumberNameBean(String number, String name) {
            this.number = number;
            this.name = name;
        }

        public NumberNameBean() {
        }

        private String number;
        private String name;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

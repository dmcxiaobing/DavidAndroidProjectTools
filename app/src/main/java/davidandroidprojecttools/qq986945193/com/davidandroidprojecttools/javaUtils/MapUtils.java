package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.javaUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 *
 * 类名：map存取数据的知识点
 */
public class MapUtils {

    public static void main(String[] args) {
        addData(map);
//		getMapVauleOneMethod(map);
//		getMapVauleTwoMethod(map);
        getMapVauleThreeMethod(map);

    }
    /**
     * 不会按照我们进行put的顺序输出
     */
//	private static Map<String, String> map = new HashMap<>();

    /**
     * 可以按照我们put的顺序进行存储map数据
     */
    private static Map<String, String> map = new LinkedHashMap<>();

    /**
     * 往map里面添加数据
     *
     * @param map
     */
    public static void addData(Map<String, String> map) {
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
    }

    /**
     * 取出map中的值 第一种方法 先取出所有键，再取出值
     */
    public static void getMapVauleOneMethod(Map<String, String> map) {
        if (map != null) {
            // 将map中的所有键去取出来，用迭代器进行读取
            Set set = map.keySet();
            if (set != null) {
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    // 取出单个的map键
                    String key = (String) iterator.next();
                    String value = map.get(key);
                    System.out.println("key = " + key + "value" + value);
                }
            }

        }
    }

    /**
     * 取出map中的值 第二种方法
     */

    public static void getMapVauleTwoMethod(Map<String, String> map) {
        if (map != null) {
            Set set = map.entrySet();
            if (set != null) {
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    System.out.println("key = " + key + "value" + value);
                }
            }

        }

    }

    /**
     * 取出map中的值 第三种方法
     */
    public static void getMapVauleThreeMethod(Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("key = " + key + "value" + value);
            }
        }
    }

}

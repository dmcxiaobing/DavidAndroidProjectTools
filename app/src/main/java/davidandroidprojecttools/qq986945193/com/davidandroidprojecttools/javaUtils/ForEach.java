package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.javaUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * 增强for循环的使用详解
 */
public class ForEach {
    /**
     * 增强for循环的语法：
     * <p/>
     * for(数据类型 自定义变量名:要循环的集合或者数组){
     * 这里面写所要便利的元素或者适当代码即可
     * }
     */


    public static void main(String args[]) {
        int arrs[] = {1,2,3,4,5,6,7,8,9,0};
        int sum = 0;
        for (int i : arrs){
            sum+=arrs[i];
            //打印出每个元素的值
            System.out.println(i);
        }
        //打印出此数组的和
        System.out.print(sum);
    }
}

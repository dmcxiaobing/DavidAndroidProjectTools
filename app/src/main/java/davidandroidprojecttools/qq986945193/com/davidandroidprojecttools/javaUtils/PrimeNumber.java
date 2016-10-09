package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.javaUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

/**
 * java基础知识点， 得出100-200之间的质数
 */
public class PrimeNumber {
    public static void main(String args[]) {
        //author：qq986945193
        for (int i = 100; i < 201; i++) {
            boolean flag = true;
            for (int j = 2; j < i - 1; j++) {
                if (i % j == 0) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println(i);
            }
        }
    }
}

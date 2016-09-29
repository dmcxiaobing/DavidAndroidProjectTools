package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.javaUtils;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * 类名：java中单例设计模式  恶汉式和懒汉式实现方法
 */
public class Singleton {
//
//    /**
//     * 恶汉式  加载类的时候比较慢，运行比较快
//     */
//
//    private static Singleton instance = new Singleton();
//
//    /**
//     * 1,定义私有的构造方法，禁止外部直接创建实例
//     * <p/>
//     * 2,内部自己创建好实例，私有属性(不建议在外部直接调用我们的成员变量)
//     * <p/>
//     * 3,创建一个方法，使外部可以得到此实例
//     */
//    private Singleton() {
//
//    }
//
//
//    public static Singleton getSingletonInstance() {
//        return instance;
//    }


    /**
     * 懒汉式 加载类的时候比较快，运行时比较慢
     * <p/>
     * 1,创建私有构造方法，禁止外部直接创建实例
     * <p/>
     * 2,创建私有变量实例化对象，私有属性(不建议在外部直接调用我们的成员变量)
     * <p/>
     * 3,创建方法，使外部可以调用我们的私有对象实例
     */

    private static Singleton instance = null;

    private Singleton() {

    }

    /**
     * 这样也是可以的，不过，如果涉及到多线程，最好加上一个同步锁(synchronized)，可以
     * <p/>
     * 直接在方法上添加 但是每次都要去进行同步，显然不是最好的。
     *
     * 最好的当然是下面的，在方法中进行加上synchronized
     * @return
     */
    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {

                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


//    public static synchronized Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }


}

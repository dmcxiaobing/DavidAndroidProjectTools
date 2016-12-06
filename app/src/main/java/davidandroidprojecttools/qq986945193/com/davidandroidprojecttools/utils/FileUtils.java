package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 在手机中创建文件并写入内容的工具类
 */
public class FileUtils {
    //    String filenameTemp = SDCardUtils.GET + "/david" + ".txt";

    //创建文件夹及文件
    public static void CreateText(String filenameTemp, Context mContext, String content) throws IOException {
        File file = new File(filenameTemp);
        if (!file.exists()) {
            try {
                //按照指定的路径创建文件夹
                file.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        File dir = new File(filenameTemp + "create_file.txt");
        Writer writer = null;
        if (!dir.exists()) {
            try {
                //在指定的文件夹中创建文件
                dir.createNewFile();
                ToastUtils.show(mContext, "创建文件成功", Toast.LENGTH_SHORT);
                /**
                 * 这里也可以直接写入
                 */
//                FileOutputStream writerStream = new FileOutputStream(filenameTemp + "create_file.txt", true);
//                BufferedWriter oWriter = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
            } catch (Exception e) {

            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

    }

    //向已创建的文件中写入数据
    public static void print(String str, String filenameTemp, Context mContext) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        String datetime = "";
        try {
//            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " "
//                    + "hh:mm:ss");
//
//            datetime = tempDate.format(new java.util.Date()).toString();

            /**
             * 这两行代码也是直接写入，但是没有设置写入的编码格式。直接用的系统默认的编码
             */
            //写入的那个文件和是否进行追加
//            fw = new FileWriter(filenameTemp + "create_file.txt", true);//
            // 创建FileWriter对象，用来写入字符流
//            bw = new BufferedWriter(fw); // 将缓冲对文件的输出


            //这里我们可以设置我们写入的编码
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filenameTemp + "create_file.txt", true), "UTF-8"));
//            String myreadline = datetime + "[]" + str;

            String myreadline = str;
            bw.write(myreadline + "\n"); // 写入文件

            bw.newLine();
            bw.flush(); // 刷新该流的缓冲

            if (bw != null) {
                bw.close();
            }
            if (fw != null) {

                fw.close();
            }
            ToastUtils.show(mContext, "写入内容成功", Toast.LENGTH_SHORT);

        } catch (IOException e) {
            LogUtil.E("抛出了异常。。。");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();

                }
                if (fw != null) {

                    fw.close();
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
            }
        }
    }

    /**
     * 删除文件夹下的所有文件
     *
     * @param root 文件夹路径
     */
    public static void deleteAllFiles(File root) {

        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();

                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {

                        }
                    }
                }
            }

        } else {
        }
    }

    /**
     * 递归找出某文件夹下面所有的的文件及文件夹(子文件及文件夹)
     */
    public static void listFile(File file) {
        File[] fileArray = file.listFiles();
        for (File f : fileArray) {
            if (f.isDirectory()) { // 是文件夹
                System.out.println("文件夹：" + f.getName());
                listFile(f); //递归 查找文件夹下面的文件及文件夹
            } else {
                System.out.println("***文件***：" + f.getName());
            }
        }
    }


    public static void readContent(Context mContext, String filePath) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
//            File urlFile = new File("/sdcard/david.txt");
            File urlFile = new File(filePath);
            isr = new InputStreamReader(new FileInputStream(urlFile), "UTF-8");
            br = new BufferedReader(isr);
            String str = "";
            String mimeTypeLine = null;
            while ((mimeTypeLine = br.readLine()) != null) {
                str = str + mimeTypeLine;
            }
            ToastUtils.show(mContext, str, Toast.LENGTH_SHORT);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.E("读取异常了");
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }


}

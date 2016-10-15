package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;


/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * SD卡工具类，包含SD卡状态、路径、容量大小
 */
public class SDCardUtils {

    private SDCardUtils() {
    }

    /**
     * 判断SD卡是否可用
     *
     * @return ture：可用；false：不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return SD卡存在返回正常路径；SD卡不存在返回""
     */
    public static String getSDCradPath() {
        if (isSDCardEnable()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        } else {
            return "";
        }
    }

    /**
     * 获取SD卡路径
     *
     * @return SD卡存在返回正常路径；SD卡不存在返回null
     */
    public static File getSDCardFile() {
        if (isSDCardEnable()) {
            return Environment.getExternalStorageDirectory();
        } else {
            return null;
        }
    }

    /**
     * 获取SD卡DownloadCache路径
     *
     * @return SD卡存在返回正常路径；SD卡不存在返回""
     */
    public static String getSDCardDownloadCachePath() {
        if (isSDCardEnable()) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath() + File.separator;
        } else {
            return "";
        }
    }

    /**
     * 获取SD卡DownloadCache路径
     *
     * @return SD卡存在返回正常路径；SD卡不存在返回null
     */
    public static File getSDCardDownloadCacheFile() {
        if (isSDCardEnable()) {
            return Environment.getDownloadCacheDirectory();
        } else {
            return null;
        }
    }

    /**
     * 获取系统存储路径
     *
     * @return SD卡存在返回正常路径；SD卡不存在返回""
     */
    public static String getSDCardRootPath() {
        if (isSDCardEnable()) {
            return Environment.getRootDirectory().getAbsolutePath() + File.separator;
        } else {
            return "";
        }
    }

    /**
     * 获取系统存储路径
     *
     * @return SD卡存在返回正常路径；SD卡不存在返回null
     */
    public static File getSDCardRootFile() {
        if (isSDCardEnable()) {
            return Environment.getRootDirectory();
        } else {
            return null;
        }
    }

    /**
     * 获取应用程序的/data/data目录
     *
     * @param context
     * @return
     */
    public static String getDataFilePath(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator;
    }

    /**
     * /data/data/PackageName/cache的路径
     *
     * @param context
     * @return
     */
    public static String getDataCachePath(Context context) {
        return context.getCacheDir().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡大小
     *
     * @return SD卡存在返回大小；SD卡不存在返回-1
     */
    @SuppressLint("NewApi")
    public static long getSDCardSize() {
        if (isSDCardEnable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
            if (android.os.Build.VERSION.SDK_INT < 18) {
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getBlockCount();
                return blockCount * blockSize;
            } else {
                long blockSize = statFs.getBlockSizeLong();
                long blockCount = statFs.getBlockCountLong();
                return blockCount * blockSize;
            }
        }
        return -1;
    }

    /**
     * 获取SD卡大小
     *
     * @return SD卡存在返回大小；SD卡不存在返回-1
     */
    @SuppressLint("NewApi")
    public static long getSDCardAvailableSize() {
        if (isSDCardEnable()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
            if (android.os.Build.VERSION.SDK_INT < 18) {
                int blockSize = statFs.getBlockSize();
                int blockCount = statFs.getAvailableBlocks();
                return blockCount * blockSize;
            } else {
                long blockSize = statFs.getBlockSizeLong();
                long blockCount = statFs.getAvailableBlocksLong();
                return blockCount * blockSize;
            }
        }
        return -1;
    }

    /**
     * 获得手机内存总大小
     *
     * @return
     */
    @SuppressLint("NewApi")
    public long getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        if (android.os.Build.VERSION.SDK_INT < 18) {
            int blockSize = statFs.getBlockSize();
            int blockCount = statFs.getAvailableBlocks();
            return blockCount * blockSize;
        } else {
            long blockSize = statFs.getBlockSizeLong();
            long blockCount = statFs.getAvailableBlocksLong();
            return blockCount * blockSize;
        }
    }

    /**
     * 获得手机可用内存
     *
     * @return
     */
    @SuppressLint("NewApi")
    public long getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        if (android.os.Build.VERSION.SDK_INT < 18) {
            int blockSize = statFs.getBlockSize();
            int blockCount = statFs.getAvailableBlocks();
            return blockCount * blockSize;
        } else {
            long blockSize = statFs.getBlockSizeLong();
            long blockCount = statFs.getAvailableBlocksLong();
            return blockCount * blockSize;
        }
    }
}

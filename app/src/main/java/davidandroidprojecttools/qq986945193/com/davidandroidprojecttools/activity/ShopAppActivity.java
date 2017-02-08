package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;


/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.AppUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * 关于电商的一些简单常用功能
 */
public class ShopAppActivity extends BaseActivity {
    private Button btnOpentaobaoOne;
    private Button btnOpentaobaoTwo;
    private Button btn_opentaobao_goodsdetail;
    private Button btn_opentaobao_address;
    public static final String APP_PACKAGENAME = "com.taobao.taobao";


    @Override
    protected void initView() {
        setContentView(R.layout.activity_shop_app);
        btnOpentaobaoOne = (Button) findViewById(R.id.btn_opentaobao_one);
        btnOpentaobaoTwo = (Button) findViewById(R.id.btn_opentaobao_two);
        btn_opentaobao_goodsdetail = (Button) findViewById(R.id.btn_opentaobao_goodsdetail);
        btn_opentaobao_address = (Button) findViewById(R.id.btn_opentaobao_address);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setOnclickListener() {
        /**
         * 打开手机淘宝第一种方式
         */
        btnOpentaobaoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先判断是否已经安装了淘宝,然后在进行启动
                if (AppUtils.isAvilible(ShopAppActivity.this, APP_PACKAGENAME)) {
                    PackageManager packageManager = getPackageManager();
                    Intent intent = new Intent();
                    intent = packageManager.getLaunchIntentForPackage(APP_PACKAGENAME);
                    startActivity(intent);

                } else {
                    ToastUtils.show(ShopAppActivity.this, "对不起您还没有安装淘宝", Toast.LENGTH_SHORT);
                }
            }
        });
        /**
         * 打开手机淘宝第二种方式
         */
        btnOpentaobaoTwo.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    //首先判断是否已经安装了淘宝,然后在进行启动
                                                    if (AppUtils.isAvilible(ShopAppActivity.this, APP_PACKAGENAME)) {
                                                        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
                                                        PackageInfo packageinfo = null;
                                                        try {
                                                            packageinfo = getPackageManager().getPackageInfo(APP_PACKAGENAME, 0);
                                                        } catch (PackageManager.NameNotFoundException e) {
                                                            e.printStackTrace();
                                                        }
                                                        if (packageinfo == null) {
                                                            return;
                                                        }

                                                        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
                                                        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
                                                        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                                                        resolveIntent.setPackage(packageinfo.packageName);

                                                        // 通过getPackageManager()的queryIntentActivities方法遍历
                                                        List<ResolveInfo> resolveinfoList = getPackageManager()
                                                                .queryIntentActivities(resolveIntent, 0);

                                                        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
                                                        if (resolveinfo != null) {
                                                            // packagename = 参数packname
                                                            String packageName = resolveinfo.activityInfo.packageName;
                                                            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
                                                            String className = resolveinfo.activityInfo.name;
                                                            // LAUNCHER Intent
                                                            Intent intent = new Intent(Intent.ACTION_MAIN);
                                                            intent.addCategory(Intent.CATEGORY_LAUNCHER);

                                                            // 设置ComponentName参数1:packagename参数2:MainActivity路径
                                                            ComponentName cn = new ComponentName(packageName, className);

                                                            intent.setComponent(cn);
                                                            startActivity(intent);

                                                        } else {
                                                            ToastUtils.show(ShopAppActivity.this, "对不起您还没有安装淘宝", Toast.LENGTH_SHORT);
                                                        }
                                                    }
                                                }
                                            }
        );
        /**
         * 打开手机淘宝商品详情
         */
        btn_opentaobao_goodsdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先判断是否已经安装了淘宝,然后在进行启动
                if (AppUtils.isAvilible(ShopAppActivity.this, APP_PACKAGENAME)) {
                    Intent intent = new Intent();
                    intent.setAction("Android.intent.action.VIEW");
                    Uri uri = Uri.parse("https://detail.tmall.com/item.htm?spm=a1z10.3-b.w4011-5088110281.136.AyTKD0&id=541525559735&rn=b0cf33aafad2069cac28506ce4fc96ba&abbucket=3&skuId=3427962535329"); // 商品地址
                    intent.setData(uri);
                    intent.setClassName(APP_PACKAGENAME, "com.taobao.tao.detail.activity.DetailActivity");
                    startActivity(intent);

                } else {
                    ToastUtils.show(ShopAppActivity.this, "对不起您还没有安装淘宝", Toast.LENGTH_SHORT);
                }
            }
        });

        /**
         * 通过淘口令打开淘宝地址
         */
        btn_opentaobao_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先判断是否已经安装了淘宝,然后在进行启动
                if (AppUtils.isAvilible(ShopAppActivity.this, APP_PACKAGENAME)) {
                    PackageManager packageManager = getPackageManager();
                    Intent intent = new Intent();
                    intent = packageManager.getLaunchIntentForPackage(APP_PACKAGENAME);
                    startActivity(intent);
                    ClipboardManager clip = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    clip.setText("￥天猫淘宝专属无门槛优惠券列表页！私密优惠券，抢到就是赚到#22202601129#￥"); // 复制
//                    clip.getText();//粘贴
                } else {
                    ToastUtils.show(ShopAppActivity.this, "对不起您还没有安装淘宝", Toast.LENGTH_SHORT);
                }

            }
        });
    }
}

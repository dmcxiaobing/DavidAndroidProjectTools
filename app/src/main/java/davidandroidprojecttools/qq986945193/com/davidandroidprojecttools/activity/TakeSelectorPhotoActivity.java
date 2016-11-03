package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.SystemgcBitmapUtils;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.UploadPictureUtil;

/**
 * 拍照以及选择相册手机照片
 */
public class TakeSelectorPhotoActivity extends BaseActivity {

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.btn_selector)
    Button btnSelector;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_take_selector_photo);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        btnSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDiaLog();
            }
        });
    }

    /**
     * 选择提示对话框
     */
    private void ShowDiaLog() {
        new AlertDialog.Builder(this)

                .setItems(new String[]{"相册", "相机"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    case 0:
                                        // 打开图库
                                        getGallery();

                                        break;
                                    case 1:
                                        // 拍照
                                        takePhoto();
                                        break;


                                }
                            }
                        })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }


    private String pictureString = "";

    private Bitmap bitmapTakePhoto;
    /**
     * 图片路径
     */
    public String mCurrentPhotoPath;

    /**
     * 处理拍照、图库返回的信息
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 如果是直接从相册获取
            case 101:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        // 获得图片的uri
                        Uri uri = data.getData();

                        LogUtil.E("图片的uri = " + uri);

                        mCurrentPhotoPath = UploadPictureUtil.getRealPathFromURI(
                                uri, this);
                        LogUtil.E("图片的路径mCurrentPhotoPath = " + mCurrentPhotoPath);

                        //已经经过处理角度的图片
                        pictureString = UploadPictureUtil.bitmapToString(
                                mCurrentPhotoPath, 75, 100, 100);

                        LogUtil.E("pictureString = " + pictureString);

                        bitmapTakePhoto = UploadPictureUtil.convertStringToIcon(pictureString);
                        ivPhoto.setImageBitmap(bitmapTakePhoto);

                        //uploadImg(pictureString);
                    } catch (Exception e) {
                        Toast.makeText(this, "选择图片文件失败", Toast.LENGTH_LONG).show();
                        LogUtil.E(e.getMessage());
                    }
                }
                break;

            // 如果是调用相机拍照时
            case 102:
                if (resultCode == Activity.RESULT_OK) {
                    // 通过图片路径获取编码后的字符串、已经经过处理角度的图片
                    pictureString = UploadPictureUtil.bitmapToString(
                            mCurrentPhotoPath, 75, 100, 100);
                    // 添加到图库,这样可以在手机的图库程序中看到程序拍摄的照片
                    UploadPictureUtil.galleryAddPic(this, mCurrentPhotoPath);

                    bitmapTakePhoto = UploadPictureUtil.convertStringToIcon(pictureString);
                    ivPhoto.setImageBitmap(bitmapTakePhoto);

                    //uploadImg(pictureString);
                    // mImageView.setImageBitmap(UploadPictureUtil.getSmallBitmap(mCurrentPhotoPath),1024,1024);
                } else {
                    // // 取消照相后，删除已经创建的临时文件。
                    // UploadPictureUtil.deleteTempFile(mCurrentPhotoPath);
                }
                break;

        }
    }


    /**
     * 拍照
     */
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            // 指定存放拍摄照片的位置
            File f = UploadPictureUtil.createImageFile();
            // 获取图片绝对路径 getAbsolutePath()返回抽象路径名的绝对路径名字符串
            mCurrentPhotoPath = f.getAbsolutePath();
            takePictureIntent
                    .putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            startActivityForResult(takePictureIntent, 102);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 打开图库
     */
    private void getGallery() {

        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        // intent.setType("image/*");
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, 101);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SystemgcBitmapUtils.SystemGcBitmap(bitmapTakePhoto);
    }


}

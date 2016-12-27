package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.activity;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.app.ProgressDialog;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.R;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean.NameNumberBean;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.LogUtil;
import davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils.ToastUtils;

/**
 * 关于查看联系人，以及添加与删除的功能
 */
public class PhoneBookActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView() {
        setContentView(R
                .layout.activity_phone_book);
    }

    @Override
    protected void initData() {

    }


    private ProgressDialog progressDialog;


    private List<String> listNames = new ArrayList<>();
    private List<NameNumberBean.NumberNameBean> listBeans = new ArrayList<>();


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 添加联系人到通讯录   批量添加姓名和手机号到通讯录
             */
            case R.id.addAddressList:
                dialog("正在添加联系人,请稍等...");
                try {
                    addNumberName(listBeans);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(PhoneBookActivity.this, "添加失败,请重试");

                }
                break;

            /**
             * 清空联系人从通讯录  从通讯录批量删除添加的手机号和姓名
             */
            case R.id.clearAddressList:
                dialog("正在清空...");
                try {
                    deleteNumber("namePhone");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(PhoneBookActivity.this, "清除失败,请重试");
                }
                break;


            /**
             *批量添加手机号到通讯录
             */
            case R.id.addAddressPhoneList:
                dialog("正在添加手机号,请稍等...");
                try {
                    addNumber();
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(PhoneBookActivity.this, "添加失败,请重试");

                }
                break;

            /**
             * 从通讯录批量删除添加的手机号
             */
            case R.id.clearAddressPhoneList:
                dialog("正在清空...");
                try {
                    deleteNumber("number");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(PhoneBookActivity.this, "清除失败,请重试");
                }
                break;

            /**
             * 查看联系人
             */
            case R.id.catAddressList:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.setData(Contacts.People.CONTENT_URI);
                startActivity(intent2);
                break;
        }

    }


    /**
     * 批量添加姓名和手机号到通讯录。
     */
    private void addNumberName(final List<NameNumberBean.NumberNameBean> listBeans) throws Exception {
        for (int i = 0; i < 3; i++) {
            listBeans.add(new NameNumberBean.NumberNameBean("986945193", "程序员小冰" + i));
        }
        LogUtil.E("listBeans.size()" + listBeans.size());
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < listBeans.size(); i++) {
                    ContentValues values = new ContentValues();
                    Uri uri = Uri
                            .parse("content://com.android.contacts/raw_contacts");
                    // ContentResolver resolver = mContext
                    // .getContentResolver();
                    // long contactid =
                    // ContentUris.parseId(resolver.insert(RawContacts.CONTENT_URI,
                    // values));
                    Uri rawcontacturi = getContentResolver().insert(
                            ContactsContract.RawContacts.CONTENT_URI, values);
                    long rawcontactid = ContentUris.parseId(rawcontacturi);
                    uri = Uri.parse("content://com.android.contacts/data");

                    // 添加姓名
                    values.put("raw_contact_id", rawcontactid);
                    values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/name");
                    if (listBeans.get(i).getName() == null || listBeans.get(i).getName().equals("")) {
                        values.put("data1", "程序员小冰");
                    } else {
                        values.put("data1", listBeans.get(i).getName());
                    }
                    getContentResolver().insert(
                            android.provider.ContactsContract.Data.CONTENT_URI,
                            values);
                    values.clear();

                    // 添加电话
                    values.put(ContactsContract.Data.RAW_CONTACT_ID, rawcontactid);
                    values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, listBeans.get(i).getNumber());
                    // 设置电话类型
//                    values.put(Phone.TYPE, Phone.TYPE_MOBILE);
                    values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_ISDN);
                    getContentResolver().insert(
                            android.provider.ContactsContract.Data.CONTENT_URI,
                            values);
                    values.clear();
                }
                hideDialog();
            }
        }).start();
    }

    private ContentValues values;

    /**
     * 批量添加手机号到通讯录。
     */
    private void addNumber() throws Exception {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    // 创建一个空的ContentValues
                    values = new ContentValues();
                    // 向rawcontent。content——uri执行一个空值插入
                    // 目的是获取系统返回的rawcontactid
                    Uri rawcontacturi = getContentResolver().insert(
                            ContactsContract.RawContacts.CONTENT_URI, values);
                    long rawcontactid = ContentUris.parseId(rawcontacturi);
                    values.clear();
                    values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawcontactid);
                    values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    // 设置联系人电话号码
                    String m = "000" + i;
                    // 150 0840 0000 其实这里就是添加手机号码
                    values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "1350840" + m);
                    // 设置电话类型
                    values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                    // 向联系人电话号码URI添加电话号码
                    getContentResolver().insert(
                            android.provider.ContactsContract.Data.CONTENT_URI,
                            values);

                }
                hideDialog();
            }
        }).start();
    }

    private List<String> numbers = new ArrayList<>();

    /**
     * 获取联系人
     */
    private void getPhoneNumber() {

        ContentResolver resolver = (PhoneBookActivity.this.getContentResolver());
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null); // 传入正确的uri

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                // 获取联系人手机号number
//                Number number = new Number();
                NameNumberBean.NumberNameBean number = new NameNumberBean.NumberNameBean();
                String phoneNumber = phoneCursor.getString(phoneCursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                //取得联系人名字
                int nameFieldColumnIndex = phoneCursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                String contact = phoneCursor.getString(nameFieldColumnIndex);
                if (TextUtils.isEmpty(phoneNumber)) {
                    continue;
                }
                number.setNumber(phoneNumber);
                numbers.add(number.getNumber());
                listNames.add(contact);
            }

        }
        if (phoneCursor != null) {
            phoneCursor.close();
        }

    }

    /**
     * 根据tpye判断是否操作手机号或者手机号和联系人
     */
    private void deleteNumber(final String typeValue) throws Exception {

        new Thread(new Runnable() {
            public void run() {
                getPhoneNumber();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.E("listNamesSIZE" + listNames.size());

                if (typeValue.equals("number")) {
                    for (int i = 0; i < numbers.size(); i++) {
                        delContact(numbers.get(i));
                    }
                } else if (typeValue.equals("namePhone")) {
                    for (int i = 0; i < listNames.size(); i++) {
                        try {
                            if (listNames.get(i).contains("程序员小冰")) {
                                LogUtil.E("listNames.get(i)" + listNames.get(i));
                                DeleteContactForName(PhoneBookActivity.this, listNames.get(i));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
//                progressDialog.cancel();
                hideDialog();

            }
        }).start();

    }


    /**
     * 根据手机号删除联系人
     */
    private void delContact(final String name) {
        Cursor cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Data.RAW_CONTACT_ID},
                ContactsContract.Contacts.DISPLAY_NAME + "=?",
                new String[]{name}, null);
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        if (cursor.moveToFirst()) {
            do {
                long Id = cursor.getLong(cursor
                        .getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
                ops.add(ContentProviderOperation
                        .newDelete(
                                ContentUris.withAppendedId(
                                        ContactsContract.RawContacts.CONTENT_URI, Id)).build());
                try {
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY,
                            ops);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * 根据姓名删除联系人
     *
     * @param context
     * @param name
     * @throws Exception
     */
    public void DeleteContactForName(Context context, final String name) throws Exception {
        // 根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID},
                "display_name=?", new String[]{name}, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            // 根据id删除data中的相应数据
            resolver.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
        }
        if (cursor != null) {
            cursor.close();
        }
    }


    private void dialog(String mm) {
        progressDialog = new ProgressDialog((PhoneBookActivity.this));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(mm);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * 隐藏progressDialog
     */
    private void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}

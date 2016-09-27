package com.example.administrator.story;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import util.SdkUtils;

/**
 * Created by Administrator on 2016/8/18.
 */
public class Register extends AppCompatActivity {
    private static final String TAG ="Register" ;
    private ImageView headImg;
    private static int REQUEST_CODE = 1;
    private byte[] mContent;
    private Bitmap mBitmap;
    private EditText account,name,password;
    private  static final String  url_regist = "http://139.129.19.51/story/index.php/home/Interface/regist";
    private String iconPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        headImg = (ImageView) findViewById(R.id.register_icon);
        account = (EditText) findViewById(R.id.register_edit_userAccount);
        name = (EditText) findViewById(R.id.register_edit_userName);
        password = (EditText) findViewById(R.id.register_edit_userPassword);


    }

    /**
     * 点击添加图片
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContentResolver resolver = getContentResolver();
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            try {
                Uri uri = data.getData();
                // 将图片内容解析成字节数组
                mContent = SdkUtils.readStream(resolver.openInputStream(Uri
                        .parse(uri.toString())));
                // 将字节数组转换为ImageView可调用的Bitmap对象
                mBitmap = SdkUtils.getPicFromBytes(mContent, null);
                // //把得到的图片绑定在控件上显示
                headImg.setImageBitmap(mBitmap);

                String[] proj = {MediaStore.Images.Media.DATA};
                //好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = managedQuery(uri, proj, null, null, null);
                //获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                //将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                //最后根据索引值获取图片路径
                 iconPath =  cursor.getString(column_index);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //点击后退
    public void back(View view) {
        onBackPressed();
    }

    /**
     * 这是判断帐号，姓名，密码不为空的情况
     */


    /**
     * 点击注册的事件
     * @param view
     */
    public void clickToRegister(View view) {
        if (TextUtils.isEmpty(account.getText())){
            account.requestFocus();
            account.setError("帐号不能为空");

        }else if (TextUtils.isEmpty(name.getText())){
            account.requestFocus();
            name.setError("昵称不能为空");
        }else if (TextUtils.isEmpty(password.getText())){
            account.requestFocus();
            password.setError("密码不能为空");
        }
        else {
            OkHttpUtils
                    .post()
                    .url(url_regist) //提交地址
                   // .addFile("portrait","小狗.jpg",new File(iconPath))
                    .addParams("nikename",name.getText().toString()) //提交注册的昵称
                    .addParams("username",account.getText().toString()) //提交注册的帐号
                    .addParams("password",password.getText().toString()) //提交密码
                    .build() //创建
                    .execute(new StringCallback() {
                        @Override
                        public void onError(okhttp3.Call call, Exception e, int id) {
                            Toast.makeText(Register.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e(TAG,response);
                            JSONObject json = null;
                            try {
                                json = new JSONObject(response);
                                int result = json.optInt("result");
                                if (result==1){
                                    startActivity(new Intent(Register.this,Load.class));
                                }
                                else {
                                    Toast.makeText(Register.this, "帐号或用户名存在", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                           // startActivity(new Intent(Register.this,Load.class));

                        }
                    });
            account.requestFocus();

        }
    }


}
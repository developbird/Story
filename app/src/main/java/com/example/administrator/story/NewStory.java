package com.example.administrator.story;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import util.SdkUtils;

/**
 * Created by Administrator on 2016/8/20.
 */
public class NewStory extends AppCompatActivity {
    private static final String TAG = "NewStory";
    private static int REQUEST_CODE = 2;
    private byte[] mContent;
    private Bitmap mBitmap;

    private EditText editText;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_story);
        editText = (EditText) findViewById(R.id.new_story_EditText);
    }



    //这是提交的按钮
    public void new_story_submit(View view) {
        Log.e(TAG,editText.getText().toString());
        SharedPreferences perfer = getSharedPreferences("load_data", MODE_PRIVATE);
        String uid = perfer.getString("uid", "uidGG");
        String userpass = perfer.getString("userpass", "userpassGG");
        OkHttpUtils
                .post()
                .url("http://139.129.19.51/story/index.php/home/Interface/sendStory")
                .addParams("story_info",editText.getText().toString())
                .addParams("uid",uid)
                .addParams("userpass",userpass)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(NewStory.this, "上传失败呵呵呵~", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject json = null;
                        try {
                            json = new JSONObject(response);
                            int result = json.optInt("result");
                            String msg = json.optString("msg");
                            Log.e(TAG,result+""+msg);
                            if (result==1){
                                Toast.makeText(NewStory.this, "发表成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(NewStory.this,MainActivity.class));
                            }
                            else {
                                Toast.makeText(NewStory.this, "发表失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }

    //点击跳转到选择图片
    public void clickToImgLib(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
              //  headImg.setImageBitmap(mBitmap);

                String[] proj = {MediaStore.Images.Media.DATA};
                //好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = managedQuery(uri, proj, null, null, null);
                //获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                //将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                //最后根据索引值获取图片路径
              //  iconPath =  cursor.getString(column_index);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    //点击跳转到选择拍照
    public void clickToCamera(View view) {


    }

    //后退
    public void back(View view) {
        finish();
    }

}

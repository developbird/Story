package com.example.administrator.story;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/18.
 */
public class Load extends AppCompatActivity {
    private static final String TAG = "Load";
    private EditText load_name, load_password;
    private final static String url_load = "http://139.129.19.51/story/index.php/home/Interface/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_load);
        load_name = (EditText) findViewById(R.id.load_edit_username);
        load_password = (EditText) findViewById(R.id.load_edit_userpassword);
    }

    /**
     * 这是跳转到注册界面
     *
     * @param view
     */
    public void loadToRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    /**
     * 这是跳转到主界面
     *
     * @param view
     */
    public void loadToMainActivity(View view) {
        if (TextUtils.isEmpty(load_name.getText())) {
            load_name.setError("帐号不能为空");
        } else if (TextUtils.isEmpty(load_password.getText())) {
            load_password.setError("密码不能为空");
        }else {
            OkHttpUtils
                    .post()
                    .url(url_load)
                    .addParams("username",load_name.getText().toString())
                    .addParams("password",load_password.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject json = new JSONObject(response);
                                int result = json.optInt("result");
                                String msg = json.optString("msg");
                                JSONObject data = json.optJSONObject("data");
                                String uid = data.optString("id");
                                String userpass = data.optString("userpass");
                                String username = data.optString("username");
                                String usersex = data.optString("usersex");
                                String useremail = data.optString("useremail");
                                String nickname = data.optString("nickname");
                                String birthday = data.optString("birthday");
                                String portrait = data.optString("portrait");
                                String signature = data.optString("signature");

                                //将objectJson传到数据库中
                                SharedPreferences perfer = getSharedPreferences("load_data",MODE_PRIVATE);
                                SharedPreferences.Editor edit = perfer.edit();
                                edit.putString("uid",uid);
                                edit.putString("userpass",userpass);
                                edit.putString("username",username);
                                edit.putString("usersex",usersex);
                                edit.putString("useremail",useremail);
                                edit.putString("nickname",nickname);
                                edit.putString("birthday",birthday);
                                edit.putString("portrait",portrait);
                                edit.putString("signature",signature);

                                edit.commit();






                                Log.e(TAG,"=================="+msg+"=================="+result);
                                if (result==1){
                                    startActivity(new Intent(Load.this,MainActivity.class));
                                }
                                else{
                                    Toast.makeText(Load.this, "登陆失败，用户名或密码错误", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }
}






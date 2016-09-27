package com.example.administrator.story.Left_Page.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.story.R;

/**
 * Created by Administrator on 2016/8/21.
 */
public class User_Info extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
    }

    public void clickTomodiName(View view) {
        startActivity(new Intent(this,Modification_name.class));
    }

    public void clickTomodiSex(View view) {
        startActivity(new Intent(this,Modification_sex.class));
    }

    public void clickTomodiMail(View view) {
        startActivity(new Intent(this,Modification_mail.class));
    }

    public void clickTomodiBirth(View view) {
        startActivity(new Intent(this,Modification_birth.class));
    }

    public void clickTomodiPassword(View view) {
        startActivity(new Intent(this,Modification_password.class));
    }
}

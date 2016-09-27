package com.example.administrator.story.Left_Page.userinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.story.R;

/**
 * Created by Administrator on 2016/8/22.
 */
public class Modification_password extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modification_password);
    }

    //点击回退上一级
    public void clickToBack(View view) {
        onBackPressed();
    }
}

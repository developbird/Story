package com.example.administrator.story.Left_Page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.story.R;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SystemSetting extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_setting);
    }

    public void clickToBack(View view) {
        onBackPressed();
    }
}

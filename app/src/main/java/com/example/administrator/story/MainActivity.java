package com.example.administrator.story;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.story.Left_Page.MyStory;
import com.example.administrator.story.Left_Page.SystemSetting;
import com.example.administrator.story.Left_Page.userinfo.User_Info;

import java.util.ArrayList;
import java.util.List;

import adapt.StoryAdapter;
import bean.GetStory;
import download.HotData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawer;
    private ImageView img;
    private TextView myStory;
    private TextView userInfo;
    private StoryAdapter storyAdapter;
    private ListView main_listView;


    private List<GetStory> storylistData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawer = (DrawerLayout) findViewById(R.id.drawLayout);
        img = (ImageView) findViewById(R.id.main_imgTonewStory);
        myStory = (TextView) findViewById(R.id.main_myStory);
        userInfo = (TextView) findViewById(R.id.main_userInfo);
        main_listView = (ListView) findViewById(R.id.main_listView);


        img.setOnClickListener(this);
        myStory.setOnClickListener(this);
        userInfo.setOnClickListener(this);

        //找到数据源
        HotData.getHotDate(new HotData.DataCallack() {
            @Override
            public void doResult(List<GetStory> getStories) {
                storylistData.addAll(getStories);
                storyAdapter.notifyDataSetChanged();
            }

        });
        //构建适配器
        storyAdapter = new StoryAdapter(this, storylistData);
        main_listView.setAdapter(storyAdapter);


    }

    /**
     *
     * @param view
     * 点击跳转到左边侧拉
     */
    public void clickToPaneLayout(View view) {
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_imgTonewStory: //跳转到新故事页面
                startActivity(new Intent(this,NewStory.class));
            break;
            case  R.id.main_myStory:
                startActivity(new Intent(this,MyStory.class));
            break;
            case R.id.main_userInfo:
                startActivity(new Intent(this, User_Info.class));
        }
    }

    /**
     * 点击跳转到系统设置
     * @param view
     */
    public void clickToSystemSetting(View view) {
        startActivity(new Intent(this, SystemSetting.class));
    }

    /**
     * 往事杂谈
     * @param view
     */
    public void clickToMain(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    /**
     * 退出
     * @param view
     */
    public void clickToBack(View view) {
        onBackPressed();
    }


    /**
     * 点击跳转到故事详情
     * @param view
     */
    public void clickToReadStory(View view) {
        startActivity(new Intent(MainActivity.this,ReadStory.class));
    }

    public void clickToReadStory2(View view) {
        startActivity(new Intent(MainActivity.this,ReadStory.class));
    }
}

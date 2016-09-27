package com.example.administrator.story.Left_Page;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.story.NewStory;
import com.example.administrator.story.R;

import java.util.ArrayList;
import java.util.List;

import adapt.MyStoryAdapter;
import bean.GetMyStory;
import bean.ShareData;
import download.MyStoryData;

/**
 * Created by Administrator on 2016/8/20.
 */
public class MyStory extends AppCompatActivity {
    private ImageView userIcon,userSex;
    private TextView userName;
    private ListView mslistView;
    private MyStoryAdapter adapter;
    private List<GetMyStory> myStoryData = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_story);
        userIcon = (ImageView) findViewById(R.id.my_story_iconImg);
        userName = (TextView) findViewById(R.id.my_story_username);
        mslistView = (ListView) findViewById(R.id.my_story_listView);
        userSex = (ImageView) findViewById(R.id.my_story_SexImg);

        SharedPreferences perfer = getSharedPreferences(ShareData.DATANAME,MODE_PRIVATE);
        //获取用户的昵称
        String nikcName = perfer.getString(ShareData.nickname, "null");
        //获取用户的头像
        String portrait = perfer.getString(ShareData.portrait, "null");
        //获取用户的性别
        String sex = perfer.getString(ShareData.usersex, "null");
        //获取用户的生日
        String birthday = perfer.getString(ShareData.birthday, "null");
        //获取用户的id
        String uid = perfer.getString(ShareData.uid, "null");

        userName.setText(nikcName); //设置昵称
        Drawable draw = BitmapDrawable.createFromPath(portrait);
        userIcon.setImageDrawable(draw);//设置头像

        //找到数据源
        MyStoryData.getMyStoryData(uid, new MyStoryData.MyStoryDataCallback() {
            @Override
            public void doResult(List<GetMyStory> getMyStories) {
                myStoryData.addAll(getMyStories);
                adapter.notifyDataSetChanged();
            }
        });

        //构建适配器

        adapter = new MyStoryAdapter(myStoryData,this);
        mslistView.setAdapter(adapter);


    }



    //点击后退
    public void back(View view) {
        finish();
    }

    //写新的故事
    public void writeStory(View view) {
        startActivity(new Intent(MyStory.this, NewStory.class));

    }
}

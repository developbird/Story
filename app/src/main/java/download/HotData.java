package download;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import bean.GetStory;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.GetUser;
import content.PathContent;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/23.
 * 解析获取最热的数据
 */
public class HotData {
    private static final String TAG = "HotData" ;
    private static String path = PathContent.ROOT_PATH +PathContent.INTERFACE+"getStorys";
    private static  List<GetStory> getStoryList = new ArrayList<>();
    private static int page =0;
    public static String story_id = null;



    public static  void getHotDate(final DataCallack dataCallack){
        OkHttpUtils
                .post()
                .url(path)
                .addParams("type","new")
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"解析失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject json = new JSONObject(response);
                            int result = json.optInt("result");
                            if (result==1){ //如果为1则获取成功
                                JSONArray data = json.optJSONArray("data");
                                for (int i=0;i<data.length();i++){
                                    JSONObject jsonObject = data.optJSONObject(i);
                                    story_id = jsonObject.optString("id");
                                    String story_time = jsonObject.optString("story_time");
                                    String story_info = jsonObject.optString("story_info");

                                  /*  JSONArray picsArray = jsonObject.optJSONArray("pics");
                                    for (int j=0;j<picsArray.length();j++){
                                        String s = picsArray.optString(j);
                                        pics[j] = s;
                                        Log.e(TAG,s);
                                    }*/

                                    String pics = jsonObject.optString("pics"); //获取到图片的地址20150827/55de764229a0b.png
                                    String uid = jsonObject.optString("uid");
                                    String lng = jsonObject.optString("lng");
                                    String lat = jsonObject.optString("lat");
                                    String city = jsonObject.optString("city");
                                    String readcount = jsonObject.optString("readcount");
                                    String comment = jsonObject.optString("comment");
                                    //这是解析User数据
                                    JSONObject user = jsonObject.optJSONObject("user");
                                    String username = user.optString("username");
                                    String usersex = user.optString("usersex");
                                    String useremail = user.optString("useremail");
                                    String nickname = user.optString("nickname");
                                    String birthday = user.optString("birthday");
                                    String portrait = user.optString("portrait");
                                    String signature = user.optString("signature");

                                    String sTime = StrToTime(story_time);//转换时间

                                    GetStory  story = new GetStory();
                                    story.setStory_time(sTime);
                                    story.setStory_info(story_info);
                                    story.setCity(city);
                                    story.setComment(comment);
                                    story.setReadcount(readcount);
                                    story.setLat(lat);
                                    story.setLng(lng);
                                    //设置的是图片的地址
                                    story.setPics(pics);
                                    GetUser users = new GetUser();
                                    users.setPortrait(portrait);
                                    users.setNickname(nickname);
                                    story.setGetUser(users);


                                    //把解析出来的story数据放到一个集合中
                                    getStoryList.add(story);

                                }
                                if (getStoryList!=null){
                                    dataCallack.doResult(getStoryList);
                                }else{
                                    Log.e(TAG,"=========ListData数据为空==========================");
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    //回调数据源
    public interface DataCallack{
        public void doResult(List<GetStory> getStories);
    }

    //把时间字符串转换为时间
    @TargetApi(Build.VERSION_CODES.N)
    public static String  StrToTime(String storyTime){

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        long lon = Long.parseLong(storyTime);
        Date date =  new Date(lon);
        String time = format.format(date);

        //获取当前时间
        return time;
    }

}

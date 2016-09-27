package download;

import android.util.Log;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import bean.GetMyStory;
import content.PathContent;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MyStoryData {
    private static final String TAG = "MyStoryData" ;
    private static String path = PathContent.ROOT_PATH +PathContent.INTERFACE+"getStorys";
    private static List<GetMyStory> myStoryList = new ArrayList<>();

    public static void getMyStoryData(String uid, final MyStoryDataCallback callback){
        OkHttpUtils
                .post()
                .url(PathContent.ROOT_PATH+PathContent.INTERFACE+"myStorys")
                .addParams("uid",uid)
                .addParams("page",0+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"解析失败了 呵呵呵呵呵呵呵");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.optInt("result");
                            if (result==1){ //当为1的时候解析成功
                                JSONArray data = jsonObject.optJSONArray("data");
                                for (int i=0;i<data.length();i++){
                                    JSONObject object = data.optJSONObject(i);
                                    String story_info = object.optString("story_info");
                                    String readcount = object.optString("readcount");
                                    String comment = object.optString("comment");

                                    //构建myStory对象来设置值
                                    GetMyStory myStory = new GetMyStory();
                                    myStory.setStory_info(story_info);
                                    myStory.setComment(comment);
                                    myStory.setReadcount(readcount);

                                    myStoryList.add(myStory);
                                }
                                if (myStoryList!=null){
                                    callback.doResult(myStoryList);
                                }else{
                                    Log.e(TAG,"==============MyStoryList数据为空=======================");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
    public interface MyStoryDataCallback{
        public void doResult(List<GetMyStory> getMyStories);
    }
}

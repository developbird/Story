package adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bean.GetStory;

import com.example.administrator.story.MainActivity;
import com.example.administrator.story.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class StoryAdapter extends BaseAdapter {
    private static final String TAG ="StoryAdapter" ;
    private Context context;
    private List<GetStory> stories = new ArrayList<>();


    public StoryAdapter(Context context, List<GetStory> stories) {
        this.context = context;
        this.stories = stories;

    }


    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int i) {
        return stories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder{
        TextView item_time,item_city,item_info,item_comment,item_readcount,item_username;
        ImageView item_pics,item_iconImg;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder = new ViewHolder();
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item,null);
            holder.item_city= (TextView) view.findViewById(R.id.item_city);
            holder.item_time = (TextView) view.findViewById(R.id.item_time);
            holder.item_info = (TextView) view.findViewById(R.id.item_info);
            holder.item_comment = (TextView) view.findViewById(R.id.item_comment);
            holder.item_readcount = (TextView) view.findViewById(R.id.item_readcount);
            holder.item_pics = (ImageView) view.findViewById(R.id.item_pics);
            //获取用户的头像和姓名
            holder.item_username = (TextView) view.findViewById(R.id.item_username);
            holder.item_iconImg = (ImageView) view.findViewById(R.id.item_iconImg);


            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        //获取当前页面的Story对象
        GetStory getStory = stories.get(i);

        //获取图片地址
        String strings = getStory.getPics();
        holder.item_pics.setTag(strings);

        //获取用户头像
        String portrait = getStory.getGetUser().getPortrait();
        holder.item_iconImg.setTag(portrait);

        Picasso.with(context)
                .load("http://139.129.19.51/story//Uploads/portrait/"+portrait)
                .resize(50,50)
                .centerCrop()
                .error(R.drawable.icon_splach)
                .into(holder.item_iconImg);


        //通过Picasso传入布局
        //把图片地址通过Picasso加载到视图上

       /* Picasso.with(context)
                .load("http://139.129.19.51/story/Uploads/"+strings[0])
                .resize(50,50)
                .centerCrop()
                .placeholder(R.drawable.gougou)
                .error(R.mipmap.ic_launcher)
                .into(holder.item_pics);*/

        //把值赋到布局上
        holder.item_city.setText(getStory.getCity());
        holder.item_time.setText(getStory.getStory_time());
        holder.item_info.setText(getStory.getStory_info());
        holder.item_readcount.setText(getStory.getReadcount());
        holder.item_comment.setText(getStory.getComment());
        holder.item_username.setText(getStory.getGetUser().getNickname());


        return view;
    }
}

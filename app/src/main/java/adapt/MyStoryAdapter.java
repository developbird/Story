package adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.story.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import bean.GetMyStory;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MyStoryAdapter extends BaseAdapter {
    private List<GetMyStory> myStories = new ArrayList<>();
    private Context context;

    public MyStoryAdapter(List<GetMyStory> myStories, Context context) {
        this.myStories = myStories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myStories.size();
    }

    @Override
    public Object getItem(int i) {
        return myStories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        TextView ms_item_info,ms_item_comment,ms_item_readcount;
        ImageView ms_item_pics;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.my_story_item, null);
            holder.ms_item_info = (TextView) view.findViewById(R.id.ms_item_info);
            holder.ms_item_comment = (TextView) view.findViewById(R.id.ms_item_comment);
            holder.ms_item_pics = (ImageView) view.findViewById(R.id.ms_item_pics);
            holder.ms_item_readcount = (TextView) view.findViewById(R.id.ms_item_readcount);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();

            GetMyStory getMyStory = myStories.get(i);

            holder.ms_item_info.setText(getMyStory.getStory_info());
            holder.ms_item_readcount.setText(getMyStory.getReadcount());
            holder.ms_item_comment.setText(getMyStory.getComment());

        }


        return view;
    }
}

package adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.story.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bean.GetUser;

/**
 * Created by Administrator on 2016/8/24.
 */
public class UserAdapter extends BaseAdapter {
    private static final String TAG ="UserAdapter" ;
    private Context context;
    private List<GetUser> users = new ArrayList<>();

    public UserAdapter(Context context, List<GetUser> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder{
        TextView item_username;
        ImageView item_iconImg;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.item_username = (TextView) view.findViewById(R.id.item_username);
            holder.item_iconImg = (ImageView) view.findViewById(R.id.item_iconImg);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        GetUser getUser = users.get(i);
        //获取用户头像
        String portrait = getUser.getPortrait();
        holder.item_iconImg.setTag(portrait);
        Picasso.with(context)
                .load("http://139.129.19.51/story//Uploads/portrait/"+portrait)
                .resize(50,50)
                .centerCrop()
                .error(R.drawable.icon_splach)
                .into(holder.item_iconImg);

        holder.item_username.setText(getUser.getNickname());
        return view;
    }
}

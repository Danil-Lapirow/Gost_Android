package assolid.com.myrestclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Данила on 5/9/2017.
 */

public class PostsAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater lInflater;
    List<Post> postList;

    PostsAdapter(Context context, List<Post> products) {
        ctx = context;
        postList = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return postList.size();
    }

    // элемент по позиции
    @Override
    public Post getItem(int position) {
        return postList.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.post, parent, false);
        }

        Post p = getPost(position);

        ((TextView) view.findViewById(R.id.postTitle)).setText(p.getTitle());
        ((TextView) view.findViewById(R.id.postCreated)).setText(p.get_created());

        return view;
    }

    // товар по позиции
    Post getPost(int position) {
        return ((Post) getItem(position));
    }

    // содержимое корзины
    List<Post> getPosts() {
        List<Post> posts = new ArrayList<Post>();
        for (Post post : postList) {
            posts.add(post);
        }
        return posts;
    }
}

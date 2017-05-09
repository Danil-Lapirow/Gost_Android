package assolid.com.myrestclient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private TextView mTxtView;
    private ListView postsLV;
    private Button updateBtn;

    public PostsAdapter postsAdapter;

    private SimpleTextRequest txtRequest;
    private List<Post> postList;
    public final String BASE_URL ="http://192.168.0.103:5000/";
    String CUR_URL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postList = new ArrayList<Post>();

        postsLV = (ListView) findViewById(R.id.postsLV);

        postsAdapter = new PostsAdapter(this,postList);

        updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpiceManager().execute(txtRequest, "txt", DurationInMillis.ONE_MINUTE,
                        new GetDataFromRest());
            }
        });

        txtRequest = new SimpleTextRequest(BASE_URL+"posts");

    }

    public final class GetDataFromRest implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final String result) {

            //Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            try{
                JSONObject rootJSON = new JSONObject(result);
                JSONArray itemsJSON = rootJSON.getJSONArray("_items");
                String jsonResponse = "";
                for (int i = 0; i < itemsJSON.length(); i++) {

                    JSONObject postProfs = (JSONObject) itemsJSON
                            .get(i);
                    Post post = new Post();
                    post.setTitle(postProfs.getString("title"));
                    post.set_created(postProfs.getString("_created"));
                    post.set_id(postProfs.getString("_id"));
                    post.set_updated(postProfs.getString("_updated"));

                    postList.add(post);
                }
//                mTxtView.setText(jsonResponse);
                postsLV.setAdapter(postsAdapter);
            }catch(JSONException e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
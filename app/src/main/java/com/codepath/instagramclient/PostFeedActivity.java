package com.codepath.instagramclient;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/// Client ID: 26e857ac6d094d46a0ed8ba4d238a7da
// Use to access the API

// API Endpoint: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
// Example Response:
// {
//     "data": [{
//         "type": "image",
//         "tags": [],
//         "comments": { ... },
//         "caption": {
//             "created_time": "1296656006",
//             "text": "ãã¼ãâ¥ã¢ããªå§ãã¦ä½¿ã£ã¦ã¿ãã(^^)",
//             "id": "26329105"
//         },
//         "likes": {
//             "count": 35,
//         },
//         "link": "http://instagr.am/p/BV5v_/",
//         "user": {
//             "username": "cocomiin",
//             "profile_picture": "http://distillery.s3.amazonaws.com/profiles/profile_1127272_75sq_1296145633.jpg",
//         },
//         "created_time": "1296655883",
//         "images": {
//             "standard_resolution": {
//                 "url": "http://distillery.s3.amazonaws.com/media/2011/02/01/34d027f155204a1f98dde38649a752ad_7.jpg",
//                 "width": 612,
//                 "height": 612
//             }
//         },
//     }]
// }

public class PostFeedActivity extends AppCompatActivity {

    public static final String CLIENT_ID = "26e857ac6d094d46a0ed8ba4d238a7da";


    private ArrayList<InstagramPost> mPosts;
    private InstagramPostAdapter mPostsAdapter;
    private SwipeRefreshLayout mSwipeLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_feed);
        mPosts = new ArrayList<>();

        // Create the adapter, linking it to the source
        mPostsAdapter = new InstagramPostAdapter(this, mPosts);

        // Find list view from layout
        ListView lvPosts = (ListView) findViewById(R.id.lvPosts);

        // Connect the adapter to the list view
        lvPosts.setAdapter(mPostsAdapter);


        mSwipeLayoutContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        mSwipeLayoutContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotos();
            }
        });

        // Configure the refreshing colors
        mSwipeLayoutContainer.setColorSchemeResources(R.color.main_blue);

        // Fetch photos once to start
        fetchPopularPhotos();
    }

    public void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler() {

            // Override the onSuccess for a JSONObject, NOT a JSONArray, since the root response
            //
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("DEBUG", response.toString());
                // Clear existing data
                mPosts.clear();
                JSONArray postsJSON = null;
                try {
                    postsJSON = response.getJSONArray("data");
                    for (int i = 0; i < postsJSON.length(); i++) {
                        JSONObject postJSON = postsJSON.getJSONObject(i);

                        String caption = postJSON.optJSONObject("caption") != null
                                ? postJSON.getJSONObject("caption").getString("text")
                                : null;

                        InstagramPost post = new InstagramPost(
                                postJSON.getJSONObject("user").getString("username"),
                                postJSON.getJSONObject("user").optString("profile_picture"),
                                caption,
                                postJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"),
                                postJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("height"),
                                Long.parseLong(postJSON.getString("created_time")),
                                Integer.parseInt(postJSON.getJSONObject("likes").getString("count")));
                        mPosts.add(post);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException("Failure parsing Instagram post", e);
                }

                mPostsAdapter.notifyDataSetChanged();
                mSwipeLayoutContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                mSwipeLayoutContainer.setRefreshing(false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

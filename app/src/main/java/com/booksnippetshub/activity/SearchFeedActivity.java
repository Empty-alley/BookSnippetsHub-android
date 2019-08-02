package com.booksnippetshub.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.R;
import com.booksnippetshub.feed.FeedListRefresh;
import com.booksnippetshub.fragment.CollectMainFragment;
import com.booksnippetshub.fragment.MainFragment;
import com.booksnippetshub.fragment.MeFragment;
import com.booksnippetshub.fragment.NotificationFragment;
import com.booksnippetshub.model.FeedModel;
import com.booksnippetshub.utils.GenerateFeedRecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchFeedActivity extends AppCompatActivity {


    RecyclerView discoveryfeedlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedsearch);
        Log.d("onCreate", getIntent().getStringExtra("keywordvalue"));
        searchFeed(getIntent().getStringExtra("keywordvalue"));


        FeedListRefresh feedListRefresh = new FeedListRefresh() {
            JSONArray allrecommendfeedsid = new JSONArray();
            HashMap<String, Object> requestparam = new HashMap();

            @Override
            public Map<String, Object> requestParam() {
                if (!requestparam.containsKey("allrecommendfeedsid")) {
                    requestparam.put("allrecommendfeedsid", allrecommendfeedsid);
                }
                return requestparam;
            }

            @Override
            public void onRespone(String responseString) {
                JSONObject responseJsonObject=JSONObject.parseObject(responseString);
                if(responseJsonObject.getInteger("error")!=0){
                    Toast.makeText(SearchFeedActivity.this, responseJsonObject.getString("errmsg"), Toast.LENGTH_SHORT).show();
                }else {

                }


                JSONArray responsearray = JSONArray.parseArray(responseString);

                for (int i = 0; i < responsearray.size(); i++) {
                    JSONObject feedjson = responsearray.getJSONObject(i);
                    FeedModel feedModel = feedjson.toJavaObject(FeedModel.class);
                    allrecommendfeedsid.add(feedModel.getId());
                }

            }
        };



//        discoveryfeedlist = GenerateFeedRecyclerView.generate(SearchFeedActivity.this, R.id.discoveryfeedlist, false, "/getrecommendfeed", feedListRefresh);


    }

    private void searchFeed(String keyword){
        JSONObject requestjson = new JSONObject();
        requestjson.put("keyword", keyword);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(CONFIG.baseUrl + "/searchfeed?keyword="+keyword).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsestring = response.body().string();
                Log.d("response string", responsestring);

            }
        });
    }


}

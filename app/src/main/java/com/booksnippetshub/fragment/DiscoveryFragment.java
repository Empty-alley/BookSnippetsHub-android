package com.booksnippetshub.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.AboutActivity;
import com.booksnippetshub.AuthorizationHeaderInterceptor;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.FeedAdapter;
import com.booksnippetshub.FeedListRefresh;
import com.booksnippetshub.R;
import com.booksnippetshub.RealseActivity;
import com.booksnippetshub.model.FeedModel;
import com.booksnippetshub.utils.GenerateFeedRecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class DiscoveryFragment extends Fragment {

    private List<FeedModel> feedModels = new ArrayList<>();

    RecyclerView discoveryfeedlist;


    public DiscoveryFragment() {
        this.setArguments(new Bundle());
    }


    public static DiscoveryFragment newInstance(String param1, String param2) {
        DiscoveryFragment fragment = new DiscoveryFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discovery_release, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                JSONArray responsearray = JSONArray.parseArray(responseString);

                for (int i = 0; i < responsearray.size(); i++) {
                    JSONObject feedjson = responsearray.getJSONObject(i);
                    FeedModel feedModel = feedjson.toJavaObject(FeedModel.class);
                    allrecommendfeedsid.add(feedModel.getId());
                }

            }
        };


        discoveryfeedlist = GenerateFeedRecyclerView.generate(getActivity(), R.id.discoveryfeedlist, false, "/getrecommendfeed", feedListRefresh);


        FloatingActionButton discoveryreleasea = getActivity().findViewById(R.id.discoveryrelease);
        discoveryreleasea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRealseActivity = new Intent(getActivity(), RealseActivity.class);
                getActivity().startActivity(toRealseActivity);
            }
        });


    }


    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

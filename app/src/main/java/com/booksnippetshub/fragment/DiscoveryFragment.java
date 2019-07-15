package com.booksnippetshub.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.feed.FeedListRefresh;
import com.booksnippetshub.R;
import com.booksnippetshub.activity.RealseActivity;
import com.booksnippetshub.model.FeedModel;
import com.booksnippetshub.utils.GenerateFeedRecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DiscoveryFragment extends Fragment {
    static String TAG="DiscoveryFragment";

    private List<FeedModel> feedModels = new ArrayList<>();

    RecyclerView discoveryfeedlist;


    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    public DiscoveryFragment() {
        this.setArguments(new Bundle());
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
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
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_discovery_release, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
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
        Log.d(TAG, "onButtonPressed: ");
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }
}

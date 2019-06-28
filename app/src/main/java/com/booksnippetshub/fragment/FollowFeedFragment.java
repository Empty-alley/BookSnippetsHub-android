package com.booksnippetshub.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.AuthorizationHeaderInterceptor;
import com.booksnippetshub.FeedListRefresh;
import com.booksnippetshub.R;
import com.booksnippetshub.model.FeedModel;
import com.booksnippetshub.utils.GenerateFeedRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;


public class FollowFeedFragment extends Fragment {
    private List<FeedModel> feedModels = new ArrayList<>();

    RecyclerView followfeedlist;




    public FollowFeedFragment() {
        this.setArguments(new Bundle());
    }

    public static FollowFeedFragment newInstance(String param1, String param2) {
        FollowFeedFragment fragment = new FollowFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followfeed, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        FeedListRefresh feedListRefresh = new FeedListRefresh() {


              HashMap<String, Object> requestparam = new HashMap();


            @Override
            public Map<String, Object> requestParam() {

                return requestparam;
            }

            @Override
            public void onRespone(String responseString) {


            }
        };


        followfeedlist = GenerateFeedRecyclerView.generate(getActivity(), R.id.followfeedlist, true, "/getfollowuserfeed", feedListRefresh);

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

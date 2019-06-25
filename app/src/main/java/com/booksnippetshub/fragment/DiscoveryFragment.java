package com.booksnippetshub.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.booksnippetshub.AuthorizationHeaderInterceptor;
import com.booksnippetshub.CONFIG;
import com.booksnippetshub.FeedAdapter;
import com.booksnippetshub.R;
import com.booksnippetshub.model.FeedModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    OkHttpClient okHttpClient;

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

        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        discoveryfeedlist = getActivity().findViewById(R.id.discoveryfeedlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        discoveryfeedlist.setLayoutManager(linearLayoutManager);
    }


    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();

        JSONObject requstbody=new JSONObject();
        requstbody.put("allrecommendfeedsid",new JSONArray());

        Request request = new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"),requstbody.toJSONString())).url(CONFIG.baseUrl + "/getrecommendfeed").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONArray responsearray = JSONArray.parseArray(response.body().string());
                for(int i=0;i<responsearray.size();i++){
                    JSONObject feedjson=responsearray.getJSONObject(i);

                    FeedModel feedModel=feedjson.toJavaObject(FeedModel.class);
                    feedModels.add(feedModel);
                }

                getActivity().runOnUiThread(()->{
                    FeedAdapter feedAdapter = new FeedAdapter(feedModels);
                    feedAdapter.setContext(getContext());
                    discoveryfeedlist.setAdapter(feedAdapter);
                });
            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

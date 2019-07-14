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

import com.booksnippetshub.NotifationAdapter;
import com.booksnippetshub.R;
import com.booksnippetshub.model.NotifationModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NotificationFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<NotifationModel> notifationModels = new ArrayList<>();
    RecyclerView notificationlist;
    OkHttpClient okHttpClient;

    public NotificationFragment() {
        this.setArguments(new Bundle());
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notificationlist = getActivity().findViewById(R.id.notifictionlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationlist.setLayoutManager(linearLayoutManager);
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(new AuthorizationHeaderInterceptor()).build();

        Request request = new Request.Builder().url(CONFIG.baseUrl + "/getnotification").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONArray responsearray = JSONArray.parseArray(response.body().string());
                for(int i=0;i<responsearray.size();i++) {
                    JSONObject notifationjson = responsearray.getJSONObject(i);

                    NotifationModel notifationModel=notifationjson.toJavaObject(NotifationModel.class);
                    notifationModels.add(notifationModel);
                }
                getActivity().runOnUiThread(()->{
                    NotifationAdapter notifationAdapter = new NotifationAdapter(notifationModels);
                    notifationAdapter.setContext(getContext());
                    notificationlist.setAdapter(notifationAdapter);
                });
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}

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

import com.booksnippetshub.FeedAdapter;
import com.booksnippetshub.R;
import com.booksnippetshub.model.FeedModel;

import java.util.ArrayList;
import java.util.List;


public class DiscoveryFragment extends Fragment {

    private List<FeedModel> feedModels=new ArrayList<>();

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

        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        discoveryfeedlist = getActivity().findViewById(R.id.discoveryfeedlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        discoveryfeedlist.setLayoutManager(linearLayoutManager);

        feedModels.add(new FeedModel());
        feedModels.add(new FeedModel());

        FeedAdapter feedAdapter=new FeedAdapter(feedModels);
        discoveryfeedlist.setAdapter(feedAdapter);

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

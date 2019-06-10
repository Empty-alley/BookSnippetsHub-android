package com.booksnippetshub.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.booksnippetshub.R;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.security.auth.login.LoginException;


public class MeFragment extends Fragment {

    private TextView nickNameTextView;
    private AppCompatActivity activity;
    private SimpleDraweeView avatarDraweeView;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume");
    }

    private String mParam2;

    public MeFragment() {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("onHiddenChanged", String.valueOf(hidden));

    }

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifecycle", "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("lifecycle", "onCreateView");
        return inflater.inflate(R.layout.fragment_me, container, false);
    }


    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        Log.d("lifecycle", "onAttach ");
        super.onAttach(context);
        this.activity= (AppCompatActivity) getActivity();
    }

    @Override
    public void onDestroyView() {
        Log.d("lifecycle", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d("lifecycle", "onDetach ");
        super.onDetach();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("lifecycle", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        avatarDraweeView=activity.findViewById(R.id.avatarDraweeView);
        nickNameTextView=activity.findViewById(R.id.nickName);

        Uri uri=Uri.parse("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIDd1HT30cLyfEkS2iaN115qC44ZREvuFAXSvXO8GbqHjuw6cYIgibO0NZf9sLiclnD7rXFuqcCibd9Gg/132");

        avatarDraweeView.setImageURI(uri);

    }


}

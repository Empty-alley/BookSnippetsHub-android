package com.booksnippetshub.feed;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface FeedListRefresh {
    Map<String,Object> requestParam();
    void onRespone(String responseString);
}

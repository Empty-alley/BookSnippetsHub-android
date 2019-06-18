package com.booksnippetshub;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder b = request.newBuilder();
        b.header("Authorization", CONFIG.token);

        return chain.proceed(b.build());
    }
}

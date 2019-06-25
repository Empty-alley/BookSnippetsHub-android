package com.booksnippetshub.utils;

import android.content.Context;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;

public class UriToByteArray {

    public static byte[] to(Uri uri, Context context) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            InputStream stream = context.getContentResolver().openInputStream(uri);
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }
}

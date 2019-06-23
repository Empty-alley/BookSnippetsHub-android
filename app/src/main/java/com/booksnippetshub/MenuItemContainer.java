package com.booksnippetshub;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MenuItemContainer extends LinearLayout {
    private ImageView imageView;
    private TextView textView;
    private LinearLayout linearLayout;

    public MenuItemContainer(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.manu_item, this);

        imageView = findViewById(R.id.item_icon);
        textView = findViewById(R.id.item_text);
        linearLayout = findViewById(R.id.item);
    }

    public MenuItemContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.manu_item, this);

        imageView = findViewById(R.id.item_icon);
        textView = findViewById(R.id.item_text);
        linearLayout = findViewById(R.id.item);
    }

    public void setDetails(int image, String text) {
        imageView.setImageResource(image);
        textView.setText(text);
    }


}

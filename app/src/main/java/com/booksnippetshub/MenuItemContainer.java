package com.booksnippetshub;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

    public void setToActivity(int image, String text, Class<?> cls) {
        imageView.setImageResource(image);
        textView.setText(text);
        linearLayout.setOnClickListener((View v) -> {
            getContext().startActivity(new Intent(getContext(), cls));
            Log.d(textView.getText().toString(), "Onclick");
        });
    }


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }
}

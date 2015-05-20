package com.example.android.cfmaterial.retailer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RetailerImageView extends ImageView {
    public RetailerImageView(Context context) {
        super(context);
    }

    public RetailerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RetailerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}

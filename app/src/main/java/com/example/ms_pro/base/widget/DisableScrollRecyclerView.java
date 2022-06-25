package com.example.ms_pro.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by ANHTH on 06-Jun-16.
 */

public class DisableScrollRecyclerView extends CRecyclerView {

    private OnMeasuredViewSize onMeasuredViewSize;

    public void setOnMeasuredViewSize(OnMeasuredViewSize onMeasuredViewSize) {
        this.onMeasuredViewSize = onMeasuredViewSize;
    }

    public DisableScrollRecyclerView(Context context) {
        super(context);
    }

    public DisableScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisableScrollRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();

            if (onMeasuredViewSize != null) {
                onMeasuredViewSize.getHeight(getHeight());
                onMeasuredViewSize.getWidth(getHeight());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

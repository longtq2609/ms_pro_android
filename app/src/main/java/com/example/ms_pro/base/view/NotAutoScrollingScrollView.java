package com.example.ms_pro.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ScrollingView;
import androidx.core.widget.NestedScrollView;

public class NotAutoScrollingScrollView extends NestedScrollView {
    public NotAutoScrollingScrollView(@NonNull Context context) {
        super(context);
    }

    public NotAutoScrollingScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotAutoScrollingScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        if (focused instanceof ScrollingView)
            return;
        super.requestChildFocus(child, focused);
    }
}

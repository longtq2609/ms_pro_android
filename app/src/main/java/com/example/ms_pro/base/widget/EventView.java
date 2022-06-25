package com.example.ms_pro.base.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.ms_pro.R;

import com.example.ms_pro.base.util.AppHelper;

/**
 * Created by anhth on 3/19/18.
 */

public class EventView extends LinearLayout {

    ImageView eventImage;
    TextView eventDistance;


    public EventView(Context context) {
        super(context);
        init();
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setOrientation(VERTICAL);
        eventImage = new ImageView(getContext());
        eventImage.setScaleType(ImageView.ScaleType.FIT_XY);
        int imageSize = (int) getContext().getResources().getDimension(R.dimen.image_event_in_route_overview_mode);
        eventImage.setLayoutParams(new LayoutParams(imageSize, imageSize));
        addView(eventImage);
        eventDistance = new TextView(getContext());
        eventDistance.setLayoutParams(new LayoutParams(imageSize, imageSize));
        eventDistance.setText("");
        eventDistance.setTextSize(getContext().getResources().getDimension(R.dimen.text_size_12sp));
        eventDistance.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        eventDistance.setGravity(Gravity.CENTER);
        eventDistance.setEllipsize(TextUtils.TruncateAt.END);
        eventDistance.setMaxLines(1);
        eventDistance.setVisibility(GONE);
        addView(eventDistance);
    }


    public void setEventImageSource(int resourceID) {
        eventImage.setImageResource(resourceID);

    }

    public void setSize(int width, int height) {
        eventImage.setLayoutParams(new LayoutParams(width, height));
        eventDistance.setLayoutParams(new LayoutParams(width, height));
    }

    public void setEventDistance(double distance) {
        eventDistance.setText(AppHelper.formatDistance(distance));
    }
}

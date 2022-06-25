package com.example.ms_pro.base.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;


public class BaveAnimAdapter extends AnimationAdapter {

    private static final float DEFAULT_SCALE_FROM = 1.7f;
    private final float mFrom;

    public BaveAnimAdapter(RecyclerView.Adapter adapter) {
        this(adapter, DEFAULT_SCALE_FROM);
    }

    public BaveAnimAdapter(RecyclerView.Adapter adapter, float from) {
        super(adapter);
        mFrom = from;
    }

    @Override
    protected Animator[] getAnimators(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.7f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.7f, 1f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0.4f, 1f);
        return new ObjectAnimator[]{scaleX, scaleY, fadeIn};
    }
}

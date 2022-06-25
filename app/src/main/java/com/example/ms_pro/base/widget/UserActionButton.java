package com.example.ms_pro.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.example.ms_pro.R;
import com.example.ms_pro.base.util.CLog;
import com.example.ms_pro.databinding.LayoutUserActionButtonBinding;


/**
 * Created by anhth on 1/18/18.
 */

public class UserActionButton extends RelativeLayout {

    public LayoutUserActionButtonBinding viewBinding;
    public boolean status = false;

    public UserActionButton(Context context) {
        super(context);
        init();
    }

    public UserActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        viewBinding.imgAction.setEnabled(enabled);
    }

    private void init() {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_user_action_button, null, false);
        addView(viewBinding.getRoot());
    }

    public void setOnClickListener(OnClickListener listener) {
        viewBinding.imgAction.setOnClickListener(listener);
    }

    public void setViewActivated(boolean stt) {
        status = stt;
        CLog.d("view " + stt);
        viewBinding.tvCount.setActivated(stt);
        viewBinding.tvCount.setSelected(stt);
        viewBinding.imgAction.setActivated(stt);
        viewBinding.imgAction.setSelected(stt);
    }

    public void setTextViewCount(int count) {
        viewBinding.tvCount.setText(String.valueOf(count));
    }

    public int getTextViewCount() {
        try {
            return Integer.parseInt(viewBinding.tvCount.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public TextView getTextView() {
        return viewBinding.tvCount;
    }

    public ImageView getImageView() {
        return viewBinding.imgAction;
    }

}

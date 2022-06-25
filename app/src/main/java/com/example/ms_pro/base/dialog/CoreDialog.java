package com.example.ms_pro.base.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

import com.example.ms_pro.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public abstract class CoreDialog<VB extends ViewDataBinding> {

    protected DialogPlus dialogPlus;
    protected Context context;
    protected VB viewBinding;
    private int[] margin = new int[4];
    private int[] padding = new int[4];

    public CoreDialog(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                getLayoutId(), null, false);
        padding = getPadding();
        margin = getMargin();
        dialogPlus = DialogPlus.newDialog(context)
                .setGravity(getGravity())
                .setCancelable(true)
                .setPadding(padding[0], padding[1], padding[2], padding[3])
                .setMargin(margin[0], margin[1], margin[2], margin[3])
                .setContentWidth(getContentWidth())
                .setContentHeight(getContentHeight())
                .setContentBackgroundResource(R.color.transparent)
                .setContentHolder(new ViewHolder(viewBinding.getRoot()))
                .create();
        listener();
        setupView();
        try {
            viewBinding.setLifecycleOwner((LifecycleOwner) context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract Integer getLayoutId();

    protected abstract void listener();

    protected abstract void setupView();

    protected int[] getMargin() {
        margin = new int[4];
        return margin;
    }

    protected int[] getPadding() {
        padding = new int[4];
        return padding;
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected int getContentWidth() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    protected int getContentHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public void show() {
        if (dialogPlus != null) {
            dialogPlus.show();
        }
    }

    public void showDelay(long ms) {
        try {
            if (dialogPlus != null) {
                dialogPlus.show();
                dialogPlus.getHolderView().postDelayed(() -> {
                    dialogPlus.dismiss();
                }, ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dismiss();
        }
    }

    public void dismiss() {
        if (dialogPlus != null) {
            dialogPlus.dismiss();
        }

    }

    public interface BaseCallback {
        void onClose(BaseDialog dialog);
    }
}

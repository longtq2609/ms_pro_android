package com.example.ms_pro.base.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.SizeUtils;
import com.example.ms_pro.R;
import com.example.ms_pro.databinding.DialogFunctionNotAvailableBinding;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class DialogFunctionNotAvailable {

    private DialogPlus dialogPlus;
    private Context context;
    private DialogFunctionNotAvailableBinding viewbinding;

    public DialogFunctionNotAvailable(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        viewbinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_function_not_available, null, false);
        dialogPlus = DialogPlus.newDialog(context)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setContentBackgroundResource(R.color.transparent)
                .setContentHolder(new ViewHolder(viewbinding.getRoot()))
                .setMargin(SizeUtils.dp2px(15), 0, SizeUtils.dp2px(15), 0)
                .setPadding(SizeUtils.dp2px(20), SizeUtils.dp2px(30), SizeUtils.dp2px(20), SizeUtils.dp2px(40))
                .create();
        setUpListener();
    }

    public void updateMessage(String message) {
        viewbinding.message.setText(message);
    }

    private void setUpListener() {
        viewbinding.btnClose.setOnClickListener(v -> {
            dismiss();
        });
    }

    public void show() {
        if (dialogPlus != null) {
            dialogPlus.show();
        }
    }

    public void dismiss() {
        if (dialogPlus != null) {
            dialogPlus.dismiss();
        }
    }

    public interface Callback {
        void onPositiveClick(DialogPlus dialogPlus);

        void onNegativeClick(DialogPlus dialogPlus);
    }
}

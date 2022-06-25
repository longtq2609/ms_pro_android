package com.example.ms_pro.base.dialog;

import android.content.Context;

import com.orhanobut.dialogplus.DialogPlus;

public class BaseDialog {

    public DialogPlus dialogPlus;
    public Context context;

    public BaseDialog(Context context) {
        this.context = context;
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

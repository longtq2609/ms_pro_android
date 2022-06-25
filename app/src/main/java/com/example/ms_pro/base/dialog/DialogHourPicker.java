package com.example.ms_pro.base.dialog;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.example.ms_pro.R;
import com.example.ms_pro.databinding.DialogPickTimePickerBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogHourPicker {

    private BottomSheetDialog dialogPlus;
    private Context context;
    private DialogPickTimePickerBinding viewBinding;
    private int minutes = 0;
    private int hour = 0;
    private Callback callback;

    public DialogHourPicker(Context context) {
        this.context = context;
        init();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void init() {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_pick_time_picker, null, false);
        dialogPlus = new BottomSheetDialog(context);
        dialogPlus.setContentView(viewBinding.getRoot());
        bindview();
        listener();
    }

    private void bindview() {
        viewBinding.timePicker.setIs24HourView(true);
    }

    public void setTime(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
        updateView();
    }

    private void updateView() {
        viewBinding.timePicker.setCurrentHour(hour);
        viewBinding.timePicker.setCurrentMinute(minutes);
        viewBinding.timePicker.setIs24HourView(false);
    }

    private void listener() {
        viewBinding.btnOk.setOnClickListener(v -> {
            if (callback != null) {
                callback.onSave(viewBinding.timePicker.getHour(), viewBinding.timePicker.getMinute());
            }
            dismiss();
        });
        viewBinding.btnCancel.setOnClickListener(v -> {
            if (callback != null) {
                callback.onDismiss();
            }
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
        void onSave(int hour, int minutes);

        void onDismiss();
    }

}

package com.example.ms_pro.base.util;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


/**
 * Created by admin on 11/5/2018.
 */

public class ToastUtil {
    private static Toast toasty;

    public static void toastSuccess(Context context, String message) {
        if (message == null)
            return;
        if (isShowing()) {
            toasty.cancel();
        }
        toasty = Toasty.success(context, message, message.length() < 50 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG,
                true);
        toasty.show();
    }

    public static void toastError(Context context, String message) {
        if (isShowing()) {
            toasty.cancel();
        }
        toasty = Toasty.error(context, message, message.length() < 50 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toasty.show();
    }

    public static void toastWarning(Context context, String message) {
        toastError(context, message);
    }

    public static void toastInfo(Context context, String message) {
        if (isShowing()) {
            toasty.cancel();
        }
        toasty = Toasty.info(context, message, message.length() < 50 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG,
                true);
//        toasty.setGravity(Gravity.CENTER, 0, 0);
        toasty.show();
    }

    public static void toastNormal(Context context, String message) {
        if (isShowing()) {
            toasty.cancel();
        }
        toasty = Toasty.normal(context, message);
//        toasty.setGravity(Gravity.CENTER, 0, 0);
        toasty.show();
    }

    private static boolean isShowing() {
        if (toasty == null) {
            return false;
        }
        try {
            return toasty.getView().isShown();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
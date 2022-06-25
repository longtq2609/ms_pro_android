package com.example.ms_pro.base.util;

import android.content.Context;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

import com.example.ms_pro.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;



/**
 * Created by admin on 5/9/2018.
 */

public class MyPermission {
    private static final MyPermission ourInstance = new MyPermission();
    private String[] listPermission;
    private OnPermissionCallBack onPermissionCallBack;

    public static MyPermission getInstance() {
        return ourInstance;
    }

    private MyPermission() {

    }

    public void requestPermission(Context activity, OnPermissionCallBack onPermissionCallBack, String... permissions) {
        requestPermission(activity, onPermissionCallBack, false, permissions);
    }

    public void requestPermission(Context activity, OnPermissionCallBack onPermissionCallBack,
                                  boolean isContinue, String... permissions) {
        listPermission = permissions;
        this.onPermissionCallBack = onPermissionCallBack;
        AndPermission.with(activity)
                .runtime()
                .permission(permissions)
                .rationale((context, data, executor) -> executor.execute())
                .onGranted(permissions1 -> onPermissionCallBack.onGranted())
                .onDenied(permissions12 -> {
                    if (isContinue) {
                        onPermissionCallBack.onGranted();
                    } else {
                        if (AndPermission.hasAlwaysDeniedPermission(activity, permissions12)) {
                            showSettingDialog(activity, permissions12);
                        }
                    }

                })
                .start();
    }

    private void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed,
                TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(R.string.setting, (dialog, which) -> setPermission(context))
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void setPermission(Context context) {
//        AndPermission.with(context)
//                .runtime()
//                .setting()
////                .onComeback(() -> {
////                    boolean isNotGranted = true;
////                    if (listPermission == null || listPermission.length == 0) {
////                        isNotGranted = false;
////                    } else {
////                        for (String aListPermission : listPermission) {
////                            if (ActivityCompat.checkSelfPermission(context, aListPermission)
////                                    != PackageManager.PERMISSION_GRANTED) {
////                                isNotGranted = true;
////                                break;
////                            }
////                        }
////                    }
////                    if (!isNotGranted) {
////                        if (onPermissionCallBack != null) {
////                            onPermissionCallBack.onGranted();
////                        }
////                    }
////
////                })
//                .start();
    }

    public interface OnPermissionCallBack {
        void onGranted();
    }
}

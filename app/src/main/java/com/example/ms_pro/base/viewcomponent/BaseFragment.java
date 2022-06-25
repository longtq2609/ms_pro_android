package com.example.ms_pro.base.viewcomponent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.FragmentUtils;
import com.example.ms_pro.base.util.CLog;

/**
 * Created by anhth on 1/27/18.
 */

public abstract class BaseFragment extends Fragment {

    public static final int POSITION_NONE = -1;
    public static final int POSITION_HOME = 0;
    public static final int POSITION_NOTIFICATION = 1;
    public static final int POSITION_CARD = 2;
    public static final int POSITION_PROFILE = 3;


    private static final int BASE_REQUEST_GPS_CODE = 1010;
    public boolean isAttached = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CLog.d("fragment: " + this.getClass().getSimpleName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.isAttached = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.isAttached = false;
    }

    public String getTAG() {
        return BaseFragment.class.getSimpleName();
    }

    public void addFragment(Fragment fragment, @IdRes int frameId) {
        try {
            FragmentUtils.add(getChildFragmentManager(), fragment, frameId);
        } catch (Exception ignored) {
        }
    }

    public void replaceFragment(Fragment fragment, @IdRes int frameId) {
        try {
            FragmentUtils.replace(getChildFragmentManager(), fragment, frameId, true);
        } catch (Exception ignored) {

        }
    }

    public void removeFragment(Fragment fragment) {
        try {
            FragmentUtils.remove(fragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface OnBackPressFragmentCallBack {
        void onBack();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

}

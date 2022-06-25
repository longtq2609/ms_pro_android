package com.example.ms_pro.base.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.ms_pro.base.viewcomponent.BaseFragment;

public abstract class CoreFragment<VB extends ViewDataBinding> extends BaseFragment {

    public VB fragmentViewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentViewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        fragmentViewBinding.setLifecycleOwner(this);
        init();
        return fragmentViewBinding.getRoot();
    }

    /**
     * tham chieu den layout cua activity
     *
     * @return layout cua activity
     */
    protected abstract Integer getLayoutId();

    /**
     * Override ham nay neu muon lam gi sau khi tao
     */
    protected void init() {
        setupView();
        listener();
        setupList();
        initViewModel();
        loadData();
    }

    protected void loadData() {

    }

    protected abstract void initViewModel();

    /**
     * Override ham nay de lang nghe su kien tu view
     */
    protected abstract void listener();

    /**
     * override ham nay de setup cac view
     */
    protected abstract void setupView();

    /**
     * Override ham nay de setup recyclerview, listview trong activity
     */
    protected void setupList() {

    }

    public void openNewActivityForResult(Bundle bundle, Class c, int requestcode) {
        try {
            Intent intent = new Intent(getActivity(), c);
            intent.putExtra(getBaseActivity().BUNDLE_KEY, bundle);
            startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openNewActivity(Bundle bundle, Class c) {
        try {
            Intent intent = new Intent(getActivity(), c);
            intent.putExtra(getBaseActivity().BUNDLE_KEY, bundle);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

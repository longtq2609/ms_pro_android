package com.example.ms_pro.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.example.ms_pro.R;
import com.blankj.utilcode.util.KeyboardUtils;
import com.example.ms_pro.base.viewcomponent.BaseActivity;


public abstract class CoreActivity<VB extends ViewDataBinding> extends BaseActivity {

    public VB activityViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewBinding = DataBindingUtil.setContentView(this, getLayoutId());
        activityViewBinding.setLifecycleOwner(this);
        init();
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
        getIncomingData();
        setupView();
        listener();
        setupList();
        initViewModel();
        loadData();
    }

    protected void initViewModel() {
    }

    protected void loadData() {

    }

    /**
     * Override ham nay neu muon lay du lieu dau tien
     */
    protected void getIncomingData() {

    }

    /**
     * Override ham nay de setup recyclerview, listview trong activity
     */
    protected void setupList() {

    }

    /**
     * Override ham nay de lang nghe su kien tu view
     */
    protected abstract void listener();

    /**
     * override ham nay de setup cac view
     */
    protected abstract void setupView();

    protected int animGoIn() {
        return R.anim.activity_come_in;
    }

    protected int animGoOut() {
        return R.anim.activity_come_out;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.hideSoftInput(this);
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) this;
    }

}

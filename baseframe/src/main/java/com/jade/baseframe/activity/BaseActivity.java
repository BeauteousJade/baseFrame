package com.jade.baseframe.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.jade.baseframe.activity.listener.BackPressable;
import com.jade.baseframe.activity.listener.OnActivityResultListener;
import com.jade.baseframe.helper.delegate.ActivityResultDelegate;
import com.jade.baseframe.helper.delegate.BackPressDelegate;
import com.jade.baseframe.fragment.BaseFragment;

public abstract class BaseActivity extends AppCompatActivity {

    private BaseFragment mCurrentFragment;
    private final ActivityResultDelegate mActivityResultDelegate = new ActivityResultDelegate();
    private final BackPressDelegate mBackPressDelegate = new BackPressDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onPrepare();
        replaceFragment(getFragmentContainerId(), buildCurrentFragment());
        addBackPressable(mCurrentFragment);
    }

    /**
     * 处理有关Activity的操作，比如，从intent获取相应的数据,初始化和操作View等
     * 不推荐在这个方法里面做其他的事情
     */
    protected void onPrepare() {
    }

    protected final void replaceFragment(@IdRes int fragmentContainerId, BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentContainerId, fragment);
        transaction.commitAllowingStateLoss();
        removeBackPressable(mCurrentFragment);
        addBackPressable(fragment);
        mCurrentFragment = fragment;
    }

    public final void addOnActivityResultListener(OnActivityResultListener listener) {
        mActivityResultDelegate.addOnActivityResultListener(listener);
    }

    public final void removeOnActivityResultListener(OnActivityResultListener listener) {
        mActivityResultDelegate.removeOnActivityResultListener(listener);
    }

    public final void addBackPressable(BackPressable backpressable) {
        mBackPressDelegate.addBackPressable(backpressable);
    }

    public final void addBackPressable(BackPressable backPressable, int index) {
        mBackPressDelegate.addBackPressable(backPressable, index);
    }

    public final void removeBackPressable(BackPressable backPressable) {
        mBackPressDelegate.removeBackPressable(backPressable);
    }

    @Override
    public void onBackPressed() {
        mBackPressDelegate.onBackPress();
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!mActivityResultDelegate.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @IdRes
    protected abstract int getFragmentContainerId();

    protected abstract <T extends BaseFragment> T buildCurrentFragment();

}

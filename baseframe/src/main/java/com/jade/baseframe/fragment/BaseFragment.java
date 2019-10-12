package com.jade.baseframe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blade.annotation.Provides;
import com.jade.baseframe.activity.BaseActivity;
import com.jade.baseframe.activity.listener.BackPressable;
import com.jade.baseframe.activity.listener.OnActivityResultListener;
import com.jade.baseframe.helper.Ids;
import com.jade.baseframe.helper.delegate.ActivityResultDelegate;
import com.jade.baseframe.helper.delegate.BackPressDelegate;
import com.jade.baseframe.mvp.Presence;
import com.jade.baseframe.mvp.Presenter;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseFragment extends Fragment implements BackPressable, OnActivityResultListener, Presence {

    private final ActivityResultDelegate mActivityResultDelegate = new ActivityResultDelegate();
    private final BackPressDelegate mBackPressDelegate = new BackPressDelegate();
    private final Map<String, Object> mExtrasMap = new HashMap<>();
    private Presenter mPresenter;

    @Provides(Ids.FRAGMENT)
    BaseFragment mFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onPrepare(view);
        onPrepareCallerContext(view);
        mPresenter = onCreatePresenter();
        mPresenter.create(this);
        mPresenter.bind(this, mExtrasMap, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.destroy();
    }

    /**
     * 处理关于Fragment的一些操作，比如说从argument中获取数据，初始化和操作View等。
     *
     * @see BaseActivity#onPrepare()
     */
    protected void onPrepare(View view) {
    }

    /**
     * 初始化一些关于注入的数据，通常调用{@link #putExtras(String, Object)}方法向注入源里面添加数据。
     * 严禁除了做注入数据之外的操作。
     *
     * @param view
     */
    @CallSuper
    protected void onPrepareCallerContext(View view) {
        mFragment = this;
    }

    protected Presenter onCreatePresenter() {
        return new Presenter();
    }

    public final void addOnActivityResultListener(OnActivityResultListener listener) {
        mActivityResultDelegate.addOnActivityResultListener(listener);
    }

    public final void removeOnActivityResultListener(OnActivityResultListener listener) {
        mActivityResultDelegate.removeOnActivityResultListener(listener);
    }

    public final void addBackPressable(BackPressable backPressable) {
        mBackPressDelegate.addBackPressable(backPressable);
    }

    public final void removeBackPressable(BackPressable backPressable) {
        mBackPressDelegate.removeBackPressable(backPressable);
    }

    @Override
    public final boolean onBackPress() {
        return mBackPressDelegate.onBackPress();
    }

    @Override
    public boolean onResult(int requestCode, int resultCode, @Nullable Intent data) {
        return mActivityResultDelegate.onActivityResult(requestCode, resultCode, data);
    }

    protected final void putExtras(String key, Object data) {
        mExtrasMap.put(key, data);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @SuppressWarnings("unchecked")
    @Override
    public final <T extends BaseFragment> T getCurrentFragment() {
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T extends BaseActivity> T getCurrentActivity() {
        return (T) getActivity();
    }

    @Override
    public final View getRootView() {
        return getView();
    }
}

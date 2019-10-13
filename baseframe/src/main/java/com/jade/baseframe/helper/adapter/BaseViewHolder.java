package com.jade.baseframe.helper.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jade.baseframe.activity.BaseActivity;
import com.jade.baseframe.fragment.BaseFragment;
import com.jade.baseframe.mvp.Presence;
import com.jade.baseframe.mvp.Presenter;

import java.util.HashMap;
import java.util.Map;

final class BaseViewHolder extends RecyclerView.ViewHolder {

    public final Presenter mPresenter;
    public final Presence mPresence;
    public final Map<String, Object> mExtrasMap = new HashMap<>();

    public BaseViewHolder(@NonNull View itemView, Presenter presenter, BaseFragment fragment, Map<String, Object> extrasMap) {
        super(itemView);
        mPresenter = presenter;
        mPresence = new Presence() {

            @SuppressWarnings("unchecked")
            @Override
            public <T extends BaseFragment> T getCurrentFragment() {
                return (T) fragment;
            }

            @SuppressWarnings("unchecked")
            @Override
            public <T extends BaseActivity> T getCurrentActivity() {
                return (T) fragment.getActivity();
            }

            @Override
            public View getRootView() {
                return itemView;
            }
        };
        mExtrasMap.putAll(extrasMap);
    }
}

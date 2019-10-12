package com.jade.baseframe.mvp;

import android.view.View;

import com.jade.baseframe.activity.BaseActivity;
import com.jade.baseframe.fragment.BaseFragment;

public interface Presence {
    <T extends BaseFragment> T getCurrentFragment();

    <T extends BaseActivity> T getCurrentActivity();

    View getRootView();
}

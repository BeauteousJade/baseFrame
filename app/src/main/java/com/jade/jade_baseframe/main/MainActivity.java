package com.jade.jade_baseframe.main;

import com.jade.baseframe.activity.BaseActivity;
import com.jade.baseframe.fragment.BaseFragment;
import com.jade.jade_baseframe.R;

public class MainActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T extends BaseFragment> T buildCurrentFragment() {
        return (T) MainFragment.newInstance();
    }
}

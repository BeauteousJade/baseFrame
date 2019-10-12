package com.jade.jade_baseframe.recyclerfragment;

import android.content.Intent;

import com.jade.baseframe.activity.BaseActivity;
import com.jade.baseframe.fragment.BaseFragment;
import com.jade.jade_baseframe.R;

public class TestActivity extends BaseActivity {

    public static void startActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, TestActivity.class);
        activity.startActivity(intent);
    }

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
        return (T) TestRecyclerViewFragment.newInstance();
    }
}

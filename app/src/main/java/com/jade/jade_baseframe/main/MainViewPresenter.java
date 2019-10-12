package com.jade.jade_baseframe.main;

import android.view.View;

import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;
import com.jade.jade_baseframe.recyclerfragment.TestActivity;

public class MainViewPresenter extends Presenter {

    private View mRecyclerViewButton;
    private View mRecyclerViewIncludeHeadAndFooterButton;

    @Override
    protected void onCreate() {
        mRecyclerViewButton = getRootView().findViewById(R.id.recycler_view_button);
        mRecyclerViewIncludeHeadAndFooterButton = getRootView().findViewById(R.id.recycler_view_head_footer_button);
    }

    @Override
    protected void onBind() {
        mRecyclerViewButton.setOnClickListener(v -> TestActivity.startActivity(getCurrentActivity()));
        mRecyclerViewIncludeHeadAndFooterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

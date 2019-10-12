package com.jade.jade_baseframe.main;

import com.jade.baseframe.fragment.BaseFragment;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;

public class MainFragment extends BaseFragment {

    public static BaseFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected Presenter onCreatePresenter() {
        Presenter presenter = new Presenter();
        presenter.addPresenter(new MainViewPresenter());
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }
}

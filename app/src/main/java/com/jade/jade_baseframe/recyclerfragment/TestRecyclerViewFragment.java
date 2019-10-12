package com.jade.jade_baseframe.recyclerfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jade.baseframe.fragment.RecyclerViewFragment;
import com.jade.baseframe.helper.adapter.RecyclerAdapterDelegate;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;

public class TestRecyclerViewFragment extends RecyclerViewFragment {

    public static TestRecyclerViewFragment newInstance() {
        return new TestRecyclerViewFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
    }

    @NonNull
    @Override
    protected RecyclerAdapterDelegate<?> onCreateOriginDelegate() {
        return new RecyclerOriginDelegate(this);
    }

    @Nullable
    @Override
    protected RecyclerAdapterDelegate<?> onCreateFooterDelegate() {
        return new RecyclerViewFooterDelegate(this);
    }

    @Nullable
    @Override
    protected RecyclerAdapterDelegate<?> onCreateHeaderDelegate() {
        return new RecyclerHeaderDelegate();
    }

    @Override
    protected Presenter onCreatePresenter() {
        Presenter presenter = new Presenter();
        presenter.addPresenter(new RefreshPresenter());
        return presenter;
    }
}

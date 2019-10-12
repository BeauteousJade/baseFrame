package com.jade.jade_baseframe.recyclerfragment;

import androidx.annotation.NonNull;

import com.jade.baseframe.fragment.RecyclerViewFragment;
import com.jade.baseframe.helper.adapter.RecyclerAdapterDelegate;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;

public class RecyclerViewFooterDelegate implements RecyclerAdapterDelegate {

    private static final int FOOTER_FLAG = 1 << 28;

    private RecyclerViewFragment mRecyclerViewFragment;

    public RecyclerViewFooterDelegate(RecyclerViewFragment recyclerViewFragment) {
        mRecyclerViewFragment = recyclerViewFragment;
    }

    @Override
    public int getStartPosition() {
        return mRecyclerViewFragment.getRecyclerAdpater().getItemCount() - 3;
    }

    @Override
    public int getEndPosition() {
        return mRecyclerViewFragment.getRecyclerAdpater().getItemCount();
    }

    @NonNull
    @Override
    public Presenter onCreatePresenter(int viewType) {
        return new Presenter();
    }

    @Override
    public int getPrefixFlag() {
        return FOOTER_FLAG;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.footer_item_layout;
    }
}

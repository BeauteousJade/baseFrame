package com.jade.jade_baseframe.recyclerfragment;

import androidx.annotation.NonNull;

import com.jade.baseframe.fragment.RecyclerViewFragment;
import com.jade.baseframe.helper.adapter.RecyclerAdapterDelegate;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;

public class RecyclerOriginDelegate implements RecyclerAdapterDelegate {

    private static final int HEAD_FLAG = 1 << 30;

    private RecyclerViewFragment mRecyclerViewFragment;

    public RecyclerOriginDelegate(RecyclerViewFragment recyclerViewFragment) {
        mRecyclerViewFragment = recyclerViewFragment;
    }

    @Override
    public int getStartPosition() {
        RecyclerAdapterDelegate headerDelegate = mRecyclerViewFragment.getHeaderDelegate();
        return headerDelegate == null ? 0 : headerDelegate.getEndPosition();
    }

    @Override
    public int getEndPosition() {
        RecyclerAdapterDelegate footerDelegate = mRecyclerViewFragment.getFooterDelegate();
        return footerDelegate == null ? mRecyclerViewFragment.getRecyclerAdpater().getItemCount() : footerDelegate.getStartPosition();
    }

    @NonNull
    @Override
    public Presenter onCreatePresenter(int viewType) {
        return new ItemPresenter();
    }

    @Override
    public int getPrefixFlag() {
        return HEAD_FLAG;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_layout;
    }
}

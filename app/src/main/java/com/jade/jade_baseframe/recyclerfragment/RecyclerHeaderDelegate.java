package com.jade.jade_baseframe.recyclerfragment;

import androidx.annotation.NonNull;

import com.jade.baseframe.helper.adapter.RecyclerAdapterDelegate;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;

public class RecyclerHeaderDelegate implements RecyclerAdapterDelegate {

    private final static int HEADER_FLAG = 1 << 29;

    @Override
    public int getStartPosition() {
        return 0;
    }

    @Override
    public int getEndPosition() {
        return 2;
    }

    @NonNull
    @Override
    public Presenter onCreatePresenter(int viewType) {
        return new Presenter();
    }

    @Override
    public int getPrefixFlag() {
        return HEADER_FLAG;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.header_item_layout;
    }
}

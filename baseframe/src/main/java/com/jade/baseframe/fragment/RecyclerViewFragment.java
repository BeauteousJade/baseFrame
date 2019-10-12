package com.jade.baseframe.fragment;

import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.jade.baseframe.R;
import com.jade.baseframe.helper.adapter.BaseRecyclerAdapter;
import com.jade.baseframe.helper.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.jade.baseframe.helper.adapter.RecyclerAdapterDelegate;

public abstract class RecyclerViewFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @CallSuper
    @Override
    protected void onPrepare(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(onCreateOriginDelegate());
        mHeaderAndFooterRecyclerViewAdapter.setHeaderDelegate(onCreateHeaderDelegate());
        mHeaderAndFooterRecyclerViewAdapter.setFooterDelegate(onCreateFooterDelegate());
        mLayoutManager = onCreateLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHeaderAndFooterRecyclerViewAdapter.onDestroy(mRecyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    public BaseRecyclerAdapter getRecyclerAdpater() {
        return mHeaderAndFooterRecyclerViewAdapter;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public final RecyclerAdapterDelegate<?> getOriginDelegate() {
        return mHeaderAndFooterRecyclerViewAdapter.getOriginDelegate();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public final RecyclerAdapterDelegate<?> getHeaderDelegate() {
        return mHeaderAndFooterRecyclerViewAdapter.getHeaderDelegate();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public final RecyclerAdapterDelegate<?> getFooterDelegate() {
        return mHeaderAndFooterRecyclerViewAdapter.getFooterDelegate();
    }

    @SuppressWarnings("unchecked")
    public final <T extends RecyclerView.LayoutManager> T getLayoutManager() {
        return (T) mLayoutManager;
    }

    @SuppressWarnings("unchecked")
    public final <T extends RecyclerView> T getRecyclerView() {
        return (T) mRecyclerView;
    }

    @Nullable
    protected RecyclerAdapterDelegate<?> onCreateHeaderDelegate() {
        return null;
    }

    @Nullable
    protected RecyclerAdapterDelegate<?> onCreateFooterDelegate() {
        return null;
    }

    @NonNull
    protected abstract RecyclerView.LayoutManager onCreateLayoutManager();

    @NonNull
    protected abstract RecyclerAdapterDelegate<?> onCreateOriginDelegate();
}

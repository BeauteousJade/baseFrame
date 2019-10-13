package com.jade.baseframe.helper.adapter;

import androidx.annotation.NonNull;

import com.jade.baseframe.helper.Ids;
import com.jade.baseframe.mvp.Presenter;

import java.util.List;

public final class HeaderAndFooterRecyclerViewAdapter extends BaseRecyclerAdapter<Object> {

    private RecyclerAdapterDelegate mHeaderDelegate;
    private RecyclerAdapterDelegate mOriginDelegate;
    private RecyclerAdapterDelegate mFooterDelegate;

    public HeaderAndFooterRecyclerViewAdapter(@NonNull RecyclerAdapterDelegate originDelegate) {
        mOriginDelegate = originDelegate;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.mExtrasMap.put(Ids.HEADER_DELEGATE, getHeaderDelegate());
        holder.mExtrasMap.put(Ids.ORIGIN_DELEGATE, getOriginDelegate());
        holder.mExtrasMap.put(Ids.FOOTER_DELEGATE, getFooterDelegate());
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return getValidDelegateByPosition(position).getViewType(position);
    }

    @Override
    public int getItemViewLayout(int viewType) {
        return getValidDelegateByViewType(viewType).getLayoutId(viewType);
    }

    @Override
    protected Presenter onCreatePresenter(int viewType) {
        return getValidDelegateByViewType(viewType).onCreatePresenter(viewType);
    }

    @NonNull
    private RecyclerAdapterDelegate getValidDelegateByViewType(int viewType) {
        RecyclerAdapterDelegate delegate = mOriginDelegate;
        if (mHeaderDelegate != null && mHeaderDelegate.checkRangeByViewType(viewType)) {
            delegate = mHeaderDelegate;
        } else if (mFooterDelegate != null && mFooterDelegate.checkRangeByViewType(viewType)) {
            delegate = mFooterDelegate;
        }
        return delegate;
    }

    private RecyclerAdapterDelegate getValidDelegateByPosition(int position) {
        RecyclerAdapterDelegate delegate = mOriginDelegate;
        if (mHeaderDelegate != null && mHeaderDelegate.checkRangeByPosition(position)) {
            delegate = mHeaderDelegate;
        } else if (mFooterDelegate != null && mFooterDelegate.checkRangeByPosition(position)) {
            delegate = mFooterDelegate;
        }
        return delegate;
    }

    public void setHeaderDelegate(RecyclerAdapterDelegate headDelegate) {
        mHeaderDelegate = headDelegate;
    }

    public void setFooterDelegate(RecyclerAdapterDelegate footerDelegate) {
        mFooterDelegate = footerDelegate;
    }

    public RecyclerAdapterDelegate getHeaderDelegate() {
        return mHeaderDelegate;
    }

    public RecyclerAdapterDelegate getFooterDelegate() {
        return mFooterDelegate;
    }

    public RecyclerAdapterDelegate getOriginDelegate() {
        return mOriginDelegate;
    }
}

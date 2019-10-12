package com.jade.baseframe.helper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jade.baseframe.fragment.BaseFragment;
import com.jade.baseframe.helper.diff.Diff;
import com.jade.baseframe.helper.diff.DiffUtilCallback;
import com.jade.baseframe.mvp.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRecyclerAdapter<U> extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Object> mEmptyPlayloads = new ArrayList<>();
    private List<U> mDataList;
    private BaseFragment mFragment;
    private Map<String, Object> mExtraMap = new HashMap<>();

    public BaseRecyclerAdapter() {
        this(new ArrayList<>());
    }

    public BaseRecyclerAdapter(List<U> dataList) {
        mDataList = dataList;
    }

    public final void addItemList(List<U> newDataList) {
        addItemList(getDataList().size(), newDataList);
    }

    public final void addItemList(int index, List<U> newDataList) {
        getDataList().addAll(index, newDataList);
        notifyItemRangeInserted(index, newDataList.size());
    }

    public final void addItem(int index, U u) {
        addItemList(index, Collections.singletonList(u));
    }

    public final void addItem(U u) {
        addItemList(Collections.singletonList(u));
    }

    public final void remove(U u) {
        final int position = getDataList().indexOf(u);
        if (position != -1) {
            remove(position);
        }
    }

    public final void remove(int index) {
        getDataList().remove(index);
        notifyItemRemoved(index);
    }

    public final void setItemList(List<U> newDatList) {
        final U item = newDatList.get(0);
        getDataList().clear();
        if (item instanceof Diff) {
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback<>(getDataList(), newDatList), false);
            getDataList().addAll(newDatList);
            diffResult.dispatchUpdatesTo(this);
        } else {
            getDataList().addAll(newDatList);
            notifyDataSetChanged();
        }
    }

    public final void setData(int index, U u, boolean notifyDataSetChanged) {
        if (index >= 0 && getDataList().size() > index) {
            getDataList().set(index, u);
            if (!notifyDataSetChanged) {
                notifyItemChanged(index);
            } else {
                notifyDataSetChanged();
            }
        }
    }

    public final void setRangeData(int startIndex, int endIndex, List<U> datList) {
        final U item = datList.get(0);
        List<U> oldDataList = getDataList();
        List<U> newDataList = new ArrayList<>();
        newDataList.addAll(oldDataList.subList(0, startIndex));
        newDataList.addAll(datList);
        newDataList.addAll(oldDataList.subList(endIndex, oldDataList.size()));
        if (item instanceof Diff) {
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback<>(oldDataList, newDataList), false);
            getDataList().clear();
            getDataList().addAll(newDataList);
            diffResult.dispatchUpdatesTo(this);
        } else {
            getDataList().clear();
            getDataList().addAll(newDataList);
            notifyDataSetChanged();
        }
    }

    public final List<U> getDataList() {
        return mDataList;
    }

    public final U getItem(int index) {
        return getDataList().get(index);
    }

    @NonNull
    @Override
    public final BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(getItemViewLayout(viewType), viewGroup, false);
        final Presenter presenter = onCreatePresenter(viewType);
        BaseViewHolder viewHolder = new BaseViewHolder(view, presenter, mFragment, mExtraMap);
        presenter.create(viewHolder.mPresence);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.mPresenter.bind(onCreateContext(position, payloads), holder.mExtrasMap, holder.itemView);
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        onBindViewHolder(holder, position, mEmptyPlayloads);
    }

    @Override
    public final int getItemCount() {
        return getDataList().size();
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        holder.mPresenter.unBind();
    }

    private Object onCreateContext(int position, List<Object> payloads) {
        CallerContext context = new CallerContext();
        context.mItem = getDataList().get(position);
        context.mPosition = position;
        context.mPayLoad = payloads;
        return context;
    }

    /**
     * destroy所有的Presenter
     *
     * @param recyclerView
     */
    public final void onDestroy(RecyclerView recyclerView) {
        final int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = recyclerView.getChildAt(i);
            final BaseViewHolder viewHolder = (BaseViewHolder) recyclerView.getChildViewHolder(child);
            viewHolder.mPresenter.destroy();
        }
    }

    public final void setCurrentFragment(BaseFragment fragment) {
        mFragment = fragment;
    }

    public final void putExtra(String id, Object object) {
        mExtraMap.put(id, object);
    }

    /**
     * 返回布局
     *
     * @param viewType
     * @return
     */
    @LayoutRes
    public abstract int getItemViewLayout(int viewType);

    /**
     * 返回Presenter
     *
     * @param viewType
     * @return
     */
    protected abstract Presenter onCreatePresenter(int viewType);
}
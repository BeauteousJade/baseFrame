package com.jade.baseframe.helper.diff;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public final class DiffUtilCallback<T> extends DiffUtil.Callback {

    private List<T> mOldList;
    private List<T> mNewList;

    public DiffUtilCallback(List<T> oldList, List<T> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override

    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        final T oldItem = mOldList.get(oldItemPosition);
        final T newItem = mNewList.get(newItemPosition);
        if (oldItem instanceof Diff && newItem instanceof Diff) {
            return ((Diff) oldItem).areItemsTheSame((Diff) newItem);
        }
        return Objects.equals(mOldList.get(oldItemPosition), newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final T oldItem = mOldList.get(oldItemPosition);
        final T newItem = mNewList.get(newItemPosition);
        if (oldItem instanceof Diff && newItem instanceof Diff) {
            return ((Diff) oldItem).onContentTheme((Diff) newItem);
        }
        return Objects.equals(mOldList.get(oldItemPosition), newItem);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        final T oldItem = mOldList.get(oldItemPosition);
        final T newItem = mNewList.get(newItemPosition);
        if (oldItem instanceof Diff && newItem instanceof Diff) {
            return ((Diff) oldItem).getChangePayload((Diff) newItem);
        }
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
package com.jade.baseframe.helper.loadmore;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public final class LoadMoreHelper extends RecyclerView.OnScrollListener {

    private RecyclerView mRecyclerView;
    private LoadMoreCallback mCallback;

    public LoadMoreHelper(@NonNull RecyclerView recyclerView, @NonNull LoadMoreCallback callback) {
        mRecyclerView = recyclerView;
        mCallback = callback;
    }

    public void attach() {
        mRecyclerView.addOnScrollListener(this);
    }

    public void detch() {
        mRecyclerView.removeOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.getAdapter() != null) {
            dispatchLoadMore(recyclerView.getLayoutManager(), recyclerView.getAdapter());
        }
    }

    private void dispatchLoadMore(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        int lastItemPosition = getLastItemPosition(layoutManager);
        int size = adapter.getItemCount();
        if (mCallback.getLoadMoreState() != LoadMoreCallback.LoadMoreState.LOAD_MORE
                && lastItemPosition >= size - mCallback.distanceFromLastItem()) {
            mCallback.onStartMore();
        }
    }

    private int getLastItemPosition(RecyclerView.LayoutManager layoutManager) {
        int lastItemPosition = Integer.MAX_VALUE;
        if (layoutManager instanceof LinearLayoutManager) {
            lastItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] positions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
            int max = 0;
            for (int position : positions) {
                max = Math.max(max, position);
            }
            lastItemPosition = max;
        } else if (layoutManager instanceof LoadMore) {
            lastItemPosition = ((LoadMore) layoutManager).getLastItemPosition();
        }
        return lastItemPosition;
    }
}

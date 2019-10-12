package com.jade.jade_baseframe.recyclerfragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jade.baseframe.fragment.RecyclerViewFragment;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.R;
import com.jade.jade_baseframe.network.Response;
import com.jade.jade_baseframe.network.request.RequestCallback;
import com.jade.jade_baseframe.network.response.FemaleNameResponse;

public class RefreshPresenter extends Presenter {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerViewFragment mRecyclerViewFragment;
    private final FemaleNameRequest mRequest = new FemaleNameRequest();
    private final RequestCallback<FemaleNameResponse> mRequestCallback = new RequestCallback<FemaleNameResponse>() {
        @SuppressWarnings("unchecked")
        @Override
        public void onResult(Response<FemaleNameResponse> response) {
            mRecyclerViewFragment.getRecyclerAdpater().setItemList(response.getData().getIntroduceList());
            mRefreshLayout.setRefreshing(false);
        }
    };

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        mRequest.cancel();
        mRequest.enqueue(mRequestCallback);
    };

    @Override
    protected void onCreate() {
        mRefreshLayout = getRootView().findViewById(R.id.refresh_layout);
        mRecyclerViewFragment = getCurrentFragment();
    }

    @Override
    protected void onBind() {
        mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mRefreshLayout.setRefreshing(true);
        mRequest.cancel();
        mRequest.enqueue(mRequestCallback);
    }

    @Override
    protected void onDestroy() {
        mRequest.cancel();
    }
}

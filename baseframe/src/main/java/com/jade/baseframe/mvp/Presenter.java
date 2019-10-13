package com.jade.baseframe.mvp;

import android.view.View;

import androidx.annotation.IntDef;

import com.blade.inject.Blade;
import com.jade.baseframe.activity.BaseActivity;
import com.jade.baseframe.fragment.BaseFragment;
import com.jade.baseframe.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Presenter implements Presence {

    @PresenterState
    private int mState;
    private Presence mPresence;

    private List<Presenter> mPresenterList = new ArrayList<>();

    /**
     * create生命周期的回调
     * 2种case:
     * 1. mState = INIT,执行顺序：init -> create。
     * 2. mState为其他状态，非法情况。
     *
     * @param presence
     */
    public final void create(Presence presence) {
        if (mState == PresenterState.INIT) {
            mPresence = presence;
            mState = PresenterState.CREATE;
            onCreate();
            traverseChildPresenter(presenter -> presenter.create(presence));
        } else {
            PresenterErrorUtils.errorState(mState, PresenterState.CREATE);
        }
    }

    /**
     * bind 生命周期的回调
     * 3种case：
     * 1. mState = CREATE,执行顺序：create -> bind。
     * 2. mState = BIND,执行顺序：bind -> unBind ->bind。
     * 3. mState为其他状态，非法情况。
     *
     * @param source
     * @param map
     * @param view
     */
    public final void bind(Object source, Map<String, Object> map, View view) {
        if (mState == PresenterState.CREATE || mState == PresenterState.BIND || mState == PresenterState.UN_BIND) {
            if (mState == PresenterState.BIND) {
                unBind();
            }
            mState = PresenterState.BIND;
            Blade.inject(this, source, map);
            onBind();
            traverseChildPresenter(presenter -> presenter.bind(source, map, view));
        } else {
            PresenterErrorUtils.errorState(mState, PresenterState.BIND);
        }
    }

    /**
     * unBind生命周期的回调
     * 2种case：
     * 1. mState = BIND,执行顺序：bind -> unBind。
     * 2. mState为其他状态，非法情况。
     * 私有方法，不允许外部调用。
     */
    public final void unBind() {
        if (mState == PresenterState.BIND) {
            mState = PresenterState.UN_BIND;
            onUnBind();
            traverseChildPresenter(Presenter::unBind);
        } else {
            PresenterErrorUtils.errorState(mState, PresenterState.UN_BIND);
        }
    }

    /**
     * destroy生命周期的回调
     * 4种case：
     * 1. mState = CREATE, 执行顺序：create -> destroy。
     * 2. mState = BIND,执行顺序：bind -> unBind -> destroy；
     * 3. mState = unBind,执行顺序：unBind -> destroy。
     * 4. mState为其他状态，非法情况。
     */
    public final void destroy() {
        if (mState == PresenterState.CREATE || mState == PresenterState.UN_BIND) {
            mState = PresenterState.DESTROY;
            onDestroy();
            traverseChildPresenter(Presenter::destroy);
        } else if (mState == PresenterState.BIND) {
            unBind();
            destroy();
        } else {
            PresenterErrorUtils.errorState(mState, PresenterState.DESTROY);
        }
    }

    protected void onCreate() {
    }

    protected void onBind() {
    }

    protected void onUnBind() {
    }

    protected void onDestroy() {
    }

    private void traverseChildPresenter(PresenterAction presenterAction) {
        if (!CollectionUtils.isEmpty(mPresenterList)) {
            for (Presenter presenter : mPresenterList) {
                presenterAction.onAction(presenter);
            }
        }
    }

    public final void addPresenter(Presenter presenter) {
        mPresenterList.add(presenter);
    }

    @Override
    public final <T extends BaseFragment> T getCurrentFragment() {
        return mPresence.getCurrentFragment();
    }

    @Override
    public final <T extends BaseActivity> T getCurrentActivity() {
        return mPresence.getCurrentActivity();
    }

    @Override
    public final View getRootView() {
        return mPresence.getRootView();
    }

    @IntDef({PresenterState.INIT, PresenterState.CREATE, PresenterState.BIND, PresenterState.UN_BIND, PresenterState.DESTROY})
    public @interface PresenterState {
        int INIT = 0;
        int CREATE = 1;
        int BIND = 2;
        int UN_BIND = 3;
        int DESTROY = 4;
    }
}

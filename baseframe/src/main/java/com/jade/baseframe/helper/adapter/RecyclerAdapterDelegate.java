package com.jade.baseframe.helper.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.jade.baseframe.mvp.Presenter;

public interface RecyclerAdapterDelegate<T> {

    /**
     * 返回该代理需要处理的数据的起始位置
     *
     * @return
     */
    int getStartPosition();

    /**
     * 返回该代理需要处理的数据的结束位置
     *
     * @return
     */
    int getEndPosition();

    /**
     * 创建该ItemView对应的Presenter
     *
     * @param viewType
     * @return
     */
    @NonNull
    Presenter onCreatePresenter(int viewType);

    /**
     * 返回该代理的前缀flag
     * 需要注意的是：代理与代理之间必须是唯一，要求独占1个bit位
     *
     * @return
     */
    int getPrefixFlag();

    /**
     * 返回布局id
     *
     * @param viewType
     * @return
     */
    @LayoutRes
    int getLayoutId(int viewType);

    default int getViewType(int position) {
        return getPrefixFlag();
    }

    /**
     * 通过position来判断指定position是否在该代理的处理范围内
     *
     * @param position
     * @return
     */
    default boolean checkRangeByPosition(int position) {
        return position >= getStartPosition() && position < getEndPosition();
    }

    /**
     * 通过viewType来判断指定position是否在该代理的处理范围内
     *
     * @param viewType
     * @return
     */
    default boolean checkRangeByViewType(int viewType) {
        return (viewType & getPrefixFlag()) != 0;
    }
}

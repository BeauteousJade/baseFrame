package com.jade.baseframe.helper.delegate;

import com.jade.baseframe.activity.listener.BackPressable;
import com.jade.baseframe.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public final class BackPressDelegate implements BackPressable {

    private List<BackPressable> mBackPressableList;

    public void addBackPressable(BackPressable backPressable) {
        addBackPressable(backPressable, CollectionUtils.isEmpty(mBackPressableList) ? 0 : mBackPressableList.size());
    }

    public void addBackPressable(BackPressable backPressable, int index) {
        if (mBackPressableList == null) {
            mBackPressableList = new ArrayList<>();
        }
        mBackPressableList.add(index, backPressable);
    }

    public void removeBackPressable(BackPressable backPressable) {
        if (mBackPressableList != null) {
            mBackPressableList.remove(backPressable);
        }
    }

    @Override
    public boolean onBackPress() {
        if (!CollectionUtils.isEmpty(mBackPressableList)) {
            for (BackPressable backPressable : mBackPressableList) {
                if (backPressable.onBackPress()) {
                    return true;
                }
            }
        }
        return false;
    }
}

package com.jade.baseframe.helper.delegate;

import android.content.Intent;

import androidx.annotation.Nullable;


import com.jade.baseframe.activity.listener.OnActivityResultListener;

import java.util.ArrayList;
import java.util.List;

public final class ActivityResultDelegate {
    private List<OnActivityResultListener> mListenerList;

    public void addOnActivityResultListener(OnActivityResultListener listener) {
        if (mListenerList == null) {
            mListenerList = new ArrayList<>();
        }
        mListenerList.add(listener);
    }

    public void removeOnActivityResultListener(OnActivityResultListener listener) {
        if (mListenerList != null) {
            mListenerList.remove(listener);
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mListenerList != null) {
            for (OnActivityResultListener listener : mListenerList) {
                if (listener.onResult(requestCode, resultCode, data)) {
                    return true;
                }
            }
        }
        return false;
    }
}

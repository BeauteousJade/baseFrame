package com.jade.baseframe.activity.listener;

import android.content.Intent;

import androidx.annotation.Nullable;

@FunctionalInterface
public interface OnActivityResultListener {
    boolean onResult(int requestCode, int resultCode, @Nullable Intent data);
}
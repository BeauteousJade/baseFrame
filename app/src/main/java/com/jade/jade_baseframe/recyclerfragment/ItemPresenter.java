package com.jade.jade_baseframe.recyclerfragment;

import android.widget.TextView;

import com.blade.annotation.Inject;
import com.jade.baseframe.helper.Ids;
import com.jade.baseframe.mvp.Presenter;
import com.jade.jade_baseframe.network.bean.Introduce;
import com.jade.jade_baseframe.R;

public class ItemPresenter extends Presenter {

    private TextView mTextView;

    @Inject(Ids.ITEM_DATA)
    Introduce mItem;

    @Override
    protected void onCreate() {
        mTextView = getRootView().findViewById(R.id.textView);
    }

    @Override
    protected void onBind() {
        mTextView.setText(mItem.getFemaleName());
    }
}

package com.jade.baseframe.helper.adapter;

import com.blade.annotation.Module;
import com.blade.annotation.Provides;
import com.jade.baseframe.helper.Ids;

import java.util.List;

@Module
final class CallerContext {
    @Provides(Ids.ITEM_POSITION)
    public int mPosition;
    @Provides(Ids.ITEM_DATA)
    public Object mItem;
    @Provides(Ids.PAYLOAD)
    public List<?> mPayLoad;
}

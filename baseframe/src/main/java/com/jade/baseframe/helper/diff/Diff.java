package com.jade.baseframe.helper.diff;

public interface Diff {
    boolean areItemsTheSame(Diff diff);

    boolean onContentTheme(Diff diff);

    Object getChangePayload(Diff diff);
}

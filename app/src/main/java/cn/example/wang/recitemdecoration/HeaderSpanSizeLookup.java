package cn.example.wang.recitemdecoration;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by WANG on 17/11/17.
 */

public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private final GridLayoutManager layoutManager;

    public HeaderSpanSizeLookup(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        position = position == 0 ? layoutManager.getSpanCount() : 1;
        return position;
    }
}

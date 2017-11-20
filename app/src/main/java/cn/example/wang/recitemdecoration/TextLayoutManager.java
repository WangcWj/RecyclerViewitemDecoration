package cn.example.wang.recitemdecoration;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by WANG on 17/11/17.
 */

public class TextLayoutManager extends RecyclerView.LayoutManager {



    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}

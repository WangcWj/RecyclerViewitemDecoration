package cn.example.wang.recitemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by WANG on 17/11/15.
 */

public class GridItemdecoration extends RecyclerView.ItemDecoration {
    Paint paint;
    int splitHeight = 10;
    public GridItemdecoration() {
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(28);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int paddingLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = layoutManager.getSpanCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            int childAdapterPosition = parent.getChildAdapterPosition(childAt);

            int bottom = childAt.getBottom();
            int viewLeft = childAt.getLeft();
            int right = childAt.getRight();
            int top = childAt.getTop();
            if(childAdapterPosition>=0 ){
                //bottom
                c.drawRect(paddingLeft,bottom,right,bottom+splitHeight,paint);
                if((childAdapterPosition+1)% spanCount != 0) {
                    //right
                    c.drawRect(right,top,right+splitHeight,bottom,paint);
                }

            }

        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = layoutManager.getSpanCount();
        if(childAdapterPosition>=0 ){
            outRect.bottom =  splitHeight;
            if((childAdapterPosition +1) % spanCount != 0) {
                outRect.right = splitHeight;
            }

        }
    }
}

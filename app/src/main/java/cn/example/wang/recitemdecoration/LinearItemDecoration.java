package cn.example.wang.recitemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by WANG on 17/11/20.
 */

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private List<RecBean.CitiListBean> citiList;
    private Context context;
    private int decoration;
    private int headlerHeight;
    private Rect rect;

    public LinearItemDecoration(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        decoration = dip2px(2);
        headlerHeight = dip2px(50);
        paint.setTextSize(dip2px(15));
        rect = new Rect();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int itemCount1 = state.getItemCount();
        int childCount = parent.getChildCount();
        int paddingLeft = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int childAdapterPosition = parent.getChildAdapterPosition(childView);
            int bottom = childView.getBottom();
            if(childAdapterPosition != itemCount1 -1) {
                paint.setColor(Color.parseColor("#ffffff"));
                c.drawRect(paddingLeft, bottom, right, bottom + decoration, paint);
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
        int childCount = state.getItemCount();
        int position = parent.getChildAdapterPosition(view);
        if(position != childCount -1){
            outRect.bottom = decoration;
        }
    }


    public int dip2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}


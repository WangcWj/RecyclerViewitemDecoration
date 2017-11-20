package cn.example.wang.recitemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by WANG on 17/11/16.
 */

public class LinearHeadItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private List<RecBean.CitiListBean> citiList;
    private Context context;
    private int decoration;
    private int headlerHeight;
    private Rect rect;

    public LinearHeadItemDecoration(Context context) {
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
        int childCount = parent.getChildCount();
        int paddingLeft = parent.getPaddingLeft();
        int paddingRight = parent.getPaddingRight();
        int paddingTop = parent.getPaddingTop();
        int paddingBottom = parent.getPaddingBottom();
        int right = parent.getWidth() - paddingRight;
        int nextTage ,tage = -1;
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);
            int top = childView.getTop();
            int bottom = childView.getBottom();
            if (citiList != null) {
                RecBean.CitiListBean citiListBean = citiList.get(position);
                String name = citiListBean.getName();
                nextTage = tage;
                tage = citiListBean.getTage();

                paint.setColor(Color.WHITE);
                c.drawRect(paddingLeft, top, right, bottom + decoration, paint);
                if(tage == nextTage) {
                    continue;
                }
                    paint.setColor(Color.parseColor("#333333"));
                    paint.getTextBounds(name, 0, name.length(), rect);
                    c.drawText(name, dip2px(10), top - (headlerHeight - rect.height()) / 2, paint);
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
        if (citiList == null) {
            return;
        }
        int position = parent.getChildAdapterPosition(view);
        int tage = citiList.get(position).getTage();
        int nextTage = -1;
        if (position - 1 >= 0) {
            nextTage = citiList.get(position - 1).getTage();
        }
        if(tage != nextTage){
            outRect.top = headlerHeight;
         }else {
            outRect.top = 0;
        }

    }

    public void setCitiList(List<RecBean.CitiListBean> citiList) {
        this.citiList = citiList;
    }

    public int dip2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

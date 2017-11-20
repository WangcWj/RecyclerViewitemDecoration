package cn.example.wang.recitemdecoration;

/**
 * Created by WANG on 17/11/17.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG on 17/11/16.
 */

public class HeadItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private List<RecBean.CitiListBean> citiList;
    private Context context;
    private int decoration;
    private int headlerHeight;
    private int h;
    private Rect rect;
    private Rect rectOver;
    private List<String> index;

    public HeadItemDecoration(Context context) {
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
        h = dip2px(10);
        rect = new Rect();
        rectOver = new Rect();
        index = new ArrayList();
        index.add("北京市");
        index.add("辽宁省");
        index.add("浙江省");
        index.add("河南省");
        index.add("广西壮族自治区");
        index.add("宁夏回族自治区");
        index.add("西藏自治区");

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Log.e("WANG", "onDraw."+"childCount =   "+ parent.getChildCount() +"    stateCount=   "+state.getItemCount());
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Log.e("WANG", "onDrawOver.");
        //该方法调用在RecyclerView的item绘制之后.
        //当每个item消失的时候都会调用一次该方法.因为一个item的加载就是另一个item的消失.
        //调用的步骤就是 :
        //item的position   该position之后的所有已加载的子Item.
        // 0              0123456789
        // 1              0123456789
        // 2              0123456789
        //当滑动的时候RecyclerView的子View是不断的变化的,所以都要重新遍历一下重新设置头布局.
        //当列表滑动切item消失和出现的时候都会调用onDrawOver方法,一旦调用head的绘制步骤一定会执行一次.
        int childCount = parent.getChildCount();
        int paddingLeft = parent.getPaddingLeft();
        int paddingRight = parent.getPaddingRight();
        int right = parent.getWidth() - paddingRight;
        int tage = -1, nextTag;
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int top = childView.getTop();
            int bottom = childView.getBottom();
            int position = parent.getChildAdapterPosition(childView);
            if (citiList != null) {
                RecBean.CitiListBean citiListBean = citiList.get(position);
                nextTag = tage;
                tage = citiListBean.getTage();
                if (tage > 0 && tage == nextTag) continue;
                Log.e("WANG", "列移动:" + "position =   " + position + "    childPosition = " + i);
                String name = index.get((tage - 1) < 0 ? 0 : (tage - 1));
                //该方法再每个Item发生变化的时候都会调用,所以每个Item都可能作为第一个childView出现,
                // 这样的话第一个ChildView肯定会执行绘制头布局的方法,这样我们就需要判断当前的有布局是否需要停留在顶部
                //因为当当前布局是第一个ChildView的时候他的getTop()肯定要小于等于头布局的高度
                int textY = Math.max(top, headlerHeight);
                //这种情只会出现在当该Item是当前屏幕中是第一个子Item并且,下一个item是要绘制head的时候.
                //当出现这种情况的时候将上一个head的布局的高度设置成该View的bottom 随着滑动bottom会不断的减小一直到消失.
                if (position + 1 < citiList.size()) {
                    RecBean.CitiListBean citiListBean1 = citiList.get(position + 1);
                    int tage1 = citiListBean1.getTage();
                    if (tage1 != tage) {
                        Log.e("WANG", "布局开始往上移:" + "position =   " + position + "    childPosition = " + i);
                        textY = bottom;
                    }
                }
                paint.setColor(Color.RED);
                c.drawRect(paddingLeft, textY - headlerHeight, right, textY, paint);
                paint.setColor(Color.WHITE);
                paint.getTextBounds(name, 0, name.length(), rectOver);
                c.drawText(name, dip2px(10), textY - (headlerHeight - rect.height()) / 2, paint);
            }
        }

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
        if (nextTage != tage) {
            outRect.top = headlerHeight;
        } else {
            outRect.top = dip2px(2);
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


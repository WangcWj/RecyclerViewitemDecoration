package cn.example.wang.recitemdecoration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG on 17/11/16.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewholder> {
    List<RecBean.CitiListBean> list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;

    public RecAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void  setRefreshData( List<RecBean.CitiListBean> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
     }

    public void  setLoadMoveData( List<RecBean.CitiListBean> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public RecViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new RecViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecViewholder holder, int position) {
        RecBean.CitiListBean s = list.get(position);
        String name = s.getName();
        holder.textView.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RecViewholder extends RecyclerView.ViewHolder{
        TextView textView;
        public RecViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}

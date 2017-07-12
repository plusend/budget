package com.plusend.budget.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plusend.budget.R;
import com.plusend.budget.activity.DetailActivity;
import com.plusend.budget.model.Detail;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Detail> mDetailList;

    public DetailAdapter(Context mContext, List<Detail> mDetailList) {
        this.mContext = mContext;
        this.mDetailList = mDetailList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailViewHolder(mLayoutInflater.inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(final DetailViewHolder holder, int position) {
        holder.nameTV.setText(mDetailList.get(position).name);
        holder.numTV.setText(String.valueOf(mDetailList.get(position).num));
        holder.nameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.DETAIL, mDetailList.get(holder.getAdapterPosition()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDetailList == null ? 0 : mDetailList.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;
        private TextView numTV;

        DetailViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.name_tv);
            numTV = (TextView) itemView.findViewById(R.id.num_tv);
        }
    }
}

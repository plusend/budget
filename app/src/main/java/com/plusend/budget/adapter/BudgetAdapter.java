package com.plusend.budget.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plusend.budget.R;
import com.plusend.budget.activity.ChildBudgetActivity;
import com.plusend.budget.model.Budget;

import java.util.List;
import java.util.Map;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Budget> mBudgetList;

    public BudgetAdapter(Context mContext, List<Budget> mBudgetList) {
        this.mContext = mContext;
        this.mBudgetList = mBudgetList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BudgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BudgetViewHolder(mLayoutInflater.inflate(R.layout.item_budget, parent, false));
    }

    @Override
    public void onBindViewHolder(final BudgetViewHolder holder, int position) {
        holder.nameTV.setText(mBudgetList.get(position).name);
        String content = "";
        for (Map.Entry<String, Integer> entry : mBudgetList.get(position).mBudgetMap.entrySet()) {
            content = content.concat(entry.getKey()).concat("、");
        }
        content = content.substring(0, content.length() - 1);
        holder.contentTV.setText(content);
        holder.actionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChildBudgetActivity.class);
                intent.putExtra(ChildBudgetActivity.BUDGET, mBudgetList.get(holder.getAdapterPosition()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBudgetList == null ? 0 : mBudgetList.size();
    }

    class BudgetViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;
        private TextView contentTV;
        private TextView actionTV;

        BudgetViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.name_tv);
            contentTV = (TextView) itemView.findViewById(R.id.content_tv);
            actionTV = (TextView) itemView.findViewById(R.id.action_iv);
        }
    }
}

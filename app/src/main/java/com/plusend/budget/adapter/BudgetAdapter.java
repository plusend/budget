package com.plusend.budget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plusend.budget.R;
import com.plusend.budget.model.Budget;
import com.plusend.budget.util.ResourceUtil;

import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Budget> mBudgetList;

    public BudgetAdapter(Context mContext, List<Budget> mBudgetList) {
        this.mBudgetList = mBudgetList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BudgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BudgetViewHolder(mLayoutInflater.inflate(R.layout.item_budget, parent, false));
    }

    @Override
    public void onBindViewHolder(final BudgetViewHolder holder, int position) {
        holder.iconIV.setImageResource(ResourceUtil.getIcon(holder.iconIV.getContext(), mBudgetList.get(position).icon));
        holder.nameTV.setText(ResourceUtil.getString(holder.nameTV.getContext(), mBudgetList.get(position).name));
        holder.numTV.setText(String.valueOf(mBudgetList.get(position).num));
    }

    @Override
    public int getItemCount() {
        return mBudgetList == null ? 0 : mBudgetList.size();
    }

    class BudgetViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIV;
        private TextView nameTV;
        private TextView numTV;

        BudgetViewHolder(View itemView) {
            super(itemView);
            iconIV = (ImageView) itemView.findViewById(R.id.icon_iv);
            nameTV = (TextView) itemView.findViewById(R.id.name_tv);
            numTV = (TextView) itemView.findViewById(R.id.num_tv);
        }
    }
}

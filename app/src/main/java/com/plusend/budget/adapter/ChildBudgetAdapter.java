package com.plusend.budget.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.plusend.budget.R;
import com.plusend.budget.model.Budget;

public class ChildBudgetAdapter extends RecyclerView.Adapter<ChildBudgetAdapter.BudgetViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Budget mBudget;

    public ChildBudgetAdapter(Context mContext, Budget mBudget) {
        this.mContext = mContext;
        this.mBudget = mBudget;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BudgetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BudgetViewHolder(mLayoutInflater.inflate(R.layout.item_child_budget, parent, false));
    }

    @Override
    public void onBindViewHolder(final BudgetViewHolder holder, int position) {
        final String name = mBudget.mBudgetMap.keyAt(position);
        int num = mBudget.mBudgetMap.valueAt(position);
        holder.nameTV.setText(name);
        holder.numET.setText(String.valueOf(num));
        holder.numET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mBudget.mBudgetMap.put(name, Integer.valueOf(s.toString()));
                } else {
                    holder.numET.setText("0");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBudget.mBudgetMap.size();
    }

    class BudgetViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;
        private EditText numET;

        BudgetViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.name_tv);
            numET = (EditText) itemView.findViewById(R.id.num_et);
        }
    }
}

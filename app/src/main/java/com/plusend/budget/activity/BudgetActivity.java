package com.plusend.budget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.plusend.budget.R;
import com.plusend.budget.adapter.BudgetAdapter;
import com.plusend.budget.app.BudgetApplication;

public class BudgetActivity extends AppCompatActivity {
    private RecyclerView mBudgetRV;
    private BudgetAdapter mBudgetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        mBudgetRV = (RecyclerView) findViewById(R.id.budget_rv);
        mBudgetRV.setLayoutManager(new LinearLayoutManager(this));
        mBudgetAdapter = new BudgetAdapter(this, BudgetApplication.mBudgetList);
        mBudgetRV.setAdapter(mBudgetAdapter);

        initBudgetList();
    }

//    从 xml 读取
//    private void initBudgetList() {
//        String[] budgetNames = getResources().getStringArray(R.array.budget);
//        for (String budgetName : budgetNames) {
//            Budget budget = new Budget();
//            budget.name = budgetName;
//            mBudgetList.add(budget);
//        }
//        mBudgetAdapter.notifyDataSetChanged();
//    }

    // 从 Json 文件读取
    private void initBudgetList() {
        while (!BudgetApplication.isReady()) {

        }
        mBudgetAdapter.notifyDataSetChanged();
    }
}

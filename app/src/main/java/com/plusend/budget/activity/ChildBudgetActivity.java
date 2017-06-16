package com.plusend.budget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.plusend.budget.R;
import com.plusend.budget.adapter.ChildBudgetAdapter;
import com.plusend.budget.app.BudgetApplication;
import com.plusend.budget.model.Budget;

public class ChildBudgetActivity extends AppCompatActivity {
    public static final String BUDGET = "budget";

    private RecyclerView mBudgetRV;
    private Budget mBudget;
    private ChildBudgetAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_budget);

        mBudgetRV = (RecyclerView) findViewById(R.id.child_budget_rv);
        mBudget = getIntent().getParcelableExtra(BUDGET);
        mAdapter = new ChildBudgetAdapter(this, mBudget);
        mBudgetRV.setLayoutManager(new LinearLayoutManager(this));
        mBudgetRV.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.child_budget, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveBudget();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveBudget() {
        BudgetApplication.updateBudget(mBudget);
        BudgetApplication.saveBudget(this);
    }
}

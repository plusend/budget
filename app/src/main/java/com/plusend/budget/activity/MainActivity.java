package com.plusend.budget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.plusend.budget.R;
import com.plusend.budget.app.BudgetApplication;
import com.plusend.budget.util.BudgetUtil;

public class MainActivity extends AppCompatActivity {
    private TextView expenseTV;
    private TextView surplusTV;
    private Button accountBTN;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_detail:
                    startActivity(new Intent(MainActivity.this, DetailListActivity.class));
                    return true;
                case R.id.navigation_budget:
                    startActivity(new Intent(MainActivity.this, BudgetActivity.class));
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        expenseTV = (TextView) findViewById(R.id.expense_num_tv);
        surplusTV = (TextView) findViewById(R.id.surplus_num_tv);
        accountBTN = (Button) findViewById(R.id.account_btn);

        int expenseNum = BudgetUtil.getInstance().getAllDetailNum(this);
        expenseTV.setText(String.valueOf(expenseNum));
        int allBudgetNum = BudgetApplication.getAllBudgetNum();
        surplusTV.setText(String.valueOf(allBudgetNum - expenseNum));

        accountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
    }

}

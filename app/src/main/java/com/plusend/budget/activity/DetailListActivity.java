package com.plusend.budget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.plusend.budget.R;
import com.plusend.budget.adapter.DetailAdapter;
import com.plusend.budget.detail.JsonDetailUtil;
import com.plusend.budget.model.Detail;

import java.util.ArrayList;
import java.util.List;

public class DetailListActivity extends AppCompatActivity {
    private RecyclerView detailRV;
    private DetailAdapter mAdapter;
    private List<Detail> mDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        detailRV = (RecyclerView) findViewById(R.id.detail_rv);

        mDetailList = new ArrayList<>();
        detailRV.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DetailAdapter(this, mDetailList);
        detailRV.setAdapter(mAdapter);

        initDetailList();
    }

    private void initDetailList() {
        List<Detail> list = JsonDetailUtil.getInstance(this).listDetail();
        if (list != null && list.size() > 0) {
            mDetailList.clear();
            mDetailList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }
}

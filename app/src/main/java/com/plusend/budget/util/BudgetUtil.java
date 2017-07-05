package com.plusend.budget.util;


import android.content.Context;
import android.util.Log;

import com.plusend.budget.detail.JsonDetailUtil;
import com.plusend.budget.model.Detail;

import java.util.List;

public class BudgetUtil {
    private static final String TAG = "BudgetUtil";

    private static volatile BudgetUtil instance;

    private BudgetUtil() {
    }

    public static BudgetUtil getInstance() {
        if (instance == null) {
            synchronized (BudgetUtil.class) {
                if (instance == null) {
                    instance = new BudgetUtil();
                }
            }
        }
        return instance;
    }

    public int getAllDetailNum(Context context) {
        int result = 0;
        List<Detail> detailList = JsonDetailUtil.getInstance(context).listDetail();
        for (Detail detail : detailList) {
            result += detail.num;
        }
        Log.d(TAG, "getAllDetailNum: " + result);
        return result;
    }
}

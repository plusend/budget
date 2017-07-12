package com.plusend.budget.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.plusend.budget.model.Budget;
import com.plusend.budget.util.Config;
import com.plusend.budget.util.FileUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class BudgetApplication extends Application {
    private static final String TAG = BudgetApplication.class.getSimpleName();
    public static List<Budget> mBudgetList;
    private static boolean mReady;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBudgetList = new ArrayList<>();
                mReady = initBudgetList();
            }
        }).start();
    }

    public static boolean isReady() {
        return mReady;
    }

    // 从 Json 文件读取
    private boolean initBudgetList() {
        File file = new File(getFilesDir(), Config.BUDGET_JSON_FILE_NAME);
        String budgetString;
        if (file.exists()) {
            budgetString = FileUtil.readJsonFromFile(file);
            Log.d(TAG, "file: " + budgetString);
        } else {
            budgetString = FileUtil.readJsonFromAssets(this, "budget.json");
            Log.d(TAG, "assets: " + budgetString);
        }
        try {
            JSONObject jsonObject = new JSONObject(budgetString);
            JSONArray jsonArray = jsonObject.optJSONArray("budget");
            if (jsonArray != null) {
                int size = jsonArray.length();
                for (int i = 0; i < size; i++) {
                    JSONObject iJson = (JSONObject) jsonArray.opt(i);
                    Budget budget = new Budget();
                    budget.name = iJson.optString("name");
                    budget.icon = iJson.optString("icon");
                    budget.num = iJson.optInt("num");
                    mBudgetList.add(budget);
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新某个预算
    public static void updateBudget(Budget budget) {
        boolean exist = false;
        for (Budget b : mBudgetList) {
            if (TextUtils.equals(b.name, budget.name)) {
                exist = true;
                b.icon = budget.icon;
                b.num = budget.num;
            }
        }
        if (!exist) {
            mBudgetList.add(budget);
        }
    }

    // 保存整个预算
    public static void saveBudget(Context context) {
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        try {
            for (Budget b : mBudgetList) {
                JSONObject bo = new JSONObject();
                bo.put("name", b.name);
                bo.put("icon", b.icon);
                bo.put("num", b.num);
                ja.put(bo);
            }
            jo.put("budget", ja);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, jo.toString());
        FileUtil.saveJsonToFile(jo.toString(), new File(context.getFilesDir(), Config.BUDGET_JSON_FILE_NAME));
    }

    // 获取整个预算的值
    public static int getAllBudgetNum() {
        int result = 0;
        for (Budget budget : mBudgetList) {
            result += budget.num;
        }
        Log.d(TAG, "getAllBudgetNum: " + result);
        return result;
    }
}

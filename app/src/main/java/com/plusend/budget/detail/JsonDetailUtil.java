package com.plusend.budget.detail;


import android.content.Context;
import android.text.TextUtils;

import com.plusend.budget.model.Detail;
import com.plusend.budget.util.Config;
import com.plusend.budget.util.FileUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonDetailUtil implements DetailUtil {

    private static volatile JsonDetailUtil instance;
    private File jsonFile;

    private JsonDetailUtil(Context context) {
        jsonFile = new File(context.getFilesDir(), Config.DETAIL_JSON_FILE_NAME);
    }

    public static JsonDetailUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (JsonDetailUtil.class) {
                instance = new JsonDetailUtil(context);
            }
        }
        return instance;
    }

    @Override
    public void saveDetail(Detail detail) {
        synchronized (this) {
            String detailString = FileUtil.readJsonFromFile(jsonFile);
            if (detailString != null) {
                try {
                    JSONObject detailListJson = new JSONObject(detailString);
                    JSONArray detailArrayJson = detailListJson.getJSONArray("detail");
                    JSONObject detailJson = new JSONObject();
                    detailJson.put("id", detail.getId());
                    detailJson.put("num", detail.num);
                    detailJson.put("name", detail.name);
                    detailJson.put("date", detail.date);
                    detailJson.put("remark", detail.remark);
                    detailArrayJson.put(detailJson);
                    detailListJson.put("detail", detailArrayJson);
                    FileUtil.saveJsonToFile(detailListJson.toString(), jsonFile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    JSONObject detailListJson = new JSONObject();
                    JSONArray detailArrayJson = new JSONArray();
                    JSONObject detailJson = new JSONObject();
                    detailJson.put("id", detail.getId());
                    detailJson.put("num", detail.num);
                    detailJson.put("name", detail.name);
                    detailJson.put("date", detail.date);
                    detailJson.put("remark", detail.remark);
                    detailArrayJson.put(detailJson);
                    detailListJson.put("detail", detailArrayJson);
                    FileUtil.saveJsonToFile(detailListJson.toString(), jsonFile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Detail> listDetail() {
        synchronized (this) {
            List<Detail> result = new ArrayList<>();

            String detailString = FileUtil.readJsonFromFile(jsonFile);
            if (!TextUtils.isEmpty(detailString)) {
                try {
                    JSONObject detailListJson = new JSONObject(detailString);
                    JSONArray detailArrayJson = detailListJson.getJSONArray("detail");
                    if (detailArrayJson != null && detailArrayJson.length() > 0) {
                        int length = detailArrayJson.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject detailJson = detailArrayJson.getJSONObject(i);
                            Detail detail = new Detail(detailJson.optString("id"), detailJson.optInt("num"), detailJson.optString("name"), detailJson.optLong("date"), detailJson.optString("remark"));
                            result.add(detail);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    @Override
    public void removeDetail(Detail detail) {
        synchronized (this) {
            List<Detail> detailList = listDetail();
            Iterator<Detail> iterator = detailList.iterator();
            while (iterator.hasNext()){
                Detail temp = iterator.next();
                if(TextUtils.equals(temp.getId(), detail.getId())){
                    iterator.remove();
                }
            }
            saveDetailList(detailList);
        }
    }

    @Override
    public void updateDetail(Detail detail) {
        synchronized (this) {
            List<Detail> detailList = listDetail();
            int size = detailList.size();
            for (int i = 0; i < size; i++) {
                if (TextUtils.equals(detailList.get(i).getId(), detail.getId())) {
                    detailList.set(i, detail);
                }
            }
//            for (Detail temp : detailList) {
//                if (TextUtils.equals(temp.getId(), detail.getId())) {
//                    temp = detail;
//                }
//            }
            saveDetailList(detailList);
        }
    }

    private void saveDetailList(List<Detail> detailList) {
        try {
            JSONObject detailListJson = new JSONObject();
            JSONArray detailArrayJson = new JSONArray();
            for (Detail detail : detailList) {
                JSONObject detailJson = new JSONObject();
                detailJson.put("id", detail.getId());
                detailJson.put("num", detail.num);
                detailJson.put("name", detail.name);
                detailJson.put("date", detail.date);
                detailJson.put("remark", detail.remark);
                detailArrayJson.put(detailJson);
            }
            detailListJson.put("detail", detailArrayJson);
            FileUtil.saveJsonToFile(detailListJson.toString(), jsonFile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

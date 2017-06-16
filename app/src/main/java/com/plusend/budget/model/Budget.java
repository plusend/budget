package com.plusend.budget.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

import java.util.Map;

public class Budget implements Parcelable {
    public String name;
    public ArrayMap<String, Integer> mBudgetMap;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.mBudgetMap.size());
        for (Map.Entry<String, Integer> entry : this.mBudgetMap.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
    }

    public Budget() {
    }

    protected Budget(Parcel in) {
        this.name = in.readString();
        int mBudgetMapSize = in.readInt();
        this.mBudgetMap = new ArrayMap<>(mBudgetMapSize);
        for (int i = 0; i < mBudgetMapSize; i++) {
            String key = in.readString();
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.mBudgetMap.put(key, value);
        }
    }

    public static final Parcelable.Creator<Budget> CREATOR = new Parcelable.Creator<Budget>() {
        @Override
        public Budget createFromParcel(Parcel source) {
            return new Budget(source);
        }

        @Override
        public Budget[] newArray(int size) {
            return new Budget[size];
        }
    };
}

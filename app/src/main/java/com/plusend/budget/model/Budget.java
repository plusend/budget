package com.plusend.budget.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Budget implements Parcelable {
    public String name;
    public String icon;
    public int num;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.icon);
        dest.writeInt(this.num);
    }

    public Budget() {
    }

    public Budget(Parcel in) {
        this.name = in.readString();
        this.icon = in.readString();
        this.num = in.readInt();
    }

    public static final Creator<Budget> CREATOR = new Creator<Budget>() {
        @Override
        public Budget createFromParcel(Parcel source) {
            return new Budget(source);
        }

        @Override
        public Budget[] newArray(int size) {
            return new Budget[size];
        }
    };

    @Override
    public String toString() {
        return "Budget{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", num=" + num +
                '}';
    }
}

package com.plusend.budget.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Detail implements Parcelable {
    private String id;
    public int num;
    public String name;
    public long date;
    public String remark;

    /**
     * 用于取出 Detail
     *
     * @param id
     * @param num
     * @param name
     * @param date
     * @param remark
     */
    public Detail(String id, int num, String name, long date, String remark) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.date = date;
        this.remark = remark;
    }

    /**
     * 用于新建 Detail
     *
     * @param num
     * @param name
     * @param date
     * @param remark
     */
    public Detail(int num, String name, long date, String remark) {
        this.id = UUID.randomUUID().toString();
        this.num = num;
        this.name = name;
        this.date = date;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.num);
        dest.writeString(this.name);
        dest.writeLong(this.date);
        dest.writeString(this.remark);
    }

    protected Detail(Parcel in) {
        this.id = in.readString();
        this.num = in.readInt();
        this.name = in.readString();
        this.date = in.readLong();
        this.remark = in.readString();
    }

    public static final Parcelable.Creator<Detail> CREATOR = new Parcelable.Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel source) {
            return new Detail(source);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };
}

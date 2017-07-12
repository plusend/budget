package com.plusend.budget.util;


import android.content.Context;

public class ResourceUtil {
    /**
     * 通过字符串 name 获取字符串的 value
     */
    public static String getString(Context context, String name) {
        return context.getResources().getString(context.getResources().getIdentifier(name, "string", context.getPackageName()));
    }

    /**
     * 通过图片 name 获取图片的资源 id
     */
    public static int getIcon(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}

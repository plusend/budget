package com.plusend.budget.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static List<String> readFileByLines(String fileName) {
        List<String> result = new ArrayList<>();

        File file = new File(Environment.getExternalStorageDirectory().getPath(), fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                result.add(tempString);
                Log.d(TAG, "readFileByLines: line: " + line + " String: " + tempString);
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 从 Assets 文件读取 JSon
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readJsonFromAssets(Context context, String fileName) {
        String result = null;
        try {
            InputStream mAssets = context.getAssets().open(fileName);
            int length = mAssets.available();
            //创建byte数组
            byte[] buffer = new byte[length];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从文件读取 JSon
     *
     * @return
     */
    public static String readJsonFromFile(File file) {
        String result = null;
        try {
            InputStream mAssets = new FileInputStream(file);
            int length = mAssets.available();
            //创建byte数组
            byte[] buffer = new byte[length];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 存储 Json 到内部存储
     *
     * @param jsonString
     */
    public static void saveJsonToFile(String jsonString, File file) {
        byte[] buffer = jsonString.getBytes(StandardCharsets.UTF_8);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

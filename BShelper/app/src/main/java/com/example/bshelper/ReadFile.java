package com.example.bshelper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    //blog.csdn.net/qq_36407875/article/details/78506874
    /**
     * @param fileDirName:文件夹目录
     * @param fileName:文件名字
     * @param ways:读取文件的方式
     * @Function: 从存储路径中读出数据
     * @Return:
     */
    private static String LOG_Info = "ReadFile";
    public void ReadDataFromStorage(String path, int ways) throws IOException {
        File file = new File(path);
        int Ways = ways;
        if (Ways == 0) {
            Log.i(LOG_Info, "FileInputStream");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                String result = new String(bytes);
                Log.i(LOG_Info, "读取的内容是：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Ways == 1) {   //最好使用 Buffer 缓冲流，安全机制 大量的文件
            Log.i(LOG_Info, "Bufferreader");
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String readline = "";
                StringBuffer stringBuffer = new StringBuffer();
                while ((readline = bufferedReader.readLine()) != null) {
                    stringBuffer.append(readline);
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                    Log.i(LOG_Info, "读取的内容是：" + stringBuffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Ways == 2) {
            Log.i(LOG_Info, "Input+Buffer");
            try {
                String fileContent = null;
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent += line;
                }
                reader.close();
                read.close();
                Log.i(LOG_Info, fileContent);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("ReadFile_ERROE", e.getMessage());
            }
        } else
            Ways = 2;
    }

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

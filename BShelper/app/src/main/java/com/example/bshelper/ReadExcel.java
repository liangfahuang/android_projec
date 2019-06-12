package com.example.bshelper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {

    private String inputFile;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    private BSInfo mBSInfo = null;
    private List<BSInfo> mList = new ArrayList<BSInfo>();
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public List read() throws Exception {
        File inputWorkbook = new File(inputFile);
        Workbook workbook;
        String[][] points = null;
        try {
            workbook = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = workbook.getSheet(0);
            points = new String[sheet.getRows()-1][sheet.getColumns()];
            for(int j=1; j<sheet.getRows(); j++){
                double longitude = Double.parseDouble(sheet.getCell(0,j).getContents());
                double latitude = Double.parseDouble(sheet.getCell(1,j).getContents());
                String BSName = sheet.getCell(2,j).getContents();
                String BSAddres = sheet.getCell(3,j).getContents();
                mBSInfo = new BSInfo(longitude,latitude,BSName,BSAddres);
                mList.add(mBSInfo);
//                for (int i=0; i<sheet.getColumns(); i++) {
//                    Cell cell = sheet.getCell(i,j);
//                    CellType cellType = cell.getType();
//                    Log.i("readExcel", "读取的内容是：" + cell.getContents() + "，类型是："+ cellType
//                    +"i="+i+",j="+j);
//                    Log.i("readexcel","-----------------------");
//                    if(cellType == CellType.LABEL) {
//
//                    }
////                   points[j-1][i] = Double.parseDouble(cell.getContents());
//                }
                Log.i("readExcel", "read: "+ mBSInfo.getLongitude() + ","+mBSInfo.getLatitude()+" ,"+mBSInfo.getBSName());
            }
//            for (int i=0; i<points.length; i++) {
//                for(int j=0; j<points[i].length;j++){
//                    Log.i("raadExcel", "points: " + points[i][j]);
//                }

//            }

        } catch (Exception e) {
            Log.i("readExcel", "read: "+e.toString());
            e.printStackTrace();
        }
        return mList;
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

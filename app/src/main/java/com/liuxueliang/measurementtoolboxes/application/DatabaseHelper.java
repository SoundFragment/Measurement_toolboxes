package com.liuxueliang.measurementtoolboxes.application;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.liuxueliang.measurementtoolboxes.application.DatabaseHelper;


public class DatabaseHelper extends SQLiteOpenHelper{




    private static final String TABLE_NAME = "data";

    //列名
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_START_ELEVATION = "start_elevation";
    private static final String COLUMN_END_ELEVATION = "end_elevation";
    private static final String COLUMN_RECORDER = "recorder";



    private static final String COLUMN_BACK_NAME = "backName";
    private static final String COLUMN_FRONT_NAME = "frontName";
    private static final String COLUMN_BACK_TOP = "backTop";
    private static final String COLUMN_BACK_BOTTOM = "backBottom";
    private static final String COLUMN_BACK_BLACK_MID = "backBlackMid";
    private static final String COLUMN_FRONT_BLACK_MID = "frontBlackMid";
    private static final String COLUMN_FRONT_TOP = "frontTop";
    private static final String COLUMN_FRONT_BOTTOM = "frontBottom";
    private static final String COLUMN_FRONT_RED_MID = "frontRedMid";
    private static final String COLUMN_BACK_RED_MID = "backRedMid";
    private static final String COLUMN_BACK_SIGHT = "backSight";
    private static final String COLUMN_FRONT_SIGHT = "frontSight";
    private static final String COLUMN_SIGHT_DIFF = "sightDiff";
    private static final String COLUMN_CUMULATIVE_SIGHT_DIFF = "cumulativeSightDiff";
    private static final String COLUMN_BLACK_HEIGHT_DIFF = "blackHeightDiff";
    private static final String COLUMN_RED_HEIGHT_DIFF = "redHeightDiff";
    private static final String COLUMN_BACK_BLACK_PLUS_K_MINUS_RED = "backBlackPlusKMinusRed";
    private static final String COLUMN_FRONT_BLACK_PLUS_K_MINUS_RED = "frontBlackPlusKMinusRed";
    private static final String COLUMN_BLACK_MINUS_RED_HEIGHT_DIFF = "blackMinusRedHeightDiff";
    private static final String COLUMN_AVERAGE_TV = "averageTV";

    public DatabaseHelper(@Nullable Context context ) {
        super(context, "measure_data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_START_ELEVATION + " REAL, "
                + COLUMN_END_ELEVATION + " REAL, "
                + COLUMN_RECORDER + " TEXT,"
                + COLUMN_BACK_NAME + " TEXT,"
                + COLUMN_FRONT_NAME + " TEXT,"
                + COLUMN_BACK_TOP + " REAL,"
                + COLUMN_BACK_BOTTOM + " REAL,"
                + COLUMN_BACK_BLACK_MID + " REAL,"
                + COLUMN_FRONT_BLACK_MID + " REAL,"
                + COLUMN_FRONT_TOP + " REAL,"
                + COLUMN_FRONT_BOTTOM + " REAL,"
                + COLUMN_FRONT_RED_MID + " REAL,"
                + COLUMN_BACK_RED_MID + " REAL,"
                + COLUMN_BACK_SIGHT + " REAL,"
                + COLUMN_FRONT_SIGHT + " REAL,"
                + COLUMN_SIGHT_DIFF + " REAL,"
                + COLUMN_CUMULATIVE_SIGHT_DIFF + " REAL,"
                + COLUMN_BLACK_HEIGHT_DIFF + " REAL,"
                + COLUMN_RED_HEIGHT_DIFF + " REAL,"
                + COLUMN_BACK_BLACK_PLUS_K_MINUS_RED + " REAL,"
                + COLUMN_FRONT_BLACK_PLUS_K_MINUS_RED + " REAL,"
                + COLUMN_BLACK_MINUS_RED_HEIGHT_DIFF + " REAL,"
                + COLUMN_AVERAGE_TV + " REAL" + ")";
        db.execSQL(CREATE_TABLE);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //增
    public String addOne (DataModel dataModel){

        ContentValues cv = new ContentValues();

        // 设置整型ID
        //cv.put(COLUMN_ID, dataModel.getId());
        // 设置起始高程
        if (dataModel.getStartElevation() != null) {
            cv.put(COLUMN_START_ELEVATION, dataModel.getStartElevation());
        } else {
            cv.putNull(COLUMN_START_ELEVATION);
        }
        // 设置结束高程
        if (dataModel.getEndElevation() != null) {
            cv.put(COLUMN_END_ELEVATION, dataModel.getEndElevation());
        } else {
            cv.putNull(COLUMN_END_ELEVATION);
        }
        // 设置录入者
        if (dataModel.getRecorder() != null) {
            cv.put(COLUMN_RECORDER, dataModel.getRecorder());
        } else {
            cv.putNull(COLUMN_RECORDER);
        }

        if (dataModel.getBackName() != null) {
            cv.put(COLUMN_BACK_NAME, dataModel.getBackName());
        } else {
            cv.putNull(COLUMN_BACK_NAME);
        }

        if (dataModel.getFrontName() != null) {
            cv.put(COLUMN_FRONT_NAME, dataModel.getFrontName());
        } else {
            cv.putNull(COLUMN_FRONT_NAME);
        }



        // 设置其他 double 类型列
        cv.put(COLUMN_BACK_TOP, dataModel.getBackTop());
        cv.put(COLUMN_BACK_BOTTOM, dataModel.getBackBottom());
        cv.put(COLUMN_BACK_BLACK_MID, dataModel.getBackBlackMid());
        cv.put(COLUMN_FRONT_BLACK_MID, dataModel.getFrontBlackMid());
        cv.put(COLUMN_FRONT_TOP, dataModel.getFrontTop());
        cv.put(COLUMN_FRONT_BOTTOM, dataModel.getFrontBottom());
        cv.put(COLUMN_FRONT_RED_MID, dataModel.getFrontRedMid());
        cv.put(COLUMN_BACK_RED_MID, dataModel.getBackRedMid());
        cv.put(COLUMN_BACK_SIGHT, dataModel.getBackSight());
        cv.put(COLUMN_FRONT_SIGHT, dataModel.getFrontSight());
        cv.put(COLUMN_SIGHT_DIFF, dataModel.getSightDiff());
        cv.put(COLUMN_CUMULATIVE_SIGHT_DIFF, dataModel.getCumulativeSightDiff());
        cv.put(COLUMN_BLACK_HEIGHT_DIFF, dataModel.getBlackHeightDiff());
        cv.put(COLUMN_RED_HEIGHT_DIFF, dataModel.getRedHeightDiff());
        cv.put(COLUMN_BACK_BLACK_PLUS_K_MINUS_RED, dataModel.getBackBlackPlusKMinusRed());
        cv.put(COLUMN_FRONT_BLACK_PLUS_K_MINUS_RED, dataModel.getFrontBlackPlusKMinusRed());
        cv.put(COLUMN_BLACK_MINUS_RED_HEIGHT_DIFF, dataModel.getBlackMinusRedHeightDiff());
        cv.put(COLUMN_AVERAGE_TV, dataModel.getAverageTV());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long insert = sqLiteDatabase.insert(TABLE_NAME, null, cv);
        sqLiteDatabase.close();

        return (insert == -1) ? "fail" : "success";
    }




    //删
    public String  deleteOne(DataModel dataModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            int delete = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(dataModel.getId())});
            if (delete == 0) {
                Log.d("DatabaseHelper", "No rows deleted");
                return "fail";
            } else {
                Log.d("DatabaseHelper", "Row deleted successfully");
                return "success";
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error deleting row: " + e.getMessage());
            return "fail";
        } finally {
            sqLiteDatabase.close();
        }
    }




    //改
    public String updataOne (DataModel dataModel){

        ContentValues cv = new ContentValues();

        // 设置起始高程
        if (dataModel.getStartElevation() != null) {
            cv.put(COLUMN_START_ELEVATION, dataModel.getStartElevation());
        } else {
            cv.putNull(COLUMN_START_ELEVATION);
        }
        // 设置结束高程
        if (dataModel.getEndElevation() != null) {
            cv.put(COLUMN_END_ELEVATION, dataModel.getEndElevation());
        } else {
            cv.putNull(COLUMN_END_ELEVATION);
        }
        // 设置录入者
        if (dataModel.getRecorder() != null) {
            cv.put(COLUMN_RECORDER, dataModel.getRecorder());
        } else {
            cv.putNull(COLUMN_RECORDER);
        }


        // 设置其他 double 类型列
        cv.put(COLUMN_BACK_TOP, dataModel.getBackTop());
        cv.put(COLUMN_BACK_BOTTOM, dataModel.getBackBottom());
        cv.put(COLUMN_BACK_BLACK_MID, dataModel.getBackBlackMid());
        cv.put(COLUMN_FRONT_BLACK_MID, dataModel.getFrontBlackMid());
        cv.put(COLUMN_FRONT_TOP, dataModel.getFrontTop());
        cv.put(COLUMN_FRONT_BOTTOM, dataModel.getFrontBottom());
        cv.put(COLUMN_FRONT_RED_MID, dataModel.getFrontRedMid());
        cv.put(COLUMN_BACK_RED_MID, dataModel.getBackRedMid());
        cv.put(COLUMN_BACK_SIGHT, dataModel.getBackSight());
        cv.put(COLUMN_FRONT_SIGHT, dataModel.getFrontSight());
        cv.put(COLUMN_SIGHT_DIFF, dataModel.getSightDiff());
        cv.put(COLUMN_CUMULATIVE_SIGHT_DIFF, dataModel.getCumulativeSightDiff());
        cv.put(COLUMN_BLACK_HEIGHT_DIFF, dataModel.getBlackHeightDiff());
        cv.put(COLUMN_RED_HEIGHT_DIFF, dataModel.getRedHeightDiff());
        cv.put(COLUMN_BACK_BLACK_PLUS_K_MINUS_RED, dataModel.getBackBlackPlusKMinusRed());
        cv.put(COLUMN_FRONT_BLACK_PLUS_K_MINUS_RED, dataModel.getFrontBlackPlusKMinusRed());
        cv.put(COLUMN_BLACK_MINUS_RED_HEIGHT_DIFF, dataModel.getBlackMinusRedHeightDiff());
        cv.put(COLUMN_AVERAGE_TV, dataModel.getAverageTV());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int update = sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(dataModel.getId())});
        sqLiteDatabase.close();

        return (update == 0) ? "fail" : "success";
    }




    //查
    public List<DataModel> getAll(){

        List<DataModel> list = new ArrayList<>();
        String sql = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        while (cursor.moveToNext()) {

            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int id = cursor.getInt(idIndex);
            int startElevationIndex = cursor.getColumnIndex(COLUMN_START_ELEVATION);
            int endElevationIndex = cursor.getColumnIndex(COLUMN_END_ELEVATION);
            int recorderIndex = cursor.getColumnIndex(COLUMN_RECORDER);
            int backNameIndex = cursor.getColumnIndex(COLUMN_BACK_NAME);
            int frontNameIndex = cursor.getColumnIndex(COLUMN_FRONT_NAME);



            String recorder = cursor.getString(recorderIndex);
            Double startElevation = null;
            String startElevationString = cursor.getString(startElevationIndex);
            if (startElevationString != null) {
                startElevationString = startElevationString.trim();
                if (!startElevationString.isEmpty()) {
                    startElevation = Double.valueOf(startElevationString);
                }
            }
            Double endElevation = null;
            String endElevationString = cursor.getString(endElevationIndex);
            if (endElevationString != null) {
                endElevationString = endElevationString.trim();
                if (!endElevationString.isEmpty()) {
                    endElevation = Double.valueOf(endElevationString);
                }
            }
            String backName = cursor.getString(backNameIndex);
            String frontName = cursor.getString(frontNameIndex);

            // Parse other double columns
            double backTop = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_BACK_TOP));
            double backBottom = cursor.getDouble(cursor.getColumnIndex(COLUMN_BACK_BOTTOM));
            double backBlackMid = cursor.getDouble(cursor.getColumnIndex(COLUMN_BACK_BLACK_MID));
            double frontBlackMid = cursor.getDouble(cursor.getColumnIndex(COLUMN_FRONT_BLACK_MID));
            double frontTop = cursor.getDouble(cursor.getColumnIndex(COLUMN_FRONT_TOP));
            double frontBottom = cursor.getDouble(cursor.getColumnIndex(COLUMN_FRONT_BOTTOM));
            double frontRedMid = cursor.getDouble(cursor.getColumnIndex(COLUMN_FRONT_RED_MID));
            double backRedMid = cursor.getDouble(cursor.getColumnIndex(COLUMN_BACK_RED_MID));
            double backSight = cursor.getDouble(cursor.getColumnIndex(COLUMN_BACK_SIGHT));
            double frontSight = cursor.getDouble(cursor.getColumnIndex(COLUMN_FRONT_SIGHT));
            double sightDiff = cursor.getDouble(cursor.getColumnIndex(COLUMN_SIGHT_DIFF));
            double cumulativeSightDiff = cursor.getDouble(cursor.getColumnIndex(COLUMN_CUMULATIVE_SIGHT_DIFF));
            double blackHeightDiff = cursor.getDouble(cursor.getColumnIndex(COLUMN_BLACK_HEIGHT_DIFF));
            double redHeightDiff = cursor.getDouble(cursor.getColumnIndex(COLUMN_RED_HEIGHT_DIFF));
            double backBlackPlusKMinusRed = cursor.getDouble(cursor.getColumnIndex(COLUMN_BACK_BLACK_PLUS_K_MINUS_RED));
            double frontBlackPlusKMinusRed = cursor.getDouble(cursor.getColumnIndex(COLUMN_FRONT_BLACK_PLUS_K_MINUS_RED));
            double blackMinusRedHeightDiff = cursor.getDouble(cursor.getColumnIndex(COLUMN_BLACK_MINUS_RED_HEIGHT_DIFF));
            double averageTV = cursor.getDouble(cursor.getColumnIndex(COLUMN_AVERAGE_TV));






            DataModel dataModel = new DataModel(
                    startElevation, endElevation, recorder
            );


            dataModel.setId(id);
            dataModel.setBackName(backName);
            dataModel.setFrontName(frontName);
            dataModel.setBackTop(backTop);
            dataModel.setBackBottom(backBottom);
            dataModel.setBackBlackMid(backBlackMid);
            dataModel.setFrontBlackMid(frontBlackMid);
            dataModel.setFrontTop(frontTop);
            dataModel.setFrontBottom(frontBottom);
            dataModel.setFrontRedMid(frontRedMid);
            dataModel.setBackRedMid(backRedMid);
            dataModel.setBackSight(backSight);
            dataModel.setFrontSight(frontSight);
            dataModel.setSightDiff(sightDiff);
            dataModel.setCumulativeSightDiff(cumulativeSightDiff);
            dataModel.setBlackHeightDiff(blackHeightDiff);
            dataModel.setRedHeightDiff(redHeightDiff);
            dataModel.setBackBlackPlusKMinusRed(backBlackPlusKMinusRed);
            dataModel.setFrontBlackPlusKMinusRed(frontBlackPlusKMinusRed);
            dataModel.setBlackMinusRedHeightDiff(blackMinusRedHeightDiff);
            dataModel.setAverageTV(averageTV);

            list.add(dataModel);
        }

        cursor.close();
        sqLiteDatabase.close();

        return list;
    }

    public double getFirstStartElevation(SQLiteDatabase db) {
        double firstStartElevation = 0.0; // 默认值或错误处理

        String query = "SELECT " + COLUMN_START_ELEVATION + " FROM " + TABLE_NAME + " LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            firstStartElevation = cursor.getDouble(cursor.getColumnIndex(COLUMN_START_ELEVATION));
            cursor.close();
        }

        return firstStartElevation;
    }

    public double getFirstEndElevation(SQLiteDatabase db) {
        double firstEndElevation = 0.0; // 默认值或错误处理

        String query = "SELECT " + COLUMN_END_ELEVATION + " FROM " + TABLE_NAME + " LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            firstEndElevation = cursor.getDouble(cursor.getColumnIndex(COLUMN_END_ELEVATION));
            cursor.close();
        }

        return firstEndElevation;
    }









    // 获取表的行数
    public int getRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM data", null);
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();
        return rowCount;
    }



    //获取视距
    public List<Double> getSightSumValues(SQLiteDatabase db) {
        List<Double> sightsumList = new ArrayList<>();

        // 查询数据库以获取 backSight 和 frontSight 列的值
        Cursor cursor = db.query("data", new String[]{"backSight", "frontSight"}, null, null, null, null, null);

        // 遍历查询结果
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // 获取每一行的 backSight 和 frontSight 列的值
                double backSightValue = cursor.getDouble(cursor.getColumnIndex("backSight"));
                double frontSightValue = cursor.getDouble(cursor.getColumnIndex("frontSight"));

                // 将 backSight 和 frontSight 列的值相加，并将结果保存在列表中
                double sum = backSightValue + frontSightValue;
                sightsumList.add(sum);
            } while (cursor.moveToNext());

            // 关闭游标
            cursor.close();
        }

        return sightsumList;
    }


    //获取高差中数
    public List<Double> getAverageTVValues(SQLiteDatabase db) {
        List<Double> averageTVList = new ArrayList<>();

        // 查询数据库以获取 averageTV 列的值
        Cursor cursor = db.query("data", new String[]{"averageTV"}, null, null, null, null, null);

        // 遍历查询结果
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // 获取每一行的 averageTV 列的值
                double averageTVValue = cursor.getDouble(cursor.getColumnIndex("averageTV"));

                // 将 averageTV 列的值保存在列表中
                averageTVList.add(averageTVValue);
            } while (cursor.moveToNext());

            // 关闭游标
            cursor.close();
        }

        return averageTVList;
    }


    //获取后视点名
    public List<String> getBackNamesFromDatabase(SQLiteDatabase db) {
        List<String> backSightNameList = new ArrayList<>();

        // 查询数据库获取 backName 列的值
        Cursor cursor = db.query("data", new String[]{"backName"}, null, null, null, null, null);

        // 遍历查询结果并存入 backSightNameList 列表
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String backName = cursor.getString(cursor.getColumnIndex("backName"));
                backSightNameList.add(backName);
            } while (cursor.moveToNext());

            // 关闭游标
            cursor.close();
        }

        return backSightNameList;
    }


    //获取前视点名
    public List<String> getFrontNamesFromDatabase(SQLiteDatabase db) {
        List<String> frontSightNameList = new ArrayList<>();

        // 查询数据库获取 frontName 列的值
        Cursor cursor = db.query("data", new String[]{"frontName"}, null, null, null, null, null);

        // 遍历查询结果并存入 frontSightNameList 列表
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String frontName = cursor.getString(cursor.getColumnIndex("frontName"));
                frontSightNameList.add(frontName);
            } while (cursor.moveToNext());

            // 关闭游标
            cursor.close();
        }

        return frontSightNameList;
    }















    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }









}

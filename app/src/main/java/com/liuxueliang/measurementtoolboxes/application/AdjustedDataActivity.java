package com.liuxueliang.measurementtoolboxes.application;

import static com.liuxueliang.measurementtoolboxes.universalCode.Caculate.Round;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.liuxueliang.measurementtoolboxes.R;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class AdjustedDataActivity extends AppCompatActivity implements View.OnClickListener {
//平差


    Double startElevation,endElevation,forwardPointName,backwardPointName,forwardDistance,backwardDistance,heightDifferenceMedian
            ,sightingDistance,initialHeightDifference,heightDifference,correction
            ,correctedHeightDifference,elevation,closingError;

    int stationCount;
    double totalSightingDistance=0;
    double totalheightDifference=0;

    Double AllowableError,CalculationError;
    TextView textView,textView11;


    List<Double> distanceList = new ArrayList<>(); // 距离列表
    List<Double> heightDifferenceList = new ArrayList<>(); // 高差列表
    List<Double> correctionList = new ArrayList<>(); // 改正数列表
    List<Double> correctedHeightDifferenceList = new ArrayList<>(); // 改正高差列表
    List<String> frontSightNameList = new ArrayList<>(); // 前视点名列表
    List<String> backSightNameList = new ArrayList<>(); // 后视点名列表


    List<Double> resultElevationList = new ArrayList<>();    //成果高程列表

    Double correctionValues;  //按测站定权的改正数



    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_levelmeasurement_adjusteddata);

        textView=findViewById(R.id.textView);
        textView11=findViewById(R.id.textView11);





        //获取数据库行数用做测站数
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        stationCount = databaseHelper.getRowCount()-1;

        //获取起始、终点高程，算出理论闭合差
        startElevation = databaseHelper.getFirstStartElevation(db);
        endElevation = databaseHelper.getFirstEndElevation(db);
        closingError = startElevation-endElevation;
        closingError = Round(closingError,3);

        //获取距离列表,进行舍入，重新存入distanceList,用作平差时的距离
        List<Double> SightSumValuesList = databaseHelper.getSightSumValues(db);
        for (double value : SightSumValuesList) {
            double roundedValue = Round(value, 2); // 调用 Round 方法
            distanceList.add(roundedValue);
        }
        //获取总距离，用作按距离平差时的总距离
        for (double value : distanceList) {
            totalSightingDistance += value;
            totalSightingDistance=Round(totalSightingDistance,3);

        }




        //获取高差列表
        List<Double> AverageTVValuesList = databaseHelper.getAverageTVValues(db);
        for (double value : AverageTVValuesList) {
            double processedValue = value * 0.001; // 将每个值乘以 0.001
            double roundedValue = Round(processedValue, 3); // 调用 Round 方法处理
            heightDifferenceList.add(roundedValue);
            //System.out.println(roundedValue);
        }

        //获取舍入后总的高差
        for (double value:heightDifferenceList){
            totalheightDifference += value;
            totalheightDifference = Round(totalheightDifference,3);
        }


        //获取前视点名
        backSightNameList = databaseHelper.getBackNamesFromDatabase(db);
        frontSightNameList = databaseHelper.getFrontNamesFromDatabase(db);



        //计算需要分配的误差，用于计算改正数
        CalculationError=closingError+totalheightDifference;
        CalculationError = Round(CalculationError,3);





        // 获取布尔值，判断是山地还是平原
        boolean isMountainValue = PreInputDataActivity.isMountain;
        double AllowableError;
        if (isMountainValue) {
            AllowableError = 15 * Math.sqrt(totalSightingDistance * 0.001);
        } else {
            AllowableError = 12 * Math.sqrt(totalheightDifference * 0.001);
        }

        if (CalculationError >= 0) {
            AllowableError = Math.abs(AllowableError);
        } else {
            AllowableError = -Math.abs(AllowableError);
        }

        if (Math.abs(AllowableError) >= Math.abs(CalculationError)) {
            showMessage("允许的误差: " + AllowableError + " \n计算的误差: " + CalculationError);

            // 计算改正数并存入correctionList列表
            for (double distance : distanceList) {
                double correctionValue = (distance / totalSightingDistance) * CalculationError;
                correctionValue=Round(correctionValue,4);
                correctionList.add(correctionValue);
            }
            // 计算改正高程，并存入correctedHeightDifferenceList列表
            for (int i = 0; i < heightDifferenceList.size(); i++) {
                double heightDifference = heightDifferenceList.get(i);
                double correction = correctionList.get(i);
                double correctedHeightDifference = heightDifference - correction;
                correctedHeightDifference=Round(correctedHeightDifference,4);
                correctedHeightDifferenceList.add(correctedHeightDifference);
            }
            //获取成果高程
            double currentElevation = startElevation; // 初始值为 startElevation
            // 遍历 correctedHeightDifferenceList 中的每个值，并进行累加
            for (double correctedHeightDifference : correctedHeightDifferenceList) {
                // 累加当前高度差到当前高程
                currentElevation += correctedHeightDifference;
                currentElevation = Round(currentElevation,4);
                // 将累加后的高程值存入 resultElevationList列表 中
                resultElevationList.add(currentElevation);
            }
        } else {
            showMessage("不满足限差要求\n允许的误差: " + AllowableError + " < 计算的误差: " + CalculationError);
        }



        String str;
        str = "\t已知点数据\n";
        str += "-------------------------------------------------------------------\n";
        str += "起点高程: " +startElevation  + " m      " + "  终点高程: " + endElevation + " m\n";
        str += "测站数：" + stationCount + "\n";
        str += "总距离：" + totalSightingDistance + " m\n";
        str += "理论闭合差："+closingError+"m\n";
        str += "实测闭合差："+totalheightDifference+"m\n";
        str += "需分配的闭合差："+CalculationError+"m\n";



        textView.setText(str);

        StringBuilder displayText = new StringBuilder();
        // 添加表头
        displayText.append(String.format("%-10s %-10s %-20s %-10s %-10s%n", "后视点名", "前视点名", "视距(m)", "高差", "成果高程(m)"));

        // 确保所有列表的大小相同
        int totalSize = Math.min(Math.min(Math.min(Math.min(frontSightNameList.size(), backSightNameList.size()), distanceList.size()), heightDifferenceList.size()), correctedHeightDifferenceList.size());

        for (int i = 1; i < totalSize; i++) {
            // 添加前视点名
            String frontSightName = (i < frontSightNameList.size()) ? frontSightNameList.get(i) : "";
            // 添加后视点名
            String backSightName = (i < backSightNameList.size()) ? backSightNameList.get(i) : "";
            // 添加视距(m)
            String distance = (i < distanceList.size()) ? String.valueOf(distanceList.get(i)) : "";
            // 添加高差
            String heightDifference = (i < heightDifferenceList.size()) ? String.valueOf(heightDifferenceList.get(i)) : "";
            // 添加成果高程(m)
            String resultElevation = (i < resultElevationList.size()) ? String.valueOf(resultElevationList.get(i)) : "";

            // 添加一行数据
            displayText.append(String.format("%-20s %-15s %-20s %-15s %-15s%n", frontSightName, backSightName, distance, heightDifference, resultElevation));
        }


        // 将拼接好的字符串显示在 TextView 上
        textView11.setText(displayText.toString());









    }














    //获取距离
    // 假设 textView 是你要显示数据的 TextView
    //displayListInTextView(SightSumValuesList, textView);
    //displayListInTextView(AverageTVValuesList, textView);
    //displayListInTextView(distanceList, textView);
    //displayListInTextView(heightDifferenceList, textView);
//            StringBuilder stringBuilder = new StringBuilder();
//            for (String name : frontSightNameList) {
//                stringBuilder.append(name).append("\n"); // 添加换行符
//            }
//
//            // 显示在 TextView 中
//            textView.setText(stringBuilder.toString());

    //用于验证，将列表中的每一行都显示在TextView上,尽适合double类型的列表
    public void displayListInTextView(List<Double> list, TextView textView) {
        StringBuilder stringBuilder = new StringBuilder();

        // 将列表中的每一行值转换为字符串，并添加到 StringBuilder 中
        for (double value : list) {
            stringBuilder.append(value).append("\n"); // 每个值换行显示
        }

        // 将字符串设置到 TextView 中显示
        textView.setText(stringBuilder.toString());
    }




    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }



}

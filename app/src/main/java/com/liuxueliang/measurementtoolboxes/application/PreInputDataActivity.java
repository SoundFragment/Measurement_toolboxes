package com.liuxueliang.measurementtoolboxes.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.liuxueliang.measurementtoolboxes.R;

public class PreInputDataActivity extends AppCompatActivity  implements View.OnClickListener {


    RadioGroup levelRG,routeRG,terrainRG;
    RadioButton firstLevelRB,secondLevelRB,thirdLevelRB,fourthLevelRB,closedLevelRouteRB,attachedLevelRouteRB
            ,branchLevelRouteRB,mountainRB,plainRB;
    EditText recorderET,startElevationET,endElevationET;
    Button startButton,viewButton;

    public static Double startElevation;
    public static Double endElevation;
    String recorder;


    //设置一、二、四等水准不可点击










    //判断是否为山地还是平地
    public static boolean isMountain;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_levelmeasurement_preinputdata);





        // 初始化视图
        initView();


        levelRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (firstLevelRB.isChecked()) {
                    // 执行第一级水准按钮的操作


                }
                else if (secondLevelRB.isChecked()) {
                    // 执行第二级水准按钮的操作

                }
                else if (thirdLevelRB.isChecked()) {
                    // 执行第三级水准按钮的操作


                }
                else if (fourthLevelRB.isChecked()) {
                    // 执行第四级水准按钮的操作

                }

            }
        });

        routeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (closedLevelRouteRB.isChecked()) {
                    // 执行封闭水准线路按钮的操作
                    endElevationET.setEnabled(false);
                    endElevationET.setHint("闭合水准路线起点、终点高程一致，只需输入起点高程");

                }
                else if (attachedLevelRouteRB.isChecked()) {
                    // 执行附属水准线路按钮的操作
                    endElevationET.setEnabled(true);
                    endElevationET.setHint("单位m");


                }

                else if (branchLevelRouteRB.isChecked()) {
                    // 执行分支水准线路按钮的操作
                    endElevationET.setEnabled(false);
                    endElevationET.setHint("支水准路线起点、终点高程一致，只需输入起点高程");

                }
            }
        });



        terrainRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mountainRB.isChecked()) {
                    // 执行山地地形按钮的操作
                    //showMessage("将根据测站进行平差");
                    isMountain=true;


                }
                else if (plainRB.isChecked()) {
                    // 执行平原地形按钮的操作
                    //showMessage("将根据距离进行平差");
                    isMountain=false;

                }
            }
        });



        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //闭合水准
                 if (closedLevelRouteRB.isChecked()){
                     // 获取 EditText 中的文本内容
                     String startElevationText = startElevationET.getText().toString();
                     String endElevationText = endElevationET.getText().toString();
                     String recorder = recorderET.getText().toString();

                     // 检查 EditText 是否为空
                     if (TextUtils.isEmpty(startElevationText)) {
                         // 如果 EditText 为空，显示提示消息或者执行其他操作
                         showMessage("请输入起点高程");
                     } else {
                         // 如果 EditText 不为空，则转换为 double 类型
                         startElevation = Double.parseDouble(startElevationText);
                         endElevation=Double.parseDouble(startElevationText);
                         recorder=recorderET.getText().toString();



                         //保存数据
                         DataModel data = new DataModel(startElevation,endElevation,recorder);
                         data.setStartElevation(startElevation);
                         data.setEndElevation(endElevation);
                         data.setRecorder(recorder);

                         // 将数据存入数据库
                         DatabaseHelper databaseHelper = new DatabaseHelper(PreInputDataActivity.this);
                         String s = databaseHelper.addOne(data);
                         Toast.makeText(PreInputDataActivity.this,"ADD:"+s,Toast.LENGTH_SHORT).show();


                         // 在这里定义你想要执行的操作，比如打开另一个页面
                         Intent intent = new Intent(PreInputDataActivity.this, StartRecordingActivity.class); // 替换成你想要跳转的页面
                         startActivity(intent);
                     }



                }
                 //附合水准
                else if (attachedLevelRouteRB.isChecked()){
                     // 获取 EditText 中的文本内容
                     String startElevationText = startElevationET.getText().toString();
                     String endElevationText = endElevationET.getText().toString();
                     String recorder = recorderET.getText().toString();

                      // 检查 EditText 是否为空
                     if (TextUtils.isEmpty(startElevationText) || TextUtils.isEmpty(endElevationText)) {
                         // 如果任何一个 EditText 为空，显示提示消息或者执行其他操作
                         showMessage("请输入起点和终点高程");
                     } else {
                         // 如果 EditText 都不为空，则转换为 double 类型
                         startElevation = Double.parseDouble(startElevationText);
                         endElevation = Double.parseDouble(endElevationText);
                         recorder=recorderET.getText().toString();


                         //保存数据
                         DataModel data = new DataModel(startElevation,endElevation,recorder);
                         data.setStartElevation(startElevation);
                         data.setEndElevation(endElevation);
                         data.setRecorder(recorder);

                         // 将数据存入数据库
                         DatabaseHelper databaseHelper = new DatabaseHelper(PreInputDataActivity.this);
                         String s = databaseHelper.addOne(data);
                         Toast.makeText(PreInputDataActivity.this,"ADD:"+s,Toast.LENGTH_SHORT).show();


                         Intent intent = new Intent(PreInputDataActivity.this, StartRecordingActivity.class); // 替换成你想要跳转的页面
                         startActivity(intent);
                     }

                }


                //支水准
                else if (branchLevelRouteRB.isChecked()) {
                     // 获取 EditText 中的文本内容
                     String startElevationText = startElevationET.getText().toString();
                     String endElevationText = endElevationET.getText().toString();
                     String recorder = recorderET.getText().toString();

                     // 检查 EditText 是否为空
                     if (TextUtils.isEmpty(startElevationText)) {
                         // 如果 EditText 为空，显示提示消息或者执行其他操作
                         showMessage("请输入起点高程");
                     } else {
                         // 如果 EditText 不为空，则转换为 double 类型
                         startElevation = Double.parseDouble(startElevationText);
                         endElevation=Double.parseDouble(startElevationText);
                         recorder=recorderET.getText().toString();



                         //保存数据
                         DataModel data = new DataModel(startElevation,endElevation,recorder);

                         data.setStartElevation(startElevation);
                         data.setEndElevation(endElevation);
                         data.setRecorder(recorder);

                         // 将数据存入数据库
                         DatabaseHelper databaseHelper = new DatabaseHelper(PreInputDataActivity.this);
                         String s = databaseHelper.addOne(data);
                         Toast.makeText(PreInputDataActivity.this,"加入数据库:"+s,Toast.LENGTH_SHORT).show();

                         Intent intent = new Intent(PreInputDataActivity.this, StartRecordingActivity.class);// 替换成你想要跳转的页面
                         startActivity(intent);



                     }


                }




            }



        });


        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreInputDataActivity.this, StartRecordingActivity.class);// 替换成你想要跳转的页面
                startActivity(intent);
            }
        });

    }












    private void initView() {
        // 初始化视图组件
        // findViewById(R.id.startButton).setOnClickListener(this);    这样写更加简洁

        levelRG = findViewById(R.id.levelRG);
        routeRG = findViewById(R.id.routeRG);
        terrainRG = findViewById(R.id.terrainRG);
        firstLevelRB = findViewById(R.id.firstLevelRB);
        secondLevelRB = findViewById(R.id.secondLevelRB);
        thirdLevelRB = findViewById(R.id.thirdLevelRB);
        fourthLevelRB = findViewById(R.id.fourthLevelRB);
        closedLevelRouteRB = findViewById(R.id.closedLevelRouteRB);
        attachedLevelRouteRB = findViewById(R.id.attachedLevelRouteRB);
        branchLevelRouteRB = findViewById(R.id.branchLevelRouteRB);
        mountainRB = findViewById(R.id.mountainRB);
        plainRB = findViewById(R.id.plainRB);
        recorderET = findViewById(R.id.recorderET);
        startElevationET = findViewById(R.id.startElevationET);
        endElevationET = findViewById(R.id.endElevationET);
        startButton = findViewById(R.id.startButton);
        viewButton = findViewById(R.id.viewButton);

        levelRG.setOnClickListener(this);
        routeRG.setOnClickListener(this);
        terrainRG.setOnClickListener(this);

        //firstLevelRB.setOnClickListener(this);
        //secondLevelRB.setOnClickListener(this);
        thirdLevelRB.setOnClickListener(this);
        //fourthLevelRB.setOnClickListener(this);

        closedLevelRouteRB.setOnClickListener(this);
        attachedLevelRouteRB.setOnClickListener(this);
        branchLevelRouteRB.setOnClickListener(this);

        mountainRB.setOnClickListener(this);
        plainRB.setOnClickListener(this);

        startButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);


    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {


    }
}

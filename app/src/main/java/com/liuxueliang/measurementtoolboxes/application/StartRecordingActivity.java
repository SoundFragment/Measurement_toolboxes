package com.liuxueliang.measurementtoolboxes.application;


import static com.liuxueliang.measurementtoolboxes.universalCode.Caculate.Round;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.liuxueliang.measurementtoolboxes.R;

import java.util.Locale;




public class StartRecordingActivity extends AppCompatActivity implements View.OnClickListener {


    //初始累计差
    double cumulativeSightDiff = 0.0;

    EditText backNameET, frontNameET;
    EditText backTopET1,backBottomET2,backBlackMidET3,frontBlackMidET4,frontTopET5,frontBottomET6
     ,frontRedMidET7,backRedMidET8,backSightET,frontSightET,sightDiffET,cumulativeSightDiffET
      ,blackHeightDiffET,redHeightDiffET,backBlackPlusKMinusRedET,frontBlackPlusKMinusRedET
     ,blackMinusRedHeightDiffET,averageTV;

    TextView stationTextView,validationPromptTV;


    Button validateButton, retestButton,moveStationButton,viewButton,adjustmentButton;

    // 创建一个 Counter 对象
    Counter counter = new Counter();

    Double backTop1,backBottom2,backBlackMid3,frontBlackMid4,frontTop5,frontBottom6
            ,frontRedMid7,backRedMid8,backSight,frontSight,sightDiff
            ,blackHeightDiff,redHeightDiff,backBlackPlusKMinusRed,frontBlackPlusKMinusRed
            ,blackMinusRedHeightDiff,average;

    Double k1,k2,k;

    double backTop, backBottom, backBlackMid, frontBlackMid
    ,frontTop, frontBottom, frontRedMid, backRedMid;

    String backName,frontName;

    int currentValue;

    String formattedBackSight,formattedfrontSight,formattedsightDiff,formattedcumulativeSightDiff;

    double tempaverage;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_levelmeasurement_startrecording);
        initView();




        //校验
        validateButton=findViewById(R.id.validateButton);
        validateButton.setOnClickListener(this);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 用于判断获取的文本内容是否为空
                String backName = backNameET.getText().toString();
                String frontName = frontNameET.getText().toString();
                String backTop = backTopET1.getText().toString();
                String backBottom = backBottomET2.getText().toString();
                String backBlackMid = backBlackMidET3.getText().toString();
                String frontBlackMid = frontBlackMidET4.getText().toString();
                String frontTop = frontTopET5.getText().toString();
                String frontBottom = frontBottomET6.getText().toString();
                String frontRedMid = frontRedMidET7.getText().toString();
                String backRedMid = backRedMidET8.getText().toString();


                // 检查 EditText 是否为空
                if (TextUtils.isEmpty(backName) || TextUtils.isEmpty(frontName) ||
                        TextUtils.isEmpty(backTop) || TextUtils.isEmpty(backBottom) ||
                        TextUtils.isEmpty(backBlackMid) || TextUtils.isEmpty(frontBlackMid) ||
                        TextUtils.isEmpty(frontTop) || TextUtils.isEmpty(frontBottom) ||
                        TextUtils.isEmpty(frontRedMid) || TextUtils.isEmpty(backRedMid)) {
                    // 如果有任何一个 EditText 为空，则显示提示信息
                    Toast.makeText(getApplicationContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                     //获取 EditText 中的文本并转换为相应的数据类型
                    backTop1 = Double.parseDouble(backTopET1.getText().toString());
                    backBottom2 = Double.parseDouble(backBottomET2.getText().toString());
                    backBlackMid3 = Double.parseDouble(backBlackMidET3.getText().toString());
                    frontBlackMid4 = Double.parseDouble(frontBlackMidET4.getText().toString());
                    frontTop5 = Double.parseDouble(frontTopET5.getText().toString());
                    frontBottom6 = Double.parseDouble(frontBottomET6.getText().toString());
                    frontRedMid7 = Double.parseDouble(frontRedMidET7.getText().toString());
                    backRedMid8 = Double.parseDouble(backRedMidET8.getText().toString());





                    backSight=(backTop1-backBottom2)*0.1;  //后尺视距差m
                    // 将后尺视距差保留一位小数，并设置文本
                    formattedBackSight = String.format(Locale.ENGLISH, "%.1f", backSight);
                    backSightET.setText("后视距：" + formattedBackSight + "m");
                    if (backSight > 75) {
                        backSightET.setTextColor(Color.RED);
                    } else {
                        // 如果条件不满足，将文本颜色恢复为默认颜色
                        backSightET.setTextColor(Color.GREEN);
                    }



                    frontSight=(frontTop5-frontBottom6)*0.1;
                    formattedfrontSight = String.format(Locale.ENGLISH, "%.1f", frontSight);      //formattedfrontSight格式化前视距：只保留一位小数
                    frontSightET.setText(String.format("前视距：%.1f m", frontSight));
                    if (frontSight>75){
                        frontSightET.setTextColor(Color.RED);
                    }else{
                        frontSightET.setTextColor(Color.GREEN);
                    }



                    //视距差不超过3米
                    sightDiff=backSight-frontSight;
                    if (sightDiff>3){
                        sightDiffET.setTextColor(Color.RED);
                    }else{
                        sightDiffET.setTextColor(Color.BLACK);
                    }
                    sightDiffET.setText(String.format("后前视距差：%.1f m",sightDiff));
                    formattedsightDiff=String.format(Locale.ENGLISH, "%.1f", sightDiff);




                    //累计视距差不超过6米
                    // 累计视距差加上当前的 sightDiff 值
                    cumulativeSightDiff += sightDiff;
                    if(cumulativeSightDiff>6){
                        cumulativeSightDiffET.setTextColor(Color.RED);
                    }else {
                        cumulativeSightDiffET.setTextColor(Color.GREEN);
                    }
                    cumulativeSightDiffET.setText(String.format("累计视距差：%.1f m",cumulativeSightDiff));
                    formattedcumulativeSightDiff=String.format(Locale.ENGLISH, "%.1f", cumulativeSightDiff);




                    //黑+K-红
                    blackHeightDiff=backBlackMid3-frontBlackMid4;
                    //blackHeightDiffET.setText("黑面高差："+(blackHeightDiff)*0.1+"m");
                    blackHeightDiffET.setText(String.format("黑面高差：%.1fdm", blackHeightDiff ));

                    redHeightDiff=backRedMid8-frontRedMid7;
                    //redHeightDiffET.setText("红面高差："+(redHeightDiff)*0.1+"m");
                    redHeightDiffET.setText(String.format("红面高差：%.1fdm", redHeightDiff ));

                    blackMinusRedHeightDiff = blackHeightDiff-redHeightDiff;
                    if (blackMinusRedHeightDiff > 0) {
                        average = blackMinusRedHeightDiff - 100;
                    } else if (blackMinusRedHeightDiff < 0) {
                        average = blackMinusRedHeightDiff + 100;
                    } else {
                        average = Double.valueOf(0);
                        averageTV.setText("高差中数错误");
                        averageTV.setTextColor(Color.RED);
                        //Toast.makeText(getApplicationContext(), "错误，黑面高差-红面高差不会为0", Toast.LENGTH_SHORT).show();
                    }

                    // 计算 K1 和 K2
                    backBlackPlusKMinusRed = backBlackMid3 - backRedMid8;
                    frontBlackPlusKMinusRed = frontBlackMid4 - frontRedMid7;



                    //判断k
                    if (backBlackPlusKMinusRed < 0) {
                        if (backBlackPlusKMinusRed > -4700) {
                            k1 = backBlackPlusKMinusRed + 4687;
                        }
                        if(backBlackPlusKMinusRed < -4700){
                            k1 = backBlackPlusKMinusRed + 4787;
                        }
                    }
                    else if(backBlackPlusKMinusRed>0) {
                        if (backBlackPlusKMinusRed > 4700) {
                            k1 = backBlackPlusKMinusRed - 4787;
                        }if(backBlackPlusKMinusRed <4700){
                            k1 = backBlackPlusKMinusRed - 4687;
                        }
                    }
                    else{
                        k1= (double) 0;
                        Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                    }

                    // 设置文本内容
                    backBlackPlusKMinusRedET.setText(String.format(Locale.ENGLISH, "%+.0f", k1));
                    // 根据条件设置文本颜色
                    if (abs(k1) > 2) {
                        backBlackPlusKMinusRedET.setTextColor(Color.RED);
                    } else {
                        backBlackPlusKMinusRedET.setTextColor(Color.GREEN);
                    }



                    if (frontBlackPlusKMinusRed < 0) {
                        if (frontBlackPlusKMinusRed > -4700) {
                            k2 = frontBlackPlusKMinusRed + 4687;
                        }
                        if (frontBlackPlusKMinusRed < -4700) {
                            k2 = frontBlackPlusKMinusRed + 4787;
                        }
                    }
                    else if (frontBlackPlusKMinusRed < -4700){
                        if (frontBlackPlusKMinusRed > 4700) {
                            k2 = frontBlackPlusKMinusRed - 4787;
                        }
                        if (frontBlackPlusKMinusRed <4700){
                            k2 = frontBlackPlusKMinusRed - 4687;
                        }
                    }
                    else{
                        k2 = (double) 0;
                        Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                    }

                    // 设置文本内容
                    frontBlackPlusKMinusRedET.setText(String.format(Locale.ENGLISH, "%+.0f", k2));
                    // 根据条件设置文本颜色
                    if (abs(k2) > 2) {
                        frontBlackPlusKMinusRedET.setTextColor(Color.RED);
                    } else {
                        frontBlackPlusKMinusRedET.setTextColor(Color.GREEN);
                    }


                    //核验k值之差
                    k = k1 - k2;
                    blackMinusRedHeightDiffET.setText(String.format(Locale.ENGLISH,"%.0f",k));
                    // 根据条件设置文本颜色
                    if (abs(k) > 3) {
                        blackMinusRedHeightDiffET.setTextColor(Color.RED);
                    } else {
                        blackMinusRedHeightDiffET.setTextColor(Color.GREEN);
                    }


                    // 计算平均高差
                    tempaverage = (blackHeightDiff + redHeightDiff + (blackHeightDiff > redHeightDiff ? 100 : -100)) / 2;
                    averageTV.setText(String.format("平均高差中数：%.3f", Round(tempaverage,3)));

                    Double tempHeightDiff;
                    if (blackHeightDiff > redHeightDiff) {
                        redHeightDiff += 100;
                        tempHeightDiff = abs(blackHeightDiff)-abs(redHeightDiff);
                    } else {
                        redHeightDiff -= 100;
                        tempHeightDiff = abs(blackHeightDiff)-abs(redHeightDiff);
                    }


                    // 判断计算结果是否正确
                    if (abs(tempHeightDiff) != abs(k)) {
                        validationPromptTV.setText("错误！！！\n高差中数不等于K+黑—红");
                        validationPromptTV.setTextColor(Color.RED);
                        validationPromptTV.setGravity(Gravity.CENTER); // 设置文本居中
                        validationPromptTV.setTextSize(18); // 设置字体大小为 18sp
                        //Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                    } else if(abs(tempHeightDiff) == abs(k)) {
                        validationPromptTV.setText("中丝读数校验通过！！！\n符合三等水准测量标准。\n黑面+k-红面之差为："+k+"\n黑、红面高差（±100）之差："+average);
                        validationPromptTV.setTextColor(Color.GREEN);
                        validationPromptTV.setGravity(Gravity.CENTER); // 设置文本居中
                        validationPromptTV.setTextSize(18); // 设置字体大小为 18sp
                        averageTV.setText(String.format("平均高差中数：%.3f", Round(tempaverage,3)));
                    }else {

                    }



                    Toast.makeText(getApplicationContext(), "校验完成。", Toast.LENGTH_SHORT).show();


                }


            }
        });


        //重测改叫清除
        retestButton=findViewById(R.id.retestButton);
        retestButton.setOnClickListener(this);
        retestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(validationPromptTV.getText().toString().trim())) {
                    // 如果内容为空，则跳出提示
                    Toast.makeText(getApplicationContext(), "请校验后再清除", Toast.LENGTH_SHORT).show();
                } else {
                    // 获取当前计数值并显示在 TextView 上
                    counter.decrement();
                    currentValue = counter.getCount();
                    stationTextView.setText("第 "+currentValue+" 站");



                    backTopET1.setHint("1后尺上丝dm");
                    backTopET1.setText("");
                    backBottomET2.setHint("2后尺下丝dm");
                    backBottomET2.setText("");
                    backBlackMidET3.setHint("3后黑中丝dm");
                    backBlackMidET3.setText("");
                    frontBlackMidET4.setHint("4前黑中丝dm");
                    frontBlackMidET4.setText("");
                    frontTopET5.setHint("5前尺上丝dm");
                    frontTopET5.setText("");
                    frontBottomET6.setHint("6前尺下丝dm");
                    frontBottomET6.setText("");
                    frontRedMidET7.setHint("7前红中丝dm");
                    frontRedMidET7.setText("");
                    backRedMidET8.setHint("8后红中丝dm");
                    backRedMidET8.setText("");
                    backSightET.setHint("后视距m");
                    backSightET.setText("");
                    frontSightET.setHint("前视距m");
                    frontSightET.setText("");
                    sightDiffET.setHint("视距差m");
                    sightDiffET.setText("");
                    cumulativeSightDiffET.setHint("累计视距差m");
                    cumulativeSightDiffET.setText("");
                    blackHeightDiffET.setHint("黑面高差dm");
                    blackHeightDiffET.setText("");
                    redHeightDiffET.setHint("红面高差dm");
                    redHeightDiffET.setText("");
                    backBlackPlusKMinusRedET.setHint("后黑+K-后红");
                    backBlackPlusKMinusRedET.setText("");
                    frontBlackPlusKMinusRedET.setHint("前黑+K-前红");
                    frontBlackPlusKMinusRedET.setText("");
                    blackMinusRedHeightDiffET.setHint("K");
                    blackMinusRedHeightDiffET.setText("");
                    averageTV.setHint("黑红面高差中数mm");
                    averageTV.setText("");
                    validationPromptTV.setText("");


                    cumulativeSightDiff -= sightDiff;

                }

            }
        });


        //搬站
        moveStationButton=findViewById(R.id.moveStationButton);
        moveStationButton.setOnClickListener(this);
        moveStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(validationPromptTV.getText().toString().trim())) {
                    // 如果内容为空，则跳出提示
                    Toast.makeText(getApplicationContext(), "请校验后再搬站", Toast.LENGTH_SHORT).show();
                } else {

                    counter.increment();
                    // 获取当前计数值并显示在 TextView 上
                    currentValue = counter.getCount();
                    stationTextView.setText("第 "+currentValue+" 站");

                    backName=String.valueOf(backNameET.getText());
                    frontName=String.valueOf(frontNameET.getText());

                    backTop1 = Double.parseDouble(backTopET1.getText().toString());
                    backBottom2 = Double.parseDouble(backBottomET2.getText().toString());
                    backBlackMid3 = Double.parseDouble(backBlackMidET3.getText().toString());
                    frontBlackMid4 = Double.parseDouble(frontBlackMidET4.getText().toString());
                    frontTop5 = Double.parseDouble(frontTopET5.getText().toString());
                    frontBottom6 = Double.parseDouble(frontBottomET6.getText().toString());
                    frontRedMid7 = Double.parseDouble(frontRedMidET7.getText().toString());
                    backRedMid8 = Double.parseDouble(backRedMidET8.getText().toString());



                    backSight = Double.parseDouble(String.valueOf(backSight));
                    frontSight = Double.parseDouble(String.valueOf(frontSight));
                    sightDiff = Double.parseDouble(String.valueOf(sightDiff));
                    cumulativeSightDiff = Double.parseDouble(String.valueOf(cumulativeSightDiff));
                    blackHeightDiff = Double.parseDouble(String.valueOf(blackHeightDiff));
                    redHeightDiff = Double.parseDouble(String.valueOf(redHeightDiff));
                    backBlackPlusKMinusRed = Double.parseDouble(backBlackPlusKMinusRedET.getText().toString());
                    frontBlackPlusKMinusRed = Double.parseDouble(frontBlackPlusKMinusRedET.getText().toString());
                    blackMinusRedHeightDiff = Double.parseDouble(blackMinusRedHeightDiffET.getText().toString());

                    average = Double.parseDouble(String.valueOf(tempaverage));

                    saveDataToDatabase();

                    backNameET.setHint("点名");
                    backNameET.setText("");
                    frontNameET.setHint("点名");
                    frontNameET.setText("");
                    backTopET1.setHint("1后尺上丝dm");
                    backTopET1.setText("");
                    backBottomET2.setHint("2后尺下丝dm");
                    backBottomET2.setText("");
                    backBlackMidET3.setHint("3后黑中丝dm");
                    backBlackMidET3.setText("");
                    frontBlackMidET4.setHint("4前黑中丝dm");
                    frontBlackMidET4.setText("");
                    frontTopET5.setHint("5前尺上丝dm");
                    frontTopET5.setText("");
                    frontBottomET6.setHint("6前尺下丝dm");
                    frontBottomET6.setText("");
                    frontRedMidET7.setHint("7前红中丝dm");
                    frontRedMidET7.setText("");
                    backRedMidET8.setHint("8后红中丝dm");
                    backRedMidET8.setText("");
                    backSightET.setHint("后视距m");
                    backSightET.setText("");
                    frontSightET.setHint("前视距m");
                    frontSightET.setText("");
                    sightDiffET.setHint("视距差m");
                    sightDiffET.setText("");
                    cumulativeSightDiffET.setHint("累计视距差m");
                    cumulativeSightDiffET.setText("");
                    blackHeightDiffET.setHint("黑面高差dm");
                    blackHeightDiffET.setText("");
                    redHeightDiffET.setHint("红面高差dm");
                    redHeightDiffET.setText("");
                    backBlackPlusKMinusRedET.setHint("后黑+K-后红");
                    backBlackPlusKMinusRedET.setText("");
                    frontBlackPlusKMinusRedET.setHint("前黑+K-前红");
                    frontBlackPlusKMinusRedET.setText("");
                    blackMinusRedHeightDiffET.setHint("K");
                    blackMinusRedHeightDiffET.setText("");
                    averageTV.setHint("黑红面高差中数mm");
                    averageTV.setText("");
                    validationPromptTV.setText("");


                }
            }
        });



        //查看数据
        viewButton=findViewById(R.id.viewButton);
        viewButton.setOnClickListener(this);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(StartRecordingActivity.this, DataPresenterActivity.class);
                startActivity(intent);
            }
        });



        //平差
        adjustmentButton=findViewById(R.id.adjustmentButton);
        adjustmentButton.setOnClickListener(this);
        adjustmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(StartRecordingActivity.this);
                // 设置对话框标题
                builder.setTitle("提示");
                // 设置对话框内容
                builder.setMessage("已经记录了"+currentValue+"站,\n确定开始平差吗？");
                // 设置确认按钮，并设置点击事件
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 在确认按钮被点击时执行的操作

                        Intent intent =new Intent(StartRecordingActivity.this, AdjustedDataActivity.class);
                        startActivity(intent);





                        dialog.dismiss(); // 关闭对话框
                    }
                });
                // 设置取消按钮，并设置点击事件
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 在取消按钮被点击时执行的操作
                        dialog.dismiss(); // 关闭对话框
                    }
                });
                // 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


    }






    private void saveDataToDatabase() {

        // 创建一个 DataModel 对象，用于保存到数据库
        DataModel data = new DataModel( backName, frontName,
                backTop, backBottom, backBlackMid, frontBlackMid,
                frontTop, frontBottom, frontRedMid, backRedMid,
                backSight, frontSight, sightDiff, cumulativeSightDiff,
                blackHeightDiff, redHeightDiff, backBlackPlusKMinusRed,
                frontBlackPlusKMinusRed, blackMinusRedHeightDiff, average);

        backName = backNameET.getText().toString();
        frontName = frontNameET.getText().toString();


        try {
            data.setFrontName(frontName);
            data.setBackName(backName);
            data.setBackTop(backTop1);
            data.setBackBottom(backBottom2);
            data.setBackBlackMid(backBlackMid3);
            data.setFrontBlackMid(frontBlackMid4);
            data.setFrontTop(frontTop5);
            data.setFrontBottom(frontBottom6);
            data.setFrontRedMid(frontRedMid7);
            data.setBackRedMid(backRedMid8);
            data.setBackSight(Double.valueOf(formattedBackSight));
            data.setFrontSight(Double.valueOf(formattedfrontSight));
            data.setSightDiff(Double.valueOf(formattedsightDiff));
            data.setCumulativeSightDiff(Double.valueOf(formattedcumulativeSightDiff));
            data.setBlackHeightDiff(blackHeightDiff);
            data.setRedHeightDiff(redHeightDiff);
            data.setBackBlackPlusKMinusRed(backBlackPlusKMinusRed);
            data.setFrontBlackPlusKMinusRed(frontBlackPlusKMinusRed);
            data.setBlackMinusRedHeightDiff(blackMinusRedHeightDiff);
            data.setAverageTV(average);
        } catch (NumberFormatException e) {
            // 处理转换异常，可以选择给出错误提示或者采取其他处理措施
            Toast.makeText(StartRecordingActivity.this, "输入数据格式错误", Toast.LENGTH_SHORT).show();
            return;
        }

        // 将数据存入数据库
        DatabaseHelper databaseHelper = new DatabaseHelper(StartRecordingActivity.this);
        String result = databaseHelper.addOne(data);
        Toast.makeText(StartRecordingActivity.this,"加入数据库:"+result,Toast.LENGTH_SHORT).show();

    }



    private void initView() {
        stationTextView=findViewById(R.id.stationTextView);
        backNameET=findViewById(R.id.backNameET);
        frontNameET=findViewById(R.id.frontNameET);
        backTopET1=findViewById(R.id.backTopET1);
        backBottomET2=findViewById(R.id.backBottomET2);
        backBlackMidET3=findViewById(R.id.backBlackMidET3);
        frontBlackMidET4=findViewById(R.id.frontBlackMidET4);
        frontTopET5=findViewById(R.id.frontTopET5);
        frontBottomET6=findViewById(R.id.frontBottomET6);
        frontRedMidET7=findViewById(R.id.frontRedMidET7);
        backRedMidET8=findViewById(R.id.backRedMidET8);
        backSightET=findViewById(R.id.backSightET);
        frontSightET=findViewById(R.id.frontSightET);
        sightDiffET=findViewById(R.id.sightDiffET);
        cumulativeSightDiffET=findViewById(R.id.cumulativeSightDiffET);
        blackHeightDiffET=findViewById(R.id.blackHeightDiffET);
        redHeightDiffET=findViewById(R.id.redHeightDiffET);
        backBlackPlusKMinusRedET=findViewById(R.id.backBlackPlusKMinusRedET);
        frontBlackPlusKMinusRedET=findViewById(R.id.frontBlackPlusKMinusRedET);
        blackMinusRedHeightDiffET=findViewById(R.id.blackMinusRedHeightDiffET);
        averageTV=findViewById(R.id.averageTV);
        validationPromptTV=findViewById(R.id.validationPromptTV);

        stationTextView.setOnClickListener(this);
        averageTV.setOnClickListener(this);

    }




    public class Counter {
        private int count;

        // 构造函数，初始化计数为1
        public Counter() {
            this.count = 1;
        }

        // 增加计数
        public void increment() {
            this.count++;
        }

        // 减少计数
        public void decrement() {
            if (this.count > 1) {
                this.count--;
            }
        }

        // 获取当前计数值
        public int getCount() {
            return this.count;
        }
    }
    // 当点击按钮时调用的方法

    public void onClick(View view) {
        // 根据点击的 RadioButton 执行不同的操作





        }
    }









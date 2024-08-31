package com.liuxueliang.measurementtoolboxes.tool;

import static com.liuxueliang.measurementtoolboxes.R.id.radioButton_k3;
import static com.liuxueliang.measurementtoolboxes.R.id.radioButton_k4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.color.DynamicColors;
import com.liuxueliang.measurementtoolboxes.R;

import java.util.Locale;


public class levelcheck_Activity extends AppCompatActivity implements View.OnClickListener{

    TextView shiju1, shiju2, heizhongcha, hongzhongcha, heyan1, heyan2, gaocha, k1, k2,show;
    EditText huoshang, huoxia, heizhong1, heizhong2, qianshang, qianxia, hongzhong1, hongzhong2;
    Button del, go;
    double back_out, up_out, black_out, red_out, k1_out, k2_out, temp_k1, temp_k2, gaocha_out, k_out,tempgaocha;
    double gaocharesult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_levelcheck);
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        initView();




        RadioButton k3 =  findViewById(R.id.radioButton_k3);
        RadioButton k4 =  findViewById(R.id.radioButton_k4);
        RadioGroup radiogroup =  findViewById(R.id.radiogroup);
        TextView hint_heimain =  findViewById(R.id.hint_heimian);
        TextView hint_hongmain = findViewById(R.id.hint_hongmian);
        TextView hint_k =  findViewById(R.id.hint_k);
        EditText heizhong1 = findViewById(R.id.heizhong1);
        EditText heizhong2 = findViewById(R.id.heizhong2);
        EditText hongzhong1 = findViewById(R.id.hongzhong1);
        EditText hongzhong2 = findViewById(R.id.hongzhong2);
        TextView heizhongcha = findViewById(R.id.heizhongcha);
        TextView hongzhongcha = findViewById(R.id.hongzhongcha);
        TextView k1 =  findViewById(R.id.k1);
        TextView k2 = findViewById(R.id.k2);










        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int checkId) {
                if (checkId==radioButton_k4){
                    Toast.makeText(levelcheck_Activity.this, "你选择的是:" + k4.getText(), Toast.LENGTH_SHORT).show();
                    //更改页面提示
                    hint_heimain.setText("黑面");
                    hint_hongmain.setText("红面");
                    hint_k.setText("K+黑-红");
                    heizhong1.setHint("黑面中丝");
                    heizhong2.setHint("黑面中丝");
                    hongzhong1.setHint("红面中丝");
                    hongzhong2.setHint("红面中丝");
                    heizhongcha.setText("黑面中丝之差");
                    hongzhongcha.setText("红面中丝之差");
                    k1.setText("K+黑-红");
                    k2.setText("K+黑-红");

                    clear_k4();
                }

                if (checkId==radioButton_k3){
                    Toast.makeText(levelcheck_Activity.this, "你选择的是:" + k3.getText(), Toast.LENGTH_SHORT).show();
                    //更改页面提示
                    hint_heimain.setText("基本分划");
                    hint_hongmain.setText("辅助分划");
                    hint_k.setText("K+基-辅");
                    heizhong1.setHint("后尺中丝");
                    heizhong2.setHint("前尺中丝");
                    hongzhong1.setHint("后尺中丝");
                    hongzhong2.setHint("前尺中丝");
                    heizhongcha.setText("基本分划之差");
                    hongzhongcha.setText("辅助分划之差");
                    k1.setText("K后+基-辅");
                    k2.setText("K前+基-辅");

                    clear_k3();
                }


            }
        });



    }


    public void initView(){

        shiju1 = findViewById(R.id.shiju1);
        shiju2 = findViewById(R.id.shiju2);
        heizhongcha = findViewById(R.id.heizhongcha);
        hongzhongcha = findViewById(R.id.hongzhongcha);
        heyan1 = findViewById(R.id.heyan1);
        heyan2 = findViewById(R.id.heyan2);
        gaocha = findViewById(R.id.gaocha);
        k1 = findViewById(R.id.k1);
        k2 = findViewById(R.id.k2);

        huoshang = findViewById(R.id.huoshang);
        huoxia = findViewById(R.id.huoxia);
        heizhong1 = findViewById(R.id.heizhong1);
        heizhong2 = findViewById(R.id.heizhong2);
        qianshang = findViewById(R.id.qianshang);
        qianxia = findViewById(R.id.qianxia);
        hongzhong1 = findViewById(R.id.hongzhong1);
        hongzhong2 = findViewById(R.id.hongzhong2);
        show = findViewById(R.id.show);

        del = findViewById(R.id.del);
        go = findViewById(R.id.go);




        huoshang.setOnClickListener(this);
        huoxia.setOnClickListener(this);
        heizhong1.setOnClickListener(this);
        heizhong2.setOnClickListener(this);
        qianshang.setOnClickListener(this);
        qianxia.setOnClickListener(this);
        hongzhong1.setOnClickListener(this);
        hongzhong2.setOnClickListener(this);

        del.setOnClickListener(this);
        go.setOnClickListener(this);




    }


    public void clear_k4() {
        shiju1.setText("后尺视距差");
        shiju2.setText("前尺视距差");
        heizhongcha.setText("黑面中丝之差");
        hongzhongcha.setText("红面中丝之差");
        heyan1.setText("高差之差");
        heyan2.setText("K值之差");
        gaocha.setText("高差中数");
        k1.setText("K+黑-红");
        k2.setText("K+黑-红");
        show.setText("");

        huoshang.setText("");
        huoxia.setText("");
        heizhong1.setText("");
        heizhong2.setText("");
        qianshang.setText("");
        qianxia.setText("");
        hongzhong1.setText("");
        hongzhong2.setText("");



    }

    public void clear_k3() {
        shiju1.setText("后尺视距差");
        shiju2.setText("前尺视距差");
        heizhongcha.setText("基本分划之差");
        hongzhongcha.setText("辅助分划之差");
        heyan1.setText("高差之差");
        heyan2.setText("K值之差");
        gaocha.setText("高差中数");
        k1.setText("K后+黑-红");
        k2.setText("K前+黑-红");
        show.setText("");

        huoshang.setText("");
        huoxia.setText("");
        heizhong1.setText("");
        heizhong2.setText("");
        qianshang.setText("");
        qianxia.setText("");
        hongzhong1.setText("");
        hongzhong2.setText("");

    }


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void onClick(View v) {



        //清除按钮
        Button del = findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton k3 = (RadioButton) findViewById(R.id.radioButton_k3);
                RadioButton k4 = (RadioButton) findViewById(R.id.radioButton_k4);

                if (k4.isChecked()) {
                    // 执行与k4选中状态相关的操作
                    clear_k4();
                    //Toast.makeText(getApplicationContext(), "已清除所有数据\uD83D\uDE18", Toast.LENGTH_LONG).show();


                } else if (k3.isChecked()) {
                    // 执行与k3选中状态相关的操作
                    clear_k3();
                    //Toast.makeText(getApplicationContext(), "已清除所有数据\uD83D\uDE18", Toast.LENGTH_LONG).show();
                }
            }
        });



        //计算按钮
        Button go =findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String back1 = huoshang.getText().toString();
                String back2 = huoxia.getText().toString();
                String up1 = qianshang.getText().toString();
                String up2 = qianxia.getText().toString();
                String black1 = heizhong1.getText().toString();
                String black2 = heizhong2.getText().toString();
                String red1 = hongzhong1.getText().toString();
                String red2 = hongzhong2.getText().toString();
                RadioButton k3 = (RadioButton) findViewById(R.id.radioButton_k3);
                RadioButton k4 = (RadioButton) findViewById(R.id.radioButton_k4);

                if (k4.isChecked()) {
                    // 执行与k4选中状态相关的操作,即K=4687/4787的尺子
                    if(back1.length()>0&back2.length()>0&up1.length()==0&up2.length()==0&black1.length()==0&black2.length()==0&red1.length()==0&red2.length()==0){
                        //这个判断是否只有后尺读数，有则计算后尺视距差
                        double tempback1 = Double.parseDouble(back1);
                        double tempback2 = Double.parseDouble(back2);
                        back_out = tempback1 - tempback2;
                        shiju1.setText(String.format(Locale.ENGLISH, "%.1f", (back_out * 0.1))+"m");
                        //Toast.makeText(getApplicationContext(),"当前计算后尺视距差\uD83D\uDE0E",Toast.LENGTH_SHORT).show();
                    }
                    else if(back1.length()==0&back2.length()==0&up1.length()>0&up2.length()>0&black1.length()==0&black2.length()==0&red1.length()==0&red2.length()==0){
                        //这个判断是否只有前尺读数，有则计算前尺视距差
                        double tempup1 = Double.parseDouble(up1);
                        double tempup2 = Double.parseDouble(up2);
                        up_out = tempup1 - tempup2;
                        shiju2.setText(String.format(Locale.ENGLISH, "%.1f",(up_out*0.1))+"m");
                        //Toast.makeText(getApplicationContext(),"当前计算前尺视距差\uD83D\uDE09",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()==0&back2.length()==0&up1.length()==0&up2.length()==0&black1.length()>0&black2.length()>0&red1.length()==0&red2.length()==0){
                        //黑面中丝之差
                        double tempblack1 = Double.parseDouble(black1);
                        double tempblack2 = Double.parseDouble(black2);
                        black_out = tempblack1 - tempblack2;
                        heizhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(black_out*0.001)));
                        //Toast.makeText(getApplicationContext(),"当前计算黑面中丝之差\uD83E\uDD2A",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()==0&back2.length()==0&up1.length()==0&up2.length()==0&black1.length()==0&black2.length()==0&red1.length()>0&red2.length()>0){
                        //红面中丝之差
                        double tempred1 = Double.parseDouble(red1);
                        double tempred2 = Double.parseDouble(red2);
                        red_out = tempred1 - tempred2;
                        hongzhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(red_out*0.001)));
                        //Toast.makeText(getApplicationContext(),"当前计算红面中丝之差\uD83D\uDE0F",Toast.LENGTH_LONG).show();
                    }
                    else if (back1.length()>0&back2.length()>0&up1.length()>0&up2.length()>0&black1.length()==0&black2.length()==0&red1.length()==0&red2.length()==0){
                        //前、后尺视距差
                        double tempback1 = Double.parseDouble(back1);
                        double tempback2 = Double.parseDouble(back2);
                        back_out = tempback1 - tempback2;
                        shiju1.setText(String.format(Locale.ENGLISH, "%.1f", (back_out * 0.1))+"m");

                        double tempup1 = Double.parseDouble(up1);
                        double tempup2 = Double.parseDouble(up2);
                        up_out = tempup1 - tempup2;
                        shiju2.setText(String.format(Locale.ENGLISH, "%.1f",(up_out*0.1))+"m");
                        double shijucha = back_out - up_out;
                        String shijucha_out = String.format(Locale.ENGLISH,"%.1f",(shijucha*0.1));
                        show.setText("后前尺视距差为：" + shijucha_out + "m");

                    }
                    else if (back1.length()==0&back2.length()==0&up1.length()==0&up2.length()==0&black1.length()>0&black2.length()>0&red1.length()>0&red2.length()>0){
                        //黑红面中丝之差
                        double tempblack1 = Double.parseDouble(black1);
                        double tempblack2 = Double.parseDouble(black2);
                        black_out = tempblack1 - tempblack2;
                        heizhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(black_out*0.001)));

                        double tempred1 = Double.parseDouble(red1);
                        double tempred2 = Double.parseDouble(red2);
                        red_out = tempred1 - tempred2;
                        hongzhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(red_out*0.001)));

                        tempgaocha = black_out - red_out;
                        if (tempgaocha>0){
                            gaocha_out=tempgaocha -100;
                            heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));
                        }
                        if(tempgaocha<0){
                            gaocha_out=tempgaocha + 100;
                            heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));
                        }
                        if(tempgaocha==0){
                            gaocha_out=tempgaocha;
                            heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));
                            gaocha.setText("高差中数错误");
                            Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                        }



                        temp_k1 = tempblack1 - tempred1;
                        if (temp_k1 < 0) {
                            if (temp_k1 > -4700) {
                                k1_out = temp_k1 + 4687;
                            }
                            if(temp_k1 < -4700){
                                k1_out = temp_k1 + 4787;
                            }
                            k1.setText(String.format(Locale.ENGLISH, "%.4f",(k1_out*0.001)));
                        }
                        else if(temp_k1>0) {
                            if (temp_k1 > 4700) {
                                k1_out = temp_k1 - 4787;
                            }if(temp_k1 <4700){
                                k1_out = temp_k1 - 4687;
                            }
                            k1.setText(String.format(Locale.ENGLISH, "%.4f",(k1_out*0.001)));
                        }
                        else{
                            k1_out=0;
                            k1.setText(String.format(Locale.ENGLISH, "%.4f",(k1_out*0.001)));
                            Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                        }


                        temp_k2 = tempblack2 - tempred2;
                        if (temp_k2 < 0) {
                            if (temp_k2 > -4700) {
                                k2_out = temp_k2 + 4687;
                            }
                            if (temp_k2 < -4700) {
                                k2_out = temp_k2 + 4787;
                            }
                            k2.setText(String.format(Locale.ENGLISH, "%.4f",(k2_out*0.001)));
                        }
                        else if (temp_k2 < -4700){
                            if (temp_k2 > 4700) {
                                k2_out = temp_k2 - 4787;
                            }
                            if (temp_k2 <4700){
                                k2_out = temp_k2 - 4687;
                            }
                            k2.setText(String.format(Locale.ENGLISH, "%.4f",(k2_out*0.001)));
                        }
                        else{
                            k2_out = 0;
                            k2.setText(String.format(Locale.ENGLISH, "%.4f",(k2_out*0.001)));
                            Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                        }

                        k_out = k1_out - k2_out;
                        heyan2.setText(String.format(Locale.ENGLISH,"%.3f",k_out*0.001));

                        if (k_out == gaocha_out) {
                            if(black_out>red_out){
                                gaocharesult = (black_out+red_out+100)/2;
                                gaocha.setText(String.format(Locale.ENGLISH, "%.4f",(gaocharesult*0.001)));
                            }
                            if(black_out<red_out){
                                gaocharesult = (black_out+red_out-100)/2;
                                gaocha.setText(String.format(Locale.ENGLISH, "%.4f",(gaocharesult*0.001)));
                            }
                        } else {
                            gaocha.setText("错误！");
                            show.setText("错误：高差中数不等于K+黑—红");

                        }
                        //Toast.makeText(getApplicationContext(),"计算完成！\uD83D\uDE18",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()>0&back2.length()>0&up1.length()>0&up2.length()>0&black1.length()>0&black2.length()>0&red1.length()>0&red2.length()>0){
                        //计算全部数据
                        double tempback1 = Double.parseDouble(back1);
                        double tempback2 = Double.parseDouble(back2);
                        back_out = tempback1 - tempback2;
                        shiju1.setText(String.format(Locale.ENGLISH, "%.1f", (back_out * 0.1))+"m");

                        double tempup1 = Double.parseDouble(up1);
                        double tempup2 = Double.parseDouble(up2);
                        up_out = tempup1 - tempup2;
                        shiju2.setText(String.format(Locale.ENGLISH, "%.1f",(up_out*0.1))+"m");

                        double tempblack1 = Double.parseDouble(black1);
                        double tempblack2 = Double.parseDouble(black2);
                        black_out = tempblack1 - tempblack2;
                        heizhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(black_out*0.001)));

                        double tempred1 = Double.parseDouble(red1);
                        double tempred2 = Double.parseDouble(red2);
                        red_out = tempred1 - tempred2;
                        hongzhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(red_out*0.001)));


                        tempgaocha = black_out - red_out;
                        if (tempgaocha>0){
                            gaocha_out=tempgaocha -100;
                            heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));
                        }
                        if(tempgaocha<0){
                            gaocha_out=tempgaocha + 100;
                            heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));
                        }
                        if(tempgaocha==0){
                            gaocha_out=tempgaocha;
                            heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));
                            gaocha.setText("高差中数错误");
                            Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                        }



                        temp_k1 = tempblack1 - tempred1;
                        if (temp_k1 < 0) {
                            if (temp_k1 > -4700) {
                                k1_out = temp_k1 + 4687;
                            }
                            if(temp_k1 < -4700){
                                k1_out = temp_k1 + 4787;
                            }
                            k1.setText(String.format(Locale.ENGLISH, "%.4f",(k1_out*0.001)));
                        }
                        else if(temp_k1>0) {
                            if (temp_k1 > 4700) {
                                k1_out = temp_k1 - 4787;
                            }if(temp_k1 <4700){
                                k1_out = temp_k1 - 4687;
                            }
                            k1.setText(String.format(Locale.ENGLISH, "%.4f",(k1_out*0.001)));
                        }
                        else{
                            k1_out=0;
                            k1.setText(String.format(Locale.ENGLISH, "%.4f",(k1_out*0.001)));
                            Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                        }

                        temp_k2 = tempblack2 - tempred2;
                        if (temp_k2 < 0) {
                            if (temp_k2 > -4700) {
                                k2_out = temp_k2 + 4687;
                            }
                            if (temp_k2 < -4700) {
                                k2_out = temp_k2 + 4787;
                            }
                            k2.setText(String.format(Locale.ENGLISH, "%.4f",(k2_out*0.001)));
                        }
                        else if (temp_k2 < -4700){
                            if (temp_k2 > 4700) {
                                k2_out = temp_k2 - 4787;
                            }
                            if (temp_k2 <4700){
                                k2_out = temp_k2 - 4687;
                            }
                            k2.setText(String.format(Locale.ENGLISH, "%.4f",(k2_out*0.001)));
                        }
                        else{
                            k2_out = 0;
                            k2.setText(String.format(Locale.ENGLISH, "%.4f",(k2_out*0.001)));
                            Toast.makeText(getApplicationContext(),"错误，黑面高差-红面高差不会为0",Toast.LENGTH_SHORT).show();
                        }


                        k_out = k1_out - k2_out;
                        heyan2.setText(String.format(Locale.ENGLISH,"%.3f",k_out*0.001));

                        if (k_out == gaocha_out) {
                            if(black_out>red_out){
                                gaocharesult = (black_out+red_out+100)/2;
                                gaocha.setText(String.format(Locale.ENGLISH, "%.4f",(gaocharesult*0.001)));
                            }
                            if(black_out<red_out){
                                gaocharesult = (black_out+red_out-100)/2;
                                gaocha.setText(String.format(Locale.ENGLISH, "%.4f",(gaocharesult*0.001)));
                            }
                        } else {
                            gaocha.setText("高差中数错误");
                            Toast.makeText(getApplicationContext(),"错误：高差中数不等于K+黑—红",Toast.LENGTH_SHORT).show();
                        }

                        double shijucha = back_out - up_out;
                        String shijucha_out = String.format(Locale.ENGLISH,"%.1f",(shijucha*0.1));
                        String gaocharesult_out = String.format(Locale.ENGLISH,"%.4f",(gaocharesult*0.001));
                        show.setText("后前尺视距差为：" + shijucha_out + "m\n"+ "高差为："  +gaocharesult_out );


                        //Toast.makeText(getApplicationContext(),"计算完成\uD83D\uDE18",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"请输入数据\uD83D\uDE21",Toast.LENGTH_SHORT).show();
                    }





                } else if (k3.isChecked()) {
                    // 执行与k3选中状态相关的操作，也就是K=301.55cm
                    //只计算后尺视距差
                    if(back1.length()>0&back2.length()>0&up1.length()==0&up2.length()==0&black1.length()==0&black2.length()==0&red1.length()==0&red2.length()==0){
                        double tempback1 = Double.parseDouble(back1);
                        double tempback2 = Double.parseDouble(back2);
                        back_out = tempback1 - tempback2;
                        shiju1.setText(String.format(Locale.ENGLISH, "%.1f", (back_out * 0.1))+"m");
                        //Toast.makeText(getApplicationContext(),"当前计算后尺视距差",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()==0&back2.length()==0&up1.length()>0&up2.length()>0&black1.length()==0&black2.length()==0&red1.length()==0&red2.length()==0){
                        double tempup1 = Double.parseDouble(up1);
                        double tempup2 = Double.parseDouble(up2);
                        up_out = tempup1 - tempup2;
                        shiju2.setText(String.format(Locale.ENGLISH, "%.1f",(up_out*0.1))+"m");
                        //Toast.makeText(getApplicationContext(),"当前计算前尺视距差",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()==0&back2.length()==0&up1.length()==0&up2.length()==0&black1.length()>0&black2.length()>0&red1.length()==0&red2.length()==0){
                        double tempblack1 = Double.parseDouble(black1);
                        double tempblack2 = Double.parseDouble(black2);
                        black_out = tempblack1 - tempblack2;
                        heizhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(black_out*0.001)));
                        //Toast.makeText(getApplicationContext(),"当前计算基本分化之差",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()==0&back2.length()==0&up1.length()==0&up2.length()==0&black1.length()==0&black2.length()==0&red1.length()>0&red2.length()>0){
                        double tempred1 = Double.parseDouble(red1);
                        double tempred2 = Double.parseDouble(red2);
                        red_out = tempred1 - tempred2;
                        hongzhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(red_out*0.001)));
                        //Toast.makeText(getApplicationContext(),"当前计算辅助分化之差",Toast.LENGTH_LONG).show();
                    }
                    else if (back1.length()>0&back2.length()>0&up1.length()>0&up2.length()>0&black1.length()==0&black2.length()==0&red1.length()==0&red2.length()==0){
                        double tempback1 = Double.parseDouble(back1);
                        double tempback2 = Double.parseDouble(back2);
                        back_out = tempback1 - tempback2;
                        shiju1.setText(String.format(Locale.ENGLISH, "%.1f", (back_out * 0.1))+"m");

                        double tempup1 = Double.parseDouble(up1);
                        double tempup2 = Double.parseDouble(up2);
                        up_out = tempup1 - tempup2;
                        shiju2.setText(String.format(Locale.ENGLISH, "%.1f",(up_out*0.1))+"m");
                        double shijucha = back_out - up_out;
                        String shijucha_out = String.format(Locale.ENGLISH,"%.1f",(shijucha*0.1));
                        show.setText("后前尺视距差为：" + shijucha_out + "m    ");

                    }
                    else if (back1.length()==0&back2.length()==0&up1.length()==0&up2.length()==0&black1.length()>0&black2.length()>0&red1.length()>0&red2.length()>0){
                        double tempblack1 = Double.parseDouble(black1);
                        double tempblack2 = Double.parseDouble(black2);
                        black_out = tempblack1 - tempblack2;
                        heizhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(black_out*0.001)));

                        double tempred1 = Double.parseDouble(red1);
                        double tempred2 = Double.parseDouble(red2);
                        red_out = tempred1 - tempred2;
                        hongzhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(red_out*0.001)));

                        tempgaocha = black_out - red_out;
                        gaocha_out = tempgaocha;
                        //heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));


                        temp_k1 = tempblack1 - tempred1;
                        k1_out =temp_k1 + 301550;
                        k1.setText(String.format(Locale.ENGLISH, "%.3f",(k1_out*0.001)));


                        temp_k2 = tempblack2 - tempred2;
                        k2_out = temp_k2 + 301550;
                        k2.setText(String.format(Locale.ENGLISH, "%.3f",(k2_out*0.001)));


                        k_out = k1_out - k2_out;
                        //heyan2.setText(String.format(Locale.ENGLISH,"%.3f",k_out*0.001));

                        if (k_out == gaocha_out) {
                            gaocharesult = (black_out*0.001+red_out*0.001)/2;
                            //gaocha.setText(String.format(Locale.ENGLISH, "%.4f",gaocharesult));
                            show.setText("高差为："  +gaocharesult);
                        } else {
                            gaocha.setText("高差中数错误");
                            Toast.makeText(getApplicationContext(),"错误：高差中数不等于K+基—辅",Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(getApplicationContext(),"计算完成",Toast.LENGTH_LONG).show();
                    }
                    else if(back1.length()>0&back2.length()>0&up1.length()>0&up2.length()>0&black1.length()>0&black2.length()>0&red1.length()>0&red2.length()>0){
                        double tempback1 = Double.parseDouble(back1);
                        double tempback2 = Double.parseDouble(back2);
                        back_out = tempback1 - tempback2;
                        shiju1.setText(String.format(Locale.ENGLISH, "%.1f", (back_out * 0.1))+"m");

                        double tempup1 = Double.parseDouble(up1);
                        double tempup2 = Double.parseDouble(up2);
                        up_out = tempup1 - tempup2;
                        shiju2.setText(String.format(Locale.ENGLISH, "%.1f",(up_out*0.1))+"m");

                        double tempblack1 = Double.parseDouble(black1);
                        double tempblack2 = Double.parseDouble(black2);
                        black_out = tempblack1 - tempblack2;
                        heizhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(black_out*0.001)));

                        double tempred1 = Double.parseDouble(red1);
                        double tempred2 = Double.parseDouble(red2);
                        red_out = tempred1 - tempred2;
                        hongzhongcha.setText(String.format(Locale.ENGLISH, "%.3f",(red_out*0.001)));


                        tempgaocha = black_out - red_out;
                        gaocha_out = tempgaocha;
                        heyan1.setText(String.format(Locale.ENGLISH,"%.3f",(gaocha_out*0.001)));


                        temp_k1 = tempblack1 - tempred1;
                        k1_out =temp_k1 + 301550;
                        k1.setText(String.format(Locale.ENGLISH, "%.3f",(k1_out*0.001)));


                        temp_k2 = tempblack2 - tempred2;
                        k2_out = temp_k2 + 301550;
                        k2.setText(String.format(Locale.ENGLISH, "%.3f",(k2_out*0.001)));



                        k_out = k1_out - k2_out;
                        heyan2.setText(String.format(Locale.ENGLISH,"%.3f",k_out*0.001));

                        if (k_out == gaocha_out) {
                            gaocharesult = (black_out*0.001+red_out*0.001)/2;
                            gaocha.setText(String.format(Locale.ENGLISH, "%.4f",gaocharesult));
                        } else {
                            gaocha.setText("高差中数错误");
                            Toast.makeText(getApplicationContext(),"错误：高差中数不等于K+基—辅",Toast.LENGTH_SHORT).show();
                        }

                        double shijucha = back_out - up_out;
                        String shijucha_out = String.format(Locale.ENGLISH,"%.1f",(shijucha*0.1));
                        String gaocharesult_out = String.format(Locale.ENGLISH,"%.4f",gaocharesult);
                        show.setText("后前尺视距差为：" + shijucha_out + "m\n"+ "高差为："  +gaocharesult_out );


                        //Toast.makeText(getApplicationContext(),"计算完成",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"请输入数据",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }






}



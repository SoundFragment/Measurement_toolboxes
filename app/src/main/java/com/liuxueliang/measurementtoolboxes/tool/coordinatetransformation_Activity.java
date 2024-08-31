package com.liuxueliang.measurementtoolboxes.tool;

import static com.liuxueliang.measurementtoolboxes.R.id.button;
import static com.liuxueliang.measurementtoolboxes.R.id.button_delete;
import static com.liuxueliang.measurementtoolboxes.R.id.clear_text;
import static com.liuxueliang.measurementtoolboxes.R.id.fangweijiao_button;
import static com.liuxueliang.measurementtoolboxes.R.id.radio;
import static com.liuxueliang.measurementtoolboxes.R.id.radioButton1;
import static com.liuxueliang.measurementtoolboxes.R.id.radiogroup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.liuxueliang.measurementtoolboxes.R;
import com.liuxueliang.measurementtoolboxes.universalCode.Caculate;

public class coordinatetransformation_Activity extends AppCompatActivity implements View.OnClickListener {



    //region 定义变量
     RadioButton rd_Krassovsky,rd_ICGG_1975,rd_WGS_84,rd_CGCS2000;
     RadioButton rd_6,rd_3;
     RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
     RadioGroup radiogroup;
     EditText ed11,ed12,ed21,ed22,ed31,ed32;
     TextView t1,t2;
     Button button_start,button_delete;
     double a, b, c, f, e1, e2;//基本椭球参数
     boolean BLH_XYZ,XYZ_BLH,BLH_xyH,xyH_BLH;//记录用户选择的转换方式
     double B,L,H,X,Y,Z,x,y;
     int k;//存储3度6度带信息
    //endregion




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_coordinatetransformation);
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        initView();

        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//不让自动弹出软键盘

        radiogroup = findViewById(R.id.radiogroup);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);
        radioButton4=findViewById(R.id.radioButton4);

        button_start=findViewById(R.id.button_start);










        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int checkedId) {
                BLH_xyH = false;
                BLH_XYZ = false;
                XYZ_BLH = false;
                xyH_BLH = false;
                ed11.setText("");
                ed21.setText("");
                ed31.setText("");
                ed12.setText("");
                ed22.setText("");
                ed32.setText("");
                if(radioButton1.isChecked()){
                    t1.setText("大地坐标");
                    t2.setText("空间直角坐标");
                    ed11.setHint("B(dms)");
                    ed21.setHint("L(dms)");
                    ed31.setHint("H(m)");
                    ed12.setHint("X(m)");
                    ed22.setHint("Y(m)");
                    ed32.setHint("Z(m)");
                    BLH_XYZ = true;
                }
                else if(radioButton2.isChecked()){
                    t1.setText("空间直角坐标");
                    t2.setText("大地坐标");
                    ed11.setHint("X(m)");
                    ed21.setHint("Y(m)");
                    ed31.setHint("Z(m)");
                    ed12.setHint("B(dms)");
                    ed22.setHint("L(dms)");
                    ed32.setHint("H(m)");
                    XYZ_BLH = true;
                }
                else if(radioButton3.isChecked()){
                    t1.setText("大地坐标");
                    t2.setText("高斯平面坐标");
                    ed11.setHint("B(dms)");
                    ed21.setHint("L(dms)");
                    ed31.setHint("H(m)");
                    ed12.setHint("x(m)");
                    ed22.setHint("y(m)");
                    ed32.setHint("H(m)");
                    BLH_xyH = true;

                }
                else if(radioButton4.isChecked()){
                    t1.setText("高斯平面坐标");
                    t2.setText("大地坐标");
                    ed11.setHint("x(m)");
                    ed21.setHint("y(m)");
                    ed31.setHint("H(m)");
                    ed12.setHint("B(dms)");
                    ed22.setHint("L(dms)");
                    ed32.setHint("H(m)");
                    xyH_BLH = true;

                }
            }
        });



        button_start=findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                // 获取椭球参数
                getEllipsoidParameters();


                // 获取坐标转换方式
                getConversionType();

                // 执行坐标转换
                performCoordinateTransformation();
            }


        });

        button_delete=findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }

    private void initView() {
        ed11 = findViewById(R.id.zuobiao_ed11);
        ed12 = findViewById(R.id.zuobiao_ed12);
        ed21 =  findViewById(R.id.zuobiao_ed21);
        ed22 = findViewById(R.id.zuobiao_ed22);
        ed31 = findViewById(R.id.zuobiao_ed31);
        ed32 = findViewById(R.id.zuobiao_ed32);
        t1 = findViewById(R.id.zuobiao_t1) ;
        t2 = findViewById(R.id.zuobiao_t2) ;

        rd_Krassovsky = findViewById(R.id.zuobiao_Krassovsky);
        rd_ICGG_1975 = findViewById(R.id.zuobiao_ICGG1975);
        rd_WGS_84 = findViewById(R.id.zuobiao_WGS_84);
        rd_CGCS2000 = findViewById(R.id.zuobiao_CGCS2000);
        rd_6 =  findViewById(R.id.zuobiao_6dudai);
        rd_3 = findViewById(R.id.zuobiao_3dudai);




        ed11.setOnClickListener(this);
        ed12.setOnClickListener(this);
        ed21.setOnClickListener(this);
        ed22.setOnClickListener(this);
        ed31.setOnClickListener(this);
        ed32.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);

        rd_Krassovsky.setOnClickListener(this);
        rd_ICGG_1975.setOnClickListener(this);
        rd_WGS_84.setOnClickListener(this);
        rd_CGCS2000.setOnClickListener(this);
        rd_3.setOnClickListener(this);
        rd_6.setOnClickListener(this);



    }




    private void getConversionType() {
        if (rd_Krassovsky.isChecked()) {
            a = 6378245; // 长半轴
            e1 = 0.0066934216230; // 第一偏心率的平方
            e2 = 0.0067385254147; // 第二偏心率的平方
        } else if (rd_ICGG_1975.isChecked()) {
            a = 6378140;
            e1 = 0.0066943849996;
            e2 = 0.0067395018195;
        } else if (rd_WGS_84.isChecked()) {
            a = 6378137;
            e1 = 0.006694379990;
            e2 = 0.006739496742;
        } else if (rd_CGCS2000.isChecked()) {
            a = 6378137;
            e1 = 0.006694380022;
            e2 = 0.006739496775;
        }
        
    }

    private void getEllipsoidParameters() {
        if (rd_6.isChecked()) {
            k = 1;
        } else if (rd_3.isChecked()) {
            k = 0;
        }

    }

    @SuppressLint("DefaultLocale")
    private void performCoordinateTransformation() {
        // 获取输入数据
        try {
            if (BLH_XYZ) {
                B = Caculate.dmstohudu(Double.parseDouble(ed11.getText().toString()));
                L = Caculate.dmstohudu(Double.parseDouble(ed21.getText().toString()));
                H = Double.parseDouble(ed31.getText().toString());
            } else if (XYZ_BLH) {
                X = Double.parseDouble(ed11.getText().toString());
                Y = Double.parseDouble(ed21.getText().toString());
                Z = Double.parseDouble(ed31.getText().toString());
            } else if (BLH_xyH) {
                B = Double.parseDouble(ed11.getText().toString());
                L = Double.parseDouble(ed21.getText().toString());
                H = Double.parseDouble(ed31.getText().toString());
            } else if (xyH_BLH) {
                x = Double.parseDouble(ed11.getText().toString());
                y = Double.parseDouble(ed21.getText().toString());
                H = Double.parseDouble(ed31.getText().toString());
            }
        } catch (Exception e) {
            Toast.makeText(coordinatetransformation_Activity.this, "请正确填写全部数据", Toast.LENGTH_SHORT).show();
            return;
        }

        // 进行坐标转换
        if (BLH_XYZ) {
            // 大地转直角
            double N = a / Math.sqrt(1 - e1 * Math.sin(B) * Math.sin(B)); // 法线长
            X = (N + H) * Math.cos(B) * Math.cos(L);
            Y = (N + H) * Math.cos(B) * Math.sin(L);
            Z = (N * (1 - e1) + H) * Math.sin(B);

            // 显示结果
            ed12.setText(String.format("%f", Caculate.Round(X, 6)));
            ed22.setText(String.format("%f", Caculate.Round(Y, 6)));
            ed32.setText(String.format("%f", Caculate.Round(Z, 6)));
        } else if (XYZ_BLH) {
            // TODO: 实现直角转大地的逻辑
            double B0, B1, Bs;//迭代初值,TAN B0
            double N;
            //L = Math.Atan(Y / X);          //L的值域为-90~90度
            L = Math.atan2(Y, X);            //atan2值域为-180~180度，可以很好地表示反正切值
            if (L < 0)
            {
                L = Math.PI * 2 + L;
            }

            B0 = Z / Math.sqrt(X * X + Y * Y);
            do
            {
                B1 = (Z + a * e1 * B0 /Math.sqrt(1 + (1 - e1) * B0 * B0)) / Math.sqrt(X * X + Y * Y);
                Bs = B1 - B0;
                B0 = B1;
            }
            while (Math.abs(Bs) > Math.tan(Caculate.dmstohudu(0.00000001)));//限差为0.0001秒
            B = Math.atan(B1);

            N = a / Math.sqrt(1 - e1 * Math.sin(B) * Math.sin(B));
            H = Math.sqrt(X * X + Y * Y) / Math.cos(B) - N;

            L = Caculate.hudutodms(L);
            B = Caculate.hudutodms(B);

            ed12.setText(String.valueOf(B));
            ed22.setText(String.valueOf(L));
            ed32.setText(String.format("%f",Caculate.Round(H,6)));
        } else if (BLH_xyH) {
            // 大地转高斯平面
            // TODO: 实现大地转高斯平面的逻辑
            //公式精确到0.001米
            double a0, a2, a4, a6, a8;
            double m0, m2, m4, m6, m8;
            double yita2, t, N;
            double X;//子午线弧长
            double l,L0;//经差以及中央子午线
            double n;// 辅助量
            double daihao;//带号
            //region 判断分带
            if (k == 1)//6度带
            {
                if (L % 6 == 0)
                {
                    daihao = (int)(L / 6);
                    L0 = 6 * daihao - 3;
                }
                else
                {
                    daihao = (int)(L / 6) + 1;
                    L0 = 6 * daihao - 3;
                }
            }
            else //3度带
            {
                if ((L - 1.5) % 3 == 0)
                {
                    daihao = (int)((L - 1.5) / 3);//int 强制类型转换返回最接近0的整数部分，-0.1 返回 0，0.1 返回 0
                    L0 = 3 * daihao;
                }
                else
                {
                    daihao = (int)Math.floor((L - 1.5) / 3) + 1;//math.floor 返回原类型小于原数值的整数部分-0.1 返回 -1， 0.1 返回 0
                    L0 = 3 * daihao;
                }
            }
            //endregion
            l = Caculate.dmstohudu(L) - Caculate.dmstohudu(L0);
            B = Caculate.dmstohudu(B);
            //L = Caculates.dmstohudu(L);



            //region 计算子午线弧长
            m0 = a * (1 - e1);
            m2 = 3 * e1 * m0 / 2;
            m4 = 5 * e1 * m2 / 4;
            m6 = 7 * e1 * m4 / 6;
            m8 = 9 * e1 * m6 / 8;

            a0 = m0 + m2 / 2 + 3 * m4 / 8 + 5 * m6 / 16 + 35 * m8 / 128;
            a2 = m2 / 2 + m4 / 2 + 15 * m6 / 32 + 7 * m8 / 16;
            a4 = m4 / 8 + 3 * m6 / 16 + 7 * m8 / 32;
            a6 = m6 / 32 + m8 / 16;
            a8 = m8 / 128;
            //子午线弧长计算公式2
            X = a0 * B - a2 * Math.sin(2 * B) / 2 + a4 * Math.sin(4 * B) / 4 - a6 * Math.sin(6 * B) / 6 + a8 * Math.sin(8 * B) / 8;
            //endregion



            yita2 = e2 * Math.cos(B) * Math.cos(B);//η平方
            t = Math.tan(B);
            N = a / Math.sqrt(1 - e1 * Math.sin(B) * Math.sin(B));



            //region 计算高斯平面坐标
            n = Math.cos(B) * l;
            x = X + N * t * (n * n / 2 + Math.pow(n, 4) * (5 - t * t + 9 * yita2 + 4 * yita2 * yita2) / 24 + Math.pow(n, 6) * (61 - 58 * t * t + Math.pow(t, 4)) / 720);
            y = N * (n + Math.pow(n, 3) * (1 - t * t + yita2) / 6 + Math.pow(n, 5) * (5 - 18 * t * t + Math.pow(t, 4) + 14 * yita2 - 58 * yita2 * t * t) / 120);
            y = y + 500000 + daihao * 1000000;//计算高斯通用坐标
            //endregion
            ed12.setText(String.format("%f",Caculate.Round(x,6)));
            ed22.setText(String.format("%f",Caculate.Round(y,6)));
            ed32.setText(String.format("%f",Caculate.Round(H,6)));
        } else if (xyH_BLH) {
            // 高斯平面转大地
            // TODO: 实现高斯平面转大地的逻辑
            //公式精确到0.0001"
            double a0, a2, a4, a6, a8;
            double m0, m2, m4, m6, m8;
            double Bfs,Bf0,Bfi,FBf;//定义迭代变量
            double tf, yitaf2, Nf, Vf;
            double l, L0;//经差以及中央子午线
            double daihao;//带号
            daihao = (int)(y / 1000000);
            y = y - daihao * 1000000 - 500000;
            //region 判断分带
            if (k == 1)//6度带
            {
                L0 = 6 * daihao - 3;
            }
            else  //3度带
            {
                L0 = 3 * daihao;
            }
            //endregion
            //region 求底点纬度
            m0 = a * (1 - e1);
            m2 = 3 * e1 * m0 / 2;
            m4 = 5 * e1 * m2 / 4;
            m6 = 7 * e1 * m4 / 6;
            m8 = 9 * e1 * m6 / 8;

            a0 = m0 + m2 / 2 + 3 * m4 / 8 + 5 * m6 / 16 + 35 * m8 / 128;
            a2 = m2 / 2 + m4 / 2 + 15 * m6 / 32 + 7 * m8 / 16;
            a4 = m4 / 8 + 3 * m6 / 16 + 7 * m8 / 32;
            a6 = m6 / 32 + m8 / 16;
            a8 = m8 / 128;
            //底点纬度 当x = X时，x轴上点的纬度,用子午线弧长的公式倒推迭代出来
            Bf0 = x / a0;
            do
            {
                FBf = - a2 * Math.sin(2 * Bf0) / 2 + a4 * Math.sin(4 * Bf0) / 4 - a6 * Math.sin(6 * Bf0) / 6 + a8 * Math.sin(8 * Bf0) / 8;
                Bfi = (x - FBf) / a0;
                Bfs = Bfi - Bf0;
                Bf0 = Bfi;
            }
            while (Math.abs(Bfs) > Caculate.dmstohudu(0.0000001));
            //endregion

            
            //region 求经纬度
            yitaf2 = e2 * Math.cos(Bfi) * Math.cos(Bfi);
            tf = Math.tan(Bfi);
            Nf = a / Math.sqrt(1 - e1 * Math.sin(Bfi) * Math.sin(Bfi));
            Vf = Math.sqrt(1 + e2 * Math.cos(Bfi) * Math.cos(Bfi));

            B = Bfi - Vf * Vf * tf * (Math.pow((y / Nf),2) - (5 + 3 * tf * tf + yitaf2 - 9 * yitaf2 * tf * tf) * Math.pow((y / Nf), 4) / 12 + (61 + 90 * tf * tf + 45 * tf * tf) * Math.pow((y / Nf),6) / 360) / 2;
            l = (y / Nf - (1 + 2 * tf * tf + yitaf2) * Math.pow((y / Nf), 3) / 6 + (5 + 28 * tf * tf + 24 * Math.pow(tf, 4) + 6 * yitaf2 + 8 * yitaf2 * tf * tf) * Math.pow((y / Nf), 5) / 120) / Math.cos(Bfi);
            B = Caculate.hudutodms(B);
            L = Caculate.hudutodms(l + Caculate.dmstohudu(L0));
            //endregion


            ed12.setText(String.valueOf(B));
            ed22.setText(String.valueOf(L));
            ed32.setText(String.format("%f",Caculate.Round(H,6)));
        }

    }



    private void delete() {
        ed11.setText("");
        ed12.setText("");
        ed21.setText("");
        ed22.setText("");
        ed31.setText("");
        ed32.setText("");

    }


    @Override
    public void onClick(View v) {

    }




}
package com.liuxueliang.measurementtoolboxes.tool;

import static android.text.method.TextKeyListener.clear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.liuxueliang.measurementtoolboxes.R;
import com.liuxueliang.measurementtoolboxes.universalCode.Caculate;

public class coordinatecalculation_Activity extends AppCompatActivity implements View.OnClickListener {
    //坐标正反算
    RadioGroup radiogroup;
    RadioButton radiobutton_forward,radiobutton_inverse;
    EditText XA,YA,fangweijiao,juli,XB,YB;
    Button button,button_delete;
    double Xa,Ya,fwj,jl,Xb,Yb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_coordinatecalculation);
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        initView();


        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radiobutton_forward.isChecked()){
                    XA.setHint("起点x坐标");
                    YA.setHint("起点y坐标");
                    fangweijiao.setHint("方位角(d.ms)");
                    juli.setHint("距离(m)");
                    XB.setHint("终点x坐标");
                    YB.setHint("终点y坐标");

                }
                else if (radiobutton_inverse.isChecked()){
                    XA.setHint("起点x坐标");
                    YA.setHint("起点y坐标");
                    fangweijiao.setHint("终点x坐标");
                    juli.setHint("终点y坐标");
                    XB.setHint("方位角(d.ms");
                    YB.setHint("距离(m)");

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobutton_forward.isChecked()){
                    forwardCalculate();

                }
                else if(radiobutton_inverse.isChecked()){
                    inverseCalculate();
                }
            }
        });


        //清除按钮
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });




    }

    private void clear() {
        XA.setText("");
        YA.setText("");
        fangweijiao.setText("");
        juli.setText("");
        XB.setText("");
        YB.setText("");

    }


    //坐标正算
    private void forwardCalculate() {
        try{//运行try_catch时程序出现异常不会使程序崩溃
            Xa = Double.parseDouble(XA.getText().toString());
            Ya = Double.parseDouble(YA.getText().toString());
            fwj = Double.parseDouble(fangweijiao.getText().toString());
            jl = Double.parseDouble(juli.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(coordinatecalculation_Activity.this, "请填写全部数据", Toast.LENGTH_SHORT).show();
            return;
        }
        //endregion
        //region 计算
        Xb = Xa + jl * Math.cos(Caculate.dmstohudu(fwj));
        XB.setText(String.format("%f",Xb));
        Yb = Ya + jl * Math.sin(Caculate.dmstohudu(fwj));
        YB.setText(String.format("%f",Yb));
        //endregion


    }
    //坐标反算
    private void inverseCalculate() {
        //region 输入数据检查，不能为空字符
        try{//运行try_catch时程序出现异常不会使程序崩溃
            Xa = Double.parseDouble(XA.getText().toString());
            Ya = Double.parseDouble(YA.getText().toString());
            Xb = Double.parseDouble(fangweijiao.getText().toString());
            Yb = Double.parseDouble(juli.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(coordinatecalculation_Activity.this, "请填写全部数据", Toast.LENGTH_SHORT).show();
            return;
        }
        //endregion
        //region 计算
        fwj = Caculate.hudutodms(Caculate.fangweijiaojisuan(Xa,Ya,Xb,Yb));
        XB.setText(String.format("%f",fwj));
        jl = Caculate.julijisuan(Xa,Ya,Xb,Yb);
        YB.setText(String.format("%f",jl));//保留六位小数
        //endregion

    }

    private void initView() {
        radiogroup = findViewById(R.id.radiogroup);
        radiobutton_forward=findViewById(R.id.radioButton_forward);
        radiobutton_inverse=findViewById(R.id.radioButton_inverse);
        XA=findViewById(R.id.XA);
        YA=findViewById(R.id.YA);
        fangweijiao=findViewById(R.id.fangweijiao);
        juli=findViewById(R.id.juli);
        XB=findViewById(R.id.XB);
        YB=findViewById(R.id.YB);
        button=findViewById(R.id.button);
        button_delete=findViewById(R.id.button_delete);

    }


    @Override
    public void onClick(View v) {

    }
}
package com.liuxueliang.measurementtoolboxes.tool;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.DynamicColors;
import com.liuxueliang.measurementtoolboxes.R;
import com.liuxueliang.measurementtoolboxes.R.id;
import com.liuxueliang.measurementtoolboxes.universalCode.Caculate;

public class azimuthcalculation_Activity extends AppCompatActivity implements View.OnClickListener{
    //方位角计算
    Button fangweijiao_button,fangweijiao_button_delete;
    String qi_X, qi_Y, zhong_X, zhong_Y, result;
    EditText editText_qi_X , editText_qi_Y ,editText_zhong_X,editText_zhong_Y;
    TextView resultShow ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_azimuthcalculation);
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        initView();

        fangweijiao_button=findViewById(R.id.fangweijiao_button);
        fangweijiao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    qi_X = editText_qi_X.getText().toString();
                    qi_Y = editText_qi_Y.getText().toString();
                    zhong_X = editText_zhong_X.getText().toString();
                    zhong_Y = editText_zhong_Y.getText().toString();

                    if (qi_X.length() > 0 & qi_Y.length() > 0 & zhong_X.length() > 0 & zhong_Y.length() > 0) {
                        double startX, startY, endX, endY;
                        startX = Double.parseDouble(qi_X);
                        startY = Double.parseDouble(qi_Y);
                        endX = Double.parseDouble(zhong_X);
                        endY = Double.parseDouble(zhong_Y);
                        result = String.valueOf(Caculate.hudutodms(Caculate.fangweijiaojisuan(startX, startY, endX, endY)));

                        resultShow.setText(String.format("%s", result));
                        Toast.makeText(getApplicationContext(), "计算完成", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "请填写全部数据", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "发生错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

        fangweijiao_button_delete=findViewById(R.id.fangweijiao_button_delete);
        fangweijiao_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });



    }

    private void clear() {
        editText_qi_X.setText("");
        editText_qi_Y.setText("");
        editText_zhong_X.setText("");
        editText_zhong_Y.setText("");
        resultShow.setText("");
    }

    private void initView() {
        editText_qi_X=findViewById(R.id.fangweijiao_qi_X);
        editText_qi_Y=findViewById(R.id.fangweijiao_qi_Y);
        editText_zhong_X=findViewById(R.id.fangweijiao_zhong_X);
        editText_zhong_Y=findViewById(R.id.fangweijiao_zhong_Y);
        resultShow=findViewById(R.id.fangweijiao_result);


    }



    @Override
    public void onClick(View v) {

    }
};
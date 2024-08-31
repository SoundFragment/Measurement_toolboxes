package com.liuxueliang.measurementtoolboxes.tool;

import static com.liuxueliang.measurementtoolboxes.R.id.dms_translate_button1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.liuxueliang.measurementtoolboxes.R;
import com.liuxueliang.measurementtoolboxes.universalCode.Caculate;

public class dmsconvert_Activity extends AppCompatActivity {

    Button button0,button1,dmstranslate_button_dmsdelete,dmstranslate_button_hududelete;
    EditText data_dms,result_hudu,data_hudu,result_dms;
    double dms,dms_result,hudu,hudu_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_dmsconvert);
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        initView();

        button0 = findViewById(R.id.dms_translate_button0);
        button0.setOnClickListener(this::onClick);

        button1 = findViewById(dms_translate_button1);
        button1.setOnClickListener(this::onClick);

    }

    private void initView() {
        data_dms=findViewById(R.id.data_dms);//
        result_hudu = findViewById(R.id.result_hudu);
        data_hudu=findViewById(R.id.data_hudu);
        result_dms = findViewById(R.id.result_dms);


        result_hudu.setFocusable(false);
        result_dms.setFocusable(false);

    }

    private void onClick(View view) {

        //点击转化为弧度的按钮
        button0 = findViewById(R.id.dms_translate_button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dms = Double.parseDouble(data_dms.getText().toString());
                    hudu_result = Caculate.dmstohudu(dms);
                    result_hudu.setText(String.format("%f",hudu_result));
                }
                catch (Exception e){
                    Toast.makeText(dmsconvert_Activity.this, "请填写数据", Toast.LENGTH_SHORT).show();
                }

            }
        });



        //点击转化为度分秒的按钮
        Button button1 = findViewById(dms_translate_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    hudu = Double.parseDouble(data_hudu.getText().toString());
                    dms_result = Caculate.hudutodms(hudu);
                    result_dms.setText(String.format("%f",dms_result));
                }
                catch(Exception e){
                    Toast.makeText(dmsconvert_Activity.this, "请填写数据", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dmstranslate_button_dmsdelete=findViewById(R.id.dmstranslate_button_dmsdelete);
        dmstranslate_button_dmsdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_dms.setText("");
                result_hudu.setText("");

            }
        });

        dmstranslate_button_hududelete=findViewById(R.id.dmstranslate_button_hududelete);
        dmstranslate_button_hududelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_hudu.setText("");
                result_dms.setText("");
            }
        });



    }


}
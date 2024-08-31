package com.liuxueliang.measurementtoolboxes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.liuxueliang.measurementtoolboxes.application.PreInputDataActivity;
import com.liuxueliang.measurementtoolboxes.application.StartRecordingActivity;
import com.liuxueliang.measurementtoolboxes.databinding.ActivityMainBinding;
import com.liuxueliang.measurementtoolboxes.main.aboutFragment;
import com.liuxueliang.measurementtoolboxes.main.applicationFragment;
import com.liuxueliang.measurementtoolboxes.main.toolFragment;
import com.liuxueliang.measurementtoolboxes.tool.angleCalculation_Activity;
import com.liuxueliang.measurementtoolboxes.tool.azimuthcalculation_Activity;
import com.liuxueliang.measurementtoolboxes.tool.coordinatecalculation_Activity;
import com.liuxueliang.measurementtoolboxes.tool.coordinatetransformation_Activity;
import com.liuxueliang.measurementtoolboxes.tool.dmsconvert_Activity;
import com.liuxueliang.measurementtoolboxes.tool.getCoordinates_Activity;
import com.liuxueliang.measurementtoolboxes.tool.levelcheck_Activity;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    Button button_levelcheck;
    Button button_angleCalculation;
    Button button_DMSConversion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化绑定对象
        setContentView(R.layout.activity_main);


        // 应用动态颜色
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        replaceFragment(new toolFragment());



        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int id = item.getItemId();
            if (id==R.id.tool){
                replaceFragment(new toolFragment());
            }
            if (id==R.id.application){
                replaceFragment(new applicationFragment());
            }
            if (id==R.id.about){
                replaceFragment(new aboutFragment());
            }
            return true;
        }
        );
        }



    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }


    //工具界面

    public void levelcheck_Activity(View view){
        Intent intent = new Intent(MainActivity.this,levelcheck_Activity.class);
        startActivity(intent);
    }

    public void angleCalculation_Activity(View view){
        Intent intent = new Intent(MainActivity.this, angleCalculation_Activity.class);
        startActivity(intent);
    }

    public void coordinatetransformation_Activity(View view){
        Intent intent = new Intent(MainActivity.this, coordinatetransformation_Activity.class);
        startActivity(intent);
    }

    public void dmsconvert_Activity(View view){
        Intent intent = new Intent(MainActivity.this, dmsconvert_Activity.class);
        startActivity(intent);
    }

    public void azimuthcalculation_Activity(View view){
        Intent intent = new Intent(MainActivity.this, azimuthcalculation_Activity.class);
        startActivity(intent);
    }

    public void coordinatecalculation_Activity(View view){
        Intent intent = new Intent(MainActivity.this, coordinatecalculation_Activity.class);
        startActivity(intent);
    }

    public void getCoordinates_Activity(View view){
        Intent intent = new Intent(MainActivity.this, getCoordinates_Activity.class);
        startActivity(intent);
    }


    //应用界面
    //水准测量
    public void PreInputDataActivity(View view){
        Intent intent = new Intent(MainActivity.this, PreInputDataActivity.class);
        startActivity(intent);
    }

    public  void StartRecordingActivity(View view){
        Intent intent = new Intent(MainActivity.this, StartRecordingActivity.class);
        startActivity(intent);
    }




    }






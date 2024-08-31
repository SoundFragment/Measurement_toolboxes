package com.liuxueliang.measurementtoolboxes.application;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.liuxueliang.measurementtoolboxes.R;
import com.liuxueliang.measurementtoolboxes.application.PreInputDataActivity;

import java.util.Arrays;
import java.util.List;

public class DataPresenterActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_levelmeasurement_datapresenter);

        listView = findViewById(R.id.listview);

        DatabaseHelper databaseHelper = new DatabaseHelper(DataPresenterActivity.this);

        // 从数据库中获取数据
        List<DataModel> dataModels = databaseHelper.getAll();
        ArrayAdapter<DataModel> adapter = new ArrayAdapter<>(DataPresenterActivity.this, android.R.layout.simple_list_item_1,dataModels);
        listView.setAdapter(adapter);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel = (DataModel) parent.getItemAtPosition(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(DataPresenterActivity.this);
                dialog.setTitle("请选择操作");
                dialog.setNegativeButton("删除",new DialogInterface.OnClickListener(){


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 创建 DatabaseHelper 实例
                        DatabaseHelper dbHelper = new DatabaseHelper(DataPresenterActivity.this);

                        // 调用 deleteOne 方法删除数据
                        String result = dbHelper.deleteOne(dataModel);

                        ArrayAdapter<DataModel> adapter = new ArrayAdapter<>(DataPresenterActivity.this, android.R.layout.simple_list_item_1,dataModels);
                        listView.setAdapter(adapter);



                        // 关闭数据库连接
                        dbHelper.close();

                        // 处理删除结果
                        if (result.equals("success")) {
                            // 删除成功
                            dataModels.remove(dataModel);
                            adapter.notifyDataSetChanged(); // 通知适配器数据已经改变
                            Toast.makeText(DataPresenterActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            // 删除失败
                            dataModels.remove(dataModel);
                            adapter.notifyDataSetChanged(); // 通知适配器数据已经改变
                            Toast.makeText(DataPresenterActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



                dialog.create();
                dialog.show();

            }
        });





    }



    public void onClick(View v) {

    }



}

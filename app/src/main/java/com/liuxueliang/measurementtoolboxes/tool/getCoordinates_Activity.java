package com.liuxueliang.measurementtoolboxes.tool;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liuxueliang.measurementtoolboxes.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class getCoordinates_Activity extends AppCompatActivity {

    Button btnGetLocation;
    EditText et_longitude, et_latitude, et_country, et_countryCode;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private boolean isLocationRequested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_getcoordinates);
        //DynamicColors.applyToActivitiesIfAvailable(this.getApplication());

        btnGetLocation = findViewById(R.id.btn_get_location);
        et_longitude = findViewById(R.id.et_longitude);
        et_latitude = findViewById(R.id.et_latitude);
//        et_country = findViewById(R.id.et_country);
//        et_countryCode = findViewById(R.id.et_countryCode);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "温馨提示：只采用GNSS定位，\n空旷位置更易获取位置。", Toast.LENGTH_LONG).show();
                // 检查是否有位置权限，如果没有，则请求权限
                if (ActivityCompat.checkSelfPermission(getCoordinates_Activity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getCoordinates_Activity.this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getCoordinates_Activity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
                    return;
                }
                getLocation(); // 如果已经有位置权限，则直接获取位置信息
            }
        });
    }

    private void getLocation() {
        // 获取位置逻辑
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // 如果定位未打开，显示相应提示
            Toast.makeText(getApplicationContext(), "请打开定位服务", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "定位未打开");
            return;
        }

        Toast.makeText(getApplicationContext(), "正在获取位置信息...", Toast.LENGTH_SHORT).show();

        locationListener = new LocationListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                et_latitude.setText("纬度：" + latitude);
                et_longitude.setText("经度：" + longitude);
                Log.d(TAG, "获取到位置信息，经度：" + longitude + ", 纬度：" + latitude);
                Toast.makeText(getApplicationContext(), "获取到位置信息：\n经度：" + longitude + ", 纬度：" + latitude, Toast.LENGTH_SHORT).show();


                //有问题
                // 获取国家和国家代码
//                Geocoder geocoder = new Geocoder(getCoordinates_Activity.this, Locale.getDefault());
//                try {
//                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//                    if (addresses != null && addresses.size() > 0) {
//                        Address address = addresses.get(0);
//                        String country = address.getCountryName();
//                        String countryCode = address.getCountryCode();
//                        // 将国家和国家代码显示在相应的EditText中
//                        et_country.setText("国家：" + country);
//                        et_countryCode.setText("国家代码：" + countryCode);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                // 移除超时回调
                removeTimeoutCallback();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(), "定位提供器已禁用", Toast.LENGTH_SHORT).show();
                removeTimeoutCallback();
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(), "定位提供器已启用", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText(getApplicationContext(), "定位提供器状态改变", Toast.LENGTH_SHORT).show();
            }
        };

        // 请求位置更新
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling ActivityCompat#requestPermissions here to request the missing permissions
            return;
        }
        // 请求位置更新，设置时间间隔和距离间隔
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);

        // 设置超时
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (!isLocationRequested) {
                            Toast.makeText(getApplicationContext(), "获取位置信息超时，请稍后重试", Toast.LENGTH_SHORT).show();
                            removeLocationUpdates();
                        }
                    }
                },
                30000); // 超时时间设为30秒
    }

    private void removeTimeoutCallback() {
        new android.os.Handler().removeCallbacksAndMessages(null);
    }

    private void removeLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isLocationRequested = true;
                getLocation();
            } else {
                Toast.makeText(getApplicationContext(), "没有获取定位权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

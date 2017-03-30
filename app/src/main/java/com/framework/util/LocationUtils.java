package com.framework.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.framework.app.CommApp;

import java.util.List;

/**
 * 位置信息的Utils
 *
 * @author luoyl
 */
public class LocationUtils {

    // 纬度
    public static double latitude = 0.0;
    // 经度
    public static double longitude = 0.0;


    /**
     * 纬度
     *
     * @return
     */
    public static double getLatitude() {
        initLocation();
        return latitude;
    }

    /**
     * 经度
     *
     * @return
     */
    public static double getLongitude() {
        initLocation();
        return longitude;
    }

    private static void initLocation() {
        if(longitude > 0.0 || latitude > 0.0){
            return;
        }
        //1.获取位置管理器
        LocationManager locationManager = (LocationManager) CommApp.getApp().getSystemService(Context.LOCATION_SERVICE);
        //2.获取位置提供器，GPS或是NetWork
        List<String> providers = locationManager.getProviders(true);
        String locationProvider = null;
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是网络定位
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS定位
            locationProvider = LocationManager.GPS_PROVIDER;
        } else {
            //Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(CommApp.getApp(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(CommApp.getApp(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        /*// 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
        locationManager.requestLocationUpdates(locationProvider, 0, 0, new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            // 如果位置发生变化，重新显示
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        });*/

    }
}
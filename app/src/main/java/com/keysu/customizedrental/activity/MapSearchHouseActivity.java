package com.keysu.customizedrental.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.keysu.customizedrental.R;
import com.keysu.customizedrental.utils.ToastUtil;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.v3.BottomMenu;

import java.util.ArrayList;
import java.util.List;
//地图找房
public class MapSearchHouseActivity extends AppCompatActivity {

    private static final String TAG = "MapSearchHouseActivity";

    // MapView 是地图主控件
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mMarkerA;
    private Marker mMarkerB;
    private Marker mMarkerC;
    private Marker mMarkerD;

    InfoWindow mInfoWindow;


    String markerId = "";


    GeoCoder mSearch = null;
    // 初始化全局 bitmap 信息，不用时及时 recycle
    private BitmapDescriptor bitmapA = BitmapDescriptorFactory.fromResource(R.drawable.d71);
    private BitmapDescriptor bitmapB = BitmapDescriptorFactory.fromResource(R.drawable.d71);
    private BitmapDescriptor bitmapC = BitmapDescriptorFactory.fromResource(R.drawable.d71);
    private BitmapDescriptor bitmapD = BitmapDescriptorFactory.fromResource(R.drawable.d71);

    public LocationClient mLocationClient;

    private boolean isFirstLocate = true;


    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_map_search_house);

        requestLocationPermissions();

        mMapView =  findViewById(R.id.baidu_mapview);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(13.0f);
        mBaiduMap.setMapStatus(mapStatusUpdate);



        initMarker();
        initListener();


    }


    private void navigateTo(BDLocation location){
        if (isFirstLocate){
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(12f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
        }
    }

    private void requestLocationPermissions() {

        //定义一个权限集合，先判断权限有没有被授权，无 -→ 添加到集合中
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MapSearchHouseActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MapSearchHouseActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MapSearchHouseActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            //将集合转化为数组
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            //一并申请权限
            ActivityCompat.requestPermissions(MapSearchHouseActivity.this, permissions, 1);
        }else {
            requestLocation();
        }

    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);

        /**
         * 添加以下两行，可获取地名信息
         */
        option.setIsNeedAddress(true);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须通过所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                }else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private void initListener() {
// add marker overlay
        LatLng latLngA = new LatLng(39.916042, 116.401825);
        LatLng latLngB = new LatLng(39.934042, 116.423482);
        LatLng latLngC = new LatLng(39.926042, 116.481321);
        LatLng latLngD = new LatLng(39.909042, 116.442153);


        MarkerOptions markerOptionsA = new MarkerOptions()
                .position(latLngA)
                .icon(bitmapA)// 设置 Marker 覆盖物的图标
                .zIndex(9)// 设置 marker 覆盖物的 zIndex
                .draggable(false); // 设置 marker 是否允许拖拽，默认不可拖拽
        markerOptionsA.animateType(MarkerOptions.MarkerAnimateType.grow);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(markerOptionsA));


        MarkerOptions markerOptionsB = new MarkerOptions()
                .position(latLngB)
                .icon(bitmapB)
                .zIndex(5);
        markerOptionsB.animateType(MarkerOptions.MarkerAnimateType.grow);
        mMarkerB = (Marker) (mBaiduMap.addOverlay(markerOptionsB));

        MarkerOptions markerOptionsC = new MarkerOptions()
                .position(latLngC)
                .icon(bitmapC)
                .zIndex(5);
        markerOptionsB.animateType(MarkerOptions.MarkerAnimateType.grow);
        mMarkerC = (Marker) (mBaiduMap.addOverlay(markerOptionsC));

        MarkerOptions markerOptionsD = new MarkerOptions()
                .position(latLngD)
                .icon(bitmapD)
                .zIndex(5);
        markerOptionsB.animateType(MarkerOptions.MarkerAnimateType.grow);
        mMarkerD = (Marker) (mBaiduMap.addOverlay(markerOptionsD));

    }




    private void initMarker() {


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                mBaiduMap.hideInfoWindow();
                final LatLng latLng = marker.getPosition();
                final View infoWindow_view = getLayoutInflater().inflate(R.layout.bubble_map_item_info,null);
                infoWindow_view.setBackgroundResource(R.drawable.triangle_down);
                final TextView tvDiagIntro = infoWindow_view.findViewById(R.id.dialog_intro);

                mSearch = GeoCoder.newInstance();

                OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        x = reverseGeoCodeResult.getAddress();

                        tvDiagIntro.setText(x);

                        if (!markerId.equals(marker.getId())){
                            mBaiduMap.showInfoWindow(mInfoWindow);
                            markerId = marker.getId();
                        }else {
                            mBaiduMap.hideInfoWindow();
                            markerId = "";
                        }
                    }
                };

                mSearch.setOnGetGeoCodeResultListener(listener);

                mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(latLng)
                        // 设置是否返回新数据 默认值0不返回，1返回
                        .newVersion(1));


                TextView tvSeeInfo = infoWindow_view.findViewById(R.id.see_info);
                TextView tvMapGo = infoWindow_view.findViewById(R.id.dialog_mapgo);
                ImageView ivClose = infoWindow_view.findViewById(R.id.dialog_close);

//                String myLocation = "23.255123,116.607167";


                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.see_info:
                                ToastUtil.show("点击了“查看详细”");
                                break;
                            case R.id.dialog_mapgo:

                                BottomMenu.show(MapSearchHouseActivity.this, new String[]{"公交路线", "驾车路线", "步行路线", "骑行路线"}, new OnMenuItemClickListener() {
                                    @Override
                                    public void onClick(String text, int index) {


                                        switch (index) {
                                            case 0:
                                                // 公交路线规划
                                                Intent i1 = new Intent();
                                                i1.setData(Uri.parse("baidumap://map/direction?" +
                                                        "destination=文光塔" +
                                                        "&coord_type=bd09ll" +
                                                        "&mode=transit" +
                                                        "&sy=0" +
                                                        "&index=0" +
                                                        "&target=1" +
                                                        "&src=andr.baidu.openAPIdemo"));
                                                startActivity(i1);
                                                break;
                                            case 1:
                                                // 驾车导航
                                                Intent i2 = new Intent();
                                                i2.setData(Uri.parse("baidumap://map/direction?" +
                                                        "destination=文光塔" +
                                                        "&coord_type=bd09ll" +
                                                        "&mode=driving" +
                                                        "&src=andr.baidu.openAPIdemo"));
                                                startActivity(i2);
                                                break;
                                            case 2:
                                                // 步行路线规划
                                                Intent i3 = new Intent();
                                                i3.setData(Uri.parse("baidumap://map/direction?" +
                                                        "destination=文光塔" +
                                                        "&coord_type=bd09ll" +
                                                        "&mode=walking" +
                                                        "&src=andr.baidu.openAPIdemo"));
                                                startActivity(i3);
                                                break;
                                            case 3:
                                                // 骑行路线规划
                                                Intent i4 = new Intent();
                                                i4.setData(Uri.parse("baidumap://map/direction?" +
                                                        "destination=文光塔" +
                                                        "&coord_type=bd09ll" +
                                                        "&mode=riding" +
                                                        "&src=andr.baidu.openAPIdemo"));
                                                startActivity(i4);
                                                break;
                                        }


                                    }
                                }).setCustomView(R.layout.layout_baidu_map_buttom_menu, new BottomMenu.OnBindView() {
                                    @Override
                                    public void onBind(BottomMenu bottomMenu, View v) {
                                        //绑定布局事件
                                        v.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                                    }
                                });



                                break;
                            case R.id.dialog_close:
                                ToastUtil.show("点击了“关闭”");
                                break;
                        }
                        mBaiduMap.hideInfoWindow();
                    }
                };
                tvSeeInfo.setOnClickListener(onClickListener);
                tvMapGo.setOnClickListener(onClickListener);
                ivClose.setOnClickListener(onClickListener);

                infoWindow_view.setMinimumHeight(550);
                mInfoWindow = new InfoWindow(infoWindow_view,latLng,-47);

//                mBaiduMap.showInfoWindow(mInfoWindow);
                Log.d("TAG", "onMarkerClick: 6"+x);

                return false;
            }
        });

    }



    @Override
    protected void onPause() {
        super.onPause();
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // MapView的生命周期与Activity同步，当activity恢复时必须调用MapView.onResume()
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 回收bitmap资源，防止内存泄露
        bitmapA.recycle();
        bitmapB.recycle();

        // 清除所有图层
        mBaiduMap.clear();
        // MapView的生命周期与Activity同步，当activity销毁时必须调用MapView.destroy()
        mMapView.onDestroy();

        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Log.d(TAG, "onBackPressed: ");

    }
}

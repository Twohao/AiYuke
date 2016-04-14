package com.riyue.aiyuke.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.riyue.aiyuke.R;
import com.riyue.aiyuke.bean.GymnasiumInfo;
import com.riyue.aiyuke.tools.http.HttpUtils;
import com.riyue.aiyuke.tools.http.URLConfig;
import com.riyue.aiyuke.tools.location.LocationService;
import com.riyue.aiyuke.tools.parsejson.JsonTool;
import com.riyue.aiyuke.tools.toast.ToastTool;
import com.riyue.aiyuke.ui.AiYusheApplication;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class NearGymnasiumActivity extends AppCompatActivity {
    @Bind(R.id.bmapView)
    TextureMapView mMapView;
    private BaiduMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_gymnasium);
        ButterKnife.bind(this);

        //发起定位请求
        LocationService mLocationService = new LocationService();
        mLocationService.start();

        initView();
        initGymnasium();


    }

    /**
     * 请求体育馆信息并在地图上显示
     */
    private void initGymnasium() {
        Map<String, String> params = new HashMap<>();
        params.put("lat", AiYusheApplication.lat + "");
        params.put("lng", AiYusheApplication.lon + "");
        HttpUtils.post(URLConfig.MAP_SPORTSCLUB_JSON, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                GymnasiumInfo gymnasiumInfo = JsonTool.parseJson2Object(response, GymnasiumInfo.class);
                if (gymnasiumInfo != null && TextUtils.equals(gymnasiumInfo.getType(), "succ")) {
                    List<GymnasiumInfo.MsgEntity> entityList = gymnasiumInfo.getMsg();
                    for (int i = 0; i < entityList.size(); i++) {
                        GymnasiumInfo.MsgEntity entity = entityList.get(i);
                        LatLng point = new LatLng(Double.valueOf(entity.getLat()), Double.valueOf(entity.getLng()));
                        //构建Marker图标
                        BitmapDescriptor bitmap = BitmapDescriptorFactory
                                .fromResource(R.drawable.mark_default);
                        //构建MarkerOption，用于在地图上添加Marker
                        OverlayOptions option = new MarkerOptions()
                                .title(entity.getTitle())
                                .position(point)
                                .icon(bitmap);
                        //在地图上添加Marker，并显示
                        map.addOverlay(option);
                    }
                }
                map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        ToastTool.ToastShowShort(NearGymnasiumActivity.this,marker.getTitle());
                        return false;
                    }
                });
            }
        });
    }

    private void initView() {
        map = mMapView.getMap();
        location();
    }

    @OnClick(R.id.near_topbar_back)
    public void onClick(View view) {
        finish();
    }

    @OnClick(R.id.near_location)
    public void locationClick() {
        location();
    }

    private void location() {
        LatLng latLng = new LatLng(AiYusheApplication.lat, AiYusheApplication.lon);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(latLng);
        builder.zoom(13);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(builder.build());
        map.animateMapStatus(mapStatusUpdate);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.btg_icon_priority_1_full);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        map.addOverlay(option);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

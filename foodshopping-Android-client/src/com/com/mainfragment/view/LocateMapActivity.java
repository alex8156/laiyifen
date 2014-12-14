package com.com.mainfragment.view;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.com.R;
import com.com.util.BaiduMapTool;

public class LocateMapActivity extends Activity {
	
	/**
	 * MapView 是地图主控件
	 */
	private MapView mapView;
	private BaiduMap baiduMap;
	private GeoCoder mSearch; // 搜索模块，也可去掉地图模块独立使用
	
	// 定位服务 相关 
	private LocationClient locationClient;			// 本地定位客户端
	private BDLocationListener locationListener;	// 定位 监听器
	private LatLng latLng;								// 导航定位地址
	private LocationMode locationMode;				// 显示模式
	private BitmapDescriptor bitmapDescriptorMarker;	// 显示标记
	//	private InfoWindow mInfoWindow;				// 显示窗口，用于显示 具体的地址 
	
	// 当前位置的地址
	private String address;
	
	private TextView locationName;
//	private boolean isFirstLoc = true;// 是否首次定位
	private int level = 18;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locate);
				
		// 获取百度地图控件实例
		mapView = (MapView) findViewById(R.id.bmapView4);
		baiduMap = mapView.getMap();
		
		// 初始化百度地图的位置， 定位所在城市
		this.initMapCenter();
		
		// 初始化搜索模块，注册事件监听
		initSearch();
		
		// 初始化 定位图层
		this.showLocationClient();
		ImageButton localbutton = (ImageButton)findViewById(R.id.localbutton);
		locationName=(TextView)findViewById(R.id.locationName);
		localbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 showLocationClient();
			}
		});
	}
	
	/**
	 * 初始化百度地图的位置， 定位所在城市
	 */
	private void initMapCenter() {
		// ============================================
		// 第3种：创建一张百度地图,  动态显示自己的城市。 默认使用深圳的 经纬度 22.560, 114.064
		Intent intent = getIntent();
		// 当用intent参数时，设置中心点为指定点
		LatLng latLng = null;
		if (intent.hasExtra("latitude") && intent.hasExtra("longitude")) {
			double latitude = intent.getDoubleExtra("latitude", 22.560);
			double longitude = intent.getDoubleExtra("longitude", 114.064);
			System.out.println("初始化城市  纬度：" + latitude + "， 经度：" + longitude);
			latLng = new LatLng(latitude, longitude);
		} else {
			// 默认使用深圳的 经纬度
			latLng = new LatLng(22.560, 114.064);
		}
		
		// 定位当前位置为  地图的中心 
		BaiduMapTool.showLocation(baiduMap, latLng, level);
	}	

	/**
	 * 初始化搜索模块，注册事件监听， 这里主要是取到 地址
	 *	（1）反Geo搜索, 实行定位的时候使用： 
	 * 		mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
	 *	（2）Geo搜索，实行定位的时候使用： 
			mSearch.geocode(new GeoCodeOption().city("深圳").address("南山区深南大道12038号"));
	 */
	public void initSearch() {
		// 初始化搜索模块
		mSearch = GeoCoder.newInstance();
		// 注册事件监听
		mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
			@Override
			public void onGetGeoCodeResult(GeoCodeResult result) {
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(LocateMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_SHORT).show();
					return;
				}
				String strInfo = String.format("纬度：%f 经度：%f", 
						result.getLocation().latitude, result.getLocation().longitude);
				 Toast.makeText(LocateMapActivity.this, strInfo, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(LocateMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_SHORT).show();
					return;
				}
				address = result.getAddress();
				
				// 第一次进入 或者 地址部位空 则定位一次
				if(address != null) {
					locationName.setVisibility(View.VISIBLE);
					locationName.setText(address);
					// 定位
					BaiduMapTool.showLocation(baiduMap, latLng, 0);
					// 增加覆盖物
					BaiduMapTool.addOverLay(baiduMap, latLng, R.drawable.ground_overlay);
					// 显示弹出窗位置信息
					// showInfoWindow(latLng);
					BaiduMapTool.showInfoWindow(LocateMapActivity.this, baiduMap, latLng, address);
				}
				// Toast.makeText(Map03LocationActivity.this, address, Toast.LENGTH_SHORT).show();
			}
		});
	}
	/**
	 *  初始化 定位图层
	 */
	private void showLocationClient() {
		// 开启定位图层,  如果没打开的话 就不会显示定位图层
		baiduMap.setMyLocationEnabled(true);
		
		// 定位服务 初始化, LocationClient类必须在主线程中声明
		locationClient = BaiduMapTool.initLocationClient(this, 0);
		
		// 显示 模式
		locationMode = LocationMode.NORMAL; // LocationMode.FOLLOWING;  LocationMode.COMPASS;
		// 图片标记
		bitmapDescriptorMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
		// 设置定位 的 显示模式 与 标记图片 
		baiduMap.setMyLocationConfigeration(
				new MyLocationConfiguration(locationMode, true, bitmapDescriptorMarker));
		
		locationListener = new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				// map view 销毁后不再处理新接收的位置
				if (location == null || mapView == null){
					return;
				}
				baiduMap.clear();
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				System.out.println("持续定位 纬度：" + latitude + "， 经度：" + longitude);
				
				// 显示 定位数据
				MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
						// 此处设置开发者获取到的方向信息，顺时针0-360
						.direction(100).latitude(latitude).longitude(longitude).build();
				baiduMap.setMyLocationData(locData);
				
				// 导航定位： 会一直改变定位地址
				latLng = new LatLng(latitude, longitude);
				// 反Geo搜索, 实行定位
				mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
				
				// 如果不是导航模式，只不需要这个首次的定义，就需要加上第一次进入的标识
//				if (isFirstLoc) {	
//					isFirstLoc = false;
//					// 改变定位地址
//					LatLng latLng = new LatLng(latitude, longitude);
//					showLocation(latLng, 0);
//					showInfoWindow(latLng);
//				}
			}
		};
		// 注册定位监听函数
		locationClient.registerLocationListener(locationListener);
		locationClient.start();
	}
	@Override
	protected void onPause() {
		// 暂停的时候 取消定位事件
		locationClient.unRegisterLocationListener(locationListener);
		mapView.onPause();
		super.onPause();
	}
	@Override
	protected void onResume() {
		mapView.onResume();
		// 恢复的时候 注册定位事件
		locationClient.registerLocationListener(locationListener);
		super.onResume();
	}
	// 销毁的时候 取消 监听 SDK 广播
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		// 退出时销毁定位
		locationClient.stop();
		// 关闭定位图层
		baiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mapView = null;
	}

}

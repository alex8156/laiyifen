package com.com.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.com.R;


public class BaiduMapTool {
	
	public static double latitude = 0.0;		// 纬度
	public static double longitude = 0.0;	// 经度
	/**
	 * 初始化的时候 获取当前位置的 纬度 和 经度
	 */
	public static void initLocationPosition(Context context) {
		// 定位初始化, 5秒扫描一次
		LocationClient mLocationClient = initLocationClient(context, 20000);
		
		// 定位SDK监听函数
		mLocationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location == null){
					return;
				}
				// 纬度
				latitude = location.getLatitude();
				// 经度
				longitude = location.getLongitude();
				
//				System.out.println(location.getAddrStr());
				 System.out.println("=1=纬度：" + latitude + "， 经度：" + longitude);
			}
		});
		mLocationClient.start();
	}
	
	
	/**
	 * 初始化百度地图的位置， 定位所在城市, 默认是深圳
	 */
	public static  LatLng initMapCenter(Activity activity, BaiduMap baiduMap, int level) {
		// ============================================
		// 第3种：创建一张百度地图,  动态显示自己的城市。 默认使用深圳的 经纬度 22.560, 114.064
		Intent intent = activity.getIntent();
		// 当用intent参数时，设置中心点为指定点
		LatLng latLng = null;
		if (intent.hasExtra("latitude") && intent.hasExtra("longitude")) {
			double latitude = intent.getDoubleExtra("latitude", 22.560);
			double longitude = intent.getDoubleExtra("longitude", 114.064);
			latLng = new LatLng(latitude, longitude);
		} else {
			// 默认使用深圳的 经纬度
			latLng = new LatLng(22.560, 114.064);
		}
		
		// 定位当前位置为  地图的中心 
		BaiduMapTool.showLocation(baiduMap, latLng, level);
		
		return latLng;
	}		
	
	
	
	/**
	 * 定位当前位置为  地图的中心 
	 * @param latLng
	 * @param level		缩放级别
	 */
	public static void showLocation(BaiduMap baiduMap, LatLng latLng, int level) {
		// ===========3.3 显示地图 并改变地点和缩放状态: 先显示地图，再改变地点和缩放 ======
		//定义地图状态, 级别是 0-18, 18是50米
		MapStatus mMapStatus = null;
		if(level>0) {
			mMapStatus = new MapStatus.Builder().target(latLng).zoom(level).build();
		} else {
			mMapStatus = new MapStatus.Builder().target(latLng).build();
		}
		// 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
		baiduMap.animateMapStatus(mapStatusUpdate);
	}
	
	
	/**
	 * 初始化  定位服务， 并设置定位参数
	 * @param context
	 * @param scanSpan		如果扫描时间大于0，则扫描
	 * @return
	 */
	public static LocationClient initLocationClient(Context context,  int scanSpan) {
		LocationClient mLocationClient = new LocationClient(context);
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setIsNeedAddress(true);//返回的定位结果包含地址信息
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setProdName("LocationDemo"); 
		if(scanSpan>0) {
			option.setScanSpan(scanSpan);// 设置定时定位的时间间隔。单位毫秒
		}
		option.SetIgnoreCacheException(true); //忽略启用缓存异常
		mLocationClient.setLocOption(option);	
		return mLocationClient;
	}
	
	
	/**
	 * 	 增加 弹出窗覆盖物
	 *  必须先执行反GEO 搜索: 
	 *  	mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
	 *  
	 * @param activity				Activity实例
	 * @param baiduMap			baiduMap实例
	 * @param latLng				当前经纬度
	 * @param address			图片弹出窗上显示的 地址信息
	 */
	public static  void showInfoWindow(Activity activity, BaiduMap baiduMap, LatLng latLng,  String address) {
		// ========增加 弹出窗覆盖物=========
		// 创建InfoWindow展示的view  
		TextView textView = new TextView(activity);
		textView.setLayoutParams(new ViewGroup.LayoutParams(
        		(ScreenUtil.getScreenDisplay(activity)[0])/2,  ViewGroup.LayoutParams.WRAP_CONTENT ));
		textView.setBackgroundResource(R.drawable.location_tips); // 设置按钮背景
		textView.setTextSize(activity.getResources().getDimension(R.dimen.activity_button_size));	// 设置字体大小
		textView.setTextColor(activity.getResources().getColor(R.color.white));		// 设置字体颜色
		textView.setSingleLine(false);	// 设置可以换行
		/* button.setText("您当前的位置"); */
		// 必须先执行反GEO 搜索,显示当前位置
		textView.setText(address);	
		
		// 定义用于显示该InfoWindow的坐标点  
		// 创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量 
		InfoWindow mInfoWindow = null;				// 显示窗口，用于显示 具体的地址 
		mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(textView), latLng, -2, null);
		// mInfoWindow = new InfoWindow(textView, latLng, -1);
		// 显示InfoWindow  
		baiduMap.showInfoWindow(mInfoWindow);
	}
	
	/**
	 *  增加覆盖物
	 * @param baiduMap		BaiduMap实例
	 * @param latLng			经纬度
	 * @param drawableId	覆盖物图片id
	 */
	public static  void addOverLay(BaiduMap baiduMap, LatLng latLng, int drawableId) {
		BitmapDescriptor bdGround = BitmapDescriptorFactory.fromResource(drawableId);
		// add ground overlay
		LatLng southwest = new LatLng(latLng.latitude-0.001, latLng.longitude-0.001);
		LatLng northeast = new LatLng(latLng.latitude+0.001, latLng.longitude+0.001);
		LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
				.include(southwest).build();

		OverlayOptions ooGround = new GroundOverlayOptions()
				.positionFromBounds(bounds).image(bdGround).transparency(0.8f);
		baiduMap.addOverlay(ooGround);

		MapStatusUpdate u = MapStatusUpdateFactory
				.newLatLng(bounds.getCenter());
		baiduMap.setMapStatus(u);
	}
	
}

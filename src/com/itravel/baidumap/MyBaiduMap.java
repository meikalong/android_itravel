package com.itravel.baidumap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.itravel.baidumap.service.LocationService;

/***
 * ���㶨λʾ��������չʾ�����Ķ�λ�����������LocationService.java�� Ĭ������Ҳ������LocationService���޸�
 * Ĭ�����õ��������ڿ�������̳�жԿ����߳����������������
 * 
 * @author baidu
 *
 */
public class MyBaiduMap {
	private LocationService locationService;

	public MyBaiduMap(Context context) {
		this.locationService = new LocationService(context);
	}

	/***
	 * Stop location service
	 */
	public void onStop() {
		locationService.unregisterListener(mListener); // ע��������
		locationService.stop(); // ֹͣ��λ����
	}

	public void onStart() {
		// ��ȡlocationserviceʵ��������Ӧ����ֻ��ʼ��1��locationʵ����Ȼ��ʹ�ã����Բο�����ʾ����activity������ͨ�����ַ�ʽ��ȡlocationserviceʵ����
		locationService.registerListener(mListener);
		// ע�����
		locationService.start();
	}

	/*****
	 * @see copy funtion to you project
	 *      ��λ����ص�����дonReceiveLocation����������ֱ�ӿ������´��뵽�Լ��������޸�
	 *
	 */
	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (null != location
					&& location.getLocType() != BDLocation.TypeServerError) {
				StringBuffer sb = new StringBuffer(256);
				sb.append("time : ");
				/**
				 * ʱ��Ҳ����ʹ��systemClock.elapsedRealtime()���� ��ȡ�����Դӿ���������ÿ�λص���ʱ�䣻
				 * location.getTime() ��ָ����˳����ν����ʱ�䣬���λ�ò������仯����ʱ�䲻��
				 */
				sb.append(location.getTime());
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nlatitude : ");
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
				sb.append("\nradius : ");
				sb.append(location.getRadius());
				sb.append("\nCountryCode : ");
				sb.append(location.getCountryCode());
				sb.append("\nCountry : ");
				sb.append(location.getCountry());
				sb.append("\ncitycode : ");
				sb.append(location.getCityCode());
				sb.append("\ncity : ");
				sb.append(location.getCity());
				sb.append("\nDistrict : ");
				sb.append(location.getDistrict());
				sb.append("\nStreet : ");
				sb.append(location.getStreet());
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\nDescribe: ");
				sb.append(location.getLocationDescribe());
				sb.append("\nDirection(not all devices have value): ");
				sb.append(location.getDirection());
				sb.append("\nPoi: ");
				if (location.getPoiList() != null
						&& !location.getPoiList().isEmpty()) {
					for (int i = 0; i < location.getPoiList().size(); i++) {
						Poi poi = (Poi) location.getPoiList().get(i);
						sb.append(poi.getName() + ";");
					}
				}
				if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS��λ���
					sb.append("\nspeed : ");
					sb.append(location.getSpeed());// ��λ��km/h
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
					sb.append("\nheight : ");
					sb.append(location.getAltitude());// ��λ����
					sb.append("\ndescribe : ");
					sb.append("gps��λ�ɹ�");
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// ���綨λ���
					// ��Ӫ����Ϣ
					sb.append("\noperationers : ");
					sb.append(location.getOperators());
					sb.append("\ndescribe : ");
					sb.append("���綨λ�ɹ�");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// ���߶�λ���
					sb.append("\ndescribe : ");
					sb.append("���߶�λ�ɹ������߶�λ���Ҳ����Ч��");
				} else if (location.getLocType() == BDLocation.TypeServerError) {
					sb.append("\ndescribe : ");
					sb.append("��������綨λʧ�ܣ����Է���IMEI�źʹ��嶨λʱ�䵽loc-bugs@baidu.com��������׷��ԭ��");
				} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
					sb.append("\ndescribe : ");
					sb.append("���粻ͬ���¶�λʧ�ܣ����������Ƿ�ͨ��");
				} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
					sb.append("\ndescribe : ");
					sb.append("�޷���ȡ��Ч��λ���ݵ��¶�λʧ�ܣ�һ���������ֻ���ԭ�򣬴��ڷ���ģʽ��һ���������ֽ�����������������ֻ�");
				}
				System.out.println("KKKKKKKKKKKKK" + sb.toString());
				if ("161".equals(location.getLocType())) {
					onStop();
				}
			}
		}

	};

	public SDKReceiver getSDKReceiver() {
		return new SDKReceiver();
	}

	public IntentFilter getIntentFilter() {
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		return iFilter;
	}

	/**
	 * ����㲥�����࣬���� SDK key ��֤�Լ������쳣�㲥
	 * ÿ�δ������ʱ����֤һ�£�������û����ȫ�رգ�����ָ����ȫ�ر��Ǳ��������kill�������ǲ����ٴ�У���
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			System.out.println("MMMMMMMMMMMMMMMMM   action: " + s);
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				Toast.makeText(context,
						"key ��֤����! ���� AndroidManifest.xml �ļ��м�� key ����",
						Toast.LENGTH_LONG).show();
			} else if (s
					.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
				Toast.makeText(context, "key ��֤�ɹ�! ���ܿ�������ʹ��", Toast.LENGTH_LONG)
						.show();
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				Toast.makeText(context, "�������", Toast.LENGTH_LONG).show();
			}
		}
	}
}
